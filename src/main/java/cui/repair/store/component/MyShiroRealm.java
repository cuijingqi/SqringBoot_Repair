package cui.repair.store.component;


import cui.repair.store.entity.LoginUserInfo;
import cui.repair.store.entity.SysPermission;
import cui.repair.store.entity.SysUser;
import cui.repair.store.service.SysService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户权限验证、以及对应的权限菜单列表获取
 */
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {
    @Resource
    private SysService sysService;

    //权限信息，包括角色以及权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //如果身份认证的时候没有传入User对象，这里只能取到userName
        //也就是SimpleAuthenticationInfo构造的时候第一个参数传递需要LoginUserInfo对象
        LoginUserInfo loginUserInfo  = (LoginUserInfo)principals.getPrimaryPrincipal();

        List<SysPermission> permissionsList = null;
        try {
            permissionsList = sysService.findPermissionsByUserId(loginUserInfo.getUserid());
            log.info(""+permissionsList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(SysPermission p : permissionsList){
            authorizationInfo.addStringPermission(p.getPercode());
            log.info(""+p.getPercode());
        }
        return authorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        log.info("MyShiroRealm.doGetAuthenticationInfo()");
        //获取用户的输入的账号.
        String userCode = (String)token.getPrincipal();
        log.info(""+token.getCredentials());
        //通过username从数据库中查找 User对象.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        SysUser sysUser = null;
        try {
            sysUser = sysService.findByUserCode(userCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("----->>sysUser="+sysUser);
        if(sysUser == null){
            return null;
        }

        log.info("数据库的密码:"+sysUser.getUserpwd());

        LoginUserInfo loginUserInfo = new LoginUserInfo();

        loginUserInfo.setUserid(sysUser.getId());
        loginUserInfo.setUsercode(sysUser.getUsercode());
        loginUserInfo.setUsername(sysUser.getUsername());
        List<SysPermission> menusList = null;
        try {
            menusList = sysService.findMenusByUserId(sysUser.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //添加菜单列表
        loginUserInfo.setMenus(menusList);


        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                loginUserInfo, //这里传入的是loginUserInfo对象
                sysUser.getUserpwd(), //密码
                ByteSource.Util.bytes(sysUser.getSalt()),
                getName()  //realm name
        );
        log.info("authenticationInfo:"+authenticationInfo);
        return authenticationInfo;
    }


    //清除缓存
    public void clearCachedAuthorization(){
        Subject subject = SecurityUtils.getSubject();

        PrincipalCollection principals = subject.getPrincipals();

        super.clearCachedAuthorizationInfo(principals);
    }
}
