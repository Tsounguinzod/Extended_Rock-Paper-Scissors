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

/**
 * The Main class is the starting point of the Rock-Paper-Scissors game.
 * It sets up the game and gets the player's information.
 */
class Main{
    /**
     * The main method sets up the game and starts it.
     * @param args command line arguments
     */
    public static void main(String[] args) throws IOException {
        Player player = new Player();
        Game game = new Game();
        player.setName();
        game.startGame(player);
    }
}
