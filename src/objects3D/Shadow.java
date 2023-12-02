package objects3D;

import static org.lwjgl.opengl.GL11.*;
import GraphicsObjects.Point4f;
import GraphicsObjects.Utils;
import GraphicsObjects.Vector4f;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;


public class Shadow {

    // basic colours
    static float black[] = { 0.0f, 0.0f, 0.0f, 1.0f };


    public Shadow() {
    }

    // Implement using notes in Animation lecture
    public void drawShadow(float delta, boolean GoodAnimation) {
        // Increase the theta to speed up the frequency of the cyberman
        float theta = (float) (delta * 16 * Math.PI);
        float LimbRotation;
        if (GoodAnimation) {
            LimbRotation = (float) Math.cos(theta) * 45;
        } else {
            LimbRotation = 0;
        }

        Sphere sphere = new Sphere();
        Cylinder cylinder = new Cylinder();
        TexSphere texSphere = new TexSphere();

        glRotatef(90f, 1, 0, 0);
        glScalef(1f, 1f, 0.2f);
        glPushMatrix();

        {

            glColor3f(black[0], black[1], black[2]);
            glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
            glTranslatef(0.0f, 0.5f, 0.0f);
            glScalef(1.0f, 1.0f, 1.0f);
            sphere.drawSphere(0.5f, 32, 32);

//			glColor3f(yellow[0], yellow[1], yellow[2]);
//			glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(yellow));
//			glTranslatef(0.0f, 0.5f, 0.0f);
//			sphere.drawSphere(0.5f, 32, 32);

            // chest
            // Set the color to white to avoid unexpected surface effects
            glPushMatrix();
            {
                glTranslatef(0.0f, 0.9f, 0.0f);
                glScalef(1.0f, 1.0f, 1.0f);
                sphere.drawSphere(0.5f, 32, 32);


                // neck
                glPushMatrix();
                {
                    glTranslatef(0.0f, 0.0f, 0.0f);
                    glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
                    // glRotatef(45.0f,0.0f,1.0f,0.0f);
                    cylinder.drawCylinder(0.15f, 0.7f, 32);

                    // head
                    // Set the color to white to avoid unexpected surface effects
                    glPushMatrix();
                    {
                        glTranslatef(0.0f, 0.0f, 1.0f);
                        glScalef(1.0f, 1.0f, 1.0f);
                        sphere.drawSphere(0.5f, 32, 32);

                        //Draw the right side handle of the cyber helmet for the cyberman
                        glPushMatrix();
                        {
                            glTranslatef(0.26f, -0.1f, 0.05f);
                            glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
                            cylinder.drawCylinder(0.09f, 0.5f, 32);

                            glPushMatrix();
                            {
                                glTranslatef(-0.61f, 0.0f, 0.5f);
                                glRotatef(90.0f, 0.0f, 3.0f, 0.0f);
                                cylinder.drawCylinder(0.09f, 0.7f, 32);

                                glPushMatrix();
                                {
                                    glTranslatef(-0.08f, 0.0f, -0.01f);
                                    glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
                                    cylinder.drawCylinder(0.09f, 0.8f, 32);
                                }
                                glPopMatrix();
                            }
                            glPopMatrix();

                        }
                        glPopMatrix();


                        //Draw the left side handle of the cyber helmet for the cyberman
                        glPushMatrix();
                        {
                            glTranslatef(-0.7f, -0.1f, 0.05f);
                            glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
                            cylinder.drawCylinder(0.09f, 0.5f, 32);

                            glPushMatrix();
                            {
                                glTranslatef(-0.61f, 0.0f, -0.05f);
                                glRotatef(90.0f, 0.0f, 3.0f, 0.0f);
                                cylinder.drawCylinder(0.09f, 0.7f, 32);

                                glPushMatrix();
                                {
                                    glTranslatef(-0.71f, 0.0f, -0.01f);
                                    glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
                                    cylinder.drawCylinder(0.09f, 0.8f, 32);
                                }
                                glPopMatrix();
                            }
                            glPopMatrix();

                        }
                        glPopMatrix();



                        glPushMatrix();
                        {
                            glTranslatef(0.1f, 0.52f, 0.0f);


                            glPopMatrix();

                        }
                        glPopMatrix();

                    }
                    glPopMatrix();



                    // left shoulder
                    glPushMatrix();
                    {
                        glTranslatef(0.5f, 0.4f, 0.0f);
                        sphere.drawSphere(0.25f, 32, 32);

                        // left arm
                        glPushMatrix();
                        {
                            glTranslatef(0.0f, 0.0f, 0.0f);
                            glRotatef(90.0f, 1.0f, 0.0f, 0.0f);

                            glRotatef(-LimbRotation, 1.0f, 0.0f, 0.0f);
                            // glRotatef(27.5f,0.0f,1.0f,0.0f);
                            cylinder.drawCylinder(0.15f, 0.7f, 32);

                            // left elbow
                            glPushMatrix();
                            {
                                glTranslatef(0.0f, 0.0f, 0.85f);
                                sphere.drawSphere(0.2f, 32, 32);

                                // left forearm
                                glPushMatrix();
                                {
                                    glTranslatef(0.0f, 0.0f, 0.0f);
                                    glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                                    // glRotatef(90.0f,0.0f,1.0f,0.0f);
                                    cylinder.drawCylinder(0.1f, 0.7f, 32);

                                    // left hand
                                    glPushMatrix();
                                    {
                                        glTranslatef(0.0f, 0.0f, 0.75f);
                                        sphere.drawSphere(0.2f, 32, 32);

                                        glPushMatrix();
                                        {
                                            glTranslatef(0.0f, 0.0f, 0.0f);
                                            glRotatef(-20.0f, 1.0f, 0.0f, 0.0f);
                                            cylinder.drawCylinder(0.05f, 0.6f, 32);

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
                    // to chest

                    // right shoulder
                    glPushMatrix();
                    {
                        glTranslatef(-0.5f, 0.3f, 0.0f);
                        sphere.drawSphere(0.25f, 32, 32);

                        // right arm
                        glPushMatrix();
                        {
                            glTranslatef(0.0f, 0.0f, 0.0f);
                            glRotatef(90.0f, 1.0f, 0.0f, 0.0f);

                            glRotatef(LimbRotation, 1.0f, 0.0f, 0.0f);
//							glRotatef(27.5f,0.0f,1.0f,0.0f);
                            cylinder.drawCylinder(0.15f, 0.7f, 32);

                            // right elbow
                            glPushMatrix();
                            {
                                glTranslatef(0.0f, 0.0f, 0.85f);
                                sphere.drawSphere(0.2f, 32, 32);

                                // right forearm
                                glPushMatrix();
                                {
                                    glTranslatef(0.0f, 0.0f, 0.0f);
                                    glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                                    // glRotatef(90.0f,0.0f,1.0f,0.0f);
                                    cylinder.drawCylinder(0.1f, 0.7f, 32);

                                    // right hand
                                    glPushMatrix();
                                    {
                                        glTranslatef(0.0f, 0.0f, 0.75f);
                                        sphere.drawSphere(0.2f, 32, 32);

                                        glPushMatrix();
                                        {
                                            glTranslatef(0.0f, 0.0f, 0.0f);
                                            glRotatef(0.0f, 0.0f, 1.0f, 0.0f);
                                            cylinder.drawCylinder(0.05f, 0.6f, 32);

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

                // pelvis

                // left hip
                glPushMatrix();
                {
                    glTranslatef(-0.5f, -0.5f, 0.0f);

                    sphere.drawSphere(0.25f, 32, 32);

                    // left high leg
                    glPushMatrix();
                    {
                        glTranslatef(0.0f, 0.0f, 0.0f);
                        glRotatef(20.0f, 0.0f, 0.0f, 0.0f);

                        glRotatef((-LimbRotation / 2) + 70, 1.0f, 0.0f, 0.0f);
                        // glRotatef(90.0f,1.0f,0.0f,0.0f);
                        cylinder.drawCylinder(0.15f, 0.7f, 32);

                        // left knee
                        glPushMatrix();
                        {
                            glTranslatef(0.0f, 0.0f, 0.75f);
                            glRotatef(120.0f, 0.0f, 0.0f, 0.0f);
                            glRotatef((-LimbRotation / 2) - 150, 1.0f, 0.0f, 0.0f);

                            sphere.drawSphere(0.25f, 32, 32);

                            // left low leg
                            glPushMatrix();
                            {
                                glTranslatef(0.0f, 0.0f, 0.0f);
                                cylinder.drawCylinder(0.15f, 0.7f, 32);

                                // left foot
                                glPushMatrix();
                                {
                                    glTranslatef(0.0f, 0.0f, 0.75f);
                                    sphere.drawSphere(0.3f, 32, 32);

                                    // Draw the shoe of the human
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
                glPushMatrix();
                {
                    glTranslatef(0.5f, -0.5f, 0.0f);

                    sphere.drawSphere(0.25f, 32, 32);

                    // right high leg
                    glPushMatrix();
                    {
                        glTranslatef(0.0f, 0.0f, 0.0f);
                        glRotatef(20.0f, 0.0f, 0.0f, 0.0f);

                        glRotatef((LimbRotation / 2) + 70, 1.0f, 0.0f, 0.0f);
                        // glRotatef(90.0f,1.0f,0.0f,0.0f);
                        cylinder.drawCylinder(0.15f, 0.7f, 32);

                        // right knee
                        glPushMatrix();
                        {
                            glTranslatef(0.0f, 0.0f, 0.75f);
                            glRotatef(120.0f, 0.0f, 0.0f, 0.0f);
                            glRotatef((LimbRotation / 2) - 150, 1.0f, 0.0f, 0.0f);

                            sphere.drawSphere(0.25f, 32, 32);

                            // right low leg
                            glPushMatrix();
                            {
                                glTranslatef(0.0f, 0.0f, 0.0f);
                                // glRotatef(120.0f,1.0f,0.0f,0.0f);
                                // glRotatef(0.0f,0.0f,0.0f,0.0f);
                                cylinder.drawCylinder(0.15f, 0.7f, 32);

                                // left foot
                                glPushMatrix();
                                {
                                    glTranslatef(0.0f, 0.0f, 0.75f);
                                    sphere.drawSphere(0.3f, 32, 32);

                                    // Draw the shoe of the human
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

                //Draw the central emotional inhibitor of the cyberman
                glPushMatrix();
                {



                    glTranslatef(0.0f, 2.5f, 0.1f);
                    glScalef(1.0f, 1.0f, 1.0f);
                    sphere.drawSphere(0.2f, 32, 32);

                }
                glPopMatrix();

            }
            glPopMatrix();

        }

    }

}
