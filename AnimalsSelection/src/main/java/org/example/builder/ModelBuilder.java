package org.example.builder;

import org.example.model.Parameter;
import org.example.repository.Rules;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class ModelBuilder {
    public abstract List<?> readAnimals(Parameter parameter) throws IOException, URISyntaxException;

    protected boolean checkParameter(Parameter parameter, String token) {
        for (Map.Entry<String, List<String>> entry : parameter.getMap().entrySet()
        ) {
            if (entry.getValue().contains(token)) {
                return true;
            }
        }
        return false;
    }
}
