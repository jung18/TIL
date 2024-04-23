package hello.exception.resolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try {
            // IllegalArgumentException 일 경우는 원래는 500이 터지지만 ExceptionResolver로 예외를 처리하고
            // 400으로 변환해 sendError로 에러 메시지와 함께 전송 -> WAS는 정상동작
            if (ex instanceof IllegalArgumentException) {
                log.info("IllegalArgumentException resolver to 400");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
                return new ModelAndView();
            }
        } catch (IOException e) {
            log.info("resolver ex", e);
        }

        return null; // IllegalArgumentException 가 아니어서 null 이 리턴되면 그냥 WAS에 예외가 터짐
    }
}
