package psn.cyf.base.database.itf;




import psn.cyf.base.vo.BaseVO;
import psn.cyf.exception.RunException;

import java.util.List;

public interface IDataBase {
  List<? extends BaseVO> query(String sql, Class<? extends BaseVO> voClass) throws RunException;
}
