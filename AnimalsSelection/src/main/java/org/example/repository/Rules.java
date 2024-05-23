package org.example.repository;

import lombok.Getter;
import lombok.Setter;
import org.example.model.Rule;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Rules {
    private final List<Rule> rules = new ArrayList<>();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Rule rule: rules
             ) {
            sb.append(rule.getParameter()).append(" = ").append(rule.getIsIncluded()).append(" ");
        }
        return sb.toString();
    }
}
