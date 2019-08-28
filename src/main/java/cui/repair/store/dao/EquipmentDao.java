package cui.repair.store.dao;


import cui.repair.store.entity.Equipment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author 付军
 * @date 2019-08-09 02:10:59
 */
@Mapper
public interface EquipmentDao {
    int insert(Equipment equipment) throws Exception;
    int update(Equipment equipment) throws Exception;
    int delete(int id) throws Exception;
    Equipment selectOne(int id) throws Exception;
    List<Equipment> selectAll() throws Exception;
    List<Equipment> getEquipmentLikeByName(String name) throws Exception;
}
