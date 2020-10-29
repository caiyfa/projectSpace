package psn.cyf.freemarker;



import psn.cyf.base.database.vo.DataBaseVO;

import java.util.ArrayList;
import java.util.List;

public class LogVO {
    List<ContentVO> contentVOS;

    public List<ContentVO> getContentVOS() {

        return contentVOS==null?contentVOS=new ArrayList<>():contentVOS;
    }

    public void setContentVOS(List<ContentVO> contentVOS) {
        this.contentVOS = contentVOS;
    }
    ContentVO popContent=null;
    public ContentVO getContentWithDbVO(DataBaseVO dataBaseVO){

        popContent=null;
        if(dataBaseVO==null ){
            return null;
        }

        getContentVOS().stream().forEach((contentVO)->{
             if(dataBaseVO.equals(contentVO.getDbInfo())){
                 popContent=contentVO;
             }
        });
        return popContent;
    }


}
