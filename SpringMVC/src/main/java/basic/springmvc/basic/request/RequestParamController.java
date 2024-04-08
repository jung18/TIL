package basic.springmvc.basic.request;

import basic.springmvc.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username = {}, age = {}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody // == @RestController처럼 뷰 리졸버 안찾아가고 바로 바디에 문자열 응답
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge) {

        log.info("username = {}, age = {}", memberName, memberAge);
        return "ok";
    }

    @ResponseBody // == @RestController처럼 뷰 리졸버 안찾아가고 바로 바디에 문자열 응답
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam("username") String username,
            @RequestParam("age") int age) {

        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @ResponseBody // == @RestController처럼 뷰 리졸버 안찾아가고 바로 바디에 문자열 응답
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @ResponseBody // == @RestController처럼 뷰 리졸버 안찾아가고 바로 바디에 문자열 응답
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "age", required = false) int age) {  // true가 디폴트

        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @ResponseBody // == @RestController처럼 뷰 리졸버 안찾아가고 바로 바디에 문자열 응답
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(value = "username", defaultValue = "guest") String username,
            @RequestParam(value = "age", defaultValue = "-1") int age) {

        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @ResponseBody // == @RestController처럼 뷰 리졸버 안찾아가고 바로 바디에 문자열 응답
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {

        log.info("username = {}, age = {}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttribute(@ModelAttribute HelloData helloData) {

//        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        log.info("helloData = {}", helloData);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {  //@ModelAttribute 생략 가능

//        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        log.info("helloData = {}", helloData);
        return "ok";
    }

}
