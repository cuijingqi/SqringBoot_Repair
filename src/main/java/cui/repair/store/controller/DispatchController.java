package cui.repair.store.controller;

import cui.repair.store.entity.RepairEntity;
import cui.repair.store.service.RepairService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * 
 *
 * @author 付军
 * @date 2019-08-09 02:10:59
 */
@Slf4j
@Controller
public class DispatchController {
    @Autowired
    private RepairService repairService;


    @GetMapping(value="/dispatchs")
    public String list(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                       @RequestParam(value = "pageSize", defaultValue = "5") int pageSize, Map map){
        RepairEntity repairEntity =new RepairEntity();
        repairEntity.setStatus(0);
        map.put("repairsList",repairService.findItemByName(currentPage,pageSize,repairEntity));
        return "dispatch/list";
    }





    /**
     * 信息
     */
    @RequestMapping("/disinfo/{id}")
    public String  info(@PathVariable("id") Integer id,Map map) {
        RepairEntity repair = repairService.getById(id);
        map.put("repair",repair);
        return "dispatch/edit";
    }


    /**
     * 修改
     */
    @RequestMapping("/disupdate")
    public String update(RepairEntity repair){
        repair.setStatus(1);
        repairService.updateById(repair);
        return "redirect:/dispatchs";
    }

}
