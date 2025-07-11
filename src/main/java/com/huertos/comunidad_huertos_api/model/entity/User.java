package com.huertos.comunidad_huertos_api.model.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.huertos.comunidad_huertos_api.model.entity.enums.UserAuthority;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column(nullable = false, unique = true, length = 10)
	private String name;
	@Column(nullable = false, unique = true)
	private String email;
	@Enumerated(EnumType.STRING)
	private UserAuthority authority;
	// se tiene que autocrear
	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime joinedAt;

	// 1:N Plot
	@OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
	private List<Plot> plots = new ArrayList<>();

	// 1:N Task
	@OneToMany(mappedBy = "assignee", fetch = FetchType.LAZY)

	private List<Task> tasks = new ArrayList<>();

	// 1:N Participation
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Participation> participations = new ArrayList<>();

	// 1:N Event (como creador)
	@OneToMany(mappedBy = "createdBy", fetch = FetchType.LAZY)
	private List<Event> createdEvents = new ArrayList<>();
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(authority, createdEvents, email, id, joinedAt, name, participations, plots, tasks);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		User other = (User) obj;
//		return authority == other.authority && Objects.equals(createdEvents, other.createdEvents)
//				&& Objects.equals(email, other.email) && Objects.equals(id, other.id)
//				&& Objects.equals(joinedAt, other.joinedAt) && Objects.equals(name, other.name)
//				&& Objects.equals(participations, other.participations) && Objects.equals(plots, other.plots)
//				&& Objects.equals(tasks, other.tasks);
//	}
//
//	@Override
//	public String toString() {
//		return "User [id=" + id + ", name=" + name + ", email=" + email + ", authority=" + authority + ", joinedAt="
//				+ joinedAt + ", plots=" + plots + ", tasks=" + tasks + ", participations=" + participations
//				+ ", createdEvents=" + createdEvents + "]";
//	}

}
