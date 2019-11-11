package ArraySort;

/**
 * @Author kosong.yu
 * @Date 2019-11-05
 * @Description
 * 堆排序
 * 解法：创建一个大顶堆，大顶堆的堆顶一定是最大的元素。
 *      交换第一个元素和最后一个元素，让剩余的元素继续调整为大顶堆。
 *      从后往前以此和第一个元素交换并重新构建，排序完成。
 *
 */
public class HeapSort {
  public static void heapSort(int[] arr) {
    createHeap(arr);
    int tmp;
    for (int i = arr.length - 1; i > 0; i--){
      tmp = arr[i];
      arr[i] = arr[0];
      arr[0] = tmp;
      adjust(arr, 0, i);
    }
  }

  public static void createHeap(int[] arr) {
    int len = arr.length;
    int start = len / 2 - 1;
    for (int i = start; i >= 0; i--) {
      adjust(arr, i, len);
    }
  }

  /**
   * 将第target个元素进行下沉，孩子节点有比他大的就下沉
   * @param arr
   * @param target
   * @param len
   */
  public static void adjust(int[] arr, int target, int len) {
    int tmp;
    for (int i = 2 * target + 1; i < len; i = 2 * i + 1) {
      // 找到孩子节点中最大的
      if (i + 1 < len && arr[i + 1] > arr[i]){
        i = i + 1;
      }
      if (arr[i] > arr[target]) {
        tmp = arr[i];
        arr[i] = arr[target];
        arr[target] = tmp;
        target = i;
      } else {
        break;
      }
    }
  }

  public static void main(String[] args) {
    int[] arr = {6, 1, 2, 7, 9, 3, 4, 5, 10, 11, 8};
    heapSort(arr);
    for (int i:arr) {
      System.out.print(i);
      System.out.print(",");
    }
  }
}