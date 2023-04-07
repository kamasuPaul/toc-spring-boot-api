package com.tableofcontents.api;

import com.tableofcontents.api.entities.Content;
import com.tableofcontents.api.entities.Table;
import com.tableofcontents.api.repositories.ContentRepository;
import com.tableofcontents.api.services.ContentServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ContentServiceTest {
    @Mock
    private ContentRepository contentRepository;
    @InjectMocks
    private ContentServiceImp contentService;

    @Test
    public void testCreateContent() {

        Table table = new Table();
        table.setId(UUID.randomUUID().toString());
        table.setName("My table");
        table.setCategory("My des");
        table.setDescription("Test");

        Content content = new Content();
        content.setName("Bootstrapping spring");
        content.setPageNo("1");
        content.setLevel(1);
        content.setOrdering(1);
        content.setParentId(null);
        content.setTable(table);

        when(contentRepository.save(content)).thenReturn(content);

        Content addedContent = contentService.createContent(content);

        // Verify that the created content matches the input content
        assertEquals(addedContent.getName(), content.getName());
        assertNull(addedContent.getParentId());
        assertEquals(addedContent.getTable(), content.getTable());
        assertEquals(addedContent.getLevel(), content.getLevel());
        assertEquals(addedContent.getOrdering(), content.getOrdering());
        assertNotNull(addedContent.getCreatedAt());
        assertNotNull(addedContent.getUpdatedAt());

    }
}
