package br.com.car.rental.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_car_history")
public class CarHistory {
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "creation_date", nullable = false)
	@JsonProperty("createdAt")
	private LocalDateTime creationDate = LocalDateTime.now();

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
	private UserHistory user;

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

	public CarHistory(Integer productionYear, String licensePlate, String model, 
			CarColor color, UserHistory user) {
		super();
		this.productionYear = productionYear;
		this.licensePlate = licensePlate;
		this.model = model;
		this.color = color;
		user.addCar(this);
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
