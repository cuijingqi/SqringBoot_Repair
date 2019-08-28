package cui.repair.store.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author 付军
 * @version 1.0
 * @date 2019/8/12
 * @description 设备的实体类
 */
@Data
@ToString
public class Equipment {


    private Integer id;
    private Integer equipment_id;
    private String  equipment_name;
    private String  equipment_type;
    //@JsonFormat(pattern = "yyyy/MM/dd", timezone ="GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date    createtime;
    private String  responsible_person;
    private String  remark;
}
