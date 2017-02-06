/**
 * Created by javlon on 21.01.17.
 */
public class Tree2DLayered extends Tree {
    private Tree2DLayered left;
    private Tree2DLayered right;
    private double[] y;
    private int[] linkLC;
    private int[] linkLF;
    private int[] linkRC;
    private int[] linkRF;

    public Tree2DLayered(Point minP, Point maxP, int k, Tree2DLayered left, Tree2DLayered right, double[] y, double[] linkL, double[] linkR) {
        super(minP, maxP, k);
        this.left = left;
        this.right = right;
        this.y = y;
        if (y.length > 1) {
            linkLC = new int[linkL.length];
            linkLF = new int[linkL.length];
            for (int i = 0; i < linkL.length; i++) {
                linkLC[i] = (int) Math.ceil(linkL[i]);
                linkLF[i] = (int) Math.floor(linkL[i]);
            }
            linkRC = new int[linkR.length];
            linkRF = new int[linkR.length];
            for (int i = 0; i < linkR.length; i++) {
                linkRC[i] = (int) Math.ceil(linkR[i]);
                linkRF[i] = (int) Math.floor(linkR[i]);
            }
        }
    }

    public double[] getY() {
        return y;
    }

    public int query(Point a, Point b) {
        if (y[y.length - 1] < a.getCor(dim - 1) || y[0] > b.getCor(dim - 1)) {
            return 0;
        }
        int begin = binSearchInd(y, a, true);
        int end = binSearchInd(y, b, false);
        return query(a, b, begin, end);
    }

    private int query(Point a, Point b, int begin, int end) {
        if (begin >= y.length || end < 0)
            return 0;
        int count = 0;
        if (a.getCor(dim - 2) <= minP && b.getCor(dim - 2) >= maxP) {
            return end - begin + 1;
        }
        if (left != null && a.getCor(dim - 2) <= left.getMaxP()) {
            count += left.query(a, b, linkLC[begin], linkLF[end]);
        }
        if (right != null && b.getCor(dim - 2) >= right.getMinP()) {
            count += right.query(a, b, linkRC[begin], linkRF[end]);
        }
        return count;
    }


}
