package part_1;

public class Exercise_1_1_12 {
    public static void main(String[] args) {
        int[] a=new int[10];
        int[] b=new int[10];
        for(int i=0;i<10;i++)
            a[i]=9-i;//初始化的是9-0倒序数组
        System.out.print("初始化数组：");    printArr(a);
        for(int i=0;i<10;i++)
            a[i]=a[a[i]];//后半部分将前半部分覆盖

    }
    private static void printArr(int [] src){
        for(int i:src){
            System.out.print(i+" 、");
        }
        System.out.println();
    }
}
