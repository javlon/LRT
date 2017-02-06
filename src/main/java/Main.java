/**
 * Created by javlon on 21.01.17.
 */
public class Main {
    public static void main(String[] args) {
        Point a = new Point(2, new double[]{-1, 0});
        Point b = new Point(2, new double[]{1, 1});
        Point[] points = new Point[4];
        points[0] = new Point(2, new double[]{-1, 2});
        points[1] = new Point(2, new double[]{0, 1});
        points[2] = new Point(2, new double[]{0, -2});
        points[3] = new Point(2, new double[]{1, 1});
        Tree t1 = Build.doTree(points, true);
        Tree t2 = Build.doTree(points, false);
        int n1 = t1.query(a, b);
        int n2 = t2.query(a, b);
        System.out.println("Layered range tree found: " + n1 +
                "\nSimple range tree found: " + n2);
    }
}
