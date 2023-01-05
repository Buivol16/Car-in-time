package ua.denis.project.CarInTime.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.denis.project.CarInTime.model.Profile;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Long> {
    @Query(value = "SELECT * FROM profile WHERE user_id = :userId", nativeQuery = true)
    Profile getByUserId(@Param("userId") long userId);
}
