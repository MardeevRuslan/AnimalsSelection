package org.example.service;

import org.example.helper.RuleType;

import java.util.List;

public class RulesFactory {
    public static Rule create(List<String> tokens) {
        Rule rule = new Rule();
        try {
            for (String token : tokens) {
                rule.getRules().add(RuleType.valueOf(token));
            }
            return rule;
        } catch (IllegalArgumentException e) {
            return null;
        }

    }
}
