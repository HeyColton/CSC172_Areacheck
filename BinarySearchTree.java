
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
/*Name:Xiaoyu Zheng
 * Email:xzheng10@u.rochester.edu
 * Project number:3
 * Lab Section: Tue 2:00 pm and Thu 2:00 pm
 */


public class BinarySearchTree extends JPanel implements MouseListener{
private MyTreeNode root;
int NoOfLine;
int number=-1;
private static final int COUNTERCLOCKWISE = -1;
private static final int CLOCKWISE = 1;
private static final int COLINEAR = 0;
private static final int INTERSECT = 0;
private static final int side = 500;
Line[] array=new Line[1];
int size=0;
Boolean mouse=true;
Point m1;
Point m2;
Line[] lines=new Line[0];
//constructor to add mouselistener
public BinarySearchTree(){
	addMouseListener(this);
}
//main program
public void PointLocation(){
	Scanner scan=new Scanner(System.in);
	//read the number of lines
	int count=scan.nextInt();
	  scan.nextLine(); 
	  Graphics g=getGraphics();
	  //get such lines and insert
	for(int i=1;i<=count;i++){
		String[] elements=scan.nextLine().split(" ");
		Point p1=new Point(500*Double.parseDouble(elements[0]),500*Double.parseDouble(elements[1]));
		Point p2=new Point(500*Double.parseDouble(elements[2]),500*Double.parseDouble(elements[3]));
		Line l=new Line(p1,p2);
		g.drawLine((int)(p1.x), (int)(p1.y),(int)(p2.x),(int)(p2.y));
		insert(l);
	}
	
	//txt documents given by TA may or may not have an empty line right after line inputs
	int q=1;
	//compare several pairs of points
	while(scan.hasNextLine()){
		    
			String string=scan.nextLine();
			if(q==1&string.isEmpty()){	
			}else if(count==0){
				System.out.println(-1);
			}else{
			if(!string.isEmpty()){
			String[] elements = string.split(" ");
			Point p1=new Point(500*Double.parseDouble(elements[0]),500*Double.parseDouble(elements[1]));
			Point p2=new Point(500*Double.parseDouble(elements[2]),500*Double.parseDouble(elements[3]));
		    MyTreeNode node=AreaCheck(root,p1,p2);
		    if(node==null){
		    	System.out.println(-1);
		    }else{
		    	System.out.println(node.number);
		    }
			}
			if(string.isEmpty()){
				break;
			}
			}
			q=0;
	}
	//count external nodes and paths
//	int externalnodes=findexternal(root);
//	double externalPath=(double)(findpath(root,root))/(double)(externalnodes);
//	System.out.println("the number of external nodes: "+externalnodes);
//	System.out.println("the average path length: "+new DecimalFormat("0.##").format(externalPath));
	}

public int findexternal(MyTreeNode node){
    if(node==null){
    	
    	return 1;
    }
	
	return findexternal(node.leftChild)+findexternal(node.rightChild);
	
	
}
public int path(MyTreeNode node){
	
	if(node.parent==null){
		
		return 0;
	}
	else{
		
		return path(node.parent)+1;
	}
	
	
}

 public int findpath(MyTreeNode node, MyTreeNode parent){
	 
	 
	 if(node == null){
		 MyTreeNode a = new MyTreeNode();
		 a.parent=parent;
		 node = a;
		return path(a);
		
	 }
	 
	 
	 
	 return findpath(node.leftChild,node)+findpath(node.rightChild,node);
	 
	 
	 
 }
 //check if two points are at the same area
public MyTreeNode AreaCheck(MyTreeNode node, Point p1, Point p2) {
	int a1 = ccw(node.data.p1, node.data.p2,p1); 
	int a2 = ccw(node.data.p1, node.data.p2,p2);
	
	if (a1 != a2) 
		return node;
	if (a1 == CLOCKWISE) {
		if (node.rightChild == null)
			return null;
		else
			return AreaCheck(node.rightChild, p1, p2);
	}
	if (a1 == COUNTERCLOCKWISE) {
		if (node.leftChild==null)
			return null;
		else
			return AreaCheck(node.leftChild, p1, p2);
		}
	return null;
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

//call insert method in MyTreeNode
	public void insert(Line x) {
		// TODO Auto-generated method stub
		number++;
		if(root==null){
			root=new MyTreeNode();
			root.data=x;
			root.number=this.number;
		}else{
			
			root.insert(x,number);
			
		}
			
	}

	
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		double x=e.getX();
		double y=e.getY();
		System.out.println((double)(x));
		System.out.println((double)(y));
		if(mouse==true){
			m1=new Point(x,y);
			mouse=false;
		}else{
			m2=new Point(x,y);
			 if(AreaCheck(root,m1,m2)==null){
				 System.out.println("Same Area!");
			 }else{
				 System.out.println("Different Area!");
			 }
			    mouse=true;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinarySearchTree tree=new BinarySearchTree();
		JFrame jframe=new JFrame("PointLocation");
		tree.setFocusable(true);
		tree.setPreferredSize(new Dimension(500,500));
		jframe.add(tree);
		jframe.setSize(tree.side,tree.side+30);
		jframe.setVisible(true);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tree.PointLocation();
	}
}