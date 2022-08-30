package com.main.config;

import com.main.jwt.JwtAuthenticationFilter;
import com.main.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.httpBasic().disable()
                .authorizeRequests()
                //.antMatchers("/main").authenticated()                             //해당 매치 되는 항목 인증
                .antMatchers(HttpMethod.POST, "/main/test").authenticated()
                .antMatchers("/**").permitAll()                                  // 보안 요구 없이 요청 허용
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),             //아래 클래스 실행전에 먼저 실행
                        UsernamePasswordAuthenticationFilter.class);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);    // 스프링시큐리티가 생성하지도않고 기존것을 사용하지도 않음
                                                                                            // jwt 같은 토큰 방식 사용시 설정
    }

}
