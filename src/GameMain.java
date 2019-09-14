/*******************************/
/* Venkat Koushik Muthyapu     */
/* Lab 10                      */
/* CS 251L                     */
/*******************************/

package com.vk.lab9;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SpringLayout.Constraints;

public class GameMain implements GameData {

	private static JFrame mainJFrame;

	private static SpaceInvaderGamePanel gamePanel;

	private static JPanel controlAndScorePanel;

	private static JButton startAndPauseButton;
	private static JButton exitButton;

	private static JLabel scoredLabel;

	private static JLabel levelLabel;

	private static JLabel lifesLabel;

	public static void main(String[] args) {

		mainJFrame = new JFrame("Layout Practice");

		mainJFrame.setSize(WIDTH_OF_JFRAME, HEIGHT_OF_JFRAME);
		mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainJFrame.setTitle("Space Invaders");
		mainJFrame.setResizable(false);

		divideFrameIntoTwoPanels();

		addActionButtonAndLabels();

		// gamePanel.add(ship);
		mainJFrame.setVisible(true);

		setComponentFromOnePanelToAnother();
	}

	private static void setComponentFromOnePanelToAnother() {

		gamePanel.setScoredLabel(scoredLabel);
		gamePanel.setLevelLabel(levelLabel);
		gamePanel.setLifesLabel(lifesLabel);

	}

	private static void divideFrameIntoTwoPanels() {
		gamePanel = new SpaceInvaderGamePanel();
		controlAndScorePanel = new JPanel();
		controlAndScorePanel.setBackground(Color.LIGHT_GRAY);
		controlAndScorePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		gamePanel.setBounds(new Rectangle(0, 0, GAME_BOARD_WIDTH, GAME_BOARD_HEIGHT));
		controlAndScorePanel
				.setBounds(new Rectangle(GAME_BOARD_WIDTH, 0, WIDTH_OF_JFRAME - GAME_BOARD_WIDTH, GAME_BOARD_HEIGHT));
		mainJFrame.add(gamePanel);
		mainJFrame.add(controlAndScorePanel);

	}

	public static void addActionButtonAndLabels() {

		JPanel LabelsAndButtonsJPanel = new JPanel();
		LabelsAndButtonsJPanel.setLayout(new BoxLayout(LabelsAndButtonsJPanel, BoxLayout.Y_AXIS));
		LabelsAndButtonsJPanel.setPreferredSize(new Dimension(130, 800));
		LabelsAndButtonsJPanel.setBackground(Color.LIGHT_GRAY);

		// Created the JLabels for the score text and scores
		JLabel scoreTextLabel = new JLabel("Score:");
		scoreTextLabel.setFont(new Font("Serif", Font.BOLD, 20));
		scoreTextLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		scoredLabel = new JLabel("0000");
		scoredLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		scoredLabel.setFont(new Font("Arial", Font.PLAIN, 20));

		// Created the JLabels for the Level text and Levels
		JLabel levelTextLabel = new JLabel("Level:");
		levelTextLabel.setFont(new Font("Serif", Font.BOLD, 20));
		levelTextLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		levelLabel = new JLabel("1");
		levelLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		levelLabel.setFont(new Font("Arial", Font.PLAIN, 20));

		// Created the JLabels for the Life's text and Life's
		JLabel lifeTextLabel = new JLabel("Lifes:");
		lifeTextLabel.setFont(new Font("Serif", Font.BOLD, 20));
		lifeTextLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lifesLabel = new JLabel("3");
		lifesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lifesLabel.setFont(new Font("Arial", Font.PLAIN, 20));

		startAndPauseButton = new JButton("START");
		startAndPauseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		startAndPauseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (startAndPauseButton.getText().equalsIgnoreCase("START")) {
					startAndPauseButton.setText("PAUSE");
					gamePanel.resumeTheGame();
				} else if (startAndPauseButton.getText().equalsIgnoreCase("PAUSE")) {
					startAndPauseButton.setText("RESUME");
					gamePanel.pauseTheGame();
				} else if (startAndPauseButton.getText().equalsIgnoreCase("RESUME")) {
					startAndPauseButton.setText("PAUSE");
					gamePanel.resumeTheGame();
				}else if (startAndPauseButton.getText().equalsIgnoreCase("NEW GAME")) {
					startAndPauseButton.setText("PAUSE");
					gamePanel.startNewGame();
				}
			}

		});
		
		gamePanel.setStartAndPauseButton(startAndPauseButton);

		exitButton = new JButton("EXIT");
		exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		exitButton.setPreferredSize(startAndPauseButton.getPreferredSize());
		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gamePanel.stopBackGroundMusic();
				mainJFrame.dispose(); // Remove mainJFrame
			}
		});

		LabelsAndButtonsJPanel.add(Box.createRigidArea(new Dimension(0, 50)));
		LabelsAndButtonsJPanel.add(scoreTextLabel);
		LabelsAndButtonsJPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		LabelsAndButtonsJPanel.add(scoredLabel);

		LabelsAndButtonsJPanel.add(Box.createRigidArea(new Dimension(0, 50)));
		LabelsAndButtonsJPanel.add(levelTextLabel);
		LabelsAndButtonsJPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		LabelsAndButtonsJPanel.add(levelLabel);

		LabelsAndButtonsJPanel.add(Box.createRigidArea(new Dimension(0, 50)));
		LabelsAndButtonsJPanel.add(lifeTextLabel);
		LabelsAndButtonsJPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		LabelsAndButtonsJPanel.add(lifesLabel);

		LabelsAndButtonsJPanel.add(Box.createRigidArea(new Dimension(0, 150)));
		LabelsAndButtonsJPanel.add(startAndPauseButton);
		LabelsAndButtonsJPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		LabelsAndButtonsJPanel.add(exitButton);
		// Component component= mainJFrame.add(startAndPauseButton);
		controlAndScorePanel.add(LabelsAndButtonsJPanel);

	}

	public interface updateStaffInformationFromGamePanel {
		void updateScore();
	}
	
	
	

}
