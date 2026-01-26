package com.example.myapplication;

import java.util.Random;

public class NumberGenerator {

    private static final Random random = new Random();

    public static int randomBetween1and99() {
        return random.nextInt(99) + 1; // valores entre 1 y 99
    }
}
