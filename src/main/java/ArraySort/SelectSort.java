package ArraySort;

/**
 * @Author kosong.yu
 * @Date 2019-11-05
 * @Description
 * 选择排序：
 *
 * 每次循环选取一个最小的数字放到前面的有序序列中。
 *
 * 时间复杂度： O(n^2)
 * 空间复杂度：O(1)
 * 稳定性： 不稳定
 */
public class SelectSort {
  public static void selectSort(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      int minIndex = i;
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[j] < arr[minIndex]) {
          minIndex = j;
        }
      }
      int tmp = arr[minIndex];
      arr[minIndex] = arr[i];
      arr[i] = tmp;
    }
  }

  public static void main(String[] args) {

    int[] arr = {6, 1, 2, 7, 9, 3, 4, 5, 10, 11, 8};
    selectSort(arr);
    for (int i:arr) {
      System.out.print(i);
      System.out.print(",");
    }
  }

}