package cui.repair.store.dao;

import cui.repair.store.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author 付军
 * @version 1.0
 * @date 2019-08-07
 */
@Mapper
public interface SysUserDao {
    SysUser findByUserCode(String userCode);
    List<SysUser> selectAll() throws Exception;
    void saveUser(SysUser  sysUser)throws Exception;
    void updateUser(SysUser  sysUser)throws Exception;
    void deleteUser(String  id)throws Exception;
    void updateUserRole(Map<String,String> map)throws Exception;
    void deleteUserRole(String id)throws Exception;
    SysUser querUserById(String id)throws Exception;
    List<SysUser> selectAllByRoleId() throws Exception;

}
