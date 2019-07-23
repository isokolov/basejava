package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class OrganizationSection extends AbstractSection {

    private final List<Organization> organisations;

    public OrganizationSection(List<Organization> organisations) {
        Objects.requireNonNull(organisations, "organizations can't be null");
        this.organisations = organisations;
    }

    public List<Organization> getOrganisations() {
        return organisations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrganizationSection)) return false;

        OrganizationSection that = (OrganizationSection) o;

        return organisations != null ? organisations.equals(that.organisations) : that.organisations == null;
    }

    @Override
    public int hashCode() {
        return organisations != null ? organisations.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "OrganizationSection{" +
                "organisations=" + organisations +
                '}';
    }
}
