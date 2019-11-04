package MaxSubArray;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @Author kosong.yu
 * @Date 2019-10-17
 * @Description
 */
public class MaxSubArray {

   /**
    *  分治法
    *  求一个函数最大和的子数组
    *
    *  分治法实现结束 start
    */

   /**
    * 分治
    * 求中间值左右两侧的最大子数组
    * @param array
    * @param low
    * @param high
    * @return
    */
   public static HashMap<String, Integer> MaxSubArray(int[] array, int low, int high) {
      int mid = (int)Math.floor((high + low) / 2);
      HashMap<String, Integer> res = new HashMap<String, Integer>();
      if (high == low) {
         res.put("from", 0);
         res.put("to", 1);
         res.put("sum", array[0]);
         return res;
      } else {
         HashMap<String, Integer> left = MaxSubArray.MaxSubArray(array, low, mid);
         HashMap<String, Integer> right = MaxSubArray.MaxSubArray(array, mid + 1, high);
         HashMap<String, Integer> cross = MaxSubArray.MaxCrossSubArray(array, low, mid, high);
         int leftSum = left.get("sum");
         int rightSum = right.get("sum");
         int crossSum = cross.get("sum");
         if (leftSum >= rightSum && leftSum >= crossSum) {
            return left;
         } else if (rightSum >= leftSum && rightSum >= crossSum) {
            return right;
         } else {
            return cross;
         }
      }
   }

   /**
    * 跨过中间值的最大和子数组
    * @param array
    * @param low
    * @param mid
    * @param high
    * @return
    */
   public static HashMap<String, Integer> MaxCrossSubArray(int[] array, int low, int mid, int high) {
      int leftSum = 0;
      int rightSum = 0;
      int sum1 = 0;
      int sum2 = 0;
      int from = 0;
      int to = 0;
      HashMap<String, Integer> res = new HashMap<String, Integer>();

      for (int i = mid; i > low; i--) {
         sum1 += array[i];
         if (leftSum == 0 || sum1 > leftSum) {
            leftSum = sum1;
            from = i;
         }
      }
      for (int i = mid + 1; i < high; i++) {
         sum2 += array[i];
         if (rightSum == 0 || sum2 > rightSum) {
            rightSum = sum2;
            to = i;
         }
      }
      res.put("sum", rightSum + leftSum);
      res.put("from", from);
      res.put("to", to);
      return res;
   }

   /**
    * 分治法实现结束 end
    */


}