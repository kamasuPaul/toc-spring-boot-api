package com.tableofcontents.api.repositories;

import com.tableofcontents.api.entities.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<Table,String> {
}
