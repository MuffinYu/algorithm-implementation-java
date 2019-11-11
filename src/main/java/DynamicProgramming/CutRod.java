package DynamicProgramming;

/**
 * @Author kosong.yu
 * @Date 2019-11-08
 * @Description
 * 切割钢条
 * 1. 带备忘录的自顶向下法（top-down with memoization）
 * 2. 自底向上法（bottom-up method）
 */
public class CutRod {

  /**
   * 朴素递归方案
   * 包含重复的计算，n 每增加 1，运算次数几乎增加一倍；
   * @param p
   * @param n
   * @return
   */
  public static int cutRod(int[] p, int n){
    if (n == 0) {
      return 0;
    }
    int q = Integer.MIN_VALUE;
    for (int i = 1; i <= n; i++) {
      q = Math.max(q, p[i] + cutRod(p, n -i));
    }
    return q;
  }

  /**
   * 自顶向下法 自带备忘录
   * @param p 价格组[1,...,n]，对应长度 n 的钢条价格
   * @param n 钢条长度
   */
  public static int memoizedCutRod(int[] p, int n) {
    int[] r = new int[n + 1];
    for (int i = 0; i <= n; i++) {
      r[i] = Integer.MIN_VALUE;
    }
    return memoizedCutRodAux(p, n, r);
  }

  public static int memoizedCutRodAux(int[] p, int n, int[] r){
    if (r[n] >= 0) {
      return r[n];
    }
    int q;

    if (n == 0) {
      q = 0;
    } else {
      q = Integer.MIN_VALUE;
      for (int i = 1; i <= n; i++) {
        q = Math.max(q, p[i] + memoizedCutRodAux(p, n-i, r));
      }
    }

    r[n] = q;
    return q;
  }

  /**
   * 自底向上法
   * 依次求解规模为 i = 0, 1, 2, ...,n的子问题
   * @param p
   * @param n
   * @return
   */
  public static int bottomUpCutRod(int[] p, int n) {
    int[] r = new int[n+1];
    r[0] = 0;
    int q;
    for (int i = 0; i <=n ; i++) {
      q = Integer.MIN_VALUE;
      for (int j = 0; j <= i; j++) {
        q = Math.max(q, p[j] + r[i-j]);
      }
      r[i] = q;
    }
    return r[n];
  }

  public static void main(String[] args) {
    // 长度为 i 的钢条价格
    int[] p = {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
    int[] ns =   {1, 2, 3, 4,  5,  6,  7,  8,  9, 10};
    // 最优收益    {1, 5, 8, 10, 13, 17, 18,22, 25, 30}
    for (int i = 0; i < ns.length; i++) {
//      int r = memoizedCutRod(p, ns[i]);
      int r = bottomUpCutRod(p, ns[i]);
      System.out.println( "长度：" + ns[i] + "=>价格" +  r);
    }

  }

}