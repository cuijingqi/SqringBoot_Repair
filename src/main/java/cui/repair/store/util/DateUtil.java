package cui.repair.store.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    /**
     *判断当前时间与给定时间差是否大于5分钟
     * @param date
     * @return 大于5分钟返回true
     * @throws Exception
     */
    public static boolean gt5MinusOf(String date) throws Exception{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date1=sdf.parse(date);
        Date now=sdf.parse(sdf.format(new Date()));
        if(now.getTime()-date1.getTime()>5*60*1000){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     *判断当前时间与给定时间差是否大于一天
     * @param date
     * @return 大于一天返回true
     * @throws Exception
     */
    public static boolean gtOneDayOf(String date) throws Exception{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        Date date1=sdf.parse(date);
        Date now=sdf.parse(sdf.format(new Date()));
        if(now.getTime()-date1.getTime()>24*60*60*1000){
            return true;
        }
        else{
            return false;
        }
    }
}
