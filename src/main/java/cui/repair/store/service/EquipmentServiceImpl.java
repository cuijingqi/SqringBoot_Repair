package cui.repair.store.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cui.repair.store.dao.EquipmentDao;
import cui.repair.store.entity.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("equipmentService")
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private EquipmentDao equipmentDao;

    @Override
    public Equipment getById(Integer id) {
        try {
            return equipmentDao.selectOne(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int save(Equipment repair) {
        try {
            return equipmentDao.insert(repair);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateById(Equipment repair) {
        try {
            return equipmentDao.update(repair);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(int id) {
        try {
            return equipmentDao.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Equipment> selectAll() {
        try {
            return equipmentDao.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PageInfo<Equipment> findItemByPage(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<Equipment> list = null;
        try {
            list = equipmentDao.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PageInfo<Equipment> equipmentList = new PageInfo<>(list);
        return equipmentList;
    }
}