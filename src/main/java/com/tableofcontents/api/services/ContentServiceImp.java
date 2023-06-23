package com.tableofcontents.api.services;

import com.tableofcontents.api.entities.Content;
import com.tableofcontents.api.entities.Table;
import com.tableofcontents.api.repositories.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ContentServiceImp implements ContentService {

    final private ContentRepository contentRepository;

    public List<Content> saveContentList(List<Content> contents, Table table) {
        int i =1;
        for (Content content: contents) {
            content.setOrdering(i);
            this.saveContent(content, table,0);
            System.out.println(content);
            i++;
        }
        return this.getAllContentsByTableId(table.getId());

    }

    @Override
    public void deleteByTableId(String tableId) {
        contentRepository.deleteByTableId(tableId);
    }
    private Content saveContent(Content content, Table table,int level) {
        content.setTable(table);
        String groupUUid = UUID.randomUUID().toString();
        content.setGroupUuid(groupUUid);
        content.setLevel(level);
        Content content1 = this.createContent(content);
        int ordering = 1;
        for (Content item : content.getChildren()) {
            item.setParentId(content1.getId());
            item.setOrdering(ordering);
            saveContent(item, table,level+1);
            ordering++;
        }
        return content;
    }

    @Override
    public Content createContent(Content content) {
        content.setCreatedAt(LocalDateTime.now());
        content.setUpdatedAt(LocalDateTime.now());
        return contentRepository.save(content);
    }

    @Override
    public void delete(String id) {
        if (getContentById(id).isPresent()) {
            contentRepository.delete(getContentById(id).get());
        }
    }

    @Override

    public Content updateContent(Content content) {
        content.setUpdatedAt(LocalDateTime.now());
        return contentRepository.save(content);
    }

    @Override

    public List<Content> getAllContentsByTableId(String tableId) {
        return contentRepository.findByTableId(tableId);
    }

    @Override
    public List<Content> getContentsByParentId(String tableId, String parentId) {
        return contentRepository.findByTableIdAndParentIdOrderByOrdering(tableId, parentId);
    }

    @Override
    public Optional<Content> getContentById(String id) {
        return contentRepository.findById(id);
    }
}
