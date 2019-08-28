package cui.repair.store.controller;


import cui.repair.store.component.MyShiroRealm;
import cui.repair.store.entity.LoginUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class IndexController {

    @Autowired
    MyShiroRealm myShiroRealm;

    @RequestMapping("/main.html")
    public String dashboard(HttpSession session){
        //从shiro的session中取activeUser
        Subject subject = SecurityUtils.getSubject();
        //取身份信息
        LoginUserInfo loginUserInfo = (LoginUserInfo) subject.getPrincipal();
        //通过model传到页面
        session.setAttribute("loginUserInfo", loginUserInfo);

        log.info("isRemembered:"+SecurityUtils.getSubject().isRemembered());
        log.info("isAuthenticated:"+SecurityUtils.getSubject().isAuthenticated());

        // 为什么 明明已经是RememberMe了，为什么还显示为false，可以查看源码Subject.class
        //如果是 authc的情况下 是不能和user并存的 而user级别 恰恰就是 RememberMe =true
        return "dashboard";
    }

    @RequestMapping("/clearshirocache")
    public String clearCachedAuthorization(){

        //清除缓存，正常工作中建议放在service中跟修改权限操作放到一起处理。
        // 这里只是演示方便
        myShiroRealm.clearCachedAuthorization();
        return "dashboard";
    }

}
