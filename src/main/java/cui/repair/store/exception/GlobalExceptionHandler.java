package cui.repair.store.exception;


import cui.repair.store.entity.LoginUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 统一异常处理器
 *
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    public static final String SHIRO_ERROR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)
    public Object  handleException(HttpServletRequest request,
                             HttpServletResponse response, Exception e){
        //从shiro的session中取activeUser
        Subject subject = SecurityUtils.getSubject();
        //取身份信息
        LoginUserInfo loginUserInfo = (LoginUserInfo) subject.getPrincipal();
        //通过model传到页面
        request.getSession().setAttribute("loginUserInfo", loginUserInfo);
        log.error(e.getMessage());
        ModelAndView mav = new ModelAndView();
        mav.addObject("msg", e.getMessage());
        mav.addObject("url", request.getRequestURL());
        String path = request.getContextPath();
        String home = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        log.info(home);
        mav.addObject("home",home);
        mav.setViewName(SHIRO_ERROR_VIEW);
        return mav;
    }
}
