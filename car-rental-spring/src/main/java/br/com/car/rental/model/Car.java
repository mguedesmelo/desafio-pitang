package br.com.car.rental.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@Table(name = "tb_car")
public class Car extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7877075265644281766L;

	public Car(Integer productionYear, String licensePlate, String model, CarColor color, User user) {
		super();
		this.productionYear = productionYear;
		this.licensePlate = licensePlate;
		this.model = model;
		this.color = color;
		user.addCar(this);
	}

	@Column(name = "production_year", length = 4, nullable = false)
	private Integer productionYear;

	@Column(name = "license_plate", length = 10, nullable = false)
	private String licensePlate;

	@Column(name = "model", length = 60, nullable = false)
	private String model;

	@Enumerated(EnumType.STRING)
	@Column(name = "color", length = 20, nullable = false)
	private CarColor color;

	@Fetch(FetchMode.SELECT)
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	@Override
	public String toString() {
		return "Car ["
				+ "productionYear=" + productionYear + 
				", licensePlate=" + licensePlate + 
				", model=" + model
				+ ", color=" + color + 
				"]";
	}
}
