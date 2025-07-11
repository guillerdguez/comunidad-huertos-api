package com.huertos.comunidad_huertos_api.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "plots")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plot {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column(nullable = false)
	private Long sizeM2;
	@Column(nullable = false)
	private String soilType;

//    private Boolean aspersor;
	@Column(nullable = false)
	private boolean active;
	// N:1 Garden
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "garden_id", nullable = false)
	private Garden garden;

	// N:1 User (propietario), opcional
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner_id")
	private User owner;

	// 1:N Plant
	@OneToMany(mappedBy = "plot", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Plant> plants = new ArrayList<>();

	// 1:N Task
	@OneToMany(mappedBy = "plot", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Task> tasks = new ArrayList<>();

	@Override
	public int hashCode() {
		return Objects.hash(garden, id, active, owner, plants, sizeM2, soilType, tasks);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Plot other = (Plot) obj;
		return Objects.equals(garden, other.garden) && Objects.equals(id, other.id) && active == other.active
				&& Objects.equals(owner, other.owner) && Objects.equals(plants, other.plants)
				&& Objects.equals(sizeM2, other.sizeM2) && Objects.equals(soilType, other.soilType)
				&& Objects.equals(tasks, other.tasks);
	}

	@Override
	public String toString() {
		return "Plot [id=" + id + ", sizeM2=" + sizeM2 + ", soilType=" + soilType + ", active=" + active + ", garden="
				+ garden + ", owner=" + owner + ", plants=" + plants + ", tasks=" + tasks + "]";
	}

}
