package ru.com.melt.info.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class ApplicationFilter  implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        LOGGER.debug("Before URL processing: {}",req.getRequestURI());
        filterChain.doFilter(req, servletResponse);
        LOGGER.debug("After URL processing: {}", req.getRequestURI());
    }

    @Override
    public void destroy() {
    }
}
