package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResumeTestData {
    public static void main(String[] args) {

        Resume resume = new Resume("tust uuid", "Григорий Кислин");

        /* Contacts */
        resume.getContacts().put(Resume.ContactType.MAIL, "gkislin@yandex.ru");
        resume.getContacts().put(Resume.ContactType.PHONE, "+7(921) 855-0482");

        /* Text Sections */
        Section objective = new TextSection(SectionType.OBJECTIVE,
                "Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        Section personalQualities = new TextSection(SectionType.PERSONAL,
                "Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
        resume.getSections().put(SectionType.OBJECTIVE, objective);
        resume.getSections().put(SectionType.PERSONAL, personalQualities);

        /* List Sections */
        List<String> achievement_infos = new ArrayList<>();
        achievement_infos.add("С 2013 года: разработка проектов \"Разработка Web приложения\"," +
                "\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX).\n " +
                "Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". " +
                "Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.\n");
        achievement_infos.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike.\n " +
                "Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.\n");

        Section achievements = new ListSection(SectionType.ACHIEVEMENT, achievement_infos);

        List<String> qualification_infos = new ArrayList<>();
        qualification_infos.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualification_infos.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualification_infos.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle");
        Section qualifications = new ListSection(SectionType.QUALIFICATIONS, qualification_infos);
        resume.getSections().put(SectionType.ACHIEVEMENT, achievements);
        resume.getSections().put(SectionType.QUALIFICATIONS, qualifications);

        /*  Timeline Sections */
        /* Education */
        Map<String, Organization> study_places = new HashMap<>();
        study_places.put("Coursera", new Organization("Coursera",
                "\"Functional Programming Principles in Scala\" by Martin Odersky", "",
                YearMonth.of(2013, Month.MARCH), YearMonth.of(2013, Month.MAY)));
        study_places.put("Luxoft", new Organization("Luxoft",
                "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"", "",
                YearMonth.of(2011, Month.MARCH), YearMonth.of(2011, Month.APRIL)));
        study_places.put("Siemens AG", new Organization("Siemens AG",
                "3 месяца обучения мобильным IN сетям (Берлин)", "",
                YearMonth.of(2005, Month.JANUARY), YearMonth.of(2005, Month.APRIL)));
        Section education = new TimelineSection(SectionType.EDUCATION, study_places);
        resume.getSections().put(SectionType.EDUCATION, education);

        /* Job experience */
        Map<String, Organization> job_places = new HashMap<>();
        job_places.put("Java Online Projects", new Organization("Java Online Projects",
                "Создание, организация и проведение Java онлайн проектов и стажировок.", "Автор проекта.",
                YearMonth.of(2013, Month.OCTOBER), YearMonth.now()));
        job_places.put("Wrike", new Organization("Wrike",
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven," +
                        " Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis).\n Двухфакторная аутентификация, авторизация " +
                        "по OAuth1, OAuth2, JWT SSO.", "Старший разработчик (backend)",
                YearMonth.of(2013, Month.OCTOBER), YearMonth.now()));
        Section jobExperience = new TimelineSection(SectionType.EXPERIENCE, job_places);
        resume.getSections().put(SectionType.EXPERIENCE, jobExperience);


        /* Printing the results */
        for (Map.Entry<Resume.ContactType, String> entry : resume.getContacts().entrySet()) {
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
                Section section = (TimelineSection) entry.getValue();
                for (Map.Entry<String, Organization> entryOrgs : ((TimelineSection) section).getOrganisations().entrySet()) {
                    System.out.println(entryOrgs.getValue().getTitle() + " " + entryOrgs.getValue().getPosition() + " " +
                            entryOrgs.getValue().getDescription() + " " +
                            entryOrgs.getValue().getStart() + " " + entryOrgs.getValue().getEnd());
                }
            }
        }

    }
}
