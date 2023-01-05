package ua.denis.project.CarInTime.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.denis.project.CarInTime.model.Offer;

import java.util.List;
@Repository
public interface OfferRepository extends CrudRepository<Offer, Long> {
    @Query(value = "SELECT * FROM offer WHERE author_id = :authorId", nativeQuery = true)
    List<Offer> getByAuthorId(@Param("authorId") long authorId);
}
