package ru.com.melt.info.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.com.melt.info.entity.Profile;
import ru.com.melt.info.model.CurrentProfileImpl;
import ru.com.melt.info.repository.storage.ProfileRepository;

@Service
public class AuthentificationService implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthentificationService.class);

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Profile profile = profileRepository.findByUidOrEmailOrPhone(username, username, username);
        if (profile != null) {
            return new CurrentProfileImpl(profile);
        } else {
            LOGGER.error("Profile not found by " + username);
            throw new UsernameNotFoundException("Profile not found by " + username);
        }
    }
}
