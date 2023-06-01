package com.tableofcontents.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TableDto {
    private String id;
    @NotNull
    @NotBlank(message = "Name of the table of contents is required")
    private String name;
    private String description;
    @NotBlank(message = "Category is required")
    private String category;

    @NotEmpty
    @Valid
    private List< @Valid Content> contents;
    @JsonIgnore
    public Table getTable(){
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        return new Table(this.id,this.name,this.description,this.category,userId, LocalDateTime.now(),LocalDateTime.now());
    }
}
