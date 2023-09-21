package d209.Idontcare.config;


import d209.Idontcare.user.entity.Role;
import d209.Idontcare.user.service.UserService;
import d209.Idontcare.utils.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.http.HttpHeaders;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final String SECRET_KEY = "i.don.care";
    private String refreshToken;

    @Override
    protected  void doFilterInternal(HttpServletRequest request,
                                     HttpServletResponse response,
                                     FilterChain filterChain) throws ServletException, IOException{

        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(authorization==null || !authorization.startsWith("Bearer ")){ // 토큰이 null이거나 Bearer으로 시작하지 않으면

            log.error("authorization을 잘못 보냈습니다.");
//            response.setCharacterEncoding("UTF-8");
//            response.setStatus(HttpStatus.UNAUTHORIZED.value());
//            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//
//            String errorResponse = "{\"code\" : \"401\", \"msg\":\"accessToken이 없습니다.\"}";
//            response.getWriter().write(errorResponse);
            filterChain.doFilter(request,response);
            return;
        }
        // Token 꺼내기
        String token = authorization.split(" ")[1];
        try {
            JwtUtil.isExpired(token, SECRET_KEY);
        }
        catch (ExpiredJwtException e){
            log.error("토큰이 만료되었습니다.");
            filterChain.doFilter(request,response);
            return;
        }
        catch (MalformedJwtException e){
            log.error("올바른 토큰이 아닙니다.");
            filterChain.doFilter(request,response);
            return;
        }

//        if(tokenBlacklistService.isTokenBlacklisted(token)){ // 로그아웃 로직 (블랙리스트에 등록된 토큰)
//
//            log.error("로그아웃 된 사용자입니다.");
//            filterChain.doFilter(request,response);
//            return;
//
//        }

        String userId = JwtUtil.getUserId(token);

        if(userId==null){
            log.error("token에서 userId를 가져오지 못했습니다");
            filterChain.doFilter(request,response);
            return;
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(Role.PARENT.toString()));
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userId,null,authorities);

        // Detail을 넣어 줌
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request,response);
    }

}
