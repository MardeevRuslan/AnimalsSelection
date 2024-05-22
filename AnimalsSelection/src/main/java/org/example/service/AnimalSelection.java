package org.example.service;

import org.example.helper.RuleType;
import org.example.model.Animal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AnimalSelection {
    public void run() {
        readAnimals();
        readRules();
        selection();
    }

    private final List<Animal> animals = new ArrayList<>();
    private final List<Rule> rules = new ArrayList<>();

    private void readRules() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("rules.txt"))))) {
            String line;
            while ((line = reader.readLine()) != null) {
                List<String> tokens = List.of(line.split(" "));
                Rule rule = RulesFactory.create(tokens);
                if (rule != null) {
                    rules.add(rule);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void readAnimals() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("animals.txt"))))) {
            String line;
            while ((line = reader.readLine()) != null) {
                List<String> tokens = List.of(line.split(" "));
                Animal animal = AnimalsFactory.create(tokens);
                if (animal != null) {
                    animals.add(animal);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void selection() {
        Map<Rule, Long> map = new HashMap<>();
        for (Rule rule: rules
             ) {
               Long animalsSelection =  rule.selection(animals);
               map.put(rule, animalsSelection);
        }
        for (Map.Entry<Rule, Long> entry: map.entrySet()
             ) {
            System.out.println("Rule: " + entry.getKey().getRules());
            System.out.println("Selection: " + entry.getValue());
        }

    }



}
