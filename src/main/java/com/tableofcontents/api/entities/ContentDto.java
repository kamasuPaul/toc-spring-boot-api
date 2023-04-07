package com.tableofcontents.api.entities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class ContentDto {
    @NotNull
    @NotBlank(message = "name cannot be empty")
    private String name;
    @NotNull
    private String pageNo;

}
