package cui.repair.store.controller;


import cui.repair.store.entity.Equipment;
import cui.repair.store.service.EquipmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;


    @GetMapping(value="/equipments")
    public String list(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                       @RequestParam(value = "pageSize", defaultValue = "5") int pageSize, Map map){
        //将查询的结果放置到请求域中
        map.put("equipmentList",equipmentService.findItemByPage(currentPage,pageSize));
        return "equipment/list";
    }



    /**
     * 列表
     */
    @RequestMapping("/elist")
    @ResponseBody
    public List<Equipment> list(@RequestParam Map<String, Object> params){
        return equipmentService.selectAll();
    }


    /**
     * 信息
     */
    @RequestMapping("/einfo/{id}")
    public String  info(@PathVariable("id") Integer id,Map map) {
        Equipment equipment = equipmentService.getById(id);
        System.out.println(equipment);
        map.put("equipment",equipment);
        return "equipment/edit";
    }
    /**
     * 保存
     */
    @RequestMapping("/esave")
    public String save( Equipment repair){
		int a=equipmentService.save(repair);

        return "redirect:/equipments";
    }

    /**
     * 修改
     */
    @RequestMapping("/eupdate")
    public String update( Equipment repair){

         equipmentService.updateById(repair);
        return "redirect:/equipments";
    }

    /**
     * 删除
     */
    @RequestMapping("/edelete/{id}")
    public String delete(@PathVariable("id") Integer id){
        equipmentService.delete(id);
        return "redirect:/equipments";
    }






}
