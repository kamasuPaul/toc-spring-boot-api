package com.tableofcontents.api.services;

import com.tableofcontents.api.entities.Table;
import com.tableofcontents.api.repositories.ITableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TableServiceImpl implements  ITableService{

    private final ITableRepository tableRepository;
    @Override
    public Table add(Table table) {
        return tableRepository.save(table);
    }

    @Override
    public List get() {
        return tableRepository.findAll();
    }

    @Override
    public Optional get(String id) {
        return tableRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        if (get(id).isPresent()){
            tableRepository.delete((Table) get(id).get());
        }
    }
}
