package org.example.builder;

import org.example.model.Parameter;
import org.example.io.FileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class ParametersBuilder {
    public Parameter readParameter() throws IOException, URISyntaxException {
        Parameter parameter = new Parameter();
        List<String> parameterData = FileReader.readLinesFromResource("parameters.txt");
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
}
