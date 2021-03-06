package kr.seok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /* 인가 API 설정 테스트를 위한 InMemory 계정 등록 */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("user").password("{noop}1234").roles("USER");
//        auth.inMemoryAuthentication().withUser("sys").password("{noop}1234").roles("SYS");
//        auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("ADMIN");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 인가 정책
        http
                /* 모든 경로에 대해서 권한 요청 설정 */
                .authorizeRequests()
                .antMatchers("/users").hasRole("USER")

//                    /* custom 한 /login resource 접근을 허용하도록 하기 위한 설정 */
//                    .antMatchers("/login").permitAll()
//
//                    /* /user 경로의 request가 들어오는 경우 인가 처리를 통해 USER role을 가진 사용자에 대해서 resource를 제공하겠다는 설정 */
//                    .antMatchers("/user").hasRole("USER")
//                    .antMatchers("/admin/pay").hasRole("ADMIN")
//                    .antMatchers("/admin/**").access("hasRole('ADMIN') or hasRole('SYS')")

                .anyRequest()
//                .authenticated()
                .permitAll()

            /* 3.1.1. 필터 초기화 및 다중 보안 설정 용 */
//            .antMatcher("/admin/**")
//            .authorizeRequests()
//            .anyRequest().authenticated()
//        .and()
//            .httpBasic()
            ;
        // 인증 정책
        http
                .formLogin()
//                .loginPage("/loginPage")
//                .defaultSuccessUrl("/")
//                .failureUrl("/login")
//                .usernameParameter("userId")
//                .passwordParameter("passwd")
//                .loginProcessingUrl("/login_proc")
//                .successHandler(new AuthenticationSuccessHandler() {
//                    @Override
//                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//                        System.out.println("authentication : " + authentication.getName());
//                        response.sendRedirect("/");
//                    }
//                })
//                .failureHandler(new AuthenticationFailureHandler() {
//                    @Override
//                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//                        System.out.println("exception" + exception.getMessage());
//                        response.sendRedirect("/login");
//                    }
//                })
//                .permitAll()
//                http
//                .logout()
//                /* GET, POST 가능 */
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/login")
//                .addLogoutHandler(new LogoutHandler() {
//                    @Override
//                    public void logout(
//                            HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
//                        HttpSession session = request.getSession();
//                        session.invalidate();
//                    }
//                })
//                .logoutSuccessHandler(new LogoutSuccessHandler() {
//                    @Override
//                    public void onLogoutSuccess(
//                            HttpServletRequest request, HttpServletResponse response, Authentication authentication
//                    ) throws IOException, ServletException {
//                        response.sendRedirect("/login");
//                    }
//                })
//                /* 쿠키명 */
//                .deleteCookies("remember-me")

//                /* 캐시 필터 구현 시 formLogin 에 추가되는 handler */
//                .successHandler(new AuthenticationSuccessHandler() {
//                    @Override
//                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//                        RequestCache requestCache = new HttpSessionRequestCache();
//                        SavedRequest savedRequest = requestCache.getRequest(request, response);
//                        String redirectUrl = savedRequest.getRedirectUrl();
//                        response.sendRedirect(redirectUrl);
//                    }
//                })
                ;


//        http
//                .rememberMe()
//                .rememberMeParameter("remember") // 기본 파라미터 명 -> "remember-me"
//                .tokenValiditySeconds(3600) // 만료시간 default 14일
//                .alwaysRemember(true) // remember me  기능이 활성화되지 않아도 항상 실행
//                /* user 계정 확인 메서드 */
//                .userDetailsService(userDetailsService)
//            ;

            /* 익명 처리 */
//        http
//                .anonymous()
//        ;

//        /* 세션 관리*/
//        http
//                .sessionManagement()
//                .maximumSessions(1)
//                .maxSessionsPreventsLogin(true)
//                .maxSessionsPreventsLogin(false)
//                .expiredUrl("/login")
//                /* 위 API 와 함께 사용할 수 없음 */
//                .sessionManagement()
//                .invalidSessionUrl("/login")


            /* 사용자의 쿠키를 공격자의 쿠키로 인증처리 한 뒤 공격자가 해당 쿠키로 인증하는 세션 고정 공격 */
//        http
                /* 세션 고정 보호*/
//                .sessionManagement()
//                .sessionFixation()
                /* 세센 고정 보호를 사용하지 않는 경우 위와 같은 문제가 발생할 수 있음 */
//                 .none()
//                 .migrateSession()
//                 .newSession()
                /* 인증 처리 시 기존 세션 내용을 새로운 인증 세션으로 변경하는 방법 */
//                .changeSessionId()
//            .and()
//                .sessionManagement()
                /* 스프링 시큐리티가 항상 세션을 생성하는 정책 */
                // .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                /* 스프링 시큐리티가 생성하지 않지만 이미 존재하면 사용 */
                // .sessionCreationPolicy(SessionCreationPolicy.NEVER)
                /* 스프링 시큐리티가 생성하지 않고 존재해도 사용하지 않음 */
                // .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                /* 스프링 시큐리티가 필요 시 생성(기본값) */
//                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)

//            ;

//        http
//                .exceptionHandling()
//                .authenticationEntryPoint(new AuthenticationEntryPoint() {
//                    @Override
//                    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//                        response.sendRedirect("/login");
//                    }
//                })
//                .accessDeniedHandler(new AccessDeniedHandler() {
//                    @Override
//                    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
//                        response.sendRedirect("/denied");
//                    }
//                })
//                ;
    }
}


























