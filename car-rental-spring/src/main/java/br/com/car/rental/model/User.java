package br.com.car.rental.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_user")
public class User extends BaseEntity implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4951439767748796428L;

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
	private List<Car> cars = new ArrayList<Car>(0);

	@Column(name = "is_active", nullable = false)
	private Boolean active = Boolean.TRUE;

	public User(String imageName, Long imageSize, String imageType, byte[] image) {
		super(imageName, imageSize, imageType, image);
	}

	public User(String firstName, String lastName, String email, LocalDate birthdate, 
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

	public void addCar(Car car) {
		this.cars.add(car);
		car.setUser(this);
	}

	public void removeCar(Car car) {
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

	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		return authentication.getAuthorities();
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	@JsonIgnore
	public String getUsername() {
		return this.login;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isEnabled() {
		return true;
	}
}
