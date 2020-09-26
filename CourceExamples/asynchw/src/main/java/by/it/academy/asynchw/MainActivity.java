package by.it.academy.asynchw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.List;
import java.util.concurrent.Executors;

import by.it.academy.asynchw.database.Database;
import by.it.academy.asynchw.repo.DataRepository;
import by.it.academy.asynchw.repo.FutureDataRepository;
import by.it.academy.asynchw.repo.RepositoryListener;
import by.it.academy.asynchw.repo.RxJavaDataRepository;

public class MainActivity extends AppCompatActivity {

    private DataRepository dataRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataRepository = new FutureDataRepository(new Database(), Executors.newFixedThreadPool(4), getMainExecutor());
        dataRepository = new RxJavaDataRepository(new Database(), Executors.newFixedThreadPool(4), getMainExecutor());

        dataRepository.getAllContacts(new RepositoryListener<List<Contact>>() {
            @Override
            public void onDataReceived(List<Contact> data) {

            }
        });
    }


}
