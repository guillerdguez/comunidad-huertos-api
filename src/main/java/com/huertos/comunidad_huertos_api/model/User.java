package com.huertos.comunidad_huertos_api.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import com.huertos.comunidad_huertos_api.model.enums.UserAuthority;

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

@Entity
@Table(name = "users")
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
	private List<Participation> participations = new ArrayList<>();

	// 1:N Event (como creador)
	@OneToMany(mappedBy = "createdBy", fetch = FetchType.LAZY)
	private List<Event> createdEvents = new ArrayList<>();

	public User() {
	}

	public User(UUID id, String name, String email, UserAuthority authority, LocalDateTime joinedAt, List<Plot> plots,
			List<Task> tasks, List<Participation> participations, List<Event> createdEvents) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.authority = authority;
		this.joinedAt = joinedAt;
		this.plots = plots;
		this.tasks = tasks;
		this.participations = participations;
		this.createdEvents = createdEvents;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserAuthority getAuthority() {
		return authority;
	}

	public void setAuthority(UserAuthority authority) {
		this.authority = authority;
	}

	public LocalDateTime getJoinedAt() {
		return joinedAt;
	}

	public void setJoinedAt(LocalDateTime joinedAt) {
		this.joinedAt = joinedAt;
	}

	public List<Plot> getPlots() {
		return plots;
	}

	public void setPlots(List<Plot> plots) {
		this.plots = plots;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public List<Participation> getParticipations() {
		return participations;
	}

	public void setParticipations(List<Participation> participations) {
		this.participations = participations;
	}

	public List<Event> getCreatedEvents() {
		return createdEvents;
	}

	public void setCreatedEvents(List<Event> createdEvents) {
		this.createdEvents = createdEvents;
	}

	@Override
	public int hashCode() {
		return Objects.hash(authority, createdEvents, email, id, joinedAt, name, participations, plots, tasks);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return authority == other.authority && Objects.equals(createdEvents, other.createdEvents)
				&& Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(joinedAt, other.joinedAt) && Objects.equals(name, other.name)
				&& Objects.equals(participations, other.participations) && Objects.equals(plots, other.plots)
				&& Objects.equals(tasks, other.tasks);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", authority=" + authority + ", joinedAt="
				+ joinedAt + ", plots=" + plots + ", tasks=" + tasks + ", participations=" + participations
				+ ", createdEvents=" + createdEvents + "]";
	}

}
