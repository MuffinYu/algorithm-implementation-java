package ArraySort;

import java.lang.reflect.Array;

/**
 * @Author kosong.yu
 * @Date 2019-11-04
 * @Description
 * 快速排序：
 * 1. 通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据比另一部分的所有数据要小
 * 2. 再按这种方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，使整个数据变成有序序列
 *
 * 时间复杂度： 平均O(nlogn)，最坏O(n2)，实际上大多数情况下小于O(nlogn)
 * 空间复杂度： O(logn)（递归调用消耗）
 * 稳定性： 不稳定
 */
public class QuickSort {
  public static void quickSort(int[] arr, int left, int right) {
    if (right - left < 1) {
      return;
    }
    int l = left;
    int r = right;
    int pivot = arr[left];
    while (l < r) {
      while (l < r && arr[r] >= pivot) {
        r--;
      }
      arr[l] = arr[r];
      while (l < r && arr[l] < pivot) {
        l++;
      }
      arr[r] = arr[l];
    }
    arr[l] = pivot;
    quickSort(arr, left, l - 1);
    quickSort(arr, l + 1, right);
  }

  public static void main(String[] args) {
    int[] arr = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
    quickSort(arr, 0, arr.length - 1);
    for (int i:arr) {
      System.out.print(i);
    }
  }
}