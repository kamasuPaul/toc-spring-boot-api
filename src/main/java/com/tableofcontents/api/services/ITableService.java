package com.tableofcontents.api.services;

import com.tableofcontents.api.entities.Table;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ITableService {
    Table add(Table table);

    List get();

    Optional get(String id);

    void delete(String id);
}
