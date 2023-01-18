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
import java.util.Scanner;

/**
 * The Player class contains information about the player, such as their name and rating.
 */
class Player {
    private String name;
    private int rating;

    /**
     * Asks player for their name and sets it to the name variable
     * Initialise the players rating with his score in the rating file else zero
     */
    public void setName() {
        System.out.print("Enter your name: ");
        this.name = new Scanner(System.in).nextLine();
        System.out.println("Hello, " + name);

        try {
            this.rating = FileHandler.FileIntoMap().getOrDefault(this.name,0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return the player's name
     */
    public String getName(){
        return this.name;
    }

    /**
     * @return the player's current rating
     */
    public int getRating() {
        // code to get player rating
            return this.rating;
    }

    /**
     *
     * @param rating the rating to set
     */
    public void setRating(int rating) {
        this.rating = rating;
    }
}
