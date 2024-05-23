package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Animal {
    private String height;
    private String weight;
    private String type;
}
