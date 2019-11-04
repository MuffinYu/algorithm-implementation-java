package StringArthmetic;

import java.util.LinkedList;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * @Author kosong.yu
 * @Date 2019-10-25
 * @Description 四则运算字符串计算
 */
public class StringArthmetic {
  /**
   * 运算符号
   */
  public static String[] operations = {"+", "-", "*", "/"};

  public static String[] brackets = {"(", ")", "[", "]", "{", "}"};

  private static Pattern numberReg = Pattern.compile("[0-9.]");

  public static double compute(String input) {

    Stack<String> numStack = new Stack<String>();
    Stack<String> opeStack = new Stack<String>();
    String[] inputArr = input.replaceAll("\\s+", "").split("");
    int len = inputArr.length;
    for (int i = 0; i < len; i ++ ) {
      String str = inputArr[i];
      if (str.equals("+") || str.equals("-")) {
        opeStack.push(str);
      } else { // 数字
        if (i == 0) {
          numStack.add(str);
        } else if (numberReg.matcher(inputArr[i-1]).matches()){
          String newNum = numStack.peek() + str;
          numStack.setElementAt(newNum, 0);
        } else {
          numStack.push(str);
        }
      }
    }
    double num1 = 0;
    double num2 = 0;
    System.out.println(opeStack.size());
    System.out.println(opeStack.isEmpty());

    while (!opeStack.isEmpty()) {
      char ope = opeStack.pop().charAt(0);
      num1 = Double.valueOf( numStack.pop());
      num2 = Double.valueOf(numStack.pop());
      switch (ope) {
        case '+':
          numStack.push(Double.toString(num1 + num2));
          break;
        case '-':
          numStack.push(Double.toString(num2 - num1));
          break;
        default:
      }
    }
    return Double.valueOf(numStack.pop());
  }
}