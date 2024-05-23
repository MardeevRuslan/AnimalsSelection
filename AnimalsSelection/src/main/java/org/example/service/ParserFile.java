package org.example.service;

import org.example.model.Animal;
import org.example.model.Parameter;
import org.example.model.Rule;
import org.example.repository.Rules;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParserFile {

    public Parameter readParameter() throws IOException, URISyntaxException {
        Parameter parameter = new Parameter();
        List<String> parameterData = readLinesFromResource("parameters.txt");
        for (String line : parameterData
        ) {
            String[] tokens = line.split("[,:]");
            String name = tokens[0];
            List<String> param = new ArrayList<>();
            for (int i = 0; i < tokens.length; i++) {
                if (i != 0) {
                    param.add(tokens[i]);
                }
            }
            parameter.getMap().put(name, param);
        }
        return parameter;
    }

    public List<Animal> readAnimals(Parameter parameter) throws IOException, URISyntaxException {
        List<Animal> animals = new ArrayList<>();
        List<String> animalData = readLinesFromResource("animals.txt");
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

    private boolean checkParameter(Parameter parameter, String token) {
        for (Map.Entry<String, List<String>> entry : parameter.getMap().entrySet()
        ) {
            if (entry.getValue().contains(token)) {
                return true;
            }
        }
        return false;
    }

    public List<Rules> readRules(Parameter parameter) throws IOException, URISyntaxException {
        List<Rules> rulesList = new ArrayList<>();
        List<String> rulesData = readLinesFromResource("rules.txt");

        for (String line : rulesData
        ) {
            Rules rules = new Rules();
            String[] tokens = line.split(",");
            for (String token : tokens
            ) {
                Rule rule = new Rule();
                if (token.contains("NO ")) {
                    String[] subTokens = token.split(" ");
                    if (subTokens.length == 2 && checkParameter(parameter, subTokens[1])) {
                        rule.setParameter(subTokens[1]);
                        rule.setIsIncluded(false);
                    }
                } else if (checkParameter(parameter, token)) {
                    rule.setParameter(token);
                    rule.setIsIncluded(true);
                }
                if (rule.getParameter() != null) {
                    rules.getRules().add(rule);
                }
            }
            if (!rules.getRules().isEmpty()) {
                rulesList.add(rules);
            }

        }
        return rulesList;
    }

    private List<String> readLinesFromResource(String resource) throws IOException, URISyntaxException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Path path = Paths.get(classLoader.getResource(resource).toURI());
        return Files.lines(path).collect(Collectors.toList());
    }
}
