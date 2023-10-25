package br.com.car.rental.repository;

import org.springframework.stereotype.Repository;

import br.com.car.rental.model.Car;

@Repository
public interface CarRepository extends BaseRepository<Car> {
//	@Override
//    @Query("SELECT NEW Car(c.imageContentType, c.image) FROM Car c WHERE c.id = :id")
//    Optional<Car> findImageById(@Param("id") Long id);
}
