package psn.cyf.base.filebase;



import psn.cyf.base.vo.BaseVO;
import psn.cyf.exception.RunException;

import java.lang.Class;
import java.lang.String;
import java.util.List;

public interface IBaseDAO {

    /**
     * 新增
     * @param baseVO
     * @return
     * @throws RunException
     */
    BaseVO insertVO(BaseVO baseVO) throws RunException;
    /**
     * 查询
     * @param cls
     * @return
     * @throws RunException
     */
    List<?> queryAllVO(Class<? extends BaseVO> cls) throws RunException;
    /**
     * 删除
     * @param cls
     * @param pk
     * @return
     * @throws RunException
     */
    int deleteVOByPK(Class<? extends BaseVO> cls, String pk) throws RunException;

    int deleteVOS(List<? extends BaseVO> voList)throws RunException;



    /**
     * 更新
     * @param baseVO
     * @return
     * @throws RunException
     */
    int updateVO(BaseVO baseVO) throws RunException;
    BaseVO queryVOByPrimaryKey(Class<? extends BaseVO> cls,String key) throws RunException;

    void curringData(String fileName,String data)throws RunException;

    String getData(String fileName)throws  RunException;
}
