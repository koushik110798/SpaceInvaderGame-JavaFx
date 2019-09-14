/*******************************/
/* Venkat Koushik Muthyapu     */
/* Lab 10                      */
/* CS 251L                     */
/*******************************/

package com.vk.lab9;

import java.awt.Rectangle;

public interface Object2D {

    /** @return x coordinate of upper left corner of object. */
    int getX();

    /** @return y coordinate of upper left corner of object. */
    int getY();
    
    /**
     * set the new position of x
     * @param newXposition new  x coordinate of upper left corner of object.
     */
    void setX(int newXposition);

    /**
     * set the new position of y
     * @param newYposition new  y coordinate of upper left corner of object.
     */
    void setY(int newYposition);

    /** @return object width. */
    int getWidth();

    /** @return object height. */
    int getHeight();

    /**
     * Get the bounding rectangle for the object.
     * @return Bounding rectangle.
     */
    Rectangle getBoundingRectangle();

    /**
     * Does this object intersect another? (Checking if the bounding
     * rectangles intersect will generally suffice.)
     * @param other The other object to check.
     * @return True if objects intersect.
     */
    boolean intersects(Object2D other);

    /** 
     * Is any part of the object outside of the game board?
     * @return True if part of object is out of bounds.
     */
    boolean isOutOfBounds();

	void setWidth(int width);

	void setHeight(int height);
}