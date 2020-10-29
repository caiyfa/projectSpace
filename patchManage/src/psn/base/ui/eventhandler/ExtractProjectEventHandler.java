package psn.base.ui.eventhandler;


import org.apache.tools.zip.ZipFile;
import psn.base.dao.BaseDAO;
import psn.base.utils.EncodeUtil;
import psn.base.vo.PatchVO;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExtractProjectEventHandler extends ExtractDemandEventHandler {
    @Override
    public List<ZipFile> getCurrZipList() throws Exception {
        //获取所有补丁文件
        List<PatchVO> patchVOList= (List<PatchVO>) BaseDAO.getInstance().queryAllVO(PatchVO.class);
        //筛选补丁
        List<ZipFile> currDemandPatchs=new ArrayList<>();
        for(PatchVO patchVO:patchVOList){
            if(patchVO.getPk_project().equals(selectedProject.getPrimaryKey())){
                String filePath=patchVO.getPath();
                String fileEncode = EncodeUtil.getEncode(filePath,true);
                currDemandPatchs.add(new ZipFile(new File(filePath), fileEncode));
            }
        }
        return currDemandPatchs;
    }

    @Override
    public String getExtractFileName() {
        return selectedProject.getName()+
                new SimpleDateFormat("yyyyMMddhhmm").format(new Date())+"_"+"全量补丁";
    }
}
