import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    WeightedQuickUnionUF myUnion;
    WeightedQuickUnionUF myUnion2;
    private boolean[] open;

    private int gridWidth;

    private int openCount;

    private boolean percolator;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        myUnion = new WeightedQuickUnionUF(N * N + 1);
        myUnion2 = new WeightedQuickUnionUF(N * N + 1);
        open = new boolean[N * N];
        openCount = 0;
        gridWidth = N;
        percolator = false;
        for (int i = 0; i < N * N; i++) {
            open[i] = false;
        }
    }

    public void open(int row, int col) {
        if (row < 0 || row >= gridWidth || col < 0 || col >= gridWidth) {
            throw new IndexOutOfBoundsException();
        }
        if (!isOpen(row, col)) {
            openCount += 1;
            open[row * gridWidth + col] = true;
            if (row == 0) {
                myUnion.union(row * gridWidth + col, open.length);
            }
            if (row == gridWidth - 1) {
                myUnion2.union(row * gridWidth + col, open.length);
            }
            if (row > 0) {
                if (isOpen(row - 1, col)) {
                    myUnion.union(row * gridWidth + col, (row - 1) * gridWidth + col);
                    myUnion2.union(row * gridWidth + col, (row - 1) * gridWidth + col);
                }
            }
            if (row < gridWidth - 1) {
                if (isOpen(row + 1, col)) {
                    myUnion.union(row * gridWidth + col, (row + 1) * gridWidth + col);
                    myUnion2.union(row * gridWidth + col, (row + 1) * gridWidth + col);
                }
            }
            if (col > 0) {
                if (isOpen(row, col - 1)) {
                    myUnion.union(row * gridWidth + col, row * gridWidth + col - 1);
                    myUnion2.union(row * gridWidth + col, row * gridWidth + col - 1);
                }
            }
            if (col < gridWidth - 1) {
                if (isOpen(row, col + 1)) {
                    myUnion.union(row * gridWidth + col, row * gridWidth + col + 1);
                    myUnion2.union(row * gridWidth + col, row * gridWidth + col + 1);
                }
            }
            if (isFull(row, col) && isFull2(row, col)) {
                percolator = true;
            }
        }
    }

    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= gridWidth || col < 0 || col >= gridWidth) {
            throw new IndexOutOfBoundsException();
        }
        return open[row * gridWidth + col];
    }

    public boolean isFull(int row, int col) {
        if (row < 0 || row >= gridWidth || col < 0 || col >= gridWidth) {
            throw new IndexOutOfBoundsException();
        }
        return myUnion.connected(row * gridWidth + col, open.length);
    }

    public int numberOfOpenSites() {
        return openCount;
    }

    public boolean percolates() {
        return percolator;
    }

    public boolean isFull2(int row, int col) {
        if (row < 0 || row >= gridWidth || col < 0 || col >= gridWidth) {
            throw new IndexOutOfBoundsException();
        }
        return myUnion2.connected(row * gridWidth + col, open.length);
    }

}
