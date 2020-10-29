package test;

import java.io.IOException;

public class TestCmdCode {
    public static void main(String[] args) throws IOException {
        Runtime.getRuntime().exec(" cmd  /c start notepad++   D:\\各种账号密码\\各种账号密码.txt");
    }
}
