package by.it.academy.async.observer;

import java.util.ArrayList;
import java.util.List;

public class JavaObservable {

    private static JavaObservable instance;

    public static JavaObservable getInstance() {
        if (instance == null) {
            instance = new JavaObservable();
        }

        return instance;
    }

    public interface JavaObservableAction {
        void onDataChanged(String data);
    }

    private JavaObservable() {
    }

    private List<JavaObservableAction> observers = new ArrayList<>();

    public void subscribe(JavaObservableAction observer) {
        observers.add(observer);
    }

    public void unsubscribe(JavaObservableAction observer) {
        observers.remove(observer);
    }

    public void onDataReceived(String data) {
        for (JavaObservableAction observer : observers) {
            observer.onDataChanged(data);
        }
    }
}