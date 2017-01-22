package main;

/**
 * Created by javlon on 21.01.17.
 */
public class Tree2DLayered extends Tree {
    Point[] y;
    private double[] linkL;
    private double[] linkR;

    public Tree2DLayered(Point minP, Point maxP, Tree2DLayered left, Tree2DLayered right, Point[] y, double[] linkL, double[] linkR) {
        super(minP, maxP, left, right);
        this.y = y;
        this.linkL = linkL;
        this.linkR = linkR;
    }

    public int query(Point a, Point b) {
        if (y[y.length - 1].getCor(dim - 1) < a.getCor(dim - 1) || y[0].getCor(dim - 1) > b.getCor(dim - 1)) {
            return 0;
        }
        int begin = binSearchInd(y, a, true);
        int end = binSearchInd(y, b, false);
        return query(a, b, begin, end);
    }

    @Override
    int query(Point a, Point b, int k) {
        if (k == dim - 1)
            throw new IllegalArgumentException("bad");
        return query(a, b);
    }

    private int query(Point a, Point b, int begin, int end) {
        int count = 0;
        if (a.getCor(dim - 2) <= minP.getCor(dim - 2) && maxP.getCor(dim - 2) <= b.getCor(dim - 2)) {
            return end - begin + 1;
        }
        if (left != null && a.getCor(dim - 2) <= left.getMaxP().getCor(dim - 2)) {
            if ((int) Math.ceil(linkL[begin]) < ((Tree2DLayered) left).y.length && (int) Math.floor(linkL[end]) > -1)
                count += ((Tree2DLayered) left).query(a, b, (int) Math.ceil(linkL[begin]), (int) Math.floor(linkL[end]));
        }
        if (right != null && b.getCor(dim - 2) >= right.getMinP().getCor(dim - 2)) {
            if ((int) Math.ceil(linkR[begin]) < ((Tree2DLayered) right).y.length && (int) Math.floor(linkR[end]) > -1)
                count += ((Tree2DLayered) right).query(a, b, (int) Math.ceil(linkR[begin]), (int) Math.floor(linkR[end]));
        }
        return count;
    }


}