// Dennis Agostinho da Silva
// 25.01.2020

package Aufgabe4.TelNetApplication;

public class UnionFind {
    private final int[] p;
    private int size;

    public UnionFind(int n) {
        p = new int[n];
        this.size = n;

        for (int i = 0; i < n; i++) {
            p[i] = -1;
        }
    }

    /*
    Test-Main.
     */
    public static void main(String[] args) {
        UnionFind unionFind = new UnionFind(12);
        unionFind.union(0, 2);
        unionFind.union(0, 1);
        unionFind.union(6, 4);
        unionFind.union(0, 4);
        unionFind.union(0, 6);


        System.out.println(unionFind.toString());
    }

    public int find(int e) {
        while (p[e] >= 0) { // e ist keine Wurzel
            e = p[e];
        }
        return e;
    }

    /*
    Union-by-height
     */
    public void union(int s1, int s2) {
        if (p[s1] >= 0 || p[s2] >= 0) {
            return;
        }
        if (s1 == s2) {
            return;
        }

        if (-p[s1] < -p[s2]) {
            p[s1] = s2;
        } else {
            if (-p[s1] == -p[s2]) {
                p[s1]--; // Höhe von s1 erhöht sich um 1
            }
            p[s2] = s1;
        }
        this.size--;
    }

    public int size() {
        return this.size;
    }

    /*
    Test-toString.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.size(); i++) {
            if (i == find(i)) {
                sb.append("{").append(i);
                for (int j = 0; j < this.size(); j++) {
                    if (i != j) {
                        if (find(i) == find(j)) {
                            sb.append(" ").append(j);
                        }
                    }
                }
                sb.append("} ");
            }
        }
        return sb.toString();
    }
}
