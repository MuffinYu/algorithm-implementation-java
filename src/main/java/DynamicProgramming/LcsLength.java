package DynamicProgramming;

/**
 * @Author kosong.yu
 * @Date 2019-11-10
 * @Description
 * longest-common-subsequence-problem，给定两个序列 X = (x1, x2, x3,...,
 * xm) 和 Y = (y1, y2, ... ,yn)，求 X 和 Y 长度最长的公共子序列
 */
public class LcsLength {

  public static void lcsLength(char[] X, char[] Y) {
    int m = X.length;
    int n = Y.length;
    char[][] b = new char[m][n];
    int[][] c = new int[m+1][n+1];
    for (int i = 0; i <= m; i++) {
      c[i][0] = 0;
    }
    for (int j = 0; j <= n; j++) {
      c[0][j] = 0;
    }
    for (int i = 1; i <= m ; i++) {
      for (int j = 1; j <= n; j++) {
        if (X[i-1] == Y[j-1]){
          c[i][j] = c[i-1][j-1] + 1;
          b[i-1][j-1] = 'a';
        } else if (c[i-1][j] >= c[i][j-1]){
          c[i][j] = c[i-1][j];
          b[i-1][j-1] = 'b';
        } else {
          c[i][j] = c[i][j-1];
          b[i-1][j-1] = 'c';
        }
      }
    }
    printLcs(b, X, m-1, n-1);

  }

  public static void printLcs(char[][]b, char[] X, int i, int j) {
    if (i == -1 || j == -1) {
      return;
    }
    if (b[i][j] == 'a') {
      printLcs(b, X, i-1, j-1);
      System.out.println(X[i]);
    } else if (b[i][j] == 'b') {
      printLcs(b, X, i-1, j);
    } else {
      printLcs(b, X, i, j - 1);
    }
  }

  public static void lcsLength2(char[] X, char[] Y) {
    int m = X.length;
    int n = Y.length;
    int[][] c = new int[m+1][n+1];
    for (int i = 0; i <= m; i++) {
      c[i][0] = 0;
    }
    for (int j = 0; j <= n; j++) {
      c[0][j] = 0;
    }
    for (int i = 1; i <= m ; i++) {
      for (int j = 1; j <= n; j++) {
        if (X[i-1] == Y[j-1]){
          c[i][j] = c[i-1][j-1] + 1;
        } else if (c[i-1][j] >= c[i][j-1]){
          c[i][j] = c[i-1][j];
        } else {
          c[i][j] = c[i][j-1];
        }
      }
    }
    printLcs2(c, X, m, n);
  }

  public static void printLcs2(int[][] c, char[] X, int i, int j) {
    if (i == 0 || j == 0) {
      return;
    }
    System.out.println(i + ":" + j);

    if (c[i][j] == c[i-1][j-1] + 1) {
      printLcs2(c, X, i-1, j-1);
      System.out.println(X[i-1] + ":" + i + ":" + j);
    } else if (c[i-1][j] >= c[i][j-1]) {
      printLcs2(c, X, i-1, j);
    } else {
      printLcs2(c, X, i, j - 1);
    }
  }

  public static void main(String[] args) {
    char[] X = {'a', 'b', 'c', 'b', 'd', 'a', 'b'};
    char[] Y = {'b', 'd', 'c', 'a', 'b', 'a'};
    // expect: b c b a
    lcsLength(X, Y);

    System.out.println("算法改进");
    lcsLength2(X, Y);
  }
}