package models;

/**
 * A class that represents a plateau at Mars. The Plateau is rectangular and is
 * represented by a coordinate system.
 * 
 * @author Robert Bagge
 * @date 2014-12-09
 */
public class Plateau {
	private static int minX = 0; // The X-axis starts at 0 as default. This can be
																// changed after needs.
	private static int minY = 0; // The X-axis starts at 0 as default. This can be
																// changed after needs.

	private int boundaryX; // How many squares the plateau spans on the X-axis
	private int boundaryY; // How many squares the plateau spans on the Y-axis

	/**
	 * Creates a plateau with boundaryX - maxX and boundaryY - maxY
	 * 
	 * @param maxX
	 * @param maxY
	 */
	public Plateau(int maxX, int maxY) {
		setBoundaryX(maxX);
		setBoundaryY(maxY);
	}

	/**
	 * 
	 * @return how many squares the plateau spans on the X-axis
	 */
	public int getBoundaryX() {
		return boundaryX;
	}

	/**
	 * Set how many squares the plateau on the X-axis to @param boundaryX
	 * 
	 * @param boundaryX
	 */
	public void setBoundaryX(int boundaryX) {
		this.boundaryX = boundaryX;
	}

	/**
	 * 
	 * @return how many squares the plateau spans on the Y-axis
	 */
	public int getBoundaryY() {
		return boundaryY;
	}

	/**
	 * Set how many squares the plateau on the Y-axis to @param boundaryY
	 * 
	 * @param boundaryY
	 */
	public void setBoundaryY(int boundaryY) {
		this.boundaryY = boundaryY;
	}

	/**
	 * Checks if a point is within the plateau or not
	 * 
	 * @param coordinateX
	 * @param coordinateY
	 * @return true if the point is within the plateau, false otherwise.
	 */
	public boolean pointWithinPlateau(int coordinateX, int coordinateY) {
		if (coordinateX >= minX && coordinateX <= getBoundaryX()
				&& coordinateY >= minY && coordinateY <= getBoundaryY()) {
			return true;
		} else {
			return false;
		}
	}
}
