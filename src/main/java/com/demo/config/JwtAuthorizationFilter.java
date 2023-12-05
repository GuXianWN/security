package com.demo.config;

import cn.hutool.core.collection.ListUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    private static List<String> getClaims(String token) {
        return ListUtil.of("USER", "2");
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
        List<String> claims = getClaims(token);
        List<SimpleGrantedAuthority> authorities = claims.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        String userName = "username";
        return new UsernamePasswordAuthenticationToken(userName, token, authorities);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("filter ------->");
        String token = request.getHeader("Token");
        if (token == null || !token.startsWith("pre")) {
            log.info("没token");
            SecurityContextHolder.clearContext();
            chain.doFilter(request, response);
            return;
        }
        log.info("有token");
        String tokenValue = token.replace("pre", "");
        UsernamePasswordAuthenticationToken authentication = null;
        try {
//            String previousToken = stringRedisTemplate.opsForValue().get(JwtTokenUtils.getId(tokenValue));
//            if (!token.equals(previousToken)) {
//                SecurityContextHolder.clearContext();
//                chain.doFilter(request, response);
//                return;
//            }
            authentication = getAuthentication(tokenValue);
        } catch (JwtException e) {
            logger.error("Invalid jwt : " + e.getMessage());
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        chain.doFilter(request, response);
    }
}