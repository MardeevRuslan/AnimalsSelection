package org.example.service;

import org.example.model.Rule;
import org.example.repository.Rules;

import java.util.Map;

public class ConsoleOutput {
    static void print(Map<Rules, Integer> map) {
        for (Map.Entry<Rules, Integer> entry : map.entrySet()
        ) {
            System.out.println("Rules:" + entry.getKey() + " " + entry.getValue() + " animals");
        }
    }
}
