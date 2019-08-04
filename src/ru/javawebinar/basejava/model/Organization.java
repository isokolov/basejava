package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class Organization {

    private final Link homePage;
    private List<Position> positionEntries;

    public Organization(String name, String url, Position... positionEntries) {
        this.positionEntries = Arrays.asList(positionEntries);
        Objects.requireNonNull(positionEntries, "positionEntries can't be null");
        this.homePage = new Link(name, url);
    }

    public List<Position> getPositionEntries() {
        return positionEntries;
    }

    public Link getHomePage() {
        return homePage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organization)) return false;

        Organization that = (Organization) o;

        if (!homePage.equals(that.homePage)) return false;
        return positionEntries.equals(that.positionEntries);
    }

    @Override
    public int hashCode() {
        int result = homePage.hashCode();
        result = 31 * result + positionEntries.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homePage=" + homePage +
                ", positionEntries=" + positionEntries +
                '}';
    }

    public static class Position {

        private final LocalDate startDate;
        private final LocalDate endDate;
        private final String title;
        private final String description;

        public Position(LocalDate startDate, LocalDate endDate, String title, String description) {
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Position)) return false;

            Position that = (Position) o;

            if (!startDate.equals(that.startDate)) return false;
            if (!endDate.equals(that.endDate)) return false;
            if (!title.equals(that.title)) return false;
            return description.equals(that.description);
        }

        @Override
        public int hashCode() {
            int result = startDate.hashCode();
            result = 31 * result + endDate.hashCode();
            result = 31 * result + title.hashCode();
            result = 31 * result + description.hashCode();
            return result;
        }
    }

}
