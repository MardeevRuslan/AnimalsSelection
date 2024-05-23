package org.example.service;


import org.example.model.Animal;
import org.example.model.Rule;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class AnimalSelection {
    public void run() {
        try {
            animals = new ParserFile().readAnimals();
            rules = new ParserFile().readRules();
            for (Animal a: animals
                 ) {
                System.out.println(a);

            }
            for (Rule r: rules
                 ) {
                System.out.println(r);
            }
        } catch (IOException | URISyntaxException e ) {
            throw new RuntimeException(e);
        }
//        selection();
    }

    private  List<Animal> animals;
    private  List<Rule> rules;



//    private void selection() {
//        Map<Rules, Long> map = new HashMap<>();
//        for (Rules rule: rules
//             ) {
//               Long animalsSelection =  rule.selection(animals);
//               map.put(rule, animalsSelection);
//        }
//        for (Map.Entry<Rules, Long> entry: map.entrySet()
//             ) {
//            System.out.println("Rule: " + entry.getKey().getRules());
//            System.out.println("Selection: " + entry.getValue());
//        }
//    }
}
