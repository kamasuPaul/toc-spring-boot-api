package com.tableofcontents.api.repositories;

import com.tableofcontents.api.entities.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IContentRepository extends JpaRepository<Content,String> {
}
