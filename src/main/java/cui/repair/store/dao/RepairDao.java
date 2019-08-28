package cui.repair.store.dao;


import cui.repair.store.entity.RepairEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author jerrychen
 * @email chensss2008@gmail.com
 * @date 2019-08-09 02:10:59
 */
@Mapper
public interface RepairDao  {
    int insert(RepairEntity repairEntity) throws Exception;
    int update(RepairEntity repairEntity) throws Exception;
    int delete(int id) throws Exception;
    RepairEntity selectOne(int id) throws Exception;
    List<RepairEntity> selectAllByName(RepairEntity repairEntity) throws Exception;
    List<RepairEntity> selectAll() throws Exception;
    List<RepairEntity> getRepairLikeByName(String name) throws Exception;
}
