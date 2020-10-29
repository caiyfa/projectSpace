package test;

import java.io.IOException;

public class TestCmdOpenBrowse {
    public static void main(String[] args) throws IOException {
        Runtime.getRuntime().exec("cmd /c start www.baidu.com" );//启用
    }
}
