package com.tableofcontents.api.services;

import com.tableofcontents.api.entities.Table;
import com.tableofcontents.api.repositories.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Qualifier("imp")
@RequiredArgsConstructor
@Service
@Primary
public class TableServiceImpl implements TableService {

    private final TableRepository tableRepository;
    @Override
    public Table createTable(Table table) {
        table.setCreatedAt(LocalDateTime.now());
        table.setUpatedAt(LocalDateTime.now());
        return tableRepository.save(table);
    }

    @Override
    public List getTables() {
        return tableRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public Table updateTable(Table table) {
        table.setUpatedAt(LocalDateTime.now());
        return tableRepository.save(table);
    }

    @Override
    public Optional getTableById(String id) {
        return tableRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        if (getTableById(id).isPresent()){
            tableRepository.delete((Table) getTableById(id).get());
        }
    }
}
