package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResumeTestData {

    public static Resume getResume(String uuid, String fullName) {
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

        for (Map.Entry<SectionType, Section> entry : resume.getSections().entrySet()) {
            if (entry.getKey() == SectionType.PERSONAL || entry.getKey() == SectionType.OBJECTIVE) {
                System.out.println(entry.getKey() + " : " + ((TextSection) entry.getValue()).getContent());
            }
            if (entry.getKey() == SectionType.QUALIFICATIONS || entry.getKey() == SectionType.ACHIEVEMENT) {
                System.out.println("\n" + entry.getKey());
                ListSection section = (ListSection) entry.getValue();
                for (String str : section.getItems()) {
                    System.out.println(str);
                }
            }
            if (entry.getKey() == SectionType.EXPERIENCE || entry.getKey() == SectionType.EDUCATION) {
                System.out.println("\n" + entry.getKey() + "\n");
                OrganizationSection section = (OrganizationSection) entry.getValue();
                for (Organization organization : section.getOrganizations()) {
                    System.out.println(organization.getHomePage().getName());
                    for (Organization.Position position : organization.getPositionEntries()) {
                        System.out.println(position.getTitle() + " " + position.getDescription() + " " +
                                position.getStartDate() + " " + position.getEndDate());
                    }
                }

            }
        }

        return resume;
    }

}