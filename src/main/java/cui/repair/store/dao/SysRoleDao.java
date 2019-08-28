package cui.repair.store.dao;

import cui.repair.store.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 付军
 * @version 1.0
 * @date 2019-08-07
 */
@Mapper
public interface SysRoleDao {
    SysRole findByRoleId(String id)throws Exception;
    List<SysRole> selectAll() throws Exception;
    void saveRole(SysRole sysRole)throws Exception;
    void updateRole(SysRole sysRole)throws Exception;
    void deleteRole(String id)throws Exception;
}
