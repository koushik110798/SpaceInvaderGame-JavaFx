/*******************************/
/* Venkat Koushik Muthyapu     */
/* Lab 10                      */
/* CS 251L                     */
/*******************************/

package com.vk.lab9;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;

public class Laser extends GameObject implements Object2D {

	public Laser(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public String toString() {

		return "Laser at (" + getX() + ", " + getY() + ")";

	}

	public void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(getX()-1, getY()-15, LASER_WIDTH, LASER_HEIGHT);

	}
}
