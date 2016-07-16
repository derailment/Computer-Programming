import java.util.*;
import javafx.geometry.Point2D;
import javafx.scene.shape.Line;

public class Polygon {
	
	private int n;
	private double r;
	private double x0;
	private double y0;
	private double alpha;
	private Point2D[] points;
	private Line[] dashlines;
	private Line[] lines;
	
	Polygon(int n){
		this(n, 100, 200, 200, 0);
	}
	
	Polygon(int n, double r, double x0, double y0, double alpha){
		this.n = n;
		this.r = r;
		this.x0 = x0;
		this.y0 = y0;
		this.alpha = alpha;
		// Initialize points and dash lines
		points = new Point2D[n];
		dashlines = new Line[n];
		double theta = alpha;
		for(int i=0;i<n;i++){
			Point2D point = new Point2D(x0+r*Math.cos(theta), y0-r*Math.sin(theta));
			points[i] = point;
			Line line = new Line(x0,y0,point.getX(),point.getY());
			dashlines[i] = line;
			theta = theta + 2*Math.PI/n;
		}
		// Initialize solid lines
		lines = new Line[n];
		for(int i=0;i<n-1;i++){
			Line line = new Line(points[i].getX(),points[i].getY(),points[i+1].getX(),points[i+1].getY());
			lines[i] = line;
		}
		Line line = new Line(points[n-1].getX(),points[n-1].getY(),points[0].getX(),points[0].getY());
		lines[n-1] = line;	
	}
	
	public Point2D[] getPoints(){
		return points;
	}
	
	public Line[] getDashLines(){
		return dashlines;
	}
	
	public Line[] getLines(){
		return lines;
	}
	
	public double getCenterX(){
		return x0;
	}
	
	public double getCenterY(){
		return y0;
	}
	
	public int getSide(){
		return n;
	}

}
