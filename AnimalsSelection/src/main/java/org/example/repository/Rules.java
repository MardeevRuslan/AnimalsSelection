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
            if (rule.getTwoRule() == null) {
                sb.append(rule.getOneRule().getParameter()).append(" = ").append(rule.getOneRule().getIsIncluded()).append(" ");
            } else {
                sb.append(rule.getOneRule().getParameter()).append(" = ").append(rule.getOneRule().getIsIncluded()).append(" ");
                sb.append(" OR ");
                sb.append(rule.getTwoRule().getParameter()).append(" = ").append(rule.getTwoRule().getIsIncluded()).append(" ");
            }

        }
        return sb.toString();
    }
}
