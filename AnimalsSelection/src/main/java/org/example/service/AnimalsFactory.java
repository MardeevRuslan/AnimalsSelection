package org.example.service;

import org.example.helper.Height;
import org.example.helper.Type;
import org.example.helper.Weight;
import org.example.model.Animal;

import java.util.List;

public class AnimalsFactory {
    public static Animal create(List<String> character){
        if (character.size() != 3)
            return null;
        try {
            return new Animal(Height.valueOf(character.get(0)), Weight.valueOf(character.get(1)), Type.valueOf(character.get(2)));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
