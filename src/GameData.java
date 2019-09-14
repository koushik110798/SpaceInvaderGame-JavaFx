/*******************************/
/* Venkat Koushik Muthyapu     */
/* Lab 10                      */
/* CS 251L                     */
/*******************************/

package com.vk.lab9;

/**
 * Holds some constants for the game in a single location for easy
 * access and modification.
 */
public interface GameData {


    /** Width of a laser fired by player's ship. */
    int LASER_WIDTH = 2;
    /** Height of a laser fired by player's ship. */
    int LASER_HEIGHT = 15;
    /** How far a laser moves in a single step. */
    int LASER_SPEED = 10;

    /** Width of a missile fired by an alien. */
    int MISSILE_WIDTH = 5;
    /** Height of a missile fired by an alien. */
    int MISSILE_HEIGHT = 20;
    /** How far a missile moves in a single step. */
    int MISSILE_SPEED = 4;
    /** Missile Flame width */
    int MISSILE_FLAME_WIDTH = 7;
    /** Missile Flame width */
    int MISSILE_FLAME_HEIGHT = 8;


    /** Width of game area. */
    int GAME_BOARD_WIDTH = 850;
    /** Height of game area. */
    int GAME_BOARD_HEIGHT = 800;
    
    /** Width of JFrame. */
    int WIDTH_OF_JFRAME = 1000;
    /** Height of JFrame. */
    int HEIGHT_OF_JFRAME = 800;
    
    
    /** Width of a alien */
    int ALIEN_WIDTH = 35;
    /** Height of a alien  */
    int ALIEN_HEIGHT = 40;
    
    
    /** gap between aliens in x direction */
    int GAP_ALIEN_X = 10;
    /** gap between aliens in y direction */
    int GAP_ALIEN_Y = 10;
    
    /** How far a alien moves in a single step. */
    int ALIEN_SPEED = 4;
    /** How far a alien moves in a single step. in y direction */
    int ALIEN_Y_STEP = 20;
    
    /** Width of a ship */
    int SHIP_WIDTH = 40;
    /** Height of ship. */
    int SHIP_HEIGHT = 20;
    /** initial x position of ship. */
    int SHIP_INITIAL_XPOSITION = 400;
    /** initial y position of ship. */
    int SHIP_INITIAL_YPOSITION = 710;
    /** initial speed of ship. */
    int INITIAL_SHIP_SPEED = 10;
    
    
    int COUNT_FOR_FLASH_DISPLAY =12;
    
    
    
    
    
    
    

}
