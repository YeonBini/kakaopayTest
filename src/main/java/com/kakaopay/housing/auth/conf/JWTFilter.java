package com.kakaopay.housing.auth.conf;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakaopay.housing.auth.service.UserTokenService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Service
public class JWTFilter implements Filter {

    private UserTokenService userTokenService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String header = req.getHeader("Authorization");

        if(!userTokenService.validateToken(header)) {
//            throw new AuthenticationException("Authentication Failed");
            AuthenticationException errorResponse = new AuthenticationException("Authentication Failed");
            response.getWriter().write(convertObjectToJson(errorResponse));

        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    public void setUserTokenService(UserTokenService uts) {
        userTokenService = uts;
    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
