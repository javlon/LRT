/**
 * Created by javlon on 22.01.17.
 */
abstract class Tree {
    Point minP;
    Point maxP;
    Tree left;
    Tree right;
    int dim;

    public Tree(Point minP, Point maxP, Tree left, Tree right) {
        this.minP = minP;
        this.maxP = maxP;
        this.left = left;
        this.right = right;
        this.dim = minP.getDim();
    }

    public Point getMinP() {
        return minP;
    }

    public Point getMaxP() {
        return maxP;
    }

    public abstract int query(Point a, Point b);

    abstract int query(Point a, Point b, int k);

    int binSearchInd(Point[] y, Point a, boolean bool) {
        int l = 0;
        int r = y.length - 1;
        if (bool) {
            while (l < r) {
                int mid = (l + r) / 2;
                if (y[mid].getCor(dim - 1) >= a.getCor(dim - 1)) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
        } else {
            while (l < r) {
                int mid = (l + r) / 2 + 1;
                if (y[mid].getCor(dim - 1) > a.getCor(dim - 1)) {
                    r = mid - 1;
                } else {
                    l = mid;
                }
            }
        }
        return l;
    }
}
