package MaxSubArray;

import java.util.HashMap;

/**
 * @Author kosong.yu
 * @Date 2019-10-17
 * @Description
 */
public class Test {
   public static void main(String[] args) {
//      int[] arr = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
//      int[] arr = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 50};
//      int[] arr = {-13, -3, -25, -20, -3, -16, -23, -18, -20, -7, -12, -5, -22, -15, -4, -50};
      int[] arr = {-13};
      HashMap res = MaxSubArray.MaxSubArray(arr, 0, arr.length);
      System.out.println(res.get("sum"));
      System.out.println(res.get("from"));
      System.out.println(res.get("to"));
   }
}