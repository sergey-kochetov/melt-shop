package ru.com.melt.info.component.impl;

import net.sf.junidecode.Junidecode;
import org.springframework.stereotype.Component;
import ru.com.melt.info.component.TranslitConverter;

@Component
public class JunidecodeTranslitConverter implements TranslitConverter {

    @Override
    public String translit(String text) {
        return Junidecode.unidecode(text);
    }
}
