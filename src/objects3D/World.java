package objects3D;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;

public class World {
    Texture[] texturesWorld;
    public World(Texture[] texturesWorld) {
        this.texturesWorld = texturesWorld;
    }

    public void drawWorld() {
        // Draw the land of the world
        GL11.glPushMatrix();
        TexCube land = new TexCube();
        GL11.glTranslatef(600,200,0);
        GL11.glScalef(5000f, 8f, 5000f);
        Color.white.bind();
        texturesWorld[0].bind();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
        land.drawTexCube();
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glPopMatrix();

        // Draw the sky of the world
        GL11.glPushMatrix();
        InwardTexSphere sky = new InwardTexSphere();
        GL11.glTranslatef(600,200,0);
        GL11.glScalef(10000f, 10000f, 10000f);
        GL11.glRotatef(90.0f, 0.0f, 0.0f, 0.0f);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
        // Bind the texture to the surface
        Color.white.bind();
        texturesWorld[1].bind();
        glEnable(GL_TEXTURE_2D);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glClearColor(0.7f, 0.7f, 0.7f, 0.0f);
        sky.DrawTexSphere(0.5f, 12, 12, texturesWorld[1]);
        GL11.glPopMatrix();


        // Draw the project texture 2023
        GL11.glPushMatrix();
        TexCube texture2023 = new TexCube();
        GL11.glTranslatef(3000,1200,0);
        GL11.glScalef(8f, 1000f, 1000f);
        GL11.glRotatef(90.0f, 0.0f, 0.0f, 0.0f);
        Color.white.bind();
        texturesWorld[3].bind();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glTexParameteri( GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MAG_FILTER,  GL11.GL_NEAREST);
        texture2023.drawTexCube();
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glPopMatrix();
    }
}
