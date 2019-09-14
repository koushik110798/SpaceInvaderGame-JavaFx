/*******************************/
/* Venkat Koushik Muthyapu     */
/* Lab 10                      */
/* CS 251L                     */
/*******************************/

package com.vk.lab9;

import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JPanel;

public abstract class GameObject implements Object2D, GameData {

	int x = 0;
	int y = 0;
	int width = 0;
	int height = 0;

	public GameObject(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return this.y;
	}

	@Override
	public void setX(int newXPosition) {

		this.x = newXPosition;
	}

	@Override
	public void setY(int newYPosition) {

		this.y = newYPosition;
	}
	
	@Override
	public void setWidth(int width) {

		this.width = width;
	}
	
	@Override
	public void setHeight(int height) {

		this.height = height;
	}

	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public int getHeight() {
		return this.height;
	}

	@Override
	public Rectangle getBoundingRectangle() {
		return new Rectangle(x, y, width, height);
	}

	@Override
	public boolean intersects(Object2D other) {

		Rectangle thisRectangle = getBoundingRectangle();

		return thisRectangle.intersects(other.getBoundingRectangle());

	}

	@Override
	public boolean isOutOfBounds() {
		Rectangle gameBoundaries = new Rectangle(0, 0, GAME_BOARD_WIDTH, GAME_BOARD_HEIGHT);
		return !gameBoundaries.contains(getBoundingRectangle());
	}
}
