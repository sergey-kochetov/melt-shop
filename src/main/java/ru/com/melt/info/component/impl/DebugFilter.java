package ru.com.melt.info.component.impl;

import org.springframework.stereotype.Component;
import ru.com.melt.info.filter.AbstractFilter;
import ru.com.melt.info.util.DebugUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class DebugFilter extends AbstractFilter {

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        try {
            LOGGER.info("****** start ******");
            DebugUtil.turnOnShowMongoQuery();
            chain.doFilter(req, resp);
        } finally {
            DebugUtil.turnOffShowMongoQuery();
            LOGGER.info("****** end ******");
        }
    }

    public boolean isEnabledDebug(){
        return true;
    }

    public String[] getDebugUrl(){
        return new String[]{"/richard-hendricks", "/welcome"};
    }
}