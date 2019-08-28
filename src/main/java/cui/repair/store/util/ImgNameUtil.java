package cui.repair.store.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author 付军
 * @version 1.0
 * @date 2019/8/13
 * @description 自定生成图片的名称
 */
public class ImgNameUtil {
    public static String getImgName(String name){
        String fileExt = name.substring(name.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString().replace("-","")+fileExt;
        return newFileName;
    }

}
