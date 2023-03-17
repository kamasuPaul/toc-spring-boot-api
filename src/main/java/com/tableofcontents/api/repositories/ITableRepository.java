package com.tableofcontents.api.repositories;

import com.tableofcontents.api.entities.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITableRepository extends JpaRepository<Table,String> {
}
