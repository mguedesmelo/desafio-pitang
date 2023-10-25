package br.com.car.rental.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.car.rental.model.User;

@Repository
public interface UserRepository extends BaseRepository<User> {
	List<User> findByFirstNameAndLastName(String firstName, String lastName);

	@Query("SELECT u FROM User u WHERE u.email = :email")
	List<User> findAllByEmail(@Param("email") String email);

	@Query("SELECT u FROM br.com.car.rental.model.User u WHERE u.login = :login")
	Optional<User> findByLogin(@Param("login") String login);

	@Query("SELECT u FROM User u WHERE u.login = :login")
	List<User> findAllByLogin(@Param("login") String login);
	
//    @Query("SELECT u FROM User u WHERE u.email = :email")
//    Optional<User> findByEmail(@Param("email") String email);

//	@Override
//    @Query("SELECT NEW User(u.imageContentType, u.image) FROM User u WHERE u.id = :id")
//    Optional<User> findImageById(@Param("id") Long id);
}
