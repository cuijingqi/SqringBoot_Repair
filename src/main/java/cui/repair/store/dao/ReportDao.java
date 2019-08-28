package cui.repair.store.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author 付军
 * @version 1.0
 * @date 2019-08-07
 */
@Mapper
public interface ReportDao {
    List<Map> queryEquipment()throws Exception;
}
