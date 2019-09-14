/*******************************/
/* Venkat Koushik Muthyapu     */
/* Lab 10                      */
/* CS 251L                     */
/*******************************/

package com.vk.lab9;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.List;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SpaceInvaderGamePanel extends JPanel implements KeyListener, ActionListener, GameData {

	private static JPanel controlAndScorePanel;
	private JButton startAndPauseButton;

	private JButton exitButton;
	/*
	 * playStatus is 0 when game not started playStatus is 1 when game playing
	 * playStatus is 2 when game is paused playStatus is 3 when game is over
	 */
	private int playStatus = 0;

	private int totalNumberOfAliens = 20;
	private Timer timer;
	private int delay = 15;

	private Ship ship;

	private int shipXPosition = SHIP_INITIAL_XPOSITION;

	private int shipYPosition = SHIP_INITIAL_YPOSITION;

	private Laser currentLaser;
	private ArrayList<Missile> listOfMissiles;

	private AlienList alienList;

	private int rowCountOfAliens = 3;

	private int columnCountOfAliens = 7;

	private JLabel scoredLabel;

	private JLabel levelLabel;

	private JLabel lifesLabel;

	// add 5 for every hit of laser to alien
	private int currentScore = 0;

	// Once all aliens die, increase the level by one
	private int currentLevel = 1;

	// once missile hit the ship reduce the life and when it comes to change the
	// status of game to game over i.e., playStatus = 3
	private int totalNumberOfLives = 3;

	private int totalNumberOfAliensKilledInThisRound = 0;

	private int totalNumberOfAliensInThisRound = 0;

	private int countForFlashScreen = 0;
	private int shipSpeedInThisLevel = INITIAL_SHIP_SPEED;

	// private Alien firstPageAlien;
	private int alienKillScore = 5;

	private Clip musicBackGroundClip;

	private boolean isShipGotHit = false;

	private int shipNotShownCounter = 0;

	private ClassLoader classLoader = getClass().getClassLoader();

	public SpaceInvaderGamePanel() {
		super();

		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();

		ship = new Ship(shipXPosition, shipYPosition, SHIP_WIDTH, SHIP_HEIGHT);

		alienList = new AlienList(rowCountOfAliens, columnCountOfAliens);
		listOfMissiles = new ArrayList<Missile>();
		currentLevel = 1;
		// firstPageAlien = new Alien(0, 0, GAME_BOARD_WIDTH, GAME_BOARD_HEIGHT);
		setSettingForEachRound();
		playBackGroundMusicAllTime();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// timer.start();
		if (playStatus == 1 || playStatus == 0) {
			repaint();
		}

	}

	private void addScoreToScoreLabel() {
		currentScore += alienKillScore;
		scoredLabel.setText(String.valueOf(currentScore));
	}

	public Color getColorBackgroundForGame() {

		if (currentLevel == 1) {
			return Color.BLUE;
		} else if (currentLevel == 2) {
			return Color.BLACK;

		} else if (currentLevel == 3) {
			return Color.PINK;

		} else if (currentLevel == 4) {
			return Color.MAGENTA;

		} else if (currentLevel == 5) {
			return Color.BLACK;

		} else {
			return Color.CYAN;

		}

	}

	public Color getColorBasedOnNumber() {

		if (countForFlashScreen < COUNT_FOR_FLASH_DISPLAY) {
			return Color.RED;
		} else if (countForFlashScreen < 2 * COUNT_FOR_FLASH_DISPLAY) {
			return Color.PINK;

		} else if (countForFlashScreen < 3 * COUNT_FOR_FLASH_DISPLAY) {
			return Color.ORANGE;

		} else if (countForFlashScreen < 4 * COUNT_FOR_FLASH_DISPLAY) {
			return Color.GRAY;

		} else if (countForFlashScreen < 5 * COUNT_FOR_FLASH_DISPLAY) {
			return Color.CYAN;

		} else if (countForFlashScreen < 6 * COUNT_FOR_FLASH_DISPLAY) {
			return Color.YELLOW;

		} else if (countForFlashScreen < 7 * COUNT_FOR_FLASH_DISPLAY) {
			return Color.GREEN;

		} else if (countForFlashScreen < 8 * COUNT_FOR_FLASH_DISPLAY) {
			return Color.PINK;

		} else {
			countForFlashScreen = 0;
		}

		return Color.RED;
	}

	public JLabel getLevelLabel() {
		return levelLabel;
	}

	public JLabel getLifesLabel() {
		return lifesLabel;
	}

	public JLabel getScoredLabel() {
		return scoredLabel;
	}

	public JButton getStartAndPauseButton() {
		return startAndPauseButton;
	}

	private void increaseLevelLabel() {
		currentLevel++;
		if (currentLevel <= 5) {
			levelLabel.setText(String.valueOf(currentLevel));
		} else {
			playStatus = 3;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (playStatus == 1) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				moveShipToLeft(true);

			}

			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				moveShipToLeft(false);
			}

			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				if (currentLaser == null) {
					currentLaser = ship.fire();
					playSound("ship_fire1.wav");
				}
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	private void moveShipToLeft(boolean isLeft) {

		if (isLeft) {
			shipXPosition -= shipSpeedInThisLevel;
			if (shipXPosition < 0) {
				shipXPosition = 0;
			}

			ship.setX(shipXPosition);
		} else {

			if (shipXPosition >= (GAME_BOARD_WIDTH - 40)) {
				shipXPosition = GAME_BOARD_WIDTH - 40;
			} else {
				shipXPosition += shipSpeedInThisLevel;
			}

			ship.setX(shipXPosition);
		}

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		if (playStatus == 1) {
			// background keep changing for each level
			g.setColor(getColorBackgroundForGame());
			g.fillRect(1, 1, GAME_BOARD_WIDTH - 1, GAME_BOARD_HEIGHT - 1);

			if (!isShipGotHit) {
				// ship
				ship.draw(g);
			} else {
				shipNotShownCounter++;
				if (shipNotShownCounter == 15) {
					isShipGotHit = false;
					shipXPosition = SHIP_INITIAL_XPOSITION;
					shipYPosition = SHIP_INITIAL_YPOSITION;
					ship.setX(shipXPosition);
					ship.setY(shipYPosition);
					shipNotShownCounter = 0;
				}

			}

			// border for aliens and ship
			g.setColor(Color.WHITE);
			g.fillRect(0, 680, GAME_BOARD_WIDTH, 5);

			// draw laser and update laser position
			if (currentLaser != null) {

				if (currentLaser.getY() < 0) {
					currentLaser = null;
				} else {
					currentLaser.draw(g);
					currentLaser.setY(currentLaser.getY() - LASER_SPEED);
				}
			}

			// draw all the aliens
			alienList.drawAllAliens(g);

			// Check if the aliens reach the border line between aliens and ship
			if (alienList.checkIfAnyAlienTouchesBorder()) {

				endGame(g);

			}

			/*
			 * Check for collision of laser and alien if collision happens remove laser and
			 * alien
			 */

			if (currentLaser != null) {
				if (alienList.checkForCollisionOfALiensAndLaser(currentLaser)) {

					currentLaser = null;
					playSound("alien_and_laser.wav");
					addScoreToScoreLabel();
					totalNumberOfAliensKilledInThisRound++;

					if (totalNumberOfAliensInThisRound == totalNumberOfAliensKilledInThisRound) {

						increaseLevelLabel();
						if (currentLevel > 5) {
							endGame(g);
							return;
						}
						setSettingForEachRound();
						listOfMissiles = new ArrayList<Missile>();
					}
				}
			}

			if (listOfMissiles.size() > 0) {
				for (int i = 0; i < listOfMissiles.size(); i++) {
					Missile missile = listOfMissiles.get(i);
					if (missile.intersects(ship)) {
						if (totalNumberOfLives == 1) {
							endGame(g);

						} else {
							playSound("ship_and_missile.wav");
							totalNumberOfLives--;
							updateLifeLabel();
							isShipGotHit = true;

						}
						listOfMissiles.remove(missile);
					} else {
						missile.draw(g);
						missile.setY(missile.getY() + MISSILE_SPEED);
						if (missile.isOutOfBounds()) {
							listOfMissiles.remove(missile);
						}
					}

				}
			} else {
				Missile missile = alienList.checkAndFireMissile(ship);
				if (missile != null) {
					listOfMissiles.add(missile);
					playSound("aliens_fire.wav");

				}
			}
		} else if (playStatus == 0) {

			try {
				// BufferedImage backgroundImage = ImageIO.read(new
				// File(classLoader.getResource("first_screen_background.png").getFile()));
				BufferedImage backgroundImage = ImageIO.read(loadImageFile("first_screen_background.png"));
				// AudioInputStream audioInputStream =
				// AudioSystem.getAudioInputStream(loadFile(soundName));

				g.drawImage(backgroundImage.getScaledInstance(GAME_BOARD_WIDTH, GAME_BOARD_HEIGHT, Image.SCALE_SMOOTH),
						0, 0, this);

			} catch (IOException e) {

				e.printStackTrace();
			}

			g.setColor(getColorBasedOnNumber());

			g.setFont(new Font("serif", Font.BOLD, 50));
			g.drawString("SPACE INVADERS", 200, 100);
			countForFlashScreen++;
			g.setColor(Color.RED);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.drawString("Click on Start Button to start", 200, 300);

		}

	}

	private void endGame(Graphics g) {
		playStatus = 3;
		startAndPauseButton.setText("NEW GAME");

		g.setColor(getColorBasedOnNumber());

		g.setFont(new Font("serif", Font.BOLD, 50));
		g.drawString("GAME OVER", 200, 100);
		countForFlashScreen++;
		g.setColor(Color.RED);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		if (currentLevel > 5)
			g.drawString("You Won", 200, 200);
		String finalScore = "Final Score is " + currentScore;
		g.drawString(finalScore, 200, 300);

	}

	public void pauseTheGame() {
		if (playStatus == 1) {
			playStatus = 2;
			playBackGroundMusicAllTime();
		}
	}

	public void playBackGroundMusicAllTime() {
		stopBackGroundMusic();
		if (playStatus == 0) {
			playGameSound("game_sound1.wav");
		} else if (playStatus == 1) {
			playGameSound("game_sound2.wav");
		} else if (playStatus == 2) {
			playGameSound("game_sound3.wav");
		}

	}

	public void playGameSound(String soundName) {

		try {

			// AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new
			// File(classLoader.getResource(soundName).getFile()).getAbsoluteFile());
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(loadAudioFile(soundName));
			musicBackGroundClip = AudioSystem.getClip();
			musicBackGroundClip.open(audioInputStream);
			FloatControl gainControl = (FloatControl) musicBackGroundClip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-10.0f); // Reduce volume by 10 decibels.
			musicBackGroundClip.loop(Integer.MAX_VALUE);
			musicBackGroundClip.start();

		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}

	/*
	 * source to add audio file for gun shot
	 * http://suavesnippets.blogspot.com/2011/06/add-sound-on-jbutton-click-in-java.
	 * html
	 */
	public void playSound(String soundName) {
		try {

			// AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new
			// File(classLoader.getResource(soundName).getFile()).getAbsoluteFile());
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(loadAudioFile(soundName));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();

		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}

	public void resumeTheGame() {

		if (playStatus == 0 || playStatus == 2) {
			playStatus = 1;
			this.grabFocus();
			this.requestFocus();
			playBackGroundMusicAllTime();
		}
	}

	public void setLevelLabel(JLabel levelLabel) {
		this.levelLabel = levelLabel;
	}

	public void setLifesLabel(JLabel lifesLabel) {
		this.lifesLabel = lifesLabel;
	}

	public void setScoredLabel(JLabel scoredLabel) {
		this.scoredLabel = scoredLabel;
	}

	private void setSettingForEachRound() {

		if (currentLevel == 1) {
			// total aliens = 21
			rowCountOfAliens = 3;
			columnCountOfAliens = 7;

		} else if (currentLevel == 2) {
			// total aliens = 28
			rowCountOfAliens = 4;
			columnCountOfAliens = 7;
			alienList.increaseAlienYStep();
			shipSpeedInThisLevel += 3;
		} else if (currentLevel == 3) {
			// total aliens = 32
			rowCountOfAliens = 4;
			columnCountOfAliens = 8;
			totalNumberOfLives++;
			alienList.increaseAlienSpeed();
			shipSpeedInThisLevel += 3;
		} else if (currentLevel == 4) {
			// total aliens = 36
			rowCountOfAliens = 4;
			columnCountOfAliens = 9;
			alienList.increaseAlienYStep();
			shipSpeedInThisLevel += 3;
		} else if (currentLevel == 5) {
			// total aliens = 45
			rowCountOfAliens = 5;
			columnCountOfAliens = 9;
			totalNumberOfLives++;
			alienList.increaseAlienSpeed();
		}

		alienKillScore++;
		totalNumberOfAliensInThisRound = rowCountOfAliens * columnCountOfAliens;
		totalNumberOfAliensKilledInThisRound = 0;
		alienList.updateAliens(rowCountOfAliens, columnCountOfAliens);
		updateLifeLabel();
	}

	public void setStartAndPauseButton(JButton startAndPauseButton) {
		this.startAndPauseButton = startAndPauseButton;
	}

	public void startNewGame() {
		ship = new Ship(SHIP_INITIAL_XPOSITION, SHIP_INITIAL_YPOSITION, SHIP_WIDTH, SHIP_HEIGHT);

		alienList = new AlienList(rowCountOfAliens, columnCountOfAliens);
		listOfMissiles = new ArrayList<Missile>();
		currentLevel = 1;
		setSettingForEachRound();
		playBackGroundMusicAllTime();
		currentScore = 0;
		currentLevel = 1;
		totalNumberOfLives = 3;
		playStatus = 0;
		alienKillScore = 5;
		resumeTheGame();
		currentLaser = null;
		updateLevelLabel();
		updateScoreLabel();
		updateLifeLabel();

	}

	private void updateLevelLabel() {
		levelLabel.setText(String.valueOf(currentLevel));
	}

	private void updateScoreLabel() {
		scoredLabel.setText(String.valueOf(currentScore));
	}

	public void stopBackGroundMusic() {
		if (musicBackGroundClip != null) {

			if (musicBackGroundClip.isActive()) {
				musicBackGroundClip.stop();
			}
		}
	}

	private void updateLifeLabel() {
		if (lifesLabel != null)
			lifesLabel.setText(String.valueOf(totalNumberOfLives));
	}

	public static InputStream loadImageFile(String filepath) {

		InputStream input = SpaceInvaderGamePanel.class.getResourceAsStream(filepath);

		if (input == null) {
			input = SpaceInvaderGamePanel.class.getResourceAsStream("/" + filepath);

		}

		return input;
	}

	public static URL loadAudioFile(String filepath) {

		URL input = SpaceInvaderGamePanel.class.getResource(filepath);

		if (input == null) {
			input = SpaceInvaderGamePanel.class.getResource("/" + filepath);

		}

		return input;
	}
}
