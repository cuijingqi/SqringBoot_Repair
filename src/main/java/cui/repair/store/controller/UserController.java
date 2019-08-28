package cui.repair.store.controller;


import cui.repair.store.entity.LoginUserInfo;
import cui.repair.store.entity.SysUser;
import cui.repair.store.exception.CustomException;
import cui.repair.store.service.LoginRecordService;
import cui.repair.store.service.SysRoleService;
import cui.repair.store.service.SysService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class UserController {
    @Autowired
    SysService sysService;
    @Autowired
    LoginRecordService loginRecordService;

    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping(value="/user/login_old")
    public String login_old(SysUser sysUser, Map<String,Object> map, HttpSession session) {
       try{
           LoginUserInfo loginUserInfo = sysService.authenticate(sysUser);
           session.setAttribute("loginUserInfo",loginUserInfo);
       }catch (Exception ex){
           map.put("msg",ex.getMessage());
           return "login";
       }
        return "redirect:/main.html";
    }
    @RequestMapping(value="/user/login",method = RequestMethod.GET)
    public String login2(){
        return "login";
    }
    //登陆按钮的提交地址，和ShiroConfig类中 shirFilter方法中设置的保持一致
    // shiroFilterFactoryBean.setLoginUrl("/login");
    @RequestMapping(value="/user/login",method = RequestMethod.POST)
    public String login(SysUser sysUser, HttpServletRequest request) throws Exception {
        // shiro 在认证的过程中出现错误后将异常类型的限定类的全路径通过request返回
        Object shiroLoginFailure = request.getAttribute("shiroLoginFailure");
        if(null!= shiroLoginFailure){
            if(shiroLoginFailure instanceof String){
                String exceptionClassName = (String)shiroLoginFailure;
                if(UnknownAccountException.class.getName().equals(exceptionClassName)){
                    throw new CustomException("账号不存在");
                } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
                    throw new CustomException("用户名或者密码错误");
                }
                else  if("randomCodeError".equals(exceptionClassName)){
                    throw new CustomException("验证码错误");
                }
                else{
                    //最终在异常处理器中，抛出最终为分类的异常信息
                    throw new Exception();
                }
            }
            //该方法不处理登陆成功（认证成功）后的跳转成功页面的操作；
            //shiro默认在认证成功后，会自动跳转到上一个记录的路径。
            // 也可通过  shiroFilterFactoryBean.setSuccessUrl("/main.html");来配置登陆成功后要跳转的页面；
            // 也可以不配置，shiro直接跳转到上一个记录的路径
            //登陆失败后，还到login页面
            return "login";
        }
        else{
            return "dashboard";
        }


    }


    @RequestMapping("/addUser")
    public String saveUser(SysUser sysUser){
        Md5Hash md5Hash = new Md5Hash(sysUser.getUserpwd(), "newedu", 1);
        String pwd=md5Hash.toString();
        sysUser.setUserpwd(pwd);
        sysService.saveUser(sysUser);

        return "redirect:/users";
    }
    @RequestMapping("/users")
    public String list(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                       @RequestParam(value = "pageSize", defaultValue = "5") int pageSize, Map map, HttpServletRequest req){
        map.put("userList",sysService.findItemByPage(currentPage,pageSize));
        return "user/list";
    }





    @RequestMapping("/getUsers")
    @ResponseBody
    public List<SysUser> getAllUser(){
            return sysService.selectAllByRoleId();
    }


    /**
     * 信息
     */
    @RequestMapping("/userinfo/{id}")
    public String  info(@PathVariable("id") String id,Map map) {
        SysUser sysUser = null;
        try {
            sysUser = sysService.querUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("roleList",sysRoleService.selectAll());
        map.put("sysUser",sysUser);
        return "user/edit";
    }
    /**
     * 保存
     */
    @RequestMapping("/usersave")
    public String save( SysUser sysUser){
        sysService.saveUser(sysUser);
        return "redirect:/users";
    }

    /**
     * 修改
     */
    @RequestMapping("/userupdate")
    public String update( SysUser sysUser,String roleid){
         if(sysUser.getUserpwd()!=null && !sysUser.getUserpwd().equals("")) {
             Md5Hash md5Hash = new Md5Hash(sysUser.getUserpwd(), "newedu", 1);
             String pwd = md5Hash.toString();
             sysUser.setUserpwd(pwd);
         }
         //保存用户对象
         sysService.updateUser(sysUser);
         //删除原有用于角色信息
        sysService.deleteUserRole(sysUser.getId());
        Map<String,String> map=new HashMap<String,String>();
        map.put("sys_user_id",sysUser.getId());
        map.put("sys_role_id",roleid);
        //添加新的用户角色信息
        sysService.updateUserRole(map);
        return "redirect:/users";
    }

    /**
     * 删除
     */
    @RequestMapping("/userdelete/{id}")
    public String delete(@PathVariable("id") String id){
            sysService.deleteUser(id);
        return "redirect:/users";
    }


}
