package com.tableofcontents.api.services;

import com.tableofcontents.api.entities.Table;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TableService {
    Table createTable(Table table);

    List getTables();

    Table updateTable(Table table);

    Optional getTableById(String id);

    void delete(String id);
}
