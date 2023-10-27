package br.com.car.rental.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_user_history")
public class UserHistory {
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "creation_date", nullable = false)
	@JsonProperty("createdAt")
	private LocalDateTime creationDate = LocalDateTime.now();
	
	@Column(name = "first_name", length = 60, nullable = false)
	private String firstName;

	@Column(name = "last_name", length = 60, nullable = false)
	private String lastName;

	@Column(name = "email", length = 255, nullable = false)
	private String email;

	@Column(name = "birthday")
	private LocalDate birthDay = LocalDate.now();

	@Column(name = "last_login")
	private LocalDateTime lastLogin;

	@Column(name = "login", length = 20, nullable = false)
	private String login;

	@Column(name = "password", length = 500, nullable = false)
	private String password;

	@Column(name = "phone", length = 20, nullable = false)
	private String phone;

	// FIXME Configure to lazy
	@Fetch(FetchMode.SELECT)
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private List<CarHistory> cars = new ArrayList<CarHistory>(0);

	@Column(name = "image_name", length = 255)
	@JsonIgnore
	private String imageName;

	@Column(name = "image_size")
	@JsonIgnore
	private Long imageSize;

	@Column(name = "image_type", length = 50)
	@JsonIgnore
	private String imageType;

	@Lob
	@Column(name = "image")
	@JsonIgnore
	private byte[] image;

	public UserHistory(String firstName, String lastName, String email, LocalDate birthdate, 
			String login, String password, String phone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.birthDay = birthdate;
		this.login = login;
		this.password = password;
		this.phone = phone;
	}

	public void addCar(CarHistory car) {
		this.cars.add(car);
		car.setUser(this);
	}

	public void removeCar(CarHistory car) {
		this.cars.remove(car);
		car.setUser(null);
	}

	@Override
	public String toString() {
		return "User ["
				+ "firstName=" + firstName + 
				", lastName=" + lastName + 
				", email=" + email + 
				", birthDay=" + birthDay + 
				", login=" + login + 
				", password=" + password + 
				", phone=" + phone + 
				", cars=" + cars + 
				"]";
	}
}
