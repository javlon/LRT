/**
 * Created by javlon on 22.01.17.
 */
abstract class Tree {
    protected double minP;
    protected double maxP;
    protected int k;
    protected int dim;

    public Tree(Point minP, Point maxP, int k) {
        this.minP = minP.getCor(k);
        this.maxP = maxP.getCor(k);
        this.k = k;
        this.dim = minP.getDim();
    }

    public double getMinP() {
        return minP;
    }

    public double getMaxP() {
        return maxP;
    }

    protected int binSearchInd(double[] y, Point a, boolean bool) {
        int l = 0;
        int r = y.length - 1;
        if (bool) {
            while (l < r) {
                int mid = (l + r) / 2;
                if (y[mid] >= a.getCor(dim - 1)) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
        } else {
            while (l < r) {
                int mid = (l + r) / 2 + 1;
                if (y[mid] > a.getCor(dim - 1)) {
                    r = mid - 1;
                } else {
                    l = mid;
                }
            }
        }
        return l;
    }

    public abstract int query(Point a, Point b);
}
