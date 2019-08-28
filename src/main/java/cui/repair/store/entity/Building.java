package cui.repair.store.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 付军
 * @version 1.0
 * @date 2019/8/8
 * @description 教学楼实体类
 */
@Data
@ToString
@NoArgsConstructor
public class Building {
    private  Integer id;
    private  String  buildingName;
}
