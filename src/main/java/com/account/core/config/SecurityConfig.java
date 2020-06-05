package com.account.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 安全配置
 *
 * @author XIAXINYU3
 * @date 2020.4.28
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/plugins/**", "/images/**", "/scripts/**", "datasource/**", "css/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/oauth/**", "/login/**", "/logout/**", "/actuator/**", "plugins/**", "/encrypt")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                //指定登录页的路径
                .loginPage("/login.html")
                //指定自定义form表单请求的路径
                .loginProcessingUrl("/authentication/form")
                .failureUrl("/login-error.html")
                .defaultSuccessUrl("/index.html")
                //必须允许所有用户访问我们的登录页（例如未验证的用户，否则验证流程就会进入死循环）
                //这个formLogin().permitAll()方法允许所有用户基于表单登录访问/login这个page。
                .permitAll();
    }


    //定义认证规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);

        //auth.jdbcAuthentication()...
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())   //在Spring Security 5.0中新增了多种加密方式，页改变了密码的格式
                .withUser("admin").password(new BCryptPasswordEncoder().encode("admin")).roles("VIP1", "VIP2")
                .and()
                .withUser("xiaxinyu").password(new BCryptPasswordEncoder().encode("xiaxinyu")).roles("VIP2", "VIP3")
                .and()
                .withUser("pengshenglan").password(new BCryptPasswordEncoder().encode("pengshenglan")).roles("VIP1", "VIP3");
    }
}