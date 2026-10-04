//package in.chandan.CampusConnect.config;
//
//import in.chandan.CampusConnect.entity.Club;
//import in.chandan.CampusConnect.entity.Event;
//import in.chandan.CampusConnect.entity.Registration;
//import in.chandan.CampusConnect.entity.Users;
//import in.chandan.CampusConnect.enums.Role;
//import in.chandan.CampusConnect.repository.ClubRepository;
//import in.chandan.CampusConnect.repository.EventRepository;
//import in.chandan.CampusConnect.repository.RegistrationRepository;
//import in.chandan.CampusConnect.repository.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.time.LocalDate;
//import java.util.*;
//
//@Configuration
//public class DataSeeder {
//
//    @Bean
//    CommandLineRunner seedDatabase(
//            UserRepository userRepository,
//            ClubRepository clubRepository,
//            EventRepository eventRepository,
//            RegistrationRepository registrationRepository,
//            PasswordEncoder passwordEncoder) {
//
//        return args -> {
//
//            // Don't seed again every time the application starts
//            if (userRepository.count() > 0) {
//                System.out.println("Database already contains data. Skipping seeding.");
//                return;
//            }
//
//            System.out.println("Starting database seeding...");
//
//            // =====================================================
//            // 1. USERS
//            // =====================================================
//
//            List<Users> users = new ArrayList<>();
//
//            // Admins
//            for (int i = 1; i <= 5; i++) {
//
//                Users admin = new Users();
//
//                admin.setUid(1000L + i);
//                admin.setName("Admin " + i);
//                admin.setPassword(
//                        passwordEncoder.encode("password123")
//                );
//                admin.setRole(Role.ADMIN);
//
//                users.add(admin);
//            }
//
//            // Students
//            for (int i = 1; i <= 495; i++) {
//
//                Users student = new Users();
//
//                student.setUid(2000L + i);
//                student.setName("Student " + i);
//                student.setPassword(
//                        passwordEncoder.encode("password123")
//                );
//                student.setRole(Role.STUDENT);
//
//                users.add(student);
//            }
//
//            userRepository.saveAll(users);
//
//            System.out.println("Users created: " + users.size());
//
//
//            // =====================================================
//            // 2. CLUBS
//            // =====================================================
//
//            List<Users> students = users.stream()
//                    .filter(user -> user.getRole() == Role.STUDENT)
//                    .toList();
//
//            List<Club> clubs = new ArrayList<>();
//
//            String[] clubNames = {
//                    "Coding Club",
//                    "Robotics Club",
//                    "AI Club",
//                    "Cyber Security Club",
//                    "Photography Club",
//                    "Music Club",
//                    "Dance Club",
//                    "Entrepreneurship Club",
//                    "Gaming Club",
//                    "Debate Club",
//                    "Literary Club",
//                    "Sports Club",
//                    "Blockchain Club",
//                    "Cloud Computing Club",
//                    "IoT Club"
//            };
//
//            String[] genres = {
//                    "Technology",
//                    "Robotics",
//                    "Artificial Intelligence",
//                    "Cyber Security",
//                    "Photography",
//                    "Music",
//                    "Dance",
//                    "Business",
//                    "Gaming",
//                    "Debate",
//                    "Literature",
//                    "Sports",
//                    "Blockchain",
//                    "Cloud",
//                    "IoT"
//            };
//
//            // 100 clubs
//            for (int i = 0; i < 100; i++) {
//
//                Club club = new Club();
//
//                club.setName(
//                        clubNames[i % clubNames.length]
//                                + " " + (i + 1)
//                );
//
//                club.setGenre(
//                        genres[i % genres.length]
//                );
//
//                // One student owns one club
//                club.setUser(students.get(i));
//
//                clubs.add(club);
//            }
//
//            clubRepository.saveAll(clubs);
//
//            System.out.println("Clubs created: " + clubs.size());
//
//
//            // =====================================================
//            // 3. EVENTS
//            // =====================================================
//
//            List<Event> events = new ArrayList<>();
//
//            String[] eventNames = {
//                    "Hackathon",
//                    "Spring Boot Workshop",
//                    "Java Workshop",
//                    "AI Seminar",
//                    "Machine Learning Workshop",
//                    "Cyber Security Seminar",
//                    "Robotics Competition",
//                    "Photography Walk",
//                    "Tech Fest",
//                    "Startup Meetup",
//                    "Cloud Workshop",
//                    "Web Development Bootcamp",
//                    "Data Science Workshop",
//                    "Competitive Programming Contest",
//                    "Open Source Meetup"
//            };
//
//            // 100 events
//            for (int i = 0; i < 100; i++) {
//
//                Event event = new Event();
//
//                event.setName(
//                        eventNames[i % eventNames.length]
//                                + " " + (i + 1)
//                );
//
//                LocalDate startDate =
//                        LocalDate.of(2026, 10, 1)
//                                .plusDays(i);
//
//                event.setStart(startDate);
//
//                event.setEnd(startDate.plusDays(1));
//
//                event.setSeats(50 + (i % 10) * 25);
//
//                events.add(event);
//            }
//
//            eventRepository.saveAll(events);
//
//            System.out.println("Events created: " + events.size());
//
//
//            // =====================================================
//            // 4. REGISTRATIONS
//            // =====================================================
//
//            List<Registration> registrations = new ArrayList<>();
//
//            Set<String> registeredPairs = new HashSet<>();
//
//            Random random = new Random(42);
//
//            // Generate around 5000 registrations
//            while (registrations.size() < 5000) {
//
//                Users student =
//                        students.get(
//                                random.nextInt(students.size())
//                        );
//
//                Event event =
//                        events.get(
//                                random.nextInt(events.size())
//                        );
//
//                /*
//                 * Prevent the same student from registering
//                 * for the same event twice.
//                 */
//                String key =
//                        student.getUid()
//                                + "-" +
//                                event.getId();
//
//                if (registeredPairs.contains(key)) {
//                    continue;
//                }
//
//                Registration registration =
//                        new Registration();
//
//                registration.setUid(
//                        student.getUid()
//                );
//
//                registration.setEvent(event);
//
//                registrations.add(registration);
//
//                registeredPairs.add(key);
//            }
//
//            registrationRepository.saveAll(registrations);
//
//            System.out.println(
//                    "Registrations created: "
//                            + registrations.size()
//            );
//
//            System.out.println("=================================");
//            System.out.println("DATABASE SEEDING COMPLETED");
//            System.out.println("Users: " + users.size());
//            System.out.println("Clubs: " + clubs.size());
//            System.out.println("Events: " + events.size());
//            System.out.println(
//                    "Registrations: "
//                            + registrations.size()
//            );
//            System.out.println("=================================");
//        };
//    }
//}