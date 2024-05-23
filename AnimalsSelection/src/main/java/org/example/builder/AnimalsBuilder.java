package org.example.builder;

import org.example.model.Animal;
import org.example.model.Parameter;
import org.example.io.FileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


public class AnimalsBuilder extends ModelBuilder {
    @Override
    public List<Animal> readAnimals(Parameter parameter) throws IOException, URISyntaxException {
        List<Animal> animals = new ArrayList<>();
        List<String> animalData = FileReader.readLinesFromResource("animals.txt");
        for (String line : animalData
        ) {
            String[] tokens = line.split(",");
            Animal animal = new Animal();
            for (String token : tokens
            ) {
                if (checkParameter(parameter, token)) {
                    animal.getParameters().add(token);
                }
            }
            if (!animal.getParameters().isEmpty()) {
                animals.add(animal);
            }
        }
        return animals;
    }
}
