package by.it.academy.asynchw.repo;

import java.util.List;

import by.it.academy.asynchw.Contact;

public interface DataRepository {
    void getAllContacts(RepositoryListener<List<Contact>> listener);
    void getContact(String id, RepositoryListener<Contact> listener);
    void close();
}
