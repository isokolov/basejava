package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResumeTestData {


    public static Resume getResume1(String uuid, String fullName) {
        Resume R1 = new Resume(uuid, fullName);
        R1.setContact(ContactType.MAIL, "mail1@ya.ru");
        R1.setContact(ContactType.PHONE, "11111");
        R1.setSection(SectionType.OBJECTIVE, new TextSection("Objective1"));
        R1.setSection(SectionType.PERSONAL, new TextSection("Personal data"));
        R1.setSection(SectionType.ACHIEVEMENT, new ListSection("Achivment11", "Achivment12", "Achivment13"));
        R1.setSection(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "JavaScript"));
        R1.setSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization11", "http://Organization11.ru",
                                new Organization.Position(2005, Month.JANUARY, "position1", "content1"),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2"))));
        R1.setSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Institute", null,
                                new Organization.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet")),
                        new Organization("Organization12", "http://Organization12.ru")));
        R1.setSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization2", "http://Organization2.ru",
                                new Organization.Position(2015, Month.JANUARY, "position1", "content1"))));


        return R1;
    }

    public static Resume getResume2(String uuid, String fullName) {
        Resume R2 = new Resume(uuid, fullName);
        R2.setContact(ContactType.SKYPE, "skype2");
        R2.setContact(ContactType.PHONE, "22222");

        return R2;
    }

    public static Resume getResume3(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);

        /* Contacts */
        resume.getContacts().put(ContactType.MAIL, "gkislin@yandex.ru");
        resume.getContacts().put(ContactType.PHONE, "+7(921) 855-0482");

        /* Text Sections */
        Section objective = new TextSection(
                "Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        Section personalQualities = new TextSection(
                "Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
        resume.getSections().put(SectionType.OBJECTIVE, objective);
        resume.getSections().put(SectionType.PERSONAL, personalQualities);

        /* List Sections */
        List<String> achievementInfos = new ArrayList<>();
        achievementInfos.add("С 2013 года: разработка проектов \"Разработка Web приложения\"," +
                "\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX).\n" +
                "Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". " +
                "Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.\n");
        achievementInfos.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike.\n" +
                "Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.\n");

        Section achievements = new ListSection(achievementInfos);

        List<String> qualificationInfos = new ArrayList<>();
        qualificationInfos.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualificationInfos.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualificationInfos.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle");
        Section qualifications = new ListSection(qualificationInfos);
        resume.getSections().put(SectionType.ACHIEVEMENT, achievements);
        resume.getSections().put(SectionType.QUALIFICATIONS, qualifications);

        /*  Organization Sections */
        /* Education */
        List<Organization> studyPlaces = new ArrayList<>();
        Organization org1 = new Organization("Coursera", "Coursera",
                new Organization.Position(LocalDate.of(2013, Month.MARCH, 1), LocalDate.of(2013, Month.MAY, 1),
                        "\"Functional Programming Principles in Scala\" by Martin Odersky", ""));
        studyPlaces.add(org1);

        Organization org2 = new Organization("Luxoft", "Luxoft",
                new Organization.Position(LocalDate.of(2011, Month.MARCH, 1), LocalDate.of(2013, Month.APRIL, 1),
                        "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"", ""));
        studyPlaces.add(org2);

        Organization org3 = new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "",
                new Organization.Position(LocalDate.of(1993, Month.SEPTEMBER, 1), LocalDate.of(1996, Month.JULY, 1),
                        "Аспирантура (программист С, С++)", ""),
                new Organization.Position(LocalDate.of(1987, Month.SEPTEMBER, 1), LocalDate.of(1993, Month.JULY, 1),
                        "Инженер (программист Fortran, C", ""));
        studyPlaces.add(org3);
        Section education = new OrganizationSection(studyPlaces);
        resume.getSections().put(SectionType.EDUCATION, education);

        /* Job experience */
        List<Organization> jobPlaces = new ArrayList<>();
        jobPlaces.add(new Organization("Java Online Projects", "",
                new Organization.Position(LocalDate.of(2013, Month.OCTOBER, 1), LocalDate.now(),
                        "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок.")));

        jobPlaces.add(new Organization("Wrike",
                "", new Organization.Position(LocalDate.of(2013, Month.OCTOBER, 1),
                LocalDate.of(2014, Month.OCTOBER, 1),
                "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven," +
                " Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis).\nДвухфакторная аутентификация, авторизация " +
                "по OAuth1, OAuth2, JWT SSO.")));
        Section jobExperience = new OrganizationSection(jobPlaces);
        resume.getSections().put(SectionType.EXPERIENCE, jobExperience);

        /* Printing the results */
        for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
            System.out.println("\n" + entry.getKey() + " : " + entry.getValue());
        }


        System.out.println(SectionType.PERSONAL + " " + resume.getSections().get(SectionType.PERSONAL));
        System.out.println(SectionType.OBJECTIVE + " " + resume.getSections().get(SectionType.OBJECTIVE));
        System.out.println(SectionType.QUALIFICATIONS);
        ListSection section1 = (ListSection) resume.getSection(SectionType.QUALIFICATIONS);
        for (String str : section1.getItems()) {
            System.out.println(str);
        }
        System.out.println(SectionType.ACHIEVEMENT);
        ListSection section2 = (ListSection) resume.getSection(SectionType.ACHIEVEMENT);
        for (String str : section2.getItems()) {
            System.out.println(str);
        }
        System.out.println(SectionType.EXPERIENCE);
        OrganizationSection section3 = (OrganizationSection) resume.getSection(SectionType.EXPERIENCE);
        readOrganization(section3);
        System.out.println(SectionType.EDUCATION);
        OrganizationSection section4 = (OrganizationSection) resume.getSection(SectionType.EDUCATION);
        readOrganization(section4);

        return resume;
    }

    private static void readOrganization(OrganizationSection section) {
        for (Organization organization : section.getOrganizations()) {
            System.out.println(organization.getHomePage().getName());
            for (Organization.Position position : organization.getPositions()) {
                System.out.println(position.getTitle() + " " + position.getDescription() + " " +
                        position.getStartDate() + " " + position.getEndDate());
            }
        }
    }

}