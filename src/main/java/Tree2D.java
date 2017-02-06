/**
 * Created by javlon on 22.01.17.
 */
public class Tree2D extends Tree {
    private Tree2D left;
    private Tree2D right;
    private double[] ys;

    public Tree2D(Point minP, Point maxP, int k, Tree2D left, Tree2D right, double[] ys) {
        super(minP, maxP, k);
        this.left = left;
        this.right = right;
        this.ys = ys;
    }

    @Override
    public int query(Point a, Point b) {
        if (a.getCor(dim - 2) <= minP && b.getCor(dim - 2) >= maxP) {
            if (ys[ys.length - 1] < a.getCor(dim - 1) || ys[0] > b.getCor(dim - 1))
                return 0;
            int begin = binSearchInd(ys, a, true);
            int end = binSearchInd(ys, b, false);
            return end - begin + 1;
        }
        int count = 0;
        if (left != null && a.getCor(dim - 2) <= left.getMaxP()) {
            count += left.query(a, b);
        }
        if (right != null && b.getCor(dim - 2) >= right.getMinP()) {
            count += right.query(a, b);
        }
        return count;
    }

}
