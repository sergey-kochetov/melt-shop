package ru.com.melt.info.filter;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ResumeFilter extends AbstractFilter {

    @Value("${application.production}")
    private boolean production;

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        String requestUrl = req.getRequestURI();
        req.setAttribute("REQUEST_URL", requestUrl);
        try {
            chain.doFilter(req, resp);
        } catch (Throwable th) {
            LOGGER.error("Process request failed: " + requestUrl, th);
            handleException(th, requestUrl, resp);
        }
    }

    private void handleException(Throwable th, String requestUrl, HttpServletResponse resp) throws ServletException, IOException {
        if (production && !"/error".equals(requestUrl)) {
            resp.sendRedirect("/error?url=" + requestUrl);
        }
        throw new ServletException(th);
    }
}
