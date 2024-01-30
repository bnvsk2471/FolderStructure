package com.folder.folderstructure.controller;

import com.folder.folderstructure.entity.Folder;
import com.folder.folderstructure.dto.FolderBinding;
import com.folder.folderstructure.exception.FolderNotFoundException;
import com.folder.folderstructure.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/folders")
public class FolderController {

    private final FolderService folderService;
    @Autowired
    public FolderController(FolderService folderService) {
        this.folderService = folderService;
    }


    //API FOR CREATING FOLDER
    @PostMapping("/create")
    public ResponseEntity<String> createFolder(
            @RequestBody FolderBinding folderBinding
            ) {
        Folder folder = null;
        try {
            folder = folderService.createFolder(folderBinding.getName(), folderBinding.getParentId());
        } catch (Exception e) {
            throw new FolderNotFoundException(e.getMessage());
        }
        return new ResponseEntity<>("Folder Created successfully",HttpStatus.CREATED);
    }

    @DeleteMapping("/{folderId}")
    public ResponseEntity<?> deleteFolder(@PathVariable Long folderId) {
        try {
            folderService.deleteFolder(folderId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (FolderNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(FolderNotFoundException.class)
    public ResponseEntity<String> handleFolderNotFoundException(FolderNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
