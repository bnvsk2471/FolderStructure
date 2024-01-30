package com.folder.folderstructure.repository;


import com.folder.folderstructure.entity.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {

    List<Folder> findByParentFolderIsNull();
    Optional<Folder> findByNameAndParentFolder(String name, Folder parentFolder);
    //Optional<Folder> findByNameAndParentFolderIsNull(String name);


}
