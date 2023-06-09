package com.tableofcontents.api.controllers;

import com.tableofcontents.api.entities.Content;
import com.tableofcontents.api.entities.Table;
import com.tableofcontents.api.entities.TableDto;
import com.tableofcontents.api.services.ContentService;
import com.tableofcontents.api.services.TableService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Validated
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TableController {
    private final TableService tableService;
    private final ContentService contentService;

    @GetMapping("/tables")
    public ResponseEntity<List<Table>> getAllTables() {
        try {
            List<Table> tableList = tableService.getTables();
            if (tableList.isEmpty() || tableList.size() == 0) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tableList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tables/{id}")
    public ResponseEntity<TableDto> getTable(@PathVariable String id) {
        Optional<Table> table = tableService.getTableById(id);
        if (table.isPresent()) {
            Table table1 = table.get();
            TableDto tableDto = new TableDto();
            tableDto.setName(table1.getName());
            tableDto.setId(table1.getId());
            tableDto.setDescription(table1.getDescription());
            tableDto.setCategory(table1.getCategory());
            List<Content> contents = contentService.getContentsByParentId(table1.getId(), null);
            tableDto.setContents(contents);
            return new ResponseEntity<>(tableDto, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Table with id " + id + " not found");
        }
    }

    @PostMapping("/tables")
    @Validated
    public ResponseEntity<TableDto> saveTable(@Valid @RequestBody TableDto tableDto) {
        try {
            Table table = tableService.createTable(tableDto.getTable());
            @NotEmpty @Valid List<@Valid Content> contents = tableDto.getContents();
            contentService.saveContentList(contents, table);
            TableDto dto = new TableDto();
            dto.setName(table.getName());
            dto.setId(table.getId());
            dto.setDescription(table.getDescription());
            dto.setCategory(table.getCategory());
            dto.setImageUrl(table.getImageUrl());
            dto.setContentUrl(table.getContentUrl());
            List<Content> savedContents = contentService.getContentsByParentId(table.getId(), null);
            dto.setContents(savedContents);

            return new ResponseEntity<TableDto>(dto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PatchMapping ("/tables/{id}")
    @Validated
    public ResponseEntity<TableDto> updateTable(@Valid @RequestBody TableDto tableDto,@PathVariable String id) {
        try {
            Optional<Table> tableOption = tableService.getTableById(id);
            if (tableOption.isPresent()) {
                Table table = tableOption.get();
                //delete old contents
                contentService.deleteByTableId(id);
                //save new table details
                tableDto.setId(table.getId());
                tableService.updateTable(tableDto.getTable());
                //save new table contents
                @NotEmpty @Valid List<@Valid Content> contents = tableDto.getContents();
                contentService.saveContentList(contents, table);

                TableDto dto = new TableDto();
                dto.setName(table.getName());
                dto.setId(table.getId());
                dto.setDescription(table.getDescription());
                dto.setCategory(table.getCategory());
                dto.setImageUrl(table.getImageUrl());
                dto.setContentUrl(table.getContentUrl());
                List<Content> savedContents = contentService.getContentsByParentId(table.getId(), null);
                dto.setContents(savedContents);
                return new ResponseEntity<TableDto>(dto, HttpStatus.OK);
            }else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Table with id " + id + " not found");
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/tables/{id}")
    public ResponseEntity<HttpStatus> deleteTable(@PathVariable String id) {
        try {
            tableService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Table with id " + id + " not found");
        }
    }
}
