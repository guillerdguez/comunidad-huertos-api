package com.huertos.comunidad_huertos_api.model.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "gardens")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Garden {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column(unique = true, nullable = false, length = 50)
	private String name;
	@Column(unique = true, nullable = false, length = 50)
	private String location;
	@Column(unique = true, nullable = false, length = 50)
	private String description;
	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@CreationTimestamp
	private LocalDateTime createdAt;

	// 1:N Plot
	@OneToMany(mappedBy = "garden", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Plot> plots = new ArrayList<>();

	// 1:N Event
	@OneToMany(mappedBy = "garden", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Event> events = new ArrayList<>();

	@Override
	public int hashCode() {
		return Objects.hash(createdAt, description, events, id, location, name, plots);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Garden other = (Garden) obj;
		return Objects.equals(createdAt, other.createdAt) && Objects.equals(description, other.description)
				&& Objects.equals(events, other.events) && Objects.equals(id, other.id)
				&& Objects.equals(location, other.location) && Objects.equals(name, other.name)
				&& Objects.equals(plots, other.plots);
	}

	@Override
	public String toString() {
		return "Garden [id=" + id + ", name=" + name + ", location=" + location + ", description=" + description
				+ ", createdAt=" + createdAt + ", plots=" + plots + ", events=" + events + "]";
	}
}
