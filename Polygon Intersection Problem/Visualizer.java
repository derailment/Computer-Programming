import java.util.*;
import javafx.application.*;
import javafx.stage.*;
import javafx.geometry.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.scene.*;


public class Visualizer extends Application {
	private Group group;
	@Override
	public void start(Stage primaryStage) {
		group = new Group();
		String ans;
		while(true){
			System.out.println("Only one polygon(Y/N)?");
			Scanner input = new Scanner(System.in);
			ans = input.next();
			if(ans.equalsIgnoreCase("y")){
				Polygon polygon = new Polygon(12);
				addPolygon(polygon, 1);
				break;
			}
			if(ans.equalsIgnoreCase("n")){
				PolygonFactory polygons = new PolygonFactory();
				for(int i =0;i<polygons.size();i++){
					Polygon polygon = polygons.get(i);
					addPolygon(polygon, i+1);
				}
				break;
			}
		}
		Scene scene = new Scene(group);
		primaryStage.setTitle("Visualizer");
		primaryStage.setWidth(500);
		primaryStage.setHeight(500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public void addPolygon(Polygon polygon, int id){
		Point2D[] points = polygon.getPoints();
		Line[] dashlines = polygon.getDashLines();
		Line[] lines = polygon.getLines();
		for (int i = 0; i < points.length; i++) {
			// add points
			Circle circle = new Circle();
			circle.setCenterX(points[i].getX());
			circle.setCenterY(points[i].getY());
			circle.setRadius(2);
			group.getChildren().add(circle);
			// add dash lines
			dashlines[i].getStrokeDashArray().addAll(2d);
			dashlines[i].setStroke(Color.GRAY);
			group.getChildren().add(dashlines[i]);
			// add solid lines
			group.getChildren().add(lines[i]);

		}
		// add identifier
		Text identifier = new Text(String.valueOf(id));
		identifier.setStyle("-fx-font-size: 25;");
		identifier.setX(polygon.getCenterX()-identifier.getLayoutBounds().getWidth()/2);
		identifier.setY(polygon.getCenterY()+identifier.getLayoutBounds().getHeight()/2);
		group.getChildren().add(identifier);
	}
	public static void main(String[] args){ 
		launch(args);
	}
}
