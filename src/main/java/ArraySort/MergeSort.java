package ArraySort;

import java.util.Arrays;

/**
 * @Author kosong.yu
 * @Date 2019-11-05
 * @Description
 * 归并排序：
 * 解法：    该算法是采用分治法（Divide and Conquer）的一个非常典型的应用，
 * 将已有序的子序列合并，得到完全有序的序列，即先使每个子序列有序，再使子序列段间有序，若将两个有序表合并成一个有序表，称为二路归并；
 * 1. 分割：将数组从中点进行分割，分为左、右两个数组，递归分割左、右数组，直到数组长度小于2；
 * 2. 归并：如果需要合并，那么左右两数组已经有序了。创建一个临时存储数组temp，
 * ，比较两数组第一个元素，将较小的元素加入临时数组。若左右数组有一个为空，那么此时另一个数组一定大于temp
 * 中的所有元素，直接将其所有元素加入temp；
 *
 * 稳定性：   归并排序是一种稳定的排序。
 *
 * 时间复杂度：对长度为n的文件，需进行趟二路归并，每趟归并的时间为O(n)，故其时间复杂度无论是在最好情况下还是在最坏情况下均是O
 * (nlgn)。
 *
 * 空间复杂度：需要一个辅助向量来暂存两有序子文件归并的结果，故其辅助空间复杂度为O(n)，显然它不是就地排序。
 */
public class MergeSort {
  public static int[] mergeSort(int[] arr, int low, int high) {
    int mid = (low + high) / 2;
    if (low < high) {
      mergeSort(arr, low, mid);
      mergeSort(arr, mid+1, high);
      merge(arr, low, mid, high);
    }
    return arr;
  }

  public static void merge(int[] arr, int low, int mid, int high) {
    int[] tmp = new int[high - low + 1];
    int i = low;
    int j = mid + 1;
    int k = 0;
    // 把较小的数先移到新数组中
    while (i<= mid && j <= high) {
      if (arr[i] < arr[j]){
        tmp[k++] = arr[i++];
      } else {
        tmp[k++] = arr[j++];
      }
    }
    // 把左边剩余的移入数组
    while (i <= mid) {
      tmp[k++] = arr[i++];
    }
    // 把右边边剩余的数移入数组
    while (j<=high){
      tmp[k++] = arr[j++];
    }
    // 把新数组中的数覆盖排序数组
    for (int x = 0; x< tmp.length; x++) {
      arr[x+low] = tmp[x];
    }
  }

  public static void main(String[] args) {
    int[] arr = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8, 11};
    mergeSort(arr, 0, arr.length - 1);
    for (int i:arr) {
      System.out.print(i);
      System.out.print(",");
    }
  }
}