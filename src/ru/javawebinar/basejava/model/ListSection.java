package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class ListSection extends Section {

    private List<String> infos = new ArrayList<>();

    public ListSection(SectionType listSection, List<String> infos) {
        super(listSection);
        this.infos = infos;
    }

    public List<String> getInfos() {
        return infos;
    }

    public void setInfos(List<String> infos) {
        this.infos = infos;
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
                "listSection=" + getSectionType() +
                ", infos=" + infos +
                '}';
    }
}
