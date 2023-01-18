/*
 * Copyright (c) 2022 Beaudelaire Tsoungui Nzodoumkouo. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under My consent.
 *
 * This code is shared on GitHub in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY OF FITNESS FOR A PARTICULAR PURPOSE.
 *
 * Please contact Me at +1 438 509 3906
 * or LinkedIn: https://www.linkedin.com/in/beaudelaire-tsoungui-nzodoumkouo-809744231
 * if you need additional information or have any questions.
 */

package rockpaperscissors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * The Game class contains the game logic of the Extended Rock-Paper-Scissors game.
 */
class Game {

    public static HashMap<String, String[]> ruleMap = new HashMap<>();
    public static int draws = 0;
    public static int loses = 0;
    public static int wins = 0;

    /**
     * starts the game and contains the main loop of the game
     *
     * @param player the current player
     */
    public void startGame(Player player) throws IOException {
        int startScore = player.getRating(); // store the player's starting score

        String[] Options = {"rock", "paper", "scissors"}; // default options
        int count = 0;
        do {
            if (count > 0) {
                System.out.print( ( (isValidOption(Options).size() == 1)? isValidOption(Options) + " is not a valid option" :
                                isValidOption(Options) + " are not valid options") +
                                    " pleas enter a valid list from the above options separated by a comma: ");
            } else {
                System.out.println("""
                        OH OH OH this game is an extended version of the traditional
                                           Rock Paper Scissors game!!
                                           
                                            Here are all the options:
                         Rock, Fire, Scissors, Snake, Human, Tree, Wolf, Sponge, Paper
                                Air, Water, Dragon, Devil, Lightning, Gun
                                            
                        Note: the default options are Rock, Paper and Scissors.
                        Note: use !rating to get your current score or !exit to exit the game.
                        """);
                System.out.print("provide a list of the options separated by a comma: ");
            }

            String options = new Scanner(System.in).nextLine();

            if (!options.equals("")) {
                Options = options.split(", ");
                count++;
            } else {
                System.out.println("\nYou have been assigned the default options");
            }
            }
            while (isValidOption(Options).size() != 0); // check if the input is valid

            System.out.println("Okay, let's start");

            int rating = startScore;
            while (true) {

                System.out.print("\nEnter your option: ");
                String input = new Scanner(System.in).next();

                // check if the input is !rating or !exit
                if (input.equalsIgnoreCase("!rating")) {
                    System.out.println("\nYour rating: " + rating);
                    continue;
                } else if (input.equalsIgnoreCase("!exit")) {
                    FileHandler.updateFile(player); // update the player's score in the file

                    System.out.println((loses <= 0)? "Amazing you have not lost any match": (loses == 1)?
                            "Overall you have lost once" : "Overall you have lost " + loses + " time.");

                    System.out.println("\nBye!");
                    break;
                } else if (!Arrays.stream(Options).toList().contains(input)) {
                    System.out.println(input + " was not part of your list of options");
                    continue;
                }

                String AI = Options[(int) (Math.random() * Options.length)];

                System.out.println(determineWinner(input.toLowerCase(), player.getName(), AI.trim().toLowerCase()));

                rating = startScore + wins * 100 + draws * 50;
                player.setRating(rating);
            }
        }

    /**
     * Validates the provided options
     * @param options the list options to validate
     * @return list of invalid options
     */
        private ArrayList<String> isValidOption (String[] options){
        ArrayList<String> invalidOptions = new ArrayList<>();
            for (String option : options) {
                switch (option.toLowerCase().trim()) {
                    case "rock", "paper", "scissors", "gun", "lightning", "devil", "dragon"
                            , "water", "air", "sponge", "wolf", "tree", "human", "snake", "fire" -> {}
                    default -> invalidOptions.add(option.toLowerCase().trim());
                }
            }
            return invalidOptions;
        }

    /**
     * Establishes the rules of the game, that is which options beats which.
     */
    private static void rules () {
            String[] choices = {"rock", "fire", "scissors", "snake", "human", "tree", "wolf", "sponge",
                    "paper", "air", "water", "dragon", "devil", "lightning", "gun",
                    "rock", "fire", "scissors", "snake", "human", "tree", "wolf"};


            for (int i = 0; i < 15; i++) {
                String[] beats = new String[7];
                for (int j = i + 1, count = 0; count < 7; j++, count++) {
                    beats[count] = choices[j];
                }
                ruleMap.put(choices[i], beats);
            }
        }

    /**
     * Determines the winner of the game
     *
     * @param playerChoice  the player's move
     * @param playerName  the player's name
     * @param AIChoice the AI's move
     * @return a string indicating the winner of the game or if it's a draw
     */
        private static String determineWinner (String playerChoice, String playerName, String AIChoice){
            rules();
            if (playerChoice.equals(AIChoice)) {
                draws++;
                return "There is a draw (" + AIChoice + ")";
            } else if (ruleMap.get(playerChoice) != null) {
                for (String s : ruleMap.get(playerChoice)) {
                    if (s.equals(AIChoice)) {
                        wins++;
                        return "Well done " + playerName + ". The computer chose " + AIChoice + " and failed";
                    }
                }
            }
            loses++;
            return "Sorry, but the computer chose " + AIChoice;
        }
    }