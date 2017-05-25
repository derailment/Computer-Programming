
public class TestLine2D {
	public static void main(String[] args) {
		Line2D[] lines = new Line2D[5];
		for (int i = 0; i < lines.length; i++) {
			lines[i] = new Line2D(Math.random() * 10, Math.random() * 10, Math.random() * 10, Math.random() * 10);
		}
		System.out.println("Print the line objects in sequence:");
		printInSequence(lines);
		System.out.println("Shuffle and print the line objects:");
		printInShuffle(lines);
		Line2D line = findMaxLength(lines);
		System.out.println("The segment with maximum length:");
		System.out.println(" Length = " + line.getLength());
	}
	
	public static void printInSequence(Line2D[] lines){
		for(Line2D line: lines){
			System.out.print(" " + line.getLength());
		}	
		System.out.print("\n");
	}
	
	public static void printInShuffle(Line2D[] lines){
		Line2D tmpLine;
		for(int i = lines.length ; i >= 1 ; i--){
			int j = (int)(Math.random() * i);
			tmpLine = lines[i - 1];
			lines[i - 1] = lines[j];
			lines[j] = tmpLine;
		}
		
		printInSequence(lines);
	}
	
	public static Line2D findMaxLength(Line2D[] lines){
		Line2D maxLine = new Line2D(0, 0, 0, 0);
		for(Line2D line: lines){
			if (maxLine.getLength() < line.getLength()){
				maxLine = line;
			}
		}	
		return maxLine;
	}

}

class Line2D {
	
	private double x1;
	private double y1;
	private double x2;
	private double y2;
	private double length;
	
	Line2D(double x1, double y1, double x2, double y2){
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.length = Math.sqrt(Math.pow(y2-y1, 2) + Math.pow(x2-x1, 2));
	}
	
	public double getLength(){
		return this.length;
	}
	
}
