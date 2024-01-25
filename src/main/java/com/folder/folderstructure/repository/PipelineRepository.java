package com.folder.folderstructure.repository;

import com.folder.folderstructure.entity.Folder;
import com.folder.folderstructure.entity.Pipeline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PipelineRepository extends JpaRepository<Pipeline,Long> {

    // This method retrieves all pipelines within a specific folder.
    List<Pipeline> findByFolder(Folder folder);

    // This method finds a pipeline by its name within a folder, used to ensure pipeline names are unique within a folder.
    Optional<Pipeline> findByNameAndFolder(String name, Folder folder);

    // You can define other custom methods as needed.
}
