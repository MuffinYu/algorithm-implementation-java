package RedBlackTree;

import lombok.Getter;
import lombok.Setter;


/**
 * @Author kosong.yu
 * @Date 2019-09-30
 * @Description
 *
 * 算法导论里是这样定义一棵红黑树的：
 *  1、每个结点或是红色的，或是黑色的
 *  2、根节点是黑色的 (*)
 *  3、每个叶结点（NIL）是黑色的 (*)
 *  4、如果一个节点是红色的，则它的两个儿子都是黑色的
 *  5、对于每个结点，从该结点到其子孙结点（nil黑节点）的所有路径上包含相同数目的黑色结点
 */
public class RedBlackTree {
   /**
    * 空节点
    * 默认颜色为黑色
    */
   public static RedBlackTreeNode nil = new RedBlackTreeNode();

   /**
    * 记录根节点
    */
   @Getter
   @Setter
   private RedBlackTreeNode root = new RedBlackTreeNode();

   /**
    * 构造空树
    */
   public RedBlackTree() {
      root = nil;
   }

   /**
    * 生成一个新节点，默认颜色为红色
    * @param key
    * @return
    */
   public RedBlackTreeNode RB_NODE(int key) {
      RedBlackTreeNode node = new RedBlackTreeNode(NodeColor.Red, key, nil, nil, nil);
      return node;
   }

   /**
    * 判断节点是否为空
    * @param node
    * @return
    */
   public boolean IsNil(RedBlackTreeNode node) {
      if (node == nil) {
         return true;
      }else {
         return false;
      }
   }

   /**
    * 插入节点
    * @param T
    * @param z
    */
   public void RB_INSERT(RedBlackTree T, RedBlackTreeNode z) {
      // 临时变量节点y,存储临时节点，默认为nil
      RedBlackTreeNode y = RedBlackTree.nil;
      // x初始为根节点
      RedBlackTreeNode x = T.getRoot();
      // 循环二分查找合适的插入点
      while (IsNil(x) == false) {
         // 保存当前节点，作为结果的跟节点
         y = x;
         if (z.getKey() < x.getKey()){
            // 查找左节点
            x = x.getLeft();
         }else {
            // 查找右节点
            x = x.getRight();
         }
      }
      // 临时节点y设置为插入点的父节点
      z.setParent(y);

      if (IsNil(y) == true) {
         // 空树时设置z为根节点
         T.setRoot((z));
      }else if (z.getKey() < y.getKey()){
         // 插入左节点
         y.setLeft(z);
      }else {
         // 插入右节点
         y.setRight(z);
      }
      // 将插入节点的左右节点设为nil，颜色为红色，已经在构造时设置过，可以省略
      z.setLeft(RedBlackTree.nil);
      z.setRight(RedBlackTree.nil);
      z.setColor(NodeColor.Red);
      // 插入调整
      RB_INSERT_FIXUP(T, z);
   }

   public void RB_INSERT_FIXUP(RedBlackTree T, RedBlackTreeNode m) {

      // 父节点是红色
      while (m != null && IsNil(m) == false &&  m.getParent().getColor() == NodeColor.Red) {
         // 父节点
         RedBlackTreeNode f = m.getParent();
         // 爷爷节点
         RedBlackTreeNode g = f.getParent();
         // 叔叔节点
         RedBlackTreeNode u = RedBlackTree.nil;
         if (f == g.getLeft()) {
            // 父节点是左节点

            u = g.getRight();
            if (u.getColor() == NodeColor.Red){
               // 叔叔节点是红色
               /**
                * 属于情况3，即叔叔节点也为红色，执行以下操作，并继续循环
                * f节点设为黑色
                * u节点设为黑色
                * g节点设为红色
                * 从g节点继续上溯循环判断，是否满足红黑树特性4
                */
               f.setColor(NodeColor.Black);
               u.setColor(NodeColor.Black);
               g.setColor(NodeColor.Red);
               m = g;
               continue;
            } else {
               // 叔叔节点是黑色
               // 判断插入节点是否是右节点
               if (m == f.getRight()) {
                  // 父节点是左节点，插入节点是右节点，叔叔节点是黑色
                  /**
                   * 图1类型，属于情况4，插入节点、父节点和祖父节点三点不一线，属于阶段1，做以下操作：
                   * f节点左旋
                   * 变成图2类型，情况4的阶段2，继续操作
                   */
                  RedBlackTreeNode tmp = m;
                  m = f;
                  f = tmp;
                  LEFT_ROTATE(T, m);
               }
               /**
                * 图2类型，情况4的阶段2，执行以下操作：
                * 父节点颜色设为黑色
                * 祖父节点颜色设为红色
                * 对祖父节点右旋
                */
               f.setColor(NodeColor.Black);
               g.setColor(NodeColor.Red);
               RIGHT_ROTATE(T, g);
            }
         } else {
            // 父节点是右节点
            u = g.getLeft();
            if (u.getColor() == NodeColor.Red) {
               // 叔叔节点是红色
               /**
                * 属于情况3，即叔叔节点也为红色，执行以下操作，并继续循环
                * f节点设为黑色
                * u节点设为黑色
                * g节点设为红色
                * 从g节点继续上溯循环判断，是否满足红黑树特性4
                */
               f.setColor(NodeColor.Black);
               u.setColor(NodeColor.Black);
               g.setColor(NodeColor.Red);
               m = g;
               continue;
            } else {
               // 叔叔节点是黑色
               // 判断插入节点是否是右节点
               if (m == f.getLeft()) {
                  // 父节点是右节点，插入节点是左节点，叔叔节点是黑色
                  /**
                   * 图3类型，属于情况4，插入节点、父节点和祖父节点三点不一线，属于阶段1，做以下操作：
                   * f节点右旋
                   * 变成图4类型，情况4的阶段2，继续操作
                   */
                  RedBlackTreeNode tmp = m;
                  m = f;
                  f = tmp;
                  RIGHT_ROTATE(T, m);
               }
               /**
                * 图4类型，情况4的阶段2，执行以下操作：
                * 父节点颜色设为黑色
                * 祖父节点颜色设为红色
                * 对祖父节点左旋
                */

               f.setColor(NodeColor.Black);
               g.setColor(NodeColor.Red);
               LEFT_ROTATE(T, g);
            }
         }
      }
      // 根节点设为黑色
      T.getRoot().setColor(NodeColor.Black);
   }

   /**
    * 删除节点调整
    * @param T
    * @param x
    */
   public void RB_DELETE_FIXUP(RedBlackTree T, RedBlackTreeNode x){
      //临时结点
      RedBlackTreeNode w = null;
      //非根结点且为黑色
      while( x != T.getRoot() && x.getColor() == NodeColor.Black ){
         //x为父结点左孩子
         if( x == x.getParent().getLeft() ){
            //w为兄弟结点
            w = x.getParent().getRight();
            //case1：w兄弟结点为红色， w一定有两个子节点
            if( w.getColor() == NodeColor.Red ){
               //w设为黑色
               w.setColor(  NodeColor.Black );
               //被删结点的父结点设为黑色
               x.getParent().setColor( NodeColor.Red );
               //对x的父结点左旋
               LEFT_ROTATE(T, x.getParent());
               //更新x的兄弟结点
               w = x.getParent().getRight();
            }
            //case2：w兄弟结点和两个孩子结点都为黑
            if( w.getLeft().getColor() == NodeColor.Black && w.getRight().getColor() == NodeColor.Black ){
               //w设为黑色
               w.setColor(NodeColor.Red);
               //重设x为x的父结点
               x = x.getParent();
            }
            //case3：w兄弟结点为黑，w的左孩子为红，右孩子为黑
            else if( w.getRight().getColor() == NodeColor.Black ){
               //w的左孩子设为黑
               w.getLeft().setColor(NodeColor.Black);
               //w设为红
               w.setColor(NodeColor.Red);
               //右旋
               RIGHT_ROTATE(T, w);
               //更新w
               w = x.getParent().getRight();
            }
            //case4：w兄弟结点为黑，w的右孩子为红
            w.setColor(x.getParent().getColor());
            x.getParent().setColor(NodeColor.Black);
            w.getRight().setColor(NodeColor.Black);
            LEFT_ROTATE(T, x.getParent());
            x = T.getRoot();
         }
         //x为父结点右孩子
         else{
            w = x.getParent().getLeft();
            if( w.getColor() == NodeColor.Red ){
               w.setColor(  NodeColor.Black );
               x.getParent().setColor( NodeColor.Red );
               RIGHT_ROTATE(T, x.getParent());
               w = x.getParent().getLeft();
            }
            if( w.getRight().getColor() == NodeColor.Black && w.getLeft().getColor() == NodeColor.Black ){
               w.setColor(NodeColor.Red);
               x = x.getParent();
            }
            else if( w.getLeft().getColor() == NodeColor.Black ){
               w.getRight().setColor(NodeColor.Black);
               w.setColor(NodeColor.Red);
               LEFT_ROTATE(T, w);
               w = x.getParent().getLeft();
            }
            w.setColor(x.getParent().getColor());
            x.getParent().setColor(NodeColor.Black);
            w.getLeft().setColor(NodeColor.Black);
            RIGHT_ROTATE(T, x.getParent());
            x = T.getRoot();
         }
      }
      x.setColor(NodeColor.Black);
   }

   /**
    * 删除节点
    * @param T
    * @param d
    */

   public void RB_DELETE(RedBlackTree T, RedBlackTreeNode d) {
      RedBlackTreeNode nil = RedBlackTree.nil;
      RedBlackTreeNode y = null;

      if (IsNil(d.getRight()) == false && IsNil(d.getLeft()) == false) {
         /**
          * 情形1：有左右节点
          * 后继节点是右子树中最小的值
          * 找到后继节点，后继节点的值替换当前节点值，只替换值，不替换颜色
          * 并把后继节点设为待删除节点，继续下述判断
          * 注意：此时待删除节点变为了后继节点
          * 后继节点只有右子树
          *
          */
         y = TREE_MINIMUM(d.getRight());
         d.setKey(y.getKey());
         d = y;
      }
      if (IsNil(d.getLeft()) == true && IsNil(d.getRight()) == true) {
         /**
          * 情形2：没有左右节点
          */
         if (d.getParent() == null || IsNil(d.getParent())) {
            // 根节点 直接删除
            T.setRoot(nil);
         } else {
            // 非根节点
            if (d.getColor() == NodeColor.Red) {
               // 红色节点 直接删除
               if (d.getParent().getLeft() == d){
                  // d是左节点
                  d.getParent().setLeft(nil);
               } else {
                  // d是右节点
                  d.getParent().setRight(nil);
               }
            } else {
               // d 黑色节点
               // 父亲节点
               RedBlackTreeNode p = d.getParent();
               // 兄弟节点
               RedBlackTreeNode s = null;

               if (p.getLeft() == d) {
                  // d 是父节点左节点
                  s = p.getRight();
                  if (s.getColor() == NodeColor.Red) {
                     /**
                      * 情况2-1
                      * d 是父节点左节点，兄弟节点是红色
                      * p节点左旋，p节点变为红色，s节点变为黑色
                      * 变换后 d的兄弟节点肯定为黑色，变成情况2-3或情况2-5
                      */
                     RedBlackTreeNode sl = s.getLeft();
                     LEFT_ROTATE(T, p);
                     p.setColor(NodeColor.Red);
                     s.setColor(NodeColor.Black);
                     // d的兄弟节点变为之前兄弟节点的左节点，一定为黑色
                     s = d.getParent().getRight();
                  }
                  if (s.getLeft().getColor() == NodeColor.Red){
                     /**
                      * 情况2-3
                      * 兄弟节点为黑色，近侄子节点为红色
                      * 即兄弟节点右节点为黑色，左节点为红色
                      * s节点右旋， s和sl换色
                      * 变换前 s一定为黑色，sl一定为红色
                      * 变换后  s为红色，sl为黑色
                      * d的兄弟节点变为之前兄弟节点的左节点sl
                      * 变成情况2-5继续处理
                      */
                     RedBlackTreeNode sl = s.getLeft();
                     s.setColor(NodeColor.Red);
                     s.getLeft().setColor(NodeColor.Black);
                     s = sl;
                     RIGHT_ROTATE(T, s);
                  }
                  if (s.getRight().getColor() == NodeColor.Red){
                     /**
                      * 情况2-5
                      * 兄弟节点为黑色，远侄子为红色
                      * 即兄弟节点右节点为红色
                      * p右旋， s设为p的颜色，p设为黑色，sr设为黑色，删除d节点
                      */
                     LEFT_ROTATE(T, p);
                     s.setColor(p.getColor());
                     p.setColor(NodeColor.Black);
                     s.getRight().setColor(NodeColor.Black);
                     // 删除d，完成删除操作
                     d.getParent().setLeft(nil);
                     return;
                  }
                  /**
                   * 情况2-7右节点和右节点子节点均为黑色
                   */
                  if (p.getColor() == NodeColor.Red) {
                     /**
                      * 情况2-7-1
                      * 父节点为红色
                      * 将父节点变为黑色
                      * 兄弟节点变为红色
                      * 删除节点 结束
                      */
                     p.setColor(NodeColor.Black);
                     s.setColor(NodeColor.Red);
                     p.setLeft(nil);
                     return;
                  } else {
                     /**
                      * 情况2-7-2
                      * 父节点为黑色
                      * s类似新插入的节点
                      * 进行变色插入变色调整
                      * 删除 d节点
                      */
                     p.setLeft(nil);
                     s.setColor(NodeColor.Red);
                     RB_DELETE_FIXUP(T, p);
                     return;
                  }

               } else {
                  // d 是父节点右节点
                  s = p.getLeft();
                  if (s.getColor() == NodeColor.Red) {
                     /**
                      * 情况2-2
                      * d 是父节点右节点，兄弟节点是红色
                      * p节点右旋，p节点变为红色，s节点变为黑色
                      * 变换后 d的兄弟节点肯定为黑色，变成情况2-4或情况2-6
                      */
                     RIGHT_ROTATE(T, p);
                     p.setColor(NodeColor.Red);
                     s.setColor(NodeColor.Black);
                     // d的兄弟节点变为之前兄弟节点的右节点，一定为黑色
                     s = s.getRight();
                  }
                  if (s.getRight().getColor() == NodeColor.Red) {
                     /**
                      * 情况2-4
                      * d 是父节点右节点， 兄弟节点为黑色，近侄子节点为红色，?远侄子节点为黑色
                      * s 节点左旋，s和sr换色
                      * 变色前 s 一定为黑色，sr一定为红色
                      * 变色后 s 一定为红色，sr一定为黑色
                      * d的兄弟节点变为之前兄弟节点的右节点
                      * 变成情况2-6继续处理
                      */
                     RedBlackTreeNode sr = s.getRight();
                     s.setColor(NodeColor.Red);
                     s.getRight().setColor(NodeColor.Black);
                     LEFT_ROTATE(T, s);
                     s = sr;
                  }
                  if (s.getLeft().getColor() == NodeColor.Red) {
                     /**
                      * 情况2-6
                      * 兄弟节点为黑色，且远侄子为红色
                      * 即兄弟节点左节点为红色
                      * s设为p颜色，p设为黑色，sl设为黑色，删除节点d
                      */
                     LEFT_ROTATE(T, p);
                     s.setColor(p.getColor());
                     p.setColor(NodeColor.Black);
                     s.getLeft().setColor(NodeColor.Black);
                     // 删除d，完成删除操作
                     d.getParent().setRight(nil);
                     return;
                  }

                  /**
                   * 情况2-8 兄弟节点和兄弟节点子节点均为黑色
                   */
                  if (p.getColor() == NodeColor.Red) {
                     /**
                      * 情况2-8-1
                      * 父节点为红色
                      * 将父节点变为黑色
                      * 兄弟节点变为红色
                      * 删除节点 结束
                      */
                     p.setColor(NodeColor.Black);
                     s.setColor(NodeColor.Red);
                     p.setRight(nil);
                     return;
                  } else {
                     /**
                      * 情况2-8-2
                      * 父节点为黑色
                      * s类似新插入的节点
                      * 进行变色插入变色调整 ？？？？？？
                      * 删除 d节点
                      */
                     p.setRight(nil);
                     s.setColor(NodeColor.Red);
                     RB_DELETE_FIXUP(T, p);
                     return;
                  }
               }
            }
         }
      }
      /**
       * 情形3：d节点只有一个左节点或者一个右节点
       * 此时d节点一定是黑色，左节点或者右节点为空，其他情况不会出现
       * 左子树或者右子树根节点为红色
       * 这种情况下的处理方式就是
       * 用存在的左子树或者右子树替换d节点
       */
      if (IsNil(d.getLeft()) == false) {
         // 左子树存在
         RedBlackTreeNode dl = d.getLeft();
         dl.setColor(NodeColor.Black);
         RB_TRANSPLANT(T, d, dl);
      } else {
         // 右子树存在
         RedBlackTreeNode dr = d.getRight();
         dr.setColor(NodeColor.Black);
         RB_TRANSPLANT(T, d, dr);
      }
   }


   public void RB_DELETE2(RedBlackTree T, RedBlackTreeNode z) {
      RedBlackTreeNode y = null;
      String yOriginColor = z.getColor();
      RedBlackTreeNode x = null;
      // z 没有左节点
      if (IsNil(z.getLeft()) == true) {
         x = z.getRight();
         RB_TRANSPLANT(T, z, z.getRight());
      }
      // z 没有右节点
      else if (IsNil(z.getRight()) == true) {
         x = z.getLeft();
         RB_TRANSPLANT(T, z, z.getLeft());
      }
      // z 有左右节点
      else {
         // 找到z 右子树中最小的节点，做为z的后继
         y = TREE_MINIMUM(z.getRight());
         yOriginColor = y.getColor();
         // y可能有右孩子，一定无左孩子
         // 保存右孩子
         x = y.getRight();
         // 若y 为z的右节点，直接相连
         if (y.getParent() == z) {
            x.setParent(y);
         }
         // 不是右节点
         else {
            RB_TRANSPLANT(T, y, y.getRight());
            y.setRight(z.getRight());
            y.getRight().setParent(y);
         }
         RB_TRANSPLANT(T, z, y);
         y.setLeft(z.getLeft());
         y.getLeft().setParent(y);
         y.setColor(z.getColor());
      }
      if (yOriginColor == NodeColor.Black) {
         RB_DELETE_FIXUP(T, x);
      }
   }

   /**
    * 查找 x 左子树最小的节点
    * @param x
    * @return
    */
   public RedBlackTreeNode TREE_MINIMUM(RedBlackTreeNode x) {
      while (IsNil(x.getLeft()) == false) {
         x = x.getLeft();
      }
      return x;
   }

   /**
    * 节点替代
    * 把根节点为o的子树替换为根节点为v的子树
    *
    * o 为父节点的左子树:
    *      r          r
    *     /           /
    *    o            n
    *   / \          / \
    *  ol  or       ol  or
    *
    *  o为父节点的右子树:
    *    r           r
    *     \           \
    *      o           n
    *     / \          / \
    *    ol  or       ol  or
    *
    *
    * @param T
    * @param o 旧节点
    * @param n 新节点
    */
   public void RB_TRANSPLANT(RedBlackTree T, RedBlackTreeNode o, RedBlackTreeNode n) {
      // u为根节点
      if (IsNil(o.getParent())) {
         T.setRoot(o);
      }
      // o为左子树
      else if (o == o.getParent().getLeft()) {
         o.getParent().setLeft(n);
      }
      // o为右子树
      else {
         o.getParent().setRight(n);
      }
      // 父节点替换
      n.setParent(o.getParent());
   }

   /**
    *  左旋(对节点x进行左旋)：
    *      px                              px
    *     /                               /
    *    x                               y
    *   /  \       --(左旋)--            / \
    *  lx   y                          x  ry
    *     /   \                       /  \
    *    ly   ry                     lx  ly
    *
    */
   public void LEFT_ROTATE(RedBlackTree T, RedBlackTreeNode x){
      // 定义y节点
      RedBlackTreeNode y = x.getRight();
      // y 左节点 设为 x右节点
      x.setRight((y.getLeft()));
      // x 设为 y左节点父节点
      y.getLeft().setParent(x);
      // x父节点 设为y父节点
      y.setParent(x.getParent());
      // y 设为x父节点左/右节点或者根节点
      if (IsNil(x.getParent()) == true) {
         // x为根节点， y设为根节点
         T.setRoot(y);
      } else if (x.getParent().getLeft() == x) {
         // x为左节点，y设为左节点
         x.getParent().setLeft(y);
      } else {
         // x为右节点，y设为右节点
         x.getParent().setRight(y);
      }
      // x 设为 y左节点
      y.setLeft(x);
      // y 设为x父节点
      x.setParent(y);
   }

   /**
    * 右旋示意图(对节点x进行右旋)：
    *            px                               px
    *           /                                /
    *          x                                y
    *         /  \      --(右旋)--             /  \
    *        y   rx                          ly   x
    *       / \                                  / \
    *      ly  ry                               ry  rx
    */
   public void RIGHT_ROTATE(RedBlackTree T, RedBlackTreeNode x){
      // 左节点
      RedBlackTreeNode y = x.getLeft();
      // y的右节点 设为 x左节点
      x.setLeft(y.getRight());
      y.getRight().setParent(x);
      // x节点父节点设为 y父节点
      y.setParent(x.getParent());
      if (IsNil(y.getParent()) == true){
         T.setRoot(y);
      }
      else if (x.getParent().getRight() == x) {
         x.getParent().setRight(y);
      } else {
         x.getParent().setLeft(y);
      }
      // x设为y右节点
      y.setRight(x);
      // y设为x父节点
      x.setParent(y);
   }

   /**
    * 前序遍历
    * @param t
    */
   public void preorder(RedBlackTreeNode t) {
      if (IsNil(t) == false) {
         System.out.println(t.getKey() + t.getColor());
         preorder(t.getLeft());
         preorder(t.getRight());
      }
   }

   /**
    * 中序遍历
    * @param t
    */
   public void midorder(RedBlackTreeNode t) {
      if (IsNil(t) == false) {
         midorder(t.getLeft());
         System.out.println(t.getKey() + t.getColor());
         midorder(t.getRight());
      }
   }

   /**
    * 后序遍历
    * @param t
    */
   public void postorder(RedBlackTreeNode t) {
      if (IsNil(t) == false) {
         postorder(t.getLeft());
         postorder(t.getRight());
         System.out.println(t.getKey());
      }
   }
}