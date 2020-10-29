package test;

import java.util.Vector;

public class TestVector {
    public static void main(String[] args) {
        Vector<String> vector=new Vector<>();
        vector.add("test");
        System.out.println(vector.size());
        System.out.println(vector.elementAt(vector.size()-1));
    }
}
