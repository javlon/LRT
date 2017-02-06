/**
 * Created by javlon on 22.01.17.
 */
public class TreeKD extends Tree {
    private TreeKD left;
    private TreeKD right;
    private Tree link;

    public TreeKD(Point minP, Point maxP, int k, TreeKD left, TreeKD right, Tree link) {
        super(minP, maxP, k);
        this.left = left;
        this.right = right;
        this.link = link;
    }

    @Override
    public int query(Point a, Point b) {
        int count = 0;
        if (a.getCor(k) <= minP && b.getCor(k) >= maxP) {
            return link.query(a, b);
        }
        if (left != null && a.getCor(k) <= left.maxP) {
            count += left.query(a, b);
        }
        if (right != null && b.getCor(k) >= right.minP) {
            count += right.query(a, b);
        }
        return count;
    }
}
