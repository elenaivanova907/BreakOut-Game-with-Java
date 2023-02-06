# BreakOut-Game-with-Java

I have created a Brick Breaker game using Java. The game is played using the left and right arrows on the keyboard, which move the paddle left and right. The ball should bounce off the paddle and hit the bricks above, causing the bricks to disappear. When all bricks have been hit, a “Congratulations” message is displayed. If the ball falls below the paddle, you lose the game and a “Gave Over” message appears on the screen.
The program consists of 8 classes and an interface:
interface Commons
The interface is used as a “container” for some values used by the other classes, so that they are not constantly repeating inside the classes.

   	int WIDTH = 300; - width of the window
   	int HEIGHT = 400; - height of the window
    int BOTTOM_EDGE = 390; - specifies a “line” and when the ball crosses that          line the game ends
   	int N_OF_BRICKS = 30; - number of bricks displayed
   	int INIT_PADDLE_X = 200; - x coordinate for the paddle
   	int INIT_PADDLE_Y = 360; - y coordinate for the paddle
   	int INIT_BALL_X = 230; - x coordinate for the ball
   	int INIT_BALL_Y = 355; - y coordinate for the ball
   	int PERIOD = 10; - delay for the timer
    
class Common
Here are stored all common properties of the objects (ball, paddle, bricks), hence there is no constructor for this class.

   	int x; - stores x value for displaying the object
   	int y; - stores y value for displaying the object
   	int imageWidth; - width of the image
   	int imageHeight; - height of the image
   	Image image; - image object
    
In addition to all getters and setters, the function of type Rectangle getRect() returns a rectangle with the specific measurements of the object, based on the image used. Also, a method getImageDimensions() is declared and is used to get the specific image width and height of every object. (It is named getImageDimentions() not setImageDimentions() because it is “getting” the dimensions of the image and not “setting” them since I am not changing the image dimensions)

class Ball
Class Ball extends the properties of the Common class, introducing 2 new private variables int xdir and int ydir, which are used to move the ball along the x and y axis. Apart from the usual getters and setters I have:

public Ball() – constructor with an initBall() function inside*
private void initBall() – responsible for the image of the ball – loading the image and getting the dimensions of the image*
resetState() – resets the x and y to their initial value in the Commons interface
move() – moves the ball on the screen. With the if statements I check where the ball is and where it should go on the x and y axis.
private void loadImage() – loads the image from the resmyces folder, it is private because I do not want the user to have access to this function outside the class. (variable of type var is used because of casting issues)
 
class Brick
Class Brick again extends the properties of the Common (parent) class and adds an additional boolean variable called destroyed, which is responsible for the “disappearing” of the bricks.

public Brick(int x, int y) – constructor with an initBrick(x, y) function, which is constructed on the same logic as the constructor in the Ball class (see *)
private void loadImage() – again, this function loads the image from the resmyces folder (variable of type var is used because of casting issues)

class Paddle
Class Paddle extends the parent Common class, implementing an additional variable (dx), responsible for locating and moving the paddle on the x axis (the paddle moves only left and right, not up and down and that is why I do not have a variable for the y axis)

public Paddle() – constructor built the same way and for the same reasons as in the previous two classes (see class Brick, class Ball, or *)
keyPressed() and keyReleased() – deal with the key commands from the user where:

➔	in keyPressed(): when the user presses the left arrow the value of dx becomes -1
when the user presses the right arrow the value of dx becomes 1
➔	in keyReleased(): when the user presses the left or right arrow the value of dx     becomes 0
void move() – deals with the movement of the paddle left and right with respect of the image dimensions and the dimensions of the window, so that the paddle does not go outside the window and moves smoothly on the screen.
private void loadImage() – again, this function loads the image from the resmyces folder (variable of type var is used because of casting issues)
resetState() – resets the x and y to their initial value in the Commons interface

class Board
It extends the JPanel class. The JPanel class is a container for the game (the dialog box that appears on the screen when the program is started).
public Board() - constructor built the same way and for the same reasons as in the previous two classes (see class Brick, class Ball, class Paddle or *)
private void initBoard() – adjusts the size of the window to the ones I want (WIDTH and HEIGHT from the Commons interface), adds the key input and adds the setFocusable() function part of the Component class. setFocusable(true) passes a true boolean value, because the function itself deals with the interaction inside the JPanel.
➔	with setFocusable(true) – I can actually move the paddle
➔	with setFocusable(false) – only the ball moves, but I cannot move the paddle

private void initGame() –
1.	creates/ initialize the objects, which will then appear in the window
2.	creates an array of Bricks, a ball and a paddle
3.	the two for loops inside each other define the number of bricks on each line and row – I will have 5 rows and 6 columns of bricks, hence 30
4.	a timer object is created which is responsible for the delay
paintComponent(Graphics g) – normally, this function is called “on-demand” and when it needs to be called, but here I have specifically overwritten it in order to specify the rendering and what happens when you lose. (Basically, what it does here is if the game is playing – display everything, else display “Game Over”)
drawObject(Graphics2D graphics) – is responsible for drawing the objects on the screen using the built-in Graphics2D class. The drawImage() function takes the image, the coordinates, the width and the height of the image and displays the image where it should be. Inside the for loop are drawn the bricks, since they are an array.
gameFinished() – displays a message “Game Over” in font Calibri, black text, size 20 and bolded
endgame() – stops the timer and changes the boolean value of the game variable to false and thus stopping the game.

collision() takes care that:
if the ball falls below the paddle, the game ends
1.	if you hit a brick the brick disappears
2.	when no bricks are left on the screen "CONGRATULATIONS! YOU WON!" is displayed
3.	the behavior of the ball when it hits the paddle (to bounce off)
4.	the behavior of the ball and the bricks when the ball hits a brick (to destroy it)

private class TAdapter extends KeyAdapter
Since from the KeyAdapter class I need only the left and right arrow keys, I override these methods.
private class Game implements ActionListener
I override the actionPerformed() function to call the doGame() function which “moves the ball and the paddle” and also calls the collision().

 class Game
Again, extends the JFrame and also contains the main method.
board() –
●	add(new Board()) creates the JFrame, by creating and adding a Board object
●	setTitle("Breakout Game") setting the title of the window, which appears on top
●	setDefaultCloseOperation(EXIT_ON_CLOSE) sets a closing operation – when I click the red X in the top right corner of the window the program ends and closes
●	setSize(Commons.WIDTH, Commons.HEIGHT) setting the size of the JFrame as the size of the board in order to center the JFrame with the next function
●	setLocationRelativeTo(null) – centers the frame
●	setResizable(false) – preventing the window from resizing by the user, as it will mess the alignment of the objects
●	pack() - it sizes the frame so that all its contents are slightly above their coordinates


              EventQueue.invokeLater(new Runnable() {
                                        	@Override
                                        	public void run() {
                                                       	var game = new Game();
                                                       	game.setVisible(true);
                                        	}
                          	});
                            
Since the complete Swing processing is done in the EDT thread, I have to block the GUI and I have to process my program (my calculations (since the movement and the positioning of the objects are basically calculations)) within a different thread, so the GUI stays responsive, and after that I need to update it. And invokeLater() does this for us as it creates an event (Runnable) at the end of Swings event list and is processed last, after all previous GUI events. 
