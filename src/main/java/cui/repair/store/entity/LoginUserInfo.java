package cui.repair.store.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 承载用户登陆后的用户基本信息以及权限列表信息（包括菜单权限和操作权限）
 */
@Data
@ToString
@NoArgsConstructor
public class LoginUserInfo implements Serializable {

    private String userid;//用户id（主键）
    private String usercode;// 用户账号
    private String username;// 用户名称

    private List<SysPermission> menus;// 菜单
    private List<SysPermission> permissions;// 权限

    public LoginUserInfo(String usercode, String username) {
        this.usercode = usercode;
        this.username = username;
    }
}
