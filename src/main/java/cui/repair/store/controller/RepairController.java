package cui.repair.store.controller;

import java.io.File;
import java.io.IOException;

import java.util.Date;
import java.util.List;
import java.util.Map;


import cui.repair.store.entity.RepairEntity;
import cui.repair.store.service.RepairService;
import cui.repair.store.util.ImgNameUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * 
 *
 * @author 付军
 * @date 2019-08-09 02:10:59
 */
@Slf4j
@Controller
public class RepairController {
    @Autowired
    private RepairService repairService;


    @GetMapping(value="/repairs")
    public String list(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                       @RequestParam(value = "pageSize", defaultValue = "5") int pageSize, Map map){
        //将查询的结果放置到请求域中
        map.put("repairsList",repairService.findItemByPage(currentPage,pageSize));
        return "repair/list";
    }


    @RequestMapping(value="/queryAllByParmar")
    public String allList(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                          @RequestParam(value = "pageSize", defaultValue = "5") int pageSize, Map map, RepairEntity repairEntity){
        //将查询的结果放置到请求域中
        map.put("repairsList",repairService.findItemByName(currentPage,pageSize,repairEntity));
        return "repair/list";
    }
    /**
     * 列表
     */
    @RequestMapping("/list")
    public List<RepairEntity> list(@RequestParam Map<String, Object> params){
        return repairService.selectAll();
    }


    /**
     * 信息
     */
    @RequestMapping("/repairinfo/{id}")
    public RepairEntity  info(@PathVariable("id") Integer id) {
        RepairEntity repair = repairService.getById(id);
        return repair;
    }

    /**
     * 查看信息
     */
    @RequestMapping("/showrinfo/{id}")
    public String  showinfo(@PathVariable("id") Integer id,Map map) {
        RepairEntity repair = repairService.getById(id);
        map.put("repair",repair);
        return "repair/show";

    }




    /**
     * 保存
     */
    @RequestMapping("/repair")
    public String save(RepairEntity repair, @RequestParam("file") MultipartFile file){
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }

        String fileName = file.getOriginalFilename();
        fileName=ImgNameUtil.getImgName(fileName);
        String filePath = "c:/upload/";
        File destDir = new File(filePath);
        if(!destDir.exists()) destDir.mkdirs();
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            System.out.println( "上传成功");
        } catch (IOException e) {
            e.printStackTrace();
        }



        repair.setImgurl(fileName);
        repair.setStarttime(new Date());
        System.out.println(repair);
        repairService.save(repair);
        return "redirect:/repairs";
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public String update(@RequestBody RepairEntity repair){
		return repairService.updateById(repair)+"";
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public String delete(@RequestBody Integer id){
        return repairService.delete(id)+"";
    }

}
