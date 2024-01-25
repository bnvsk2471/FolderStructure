package com.folder.folderstructure.repository;


import com.folder.folderstructure.entity.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {

    // This method retrieves all folders that have no parent, i.e., root folders.
    List<Folder> findByParentFolderIsNull();

    // This method finds a folder by its name and parent, used to avoid duplicate folder names under the same parent.
    Optional<Folder> findByNameAndParentFolder(String name, Folder parentFolder);

    // Overloaded method to find a root folder by its name (since the parentFolder is null).
    Optional<Folder> findByNameAndParentFolderIsNull(String name);

    // You can define other custom methods as needed.
}
