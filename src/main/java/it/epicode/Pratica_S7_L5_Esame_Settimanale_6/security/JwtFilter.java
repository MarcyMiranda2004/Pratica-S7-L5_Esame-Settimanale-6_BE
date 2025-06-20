package it.epicode.Pratica_S7_L5_Esame_Settimanale_6.security;

import it.epicode.Pratica_S7_L5_Esame_Settimanale_6.exception.NotFoundException;
import it.epicode.Pratica_S7_L5_Esame_Settimanale_6.exception.UnAuthorizedException;
import it.epicode.Pratica_S7_L5_Esame_Settimanale_6.model.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTool jwtTool;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            throw new UnAuthorizedException("Token non presente, non sei autorizzato ad usare il servizio richiesto");
        }

        String token = authorization.substring(7);
        jwtTool.validateToken(token);

        try {
            User user = jwtTool.getUserFromToken(token);
            Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (NotFoundException e) {
            throw new UnAuthorizedException("Utente collegato al token non trovato");
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}
