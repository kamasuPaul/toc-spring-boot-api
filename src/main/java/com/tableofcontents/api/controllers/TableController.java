package com.tableofcontents.api.controllers;

import com.tableofcontents.api.entities.Table;
import com.tableofcontents.api.entities.TableDto;
import com.tableofcontents.api.services.ITableService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TableController {
    private final ITableService  tableService;

    @GetMapping("/tables")
    public ResponseEntity<List<Table>> getAllTables() {
        try {
            List<Table> tableList = tableService.get();
            if (tableList.isEmpty() || tableList.size() == 0) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tableList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/tables/{id}")
    public ResponseEntity<Table> getTable(@PathVariable String id){
        Optional<Table> table = tableService.get(id);
        if(table.isPresent()){
            return new ResponseEntity<Table>(table.get(),HttpStatus.OK);
        }
        return new ResponseEntity<Table>(HttpStatus.OK);
    }

    @PostMapping("/tables")
    public ResponseEntity<Table> saveTable( @Valid @RequestBody TableDto tableDto){
        try {
            return new ResponseEntity<Table>(tableService.add(tableDto.getTable()),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/tables/{id}")
    public ResponseEntity<HttpStatus> deleteTable(@PathVariable String id){
        try{
            tableService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
