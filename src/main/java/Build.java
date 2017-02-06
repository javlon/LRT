import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by javlon on 22.01.17.
 */
public class Build {
    private static Tree2DLayered doTree2DLayered(Point[] m) {
        final int dim = m[0].getDim();

        if (m.length == 0) {
            throw new IllegalArgumentException("A count of points must be more than zero!");
        } else if (m.length == 1) {
            double[] y = new double[1];
            y[0] = m[0].getCor(dim - 1);
            return new Tree2DLayered(m[0], m[0], dim - 2, null, null, y, null, null);
        }

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
        Tree2DLayered left = doTree2DLayered(mL);
        Tree2DLayered right = doTree2DLayered(mR);

        double[] y = Arrays.stream(m).mapToDouble(x -> x.getCor(dim - 1)).sorted().toArray();
        double[] linkL = new double[length];
        double[] yL = left.getY();
        for (int i = 0, ind = 0; i < length; i++) {
            if (ind < sizeL && y[i] == yL[ind]) {
                linkL[i] = ind;
                ++ind;
            } else {
                linkL[i] = -0.5 + ind;
            }
        }

        double[] linkR = new double[length];
        double[] yR = right.getY();
        for (int i = 0, ind = 0; i < length; i++) {
            if (ind < sizeR && y[i] == yR[ind]) {
                linkR[i] = ind;
                ++ind;
            } else {
                linkR[i] = -0.5 + ind;
            }
        }

        return new Tree2DLayered(minP, maxP, dim - 2, left, right, y, linkL, linkR);
    }

    private static Tree2D doTree2D(Point[] m) {
        final int dim = m[0].getDim();
        if (m.length == 0) {
            throw new IllegalArgumentException("A count of points must be more than one!");
        } else if (m.length == 1) {
            double[] ys = new double[1];
            ys[0] = m[0].getCor(dim - 1);
            return new Tree2D(m[0], m[0], dim - 2, null, null, ys);
        }

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
        Tree2D left = doTree2D(mL);
        Tree2D right = doTree2D(mR);

        double[] ys = Arrays.stream(m).mapToDouble(x -> x.getCor(dim - 1)).sorted().toArray();
        return new Tree2D(minP, maxP, dim - 2, left, right, ys);
    }

    private static Tree doTree(Point[] m, int k, boolean isLayered) {
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
            return new TreeKD(m[0], m[0], k, null, null, doTree(m, k + 1, isLayered));
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
        TreeKD left = (TreeKD) doTree(mL, k, isLayered);
        TreeKD right = (TreeKD) doTree(mR, k, isLayered);
        Tree link = doTree(m, k + 1, isLayered);
        return new TreeKD(minP, maxP, k, left, right, link);
    }

    public static Tree doTree(Point[] m, boolean isLayered) {
        return doTree(m, 0, isLayered);
    }
}
