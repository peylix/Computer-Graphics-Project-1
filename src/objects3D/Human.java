package objects3D;

import static org.lwjgl.opengl.GL11.*;

import GraphicsObjects.Utils;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;


public class Human {

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

	private boolean specialEffect = false;

	Texture[] texturesHuman;
	public Human(Texture[] texturesHuman) {
		this.texturesHuman = texturesHuman;
	}

	public void setSpecialEffect() {
		specialEffect = !specialEffect;
	}

	public void forceTurnOffSpecialEffect() {
		specialEffect = false;
	}

	// Implement using notes in Animation lecture
	public void drawHuman(float delta, boolean GoodAnimation) {
		float theta = (float) (delta * 9 * Math.PI);
		float LimbRotation;
		if (GoodAnimation) {
			LimbRotation = (float) Math.cos(theta) * 45;
		} else {
			LimbRotation = 0;
		}

		Sphere sphere = new Sphere();
		Cylinder cylinder = new Cylinder();
		TexSphere texSphere = new TexSphere();


		if (specialEffect) {
			glColor3f(magenta[0], magenta[1], magenta[2]);
			glTranslatef(0.0f, 0.5f, 0.0f);
			glScalef(0.1f, 0.1f, 0.1f);
			sphere.drawSphere(50.0f, 16, 16);

		}

		glPushMatrix();

		{
			// Set the color to white to avoid unexpected surface effects
			glColor3f(white[0], white[1], white[2]);
			glTranslatef(0.0f, 0.5f, 0.0f);
			glScalef(1.0f, 1.0f, 1.0f);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
			// Bind the texture to the surface
			Color.white.bind();
			texturesHuman[6].bind();
			glEnable(GL_TEXTURE_2D);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			glClearColor(0.7f, 0.7f, 0.7f, 0.0f);
			texSphere.DrawTexSphere(0.5f, 32, 32, texturesHuman[6]);

//			glColor3f(yellow[0], yellow[1], yellow[2]);
//			glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(yellow));
//			glTranslatef(0.0f, 0.5f, 0.0f);
//			sphere.drawSphere(0.5f, 32, 32);

			// chest
			// Set the color to white to avoid unexpected surface effects
			glColor3f(white[0], white[1], white[2]);
			glPushMatrix();
			{
				glTranslatef(0.0f, 0.9f, 0.0f);
				glScalef(1.0f, 1.0f, 1.0f);
				glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
				// Bind the texture to the surface
				Color.white.bind();
				texturesHuman[5].bind();
				glEnable(GL_TEXTURE_2D);
				glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
				glClearColor(0.7f, 0.7f, 0.7f, 0.0f);
				texSphere.DrawTexSphere(0.5f, 32, 32, texturesHuman[5]);


				// neck
				glColor3f(orange[0], orange[1], orange[2]);
				glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
				glPushMatrix();
				{
					glTranslatef(0.0f, 0.0f, 0.0f);
					glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
					// glRotatef(45.0f,0.0f,1.0f,0.0f);
					cylinder.drawCylinder(0.15f, 0.7f, 32);

					// head
					// Set the color to white to avoid unexpected surface effects
					glColor3f(white[0], white[1], white[2]);
					glPushMatrix();
					{
						glTranslatef(0.0f, 0.0f, 1.0f);
						glScalef(1.0f, 1.0f, 1.0f);
						glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
						// Bind the texture to the surface
						Color.white.bind();
						texturesHuman[4].bind();
						glEnable(GL_TEXTURE_2D);
						glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
						glClearColor(0.7f, 0.7f, 0.7f, 0.0f);
						texSphere.DrawTexSphere(0.5f, 32, 32, texturesHuman[4]);

						// Draw the nose of the human
						glColor3f(red[0], red[1], red[2]);
						glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
						glPushMatrix();
						{
							glTranslatef(0.1f, 0.52f, 0.0f);
							sphere.drawSphere(0.05f, 32, 32);

							// Draw the eye of the human
							glColor3f(black[0], black[1], black[2]);
							glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
							glPushMatrix();
							{
								glTranslatef(0.2f, -0.15f, 0.1f);

								sphere.drawSphere(0.1f, 32, 32);

								// Draw the another eye of the human
								glColor3f(black[0], black[1], black[2]);
								glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
								glPushMatrix();
								{
									glTranslatef(-0.4f, 0.1f, 0.0f);
									sphere.drawSphere(0.1f, 32, 32);


									glPopMatrix();
								}
								glPopMatrix();

							}
							glPopMatrix();

						}
						glPopMatrix();

					}
					glPopMatrix();


					// left shoulder
					glColor3f(blue[0], blue[1], blue[2]);
					glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
					glPushMatrix();
					{
						glTranslatef(0.5f, 0.4f, 0.0f);
						sphere.drawSphere(0.25f, 32, 32);

						// left arm
						glColor3f(orange[0], orange[1], orange[2]);
						glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
						glPushMatrix();
						{
							glTranslatef(0.0f, 0.0f, 0.0f);
							glRotatef(90.0f, 1.0f, 0.0f, 0.0f);

							glRotatef(-LimbRotation, 1.0f, 0.0f, 0.0f);
							// glRotatef(27.5f,0.0f,1.0f,0.0f);
							cylinder.drawCylinder(0.15f, 0.7f, 32);

							// left elbow
							glColor3f(blue[0], blue[1], blue[2]);
							glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
							glPushMatrix();
							{
								glTranslatef(0.0f, 0.0f, 0.85f);
								sphere.drawSphere(0.2f, 32, 32);

								// left forearm
								glColor3f(orange[0], orange[1], orange[2]);
								glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
								glPushMatrix();
								{
									glTranslatef(0.0f, 0.0f, 0.0f);
									glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
									// glRotatef(90.0f,0.0f,1.0f,0.0f);
									cylinder.drawCylinder(0.1f, 0.7f, 32);

									// left hand
									glColor3f(blue[0], blue[1], blue[2]);
									glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
									glPushMatrix();
									{
										glTranslatef(0.0f, 0.0f, 0.75f);
										sphere.drawSphere(0.2f, 32, 32);


									}
									glPopMatrix();
								}
								glPopMatrix();
							}
							glPopMatrix();
						}
						glPopMatrix();
					}
					glPopMatrix();
					// to chest

					// right shoulder
					glColor3f(blue[0], blue[1], blue[2]);
					glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
					glPushMatrix();
					{
						glTranslatef(-0.5f, 0.3f, 0.0f);
						sphere.drawSphere(0.25f, 32, 32);

						// right arm
						glColor3f(orange[0], orange[1], orange[2]);
						glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
						glPushMatrix();
						{
							glTranslatef(0.0f, 0.0f, 0.0f);
							glRotatef(90.0f, 1.0f, 0.0f, 0.0f);

							glRotatef(LimbRotation, 1.0f, 0.0f, 0.0f);
//							glRotatef(27.5f,0.0f,1.0f,0.0f);
							cylinder.drawCylinder(0.15f, 0.7f, 32);

							// right elbow
							glColor3f(blue[0], blue[1], blue[2]);
							glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
							glPushMatrix();
							{
								glTranslatef(0.0f, 0.0f, 0.85f);
								sphere.drawSphere(0.2f, 32, 32);

								// right forearm
								glColor3f(orange[0], orange[1], orange[2]);
								glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
								glPushMatrix();
								{
									glTranslatef(0.0f, 0.0f, 0.0f);
									glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
									// glRotatef(90.0f,0.0f,1.0f,0.0f);
									cylinder.drawCylinder(0.1f, 0.7f, 32);

									// right hand
									glColor3f(blue[0], blue[1], blue[2]);
									glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
									glPushMatrix();
									{
										glTranslatef(0.0f, 0.0f, 0.75f);
										sphere.drawSphere(0.2f, 32, 32);


									}
									glPopMatrix();
								}
								glPopMatrix();
							}
							glPopMatrix();
						}
						glPopMatrix();
					}
					glPopMatrix();
					// right arm

					// right elbow

					// right forearm
					// right hand

					// chest

				}
				glPopMatrix();

				// pelvis

				// left hip
				glColor3f(blue[0], blue[1], blue[2]);
				glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
				glPushMatrix();
				{
					glTranslatef(-0.5f, -0.5f, 0.0f);

					sphere.drawSphere(0.25f, 32, 32);

					// left high leg
					glColor3f(orange[0], orange[1], orange[2]);
					glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
					glPushMatrix();
					{
						glTranslatef(0.0f, 0.0f, 0.0f);
						glRotatef(20.0f, 0.0f, 0.0f, 0.0f);

						glRotatef((-LimbRotation / 2) + 70, 1.0f, 0.0f, 0.0f);
						// glRotatef(90.0f,1.0f,0.0f,0.0f);
						cylinder.drawCylinder(0.15f, 0.7f, 32);

						// left knee
						glColor3f(blue[0], blue[1], blue[2]);
						glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
						glPushMatrix();
						{
							glTranslatef(0.0f, 0.0f, 0.75f);
							glRotatef(120.0f, 0.0f, 0.0f, 0.0f);
							glRotatef((-LimbRotation / 2) - 150, 1.0f, 0.0f, 0.0f);

							sphere.drawSphere(0.25f, 32, 32);

							// left low leg
							glColor3f(orange[0], orange[1], orange[2]);
							glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
							glPushMatrix();
							{
								glTranslatef(0.0f, 0.0f, 0.0f);
//								glRotatef(50.0f,1.0f,0.0f,0.0f);
//								glRotatef(0.0f,0.0f,0.0f,0.0f);
								cylinder.drawCylinder(0.15f, 0.7f, 32);

								// left foot
								glColor3f(grey[0], grey[1], grey[2]);
								glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
								glPushMatrix();
								{
									glTranslatef(0.0f, 0.0f, 0.75f);
									sphere.drawSphere(0.3f, 32, 32);

									// Draw the shoe of the human
									glColor3f(grey[0], grey[1], grey[2]);
									glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
									glPushMatrix();
									{
										glTranslatef(0.0f, 0.0f, 0.0f);
										glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
										cylinder.drawCylinder(0.3f, 0.6f, 32);

									}
									glPopMatrix();

								}
								glPopMatrix();
							}
							glPopMatrix();
						}
						glPopMatrix();
					}
					glPopMatrix();
				}
				glPopMatrix();

				// pelvis

				// right hip
				glColor3f(blue[0], blue[1], blue[2]);
				glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
				glPushMatrix();
				{
					glTranslatef(0.5f, -0.5f, 0.0f);

					sphere.drawSphere(0.25f, 32, 32);

					// right high leg
					glColor3f(orange[0], orange[1], orange[2]);
					glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
					glPushMatrix();
					{
						glTranslatef(0.0f, 0.0f, 0.0f);
						glRotatef(20.0f, 0.0f, 0.0f, 0.0f);

						glRotatef((LimbRotation / 2) + 70, 1.0f, 0.0f, 0.0f);
						// glRotatef(90.0f,1.0f,0.0f,0.0f);
						cylinder.drawCylinder(0.15f, 0.7f, 32);

						// right knee
						glColor3f(blue[0], blue[1], blue[2]);
						glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
						glPushMatrix();
						{
							glTranslatef(0.0f, 0.0f, 0.75f);
							glRotatef(120.0f, 0.0f, 0.0f, 0.0f);
							glRotatef((LimbRotation / 2) - 150, 1.0f, 0.0f, 0.0f);

							sphere.drawSphere(0.25f, 32, 32);

							// right low leg
							glColor3f(orange[0], orange[1], orange[2]);
							glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
							glPushMatrix();
							{
								glTranslatef(0.0f, 0.0f, 0.0f);
								// glRotatef(120.0f,1.0f,0.0f,0.0f);
								// glRotatef(0.0f,0.0f,0.0f,0.0f);
								cylinder.drawCylinder(0.15f, 0.7f, 32);

								// left foot
								glColor3f(grey[0], grey[1], grey[2]);
								glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
								glPushMatrix();
								{
									glTranslatef(0.0f, 0.0f, 0.75f);
									sphere.drawSphere(0.3f, 32, 32);

									// Draw the shoe of the human
									glColor3f(grey[0], grey[1], grey[2]);
									glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
									glPushMatrix();
									{
										glTranslatef(0.0f, 0.0f, 0.0f);
										glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
										cylinder.drawCylinder(0.3f, 0.6f, 32);

									}
									glPopMatrix();

								}
								glPopMatrix();
							}
							glPopMatrix();
						}
						glPopMatrix();
					}
					glPopMatrix();
				}
				glPopMatrix();
			}
			glPopMatrix();

		}

	}

}