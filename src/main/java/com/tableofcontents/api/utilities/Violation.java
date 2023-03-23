package com.tableofcontents.api.utilities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
@Getter

public class Violation {
    private String fieldName;
    private String message;
}
