
public class testPercolation {
	public static void main(String[] args) {
		PercolationStats ps = new PercolationStats(10, 10);
		Percolation p = new Percolation(10);
		p.numberOfOpenSites();
		p.percolates();
		for (int row = 1; row <= 10; row++) {
			for (int col = 1; col <= 10; col++) {

				p.isFull(row, col);
				p.isOpen(row, col);
			}
		}
		p.open(7, 10);
		p.open(4, 5);
		p.numberOfOpenSites();
		p.numberOfOpenSites();
		p.open(1, 2);
		System.out.println("mean                    = " + ps.mean());
		System.out.println("stddev                  = " + ps.stddev());
		System.out.println("95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
	}
}
