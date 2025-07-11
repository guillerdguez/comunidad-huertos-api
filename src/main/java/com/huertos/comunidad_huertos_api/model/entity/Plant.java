package com.huertos.comunidad_huertos_api.model.entity;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.huertos.comunidad_huertos_api.model.entity.enums.PlantStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "plants")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plant {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column(nullable = false)
	private String species;
	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@CreationTimestamp
	private LocalDateTime plantedAt;
	@Column(nullable = true, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@CreationTimestamp
	private LocalDateTime harvestDate;
	@Enumerated(EnumType.STRING)
	private PlantStatus status;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "plot_id", nullable = false)
	private Plot plot;

	@Override
	public int hashCode() {
		return Objects.hash(harvestDate, id, plantedAt, plot, species, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Plant other = (Plant) obj;
		return Objects.equals(harvestDate, other.harvestDate) && Objects.equals(id, other.id)
				&& Objects.equals(plantedAt, other.plantedAt) && Objects.equals(plot, other.plot)
				&& Objects.equals(species, other.species) && status == other.status;
	}

	@Override
	public String toString() {
		return "Plant [id=" + id + ", species=" + species + ", plantedAt=" + plantedAt + ", harvestDate=" + harvestDate
				+ ", status=" + status + ", plot=" + plot + "]";
	}
}
