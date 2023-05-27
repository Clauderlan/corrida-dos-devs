package com.c7.corrida.security.filters.exceptions;

import com.c7.corrida.services.exceptions.DTOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

import java.io.IOException;

public class JWTExceptions {
    public static void expiredError(HttpServletResponse response) throws IOException {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        String error = "Token expired";
        DTOException exception = new DTOException(status.value(), error);
        response.setStatus(exception.getStatus());
        response.setContentType("application/json");
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().print(objectMapper.writeValueAsString(exception));
        response.getWriter().flush();
    }

    public static void noAuth(HttpServletResponse response) throws IOException{

        HttpStatus status = HttpStatus.UNAUTHORIZED;
        String error = "Token not found";
        DTOException exception = new DTOException(status.value(), error);
        response.setStatus(exception.getStatus());
        response.setContentType("application/json");
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().print(objectMapper.writeValueAsString(exception));
        response.getWriter().flush();

    }
}
