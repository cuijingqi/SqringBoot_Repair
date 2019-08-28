package cui.repair.store.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 自定义 语言区域信息解析器
 */
public class MyLocaleResolver implements LocaleResolver {

    Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 根据 请求参数，来设置指定的区域信息解析器
     * @param request
     * @return
     */
    @Override
    public Locale resolveLocale(HttpServletRequest request) {

        String l = request.getParameter("l");

        Locale locale = Locale.getDefault();//获取默认的区域信息
        if(!StringUtils.isEmpty(l)){//有请求参数
            String[] arr = l.split("_");

            //按请求参数来更新区域信息的设置
            locale = new Locale(arr[0],arr[1]);

            //将国际化语言保存到session
//            HttpSession session = request.getSession();
//            session.setAttribute("l_session", locale);

        }
//        else{
//            //如果没有带国际化参数，则判断session有没有保存，有保存，则使用保存的，也就是之前设置的，避免之后的请求不带国际化参数造成语言显示不对
//            HttpSession session = request.getSession();
//            Locale localeInSession = (Locale) session.getAttribute("l_session");
//            if(localeInSession != null) {
//                locale = localeInSession;
//            }
//        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, @Nullable HttpServletResponse response, @Nullable Locale locale) {

    }
}
