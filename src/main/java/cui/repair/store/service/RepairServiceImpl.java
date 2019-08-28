package cui.repair.store.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cui.repair.store.dao.RepairDao;
import cui.repair.store.entity.RepairEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("repairService")
public class RepairServiceImpl  implements RepairService {

    @Autowired
    private RepairDao repairDao;

    @Override
    public RepairEntity getById(Integer id) {
        try {
            return repairDao.selectOne(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int save(RepairEntity repair) {
        try {
            return repairDao.insert(repair);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateById(RepairEntity repair) {
        try {
            return repairDao.update(repair);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(int id) {
        try {
            return repairDao.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<RepairEntity> selectAll() {
        try {
            return repairDao.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PageInfo<RepairEntity> findItemByPage(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<RepairEntity> list = null;
        try {
            list = repairDao.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PageInfo<RepairEntity> buildingList = new PageInfo<>(list);
        return buildingList;
    }

    @Override
    public PageInfo<RepairEntity> findItemByName(int currentPage, int pageSize, RepairEntity repair) {
        PageHelper.startPage(currentPage, pageSize);
        List<RepairEntity> list = null;
        try {
            list = repairDao.selectAllByName(repair);
        } catch (Exception e) {
            e.printStackTrace();
        }
        PageInfo<RepairEntity> buildingList = new PageInfo<>(list);
        return buildingList;
    }
}