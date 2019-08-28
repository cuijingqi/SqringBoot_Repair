package cui.repair.store.controller;


import cui.repair.store.entity.Building;
import cui.repair.store.service.BuildingService;
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
public class BuildingController {
    @Autowired
    private BuildingService buildingService;


    @GetMapping(value="/buildings")
    //@RequiresPermissions(value={"emp:query"})
    public String list(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                       @RequestParam(value = "pageSize", defaultValue = "5") int pageSize, Map map){
        //将查询的结果放置到请求域中
        map.put("buildingList",buildingService.findItemByPage(currentPage,pageSize));
        return "building/list";
    }



    /**
     * 列表
     */
    @RequestMapping("/blist")
    @ResponseBody
    public List<Building> list(@RequestParam Map<String, Object> params){
        return buildingService.selectAll();
    }


    /**
     * 信息
     */
    @RequestMapping("/binfo/{id}")
    public String  info(@PathVariable("id") Integer id,Map map) {
        Building building = buildingService.getById(id);
        map.put("building",building);
        return "building/edit";
    }
    /**
     * 保存
     */
    @RequestMapping("/bsave")
    public String save( Building building){
		int a=buildingService.save(building);
        return "redirect:/buildings";
    }

    /**
     * 修改
     */
    @RequestMapping("/bupdate")
    public String update( Building repair){
		 buildingService.updateById(repair);
        return "redirect:/buildings";
    }

    /**
     * 删除
     */
    @RequestMapping("/bdelete/{id}")
    public String delete(@PathVariable("id") Integer id){
         buildingService.delete(id);
        return "redirect:/buildings";
    }

}
