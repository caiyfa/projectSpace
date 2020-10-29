package psn.base.utils;

import org.json.JSONObject;
import psn.base.dao.BaseDAO;
import psn.base.exception.RunException;
import psn.base.vo.DataBaseVO;
import psn.base.vo.PatchVO;

import java.io.IOException;
import java.lang.InterruptedException;
import java.lang.String;
import java.lang.System;
import java.util.List;

public class TestJson {
    public static void main(String[] args) throws IOException, InterruptedException, RunException {

      //  System.out.println(true^false);
       /* PatchVO patchVO=new    PatchVO();
        patchVO.setFieldValue("name",88888);
        patchVO.setVersion(2.1);
        patchVO.setPath("d:/temp");
        patchVO.setPk_project("project456");
        patchVO.setPk_patch("30428690bb064f8982b2d57ece367627");
       int res= BaseDAO.getInstance().updateVO(patchVO);
        System.out.println(res);*/
//       List<PatchVO> patchVOs= (List<PatchVO>) BaseDAO.getInstance().queryAllVO(PatchVO.class);
//        System.out.println(patchVOs.get(0).getPk_patch()==null);
//        int res=BaseDAO.getInstance().deleteVOByPK(PatchVO.class,"30428690bb064f8982b2d57ece367627");
//        System.out.println(res);

        List<DataBaseVO> dataBaseVOS= (List<DataBaseVO>) BaseDAO.getInstance().queryAllVO(DataBaseVO.class);

        System.out.println(  BaseDAO.getInstance().transferVOListToJsonArr(dataBaseVOS).toString());
    }
}
