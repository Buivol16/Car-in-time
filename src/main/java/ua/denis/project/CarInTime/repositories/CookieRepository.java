package ua.denis.project.CarInTime.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.denis.project.CarInTime.model.Cookie;

@Repository
public interface CookieRepository extends CrudRepository<Cookie, Long> {
    @Query(value = "SELECT * FROM cookies WHERE cookie_value = :value", nativeQuery = true)
    Cookie getByCookieValue(@Param("value") String cookieValue);
}
