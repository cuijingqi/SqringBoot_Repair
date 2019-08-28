package cui.repair.store.config;


import cui.repair.store.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;


@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    /**
     * Configure simple automated controllers pre-configured with the response
     * status code and/or a view to render the response body. This is useful in
     * cases where there is no need for custom controller logic -- e.g. render a
     * home page, perform simple site URL redirects, return a 404 status with
     * HTML content, a 204 with no content, and more.
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/login.html").setViewName("login");
//        registry.addViewController("/main.html").setViewName("dashboard");
        registry.addViewController("/addrepair.html").setViewName("repair/add");
        registry.addViewController("/building").setViewName("building/add");
        registry.addViewController("/equipment").setViewName("equipment/add");
        registry.addViewController("/showuser").setViewName("user/add");
    }

    /**
     * 将自定义的区域信息解析器，让spring接管
     * 方法名不能是myLocaleResolver，否则国际化无法生效
     * 把方法名修改为了localeResolver()可以
     *
     */
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }

    /**
     * 添加静态资源文件，外部可以直接访问地址
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //需要配置1：----------- 需要告知系统，这是要被当成静态文件的！
        //第一个方法设置访问路径前缀，第二个方法设置资源路径
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/")
                //新增 resourceChain 配置即开启缓存配置。
                //不知道为何不加这个配置 设置了 webjars-locator 未生效。。没过多纠结。。
                //生产时建议开启缓存（只是缓存了资源路径而不是资源内容）,开发是可以设置为false
                .resourceChain(false);
        // ref: https://github.com/spring-projects/spring-boot/issues/4403


    }
}
