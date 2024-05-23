package org.example.io;

import org.example.repository.Rules;

import java.util.Map;

public class ConsoleOutput {
    public static void print(Map<Rules, Integer> map) {
        for (Map.Entry<Rules, Integer> entry : map.entrySet()
        ) {
            System.out.println("Rules:" + entry.getKey() + " " + entry.getValue() + " animals");
        }
    }
}
