package com.ponchikchik.webapp;

import com.ponchikchik.webapp.model.*;

import java.time.LocalDate;
import java.util.*;

import static com.ponchikchik.webapp.model.ContactType.*;
import static com.ponchikchik.webapp.model.SectionType.*;

public class ResumeTestData {

    public static void main(String[] args) {
        String uuid = UUID.randomUUID().toString();
        String fillName = "Григорий Кислин";

        Resume resume = createResume(uuid, fillName);
    }

    public static Resume createResume(String uuid, String fillName) {
        Resume resume = new Resume(uuid, fillName);

        resume.addContact(EMAIL, "gkislin@yandex.ru");
        resume.addContact(PHONE_NUMBER, "+7(921) 855-0482");
        resume.addContact(SKYPE, "grigory.kislin");
        resume.addContact(LINKEDIN, "https://www.linkedin.com/in/gkislin");
        resume.addContact(GITHUB, "https://github.com/gkislin");
        resume.addContact(STACKOVERFLOW, "https://stackoverflow.com/users/548473/grigory-kislin");
        resume.addContact(HOME_PAGE, "http://gkislin.ru/");

        resume.addSection(PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
        resume.addSection(OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));

        List<String> achievementList = createAchievementList();
        List<String> qualificationList = createQualificationList();
        List<Organization> organizationList = createOrganizationList();
        List<Organization> educationList = createEducationList();

        resume.addSection(ACHIEVEMENT, new ListSection(achievementList));
        resume.addSection(QUALIFICATION, new ListSection(qualificationList));
        resume.addSection(EXPERIENCE, new OrganizationList(organizationList));
        resume.addSection(EDUCATION, new OrganizationList(educationList));

        return resume;
    }

    private static List<Organization> createOrganizationList() {
        List<Organization> organizationList = new ArrayList<>();

        organizationList.add(
                new Organization(
                        "Java Online Projects",
                        "https://javaops.ru/",
                        new Organization.Experience(
                                "Автор проекта.",
                                "Создание, организация и проведение Java онлайн проектов и стажировок.",
                                LocalDate.parse("2013-10-01"),
                                LocalDate.now()
                        )
                )
        );
        organizationList.add(
                new Organization(
                        "Wrike",
                        "https://www.wrike.com/",
                        new Organization.Experience(
                                "Старший разработчик (backend)",
                                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.",
                                LocalDate.parse("2014-10-01"),
                                LocalDate.parse("2016-01-01")
                        )
                )
        );
        organizationList.add(
                new Organization(
                        "RIT Center",
                        null,
                        new Organization.Experience(
                                "Старший разработчик (backend)",
                                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.",
                                LocalDate.parse("2014-10-01"),
                                LocalDate.parse("2016-01-01")
                        )
                )
        );
        organizationList.add(
                new Organization(
                        "Luxoft (Deutsche Bank)",
                        "http://www.luxoft.ru/",
                        new Organization.Experience(
                                "Ведущий программист",
                                "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.",
                                LocalDate.parse("2010-12-01"),
                                LocalDate.parse("2012-04-01")
                        )
                )
        );
        organizationList.add(
                new Organization(
                        "Yota",
                        "https://www.yota.ru/",
                        new Organization.Experience(
                                "Ведущий специалист",
                                "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)",
                                LocalDate.parse("2008-06-01"),
                                LocalDate.parse("2010-12-01")
                        )
                )
        );
        organizationList.add(
                new Organization(
                        "Enkata",
                        "http://enkata.com/",
                        new Organization.Experience(
                                "Разработчик ПО",
                                "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).",
                                LocalDate.parse("2007-03-01"),
                                LocalDate.parse("2008-06-01")
                        )
                )
        );
        organizationList.add(
                new Organization(
                        "Siemens AG",
                        "https://www.siemens.com/ru/ru/home.html",
                        new Organization.Experience(
                                "Разработчик ПО",
                                "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).",
                                LocalDate.parse("2005-01-01"),
                                LocalDate.parse("2007-02-01")
                        )
                )
        );
        organizationList.add(
                new Organization(
                        "Alcatel",
                        "http://www.alcatel.ru/",
                        new Organization.Experience(
                                "Инженер по аппаратному и программному тестированию",
                                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).",
                                LocalDate.parse("1997-09-01"),
                                LocalDate.parse("2005-01-01")
                        )
                )
        );

        return organizationList;
    }

    private static List<String> createQualificationList() {
        List<String> qualificationList = new ArrayList<>();
        qualificationList.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualificationList.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce ");
        qualificationList.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB");
        qualificationList.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy, XML/XSD/XSLT, SQL, C/C++, Unix shell scripts");
        qualificationList.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).");
        qualificationList.add("Python: Django.");
        qualificationList.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        qualificationList.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka ");
        qualificationList.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT. ");
        qualificationList.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix, администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer. ");
        qualificationList.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования ");
        qualificationList.add("Родной русский, английский \"upper intermediate\"");

        return qualificationList;
    }

    private static List<String> createAchievementList() {
        List<String> achievementList = new ArrayList<>();
        achievementList.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        achievementList.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achievementList.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера");
        achievementList.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        achievementList.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
        achievementList.add("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");

        return achievementList;
    }

    private static List<Organization> createEducationList() {
        List<Organization> organizationList = new ArrayList<>();

        organizationList.add(
                new Organization(
                        "Coursera",
                        "https://www.coursera.org/course/progfun",
                        new Organization.Experience(
                                "\"Functional Programming Principles in Scala\" by Martin Odersky",
                                null,
                                LocalDate.parse("2013-03-01"),
                                LocalDate.parse("2013-05-01")
                        )
                )
        );
        organizationList.add(
                new Organization(
                        "Luxoft",
                        "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366",
                        new Organization.Experience(
                                "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"",
                                null,
                                LocalDate.parse("2011-03-01"),
                                LocalDate.parse("2011-04-01")
                        )
                )
        );
        organizationList.add(
                new Organization(
                        "Siemens AG",
                        "http://www.siemens.ru/",
                        new Organization.Experience(
                                "3 месяца обучения мобильным IN сетям (Берлин)",
                                null,
                                LocalDate.parse("2005-01-01"),
                                LocalDate.parse("2005-04-01")
                        )
                )
        );
        organizationList.add(
                new Organization(
                        "Alcatel",
                        "http://www.alcatel.ru/",
                        new Organization.Experience(
                                "6 месяцев обучения цифровым телефонным сетям (Москва)",
                                null,
                                LocalDate.parse("1997-09-01"),
                                LocalDate.parse("1998-03-01")
                        )
                )
        );
        organizationList.add(
                new Organization(
                        "Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики",
                        "http://www.ifmo.ru/",
                        new Organization.Experience(
                                "Аспирантура (программист С, С++)",
                                null,
                                LocalDate.parse("1993-09-01"),
                                LocalDate.parse("1996-07-01")
                        ),
                        new Organization.Experience(
                                "Инженер (программист Fortran, C)",
                                null,
                                LocalDate.parse("1987-09-01"),
                                LocalDate.parse("1993-07-01")
                        )
                )
        );
        organizationList.add(
                new Organization(
                        "Заочная физико-техническая школа при МФТИ",
                        "http://www.school.mipt.ru/",
                        new Organization.Experience(
                                "Закончил с отличием",
                                null,
                                LocalDate.parse("1984-09-01"),
                                LocalDate.parse("1987-06-01")
                        )
                )
        );

        return organizationList;
    }
}
