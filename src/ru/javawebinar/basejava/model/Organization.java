package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Calendar;

public class Organization {

    private String title;
    private String description;
    private String position;
    private YearMonth start;
    private YearMonth end;

    public Organization(String title, String description, String position, YearMonth start, YearMonth end) {
        this.title = title;
        this.description = description;
        this.position = position;
        this.start = start;
        this.end = end;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public YearMonth getStart() {
        return start;
    }

    public void setStart(YearMonth start) {
        this.start = start;
    }

    public YearMonth getEnd() {
        return end;
    }

    public void setEnd(YearMonth end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organization)) return false;

        Organization that = (Organization) o;

        if (!title.equals(that.title)) return false;
        if (!description.equals(that.description)) return false;
        if (position != null ? !position.equals(that.position) : that.position != null) return false;
        if (!start.equals(that.start)) return false;
        return end != null ? end.equals(that.end) : that.end == null;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + start.hashCode();
        result = 31 * result + (end != null ? end.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
