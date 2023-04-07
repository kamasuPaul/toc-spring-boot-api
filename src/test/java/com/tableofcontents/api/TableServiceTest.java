package com.tableofcontents.api;
import com.tableofcontents.api.entities.Table;
import com.tableofcontents.api.repositories.TableRepository;
import com.tableofcontents.api.services.TableServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TableServiceTest {
    @Mock
    private TableRepository tableRepository;
    @InjectMocks
    private TableServiceImpl tableService;
    @Test
    void createTable(){
        Table table = new Table();
        table.setName("My table");
        table.setCategory("My category ");
        table.setDescription("My description");

        //configure mock repository
        when(tableRepository.save(table)).thenReturn(table);

        //call the service to create table
        Table createdTable = tableService.createTable(table);

        // Verify that the created table matches the input table
        assertEquals(createdTable.getName(), table.getName());
        assertEquals(createdTable.getDescription(), table.getDescription());
        assertNotNull(createdTable.getCreatedAt());
        assertNotNull(createdTable.getUpatedAt());
    }
    @Test
    void testGetTableById(){
        Table table = new Table();
        table.setName("My table");
        table.setCategory("My category ");
        table.setDescription("My description");
        table.setCreatedAt(LocalDateTime.now());
        table.setUpatedAt(LocalDateTime.now());

        when(tableRepository.findById(table.getId())).thenReturn(Optional.of(table));

        Optional optional = tableService.getTableById(table.getId());
        Table retrievedTable = (Table) optional.get();

        // Verify that the retrieved table matches the input table
        assertNotNull(retrievedTable);
        assertEquals(retrievedTable.getName(), table.getName());
        assertEquals(retrievedTable.getDescription(), table.getDescription());
        assertNotNull(retrievedTable.getCreatedAt());
        assertNotNull(retrievedTable.getUpatedAt());
    }
    @Test
    void testGetAllTables(){
        List<Table> tables = new ArrayList<>();
        Table table1 = new Table();
        table1.setName("Test Table 1");
        table1.setDescription("A test table");
        Table table2 = new Table();
        table2.setName("Test Table 2");
        table2.setDescription("Another test table");
        tables.add(table1);
        tables.add(table2);

        when(tableRepository.findAll()).thenReturn(tables);

        // Call service method to get all tables
        List<Table> retrievedTables = tableService.getTables();

        // Verify that the retrieved tables match the input tables
        assertEquals(retrievedTables.size(), tables.size());
        for (int i = 0; i < retrievedTables.size(); i++) {
            assertEquals(retrievedTables.get(i).getName(), tables.get(i).getName());
            assertEquals(retrievedTables.get(i).getDescription(), tables.get(i).getDescription());
        }

    }
}
