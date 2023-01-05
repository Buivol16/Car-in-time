package ua.denis.project.CarInTime.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.denis.project.CarInTime.model.Feedback;

import java.util.List;

@Repository
public interface FeedbackRepository extends CrudRepository<Feedback, Long> {
    @Query(value = "SELECT * FROM feedback WHERE consumer_id = :consumerId", nativeQuery = true)
    List<Feedback> getByConsumerId(@Param("consumerId") long consumerId);
}
