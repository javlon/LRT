/**
 * Created by javlon on 22.01.17.
 */
public class Tree2D extends Tree {
    private Point[] ys;

    public Tree2D(Point minP, Point maxP, Tree left, Tree right, Point[] ys) {
        super(minP, maxP, left, right);
        this.ys = ys;
    }

    @Override
    public int query(Point a, Point b) {
        if (minP.getCor(dim - 2) >= a.getCor(dim - 2) && maxP.getCor(dim - 2) <= b.getCor(dim - 2)) {
            if (ys[ys.length - 1].getCor(dim - 1) < a.getCor(dim - 1) || ys[0].getCor(dim - 1) > b.getCor(dim - 1))
                return 0;
            int begin = binSearchInd(ys, a, true);
            int end = binSearchInd(ys, b, false);
            return end - begin + 1;
        }
        int count = 0;
        if (left != null && left.getMaxP().getCor(dim - 2) >= a.getCor(dim - 2)) {
            count += left.query(a, b);
        }
        if (right != null && right.getMinP().getCor(dim - 2) <= b.getCor(dim - 2)) {
            count += right.query(a, b);
        }
        return count;
    }

    @Override
    int query(Point a, Point b, int k) {
        return query(a, b);
    }
}
