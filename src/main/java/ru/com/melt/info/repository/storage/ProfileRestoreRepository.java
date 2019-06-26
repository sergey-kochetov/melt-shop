package ru.com.melt.info.repository.storage;

import org.springframework.data.repository.CrudRepository;

import ru.com.melt.info.entity.ProfileRestore;

public interface ProfileRestoreRepository extends CrudRepository<ProfileRestore, Long> {
	
	ProfileRestore findByToken(String token);
}
