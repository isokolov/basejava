package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

import static ru.javawebinar.basejava.model.SectionType.valueOf;

public class DataStreamSerializer implements StreamSerializer {


    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            writeWithException(dos, resume.getContacts().entrySet(), entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });

            // TODO implements sections
            writeWithException(dos, resume.getSections().entrySet(), entry -> {
                SectionType sectionType = entry.getKey();
                Section section = entry.getValue();
                dos.writeUTF(sectionType.name());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((TextSection) section).getContent());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        writeWithException(dos, ((ListSection) section).getItems(), dos::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        writeWithException(dos, ((OrganizationSection) section).getOrganizations(), organization -> {
                            dos.writeUTF(organization.getHomePage().getName());
                            dos.writeUTF(organization.getHomePage().getUrl());
                            writeWithException(dos, organization.getPositions(), position -> {
                                dos.writeUTF(position.getStartDate().toString());
                                dos.writeUTF(position.getEndDate().toString());
                                dos.writeUTF(position.getTitle());
                                dos.writeUTF(position.getDescription());
                            });
                        });
                        break;
                    default:
                        throw new IllegalStateException();
                }
            });

        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readWithException(dis, o -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));

            // TODO implements sections
            readWithException(dis, section -> {
                SectionType sectionType = valueOf(dis.readUTF());
                switch (sectionType) {
                    case OBJECTIVE:
                    case PERSONAL:
                        resume.addSection(sectionType, new TextSection(dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> items = new ArrayList<>();
                        readWithException(dis, s -> items.add(dis.readUTF()));
                        resume.addSection(sectionType, new ListSection(items));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> organizations = new ArrayList<>();
                        readWithException(dis, organization -> {
                            String name = dis.readUTF();
                            String url = dis.readUTF();
                            Link homePage = new Link(name, url);
                            List<Organization.Position> positions = new ArrayList<Organization.Position>();
                            readWithException(dis, o -> positions.add(new Organization.Position(
                                    LocalDate.parse(dis.readUTF()), LocalDate.parse(dis.readUTF()),
                                    dis.readUTF(), dis.readUTF())));
                            organizations.add(new Organization(homePage, positions));
                        });
                        resume.addSection(sectionType, new OrganizationSection(organizations));
                        break;
                    default:
                        throw new IllegalStateException();
                }
            });
            return resume;
        }
    }

    private interface ConsumerWriter<T> {
        void accept(T t) throws IOException;
    }

    private <T> void writeWithException(DataOutputStream dos, Collection<T> collection, ConsumerWriter<T> action) throws IOException {
        dos.writeInt(collection.size());
        Objects.requireNonNull(action);
        for (T t : collection) {
            action.accept(t);
        }
    }

    private <T> void readWithException(DataInputStream dis, ConsumerWriter<T> action) throws IOException {
        int size = dis.readInt();
        Objects.requireNonNull(action);
        for (int i = 0; i < size; i++) {
            action.accept(null);
        }
    }

}