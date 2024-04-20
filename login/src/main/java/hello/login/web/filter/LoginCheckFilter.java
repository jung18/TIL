package hello.login.web.filter;

import hello.login.web.SessionConst;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {

    // 필터를 거치지 않을 경로들
    private static final String[] whitelist = {"/", "/members/add", "/login", "/logout", "/css/*"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            log.info("인증 체크 필터 시작");

            if (isLoginCheckPath(requestURI)) {
                log.info("인증 체크 로직 시작 {}", requestURI);
                HttpSession session = httpRequest.getSession(false);
                if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
                    log.info("미인증 사용자 요청 {}", requestURI);
                    // 로그인 페이지로 redirect
                    // redirect에 현재 요청 페이지 정보를 넘겨줘서 로그인을 하면 이 페이지로 다시 오도록 설정
                    httpResponse.sendRedirect("/login?redirectURL=" + requestURI);
                    return;
                }
            }
            chain.doFilter(request, response);

        } catch (Exception e) {
            throw e; // 예외 로깅 가능하지만, 톰캣까지 예외를 보내줘야함
        } finally {
            log.info("인증 체크 필터 종료");
        }

    }

    /**
     * whitelist의 경우 로그인 체크를 거치지 않음
     */
    private boolean isLoginCheckPath(String requsetURI) {
        return !PatternMatchUtils.simpleMatch(whitelist, requsetURI);
    }
}
