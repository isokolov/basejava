package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ru.javawebinar.basejava.model.SectionType.*;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            dos.writeInt(resume.getContacts().size());
            for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            // TODO implements sections
            dos.writeInt(resume.getSections().size());
            for (Map.Entry<SectionType, Section> entry : resume.getSections().entrySet()) {
                SectionType sectionType = entry.getKey();
                dos.writeUTF(entry.getKey().name());
                switch (sectionType) {
                    case OBJECTIVE:
                    case PERSONAL:
                        //writeTextSection(dos, entry);
                        dos.writeUTF(((TextSection) entry.getValue()).getContent());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        writeListSection(dos, entry);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        writeOrganizationSection(dos, entry);
                        break;
                };
                /*if (entry.getValue() instanceof TextSection) {
                    writeTextSection(dos, entry);
                }
                if (entry.getValue() instanceof ListSection) {
                    writeListSection(dos, entry);
                }
                if (entry.getValue() instanceof OrganizationSection) {
                    writeOrganizationSection(dos, entry);
                }*/
            }

        }
    }

    /*private void writeTextSection(DataOutputStream dos, Map.Entry<SectionType, Section> entry) throws IOException {
        dos.writeUTF(entry.getKey().name());
        dos.writeUTF(((TextSection) entry.getValue()).getContent());
    }*/

    private void writeListSection(DataOutputStream dos, Map.Entry<SectionType, Section> entry) throws IOException {
        int size = ((ListSection) entry.getValue()).getItems().size();
        dos.writeInt(size);
        for (String str : ((ListSection) entry.getValue()).getItems()) {
            dos.writeUTF(str);
        }
    }

    private void writeOrganizationSection(DataOutputStream dos, Map.Entry<SectionType, Section> entry) throws IOException {
        int size = ((OrganizationSection) entry.getValue()).getOrganizations().size();
        dos.writeInt(size);
        for (Organization organization : ((OrganizationSection) entry.getValue()).getOrganizations()) {
            dos.writeUTF(organization.getHomePage().getName());
            dos.writeUTF(organization.getHomePage().getUrl());
            int sizePositions = organization.getPositions().size();
            dos.writeInt(sizePositions);
            for (Organization.Position position : organization.getPositions()) {
                dos.writeUTF(position.getTitle());
                //writeLocalDate(dos, position.getStartDate());
                //writeLocalDate(dos, position.getEndDate());
                dos.writeUTF(position.getStartDate().toString());
                dos.writeUTF(position.getEndDate().toString());
                dos.writeUTF(position.getDescription());
            }
        }

    }

    /*private void writeLocalDate(DataOutputStream dos, LocalDate localDate) throws IOException {
        dos.writeInt(localDate.getYear());
        dos.writeInt(localDate.getMonth().getValue());
    }*/

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            // TODO implements sections
            size = dis.readInt();
            for (int i = 0; i < size; i++) {
                SectionType sectionType = valueOf(dis.readUTF());
                switch (sectionType) {
                    case OBJECTIVE:
                    case PERSONAL:
                        TextSection textSection = readTextSection(dis);
                        resume.addSection(sectionType, textSection);
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        ListSection listSection = readListSection(dis);
                        resume.addSection(sectionType, listSection);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        OrganizationSection organizationSection = readOrganizationSection(dis);
                        resume.addSection(sectionType, organizationSection);
                        break;
                };
            }
            return resume;
        }
    }

    private TextSection readTextSection(DataInputStream dis) throws IOException {
        return new TextSection(dis.readUTF());
    }

    private ListSection readListSection(DataInputStream dis) throws IOException {
        int sizeItems = dis.readInt();
        List<String> items = new ArrayList<>();
        for (int i = 0; i < sizeItems; i++) {
            items.add(dis.readUTF());
        }
        return new ListSection(items);

    }

    private OrganizationSection readOrganizationSection(DataInputStream dis) throws IOException {
        int size = dis.readInt();
        List<Organization> organizations = new ArrayList<Organization>();
        for (int i = 0; i < size; i++) {
            String name = dis.readUTF();
            String url = dis.readUTF();
            Link homePage = new Link(name, url);
            int sizePosition = dis.readInt();
            List<Organization.Position> positions = new ArrayList<Organization.Position>();
            for (int j = 0; j < sizePosition; j++) {
                String title = dis.readUTF();
                /*LocalDate startDate = readLocalDate(dis);
                LocalDate endDate = readLocalDate(dis);*/
                LocalDate startDate = LocalDate.parse(dis.readUTF());
                LocalDate endDate = LocalDate.parse(dis.readUTF());
                String description = dis.readUTF();
                positions.add(new Organization.Position(startDate, endDate, title, description));
            }
            organizations.add(new Organization(homePage, positions));
        }
        return new OrganizationSection(organizations);
    }

    /*private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
    }*/

}