package com.ponchikchik.webapp;

import com.ponchikchik.webapp.model.*;

import java.time.LocalDate;
import java.util.*;

import static com.ponchikchik.webapp.model.ContactType.*;
import static com.ponchikchik.webapp.model.SectionType.*;

public class ResumeTestData {
    private static Resume resume = null;

    public static void main(String[] args) {
        String uuid = UUID.randomUUID().toString();
        String fillName = "Григорий Кислин";

        resume = createResume(uuid, fillName);
    }

    public static Resume createResume(String uuid, String fillName) {
        Map<ContactType, String> contacts = new HashMap<>();
        Map<SectionType, AbstractSection> sections = new HashMap<>();
        contacts.put(EMAIL, "gkislin@yandex.ru");
        contacts.put(PHONE_NUMBER, "+7(921) 855-0482");
        contacts.put(SKYPE, "grigory.kislin");
        contacts.put(LINKEDIN, "https://www.linkedin.com/in/gkislin");
        contacts.put(GITHUB, "https://github.com/gkislin");
        contacts.put(STACKOVERFLOW, "https://stackoverflow.com/users/548473/grigory-kislin");
        contacts.put(HOME_PAGE, "http://gkislin.ru/");

        sections.put(PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
        sections.put(OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));

        List<String> achievementList = createAchievementList();
        List<String> qualificationList = createQualificationList();
        List<Organization> organizationList = createOrganizationList();
        List<Organization> educationList = createEducationList();

        sections.put(ACHIEVEMENT, new ListSection(achievementList));
        sections.put(QUALIFICATION, new ListSection(qualificationList));
        sections.put(EXPERIENCE, new OrganizationList(organizationList));
        sections.put(EDUCATION, new OrganizationList(educationList));

        return new Resume(uuid, fillName, sections, contacts);
    }

    private static List<Organization> createOrganizationList() {
        List<Organization> organizationList = new ArrayList<>();

        List<Experience> javaOnlineProjects = new ArrayList<>();
        javaOnlineProjects.add(new Experience(
                "Автор проекта.",
                "Создание, организация и проведение Java онлайн проектов и стажировок.",
                LocalDate.parse("2013-10-01"),
                null
        ));
        organizationList.add(
                new Organization(
                        "Java Online Projects",
                        "https://javaops.ru/",
                        javaOnlineProjects
                )
        );
        List<Experience> wrike = new ArrayList<>();
        wrike.add(new Experience(
                "Старший разработчик (backend)",
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.",
                LocalDate.parse("2014-10-01"),
                LocalDate.parse("2016-01-01")
        ));
        organizationList.add(
                new Organization(
                        "Wrike",
                        "https://www.wrike.com/",
                        wrike
                )
        );
        List<Experience> ritCenter = new ArrayList<>();
        ritCenter.add(new Experience(
                "Старший разработчик (backend)",
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.",
                LocalDate.parse("2014-10-01"),
                LocalDate.parse("2016-01-01")
        ));
        organizationList.add(
                new Organization(
                        "RIT Center",
                        null,
                        ritCenter
                )
        );
        List<Experience> luxoft = new ArrayList<>();
        luxoft.add(new Experience(
                "Ведущий программист",
                "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.",
                LocalDate.parse("2010-12-01"),
                LocalDate.parse("2012-04-01")
        ));
        organizationList.add(
                new Organization(
                        "Luxoft (Deutsche Bank)",
                        "http://www.luxoft.ru/",
                        luxoft
                )
        );
        List<Experience> yota = new ArrayList<>();
        yota.add(new Experience(
                "Ведущий специалист",
                "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)",
                LocalDate.parse("2008-06-01"),
                LocalDate.parse("2010-12-01")
        ));
        organizationList.add(
                new Organization(
                        "Yota",
                        "https://www.yota.ru/",
                        yota
                )
        );
        List<Experience> enkata = new ArrayList<>();
        enkata.add(new Experience(
                "Разработчик ПО",
                "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).",
                LocalDate.parse("2007-03-01"),
                LocalDate.parse("2008-06-01")
        ));
        organizationList.add(
                new Organization(
                        "Enkata",
                        "http://enkata.com/",
                        enkata
                )
        );
        List<Experience> siemensAG = new ArrayList<>();
        siemensAG.add(new Experience(
                "Разработчик ПО",
                "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).",
                LocalDate.parse("2005-01-01"),
                LocalDate.parse("2007-02-01")
        ));
        organizationList.add(
                new Organization(
                        "Siemens AG",
                        "https://www.siemens.com/ru/ru/home.html",
                        siemensAG
                )
        );
        List<Experience> alcatel = new ArrayList<>();
        alcatel.add(new Experience(
                "Инженер по аппаратному и программному тестированию",
                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).",
                LocalDate.parse("1997-09-01"),
                LocalDate.parse("2005-01-01")
        ));
        organizationList.add(
                new Organization(
                        "Alcatel",
                        "http://www.alcatel.ru/",
                        alcatel
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

        List<Experience> coursera = new ArrayList<>();
        coursera.add(new Experience(
                "\"Functional Programming Principles in Scala\" by Martin Odersky",
                null,
                LocalDate.parse("2013-03-01"),
                LocalDate.parse("2013-05-01")
        ));
        organizationList.add(
                new Organization(
                        "Coursera",
                        "https://www.coursera.org/course/progfun",
                        coursera
                )
        );

        List<Experience> luxoft = new ArrayList<>();
        luxoft.add(new Experience(
                "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"",
                null,
                LocalDate.parse("2011-03-01"),
                LocalDate.parse("2011-04-01")
        ));
        organizationList.add(
                new Organization(
                        "Luxoft",
                        "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366",
                        luxoft
                )
        );

        List<Experience> siemensAG = new ArrayList<>();
        siemensAG.add(new Experience(
                "3 месяца обучения мобильным IN сетям (Берлин)",
                null,
                LocalDate.parse("2005-01-01"),
                LocalDate.parse("2005-04-01")
        ));
        organizationList.add(
                new Organization(
                        "Siemens AG",
                        "http://www.siemens.ru/",
                        siemensAG
                )
        );

        List<Experience> alcatel = new ArrayList<>();
        alcatel.add(new Experience(
                "6 месяцев обучения цифровым телефонным сетям (Москва)",
                null,
                LocalDate.parse("1997-09-01"),
                LocalDate.parse("1998-03-01")
        ));
        organizationList.add(
                new Organization(
                        "Alcatel",
                        "http://www.alcatel.ru/",
                        alcatel
                )
        );

        List<Experience> ifmo = new ArrayList<>();
        ifmo.add(new Experience(
                "Аспирантура (программист С, С++)",
                null,
                LocalDate.parse("1993-09-01"),
                LocalDate.parse("1996-07-01")
        ));
        ifmo.add(new Experience(
                "Инженер (программист Fortran, C)",
                null,
                LocalDate.parse("1987-09-01"),
                LocalDate.parse("1993-07-01")
        ));
        organizationList.add(
                new Organization(
                        "Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики",
                        "http://www.ifmo.ru/",
                        ifmo
                )
        );

        List<Experience> schoolMipt = new ArrayList<>();
        schoolMipt.add(new Experience(
                "Закончил с отличием",
                null,
                LocalDate.parse("1984-09-01"),
                LocalDate.parse("1987-06-01")
        ));
        organizationList.add(
                new Organization(
                        "Заочная физико-техническая школа при МФТИ",
                        "http://www.school.mipt.ru/",
                        schoolMipt
                )
        );

        return organizationList;
    }
}
