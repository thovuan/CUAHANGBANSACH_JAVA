package com.cuahangbansach.cuahangbansach_java.Security;

import com.cuahangbansach.cuahangbansach_java.Service.CustomStaffDetailsService;
import com.cuahangbansach.cuahangbansach_java.Service.StaffIdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    public CustomStaffDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers("/SHOP/**").permitAll()
                                .requestMatchers("/ShoppingCart/**").permitAll()
                                .requestMatchers("/Error/**").permitAll()
                                .requestMatchers("/About/**").permitAll()
                                .requestMatchers("/Tracking/**").permitAll()
                                .requestMatchers("/Home/**").permitAll()
                                .requestMatchers("/NeoHome/**").hasAnyAuthority("Admin", "Nhân Viên Quản Lý Đơn Hàng", "Nhân viên Quản Lý Sách")
                                .requestMatchers("/Identity/Guest/**").permitAll()
//                        .requestMatchers("/Identity/Admin/**").permitAll()
                                .requestMatchers("/QLDONHANG/**").hasAnyAuthority("Admin", "Nhân Viên Quản Lý Đơn Hàng")
                                .requestMatchers("/QLSACH/**").hasAnyAuthority("Admin", "Nhân viên Quản Lý Sách")
                                .requestMatchers("/QLTHELOAI/**").hasAnyAuthority("Admin", "Nhân viên Quản Lý Sách")
                                .requestMatchers("/QLNXB/**").hasAnyAuthority("Admin", "Nhân viên Quản Lý Sách")
                                .requestMatchers("/QLKHACH/**").hasAuthority("Admin")
                                .requestMatchers("/css/**", "/js/**", "/images/**", "/PIC_INTRO/**").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/Identity/Admin/Login").permitAll()
                        .loginProcessingUrl("/Identity/Admin/Login").permitAll()
                        .defaultSuccessUrl("/NeoHome/Index",true).permitAll()
                        .failureUrl("/Identity/Admin/Login?error=true").permitAll()
                )
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return userDetailsService;
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }
}