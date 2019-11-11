package GreedyAlgorithm;

import java.util.ArrayList;

/**
 * @Author kosong.yu
 * @Date 2019-11-10
 * @Description
 * 活动选择问题
 * 采用自顶向下进行计算，选择一个活动放入最优解，然后，对剩余的子问题（包含与已选择的活动兼容的活动）进行求解。
 * 贪心算法通常都是这种自顶向下的设计：作出一个选择，然后求解剩下的那个子问题，而不是自底向上的求解出很多子问题，然后再做选择。
 */
public class RecursiveActivitySelector {

  /**
   *
   * @param s 活动开始的时间
   * @param f 活动结束的时间，假设已经单调递增排好序
   * @param k
   * @param n
   * @param activity
   */
  public static void recursiveActivitySelector(int[] s, int[] f,
                                               int k, int n,
                                               ArrayList activity) {
    int m = k + 1;
    // 找到 s 中最早结束的活动
    while (m < n && s[m] < f[k]) {
      m = m + 1;
    }
    if (m < n) {
      activity.add(m);
      recursiveActivitySelector(s, f, m, n, activity);
    }
  }

  public static void main(String[] args) {
//    int[] s = {0, 1, 3, 0, 5, 3, 5, 6, 8, 8, 2, 12};
//    int[] f = {0, 4, 5, 6, 7, 9, 9, 10, 11, 12, 14, 16};
//    ArrayList activity = new ArrayList();
//    recursiveActivitySelector(s, f, 0, s.length, activity);
//    System.out.println(activity.toString());
    int[][] grid = {{1, 2, 3}, {4, 5, 6}};
    System.out.println(grid.length);
//    [[1,3,1],[1,5,1],[4,2,1]]
  }

  public int minPathSum(int[][] grid) {
    int m = grid.length;
    if(m == 0) {
      return 0;
    }
    int n = grid[0].length;
    int[][] path = new int[n][m];
    for (int i = 0; i < m; i++) {
      path[0][i] = grid[0][i];
    }
    for (int i = 0; i < n; i++) {
      path[i][0] = grid[i][0];
    }
    for (int i = 1; i < m ; i++) {
      for (int j = 1; j < n; j++) {
        path[i][j] =
          Math.min(path[j-1][i], path[j][i-1]) + grid[j][i];
      }
    }
    return path[n-1][m-1];
  }
//
//  public static int point(int[][] grid, int n, int m) {
//    if (n > 0 && m > 0) {
//      return Math.min(point(grid, n-1, m), point(grid, n, m-1));
//    } else if (n > 0) {
//      return point(grid, n-1, m);
//    } else if (m > 0){
//      return point(grid, n, m-1);
//    } else {
//      return grid[0][0];
//    }
//  }
}