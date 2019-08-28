package cui.repair.store.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
/**
 *@author 付军
 *@date  2019-08-07
 *@version 1.0
 */
@Data
@ToString
@NoArgsConstructor
public class SysPermission  implements Serializable {
    private Long id;
    //权限名称
    private String name;
    //权限分类
    private String type;
    //权限url
    private String url;
    //权限标识符
    private String percode;
    //权限的父节点id
    private Long parentid;
    private String parentids;
    private String sortstring;
    private String available;

}