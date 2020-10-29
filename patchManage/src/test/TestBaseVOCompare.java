package test;

import psn.base.vo.BaseVO;
import psn.base.vo.DataBaseVO;

public class TestBaseVOCompare {
    public static void main(String[] args) {
        System.out.println(getObj().compareWith(getObj1()));

    }
    static BaseVO getObj(){
        DataBaseVO dataBaseVO=new DataBaseVO();
        dataBaseVO.setInstance("orcl");
        dataBaseVO.setIp("127.0.0.1");
        dataBaseVO.setPort("1521");
        dataBaseVO.setSourceName("test");
        dataBaseVO.setUser("hxgfnc");
        dataBaseVO.setPassword("1");
        dataBaseVO.setPk_dataBase("pk");
        return dataBaseVO;
    }
    static BaseVO getObj1(){
        DataBaseVO dataBaseVO=new DataBaseVO();
        dataBaseVO.setInstance("orcl");
        dataBaseVO.setIp("127.0.0.1");
        dataBaseVO.setPort("1521");
        dataBaseVO.setSourceName("test");
        dataBaseVO.setUser("hxgfnc");
        dataBaseVO.setPassword("1");
        dataBaseVO.setPk_dataBase("pk");
        return dataBaseVO;
    }

}
