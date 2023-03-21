package com.tableofcontents.api.services;

import com.tableofcontents.api.entities.Content;
import com.tableofcontents.api.entities.Table;
import com.tableofcontents.api.repositories.IContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ContentServiceImp implements IContentService{

    final private IContentRepository contentRepository;
    @Override
    public Content add(Table table, Content content) {
        return contentRepository.save(content);
    }

    @Override
    public List get() {
        return contentRepository.findAll();
    }

    @Override
    public Optional get(String id) {
        return contentRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        if (get(id).isPresent()){
            contentRepository.delete((Content) get(id).get());
        }
    }
}
