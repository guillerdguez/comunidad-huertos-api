package com.huertos.comunidad_huertos_api.model.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "events")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column(nullable = false, unique = true, length = 50)
	private String title;
	@Column(nullable = false, unique = true, length = 50)
	private String description;
	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime eventDate;

	// N:1 Garden
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "garden_id", nullable = false)
	private Garden garden;

	// N:1 User (creador)
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "created_by", nullable = true)
	private User createdBy;

	// 1:N Participation
	@OneToMany(mappedBy = "event", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Participation> participations = new ArrayList<>();

	@Override
	public int hashCode() {
		return Objects.hash(createdBy, description, eventDate, garden, id, participations, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		return Objects.equals(createdBy, other.createdBy) && Objects.equals(description, other.description)
				&& Objects.equals(eventDate, other.eventDate) && Objects.equals(garden, other.garden)
				&& Objects.equals(id, other.id) && Objects.equals(participations, other.participations)
				&& Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", title=" + title + ", description=" + description + ", eventDate=" + eventDate
				+ ", garden=" + garden + ", createdBy=" + createdBy + ", participations=" + participations + "]";
	}
}
