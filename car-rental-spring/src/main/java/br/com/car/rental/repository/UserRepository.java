package br.com.car.rental.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.car.rental.model.User;

@Repository
public interface UserRepository extends BaseRepository<User> {
	List<User> findByFirstNameAndLastName(String firstName, String lastName);

	User findByLogin(String login);
	
//    @Query("SELECT u FROM User u WHERE u.email = :email")
//    Optional<User> findByEmail(@Param("email") String email);

//	@Override
//    @Query("SELECT NEW User(u.imageContentType, u.image) FROM User u WHERE u.id = :id")
//    Optional<User> findImageById(@Param("id") Long id);
}
