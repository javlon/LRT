/**
 * Created by javlon on 22.01.17.
 */
public class TreeKD extends Tree {
    private Tree link;

    public TreeKD(Point minP, Point maxP, Tree left, Tree right, Tree link) {
        super(minP, maxP, left, right);
        this.link = link;
    }

    @Override
    public int query(Point a, Point b) {
        return query(a, b, 0);
    }

    public int query(Point a, Point b, int k) {
        int count = 0;
        if (a.getCor(k) <= minP.getCor(k) && b.getCor(k) >= maxP.getCor(k)) {
            return link.query(a, b, k + 1);
        }
        if (left != null && a.getCor(k) <= left.maxP.getCor(k)) {
            count += left.query(a, b, k);
        }
        if (right != null && b.getCor(k) >= right.minP.getCor(k)) {
            count += right.query(a, b, k);
        }
        return count;
    }
}
