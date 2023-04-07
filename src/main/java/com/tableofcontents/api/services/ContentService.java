package com.tableofcontents.api.services;

import com.tableofcontents.api.entities.Content;

import java.util.List;
import java.util.Optional;

public interface ContentService {
    Content createContent(Content content);

    Optional getContentById(String id);

    void delete(String id);

    List<Content> getAllContentsByTableId(String tableId);

    List<Content> getContentsByParentId(String tableId, String parentId);

    public Content updateContent(Content content);
}