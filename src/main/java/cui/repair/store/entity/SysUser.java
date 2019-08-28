package cui.repair.store.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 *@author 付军
 *@date  2019-08-07
 *@version 1.0
 */
@Data
@ToString
@NoArgsConstructor
public class SysUser  {
    private String id;
    private String usercode;
    private String username;
    private String userpwd;
    private String salt;
    private String locked;

    //关系属性
    private List<SysRole> sysRoles;
}
