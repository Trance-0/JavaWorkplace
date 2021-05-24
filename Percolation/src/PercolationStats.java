import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	private final double[] thresholds;

	// perform independent trials on an n-by-n grid
	public PercolationStats(int n, int trials) {
		if (n > 0 && trials > 0) {
			thresholds = new double[trials];
			for (int i = 0; i < trials; i++) {
				Percolation temp = new Percolation(n);
				while (!temp.percolates()) {
					int r = StdRandom.uniform(n);
					int c = StdRandom.uniform(n);
					temp.open(r + 1, c + 1);
//                System.out.println(temp);
				}
				thresholds[i] = (double) temp.numberOfOpenSites() / (n * n);
				temp = null;
			}
		} else {
			throw new IllegalArgumentException();
		}
	}

	// sample mean of percolation threshold
	public double mean() {
		return StdStats.mean(thresholds);
	}

	// sample standard deviation of percolation threshold
	public double stddev() {
		return StdStats.stddev(thresholds);
	}

	// low endpoint of 95% confidence interval
	public double confidenceLo() {
		return mean() - 1.96 * stddev() / Math.sqrt(thresholds.length);
	}

	// high endpoint of 95% confidence interval
	public double confidenceHi() {
		return mean() + 1.96 * stddev() / Math.sqrt(thresholds.length);
	}

	public static void main(String[] args) {
		PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		System.out.println("mean                    = " + ps.mean());
		System.out.println("stddev                  = " + ps.stddev());
		System.out.println("95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
	}
}
