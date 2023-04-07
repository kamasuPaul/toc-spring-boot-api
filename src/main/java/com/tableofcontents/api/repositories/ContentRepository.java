package com.tableofcontents.api.repositories;

import com.tableofcontents.api.entities.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content,String> {

    //spring Data JPA will generate a SQL query based on the method name  and parameter
    List<Content> findByTableId(String tableId);

    List<Content> findByTableIdAndParentId(String tableId, String parentId);
}
