package ua.denis.project.CarInTime.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.denis.project.CarInTime.model.User;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query(value = "SELECT * FROM user as f WHERE email = :email", nativeQuery = true)
    List<User> findByEmail(@Param(value = "email") String email);
}
