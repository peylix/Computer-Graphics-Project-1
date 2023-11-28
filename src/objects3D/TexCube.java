package objects3D;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;

public class TexCube {

	public TexCube() {

	}

	// Implement using notes and looking at TexSphere
	public void drawTexCube() {
		// Create the array of vertices of the cube
		Point4f[] vertices = {
				new Point4f(-1.0f, -1.0f, -1.0f, 0.0f),  // (-1, -1, -1), p0
				new Point4f(-1.0f, -1.0f, 1.0f, 0.0f), // (-1, -1, 1), p1
				new Point4f(-1.0f, 1.0f, -1.0f, 0.0f), // (-1, 1, -1), p2
				new Point4f(-1.0f, 1.0f, 1.0f, 0.0f), // (-1, 1, 1), p3
				new Point4f(1.0f, -1.0f, -1.0f, 0.0f), // (1, -1, -1), p4
				new Point4f(1.0f, -1.0f, 1.0f, 0.0f), // (1, -1, 1), p5
				new Point4f(1.0f, 1.0f, -1.0f, 0.0f), // (1, 1, -1), p6
				new Point4f(1.0f, 1.0f, 1.0f, 0.0f) // (1, 1, 1), p7
		};

		// Create the array containing the vertices of each face of the cube.
		// The vertices are stored in the form of their indices in the vertices array.
		int[][] faces = {
				{0, 4, 5, 1}, // face 0
				{2, 6, 4, 0}, // face 1
				{2, 0, 1, 3}, // face 2
				{4, 6, 7, 5}, // face 3
				{1, 5, 7, 3}, // face 4
				{6, 2, 3, 7}, // face 5
		};

		float s, t;


		GL11.glBegin(GL_QUADS);
		for (int face = 0; face < 6; face++) {
			// Get the vertices of the current face and use them to compute the vectors of the triangles in the face.

			Vector4f v = vertices[faces[face][1]].MinusPoint(vertices[faces[face][0]]);
			Vector4f w = vertices[faces[face][2]].MinusPoint(vertices[faces[face][0]]);
			Vector4f normal = v.cross(w).Normal();
			// Set the normal and vectors to draw the cube.
			// And set the corresponding surface parameters for each vertex.
			GL11.glNormal3f(normal.x, normal.y, normal.z);

			GL11.glTexCoord2f(0.0f, 0.0f);
			GL11.glVertex3f(vertices[faces[face][0]].x, vertices[faces[face][0]].y, vertices[faces[face][0]].z);
			GL11.glTexCoord2f(1.0f, 0.0f);
			GL11.glVertex3f(vertices[faces[face][1]].x, vertices[faces[face][1]].y, vertices[faces[face][1]].z);
			GL11.glTexCoord2f(1.0f, 1.0f);
			GL11.glVertex3f(vertices[faces[face][2]].x, vertices[faces[face][2]].y, vertices[faces[face][2]].z);
			GL11.glTexCoord2f(0.0f, 1.0f);
			GL11.glVertex3f(vertices[faces[face][3]].x, vertices[faces[face][3]].y, vertices[faces[face][3]].z);


		}
		GL11.glEnd();
	}

}
