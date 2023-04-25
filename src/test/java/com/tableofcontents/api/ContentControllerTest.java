package com.tableofcontents.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tableofcontents.api.entities.Content;
import com.tableofcontents.api.entities.Table;
import com.tableofcontents.api.services.ContentService;
import com.tableofcontents.api.services.TableService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc
class ContentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContentService contentService;

    @MockBean
    private TableService tableService;

    @Test
    void createContent_returnsCreatedContent() throws Exception {
        String id = "f8a53298-81e0-4e33-a3f7-b82814ca0ffd";
        String tableId = "fc04f4ef-31f6-4c32-aae5-95b593acbf83";
        //mock the tableServicee to return a table
        Table table = new Table(tableId,"My table","Testing table","default",LocalDateTime.now(), LocalDateTime.now());
        Content content = new Content(id,"Content 1","2",1,1,id,null,table,LocalDateTime.now(), LocalDateTime.now(),null);
        Content createdContent = new Content(id,"Content 2","2",1,1,id,null,table,LocalDateTime.now(), LocalDateTime.now(),null);

        List<Content> contents = Arrays.asList(content,createdContent);

        given(contentService.createContent(content)).willReturn(createdContent);
        given(contentService.getAllContentsByTableId(tableId)).willReturn(contents);
        given(tableService.getTableById(tableId)).willReturn(Optional.of(table));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String contentsString = objectMapper.writeValueAsString(contents);
        System.out.println(contentsString);


        mockMvc.perform(MockMvcRequestBuilders.post("/api/tables/"+tableId+"/contents")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(contentsString))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", is("Content 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].table.name", is("My table")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].table.description", is("Testing table")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", is("Content 2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].table.name", is("My table")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].table.description", is("Testing table")));
    }

}
