//package wanwei.security;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author http://www.sm1234.cn
// * @version 1.0
// * @description cn.sm1234.security
// * @date 18/4/15
// */
//public class MyAuthenticationnSuccessHandler implements AuthenticationSuccessHandler {
//
//    private ObjectMapper objectMapper = new ObjectMapper();
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        //返回json数据
//        Map result = new HashMap();
//        result.put("success",true);
//
//        String json = objectMapper.writeValueAsString(result);
//        response.setContentType("text/json;charset=utf-8");
//        response.getWriter().write(json);
//    }
//}
