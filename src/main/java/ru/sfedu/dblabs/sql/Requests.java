package ru.sfedu.dblabs.sql;

public class Requests {

    ///////////// 1 вывести клиентов с именами Андрей или Никита возрастом от 25, которым отказали в услуге
    public static final String REQUEST_1 = "with people(name, id) as (\n" +
            "SELECT name, id FROM client \n" +
            "where (name like '%Андрей%' or name like '%Никита%') and age >= 25\n" +
            "),\n" +
            "federal (name, service, type) as (\n" +
            "SELECT p.name, sr.name, service_type  FROM request rq\n" +
            "\tjoin federal_service sr on sr.type = rq.service_type and rq.service_id = sr.id\n" +
            "\tjoin people p on client_id = p.id\n" +
            "where rejected = 1\n" +
            "),\n" +
            "municipal (name, service, type) as (\n" +
            "SELECT p.name, sr.name, service_type  FROM request rq\n" +
            "\tjoin municipal_service sr on sr.type = rq.service_type and rq.service_id = sr.id\n" +
            "\tjoin people p on client_id = p.id\n" +
            "where rejected = 1\n" +
            "),\n" +
            "\n" +
            "regional (name, service, type) as (\n" +
            "SELECT p.name, sr.name, service_type  FROM request rq\n" +
            "\tjoin regional_service sr on sr.type = rq.service_type and rq.service_id = sr.id\n" +
            "\tjoin people p on client_id = p.id\n" +
            "where rejected = 1\n" +
            ")\n" +
            "\n" +
            "select name, service, type from federal union \n" +
            "select name, service, type from municipal union \n" +
            "select name, service, type from regional";

    /////////// 2 вывести города, в которых обслуживались клиенты старше 60 лет сотрудниками младше 25 лет
    public static final String REQUEST_2 = "select DISTINCT c.name city from city c\n" +
            "\tjoin client cl on cl.city_id = c.id\n" +
            "\tjoin request rq on rq.client_id = cl.id\n" +
            "\tjoin emploee e on rq.employee_id = e.id \n" +
            "where cl.age > 60 and e.age < 25 \n" +
            "group by c.name";


    // 3 вывести клиентов,у которых номер паспорта начинается на "25", из городов начинающихся на А и Р, получивших региональную услугу,
    public static final String REQUEST_3 = "select DISTINCT cl.name from city c \n" +
            "\tjoin client cl on c.id = cl.city_id\n" +
            "\tjoin request rq on rq.client_id = cl.id\n" +
            "where c.name like 'А%' or c.name like 'Р%' and cl.passport_number like '25%' and rq.service_type = 'федеральный'";

    // 4 вывести региональные и муницыпальные услуги, которые оказали сотрудники старше 30 из города Москва, с должностью бухгалтер
    public static final String REQUEST_4 = "with moscow as (\n" +
            "select id from city where name = 'Москва'\n" +
            "),\n" +
            "federal (name) as (\n" +
            "select sr.name from federal_service sr\n" +
            "\tjoin request rq on rq.service_id = sr.id\n" +
            "\tjoin emploee e on e.id = rq.employee_id\n" +
            "where e.age > 30 and e.city_id = (select id from moscow)\n" +
            "),\n" +
            "municipal (name) as (\n" +
            "select sr.name from municipal_service sr\n" +
            "\tjoin request rq on rq.service_id = sr.id\n" +
            "\tjoin emploee e on e.id = rq.employee_id\n" +
            "where e.age > 30 and e.city_id = (select id from moscow)\n" +
            ")\n" +
            "\n" +
            "select * from federal \n" +
            "union\n" +
            "select * from municipal ";

    // 5 вывести сотрудников и клиентов старше 40 лет, которые родились в городе с именем на букву А
    public static final String REQUEST_5 = "select cl.name clint, e.name employee, c.name from client cl\n" +
            "join request rq on rq.client_id = cl.id\n" +
            "join emploee e on e.id = rq.employee_id\n" +
            "join city c on e.city_id = c.id\n" +
            "where e.age > 40 and cl.age > 40 and c.name like 'А%'";


    // 6 вывести клиентов младше 50 лет из города Ростов, которых по муниципальной услуге обслуживал работник старше 25 лет
    public static final String REQUEST_6 = "select cl.name clint, e.name employee, c.name from client cl\n" +
            "join request rq on rq.client_id = cl.id\n" +
            "join emploee e on e.id = rq.employee_id\n" +
            "join city c on e.city_id = c.id\n" +
            "where e.age > 25 and cl.age < 50 and c.name = 'Рио-де-Жанейро'";

    // 7 вывести федеральные услуги, которые оказали за 5 и более дней сотрудники с телефоном содержащим цифру 5 клиентам с первой цифрой паспорта 5
    public static final String REQUEST_7 = "with clients(id, name, passport) as (\n" +
            "SELECT id, name, passport_number  FROM client\n" +
            "where passport_number like '5%'\n" +
            "),\n" +
            "employees(id, name, phone_number) as (\n" +
            "select id, name, phone_number from emploee\n" +
            "where phone_number like '%5%'\n" +
            "),\n" +
            "federal (employee, client, service) as (\n" +
            "SELECT e.name, cl.name, sr.name FROM request rq\n" +
            "\tjoin federal_service sr on sr.type = rq.service_type and rq.service_id = sr.id\n" +
            "\tjoin employees e on employee_id = e.id\n" +
            "\tjoin clients cl on client_id = cl.id\n" +
            "where duration > 5\n" +
            ")\n" +
            "select * from federal";

    // 8 вывести директоров центров старше 50 из города с названем длиннее 6 букв, которые оказали муниципальные услуги клиентам младше 50 без судимости
    public static final String REQUEST_8 = "select e.name e_name, c.name c_name from city c\n" +
            "join emploee e on c.id = e.city_id\n" +
            "join request rq on rq.employee_id = e.id\n" +
            "join client cl on cl.id = rq.client_id\n" +
            "where e.position = 'Директор' and cl.age > 50 and cl.has_convictions = 0 and len(c.name) > 6\n" +
            "and rq.service_id in (select municipal_service.id from municipal_service)";

    // 9 вывести неактивные заявки,где у сотрудников первая цифра йд 2 родом из города с йд 2 и йд услуги 2
    public static final String REQUEST_9 = "with people(name, id, city_id) as (\n" +
            "SELECT name, id, city_id FROM emploee \n" +
            "where id like '2%'\n" +
            "),\n" +
            "federal (sr_id, sr_name, e_id, e_name, c_id, type) as (\n" +
            "SELECT sr.id, sr.name, p.id, p.name, p.city_id, service_type  FROM request rq\n" +
            "\tjoin federal_service sr on sr.type = rq.service_type and rq.service_id = sr.id\n" +
            "\tjoin people p on employee_id = p.id\n" +
            "where is_active = 0 and p.city_id in (select id from city where id like '2%') and sr.id like '2%'\n" +
            "),\n" +
            "municipal (sr_id, sr_name, e_id, e_name, c_id, type) as (\n" +
            "SELECT sr.id, sr.name, p.id, p.name, p.city_id, service_type  FROM request rq\n" +
            "\tjoin municipal_service sr on sr.type = rq.service_type and rq.service_id = sr.id\n" +
            "\tjoin people p on employee_id = p.id\n" +
            "where is_active = 0 and p.city_id in (select id from city where id like '2%') and sr.id like '2%'\n" +
            "),\n" +
            "\n" +
            "regional (sr_id, sr_name, e_id, e_name, c_id, type) as (\n" +
            "SELECT sr.id, sr.name, p.id, p.name, p.city_id, service_type  FROM request rq\n" +
            "\tjoin regional_service sr on sr.type = rq.service_type and rq.service_id = sr.id\n" +
            "\tjoin people p on employee_id = p.id\n" +
            "where is_active = 0 and p.city_id in (select id from city where id like '2%') and sr.id like '2%'\n" +
            ")\n" +
            "\n" +
            "select sr_id, sr_name, e_id, e_name, c_id, type from federal union \n" +
            "select sr_id, sr_name, e_id, e_name, c_id, type from municipal union \n" +
            "select sr_id, sr_name, e_id, e_name, c_id, type from regional";

    // 10 Вывести заявки, оказанные клиентам с судимостью администраторами из городов с буквой М
    public static final String REQUEST_10 = "select rq.service_type, cl.name client, c.name city from request rq\n" +
            "join client cl on cl.id = rq.client_id\n" +
            "join city c on c.id = cl.city_id\n" +
            "where cl.has_convictions = 1 and rq.employee_id in (select id from emploee e where e.position = 'Администратор')\n" +
            "and c.name like '%М%'";

}
