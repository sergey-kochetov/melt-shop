package ru.com.melt.info.component.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("applicationAccessDeniedHandler")
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccessDeniedHandlerImpl.class);

    @Value("${application.production}")
    private boolean production;

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        LOGGER.error("Detected AccessDeniedException" + e.getMessage(), e);
        if (production) {
            httpServletResponse.sendRedirect("/error?status=403");
        } else {
            throw new ServletException(e);
        }
    }
}
