package ru.javawebinar.basejava.model;

import java.util.HashMap;
import java.util.Map;

public class TimelineSection extends Section {

    private Map<String, Organization> organisations = new HashMap<>();

    public TimelineSection(SectionType sectionType, Map<String, Organization> organisations) {
        super(sectionType);
        this.organisations = organisations;
    }

    public Map<String, Organization> getOrganisations() {
        return organisations;
    }

    public void setOrganisations(Map<String, Organization> organisations) {
        this.organisations = organisations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimelineSection)) return false;

        TimelineSection that = (TimelineSection) o;

        return organisations != null ? organisations.equals(that.organisations) : that.organisations == null;
    }

    @Override
    public int hashCode() {
        return organisations != null ? organisations.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TimelineSection{" +
                "organisations=" + organisations +
                '}';
    }
}
