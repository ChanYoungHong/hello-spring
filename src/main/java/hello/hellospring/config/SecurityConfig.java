package hello.hellospring.config;


import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Log4j2
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
            .antMatchers("/sample/all").permitAll()
            .antMatchers("/sample/member").hasRole("USER");

            http.formLogin(); // 인가 인증에 문제시 로그인 화면
            http.csrf().disable();
            http.logout();

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
