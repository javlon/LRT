package main;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by javlon on 22.01.17.
 */
public class Build {
    static Tree2DLayered doTree2DLayered(Point[] m) {
        if (m.length == 0) {
            throw new IllegalArgumentException("A count of points must be more than zero!");
        } else if (m.length == 1) {
            Point[] y = new Point[1];
            y[0] = m[0];
            return new Tree2DLayered(m[0], m[0], null, null, y, null, null);
        }

        final int dim = m[0].getDim();

        int length = m.length;
        int sizeL = length / 2;
        int sizeR = length - sizeL;

        Arrays.sort(m, Comparator.comparingDouble(x -> x.getCor(dim - 2))); //sort by x
        Point[] mL = new Point[sizeL];
        Point[] mR = new Point[sizeR];
        System.arraycopy(m, 0, mL, 0, sizeL);
        System.arraycopy(m, sizeL, mR, 0, sizeR);
        Point minP = m[0];
        Point maxP = m[length - 1];
        Tree2DLayered left = doTree2DLayered(mL);
        Tree2DLayered right = doTree2DLayered(mR);

        Point[] y = new Point[length];
        Arrays.sort(m, Comparator.comparingDouble(x -> x.getCor(dim - 1)));
        System.arraycopy(m, 0, y, 0, length);
        double[] linkL = new double[length];
        Point[] yL = left.y;
        for (int i = 0, ind = 0; i < length; i++) {
            if (ind < sizeL && y[i].equals(yL[ind])) {
                linkL[i] = ind;
                ++ind;
            } else {
                linkL[i] = -0.5 + ind;
            }
        }

        double[] linkR = new double[length];
        Point[] yR = right.y;
        for (int i = 0, ind = 0; i < length; i++) {
            if (ind < sizeR && y[i].equals(yR[ind])) {
                linkR[i] = ind;
                ++ind;
            } else {
                linkR[i] = -0.5 + ind;
            }
        }

        return new Tree2DLayered(minP, maxP, left, right, y, linkL, linkR);
    }

    static Tree2D doTree2D(Point[] m) {
        if (m.length == 0) {
            throw new IllegalArgumentException("A count of points must be more than one!");
        } else if (m.length == 1) {
            Point[] ys = new Point[1];
            ys[0] = m[0];
            return new Tree2D(m[0], m[0], null, null, ys);
        }

        final int dim = m[0].getDim();
        int length = m.length;
        int sizeL = length / 2;
        int sizeR = length - sizeL;

        Arrays.sort(m, Comparator.comparingDouble(x -> x.getCor(dim - 2)));
        Point[] mL = new Point[sizeL];
        Point[] mR = new Point[sizeR];
        System.arraycopy(m, 0, mL, 0, sizeL);
        System.arraycopy(m, sizeL, mR, 0, sizeR);
        Point minP = m[0];
        Point maxP = m[length - 1];
        Tree left = doTree2D(mL);
        Tree right = doTree2D(mR);

        Arrays.sort(m, Comparator.comparingDouble(x -> x.getCor(dim - 1)));
        Point[] ys = new Point[length];
        System.arraycopy(m, 0, ys, 0, length);

        return new Tree2D(minP, maxP, left, right, ys);
    }

    static Tree doTree(Point[] m, int k, boolean isLayered) {
        final int dim = m[0].getDim();
        if (k == dim - 2) {
            if (isLayered)
                return doTree2DLayered(m);
            else
                return doTree2D(m);
        }

        if (m.length == 0) {
            throw new IllegalArgumentException("A count of points must be more than one!");
        } else if (m.length == 1) {
            return new TreeKD(m[0], m[0], null, null, doTree(m, k + 1, isLayered));
        }

        int length = m.length;
        int sizeL = length / 2;
        int sizeR = length - sizeL;

        Arrays.sort(m, Comparator.comparingDouble(x -> x.getCor(k)));
        Point[] mL = new Point[sizeL];
        Point[] mR = new Point[sizeR];
        System.arraycopy(m, 0, mL, 0, sizeL);
        System.arraycopy(m, sizeL, mR, 0, sizeR);
        Point minP = m[0];
        Point maxP = m[length - 1];
        Tree left = doTree(mL, k, isLayered);
        Tree right = doTree(mR, k, isLayered);
        Tree link = doTree(m, k + 1, isLayered);
        return new TreeKD(minP, maxP, left, right, link);
    }
}
