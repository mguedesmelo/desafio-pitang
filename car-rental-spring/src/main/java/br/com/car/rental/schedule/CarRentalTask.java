package br.com.car.rental.schedule;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.car.rental.model.CarHistory;
import br.com.car.rental.model.User;
import br.com.car.rental.model.UserHistory;
import br.com.car.rental.repository.UserHistoryRepository;
import br.com.car.rental.repository.UserRepository;

@Component
public class CarRentalTask {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserHistoryRepository userHistoryRepository;

	/**
	 * Generates a history with users marked for deletion, if migrated successfully, 
	 * these users are physically deleted from the table. Fired every 2 minutes.
	 */
	@Scheduled(fixedRate = 120000)
	public void createUserHistory() {
		List<User> deletedUsers = userRepository.findAllDeleted();
		deletedUsers.forEach(user -> {
			UserHistory userHistory = new UserHistory();
			BeanUtils.copyProperties(user, userHistory);
			
			user.getCars().forEach(car -> {
				CarHistory carHistory = new CarHistory();
				BeanUtils.copyProperties(car, carHistory);
				userHistory.addCar(carHistory);
			});
			
			userHistoryRepository.save(userHistory);
		});
		userRepository.deleteAll(deletedUsers);
	}
	
	/**
	 * Creates the necessary data structure for the extra requirement. Fired every 2 minutes.
	 */
	@Scheduled(fixedRate = 120000)
	public void createDataStructure() {
		// TODO Creates the necessary data structure for the extra requirement
	}
}
