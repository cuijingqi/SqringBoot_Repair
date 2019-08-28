package cui.repair.store.service;

import com.github.pagehelper.PageInfo;
import cui.repair.store.entity.RepairEntity;

import java.util.List;

/**
 * 
 *
 * @author 付军
 * @date 2019-08-09 02:10:59
 */
public interface RepairService  {
    RepairEntity getById(Integer id);
    int save(RepairEntity repair);
    int updateById(RepairEntity repair);
    int delete(int id) ;
    List<RepairEntity> selectAll() ;
    PageInfo<RepairEntity> findItemByPage(int currentPage, int pageSize);
    PageInfo<RepairEntity> findItemByName(int currentPage, int pageSize,RepairEntity repair);
}

