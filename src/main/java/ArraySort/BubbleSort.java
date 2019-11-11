package ArraySort;

/**
 * @Author kosong.yu
 * @Date 2019-11-05
 * @Description
 * 冒泡排序
 * 每次循环找到最大数字，移到最后
 * 解法：循环数组，比较当前元素和下一个元素，如果当前元素比下一个元素大，向上冒泡。这样一次循环之后最后一个数就是本数组最大的数。下一次循环继续上面的操作，不循环已经排序好的数。
 *
 * 优化：当一次循环没有发生冒泡，说明已经排序完成，停止循环。
 * 时间复杂度：O(n2)
 * 空间复杂度：O(1)
 * 稳定性：稳定
 */
public class BubbleSort {
  public static void bubbleSort(int[] arr) {
    int tmp;
    for (int i = 0; i < arr.length; i++) {
      boolean complete = true;
      for (int j = 0; j < arr.length - 1 - i; j++) {
        // 比较相邻数字,前面的数字比较大，向后移一位
        if (arr[j] > arr[j+1]){
          tmp = arr[j];
          arr[j] = arr[j+1];
          arr[j+1] = tmp;
          complete = false;
        }
      }
      // 没有冒泡，结束循环
      if (complete){
        break;
      }
    }
  }

  public static void main(String[] args) {
    int[] arr = {6, 1, 2, 7, 9, 3, 4, 5, 10, 11, 8};
    bubbleSort(arr);
    for (int i:arr) {
      System.out.print(i);
      System.out.print(",");
    }
  }
}