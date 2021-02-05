package ru.sfedu.dblabs;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.sfedu.dblabs.csvModel.CsvCity;
import ru.sfedu.dblabs.model.*;
import ru.sfedu.dblabs.repo.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@SpringBootApplication
public class DblabsApplication {

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(DblabsApplication.class, args);

        fillDb(context);

    }

    private static void fillDb(ConfigurableApplicationContext context) throws IOException {
        CityRepo cityRepo = context.getBean(CityRepo.class);
        EmploeeRepo emploeeRepo = context.getBean(EmploeeRepo.class);
        ServiceTypeRepo serviceTypeRepo = context.getBean(ServiceTypeRepo.class);
        ClientRepo clientRepo = context.getBean(ClientRepo.class);
		FederalServiceRepo federalServiceRepo = context.getBean(FederalServiceRepo.class);
		MunicipalServiceRepo municipalServiceRepo = context.getBean(MunicipalServiceRepo.class);
		RegionalServiceRepo regionalServiceRepo = context.getBean(RegionalServiceRepo.class);
		RequestsRepo requestsRepo = context.getBean(RequestsRepo.class);


        List<City> cities = fillCitiesAndGet(cityRepo);

        List<ServiceType> serviceTypes = Arrays.asList(
                new ServiceType(getId(), "федеральный"),
                new ServiceType(getId(), "муниципальный"),
                new ServiceType(getId(), "региональный")
        );

        serviceTypeRepo.saveAll(serviceTypes);

        List<String> positions = Arrays.asList("Директор", "Менеджер", "Мл.специалист", "Администратор", "Специалист", "Зам.директора", "Бухгалтер", "Консультант");

        File lastnamesFile = new File(ClassLoader.getSystemResources("lastnames.txt").nextElement().getFile());
        File firstnamesFile = new File(ClassLoader.getSystemResources("firstnames.txt").nextElement().getFile());
        List<String> firstnamesList = Files.readAllLines(firstnamesFile.toPath());
        List<String> lastnamesList = Files.readAllLines(lastnamesFile.toPath());

        List<String> names = new ArrayList<>();

        for (int i = 0; i < firstnamesList.size(); i++) {
            String firsfname = firstnamesList.get(i);

            for (int j = 0; j < lastnamesList.size(); j++) {

                String lastname = lastnamesList.get(j);
                String name = lastname + " " + firsfname;
                names.add(name);
            }
        }
        Collections.shuffle(names);

        List<Emploee> emploees = fillEmploeesAndGet(emploeeRepo, positions, names, serviceTypes, cities);

        List<Client> clients = fillClientsAndGet(names, cities, clientRepo);

        List<FederalService> fedServ = fillServiceFederalAndGet(names, positions, federalServiceRepo);


        List<MunicipalService> munServ = new ArrayList<>();
        List<RegionalService> regServ = new ArrayList<>();

        List<Request> requests = fillRequestsAndGet(clients, fedServ, munServ, regServ, emploees, requestsRepo);
    }

    private static List<Request> fillRequestsAndGet(List<Client> clients, List<FederalService> federalServices, List<MunicipalService> munServ, List<RegionalService> rigionServ, List<Emploee> emploees, RequestsRepo requestsRepo) {
        List<Request> result = new ArrayList<>();

        Random rnd = new Random();

        for (int i = 0; i < 1000; i++) {
            long id = getId();
            boolean isActive = rnd.nextBoolean();
            Client client = clients.get(rnd.nextInt(clients.size()));
            long cityId = client.getCityId();
            List<Emploee> emploeeInCity = emploees.stream().filter(it -> it.getCityId() == cityId).collect(Collectors.toList());
            Emploee emploee = emploeeInCity.get(rnd.nextInt(emploeeInCity.size()));


            long employeeId = emploee.getId();
            long clientId = client.getId();

            long serviceId;
            String serviceType;

//            int servType = rnd.nextInt(3);
            int servType = 0;
            if (servType == 0) {
                serviceId = federalServices.get(rnd.nextInt(federalServices.size())).getId();
                serviceType = "федеральный";
            } else if (servType == 1) {
                serviceId = munServ.get(rnd.nextInt(munServ.size())).getId();
                serviceType = "муниципальный";
            } else {
                serviceId = rigionServ.get(rnd.nextInt(rigionServ.size())).getId();
                serviceType = "региональный";
            }

            long dateStart = getRandDate();

            Request request = new Request(id, isActive, clientId, serviceId, serviceType, employeeId, dateStart);
            result.add(request);
            requestsRepo.save(request);
        }

        return result;
    }

    private static List<FederalService> fillServiceFederalAndGet(List<String> names, List<String> positions, FederalServiceRepo federalServiceRepo) {
        List<FederalService> result = new ArrayList<>();

        Random rnd = new Random();

        for (int i = 0; i < 1000; i++) {
            long id = getId();
            String name = names.get(rnd.nextInt(names.size()));
            long duration = 86400000 * rnd.nextInt(15);
            String emploeePosition = positions.get(rnd.nextInt(positions.size()));
            String type = "федеральный";

            FederalService federalService = new FederalService(id, name, duration, emploeePosition, type);
            result.add(federalService);
            federalServiceRepo.save(federalService);
        }
        return result;
    }

    private static List<Client> fillClientsAndGet(List<String> names, List<City> cities, ClientRepo clientRepo) {
        List<Client> clients = new ArrayList<>();

        Random rnd = new Random();

        for (int i = 0; i < cities.size(); i++) {
            long id = getId();
            int age = 20 + rnd.nextInt(60);
            long cityId = cities.get(i).getId();
            long birthDate = getRandDate();
            String passportNumber = String.valueOf(rnd.nextInt(8999) + 1000);
            String passportSereis = String.valueOf(rnd.nextInt(899999) + 100000);
            String name = names.get(rnd.nextInt(names.size()));
            boolean hasConvictions = rnd.nextBoolean();

            Client client = new Client(id, age, cityId, birthDate, passportNumber, passportSereis, name, hasConvictions);
            clients.add(client);
            clientRepo.save(client);
        }

        return clients;
    }

    private static List<Emploee> fillEmploeesAndGet(EmploeeRepo emploeeRepo, List<String> positions, List<String> names, List<ServiceType> serviceTypes, List<City> cities) {
        List<Emploee> result = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < cities.size(); i++) {
            long id = getId();
            String name = names.get(random.nextInt(names.size()));
            int age = random.nextInt(60) + 20;
            String phoneNumber = String.valueOf(random.nextInt(10))
                    + String.valueOf(random.nextInt(10))
                    + String.valueOf(random.nextInt(10))
                    + String.valueOf(random.nextInt(10))
                    + String.valueOf(random.nextInt(10))
                    + String.valueOf(random.nextInt(10));
            String position = positions.get(random.nextInt(positions.size()));
            String department = serviceTypes.get(random.nextInt(serviceTypes.size())).getType();
            long cityId = cities.get(i).getId();

            Emploee emploee = new Emploee(id, name, age, phoneNumber, position, department, cityId);
            emploeeRepo.save(emploee);
            result.add(emploee);
        }
        return result;
    }

    private static long getId() {
        return ThreadLocalRandom.current().nextLong(Long.MAX_VALUE);
    }

    private static List<City> fillCitiesAndGet(CityRepo cityRepo) throws IOException {
        List<City> result = new ArrayList<>();

        File cityFile = new File(ClassLoader.getSystemResources("city.csv").nextElement().getFile());
        try (Reader reader = new FileReader(cityFile)) {
            HeaderColumnNameMappingStrategy<CsvCity> ms = new HeaderColumnNameMappingStrategy<>();
            ms.setType(CsvCity.class);

            CsvToBean<CsvCity> cb = new CsvToBeanBuilder<CsvCity>(reader)
                    .withMappingStrategy(ms)
                    .build();

            List<CsvCity> cities = cb.parse();
            System.out.println(cities);
            cities.stream().forEach(it -> {
                City city = new City(Integer.parseInt(it.getCity_id()), it.getName(), getRandDate());
                result.add(city);
                cityRepo.save(city);
            });
        }

        return result;
    }

    public static long getRandDate() {
        Random rnd = new Random();
        long ms = -946771200000L + (Math.abs(rnd.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000));
        return ms;
    }

}
