/*Name:Xiaoyu Zheng
 * Email:xzheng10@u.rochester.edu
 * Project number:3
 * Lab Section: Tue 2:00 pm and Thu 2:00 pm
 */

public class MyTreeNode {
	public Line data;
	public MyTreeNode leftChild;
	public MyTreeNode rightChild;
	public MyTreeNode parent;
	public int number;
	private static final int COUNTERCLOCKWISE = -1;
	private static final int CLOCKWISE = 1;
	private static final int COLINEAR = 0;
	private static final int INTERSECTION = 2;

	public void insert(Line x, int num) {

		Point a = data.p1;
		Point b = data.p2;

		int ccws = ccw(a, b, x.p1);
		int ccwe = ccw(a, b, x.p2);

		if (ccws == 0 || ccwe == 0) {
			if (ccws == 0) {
				if (ccwe == COUNTERCLOCKWISE || ccwe == 0) {

					if (leftChild == null) {
						dire(COUNTERCLOCKWISE, x,num);
					} else {
						leftChild.insert(x,num);
					}
				}

				if (ccwe == CLOCKWISE) {

					if (rightChild == null) {
						dire(CLOCKWISE, x,num);
					} else {
						rightChild.insert(x,num);
					}

				}

			} else {

				if (ccws == COUNTERCLOCKWISE || ccws == 0) {

					if (leftChild == null) {
						dire(COUNTERCLOCKWISE, x,num);
					} else {
						leftChild.insert(x,num);
					}
				}

				if (ccws == CLOCKWISE) {

					if (rightChild == null) {
						dire(CLOCKWISE, x, num);
					} else {
						rightChild.insert(x, num);
					}

				}

			}

		}

		else if (ccws == ccwe) {
			if (ccws == COUNTERCLOCKWISE || ccws == 0) {

				if (leftChild == null) {
					dire(COUNTERCLOCKWISE, x, num);
				} else {
					leftChild.insert(x,num);
				}
			}

			if (ccws == CLOCKWISE) {

				if (rightChild == null) {
					dire(CLOCKWISE, x,num);
				} else {
					rightChild.insert(x,num);
				}

			}

		}
//if two lines intersect
		else if (ccws != ccwe) {

			double x1 = x.p1.x;
			double y1 = x.p1.y;
			double x2 = x.p2.x;
			double y2 = x.p2.y;
			double x3 = data.p1.x;
			double y3 = data.p1.y;
			double x4 = data.p2.x;
			double y4 = data.p2.y;

			double xi = ((x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 - y3 * x4))
					/ ((x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4));
			double yi = ((x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 - y3 * x4))
					/ ((x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4));
			Point pi = new Point(xi, yi);
			Line n1 = new Line(x.p1, pi);
			Line n2 = new Line(x.p2, pi);
			int ccw1 = ccw(data.p1, data.p2, x.p1);

			if (ccw1 == COUNTERCLOCKWISE || ccw1 == 0) {
				if (leftChild == null) {
					dire(COUNTERCLOCKWISE, n1, num);

				} else {
					leftChild.insert(n1,num);

				}
				if (rightChild == null) {
					dire(CLOCKWISE, n2,num);

				} else {
					rightChild.insert(n2,num);
				}

			}

			else if (ccw1 == CLOCKWISE) {

				if (rightChild == null) {
					dire(CLOCKWISE, n1,num);
				} else {
					rightChild.insert(n1,num);
				}
				if (leftChild == null) {
					dire(COUNTERCLOCKWISE, n2,num);
				} else {
					leftChild.insert(n2,num);
				}
			}

		}
	}

	public void dire(int direction, Line x, int num) {

		if (direction == COUNTERCLOCKWISE) {
			MyTreeNode node = new MyTreeNode();
			node.data = x;
			node.number=num;
			leftChild = node;
			leftChild.parent = this;
		}

		else if (direction == CLOCKWISE) {

			MyTreeNode node = new MyTreeNode();
			node.data = x;
			node.number=num;
			rightChild = node;
			rightChild.parent = this;
		}

	}
	



	public int ccw(Point p0, Point p1, Point p2) {
	    double dx1 = p1.x - p0.x;
	    double dy1 = p1.y - p0.y;
	    double dx2 = p2.x - p0.x;
	    double dy2 = p2.y - p0.y;
	    if (dx1*dy2 > dy1*dx2) return COUNTERCLOCKWISE;
	    else if (dx1*dy2 < dy1*dx2) return CLOCKWISE;
	    else if ((dx1*dx2 < 0) || (dy1*dy2 < 0)) return CLOCKWISE;
	    else if ((dx1*dx1+dy1*dy1) < (dx2*dx2+dy2*dy2)) return COUNTERCLOCKWISE;
	    else return COLINEAR;
	}
	
	

	
	

	public void printPreOrder() {
		System.out.print(number + " ");
		if (leftChild != null) {
			leftChild.printPreOrder();
		}
		if (rightChild != null) {
			rightChild.printPreOrder();
		}
	}



	
	}

