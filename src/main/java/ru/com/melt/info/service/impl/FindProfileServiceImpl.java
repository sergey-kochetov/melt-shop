package ru.com.melt.info.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.com.melt.info.entity.Profile;
import ru.com.melt.info.repository.storage.ProfileRepository;
import ru.com.melt.info.service.FindProfileService;

@Service
public class FindProfileServiceImpl implements FindProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Profile findByUid(String uid) {
        return profileRepository.findByUid(uid);
    }

    @Override
    public Page<Profile> findAll(Pageable pageable) {
        return profileRepository.findAllByCompletedTrue(pageable);
    }
}
