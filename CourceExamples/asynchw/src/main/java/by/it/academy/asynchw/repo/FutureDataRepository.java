package by.it.academy.asynchw.repo;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Consumer;
import java.util.function.Supplier;

import by.it.academy.asynchw.Contact;
import by.it.academy.asynchw.database.Database;

public class FutureDataRepository implements DataRepository {

    private Database database;
    private Executor mainThreadPoolExecutor;
    private Executor executionThreadPool;

    public FutureDataRepository(Database database, Executor mainThreadPoolExecutor,
                                Executor executionThreadPool) {
        this.database = database;
        this.mainThreadPoolExecutor = mainThreadPoolExecutor;
        this.executionThreadPool = executionThreadPool;
    }

    @Override
    public void getAllContacts(final RepositoryListener<List<Contact>> listener) {
        CompletableFuture.supplyAsync(new Supplier<List<Contact>>() {
            @Override
            public List<Contact> get() {
                return database.getAllData();
            }
        }, executionThreadPool)
                .thenAcceptAsync(new Consumer<List<Contact>>() {
                    @Override
                    public void accept(List<Contact> contacts) {
                        listener.onDataReceived(contacts);
                    }
                }, mainThreadPoolExecutor);

    }

    @Override
    public void getContact(String id, RepositoryListener<Contact> listener) {

    }
}
