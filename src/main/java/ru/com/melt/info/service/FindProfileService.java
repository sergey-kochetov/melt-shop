package ru.com.melt.info.service;

import ru.com.melt.info.entity.Profile;

public interface FindProfileService {

	Profile findByUid(String uid);
}
