
import java.io.IOException;
import java.nio.FloatBuffer;

import objects3D.*;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.util.glu.GLU;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import GraphicsObjects.Arcball;
import GraphicsObjects.Utils;

//Main windows class controls and creates the 3D virtual world , please do not change this class but edit the other classes to complete the assignment. 
// Main window is built upon the standard Helloworld LWJGL class which I have heavily modified to use as your standard openGL environment. 
// 

// Do not touch this class, I will be making a version of it for your 3rd Assignment 
public class MainWindow {

	private boolean MouseOnepressed = true;
	private boolean dragMode = false;
	private boolean BadAnimation = false;
	private boolean Earth = false;
	/** position of pointer */
	float x = 400, y = 300;
	/** angle of rotation */
	float rotation = 0;
	/** time at last frame */
	long lastFrame;
	/** frames per second */
	int fps;
	/** last fps time */
	long lastFPS;

	long myDelta = 0; // to use for animation
	float Alpha = 0; // to use for animation
	long StartTime; // beginAnimiation

	Arcball MyArcball = new Arcball();

	boolean DRAWGRID = false;
	boolean waitForKeyrelease = true;
	/** Mouse movement */
	int LastMouseX = -1;
	int LastMouseY = -1;

	float pullX = 0.0f; // arc ball X cord.
	float pullY = 0.0f; // arc ball Y cord.

	int OrthoNumber = 1200; // using this for screen size, making a window of 1200 x 800 so aspect ratio 3:2
							// // do not change this for assignment 3 but you can change everything for your
							// project

	// basic colours
	static float black[] = { 0.0f, 0.0f, 0.0f, 1.0f };
	static float white[] = { 1.0f, 1.0f, 1.0f, 1.0f };

	static float grey[] = { 0.5f, 0.5f, 0.5f, 1.0f };
	static float spot[] = { 0.1f, 0.1f, 0.1f, 0.5f };

	// primary colours
	static float red[] = { 1.0f, 0.0f, 0.0f, 1.0f };
	static float green[] = { 0.0f, 1.0f, 0.0f, 1.0f };
	static float blue[] = { 0.0f, 0.0f, 1.0f, 1.0f };

	// secondary colours
	static float yellow[] = { 1.0f, 1.0f, 0.0f, 1.0f };
	static float magenta[] = { 1.0f, 0.0f, 1.0f, 1.0f };
	static float cyan[] = { 0.0f, 1.0f, 1.0f, 1.0f };

	// other colours
	static float orange[] = { 1.0f, 0.5f, 0.0f, 1.0f, 1.0f };
	static float brown[] = { 0.5f, 0.25f, 0.0f, 1.0f, 1.0f };
	static float dkgreen[] = { 0.0f, 0.5f, 0.0f, 1.0f, 1.0f };
	static float pink[] = { 1.0f, 0.6f, 0.6f, 1.0f, 1.0f };

	float paceX1 = 0.0f;
	float paceY1 = 0.0f;
	float paceZ1 = 0.0f;
	float paceX2 = 0.0f;
	float paceY2 = 0.0f;
	float paceZ2 = 0.0f;
	float currentAngle = 0.0f;
	float staticX1 = 285;
	float staticY1 = 380;
	float staticZ1 = 0;



	// static GLfloat light_position[] = {0.0, 100.0, 100.0, 0.0};

	// support method to aid in converting a java float array into a Floatbuffer
	// which is faster for the opengl layer to process

	public void start() {

		StartTime = getTime();
		try {
			Display.setDisplayMode(new DisplayMode(1200, 800));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		initGL(); // init OpenGL
		getDelta(); // call once before loop to initialise lastFrame
		lastFPS = getTime(); // call before loop to initialise fps timer

		while (!Display.isCloseRequested()) {
			int delta = getDelta();
			update(delta);
			renderGL();
			Display.update();
			Display.sync(120); // cap fps to 120fps
			long passed = getTime() - StartTime;
			System.out.println("Time passed: " + passed);
		}

		Display.destroy();
	}

	public void update(int delta) {
		// rotate quad
		// rotation += 0.01f * delta;

		int MouseX = Mouse.getX();
		int MouseY = Mouse.getY();
		int WheelPostion = Mouse.getDWheel();

		boolean MouseButonPressed = Mouse.isButtonDown(0);

		if (MouseButonPressed && !MouseOnepressed) {
			MouseOnepressed = true;
			// System.out.println("Mouse drag mode");
			MyArcball.startBall(MouseX, MouseY, 1200, 800);
			dragMode = true;

		} else if (!MouseButonPressed) {
			// System.out.println("Mouse drag mode end ");
			MouseOnepressed = false;
			dragMode = false;
		}

		if (dragMode) {
			MyArcball.updateBall(MouseX, MouseY, 1200, 800);
		}

		if (WheelPostion > 0) {
			OrthoNumber += 10;

		}

		if (WheelPostion < 0) {
			OrthoNumber -= 10;
			if (OrthoNumber < 610) {
				OrthoNumber = 610;
			}

			// System.out.println("Orth nubmer = " + OrthoNumber);

		}

		/** rest key is R */
		if (Keyboard.isKeyDown(Keyboard.KEY_R))
			MyArcball.reset();

		/* bad animation can be turn on or off using A key) */

		if (Keyboard.isKeyDown(Keyboard.KEY_A))
			BadAnimation = !BadAnimation;
		if (Keyboard.isKeyDown(Keyboard.KEY_D))
			x += 0.35f * delta;

		if (Keyboard.isKeyDown(Keyboard.KEY_W))
			y += 0.35f * delta;
		if (Keyboard.isKeyDown(Keyboard.KEY_S))
			y -= 0.35f * delta;

		if (Keyboard.isKeyDown(Keyboard.KEY_Q))
			rotation += 0.35f * delta;
		if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
			Earth = !Earth;
		}
		
		if (waitForKeyrelease) // check done to see if key is released
		{
			if (Keyboard.isKeyDown(Keyboard.KEY_G)) {

				DRAWGRID = !DRAWGRID;
				Keyboard.next();
				if (Keyboard.isKeyDown(Keyboard.KEY_G)) {
					waitForKeyrelease = true;
				} else {
					waitForKeyrelease = false;

				}
			}
		}

		/** to check if key is released */
		if (Keyboard.isKeyDown(Keyboard.KEY_G) == false) {
			waitForKeyrelease = true;
		} else {
			waitForKeyrelease = false;

		}

		// keep quad on the screen
		if (x < 0)
			x = 0;
		if (x > 1200)
			x = 1200;
		if (y < 0)
			y = 0;
		if (y > 800)
			y = 800;

		updateFPS(); // update FPS Counter

		LastMouseX = MouseX;
		LastMouseY = MouseY;
	}

	/**
	 * Calculate how many milliseconds have passed since last frame.
	 * 
	 * @return milliseconds passed since last frame
	 */
	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;

		return delta;
	}

	/**
	 * Get the accurate system time
	 * 
	 * @return The system time in milliseconds
	 */
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	/**
	 * Calculate the FPS and set it in the title bar
	 */
	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}

	public void initGL() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		changeOrth();
		MyArcball.startBall(0, 0, 1200, 800);
		glMatrixMode(GL_MODELVIEW);
		FloatBuffer lightPos = BufferUtils.createFloatBuffer(4);
		lightPos.put(10000f).put(1000f).put(1000).put(0).flip();

		FloatBuffer lightPos2 = BufferUtils.createFloatBuffer(4);
		lightPos2.put(0f).put(1000f).put(0).put(-1000f).flip();

		FloatBuffer lightPos3 = BufferUtils.createFloatBuffer(4);
		lightPos3.put(-10000f).put(1000f).put(1000).put(500).flip();

		FloatBuffer lightPos4 = BufferUtils.createFloatBuffer(4);
		lightPos4.put(-10000f).put(10000f).put(10000f).put(10000).flip();

		glLight(GL_LIGHT0, GL_POSITION, lightPos); // specify the
													// position
													// of the
													// light
		// glEnable(GL_LIGHT0); // switch light #0 on // I've setup specific materials
		// so in real light it will look abit strange

		glLight(GL_LIGHT1, GL_POSITION, lightPos); // specify the
													// position
													// of the
													// light
		glEnable(GL_LIGHT1); // switch light #0 on
		glLight(GL_LIGHT1, GL_DIFFUSE, Utils.ConvertForGL(spot));

		glLight(GL_LIGHT2, GL_POSITION, lightPos3); // specify
													// the
													// position
													// of the
													// light
		glEnable(GL_LIGHT2); // switch light #0 on
		glLight(GL_LIGHT2, GL_DIFFUSE, Utils.ConvertForGL(grey));

		glLight(GL_LIGHT3, GL_POSITION, lightPos4); // specify
													// the
													// position
													// of the
													// light
		glEnable(GL_LIGHT3); // switch light #0 on
		glLight(GL_LIGHT3, GL_DIFFUSE, Utils.ConvertForGL(grey));

		glEnable(GL_LIGHTING); // switch lighting on
		glEnable(GL_DEPTH_TEST); // make sure depth buffer is switched
									// on
		glEnable(GL_NORMALIZE); // normalize normal vectors for safety
		glEnable(GL_COLOR_MATERIAL);

		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		try {
			init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // load in texture

	}

	public void changeOrth() {

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(1200 - OrthoNumber, OrthoNumber, (800 - (OrthoNumber * 0.66f)), (OrthoNumber * 0.66f), 100000, -100000);
		glMatrixMode(GL_MODELVIEW);

		FloatBuffer CurrentMatrix = BufferUtils.createFloatBuffer(16);
		glGetFloat(GL_MODELVIEW_MATRIX, CurrentMatrix);

		// if(MouseOnepressed)
		// {

		MyArcball.getMatrix(CurrentMatrix);
		// }

		glLoadMatrix(CurrentMatrix);

	}

	/*
	 * You can edit this method to add in your own objects / remember to load in
	 * textures in the INIT method as they take time to load
	 * 
	 */
	public void renderGL() {
		changeOrth();

		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		glColor3f(0.5f, 0.5f, 1.0f);

		myDelta = getTime() - StartTime;
		float delta = ((float) myDelta) / 10000;

		// code to aid in animation
		float theta = (float) (delta * 2 * Math.PI);
		float thetaDeg = delta * 360;
		float posn_x = (float) Math.cos(theta); // same as your circle code in your notes
		float posn_y = (float) Math.sin(theta);



		if (myDelta <= 3000 && myDelta > 0) {
			// set the camera
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			GLU.gluPerspective(45f, 1.5f, 2.8f, 20000);
			glMatrixMode(GL_MODELVIEW);

			GLU.gluLookAt(1050, 1350, -1200, 1000, 800, -500, 0, 1, 0);

		} else if (myDelta <= 21000 && myDelta > 3000) {
			// set the camera
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			GLU.gluPerspective(45f, 1.5f, 2.8f, 20000);
			glMatrixMode(GL_MODELVIEW);
			GLU.gluLookAt(-950, 550, -2200, 1000, 800, -500, 0, 1, 0);
		} else if (myDelta <= 31000 && myDelta > 21000) {
			// set the camera
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			GLU.gluPerspective(45f, 1.5f, 2.8f, 20000);
			glMatrixMode(GL_MODELVIEW);
			GLU.gluLookAt(-950, 550, -2200,1000, 800, -500, 0, 1, 0);
		} else if (myDelta <= 46000 && myDelta > 31000) {
			// set the camera
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			GLU.gluPerspective(45f, 1.5f, 2.8f, 20000);
			glMatrixMode(GL_MODELVIEW);
			GLU.gluLookAt(850, 550, -3800,1000, 800, -500, 0, 1, 0);
		} else {
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			GLU.gluPerspective(45f, 1.5f, 2.8f, 20000);
			glMatrixMode(GL_MODELVIEW);
			GLU.gluLookAt(550, 450, 2200, 1000, 800, -500, 0, 1, 0);
		}


		// draw the sky and the land
		World world = new World(texturesWorld);
		world.drawWorld();


		/*
		 * This code draws a grid to help you view the human models movement You may
		 * change this code to move the grid around and change its starting angle as you
		 * please
		 */
//		if (DRAWGRID) {
//			glPushMatrix();
//			Grid MyGrid = new Grid();
//			glTranslatef(600, 400, 0);
//			glScalef(200f, 200f, 200f);
//			MyGrid.DrawGrid();
//			glPopMatrix();
//		}


		// Hibernation Chambers of the two cybermen in the screen
		glPushMatrix();
		TexCube hibernationChamber = new TexCube();
		glColor3f(white[0], white[1], white[2]);
//		glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(pink));
		glTranslatef(300, 300, 0);
		glScalef(100f, 400f, 100f);


		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

		// Bind the texture to the surface
		Color.white.bind();
		textureCube.bind();
		glEnable(GL_TEXTURE_2D);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glClearColor(0.8f, 0.8f, 0.8f, 0.0f);

		hibernationChamber.drawTexCube();
		glPopMatrix();

		// doorway to BJUT
		glPushMatrix();
		TexCube doorway = new TexCube();
		glColor3f(white[0], white[1], white[2]);
//		glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(pink));
		glTranslatef(800, 600, -4500);
		glScalef(500f, 500f, 8f);
		glRotatef(90, 0, 0, 0);


		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

		// Bind the texture to the surface
		Color.white.bind();
		textureCube2.bind();
		glEnable(GL_TEXTURE_2D);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glClearColor(0.8f, 0.8f, 0.8f, 0.0f);

		doorway.drawTexCube();
		glPopMatrix();

//		glPushMatrix();
//		TexCube hibernationChamber2 = new TexCube();
//		glColor3f(white[0], white[1], white[2]);
////		glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(pink));
//		glTranslatef(300, 300, -700);
//		glScalef(100f, 400f, 100f);
//
//
//		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
//
//		// Bind the texture to the surface
//		Color.white.bind();
//		textureCube.bind();
//		glEnable(GL_TEXTURE_2D);
//		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
//		glClearColor(0.8f, 0.8f, 0.8f, 0.0f);
//
//		hibernationChamber2.drawTexCube();
//		glPopMatrix();




//		glPushMatrix();
//		TexCube MyTexCube = new TexCube();
//		glColor3f(white[0], white[1], white[2]);


//		glPushMatrix();
//		Grid grid = new Grid();
//		glTranslatef(285, 210, 0);
//		glScalef(90f, 90f, 90f);
//		grid.DrawGrid();
//		glPopMatrix();


		// draw the shadow of the cyberman
		glPushMatrix();
		Shadow shadow = new Shadow();
		glTranslatef(105, 240, 0);
		glScalef(90f, 90f, 90f);
		glRotatef(-90.0f, 0, 1, 0);
		if (!BadAnimation) {
			glTranslatef(paceX1, paceY1, paceZ1);

		} else {

			// bad animation version
//			glTranslatef(posn_x * 3.0f, 0.0f, posn_y * 3.0f);
		}

		shadow.drawShadow(delta, !BadAnimation); // give a delta for the Human object ot be animated

		glPopMatrix();

		glPushMatrix();
		glTranslatef(285, 380, 0);
		glScalef(90f, 90f, 90f);
		glRotatef(-90.0f, 0, 1, 0);

		if (!BadAnimation) {

//			if (myDelta > 5000 && myDelta < 100000){
//				glTranslatef(paceX1, 0.0f, -paceZ1);
//			} else {
				// insert your animation code to correct the postion for the human rotating
//				glTranslatef(paceX1, 0.0f, posn_y * 3.0f);
//				// Rotate the Human object using the thetaDeg variable
//				glRotatef(-thetaDeg + 180, 0, 1, 0);
//			glTranslatef(posn_x * 3.0f, 0.0f, posn_y * 3.0f);
//			}

			if (myDelta <= 3500 && myDelta > 0) {
				paceZ1 -= 0.2f;

				glTranslatef(paceX1, paceY1, paceZ1); // Move forward. Leave the hibernation chamber.
				glRotatef(currentAngle, 0, 1, 0);

			} else if (myDelta <= 7000 && myDelta > 3500) {
				glTranslatef(paceX1, paceY1, paceZ1); // Stop for a while.
				glRotatef(currentAngle, 0, 1, 0);
			} else if (myDelta <= 12000 && myDelta > 7000) {
				currentAngle = thetaDeg + 45;
				glTranslatef(paceX1, paceY1, paceZ1); // Rotate to the left.
				glRotatef(currentAngle, 0, 1, 0);

			}
			else if (myDelta <= 13000 && myDelta > 12000) {

				paceX1 -= 0.2f;
				glTranslatef(paceX1, paceY1, paceZ1); // Move for a distance.
				glRotatef(currentAngle, 0, 1, 0);
			} else if (myDelta <= 17500 && myDelta > 13000) {
				currentAngle -= 1.5f;
				glTranslatef(paceX1, paceY1, paceZ1); // Rotate to the right.
				glRotatef(currentAngle, 0, 1, 0);
			} else if (myDelta <= 21000 && myDelta > 17500) {
				glTranslatef(paceX1, paceY1, paceZ1); // Stop for a while.
				glRotatef(currentAngle, 0, 1, 0);
			} else if (myDelta <= 25500 && myDelta > 21000){
				currentAngle += 1.3f;
				glTranslatef(paceX1, paceY1, paceZ1); // Rotate to the left.
				glRotatef(currentAngle, 0, 1, 0);
			} else {
				paceX1 -= 0.1f;
				glTranslatef(paceX1, paceY1, paceZ1); // Leave the scene.
				glRotatef(currentAngle, 0, 1, 0);
			}
//			else {
//				glPopMatrix();
//				glPushMatrix();
//				paceX1 = 3000;
//				paceZ1 = 5000;
//				glTranslatef(285, 380, 200);
//				glRotatef(currentAngle, 0, 1, 0);
//			}
//			glRotatef(-thetaDeg + 180, 0, 1, 0);

		} else {

			// bad animation version
//			glTranslatef(posn_x * 3.0f, 0.0f, posn_y * 3.0f);
		}

		cyberman.drawCyberman(delta, !BadAnimation); // give a delta for the Human object ot be animated

		glPopMatrix();




		glPushMatrix();
		glTranslatef(3385, 380, 200);
		glScalef(90f, 90f, 90f);
		glRotatef(90.0f, 0, 1, 0);

		if (!BadAnimation) {
			if (myDelta <= 2900 && myDelta > 0) {

			} else if (myDelta <= 7000 && myDelta > 2900) {
				paceZ2 -= 0.2f;
				glTranslatef(paceX2, paceY2, paceZ2);
			} else if (myDelta <= 8000 && myDelta > 7000) {
				human.setSpecialEffect();
				glTranslatef(paceX2, paceY2, paceZ2);
			} else if (myDelta <= 12000 && myDelta > 8000) {
				human.forceTurnOffSpecialEffect();
				paceZ2 = 0.0f;
				glTranslatef(paceX2, paceY2, paceZ2);
			} else if (myDelta <= 13000 && myDelta > 12000) {
				paceX2 += 0.2f;
				glTranslatef(paceX2, paceY2, paceZ2);
			} else if (myDelta <= 15000 && myDelta > 13000) {

			} else if (myDelta <= 20500 && myDelta > 15000) {
				paceZ2 -= 0.2f;
				glTranslatef(paceX2, paceY2, paceZ2);
			} else if (myDelta <= 22000 && myDelta > 20500) {
				human.setSpecialEffect();
				glTranslatef(paceX2, paceY2, paceZ2);
			} else {
				human.forceTurnOffSpecialEffect();
				paceZ2 = 0.0f;
				glTranslatef(paceX2, paceY2, paceZ2);
			}
		} else {

			// bad animation version
//			glTranslatef(posn_x * 3.0f, 0.0f, posn_y * 3.0f);
		}

		human.drawHuman(delta, !BadAnimation); // give a delta for the Human object ot be animated

		glPopMatrix();

		/*
		 * This code puts the earth code in which is larger than the human so it appears
		 * to change the scene
		 */
		if (Earth) {
			// Globe in the centre of the scene
			glPushMatrix();
			TexSphere MyGlobe = new TexSphere();
			// TexCube MyGlobe = new TexCube();
			glTranslatef(500, 500, 500);
			glScalef(140f, 140f, 140f);

			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

			Color.white.bind();
			texture.bind();
			glEnable(GL_TEXTURE_2D);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

			MyGlobe.DrawTexSphere(8f, 100, 100, texture);
			// MyGlobe.DrawTexCube();
			glPopMatrix();
		}

	}

	public static void main(String[] argv) {
		MainWindow hello = new MainWindow();
		hello.start();
	}

	Texture texture;
	Texture textureCube;
	Texture textureCube2;

	// An array for storing textures to make it easier to pass it to a Human object.
	Texture[] texturesHumanoid = new Texture[20];
	Texture[] texturesWorld = new Texture[5];

	Cyberman cyberman;
	Human human;

//	Cyberman cyberman2;

	/*
	 * Any additional textures for your assignment should be written in here. Make a
	 * new texture variable for each one so they can be loaded in at the beginning
	 */
	public void init() throws IOException {

		texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/earthspace.png"));
		System.out.println("Texture loaded okay ");

		textureCube = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/cabinShell.png"));
		System.out.println("TextureCube loaded okay ");
		textureCube2 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/bjut_picture.png"));
		System.out.println("TextureCube2 loaded okay ");

		texturesHumanoid[0] = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/texture2.png"));
		System.out.println("Texture0 for the Cyberman loaded okay ");

		texturesHumanoid[1] = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/texture5.png"));
		System.out.println("Texture1 for the Cyberman loaded okay ");

		texturesHumanoid[2] = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/texture6.png"));
		System.out.println("Texture2 for the Cyberman loaded okay ");

		texturesHumanoid[3] = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/texture4.png"));
		System.out.println("Texture3 for the Cyberman loaded okay ");
		texturesHumanoid[4] = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/textureSkin.png"));
		System.out.println("Texture0 for the Human loaded okay ");
		texturesHumanoid[5] = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/textureCloth1.png"));
		System.out.println("Texture1 for the Human loaded okay ");
		texturesHumanoid[6] = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/textureCloth2.png"));
		System.out.println("Texture2 for the Human loaded okay ");


		texturesWorld[0] = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/cityLand.png"));
		System.out.println("textureWorld0 loaded okay ");
		texturesWorld[1] = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/burningSky2.png"));
		System.out.println("textureWorld1 loaded okay ");
		texturesWorld[2] = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/burningSky2.png"));
		System.out.println("textureWorld2 loaded okay ");
		texturesWorld[3] = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/Project_Texture_2023.png"));
		System.out.println("textureWorld3 loaded okay ");

		cyberman = new Cyberman(texturesHumanoid);
//		cyberman2 = new Cyberman(texturesHumanoid);

		human = new Human(texturesHumanoid);



	}
}
