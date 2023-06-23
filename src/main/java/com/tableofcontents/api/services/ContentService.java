package com.tableofcontents.api.services;

import com.tableofcontents.api.entities.Content;
import com.tableofcontents.api.entities.Table;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface ContentService {
    Content createContent(Content content);

    Optional getContentById(String id);

    void delete(String id);

    List<Content> getAllContentsByTableId(String tableId);

    List<Content> getContentsByParentId(String tableId, String parentId);

    public Content updateContent(Content content);

    List<Content> saveContentList(List<Content> contents, Table table) ;

    void deleteByTableId(String tableId);
}
