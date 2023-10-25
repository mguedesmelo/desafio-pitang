package br.com.car.rental.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, Long> {
	Optional<T> findImageById(@Param("id") Long id);
}
