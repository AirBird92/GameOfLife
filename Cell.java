
public class Cell {

	private boolean alive;
	private int i, j;
	private int x, y;

	public Cell(int _i, int _j) {
		i = _i;
		j = _j;
		x = 3*i-2;
		y = 3*j-2;
		alive = true;
	}
	
	public Cell(int _i, int _j, boolean b) {
		i = _i;
		j = _j;
		x = 3*i-2;
		y = 3*j-2;
		alive = b;
	}
	
	public boolean alive() {
		return alive;
	}
	
	public void setAlive(boolean b) {
		alive = b;
	}
	
	public int getI() {
		return i;
	}

	public int getJ() {
		return j;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
