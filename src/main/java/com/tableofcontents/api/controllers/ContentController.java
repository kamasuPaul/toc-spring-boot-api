package com.tableofcontents.api.controllers;

import com.tableofcontents.api.entities.Content;
import com.tableofcontents.api.entities.Table;
import com.tableofcontents.api.services.ContentService;
import com.tableofcontents.api.services.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/api/")
@RestController
@Validated
public class ContentController {
    @Autowired
    private ContentService contentService;

    @Autowired
    private TableService tableService;

    @PostMapping("/tables/{tableId}/contents")
    public ResponseEntity<List<Content>> createContent(@PathVariable String tableId, @RequestBody List<Content> contents) throws ClassNotFoundException {
        Optional option = tableService.getTableById(tableId);
        if (option.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Table with id " + tableId + " not found");
        }
        Table table = (Table) option.get();
        if (table == null) {
            throw new ClassNotFoundException("Table not found with id: " + tableId);
        }
        String groupUUid = UUID.randomUUID().toString();
        for (Content content : contents) {
            content.setTable(table);
            content.setGroupUuid(groupUUid);
            contentService.createContent(content);
        }
        List<Content> createdContents = contentService.getAllContentsByTableId(tableId);
        return new ResponseEntity<>(createdContents, HttpStatus.CREATED);
    }
}
