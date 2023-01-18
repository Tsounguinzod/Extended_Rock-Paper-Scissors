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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The FileHandler class contains methods for reading and writing player information to a file.
 */
class FileHandler {

    /**
     * Reads the file and returns the contents as a map
     * @return a map of player name and points
     * @throws IOException in case the file is not found
     */
    public static Map<String, Integer> FileIntoMap() throws IOException {
        Map<String, Integer> nameAndPoints = new HashMap<>();
File file = new File("rating.txt");

/* while there are more lines in the file read the next line, split the line into two parts:
the player name and the player's points and then add the player name and points to the map
 */
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] keyValue = line.split(" ");
                nameAndPoints.put(keyValue[0], Integer.parseInt(keyValue[1]));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // return the contents of the file as a single String
        return nameAndPoints;
    }

    /**
     * Updates the file with the latest player information
     * @param player the player whose information will be written to the file
     * @throws IOException in case the file is not accessible or modifiable
     */
    public static void updateFile(Player player) throws IOException{
        Map<String, Integer> playerScores = FileIntoMap(); // get the current player information from the file

        /* if the player already has an entry in the file update the player's points
           else add the player and their points to the map
         */
        if (playerScores.containsKey(player.getName())) {
            playerScores.replace(player.getName(), player.getRating());
        } else {
            playerScores.put(player.getName(), player.getRating());
        }

        // for each player in the map write the player name and points to the file
        try (FileWriter fileWriter = new FileWriter("rating.txt")) {
            for (Map.Entry<String, Integer> entry : playerScores.entrySet()) {
                fileWriter.write(entry.getKey() + " " + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}