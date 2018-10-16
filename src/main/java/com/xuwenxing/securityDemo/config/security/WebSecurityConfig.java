package com.xuwenxing.securityDemo.config.security;

import com.xuwenxing.securityDemo.domain.system.User;
import com.xuwenxing.securityDemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 编写我们自己定值的内存用户名密码认证
 *
 * Created by xuwx on 2018/9/4.
 */
@Configuration
/**
 *  1. 通过 @EnableWebSecurity注解开启Spring Security的功能
 * @EnableGlobalMethodSecurity(prePostEnabled = true)这个注解，可以开启security的注解，
 * 我们可以在需要控制权限的方法上面使用@PreAuthorize，@PreFilter这些注解
 * 2. 继承 WebSecurityConfigurerAdapter 类，并重写它的方法来设置一些web安全的细节。
 * 我们结合@EnableWebSecurity注解和继承WebSecurityConfigurerAdapter，来给我们的系统加上基于web的安全机制。
 * 3.
 */
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    /**
     * 配置策略
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//       http.csrf().disable();//csrf防御默认是开启的
       http.authorizeRequests().antMatchers("/bootstrap/**").permitAll().anyRequest().authenticated()
               .and().formLogin().loginPage("/login").failureUrl("/login?error=true").permitAll().successHandler(loginSuccessHandler())
               .and().logout().permitAll().invalidateHttpSession(true).deleteCookies("JSESSIONID").logoutSuccessHandler(logoutSuccessHandler())
               .and().sessionManagement().maximumSessions(10).expiredUrl("/login");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
        auth.eraseCredentials(false);
    }

    /**
     * 密码加密
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

    /**
     * 登入处理
     * @return
     */
    @Bean
    public SavedRequestAwareAuthenticationSuccessHandler loginSuccessHandler() {
        return new SavedRequestAwareAuthenticationSuccessHandler(){
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
                User userDetails = (User) authentication.getPrincipal();
                logger.info("USER : "+userDetails.getUsername()+"登录操作成功 SUCCESS!");
                super.onAuthenticationSuccess(request,response,authentication);
            }
        };
    }

    /**
     * 登出处理
     * @return
     */
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new LogoutSuccessHandler(){
            @Override
            public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
               try {
                SecurityUser user = (SecurityUser) authentication.getPrincipal();
                logger.info("USER : "+user.getUsername()+"登出操作成功");
               }catch (Exception e){
                logger.info("logout exception"+e+"登出操作失败:"+e.getMessage());
               }
               httpServletResponse.sendRedirect("/login");
            }
        };

    }

    /**
     * 用户登录实现
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsService(){
            @Autowired
            private UserService  userService;

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                User user = userService.checkUsername(username);
                if (user == null){
                    throw new UsernameNotFoundException("USERNAME"+username+ "没有找到");
                }
                return new SecurityUser(user);
            }
        };
    }







    //    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
// //security 5.0之前的方式 ; 5.0后 需要指定加密方式 {id}password  (id为加密方式,password为加密后的密码)
////               auth.inMemoryAuthentication().withUser("admin").password("admin").roles("USER").
////                and().withUser("1024").password("1024").roles("ADMIN","USER");
//                 auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("admin").password(new BCryptPasswordEncoder().encode("admin")).roles("ADMIN");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////        super.configure(http);
//                http
//                .authorizeRequests().
//                        antMatchers("/bootstrap/**").permitAll()//这里配置的路径与静态资源访问有关  打开控制台可以看见
////                        .antMatchers("/admin/**").hasRole("ADMIN")
////                        .antMatchers("/db/**").access("hasRole('ADMIN') and  hasRole('DBA')")
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin()//开启登录
//                .loginPage("/login")  //指定登录页的路径
//                .permitAll();//我们必须允许所有用户访问我们的登录页面
//                //这个formLogin().permitAll()方法允许基于表单登录的所有的URL的所有用户的访问。
//    }

}
