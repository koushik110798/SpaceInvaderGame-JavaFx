/*******************************/
/* Venkat Koushik Muthyapu     */
/* Lab 10                      */
/* CS 251L                     */
/*******************************/

package com.vk.lab9;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.concurrent.ThreadLocalRandom;

public class AlienList implements GameData {

	// no of row of Aliens
	public int rowsOfAliens;

	// no of column of Aliens
	public int columnOfALiens;

	// total number of aliens
	public int totalNumberOfAliens;

	// total number of aliens killed
	public int totalNumberOfAliensKilled;

	public Alien mapOfALiens[][];

	public int startPointX = 30;
	public int startPointY = 30;

	public int widthOfAlienGroup;
	public int heightOfAlienGroup;

	public int currentDirection = 1;
	public int currentAlienYStep = ALIEN_Y_STEP;
	public int currentAlienSpeed = ALIEN_SPEED;

	public AlienList(int rowsOfAliens, int columnOfALiens) {
		super();
		updateAliens(rowsOfAliens, columnOfALiens);
	}

	public void updateAliens(int rowsOfAliens, int columnOfALiens) {
		this.rowsOfAliens = rowsOfAliens;
		this.columnOfALiens = columnOfALiens;
		this.totalNumberOfAliens = rowsOfAliens * columnOfALiens;

		mapOfALiens = new Alien[rowsOfAliens][columnOfALiens];

		createAllAliens();
	}

	private void createAllAliens() {
		int x = startPointX;
		int y = startPointY;
		for (int i = 0; i < rowsOfAliens; i++) {
			for (int j = 0; j < columnOfALiens; j++) {
				mapOfALiens[i][j] = new Alien(j * ALIEN_WIDTH + x, i * ALIEN_HEIGHT + y, ALIEN_WIDTH, ALIEN_HEIGHT);
				x = x + GAP_ALIEN_X;
			}
			x = startPointX;
			y += GAP_ALIEN_Y;
		}

	}

	public void anAlienKilled(int rowNum, int colNum) {

		mapOfALiens[rowNum][colNum] = null;
		totalNumberOfAliensKilled++;
	}

	public void drawAllAliens(Graphics g) {
		if (isAllAliensInsideGameBounds()) {
			for (int i = 0; i < rowsOfAliens; i++) {
				for (int j = 0; j < columnOfALiens; j++) {
					if (mapOfALiens[i][j] != null) {
						mapOfALiens[i][j].draw(g);
					}
				}
			}
		} else {
			currentDirection = currentDirection * -1;
			for (int i = 0; i < rowsOfAliens; i++) {
				for (int j = 0; j < columnOfALiens; j++) {
					if (mapOfALiens[i][j] != null) {
						mapOfALiens[i][j].setX(mapOfALiens[i][j].getX() + currentDirection * currentAlienSpeed);
						mapOfALiens[i][j].setY(mapOfALiens[i][j].getY() + currentAlienYStep);
						mapOfALiens[i][j].draw(g);
					}
				}
			}
		}
	}

	public boolean isAllAliensInsideGameBounds() {

		// FIRST INCREASE VALUE AND CHECK BOUNDS
		for (int i = 0; i < rowsOfAliens; i++) {
			for (int j = 0; j < columnOfALiens; j++) {
				if (mapOfALiens[i][j] != null) {
					mapOfALiens[i][j].setX(mapOfALiens[i][j].getX() + currentDirection * ALIEN_SPEED);

				}
			}
		}

		for (int i = 0; i < rowsOfAliens; i++) {
			for (int j = 0; j < columnOfALiens; j++) {
				if (mapOfALiens[i][j] != null) {

					if (mapOfALiens[i][j].isOutOfBounds()) {
						return false;
					}
				}
			}
		}

		return true;
	}

	public boolean checkForCollisionOfALiensAndLaser(Laser currentLaser) {
		for (int i = 0; i < rowsOfAliens; i++) {
			for (int j = 0; j < columnOfALiens; j++) {
				if (mapOfALiens[i][j] != null) {

					if (mapOfALiens[i][j].intersects(currentLaser)) {
						totalNumberOfAliensKilled++;
						mapOfALiens[i][j] = null;
						return true;
					}
				}
			}
		}

		return false;
	}

	// this method check for the any alien above the ship and fires missile from
	// that
	public Missile checkAndFireMissile(Ship ship) {
		Missile missile = null;
		Rectangle shipXRectangle = new Rectangle(ship.getX(), 0, SHIP_WIDTH, GAME_BOARD_HEIGHT);
		for (int i = rowsOfAliens - 1; i >= 0; i--) {
			for (int j = columnOfALiens - 1; j >= 0; j--) {
				if (mapOfALiens[i][j] != null) {
					if (mapOfALiens[i][j].getBoundingRectangle().intersects(shipXRectangle)) {
						missile = mapOfALiens[i][j].fire();
						return missile;
					}
				}
			}
		}

		return missile;
	}

	private boolean checkShipIsUnderAliens(Ship ship) {
		// TODO Auto-generated method stub
		return false;
	}

	public void increaseAlienSpeed() {
		currentAlienSpeed += 1;
	}

	public void resetAlienSpeed() {
		currentAlienSpeed = ALIEN_SPEED;
	}

	public void increaseAlienYStep() {
		currentAlienYStep += 5;
	}

	public boolean checkIfAnyAlienTouchesBorder() {
		for (int i = 0; i < rowsOfAliens; i++) {
			for (int j = 0; j < columnOfALiens; j++) {
				if (mapOfALiens[i][j] != null) {

					if (mapOfALiens[i][j].getY()+ALIEN_HEIGHT >= 680) {

						return true;
					}
				}
			}
		}
		return false;
	}

}
