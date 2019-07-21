package ru.javawebinar.basejava.model;

public abstract class Section {

    protected SectionType sectionType;

    public Section(SectionType sectionType) {
        this.sectionType = sectionType;
    }

    public SectionType getSectionType() {
        return sectionType;
    }

    public void setSectionType(SectionType sectionType) {
        this.sectionType = sectionType;
    }
}
