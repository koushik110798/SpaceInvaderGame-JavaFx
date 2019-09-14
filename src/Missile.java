/*******************************/
/* Venkat Koushik Muthyapu     */
/* Lab 10                      */
/* CS 251L                     */
/*******************************/

package com.vk.lab9;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Missile extends GameObject {

	public boolean isFlameOrange = false;
	public int countForEach = 0;

	public Missile(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {

		return "Missile at (" + getX() + ", " + getY() + ")";

	}

	public void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(getX() - 1, getY() - 15, MISSILE_WIDTH, MISSILE_HEIGHT);

		if (!isFlameOrange && countForEach < COUNT_FOR_FLASH_DISPLAY) {
			// For BLUE Flame behind missile
			g.setColor(Color.GREEN);
			g.fillRect(getX() - 3, getY() - 15 - MISSILE_FLAME_HEIGHT, MISSILE_FLAME_WIDTH, MISSILE_FLAME_HEIGHT);
			isFlameOrange = !isFlameOrange;
			countForEach++;

		} else {
			// For YELLOW Flame behind missile
			g.setColor(Color.RED);
			g.fillRect(getX() - 3, getY() - 15 - MISSILE_FLAME_HEIGHT, MISSILE_FLAME_WIDTH, MISSILE_FLAME_HEIGHT);
			isFlameOrange = !isFlameOrange;
			countForEach++;
			if (countForEach == 2 * COUNT_FOR_FLASH_DISPLAY) {
				countForEach = 0;
			}
		}

	}
}
