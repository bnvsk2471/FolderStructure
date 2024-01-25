package com.folder.folderstructure.controller;

import com.folder.folderstructure.entity.Folder;
import com.folder.folderstructure.dto.FolderBinding;
import com.folder.folderstructure.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/folders")
public class FolderController {

    private final FolderService folderService;
    @Autowired
    public FolderController(FolderService folderService) {
        this.folderService = folderService;
    }


    @PostMapping("/create")
    public ResponseEntity<Folder> createFolder(
            @RequestBody FolderBinding folderBinding
            ){
        try {
            Folder folder = folderService.createFolder(folderBinding.getName(), folderBinding.getParentId());
            return new ResponseEntity<>(folder,HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
