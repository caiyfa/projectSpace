package psn.base.dao;

import psn.base.exception.RunException;
import psn.base.vo.BaseVO;

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
     * 删除
     * @param cls
     * @param pk
     * @return
     * @throws RunException
     */
    int deleteVOByPK(Class<? extends BaseVO> cls, String pk) throws RunException;

    /**
     * 查询
     * @param cls
     * @return
     * @throws RunException
     */
    List<?> queryAllVO(Class<? extends BaseVO> cls) throws RunException;

    /**
     * 更新
     * @param baseVO
     * @return
     * @throws RunException
     */
    int updateVO(BaseVO baseVO) throws RunException;
    BaseVO queryVOByPrimaryKey(Class<? extends BaseVO> cls,String key) throws RunException;
}
