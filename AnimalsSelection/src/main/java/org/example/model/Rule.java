package org.example.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Rule {
    private final Map<String, Boolean> rule = new HashMap<>();
}
