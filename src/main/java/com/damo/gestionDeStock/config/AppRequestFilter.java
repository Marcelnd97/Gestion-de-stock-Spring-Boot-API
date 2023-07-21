package com.damo.gestionDeStock.config;

import com.damo.gestionDeStock.service.auth.AppUserDetailsService;
import com.damo.gestionDeStock.utils.JwtUtil;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class AppRequestFilter extends OncePerRequestFilter {

    @Autowired
    private AppUserDetailsService appUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//
//        final  String authHeader = request.getHeader("Authorization");
//
//        String userEmail = null;
//        String jwt = null;
//        String idEntreprise = null;
//
//        if (authHeader != null && authHeader.startsWith("Bearer ")){
//            jwt = authHeader.substring(7);
//            userEmail = jwtUtil.extractUsername(jwt);
//            idEntreprise = jwtUtil.extractIdEntreprise(jwt);
//        }
//
//        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = this.appUserDetailsService.loadUserByUsername(userEmail);
//            if (jwtUtil.validateToken(jwt, userDetails)){
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//                        userDetails, null, userDetails.getAuthorities()
//                );
//                usernamePasswordAuthenticationToken.setDetails(
//                        new WebAuthenticationDetailsSource().buildDetails(request)
//                );
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//            }
//        }
//        MDC.put("idEntreprise", idEntreprise);
//        filterChain.doFilter(request, response);
//    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        String userEmail = null;
        String jwt = null;
        String idEntreprise = null;

        if(authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            userEmail = jwtUtil.extractUsername(jwt);
            idEntreprise = jwtUtil.extractIdEntreprise(jwt);
        }

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.appUserDetailsService.loadUserByUsername(userEmail);
            if (jwtUtil.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                usernamePasswordAuthenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        MDC.put("idEntreprise", idEntreprise);
        chain.doFilter(request, response);
    }
}
