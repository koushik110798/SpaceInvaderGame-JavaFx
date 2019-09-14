Genral Controls of the Game
---------------------------

- Left Arrow Key: Left arrow key is used to maove the space ship to the elft.

- Right Arorow jey: it is used to move the space ship to the right.

- spacebar or blank key: it is used to fire a laser from the space ship.

Game score
----------

- Every time the laser shots an aline the score is increased by 6 points.

Description of program internals
--------------------------------

- GamaData.java (interface): it contains all the constants that are required for the game.

- Object2D (interface) - it contains all the required methods which are need to describe the objects in 2d in Jframe. 

- GameObject.java (abstract): every object class that is in the game (Ship, laser, missile, alien) should implement this interface, it contains common methods.It implements Object2D

- shooter.java (interface): this interface contains fire method which needs to be implemented when ever it is needed.

- ship.java: this class implements object2D and Shooter classes. It provides all the information to specify the ship in JFrame. It also additionally contains the draw method which is used to draw the ship in JPanel.It contains fire method which returns Laser object

- Alien.java: this class implements object2D and Shooter classes. It provides all the information to specify the alien in JFrame. It also additionally contains the draw method which is used to draw the Alien in JPanel.It contains fire method which returns Missile object

- Missile.java: this class implements object2D classes. It provides all the information to specify the missile in JFrame. It also additionally contains the draw method which is used to draw the missile in JPanel.

- Laser.java: this class implements object2D classes. It provides all the information to specify the laser in JFrame. It also additionally contains the draw method which is used to draw the laser in JPanel.

- AlienList.java: this class contains 2D array which contains alien objects. This class constructor takes number of rows and column and generate a 2D array with those as length. In this class, we handle the drawing of all the aliens in rows and column. It also contains method to check whether there is any collosion with the laser. This class also contains method which targets the ship based on the position of ship and an alien above the ship. It also handles the aliens position and direction in which it needs to move nextt.

- SpaceInvaderGamePanel.java : This class extends Jpanel and takes the responsibility of drawing the Jpanel which contains the portion of game. It also handles the drawing of all game objects based on the condition of game. It handles all the game stages like game screen before starting, while playing, when paused, when game is over. It also handles the collision of ship and missile by checking the position of missile with position of ship. it also contains the settings for each game level. It manages the scores, life and level stats. It also handles the user keyboard input and take respective actions.

- GameMain.java:  This class contains the main method and has a Jframe and contains two panels one which is gamePanel(SpaceInvaderGamePanel) which contains the game area and other panel contains all the labels and buttons required for the game.

Extras
------

- For every level increse the bacgrount color changes, and the number of aliens increase in each level.

- After every two levels one life of the space ship increases.

- Background sounds, the background sounds enhances the game experience.

Bugs
----

- The game turned out to be very difficult after each level an it is almost imposible to pass level 4.