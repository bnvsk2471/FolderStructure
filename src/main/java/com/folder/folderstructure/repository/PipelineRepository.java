package com.folder.folderstructure.repository;

import com.folder.folderstructure.entity.Folder;
import com.folder.folderstructure.entity.Pipeline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PipelineRepository extends JpaRepository<Pipeline,Long> {

    List<Pipeline> findByFolder(Folder folder);
    Optional<Pipeline> findByNameAndFolder(String name, Folder folder);


}
