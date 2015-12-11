import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Panel;

public class GameOfLife {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		int width = 600;
		int height = 375;
		double percentage = 0.01;
		final CellMatrix cells = new CellMatrix(width, height, percentage);
		
		int[] status = {-1, -1, 0, 1, -1, -1, -1, -1, -1};
		
		final Frame frame = new Frame("Game of Life");
		final Panel panel = new Panel() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;
			public void paint(Graphics g) {
				for (int i = 0; i < cells.getNRows(); i++) {
					for (int j = 0; j < cells.getNCols(); j++) {
						if (cells.get(i, j).alive())
							g.drawRect(cells.get(i,j).getX(), cells.get(i,j).getY(), 1, 1);
					}
				}
			}
		};
		frame.add(panel);
		frame.setSize(cells.getWidth(), cells.getHeight());
		frame.setVisible(true);
		
		while (true) {
			panel.repaint();
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				
			}
			cells.Update(status);
		}
	}

}