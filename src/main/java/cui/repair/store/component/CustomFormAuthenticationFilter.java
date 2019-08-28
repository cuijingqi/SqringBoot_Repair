package cui.repair.store.component;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {

    //重写FormAuthenticationFilter的认证方法
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //在这里进行验证码的校验

        //从session获取正确验证码
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session =httpServletRequest.getSession();
        //取出session的验证码（正确的验证码）
        String rightCode = (String) session.getAttribute("rightCode");

        //取出页面的验证码
        //输入的验证和session中的验证进行对比
        String tryCode = httpServletRequest.getParameter("tryCode");
        if(tryCode!=null && rightCode!=null && !tryCode.equals(rightCode)){
            //如果校验失败，将验证码错误失败信息，通过shiroLoginFailure设置到request中
            httpServletRequest.setAttribute("shiroLoginFailure", "randomCodeError");
            //拒绝访问，不再校验账号和密码
            return true;
        }
        return super.onAccessDenied(request, response);
    }
    //重写issueSuccessRedirect，直接跳转我们设置的SuccessUrl
    @Override
    protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
        WebUtils.issueRedirect(request, response, this.getSuccessUrl(), null, true);
    }
}
