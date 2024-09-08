package com.designershop.security;

import com.designershop.entities.UserProfile;
import com.designershop.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final HttpSession session;
    private final MyUserDetailsService myUserDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;
        if (ObjectUtils.isNotEmpty(authHeader) && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            username = JwtUtil.parseToken(jwt).getSubject();
        }

        if (StringUtils.isNotBlank(username)
                && ObjectUtils.isEmpty(SecurityContextHolder.getContext().getAuthentication())) {
            MyUser myUser = myUserDetailsService.loadUserByUsername(username);
            UserProfile sessionUserProfile = (UserProfile) session.getAttribute("userProfile");

            if (JwtUtil.validateToken(jwt, myUser)) {
                if (Objects.nonNull(sessionUserProfile) && StringUtils.equals(sessionUserProfile.getSignOnToken(), jwt)
                        && !StringUtils.equals("Y", sessionUserProfile.getIsLock())
                        && !sessionUserProfile.isDeleted()) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            myUser, null, myUser.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
