package com.folder.folderstructure.service;

import com.folder.folderstructure.entity.Folder;
import com.folder.folderstructure.entity.Pipeline;
import com.folder.folderstructure.repository.FolderRepository;
import com.folder.folderstructure.repository.PipelineRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PipelineService {

    private final PipelineRepository pipelineRepository;
    private final FolderRepository folderRepository;

    @Autowired
    public PipelineService(PipelineRepository pipelineRepository, FolderRepository folderRepository) {
        this.pipelineRepository = pipelineRepository;
        this.folderRepository = folderRepository;
    }

    @Transactional
    public Pipeline createPipeline(String name, Long folderId) throws Exception {
        Folder folder = folderRepository.findById(folderId)
                .orElseThrow(() -> new Exception("Folder not found"));

        if (pipelineRepository.findByNameAndFolder(name, folder).isPresent()) {
            throw new Exception("A pipeline with this name already exists in the specified folder.");
        }

        Pipeline newPipeline = new Pipeline();
        newPipeline.setName(name);
        newPipeline.setFolder(folder);
        return pipelineRepository.save(newPipeline);
    }

}
