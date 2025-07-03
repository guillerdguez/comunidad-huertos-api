package com.huertos.comunidad_huertos_api.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "plots")
public class Plot {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private Long sizeM2;
    @Column(nullable = false)
    private String soilType;
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

    public Plot() {

    }

    public Plot(UUID id, Long sizeM2, String soilType, boolean active, Garden garden, User owner, List<Plant> plants,
                List<Task> tasks) {
        super();
        this.id = id;
        this.sizeM2 = sizeM2;
        this.soilType = soilType;
        this.active = active;
        this.garden = garden;
        this.owner = owner;
        this.plants = plants;
        this.tasks = tasks;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getSizeM2() {
        return sizeM2;
    }

    public void setSizeM2(Long sizeM2) {
        this.sizeM2 = sizeM2;
    }

    public String getSoilType() {
        return soilType;
    }

    public void setSoilType(String soilType) {
        this.soilType = soilType;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Garden getGarden() {
        return garden;
    }

    public void setGarden(Garden garden) {
        this.garden = garden;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

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
        return "Plot [id=" + id + ", sizeM2=" + sizeM2 + ", soilType=" + soilType + ", active=" + active
                + ", garden=" + garden + ", owner=" + owner + ", plants=" + plants + ", tasks=" + tasks + "]";
    }

}
