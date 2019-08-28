package cui.repair.store.service;

import com.github.pagehelper.PageInfo;
import cui.repair.store.entity.Equipment;

import java.util.List;

/**
 * 
 *
 * @author 付军
 * @date 2019-08-09 02:10:59
 */
public interface EquipmentService {
    Equipment getById(Integer id);
    int save(Equipment equipment);
    int updateById(Equipment equipment);
    int delete(int id) ;
    List<Equipment> selectAll() ;
    PageInfo<Equipment> findItemByPage(int currentPage, int pageSize);
}

