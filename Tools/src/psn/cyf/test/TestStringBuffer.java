package psn.cyf.test;

public class TestStringBuffer {
    public static void main(String[] args) {
        StringBuffer stringBuffer=new StringBuffer("123456789");
        stringBuffer.setLength(5);

        System.out.println(stringBuffer.toString());
    }
}
