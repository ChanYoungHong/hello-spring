package hello.hellospring.config;


import hello.hellospring.security.filter.ApiCheckFilter;
import hello.hellospring.security.filter.ApiLoginFilter;
import hello.hellospring.security.handler.ApiLoginFailHandler;
import hello.hellospring.security.handler.ClubLoginSuccessHandler;
import hello.hellospring.service.Impl.ClubUserDetailsServiceImpl;
import hello.hellospring.util.JWTUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Log4j2
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ClubUserDetailsServiceImpl clubUserDetailsService;

    @Bean
    public ApiCheckFilter apiCheckFilter() {
        return new ApiCheckFilter("/notes/**/*");
    }

    @Bean
    public ApiLoginFilter apiLoginFilter() throws Exception {

        ApiLoginFilter apiLoginFilter = new ApiLoginFilter("/api/login", jwtUtil());

        apiLoginFilter.setAuthenticationManager(authenticationManager());

        apiLoginFilter
            .setAuthenticationFailureHandler(new ApiLoginFailHandler());

        return apiLoginFilter;
    }

    @Bean
    public JWTUtil jwtUtil() {
        return new JWTUtil();
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.authorizeRequests()
//            .antMatchers("/sample/all").permitAll()
//            .antMatchers("/sample/member").hasRole("USER");

        http.formLogin(); // 인가 인증에 문제시 로그인 화면
        http.csrf().disable(); // CSRF 토큰 발행하지 않게 설정
        http.logout();
        http.oauth2Login().successHandler(successHandler());
        http.rememberMe().tokenValiditySeconds(60*60*7);
        userDetailsService(clubUserDetailsService); // 7days

        http.addFilterBefore(apiCheckFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(apiCheckFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    private void userDetailsService(ClubUserDetailsServiceImpl clubUserDetailsService) {


    }

    private ClubLoginSuccessHandler successHandler() {
        return new ClubLoginSuccessHandler(passwordEncoder());
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        // 사용자 계정은 user1
//        auth.inMemoryAuthentication().withUser("user1")
//            .password("$2a$10$8E4OmpwpWZbSTmf.2HoZK.PWN8qPBvUVGVz83bOx4v/AjQLS1cQXK")
//            .roles("USER");
//    }


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .authorizeRequests()
//            .anyRequest().authenticated();
//        http
//            .formLogin()
////            .loginPage("/loginPage") // 로그인 페이지로 이동함
//            .defaultSuccessUrl("/")
//            .failureUrl("/login")
//            .usernameParameter("userId")
//            .passwordParameter("passwd")
//            .loginProcessingUrl("/login_proc")
////            .successHandler(new AuthenticationSuccessHandler() {
////                @Override
////                public void onAuthenticationSuccess(HttpServletRequest request,
////                                                    HttpServletResponse response,
////                                                    Authentication authentication)
////                    throws IOException, ServletException {
////                    System.out.println("authentication : "  + authentication.getName());
////                }
////            })
////            .failureHandler(new AuthenticationFailureHandler(){
////
////                @Override
////                public void onAuthenticationFailure(HttpServletRequest request,
////                                                    HttpServletResponse response,
////                                                    AuthenticationException exception)
////                    throws IOException, ServletException {
////                    System.out.println("exeception : " + exception.getMessage());
////                    response.sendRedirect("/login");
////                }
////            })
//            .permitAll();
//    }

}
