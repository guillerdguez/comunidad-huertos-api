package com.huertos.comunidad_huertos_api;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.huertos.comunidad_huertos_api.model.entity.Event;
import com.huertos.comunidad_huertos_api.model.entity.Garden;
import com.huertos.comunidad_huertos_api.model.entity.Participation;
import com.huertos.comunidad_huertos_api.model.entity.Plant;
import com.huertos.comunidad_huertos_api.model.entity.Plot;
import com.huertos.comunidad_huertos_api.model.entity.Task;
import com.huertos.comunidad_huertos_api.model.entity.User;
import com.huertos.comunidad_huertos_api.model.entity.enums.ParticipationStatus;
import com.huertos.comunidad_huertos_api.model.entity.enums.PlantStatus;
import com.huertos.comunidad_huertos_api.model.entity.enums.TaskStatus;
import com.huertos.comunidad_huertos_api.model.entity.enums.UserAuthority;
import com.huertos.comunidad_huertos_api.repository.EventRepository;
import com.huertos.comunidad_huertos_api.repository.GardenRepository;
import com.huertos.comunidad_huertos_api.repository.ParticipationRepository;
import com.huertos.comunidad_huertos_api.repository.PlantRepository;
import com.huertos.comunidad_huertos_api.repository.PlotRepository;
import com.huertos.comunidad_huertos_api.repository.TaskRepository;
import com.huertos.comunidad_huertos_api.repository.UserRepository;

@SpringBootApplication
public class HuertosComunitariosApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HuertosComunitariosApiApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(UserRepository userRepo, GardenRepository gardenRepo, PlotRepository plotRepo,
			PlantRepository plantRepo, TaskRepository taskRepo, EventRepository eventRepo,
			ParticipationRepository participationRepo) {
		return args -> {

			List<User> users = new ArrayList<>();
			for (int i = 1; i <= 10; i++) {
				User u = new User();
				u.setName("user" + i);
				u.setEmail("user" + i + "@example.com");
				u.setAuthority(i % 2 == 0 ? UserAuthority.ADMIN : UserAuthority.MEMBER);
				u.setJoinedAt(LocalDateTime.now().minusDays(i));
				users.add(u);
			}
			userRepo.saveAll(users);

			List<Garden> gardens = new ArrayList<>();
			for (int i = 1; i <= 10; i++) {
				Garden g = new Garden();
				g.setName("Garden " + i);
				g.setLocation("Location " + i);
				g.setDescription("Description for garden " + i);
				g.setCreatedAt(LocalDateTime.now().minusDays(i * 2));
				gardens.add(g);
			}
			gardenRepo.saveAll(gardens);

			List<Plot> plots = new ArrayList<>();
			for (int i = 1; i <= 10; i++) {
				Plot p = new Plot();
				p.setSizeM2((long) (i * 5));
				p.setSoilType(i % 2 == 0 ? "Clay" : "Loam");
				p.setActive(i % 3 != 0);
				p.setGarden(gardens.get((i - 1) % gardens.size()));
				p.setOwner(users.get((i - 1) % users.size()));
				plots.add(p);
			}
			plotRepo.saveAll(plots);

			long clayActive = plots.stream().filter(p -> p.isActive() && "Clay".equals(p.getSoilType())).count();
			long loamActive = plots.stream().filter(p -> p.isActive() && "Loam".equals(p.getSoilType())).count();

			List<Plot> extraPlots = new ArrayList<>();
			int index = 1;

			while (clayActive < 6) {
				Plot p = new Plot();
				p.setSizeM2(50L + index);
				p.setSoilType("Clay");
				p.setActive(true);
				p.setGarden(gardens.get(index % gardens.size()));
				p.setOwner(users.get(index % users.size()));
				extraPlots.add(p);
				clayActive++;
				index++;
			}

			while (loamActive < 6) {
				Plot p = new Plot();
				p.setSizeM2(60L + index);
				p.setSoilType("Loam");
				p.setActive(true);
				p.setGarden(gardens.get(index % gardens.size()));
				p.setOwner(users.get(index % users.size()));
				extraPlots.add(p);
				loamActive++;
				index++;
			}

			for (int i = 0; i < 6; i++) {
				Plot p = new Plot();
				p.setSizeM2(70L + i);
				p.setSoilType(i % 2 == 0 ? "Clay" : "Loam");
				p.setActive(true);
				p.setGarden(gardens.get(0));
				p.setOwner(null);
				extraPlots.add(p);
			}

			plots.addAll(extraPlots);
			plotRepo.saveAll(extraPlots);

			List<Plant> plants = new ArrayList<>();
			for (int i = 1; i <= 10; i++) {
				Plant pl = new Plant();
				pl.setSpecies("Species " + i);
				pl.setPlantedAt(LocalDateTime.now().minusDays(i));
				pl.setHarvestDate(i % 2 == 0 ? LocalDateTime.now().plusDays(i) : null);
				pl.setStatus(
						i % 3 == 0 ? PlantStatus.HARVESTED : i % 2 == 0 ? PlantStatus.GROWING : PlantStatus.SEEDLING);
				pl.setPlot(plots.get((i - 1) % plots.size()));
				plants.add(pl);
			}
			plantRepo.saveAll(plants);

			List<Task> tasks = new ArrayList<>();
			for (int i = 1; i <= 10; i++) {
				Task t = new Task();
				t.setDescription("Task description " + i);
				t.setDueDate(LocalDateTime.now().plusDays(i));
				t.setStatus(i % 2 == 0 ? TaskStatus.DONE : TaskStatus.PENDING);
				t.setPlot(plots.get((i - 1) % plots.size()));
				t.setAssignee(users.get((i - 1) % users.size()));
				tasks.add(t);
			}
			for (int i = 1; i <= 5; i++) {
				Task t = new Task();
				t.setDescription("Overdue task " + i);
				t.setDueDate(LocalDateTime.now().minusDays(i));
				t.setStatus(TaskStatus.PENDING);
				t.setPlot(plots.get((i - 1) % plots.size()));
				t.setAssignee(users.get((i - 1) % users.size()));
				tasks.add(t);
			}
			taskRepo.saveAll(tasks);

			List<Event> events = new ArrayList<>();
			for (int i = 1; i <= 10; i++) {
				Event e = new Event();
				e.setTitle("Event " + i);
				e.setDescription("Event desc " + i);
				e.setEventDate(LocalDateTime.now().plusDays(i * 2));
				e.setGarden(gardens.get((i - 1) % gardens.size()));
				e.setCreatedBy(users.get((i - 1) % users.size()));
				events.add(e);
			}
			eventRepo.saveAll(events);

			List<Participation> parts = new ArrayList<>();
			for (int i = 1; i <= 10; i++) {
				Participation p = new Participation();
				p.setEvent(events.get((i - 1) % events.size()));
				p.setUser(users.get((i - 1) % users.size()));
				p.setStatus(i % 2 == 0 ? ParticipationStatus.CONFIRMED : ParticipationStatus.CANCELED);
				parts.add(p);
			}
			participationRepo.saveAll(parts);
		};
	}
}
