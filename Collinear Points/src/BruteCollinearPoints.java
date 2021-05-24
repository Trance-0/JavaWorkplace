public class BruteCollinearPoints {
	private LineSegment[] lines;
	private int threshold = 4;
	private int index = 0;

	// finds all line segments containing 4 or more points
	public BruteCollinearPoints(Point[] points) {
		if (points == null) {
			throw new IllegalArgumentException();
		} else if (points.length < threshold) {
			lines = new LineSegment[0];
		} else {
			LineSegment[] Prefourpoints = new LineSegment[points.length];
			for (int i = 0; i < points.length - 1; i++) {
				Point Ori = points[i];
				points = sortPointsBySlope(points, Ori);
				int numOfSameElement = 1;
				double previousSlope = Ori.slopeTo(points[i + 1]);
				for (int j = i + 2; j < points.length; j++) {
					double currentSlope = Ori.slopeTo(points[j]);
					if (previousSlope == currentSlope) {
						numOfSameElement++;
					}
					if (previousSlope != currentSlope || j == points.length - 1) {
						if (numOfSameElement >= threshold - 1) {
							Point[] temp = new Point[numOfSameElement + 1];
							temp[0] = Ori;
							for (int k = 0; k < numOfSameElement; k++) {
								temp[k + 1] = points[j - k - 1];
							}
							Prefourpoints = processPoints(temp, Prefourpoints);
							i += numOfSameElement;
						}
						numOfSameElement = 0;
					}
				}
			}
			lines = cleanNull(Prefourpoints);
		}
	}

	private LineSegment[] cleanNull(LineSegment[] prefourpoints) {
		LineSegment[] result = new LineSegment[countElement(prefourpoints)];
		int index = 0;
		for (LineSegment i : prefourpoints) {
			if (i != null) {
				result[index] = i;
				index++;
			}
		}
		return result;
	}

	private int countElement(LineSegment[] prefourpoints) {
		int count = 0;
		for (LineSegment i : prefourpoints) {
			if (i != null) {
				count++;
			}
		}
		return count;
	}

	private LineSegment[] processPoints(Point[] points, LineSegment[] container) {
		points = sortPointsByPos(points);
		Point pre = null;
		for (Point i : points) {
			if (pre == null) {
				pre = i;
				continue;
			}
			container[index] = new LineSegment(pre, i);
			index++;
			pre = i;
		}
		return container;
	}

	private Point[] sortPointsByPos(Point[] points) {
		// initialize the result
		Point[] result = new Point[points.length];
		// set up two sub array and start
		Point[] a = subArray(points, 0, points.length / 2);
		Point[] b = subArray(points, points.length / 2, points.length);
		// set up two pointer
		int i = 0;
		int j = 0;
		// if the two array is sorted
		if (!isSortedPos(a)) {
			a = sortPointsByPos(a);
		}
		if (!isSortedPos(b)) {
			b = sortPointsByPos(b);
		}
		// sort the array
		for (int k = 0; k < points.length; k++) {
			// if array a is empty
			if (i == a.length) {
				result[k] = b[j];
				j++;
			}
			// if array b is empty
			else if (j == b.length) {
				result[k] = a[i];
				i++;
			}
			// if both arrays are not empty
			else {
				// if element in a is greater than b
				if (a[i].compareTo(b[j]) == -1) {
					result[k] = b[j];
					j++;
				} else {
					result[k] = a[i];
					i++;
				}
			}
		}
		return result;
	}

	private Point[] sortPointsBySlope(Point[] points, Point Ori) {
		// initialize the result
		Point[] result = new Point[points.length];
		// set up two sub array and start
		Point[] a = subArray(points, 0, points.length / 2);
		Point[] b = subArray(points, points.length / 2, points.length);
		// set up two pointer
		int i = 0;
		int j = 0;
		// if the two array is sorted
		if (!isSortedSlope(a, Ori)) {
			a = sortPointsBySlope(a, Ori);
		}
		if (!isSortedSlope(b, Ori)) {
			b = sortPointsBySlope(b, Ori);
		}
		// sort the array
		for (int k = 0; k < points.length; k++) {
			// if array a is empty
			if (i == a.length) {
				result[k] = b[j];
				j++;
			}
			// if array b is empty
			else if (j == b.length) {
				result[k] = a[i];
				i++;
			}
			// if both arrays are not empty
			else {
				// if element in a is greater than b
				if (Ori.slopeOrder().compare(a[i], b[j]) == 1) {
					result[k] = b[j];
					j++;
				} else {
					result[k] = a[i];
					i++;
				}
			}
		}
		return result;
	}

	private boolean isSortedSlope(Point[] points, Point Ori) {
		Point pre = points[0];
		for (Point i : points) {
			if (Ori.slopeOrder().compare(pre, i) > 0) {
				return false;
			}
			pre = i;
		}
		return true;
	}

	private boolean isSortedPos(Point[] points) {
		Point pre = points[0];
		for (Point i : points) {
			if (pre.compareTo(i) < 0) {
				return false;
			}
			pre = i;
		}
		return true;
	}

	private Point[] subArray(Point[] points, int sta, int ed) {
		Point[] result = new Point[ed - sta];
		for (int i = sta; i < ed; i++) {
			result[i - sta] = points[i];
		}
		return result;
	}

	// the number of line segments
	public int numberOfSegments() {
		return lines.length;

	}

	// the line segments
	public LineSegment[] segments() {
		return lines;

	}

	public static void main(String[] args) {

	}
}