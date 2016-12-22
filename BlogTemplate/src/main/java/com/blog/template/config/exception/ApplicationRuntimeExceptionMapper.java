package com.blog.template.config.exception;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationServiceException;

@Singleton
@Provider
public class ApplicationRuntimeExceptionMapper implements ExceptionMapper<Exception> {
    
    private static Logger LOG = Logger.getLogger(ApplicationRuntimeException.class);

    @Override
    public Response toResponse(Exception exception) {
        
        if (exception instanceof AccessDeniedException) {
            LOG.info("AccessDenied Exception: The used does not allow to access this method.");
            LOG.error(exception.getMessage(), exception); 
            return Response.status(Response.Status.UNAUTHORIZED).type(MediaType.APPLICATION_JSON).build();
        }

        if (exception instanceof WebApplicationException) {
            LOG.info("WebApplication Exception: Technical problem while executing method.");
            LOG.error(exception.getMessage(), exception); 
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_JSON).build();
        }
        
        if (exception instanceof ConstraintViolationException) {
            LOG.info("Constraint Violation Exception: Business problem while executing method.");
            LOG.error(exception.getMessage(), exception);
            
            ConstraintViolationException ex = (ConstraintViolationException) exception;            
            Map<String, String> responseObj = new HashMap<String, String>();
            for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
                responseObj.put(violation.getPropertyPath().toString(), violation.getMessage());
            }

            return Response.status(Response.Status.BAD_REQUEST).entity(responseObj).type(MediaType.APPLICATION_JSON).build();
        }
        
        if (exception instanceof AuthenticationServiceException){
            LOG.info("AuthenticationServiceException Exception: Username or Password Error");
            LOG.error(exception.getMessage(), exception); 
            return Response.status(Response.Status.BAD_REQUEST).entity("Login error! Please check your username and password.").type(MediaType.APPLICATION_JSON).build();
        }
    
        LOG.info("Unknown Exception: Technical problem while executing method. ");
        LOG.error(exception.getMessage(), exception);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_JSON).build();
    }
}
