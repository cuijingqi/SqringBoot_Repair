package cui.repair.store.service;

import com.github.pagehelper.PageInfo;
import cui.repair.store.entity.Building;

import java.util.List;

/**
 * 
 *
 * @author 付军
 * @date 2019-08-09 02:10:59
 */
public interface BuildingService {
    Building getById(Integer id);
    int save(Building building);
    int updateById(Building building);
    int delete(int id) ;
    List<Building> selectAll() ;
    PageInfo<Building> findItemByPage(int currentPage, int pageSize);
}

