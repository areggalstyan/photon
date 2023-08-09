package com.aregcraft.photon;

import java.util.HashMap;
import java.util.Map;

public class EvaluationContext {
    private final Map<String, Double> arguments = new HashMap<>();

    public double getArgument(String name) {
        return arguments.get(name);
    }

    public void setArgument(String name, double value) {
        arguments.put(name, value);
    }
}
