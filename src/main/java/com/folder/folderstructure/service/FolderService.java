package com.folder.folderstructure.service;

import com.folder.folderstructure.dto.FolderDTO;
import com.folder.folderstructure.dto.PipelineDTO;
import com.folder.folderstructure.entity.Folder;
import com.folder.folderstructure.entity.Pipeline;
import com.folder.folderstructure.repository.FolderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FolderService {

    private final FolderRepository folderRepository;

    @Autowired
    public FolderService(FolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }


    //API FOR CREATING FOLDER
    public Folder createFolder(String name, Long parentId) throws Exception{
        Folder parentFolder=null;
        if (parentId !=null){
            parentFolder=folderRepository.findById(parentId)
                    .orElseThrow(()->new Exception("Parent folder not found"));
        }
        if(folderRepository.findByNameAndParentFolder(name,parentFolder).isPresent()){
            throw new Exception("A folder with this name already exists in the specified location.");
        }
        Folder newFolder=new Folder();
        newFolder.setName(name);
        newFolder.setParentFolder(parentFolder);
        return folderRepository.save(newFolder);
    }

    @Transactional
    public void deleteFolder(Long folderId) throws Exception{
        Folder folder=folderRepository.findById(folderId)
                .orElseThrow();
        deleteSubFoldersAndPipelines(folder);
        folderRepository.delete(folder);
    }

    private void deleteSubFoldersAndPipelines(Folder folder){
        folder.getSubFolders().forEach(this::deleteSubFoldersAndPipelines);
        folderRepository.deleteAll(folder.getSubFolders());
    }

    public FolderDTO getFolderStructure() {
        List<Folder> rootFolders = folderRepository.findByParentFolderIsNull();
        return (FolderDTO) rootFolders.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    private FolderDTO convertToDTO(Folder folder) {
        FolderDTO dto = new FolderDTO();
        dto.setId(folder.getId());
        dto.setName(folder.getName());
        dto.setSubFolders(folder.getSubFolders().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList()));
        dto.setPipelines(folder.getPipelines().stream()
                .map(this::convertPipelineToDTO)
                .collect(Collectors.toList()));
        return dto;
    }

    private PipelineDTO convertPipelineToDTO(Pipeline pipeline) {
        PipelineDTO dto = new PipelineDTO();
        dto.setId(pipeline.getId());
        dto.setName(pipeline.getName());
        return dto;
    }
}
