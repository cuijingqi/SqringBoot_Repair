package cui.repair.store.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cui.repair.store.dao.SysPermissionDao;
import cui.repair.store.dao.SysUserDao;
import cui.repair.store.entity.LoginUserInfo;
import cui.repair.store.entity.SysPermission;
import cui.repair.store.entity.SysUser;
import cui.repair.store.exception.CustomException;
import cui.repair.store.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SysServiceImpl implements SysService {
    @Autowired
    SysUserDao sysUserDao;
    @Autowired
    SysPermissionDao sysPermissionDao;
    /**
     * 用户认证过程：
     * 1：先在数据库中根据UserCode查询用户信息
     * 2：拿数据库的密码和输入的密码做校验，一致则用户认证通过
     * @param sysUser
     * @return
     * @throws Exception
     */
    @Override
    public LoginUserInfo authenticate(SysUser sysUser) throws Exception {
        SysUser sysUserDB = sysUserDao.findByUserCode(sysUser.getUsercode());
        if(sysUserDB == null){
            throw new CustomException("用户账号不存在");
        }
        //数据库存储的密码
        String userpwdDB = sysUserDB.getUserpwd();
        //输入的密码
        String userpwd = sysUser.getUserpwd();
        //md5加密后的密码
        String userpwdMD5 = new MD5().getMD5ofStr(userpwd);
        if(!userpwdDB.equalsIgnoreCase(userpwdMD5)){
            throw new CustomException("用户或密码错误！");
        }

       //认证通过后，构建LoginUserInfo对象来承载用户信息和相关的权限清单
        LoginUserInfo loginUserInfo = new LoginUserInfo();
        loginUserInfo.setUserid(sysUserDB.getId());
        loginUserInfo.setUsercode(sysUserDB.getUsercode());
        loginUserInfo.setUsername(sysUserDB.getUsername());
        List<SysPermission> menusList = sysPermissionDao.findMenusByUserId(sysUserDB.getId());
        List<SysPermission> permissionsList = sysPermissionDao.findPermissionsByUserId(sysUserDB.getId());
        loginUserInfo.setMenus(menusList);
        loginUserInfo.setPermissions(permissionsList);
        return loginUserInfo;
    }
    @Override
    public SysUser findByUserCode(String userCode) throws Exception {
        SysUser sysUser = sysUserDao.findByUserCode(userCode);
        return sysUser;
    }

    @Override
    public List<SysPermission> findMenusByUserId(String userid) throws Exception {
        List<SysPermission> menusList = sysPermissionDao.findMenusByUserId(userid);
        return menusList;
    }

    @Override
    public List<SysPermission> findPermissionsByUserId(String userid) throws Exception {
        List<SysPermission> permissionsList = sysPermissionDao.findPermissionsByUserId(userid);
        return permissionsList;
    }

    @Override
    public List<SysUser> selectAll()  {
        try {
            return sysUserDao.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<SysUser>();
        }
    }

    @Override
    public void saveUser(SysUser sysUser)  {
        try {
            sysUserDao.saveUser(sysUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(SysUser sysUser) {
        try {
            sysUserDao.updateUser(sysUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(String id)  {
        try {
            sysUserDao.deleteUser(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public PageInfo<SysUser> findItemByPage(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<SysUser> allEmployees = null;
        try {
            allEmployees = sysUserDao.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PageInfo<SysUser> pageInfo = new PageInfo<>(allEmployees);
        return pageInfo;
    }

    @Override
    public void updateUserRole(Map<String, String> map) {
        try {
            sysUserDao.updateUserRole(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUserRole(String id) {
        try {
            sysUserDao.deleteUserRole(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public SysUser querUserById(String id){

        try {
            return sysUserDao.querUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<SysUser> selectAllByRoleId() {
        try {
            return sysUserDao.selectAllByRoleId();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
