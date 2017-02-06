

import java.util.Arrays;

/**
 * Created by javlon on 21.01.17.
 */
public class Point {
    private int d;
    private double[] p;

    public Point(int d, double[] p) {
        this.d = d;
        this.p = p;
    }

    public double getCor(int i) {
        return p[i];
    }

    public int getDim() {
        return d;
    }

    @Override
    public String toString() {
        return Arrays.toString(p);
    }
}
