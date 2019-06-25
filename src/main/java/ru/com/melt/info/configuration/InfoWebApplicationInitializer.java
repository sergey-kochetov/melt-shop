package ru.com.melt.info.configuration;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import ru.com.melt.info.controller.ProfileController;
import ru.com.melt.info.filter.ApplicationFilter;
import ru.com.melt.info.listener.ApplicationListener;

import javax.servlet.*;
import java.util.EnumSet;

@Configuration
public class InfoWebApplicationInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        WebApplicationContext context = createWebApplicationContext(servletContext);

        servletContext.setSessionTrackingModes(EnumSet.of(SessionTrackingMode.COOKIE));
        servletContext.addListener(new ContextLoaderListener(context));
        servletContext.addListener(context.getBean(ApplicationListener.class));

        registerFilters(servletContext, context);
        registerServlet(servletContext, context.getBean(ProfileController.class), "/profile");
    }


    private WebApplicationContext createWebApplicationContext(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.scan("ru.com.melt.configuration");
        context.setServletContext(servletContext);
        context.refresh();
        return context;
    }

    private void registerFilters(ServletContext servletContext, WebApplicationContext context) {
        registerFilter(servletContext, new CharacterEncodingFilter("UTF-8", true));
        registerFilter(servletContext, context.getBean(ApplicationFilter.class));
        registerFilter(servletContext, buildConfigurableSiteMeshFilter(), "sitemesh");
    }


    private void registerFilter(ServletContext servletContext, Filter filter, String... filterNames) {
        String filterName = filterNames.length > 0 ? filterNames[0] : filter.getClass().getSimpleName();
        servletContext.addFilter(filterName, filter).addMappingForUrlPatterns(null, true, "/*");
    }

    private void registerServlet(ServletContext container, Servlet servletInstance, String url) {
        ServletRegistration.Dynamic servlet = container.addServlet(servletInstance.getClass().getSimpleName(), servletInstance);
        servlet.setLoadOnStartup(1);
        servlet.addMapping(url);
    }

    private Filter buildConfigurableSiteMeshFilter() {
        return new ConfigurableSiteMeshFilter() {
            @Override
            protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
                builder.addDecoratorPath("/*", "/WEB-INF/template/page-template.jsp");
            }
        };
    }
}
