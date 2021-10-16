package com.wanwei.cbase.common.filter;//package com.wanwei.oneview.common.filter;
//
//import com.wanwei.oneview.common.utils.RedisUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//public class TokenFilter extends OncePerRequestFilter {
//    @Autowired
//    private RedisUtils redisUtils;
//
//    private String TOKEN_HEAD = "Bearer ";
//    private String TOKEN_HEADER = "Authorization";
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String authHeader = request.getHeader(this.TOKEN_HEADER);
//        logger.error("XXXXXXXXXXXXXXXXXXXXXX:"+authHeader);
////        try {
////           JwtUtils.validateJwtToken(authHeader);
////        } catch (TokenException e) {
////            // TOKEN有误 或者token过期
////            e.printStackTrace();
////        }
////        if (authHeader != null && authHeader.startsWith(TOKEN_HEAD)) {
////            final String authToken = authHeader.substring(TOKEN_HEAD.length());
////            if (authToken != null && redisUtils.hasKey(authToken)) {
////                String username = redisUtils.opsForValue().get(authToken);
////                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
////                    UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
////                    //可以校验token和username是否有效，目前由于token对应username存在redis，都以默认都是有效的
////                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
////                            userDetails, null, userDetails.getAuthorities());
////                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
////                            request));
////                    logger.info("authenticated user " + username + ", setting security context");
////                    SecurityContextHolder.getContext().setAuthentication(authentication);
////                }
////            }
////        }
//        filterChain.doFilter(request, response);
//    }
//}
