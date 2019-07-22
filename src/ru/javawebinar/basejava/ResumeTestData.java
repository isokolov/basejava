package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResumeTestData {
    public static void main(String[] args) {

        Resume resume = new Resume("tust uuid", "Григорий Кислин");

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
        studyPlaces.add(new Organization("Coursera",
                "\"Functional Programming Principles in Scala\" by Martin Odersky", "",
                YearMonth.of(2013, Month.MARCH), YearMonth.of(2013, Month.MAY)));
        studyPlaces.add(new Organization("Luxoft",
                "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"", "",
                YearMonth.of(2011, Month.MARCH), YearMonth.of(2011, Month.APRIL)));
        studyPlaces.add(new Organization("Siemens AG",
                "3 месяца обучения мобильным IN сетям (Берлин)", "",
                YearMonth.of(2005, Month.JANUARY), YearMonth.of(2005, Month.APRIL)));
        Section education = new OrganizationSection(studyPlaces);
        resume.getSections().put(SectionType.EDUCATION, education);

        /* Job experience */
        List<Organization> jobPlaces = new ArrayList<>();
        jobPlaces.add(new Organization("Java Online Projects",
                "Создание, организация и проведение Java онлайн проектов и стажировок.", "Автор проекта.",
                YearMonth.of(2013, Month.OCTOBER), YearMonth.now()));
        jobPlaces.add(new Organization("Wrike",
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven," +
                        " Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis).\nДвухфакторная аутентификация, авторизация " +
                        "по OAuth1, OAuth2, JWT SSO.", "Старший разработчик (backend)",
                YearMonth.of(2013, Month.OCTOBER), YearMonth.now()));
        Section jobExperience = new OrganizationSection(jobPlaces);
        resume.getSections().put(SectionType.EXPERIENCE, jobExperience);


        /* Printing the results */
        for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
            System.out.println("\n" + entry.getKey() + " : " + entry.getValue());
        }

        for (Map.Entry<SectionType, Section> entry : resume.getSections().entrySet()) {
            if (entry.getKey() == SectionType.PERSONAL || entry.getKey() == SectionType.OBJECTIVE) {
                System.out.println(entry.getKey() + " : " + ((TextSection) entry.getValue()).getText());
            }
            if (entry.getKey() == SectionType.QUALIFICATIONS || entry.getKey() == SectionType.ACHIEVEMENT) {
                System.out.println("\n" + entry.getKey());
                Section section = (ListSection) entry.getValue();
                for (String str : ((ListSection) section).getInfos()) {
                    System.out.println(str);
                }
            }
            if (entry.getKey() == SectionType.EXPERIENCE || entry.getKey() == SectionType.EDUCATION) {
                System.out.println("\n" + entry.getKey() + "\n");
                Section section = (OrganizationSection) entry.getValue();
                for (Organization organization : ((OrganizationSection) section).getOrganisations()) {
                    System.out.println(organization.getTitle() + " " + organization.getPosition() + " " +
                            organization.getDescription() + " " +
                            organization.getStart() + " " + organization.getEnd());
                }

            }
        }

    }
}
