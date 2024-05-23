package org.example.builder;

import org.example.model.Parameter;
import org.example.model.Rule;
import org.example.model.SubRule;
import org.example.repository.Rules;
import org.example.io.FileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class RuleBuilder extends ModelBuilder {
    @Override
    public List<Rules> readAnimals(Parameter parameter) throws IOException, URISyntaxException {
        List<Rules> rulesList = new ArrayList<>();
        List<String> rulesData = FileReader.readLinesFromResource("rules.txt");
        for (String line : rulesData
        ) {
            Rules rules = new Rules();
            String[] tokens = line.split(",");
            for (String token : tokens
            ) {
                Rule rule = new Rule();
                if (!token.contains(" OR ")) {
                    rule.setOneRule(createRule(token,parameter));
                } else {
                    String[] subTokens = token.split(" OR ");
                    rule.setOneRule(createRule(subTokens[0], parameter));
                    rule.setTwoRule(createRule(subTokens[1],parameter ));
                }
                if (rule.getOneRule() != null) {
                    rules.getRules().add(rule);
                }
            }
            if (!rules.getRules().isEmpty()) {
                rulesList.add(rules);
            }

        }
        return rulesList;
    }

    private SubRule createRule(String token, Parameter parameter) {
        SubRule rule = new SubRule();
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
        if (rule.getParameter() == null) {
            return null;
        }
        return rule;
    }
}
