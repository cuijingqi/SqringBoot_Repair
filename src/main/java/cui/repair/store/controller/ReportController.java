package cui.repair.store.controller;

import cui.repair.store.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author 付军
 * @version 1.0
 * @date 2019/8/15
 * @description 报表的控制器
 */
@RestController
public class ReportController {
    @Autowired
    private ReportService reportService;
    /**
     * 报表专用，每个设备的维修次数
     */
    @RequestMapping("/ereport")
    public List<Map> ereport(){
        try {
            return reportService.queryEquipment();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
