public class GradientLocalization {

	
	
	public static double euclideanDistance(Point p1, Point p2) {
		return euclideanDistance(p1.x, p1.y, p2.x, p2.y);
	}
	
	public static double euclideanDistance(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow((x1-x2),2) + Math.pow(y1-y2, 2));
	}
	
	
	public static void test() {
		//bekannte Punkte
		Point p1 = new Point(1, 1);
		Point p2 = new Point(5, 1);
		Point p3 = new Point(2, 3);
		
		//Gesuchter unbekannter Punkt: p_s = (4,4). Wird initialisiert auf nächsten bekannten Nachbarpunkt.
		Point p_s = new Point(2,3);

		for (int i = 0; i<=100; i++) {
			
			//Update rate alpha
			double alpha = 0.5;
			
			//Berechnete Distanzen 
			double cal_p1_p_s = euclideanDistance(p1, p_s);
			double cal_p2_p_s = euclideanDistance(p2, p_s);
			double cal_p3_p_s = euclideanDistance(p3, p_s);
		
			//Geschätzte Distanzen. In der Aufgabe über Hop-Count geschätzt.
			double est_p1_p_s = 4.242640687119285;
			double est_p2_p_s = 3.1622776601683795;
			double est_p3_p_s = 2.23606797749979;
			
			//Partiellen Ableitungen
			double gradientX = gradientX(p1,p_s,cal_p1_p_s, est_p1_p_s) + gradientX(p2,p_s,cal_p2_p_s, est_p2_p_s) + gradientX(p3,p_s,cal_p3_p_s, est_p3_p_s);
			double gradientY = gradientY(p1,p_s,cal_p1_p_s, est_p1_p_s) + gradientY(p2,p_s,cal_p2_p_s, est_p2_p_s) + gradientY(p3,p_s,cal_p3_p_s, est_p3_p_s);
			
			//Coordinate updates
			double delta_x = -alpha*gradientX;
			double delta_y = -alpha*gradientY;
		
			//new Location
			p_s = new Point(p_s.x-delta_x, p_s.y-delta_y);
		
			System.out.println(p_s);
		}
		
	}
	
	public static double gradientX(Point p1, Point p_s, double calDist, double estDist) {
		return ((p_s.x - p1.x)*(1-(calDist/estDist)));
	}
	
	public static double gradientY(Point p1, Point p_s, double calDist, double estDist) {
		return ((p_s.y - p1.y)*(1-(calDist/estDist)));
	}
	
	public static void main(String[] args) {
		test();
	}
	
}
