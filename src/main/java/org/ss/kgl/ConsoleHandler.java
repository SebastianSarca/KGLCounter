package org.ss.kgl;

import java.io.Console;

public class ConsoleHandler {

    private static final int BREAK_TIME_IN_SECONDS = 3;

    private Console console;

    public ConsoleHandler() {
        this.console = System.console();
    }

    public void startInteraction() {
        Settings settings = readSettings();
        runAllExercices(settings);
        finishMessage();

    }

    private void finishMessage() {
        System.out.println("Done.");
    }

    private void runAllExercices(Settings settings) {
        System.out.println("Starting......get ready.....");
        wait(1500);
        for (int i = 1; i <= settings.getRepetitions(); i++) {
            System.out.println("[set " + i + "]");
            runSet("Go", settings.getDurationInSeconds());
            if (i != settings.getRepetitions()) {
                runSet("Break", BREAK_TIME_IN_SECONDS);
            }
        }
    }

    private void runSet(String title, int size) {
        System.out.print(title + ": ");
        wait(500);
        for (int j = 1; j <= size; j++) {
            wait(1000);
            System.out.print(j + " ");
        }
        wait(1000);
        System.out.print(" OK");
        wait(1000);
        System.out.println(" ");

    }

    private Settings readSettings() {
        String acceptDefault = console.readLine("default settings (20/10): (y/n) >");
        if (acceptDefault.trim().toLowerCase().equals("y")) {
            return new Settings(20, 10);
        } else {
            return readDetailedSettings();
        }
    }

    private Settings readDetailedSettings() {
        int repetitions = readNumberFromConsole("Number of sets: ");
        int duration = readNumberFromConsole("Duration of a set(in seconds): ");
        return new Settings(repetitions, duration);
    }


    private int readNumberFromConsole(String label) {
        Integer value = null;
        while (value == null) {
            try {
                value = Integer.valueOf(console.readLine(label));
            } catch (NumberFormatException e) {
                System.out.println("Value should be numeric, try again");
            }
        }
        return value;
    }

    private void wait(int numberOfMiliseconds) {
        try {
            Thread.sleep(numberOfMiliseconds);
        } catch (InterruptedException e) {
            System.out.println("I was interupted. exiting......");
            System.exit(0);
        }
    }
}