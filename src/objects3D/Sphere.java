package objects3D;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Point;

import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;

public class Sphere {

	public Sphere() {

	}

	// Implement using notes and examine Tetrahedron to aid in the coding look at
	// lecture 7 , 7b and 8
	// 7b should be your primary source, we will cover more about circles in later
	// lectures to understand why the code works
	public void drawSphere(float radius, float nSlices, float nSegments) {
		// Store the value of float PI for further convenience.
		final float PI_FLOAT = (float) Math.PI;
		// Compute the increment of theta and phi
		float inctheta = (2.0f * PI_FLOAT) / nSlices;
		float incphi = PI_FLOAT / nSegments;

		GL11.glBegin(GL11.GL_TRIANGLE_STRIP);
		for (float theta = -PI_FLOAT; theta < PI_FLOAT; theta += inctheta) {

			for (float phi = -(PI_FLOAT / 2.0f); phi < (PI_FLOAT / 2.0f); phi += incphi) {
				// Find four points on the sphere and draw a small quadrilateral with them.
				float x1 = radius * (float) Math.cos(phi) * (float) Math.cos(theta);
				float y1 = radius * (float) Math.cos(phi) * (float) Math.sin(theta);
				float z1 = radius * (float) Math.sin(phi);

				float x2 = radius * (float) Math.cos(phi) * (float) Math.cos(theta + inctheta);
				float y2 = radius * (float) Math.cos(phi) * (float) Math.sin(theta + inctheta);
				float z2 = radius * (float) Math.sin(phi);

				float x3 = radius * (float) Math.cos(phi + incphi) * (float) Math.cos(theta);
				float y3 = radius * (float) Math.cos(phi + incphi) * (float) Math.sin(theta);
				float z3 = radius * (float) Math.sin(phi + incphi);

				float x4 = radius * (float) Math.cos(phi + incphi) * (float) Math.cos(theta + inctheta);
				float y4 = radius * (float) Math.cos(phi + incphi) * (float) Math.sin(theta + inctheta);
				float z4 = radius * (float) Math.sin(phi + incphi);



				// Draw the small quadrilaterals
				GL11.glNormal3f(x1, y1, z1);
				GL11.glVertex3f(x1, y1, z1);

				GL11.glNormal3f(x2, y2, z2);
				GL11.glVertex3f(x2, y2, z2);

				GL11.glNormal3f(x3, y3, z3);
				GL11.glVertex3f(x3, y3, z3);

				GL11.glNormal3f(x4, y4, z4);
				GL11.glVertex3f(x4, y4, z4);

			}
		}
		GL11.glEnd();

	}
}
