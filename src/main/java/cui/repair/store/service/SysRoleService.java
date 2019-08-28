package cui.repair.store.service;


import cui.repair.store.entity.SysRole;

import java.util.List;

public interface SysRoleService {
    SysRole findByRoleId(String id);
    List<SysRole> selectAll();
    void saveRole(SysRole sysRole);
    void updateRole(SysRole sysRole);
    void deleteRole(String id);
}
