package org.example.service;

import org.example.model.Animal;
import org.example.model.Rule;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParserFile {




    public List<Animal> readAnimals() throws IOException, URISyntaxException {
        List<Animal> animals = new ArrayList<>();
        List<String> animalData = readLinesFromResource("animals.txt");
        for (String line: animalData
             ) {
            String[] properties =  line.split(",");
            if (properties.length == 3) {
                animals.add(new Animal(properties[0],properties[1],properties[2] ));
            }
        }
        return animals;
    }

    public List<Rule> readRules() throws IOException, URISyntaxException {
        List<Rule> rules = new ArrayList<>();
        List<String> rulesData =readLinesFromResource("rules.txt");
        for (String line : rulesData
             ) {
            Rule rule = new Rule();
            String [] tokens = line.split(",");
            for (String token: tokens
                 ) {
                if (token.contains("NO ")) {
                    String [] subTokens = token.split(" ");
                    if (subTokens.length == 2) {
                        rule.getRule().put(subTokens[1], false);
                    }
                }
                rule.getRule().put(token, true);
                rules.add(rule);
            }
        }
        return rules;
    }

    private List<String> readLinesFromResource(String resource) throws IOException, URISyntaxException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Path path = Paths.get(classLoader.getResource(resource).toURI());
        return Files.lines(path).collect(Collectors.toList());
    }
}
