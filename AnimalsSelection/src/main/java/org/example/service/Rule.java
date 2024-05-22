package org.example.service;


import lombok.Data;
import org.example.helper.Height;
import org.example.helper.RuleType;
import org.example.helper.Weight;
import org.example.helper.Type;
import org.example.model.Animal;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Data
public class Rule {
    private List<RuleType> rules = new ArrayList<>();

    private Map<RuleType, Predicate<Animal>> filters;

    public Rule() {
        filters = new EnumMap<>(RuleType.class);
        filters.put(RuleType.LIGHT, animal -> animal.getWeight() == Weight.LIGHT);
        filters.put(RuleType.MEDIUM, animal -> animal.getWeight() == Weight.MEDIUM);
        filters.put(RuleType.HEAVY, animal -> animal.getWeight() == Weight.HEAVY);
        filters.put(RuleType.SMALL, animal -> animal.getHeight() == Height.SMALL);
        filters.put(RuleType.LOW, animal -> animal.getHeight() == Height.LOW);
        filters.put(RuleType.HIGH, animal -> animal.getHeight() == Height.HIGH);
        filters.put(RuleType.HERBIVORE, animal -> animal.getType() == Type.HERBIVORE);
        filters.put(RuleType.CARNIVORE, animal -> animal.getType() == Type.CARNIVORE);
        filters.put(RuleType.OMNIVORE, animal -> animal.getType() == Type.OMNIVORE);
        filters.put(RuleType.NO_SMALL, animal -> animal.getHeight() != Height.SMALL);
        filters.put(RuleType.NO_LOW, animal -> animal.getHeight() != Height.LOW);
        filters.put(RuleType.NO_HIGH, animal -> animal.getHeight() != Height.HIGH);
        filters.put(RuleType.NO_LIGHT, animal -> animal.getWeight() != Weight.LIGHT);
        filters.put(RuleType.NO_MEDIUM, animal -> animal.getWeight() != Weight.MEDIUM);
        filters.put(RuleType.NO_HEAVY, animal -> animal.getWeight() != Weight.HEAVY);
        filters.put(RuleType.NO_HERBIVORE, animal -> animal.getType() != Type.HERBIVORE);
        filters.put(RuleType.NO_CARNIVORE, animal -> animal.getType() != Type.CARNIVORE);
        filters.put(RuleType.NO_OMNIVORE, animal -> animal.getType() != Type.OMNIVORE);
    }

    public long selection(List<Animal> animals) {
        return animals.stream()
                .filter(animal -> rules.stream()
                        .anyMatch(rule -> {
                            Predicate<Animal> filter = filters.get(rule);
                            return filter.test(animal);
                        }))
                .count();
    }

}
