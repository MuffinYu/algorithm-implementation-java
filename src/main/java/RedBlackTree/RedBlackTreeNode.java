package RedBlackTree;

import lombok.*;

/**
 * @Author kosong.yu
 * @Date 2019-09-30
 * @Description
 */
@AllArgsConstructor
@NoArgsConstructor
public class RedBlackTreeNode {

   @Getter
   @Setter
   private String color = NodeColor.Black;

   @Getter
   @Setter
   private int key = 0;

   @Getter
   @Setter
   private RedBlackTreeNode left;

   @Getter
   @Setter
   private RedBlackTreeNode right;

   @Getter
   @Setter
   private RedBlackTreeNode parent;

}