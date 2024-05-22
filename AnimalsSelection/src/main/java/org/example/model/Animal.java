package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.helper.Height;
import org.example.helper.Type;
import org.example.helper.Weight;

@Data
@AllArgsConstructor
public class Animal {
    private Height height;
    private Weight weight;
    private Type type;
}
