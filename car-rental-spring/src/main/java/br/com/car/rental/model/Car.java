package br.com.car.rental.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "tb_car")
public class Car extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7877075265644281766L;

	public Car() {
		super();
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
	@JsonIgnore
	private User user;

	@Column(name = "image_name", length = 255)
	private String imageName;

	@Column(name = "image_size")
	private Long imageSize;

	@Column(name = "image_type", length = 50)
	private String imageType;

	@Lob
	@Column(name = "image")
	private byte[] image;

	// FIXME Configure to lazy
	@Fetch(FetchMode.SELECT)
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "car_id")
	private List<CarUsageHistory> carUsageHistory = new ArrayList<CarUsageHistory>(0);

	public Car(Integer productionYear, String licensePlate, String model, CarColor color, User user) {
		super();
		this.productionYear = productionYear;
		this.licensePlate = licensePlate;
		this.model = model;
		this.color = color;
		user.addCar(this);
	}

	public Car(String imageName, Long imageSize, String imageType, byte[] image) {
		super();
		this.imageName = imageName;
		this.imageSize = imageSize;
		this.imageType = imageType;
		this.image = image;
	}

	public void addCarUsageHistory(CarUsageHistory carUsageHistory) {
		this.carUsageHistory.add(carUsageHistory);
		carUsageHistory.setCar(this);
	}

	public void removeCarUsageHistory(CarUsageHistory carUsageHistory) {
		this.carUsageHistory.remove(carUsageHistory);
		carUsageHistory.setCar(null);
	}

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
