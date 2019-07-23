package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection {

    private final List<String> infos;

    public ListSection(List<String> infos) {
        Objects.requireNonNull(infos, "Infos can't be null");
        this.infos = infos;
    }

    public List<String> getInfos() {
        return infos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListSection)) return false;

        ListSection that = (ListSection) o;

        return infos.equals(that.infos);
    }

    @Override
    public int hashCode() {
        return infos.hashCode();
    }

    @Override
    public String toString() {
        return "ListSection{" +
                ", infos=" + infos +
                '}';
    }
}
