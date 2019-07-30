package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * gkislin
 * 19.07.2016
 */
public class Organization {

    private final Link homePage;
    //private final String description;
    private List<PositionEntry> positionEntries = new ArrayList<>();

    public Organization(String name, String url, PositionEntry... positionEntries) {
        this.positionEntries = Arrays.asList(positionEntries);
        Objects.requireNonNull(positionEntries, "positionEntries can't be null");
        this.homePage = new Link(name, url);
    }

    public List<PositionEntry> getPositionEntries() {
        return positionEntries;
    }

    public Link getHomePage() {
        return homePage;
    }

    public static class PositionEntry {

        private final LocalDate startDate;
        private final LocalDate endDate;
        private final String title;
        private final String description;

        public PositionEntry(LocalDate startDate, LocalDate endDate, String title, String description) {
            Objects.requireNonNull(startDate, "startDate must not be null");
            Objects.requireNonNull(endDate, "endDate must not be null");
            Objects.requireNonNull(title, "title must not be null");
            this.startDate = startDate;
            this.endDate = endDate;
            this.title = title;
            this.description = description;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }
    }

}
