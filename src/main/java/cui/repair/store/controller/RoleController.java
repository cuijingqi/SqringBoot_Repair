package cui.repair.store.controller;

import cui.repair.store.entity.SysRole;
import cui.repair.store.service.SysRoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author 付军
 * @version 1.0
 * @date 2019/8/18
 * @description 角色的控制类
 */
@Controller
public class RoleController {

    private SysRoleService sysRoleService;

    @RequestMapping("/roles")
    public List<SysRole> getAllRole(){
        return sysRoleService.selectAll();
    }
}
