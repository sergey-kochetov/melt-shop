package ru.com.melt.info.repository.storage;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.com.melt.info.entity.ProfileRestore;

@Repository
public interface ProfileRestoreRepository extends CrudRepository<ProfileRestore, Long> {

    ProfileRestore findByToken(String token);
}
