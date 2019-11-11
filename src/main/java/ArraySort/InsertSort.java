package ArraySort;

/**
 * @Author kosong.yu
 * @Date 2019-11-05
 * @Description
 * 插入排序
 * 解法： 将左侧序列看成一个有序序列，每次将一个数字插入该有序序列。插入时，从有序序列最右侧开始比较，若比较的数较大，后移一位。
 * 时间复杂度：O(n2)
 * 空间复杂度：O(1)
 * 稳定性：稳定
 */
public class InsertSort {
  public static void insertSort(int[] arr){
    int tmp;
    for (int i = 1; i < arr.length; i++) {
      int target = i;
      for (int j = i - 1; j >= 0; j--) {
        if (arr[target] < arr[j]) {
          tmp = arr[target];
          arr[target] = arr[j];
          arr[j] = tmp;
          target = j;
        } else {
          break;
        }
      }
    }
  }

  public static void main(String[] args) {
    int[] arr = {6, 1, 2, 7, 9, 3, 4, 5, 10, 11, 8};
    insertSort(arr);
    for (int i:arr) {
      System.out.print(i);
      System.out.print(",");
    }
  }
}