package br.com.car.rental.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.car.rental.model.Car;

@Repository
public interface CarRepository extends BaseRepository<Car> {
//	@Override
//    @Query("SELECT NEW Car(c.imageContentType, c.image) FROM Car c WHERE c.id = :id")
//    Optional<Car> findImageById(@Param("id") Long id);

	@Query("SELECT c FROM br.com.car.rental.model.Car c WHERE c.user.login = :login AND c.id = :id")
	Optional<Car> findByUserAndId(@Param("login") String login, @Param("id") Long id);

	@Query("SELECT c FROM br.com.car.rental.model.Car c WHERE c.user.login = :login")
	List<Car> findAllByUser(@Param("login") String login);

	@Modifying
	@Query("DELETE FROM br.com.car.rental.model.Car c WHERE c.id = :id")
	void delete(@Param("id") Long id);

	@Query("SELECT c FROM br.com.car.rental.model.Car c WHERE c.id <> :id AND c.licensePlate = :licensePlate")
	Collection<Car> findAllByLicensePlate(@Param("id") Long id, @Param("licensePlate") String licensePlate);
}
