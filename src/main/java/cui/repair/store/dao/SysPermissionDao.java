package cui.repair.store.dao;


import cui.repair.store.entity.SysPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysPermissionDao {

    //根据用户id查询该用户下的所有授权的菜单权限列表
    List<SysPermission> findMenusByUserId(String uid);

    //根据用户id查询该用户下的所有授权的功能权限列表
    List<SysPermission> findPermissionsByUserId(String uid);
}
