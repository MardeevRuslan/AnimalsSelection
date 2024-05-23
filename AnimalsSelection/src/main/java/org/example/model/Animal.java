package org.example.model;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class Animal {
    private final List<String> parameters = new ArrayList<>();
}
