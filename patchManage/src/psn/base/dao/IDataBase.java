package psn.base.dao;

import psn.base.exception.RunException;
import psn.base.vo.BaseVO;

import java.util.List;

public interface IDataBase {
  List<? extends BaseVO> query(String sql, Class<? extends BaseVO> voClass) throws RunException;
}
