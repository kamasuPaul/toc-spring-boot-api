package com.tableofcontents.api.entities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

import java.util.List;


@Data
@ToString
public class ContentDto {
    @NotNull
    @NotBlank(message = "name cannot be empty")
    private String name;
    @NotNull
    private String pageNo;
    private List<Content> children;

}
