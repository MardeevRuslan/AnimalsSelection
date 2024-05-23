package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
public class Parameter {
    private final Map<String, List<String>> map = new HashMap<>();
}
