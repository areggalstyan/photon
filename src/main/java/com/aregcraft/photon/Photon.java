package com.aregcraft.photon;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Photon {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static Evaluator createEvaluator(String expression, EvaluationContext context) {
        return new Evaluator(new Parser(new Tokenizer(expression).tokenize()).parse(), context);
    }

    public static void main(String[] args) throws IOException {
        try (var scanner = new Scanner(System.in);
             var reader = Files.newBufferedReader(Path.of("context.json"));
             var writer = Files.newBufferedWriter(Path.of("evaluator.json"))) {
            var context = GSON.fromJson(reader, EvaluationContext.class);
            var evaluator = createEvaluator(scanner.nextLine(), context);
            System.out.println(evaluator.evaluate());
            GSON.toJson(evaluator, writer);
        }
    }
}
