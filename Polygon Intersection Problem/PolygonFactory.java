import java.util.*;
import javafx.geometry.Point2D;
import javafx.scene.shape.Line;

public class PolygonFactory {
	private Polygon[] polygons;
	private int size;
	PolygonFactory(){		
		// Initialize polygons
		size = (int)(Math.random()*9)+2; // 2 <= size <= 10
		polygons = new Polygon[size];
		for(int i=0;i<size;i++){
			int  n = (int)(Math.random()*18)+3; // 3 <= n <= 20
			double  r = (int)(Math.random()*51)+50; // 50 <= r <= 100
			double  x0 = (int)(Math.random()*301)+200; // 200 <= x0 <= 500
			double  y0 = (int)(Math.random()*301)+200; // 200 <= y0 <= 500
			double  alpha = Math.random()*Math.PI; // 0 <= alpha < PI
			Polygon polygon = new Polygon(n, r, x0, y0, alpha);
			polygons[i] = polygon;
		}
		// Judge which pair of polygons overlaps
		ArrayList<Pair> pairs = new ArrayList<Pair>();
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				if(i!=j && !pairs.contains(new Pair(i,j))){
					Polygon one = polygons[i];
					Polygon another = polygons[j];
					Point2D[] ptsAnother = another.getPoints();
					Line[] lisOne = one.getLines();
					for(int k=0;k<another.getSide();k++){
						double thresholdY = ptsAnother[k].getY();
						ArrayList<Line> twoline = new ArrayList<Line>();
						for(int l=0;l<one.getSide();l++){
							double maxY = Math.max(lisOne[l].getStartY(), lisOne[l].getEndY());
							double minY = Math.min(lisOne[l].getStartY(), lisOne[l].getEndY());
							if(thresholdY>=minY && thresholdY<=maxY){
								twoline.add(lisOne[l]);
							}
							if(twoline.size()==2)break;
						}
						if(twoline.size()==0)continue;
						if(isAnotherInsideOne(ptsAnother[k], twoline)){
							System.out.println((i+1)+" and "+(j+1)+" overlap.");
							pairs.add(new Pair(i,j));
							break;
						}
					}
				}
			}
		}
	}
	public int size(){
		return size;
	}
	public Polygon get(int id){
		return polygons[id];
	}
	boolean isAnotherInsideOne(Point2D pt, ArrayList<Line> twoline){
		double thresholdX = pt.getX();
		Line l0 = twoline.get(0);
		double maxy0 = Math.max(l0.getStartY(),l0.getEndY());
		double miny0 = Math.min(l0.getStartY(),l0.getEndY());
		double maxx0 = Math.max(l0.getStartX(),l0.getEndX());
		double minx0 = Math.min(l0.getStartX(),l0.getEndX());
		double x0;
		if((l0.getStartX()==minx0 && l0.getStartY()==miny0) || (l0.getStartX()==maxx0 && l0.getStartY()==maxy0)){
			x0 = maxx0 - (maxy0 - pt.getY()) / (maxy0 - miny0) * (maxx0 - minx0);
		}
		else{
			x0 = (maxy0 - pt.getY()) / (maxy0 - miny0) * (maxx0 - minx0) + minx0;
		}
		Line l1 = twoline.get(1);
		double maxy1 = Math.max(l1.getStartY(),l1.getEndY());
		double miny1 = Math.min(l1.getStartY(),l1.getEndY());
		double maxx1 = Math.max(l1.getStartX(),l1.getEndX());
		double minx1 = Math.min(l1.getStartX(),l1.getEndX());
		double x1;
		if((l1.getStartX()==minx1 && l1.getStartY()==miny1) || (l1.getStartX()==maxx1 && l1.getStartY()==maxy1)){
			x1 = maxx1 - (maxy1 - pt.getY()) / (maxy1 - miny1) * (maxx1 - minx1);
		}
		else{
			x1 = (maxy1 - pt.getY()) / (maxy1 - miny1) * (maxx1 - minx1) + minx1;
		}
		double maxX = Math.max(x0, x1);
		double minX = Math.min(x0, x1);
		if(thresholdX>=minX && thresholdX<=maxX)return true;
		return false;
	}
}
class Pair{
	private int id0;
	private int id1;
	Pair(int id0, int id1){
		this.id0 = id0;
		this.id1 = id1;
	}
	@Override 
	public boolean equals(Object obj) {
		if(obj==this)return true;
		if(obj==null || obj.getClass()!=this.getClass())return false;
		Pair other = (Pair) obj;
		return (other.id0==this.id0 && other.id1==this.id1) || (other.id0==this.id1 && other.id1==this.id0);
	}	
}

