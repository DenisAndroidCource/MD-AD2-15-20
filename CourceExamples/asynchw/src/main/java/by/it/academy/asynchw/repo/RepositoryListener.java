package by.it.academy.asynchw.repo;

public interface RepositoryListener<T> {
    void onDataReceived(T data);
}
