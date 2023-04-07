package com.tableofcontents.api.entities;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

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

    public Table getTable(){
        return new Table(this.id,this.name,this.description,this.category, LocalDateTime.now(),LocalDateTime.now());
    }
}
