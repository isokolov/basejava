package ru.javawebinar.basejava.model;

import java.util.Objects;

public class TextSection extends Section {

    private String text;

    public TextSection(SectionType sectionType, String text) {
        super(sectionType);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextSection that = (TextSection) o;

        if (getSectionType() != that.getSectionType()) return false;
        return text != null ? text.equals(that.text) : that.text == null;
    }

    @Override
    public int hashCode() {
        int result = getSectionType().hashCode();
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TextSection{" +
                "sectionType=" + getSectionType() +
                ", text='" + text + '\'' +
                '}';
    }
}
