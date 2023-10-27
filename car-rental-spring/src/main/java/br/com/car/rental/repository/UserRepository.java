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

	@Query("SELECT u FROM User u WHERE u.id <> :id AND u.email = :email")
	List<User> findAllByEmail(@Param("id") Long id, @Param("email") String email);

	@Query("SELECT u FROM User u WHERE u.id <> :id AND u.login = :login")
	List<User> findAllByLogin(@Param("id") Long id, @Param("login") String login);
	
	@Query("SELECT u FROM User u WHERE u.login = :login")
	Optional<User> findByLogin(@Param("login") String login);
	
	@Query("SELECT u FROM User u WHERE u.active = FALSE")
	List<User> findAllDeleted();

    @Query("SELECT NEW User(u.imageName, u.imageSize, u.imageType, u.image) "
    		+ "FROM User u WHERE u.id = :id")
    Optional<User> findImageById(@Param("id") Long id);
}
