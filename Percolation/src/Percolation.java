import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int numberOfOpenSites;
	private final int[][] detectArea = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };
	private final int virtualTop;
	private final int virtualDown;
	private WeightedQuickUnionUF grid;
	private boolean[][] gridstats;
	private final int side;

	// creates n-by-n grid, with all sites initially blocked
	public Percolation(int n) {
		if (n > 0) {
			numberOfOpenSites = 0;
			side = n;
			grid = new WeightedQuickUnionUF(n * n + 2);
			gridstats = new boolean[n][n];
			virtualTop = n * n;
			virtualDown = n * n + 1;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					gridstats[i][j] = false;
				}
			}
		} else {
			throw new IllegalArgumentException();
		}
	}

	private int findNumIngrid(int row, int col) {
		if (row > 0 && row <= side && col > 0 && col <= side) {
			return side * (row - 1) + (col - 1);
		}
		throw new IllegalArgumentException("with col= " + col + " row= " + row);
	}

	private void refreshgrid(int a, int b) {
		for (int[] i : detectArea) {
			int temprow = a + i[0];
			int tempcol = b + i[1];
			if (temprow > 0 && temprow <= side && tempcol > 0 && tempcol <= side) {
				if (gridstats[temprow - 1][tempcol - 1]) {
					grid.union(findNumIngrid(a, b), findNumIngrid(temprow, tempcol));
				}
			}
		}
	}

	// opens the site (row, col) if it is not open already
	public void open(int row, int col) {
		if (row > 0 && row <= side && col > 0 && col <= side) {
			if (gridstats[row - 1][col - 1] == false) {
				if (row == 1) {
					grid.union(virtualTop, findNumIngrid(row, col));
				}
				if (row == side) {
					grid.union(virtualDown, findNumIngrid(row, col));
				}
				gridstats[row - 1][col - 1] = true;
				numberOfOpenSites++;
				refreshgrid(row, col);
			}
		} else {
			throw new IllegalArgumentException();
		}
	}

	// is the site (row, col) open?
	public boolean isOpen(int row, int col) {
		if (row > 0 && row <= side && col > 0 && col <= side) {
			return gridstats[row - 1][col - 1];
		} else {
			throw new IllegalArgumentException();
		}
	}

	// is the site (row, col) full?
	public boolean isFull(int row, int col) {
		if (row > 0 && row <= side && col > 0 && col <= side) {
			return grid.find(findNumIngrid(row, col)) == grid.find(virtualTop);
		}
		throw new IllegalArgumentException();
	}

	// returns the number of open sites
	public int numberOfOpenSites() {
		return numberOfOpenSites;
	}

	// does the system percolate?
	public boolean percolates() {
		if (numberOfOpenSites > 0) {
			return grid.find(virtualTop) == grid.find(virtualDown);
		}
		return false;
	}

}