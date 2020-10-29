package psn.cyf.utils;

import psn.cyf.exception.RunException;

import java.io.IOException;

public class TestJson {
    public static void main(String[] args) throws IOException, InterruptedException, RunException {

        System.out.println(true^false);
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
       /* JSONObject jsonObject=new JSONObject();
        jsonObject.put("name","testName");
        PatchVO patchVO1= (PatchVO) BaseDAO.getInstance().transferJsonToVO(PatchVO.class,jsonObject);
        System.out.println(patchVO1.getName());*/
    }
}
