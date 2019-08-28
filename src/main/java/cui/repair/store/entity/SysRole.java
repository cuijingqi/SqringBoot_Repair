package cui.repair.store.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 *@author 付军
 *@date  2019-08-07
 *@version 1.0
 */
@Data
@ToString
@NoArgsConstructor
public class SysRole {
    private String id;
    private String rolename;
    private String available;

}