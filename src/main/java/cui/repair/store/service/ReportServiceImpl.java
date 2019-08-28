package cui.repair.store.service;

import cui.repair.store.dao.ReportDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author 付军
 * @version 1.0
 * @date 2019/8/15
 * @description 报表的服务类
 */
@Service
public class ReportServiceImpl implements ReportService{
    @Autowired
    private ReportDao reportDao;
    @Override
    public List<Map> queryEquipment() throws Exception {
        return reportDao.queryEquipment();
    }
}
