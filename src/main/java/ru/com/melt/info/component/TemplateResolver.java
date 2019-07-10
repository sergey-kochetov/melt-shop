package ru.com.melt.info.component;

import javax.annotation.Nonnull;

public interface TemplateResolver {
    @Nonnull String resolve(@Nonnull String template, @Nonnull Object model);
}
