package cui.repair.store.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cui.repair.store.dao.BuildingDao;
import cui.repair.store.entity.Building;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 付军
 * @version 1.0
 * @date 2019/8/13
 * @description 位置类服务层
 */
@Service("buildingService")
public class BuildingServiceImpl implements BuildingService{
    @Autowired
    private BuildingDao buildingDao;
    @Override
    public Building getById(Integer id) {
        try {
            return buildingDao.selectOne(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int save(Building building) {
        try {
            return buildingDao.insert(building);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateById(Building building) {
        try {
            return buildingDao.update(building);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(int id) {
        try {
            return buildingDao.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Building> selectAll() {
        try {
            return buildingDao.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PageInfo<Building> findItemByPage(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<Building> list = null;
        try {
            list = buildingDao.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PageInfo<Building> buildingList = new PageInfo<>(list);
        return buildingList;
    }
}
