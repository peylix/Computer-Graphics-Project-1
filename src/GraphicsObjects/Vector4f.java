package GraphicsObjects;

public class Vector4f {

	public float x = 0;
	public float y = 0;
	public float z = 0;
	public float a = 0;

	public Vector4f() {
		x = 0.0f;
		y = 0.0f;
		z = 0.0f;
		a = 0.0f;
	}

	public Vector4f(float x, float y, float z, float a) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.a = a;
	}

	// implement Vector plus a Vector
	public Vector4f PlusVector(Vector4f Additonal)
	{
		// Add the coordinates of vector 1 and the coordinates of vector 2
		float resultX = this.x + Additonal.x;
		float resultY = this.y + Additonal.y;
		float resultZ = this.z + Additonal.z;
		float resultA = this.a + Additonal.a;

		return new Vector4f(resultX, resultY, resultZ, resultA);
	}

	// implement Vector minus a Vector
	public Vector4f MinusVector(Vector4f Minus)
	{
		// Subtract the coordinates of vector 2 from the coordinates of vector 1
		float resultX = this.x - Minus.x;
		float resultY = this.y - Minus.y;
		float resultZ = this.z - Minus.z;
		float resultA = this.a - Minus.a;
		return new Vector4f(resultX, resultY, resultZ, resultA);
	}
	// implement Vector plus a Point
	public Point4f PlusPoint(Point4f Additonal)
	{
		// Add the coordinates of the vector to the coordinates of the point
		float resultX = this.x + Additonal.x;
		float resultY = this.y + Additonal.y;
		float resultZ = this.z + Additonal.z;
		float resultA = this.a + Additonal.a;
		return new Point4f(resultX, resultY, resultZ, resultA);
	} 	// Do not implement Vector minus a Point as it is undefined

	// Implement a Vector * Scalar
	public Vector4f byScalar(float scale )
	{
		// Multiply the coordinates of this vector by the scale
		float resultX = this.x * scale;
		float resultY = this.y * scale;
		float resultZ = this.z * scale;
		float resultA = this.a * scale;
		return new Vector4f(resultX, resultY, resultZ, resultA);
	}
	// implement returning the negative of a Vector
	public Vector4f NegateVector()
	{
		// Turn all the coordinates of this vector into its opposite
		float resultX = -this.x;
		float resultY = -this.y;
		float resultZ = -this.z;
		float resultA = -this.a;
		return new Vector4f(resultX, resultY, resultZ, resultA);
	}
	// implement getting the length of a Vector
	public float length()
	{
		// Get the projection of this vector in the x-y surface using the Pythagorean theorem.
		double pythagorasX = Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
		// Get the length of this vector using the Pythagorean theorem.
		// The equation is length = √(pythagorasX ^ 2 + this.z ^ 2)
        return (float) Math.sqrt(Math.pow(pythagorasX, 2) + Math.pow(this.z, 2));
	}
	// Just to avoid confusion here is getting the Normal of a Vector
	public Vector4f Normal() {
		float LengthOfTheVector = this.length();
		return this.byScalar(1.0f / LengthOfTheVector);
	}

	// implement getting the dot product of Vector.Vector

	public float dot(Vector4f v)
	{
		return ( this.x*v.x + this.y*v.y + this.z*v.z+ this.a*v.a);
	}
	// Implemented this for you to avoid confusion
	// as we will not normally be using 4 float vector
	public Vector4f cross(Vector4f v) {
		float u0 = (this.y * v.z - z * v.y);
		float u1 = (z * v.x - x * v.z);
		float u2 = (x * v.y - y * v.x);
		float u3 = 0; // ignoring this for now
		return new Vector4f(u0, u1, u2, u3);
	}

}
	 
	   

/*

										MMMM                                        
										MMMMMM                                      
 										MM MMMM                                    
 										MMI  MMMM                                  
 										MMM    MMMM                                
 										MMM      MMMM                              
  										MM        MMMMM                           
  										MMM         MMMMM                         
  										MMM           OMMMM                       
   										MM             .MMMM                     
MMMMMMMMMMMMMMM                        MMM              .MMMM                   
MM   IMMMMMMMMMMMMMMMMMMMMMMMM         MMM                 MMMM                 
MM                  ~MMMMMMMMMMMMMMMMMMMMM                   MMMM               
MM                                  OMMMMM                     MMMMM            
MM                                                               MMMMM          
MM                                                                 MMMMM        
MM                                                                   ~MMMM      
MM                                                                     =MMMM    
MM                                                                        MMMM  
MM                                                                       MMMMMM 
MM                                                                     MMMMMMMM 
MM                                                                  :MMMMMMMM   
MM                                                                MMMMMMMMM     
MM                                                              MMMMMMMMM       
MM                             ,MMMMMMMMMM                    MMMMMMMMM         
MM              IMMMMMMMMMMMMMMMMMMMMMMMMM                  MMMMMMMM            
MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM               ZMMMMMMMM              
MMMMMMMMMMMMMMMMMMMMMMMMMMMMM          MM$             MMMMMMMMM                
MMMMMMMMMMMMMM                       MMM            MMMMMMMMM                  
  									MMM          MMMMMMMM                     
  									MM~       IMMMMMMMM                       
  									MM      DMMMMMMMM                         
 								MMM    MMMMMMMMM                           
 								MMD  MMMMMMMM                              
								MMM MMMMMMMM                                
								MMMMMMMMMM                                  
								MMMMMMMM                                    
  								MMMM                                      
  								MM                                        
                             GlassGiant.com */