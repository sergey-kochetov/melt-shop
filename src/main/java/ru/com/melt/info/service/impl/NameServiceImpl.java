package ru.com.melt.info.service.impl;

import org.springframework.stereotype.Service;
import ru.com.melt.info.service.NameService;

@Service
public class NameServiceImpl implements NameService {
    @Override
    public String convertName(String name) {
        return name.toUpperCase();
    }
}
