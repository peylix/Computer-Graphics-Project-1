package objects3D;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL11;
import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;
import java.math.*;

public class Cylinder {

	
	public Cylinder() { 
	}
	
	// remember to use Math.PI isntead PI 
	// Implement using notes and examine Tetrahedron to aid in the coding  look at lecture  7 , 7b and 8 
	public void drawCylinder(float radius, float height, int nSegments ) {
		GL11.glBegin(GL11.GL_TRIANGLES);
		for (float i = 0.0f; i < nSegments; i += 1.0f) {
			// Calculate angles for the current and next points
			double angle = Math.PI * i * 2.0f / nSegments;
			double nextAngle = (float) Math.PI * (i + 1.0f) * 2.0f / nSegments;

			// Calculate x and y coordinates for the current and next points
			float x1 = (float) Math.sin(angle) * radius;
			float x2 = (float) Math.sin(nextAngle) * radius;
			float y1 = (float) Math.cos(angle) * radius;
			float y2 = (float) Math.cos(nextAngle) * radius;

			// Draw the triangles for the sides
			GL11.glNormal3f(x1, y1, 0.0f);
			GL11.glVertex3f(x1, y1, 0.0f);
			GL11.glNormal3f(x2, y2, 0.0f);
			GL11.glVertex3f(x2, y2, height);
			GL11.glNormal3f(x1, y1, 0.0f);
			GL11.glVertex3f(x1, y1, height);

			GL11.glNormal3f(x1, y1, 0.0f);
			GL11.glVertex3f(x1, y1, 0.0f);
			GL11.glNormal3f(x2, y2, 0.0f);
			GL11.glVertex3f(x2, y2, 0.0f);
			GL11.glNormal3f(x2, y2, 0.0f);
			GL11.glVertex3f(x2, y2, height);


		}

		GL11.glEnd();

		// Draw the top of the cylinder
		GL11.glBegin(GL11.GL_TRIANGLE_FAN);
		for (float vertex = 0.0f; vertex < nSegments; vertex++) {
			// Split the "circle" into small triangle fans to make it easier to draw.
			double theta1 = vertex * 2 * Math.PI / nSegments;
			double theta2 = (vertex + 1) * 2 * Math.PI / nSegments;
			// Obtain the vertices of a small triangle in the "circle"
			GL11.glVertex3f((float) Math.sin(theta1) * radius, (float) Math.cos(theta1) * radius, 0.0f);
			GL11.glVertex3f((float) Math.sin(theta2) * radius, (float) Math.cos(theta2) * radius, 0.0f);

		}
		GL11.glEnd();

		// Draw the bottom of the cylinder
		GL11.glBegin(GL11.GL_TRIANGLE_FAN);
		for (float vertex = 0.0f; vertex < nSegments; vertex++) {
			double theta1 = vertex * 2 * Math.PI / nSegments;
			double theta2 = (vertex + 1) * 2 * Math.PI / nSegments;
			// Obtain the vertices of a small triangle in the "circle"
			GL11.glVertex3f((float) Math.sin(theta1) * radius, (float) Math.cos(theta1) * radius, height);
			GL11.glVertex3f((float) Math.sin(theta2) * radius, (float) Math.cos(theta2) * radius, height);

		}
		GL11.glEnd();

	}
}
