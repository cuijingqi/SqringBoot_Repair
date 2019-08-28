package cui.repair.store.dao;


import cui.repair.store.entity.Building;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author 付军
 * @date 2019-08-09 02:10:59
 */
@Mapper
public interface BuildingDao {
    int insert(Building building) throws Exception;
    int update(Building building) throws Exception;
    int delete(int id) throws Exception;
    Building selectOne(int id) throws Exception;
    List<Building> selectAll() throws Exception;
    List<Building> getBuildingLikeByName(String name) throws Exception;
}
