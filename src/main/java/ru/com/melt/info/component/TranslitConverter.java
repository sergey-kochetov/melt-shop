package ru.com.melt.info.component;

import javax.annotation.Nonnull;

public interface TranslitConverter {

    @Nonnull String translit(@Nonnull String text);
}