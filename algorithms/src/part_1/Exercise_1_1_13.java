package part_1;

import java.util.Scanner;

/**
 * 编写一段代码打印出一列M行N列二维数组的转置（行列互换）
 */
public class Exercise_1_1_13 {
    static  Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println();

    }
    public int[][] initDimensionalArray(){
        return null;
    }
    public static int getIntValue(){
        String reg="^[0-9]*$";
        String inputValue=scanner.nextLine();
        if(inputValue.matches(reg)){
            return new Integer(inputValue).intValue();
        }   else{
            System.out.println("请输入正整数！");
         return getIntValue();
        }
    }
}
