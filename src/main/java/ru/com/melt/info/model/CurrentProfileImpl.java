package ru.com.melt.info.model;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import ru.com.melt.info.Constants;
import ru.com.melt.info.entity.Profile;

public class CurrentProfileImpl extends User implements CurrentProfile {
    private static final long serialVersionUID = 1L;
    private final Long id;
    private final String fullName;

    public CurrentProfileImpl(Profile profile) {
        super(profile.getUid(), profile.getPassword(), true, true, true, true,
                Collections.singleton(new SimpleGrantedAuthority(Constants.USER)));
        this.id = profile.getId();
        this.fullName = profile.getFullName();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getUid() {
        return getUsername();
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return String.format("CurrentProfile [id=%s, username=%s]", id, getUsername());
    }
}
