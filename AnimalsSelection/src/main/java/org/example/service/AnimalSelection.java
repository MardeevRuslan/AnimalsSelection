package org.example.service;


import org.example.builder.AnimalsBuilder;
import org.example.builder.ParametersBuilder;
import org.example.builder.RuleBuilder;
import org.example.io.ConsoleOutput;
import org.example.model.Animal;
import org.example.model.Parameter;
import org.example.model.Rule;
import org.example.repository.Rules;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class AnimalSelection {
    private List<Animal> animals;
    private List<Rules> rulesList;

    public void run() {
        try {
            Parameter parameter = new ParametersBuilder().readParameter();
            animals = new AnimalsBuilder().readAnimals(parameter);
            rulesList = new RuleBuilder().readAnimals(parameter);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        ConsoleOutput.print(selection());
    }

    private Map<Rules, Integer> selection() {
        Map<Rules, Integer> map = new HashMap<>();
        for (Rules rules : rulesList
        ) {
            int count = 0;
            for (Animal animal : animals
            ) {
                if (selectionOneAnimal(animal, rules)) {
                    count++;
                }
            }
            map.put(rules, count);
        }
        return map;
    }

    private boolean selectionOneAnimal(Animal animal, Rules rules) {
        for (Rule rule : rules.getRules()
        ) {
            boolean is;
            if (rule.getTwoRule() == null) {
                is = selectionOneRule(rule, animal);
            } else {
                is = selectionTwoRule(rule, animal);
            }


            if (!is) {
                return false;
            }
        }
        return true;
    }

    private Boolean selectionTwoRule(Rule rule, Animal animal) {
        return selectionConditions(rule.getOneRule().getParameter(), animal, rule.getOneRule().getIsIncluded()) ||
                selectionConditions(rule.getTwoRule().getParameter(), animal, rule.getTwoRule().getIsIncluded());
    }

    private Boolean selectionOneRule(Rule rule, Animal animal) {
            return selectionConditions(rule.getOneRule().getParameter(), animal, rule.getOneRule().getIsIncluded());
    }



    private Boolean selectionConditions(String parameter, Animal animal, Boolean flag) {
        return animal.getParameters().contains(parameter) == flag;
    }
}
