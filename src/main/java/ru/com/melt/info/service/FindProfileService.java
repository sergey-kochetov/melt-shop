package ru.com.melt.info.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.com.melt.info.entity.Profile;

public interface FindProfileService {

	Profile findByUid(String uid);

	Page<Profile> findAll(Pageable pageable);

	Iterable<Profile> findAllForIndexing();

	Page<Profile> findBySearchQuety(String query, Pageable pageable);
}
