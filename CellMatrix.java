
public class CellMatrix {

	private Cell[][] cellMatrix;
	
	private int nrows, ncols, frameWidth, frameHeight;
	
	public CellMatrix(int _nrows, int _ncols, double p) {
		nrows = _nrows; ncols = _ncols;
		cellMatrix = new Cell[nrows][ncols];
		frameWidth = nrows * 3 - 2;
		frameHeight = ncols * 3 - 2;
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				if (Math.random() <= p)
					cellMatrix[i][j] = new Cell(i, j);
				else
					cellMatrix[i][j] = new Cell(i, j, false);
			}
		}
	}
	
	public CellMatrix(CellMatrix cells) {
		nrows = cells.getNRows(); ncols = cells.getNCols();
		cellMatrix = new Cell[nrows][ncols];
		frameWidth = nrows * 3 - 2;
		frameHeight = ncols * 3 - 2;
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				cellMatrix[i][j] = new Cell(i, j, cells.get(i, j).alive());
			}
		}
	}
			
	public Cell get(int i, int j) {
		return cellMatrix[i][j];
	}
	
	public int getNRows() {
		return nrows;
	}
	
	public int getNCols() {
		return ncols;
	}

	public int getWidth() {
		return frameWidth;
	}
	
	public int getHeight() {
		return frameHeight+22;
	}

	public int numberOfNeighbors(Cell[][] cellMatrix, int i, int j) {

		int counter = 0;
		Cell cell = cellMatrix[i][j];
		Cell[] cells = new Cell[8];
		
		if (cell.getI()-1 >= 0)
			cells[0] = get(cell.getI()-1, cell.getJ());
		else
			cells[0] = null;
		
		if (cell.getJ()+1 < ncols)
			cells[1] = get(cell.getI(), cell.getJ()+1);
		else
			cells[1] = null;
		
		if (cell.getI()-1 >= 0 && cell.getJ()+1 < ncols)
			cells[2] = get(cell.getI()-1, cell.getJ()+1);
		else
			cells[2] = null;
		
		if (cell.getI()+1 < nrows)
			cells[3] = get(cell.getI()+1, cell.getJ());
		else
			cells[3] = null;
		
		if (cell.getJ()-1 >= 0)
			cells[4] = get(cell.getI(), cell.getJ()-1);
		else
			cells[4] = null;
		
		if (cell.getI()+1 < nrows && cell.getJ()-1 >= 0)
			cells[5] = get(cell.getI()+1, cell.getJ());
		else
			cells[5] = null;
           
		if (cell.getI()-1 >= 0 && cell.getJ()-1 >= 0)
			cells[6] = get(cell.getI()-1, cell.getJ()-1);
		else
			cells[6] = null;
		
		if (cell.getI()+1 < nrows && cell.getJ()+1 < ncols)
			cells[7] = get(cell.getI()+1, cell.getJ()+1);
		else
			cells[7] = null;
		
		for (Cell c : cells) {
			if (c != null)
				if (c.alive())
					counter++;
		}

		return counter;
	}
	
	public void Update(int[] status) {
		Cell[][] mirror = new Cell[nrows][ncols];
		
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				mirror[i][j] = new Cell(i, j, cellMatrix[i][j].alive());
			}
		}
		
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				int phase = status[numberOfNeighbors(mirror, i, j)];
				if (phase == -1)
					cellMatrix[i][j].setAlive(false);
				else if (phase == 1)
					cellMatrix[i][j].setAlive(true);
			}
		}
	}

}