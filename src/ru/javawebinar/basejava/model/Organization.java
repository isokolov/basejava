package ru.javawebinar.basejava.model;

import java.time.YearMonth;
import java.util.Objects;

public class Organization {

    private final String title;
    private final String description;
    private final String position;
    private final YearMonth start;
    private final YearMonth end;

    public Organization(String title, String description, String position, YearMonth start, YearMonth end) {
        Objects.requireNonNull(title, "title can't be null");
        Objects.requireNonNull(start, "startdate can't be null");
        Objects.requireNonNull(end, "enddate can't be null");
        this.title = title;
        this.description = description;
        this.position = position;
        this.start = start;
        this.end = end;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPosition() {
        return position;
    }

    public YearMonth getStart() {
        return start;
    }

    public YearMonth getEnd() {
        return end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organization)) return false;

        Organization that = (Organization) o;

        if (!title.equals(that.title)) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (position != null ? !position.equals(that.position) : that.position != null) return false;
        if (!start.equals(that.start)) return false;
        return end.equals(that.end);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + start.hashCode();
        result = 31 * result + end.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", position='" + position + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
