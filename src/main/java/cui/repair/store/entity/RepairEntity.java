package cui.repair.store.entity;


import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.ToString;

/**
 * 
 * 
 * @author jerrychen
 * @email chensss2008@gmail.com
 * @date 2019-08-09 02:10:59
 */
@Data
@ToString
public class RepairEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

		private Integer id;
	/**
	 * 故障位置
	 */

		private Integer buildingid;
		private Building building;
	/**
	 * 提交用户id
	 */

		private String userid;
		private SysUser user;
	/**
	 * 故障描述
	 */

		private String content;
	/**
	 * 保修时间
	 */

		private Date starttime;
	/**
	 * 完成时间
	 */

		private Date endtime;
	/**
	 * 指派人员
	 */

		private String assignid;
		private SysUser assingname;
	/**
	 * 表单状态 0 保修 1 受理  2 完成
	 */

		private Integer status;
	/**
	 * 设备id
	 */

		private Integer equipmentid;
		private Equipment equipment;
	/**
	 * 图片地址
	 */

		private String imgurl;
	/**
	 * 具体位置
	 */

		private String addr;
	/**
	 * 受理时间
	 */

		private Date accepttime;
	/**
	 * 维修描述
	 */

		private String repaircontent;
	/**
	 * 报修单号
	 */

		private String repairno;

}
