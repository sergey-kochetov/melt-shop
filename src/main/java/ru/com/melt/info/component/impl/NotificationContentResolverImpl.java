package ru.com.melt.info.component.impl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import ru.com.melt.info.component.NotificationContentResolver;

import java.io.IOException;
import java.io.StringReader;

@Component
public class NotificationContentResolverImpl implements NotificationContentResolver {
    @Override
    public String resolve(String stringTemplate, Object model) {
        try {
            Template template = new Template("", new StringReader(stringTemplate),
                    new Configuration(Configuration.VERSION_2_3_0));
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        } catch (IOException | TemplateException e) {
            throw new IllegalArgumentException("Can't resolve string template: " + e.getMessage(), e);
        }
    }
}
