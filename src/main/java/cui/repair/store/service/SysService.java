package cui.repair.store.service;


import com.github.pagehelper.PageInfo;
import cui.repair.store.entity.LoginUserInfo;
import cui.repair.store.entity.SysPermission;
import cui.repair.store.entity.SysUser;

import java.util.List;
import java.util.Map;

public interface SysService {
    //用户认证
    LoginUserInfo authenticate(SysUser sysUser) throws Exception;

    SysUser findByUserCode(String userCode) throws Exception;

    List<SysPermission> findMenusByUserId(String userid) throws Exception;
    List<SysPermission> findPermissionsByUserId(String userid) throws Exception;
    List<SysUser> selectAll() ;

    void saveUser(SysUser  sysUser);
    void updateUser(SysUser  sysUser);
    void deleteUser(String  id);
    PageInfo<SysUser> findItemByPage(int currentPage, int pageSize);
    void updateUserRole(Map<String,String> map);
    void deleteUserRole(String id);
    SysUser querUserById(String id);
    List<SysUser> selectAllByRoleId();
}
