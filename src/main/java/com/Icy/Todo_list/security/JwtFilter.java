package com.Icy.Todo_list.security;

import com.Icy.Todo_list.model.User;
import com.Icy.Todo_list.repository.UserRepository;
import com.Icy.Todo_list.security.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public JwtFilter(JwtUtil jwtUtil, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // Step 1 — get the Authorization header
        String authHeader = request.getHeader("Authorization");

        // Step 2 — no token? pass through
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Step 3 — extract token (strip "Bearer " prefix)
        String token = authHeader.substring(7);

        // Step 4 — validate token
        if (jwtUtil.isTokenValid(token)) {
            String username = jwtUtil.extractUsername(token);

            // Step 5 — load user from DB
            User user = userRepository.findByUsername(username).orElse(null);

            if (user != null) {
                // Step 6 — tell Spring Security this request is authenticated
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                username,
                                null,
                                List.of(new SimpleGrantedAuthority(user.getRole()))
                        );
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        // Step 7 — continue to the next filter / controller
        filterChain.doFilter(request, response);
    }
}