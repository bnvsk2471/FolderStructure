package com.folder.folderstructure.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FolderDTO {
    private Long id;
    private String name;
    private Long parentId;
    private List<FolderDTO> subFolders; // Nested sub-folders
    private List<PipelineDTO> pipelines; // Associated pipelines
}
