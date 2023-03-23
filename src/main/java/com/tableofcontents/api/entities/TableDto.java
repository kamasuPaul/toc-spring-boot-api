package com.tableofcontents.api.entities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class TableDto {
    @NotNull
    @NotBlank(message = "Name of the table of contents is required")
    private String name;
    private String description;
    @NotBlank(message = "Category is required")
    private String category;

    @NotEmpty
    private List<Content> contentList;

    public Table getTable(){
        return new Table();
    }
}
