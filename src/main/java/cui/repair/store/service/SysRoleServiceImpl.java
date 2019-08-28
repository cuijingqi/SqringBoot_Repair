package cui.repair.store.service;

import cui.repair.store.dao.SysRoleDao;
import cui.repair.store.entity.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 付军
 * @version 1.0
 * @date 2019/8/18
 * @description 角色的实现类
 */
@Service
public class SysRoleServiceImpl  implements SysRoleService{
    @Autowired
    private SysRoleDao sysRoleDao;

    @Override
    public SysRole findByRoleId(String id) {
        try {
            return sysRoleDao.findByRoleId(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<SysRole> selectAll() {
        try {
            return sysRoleDao.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void saveRole(SysRole sysRole) {
        try {
            sysRoleDao.saveRole(sysRole);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRole(SysRole sysRole) {
        try {
            sysRoleDao.updateRole(sysRole);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRole(String id) {
        try {
            sysRoleDao.deleteRole(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
