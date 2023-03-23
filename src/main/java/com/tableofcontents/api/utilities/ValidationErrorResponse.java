package com.tableofcontents.api.utilities;

import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationErrorResponse {
    public List<Violation> violations = new ArrayList<>();

}
