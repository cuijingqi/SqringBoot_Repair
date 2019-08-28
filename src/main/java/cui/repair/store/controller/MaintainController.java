package cui.repair.store.controller;

import cui.repair.store.entity.LoginUserInfo;
import cui.repair.store.entity.RepairEntity;
import cui.repair.store.service.RepairService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 
 *
 * @author 付军
 * @date 2019-08-09 02:10:59
 */
@Slf4j
@Controller
public class MaintainController {
    @Autowired
    private RepairService repairService;
    @GetMapping(value="/maintains")
    public String list(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                       @RequestParam(value = "pageSize", defaultValue = "5") int pageSize, Map map, HttpServletRequest req){
//        LoginUserInfo user= (LoginUserInfo) req.getSession().getAttribute("loginUserInfo");

        //从shiro的session中取activeUser
        Subject subject = SecurityUtils.getSubject();
        //取身份信息
        LoginUserInfo loginUserInfo = (LoginUserInfo) subject.getPrincipal();

        System.out.println(loginUserInfo);
        RepairEntity repairEntity =new RepairEntity();
        repairEntity.setAssignid(loginUserInfo.getUserid());
        map.put("repairsList",repairService.findItemByName(currentPage,pageSize,repairEntity));
        return "maintain/list";
    }



    /**
     * 列表
     */
    @RequestMapping("/mlist")
    public List<RepairEntity> list(@RequestParam Map<String, Object> params){
        return repairService.selectAll();
    }


    /**
     * 信息
     */
    @RequestMapping("/minfo/{id}")
    public String  info(@PathVariable("id") Integer id,Map map) {
        RepairEntity repair = repairService.getById(id);
        map.put("repair",repair);
        return "maintain/edit";

    }
    /**
     * 信息
     */
    @RequestMapping("/showminfo/{id}")
    public String  showinfo(@PathVariable("id") Integer id,Map map) {
        RepairEntity repair = repairService.getById(id);

        map.put("repair",repair);
        return "maintain/show";

    }


    /**
     * 修改
     */
    @RequestMapping("/mupdate")
    public String update(RepairEntity repair){
        Subject subject = SecurityUtils.getSubject();
        //取身份信息
        LoginUserInfo loginUserInfo = (LoginUserInfo) subject.getPrincipal();
        repair.setAssignid(loginUserInfo.getUserid());
        repair.setEndtime(new Date());
        repair.setStatus(2);
        repairService.updateById(repair);
        return "redirect:/maintains";
    }

    /**
     * 删除
     */
    @RequestMapping("/mdelete")
    public String delete(@RequestBody Integer id){
        return repairService.delete(id)+"";
    }

}
