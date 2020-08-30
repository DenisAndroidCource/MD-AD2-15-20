package by.it.academy.async.rxjava;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import by.it.academy.async.rxjava.database.AppDatabase;
import by.it.academy.async.rxjava.database.data.UserInfoEntity;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxJava2ExampleActivity extends AppCompatActivity {

    private AppDatabase appDatabase;
    private Disposable disposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appDatabase = AppDatabase.getDatabase(this);
        appDatabase = AppDatabase.getDatabase(this);
        appDatabase = AppDatabase.getDatabase(this);
        appDatabase = AppDatabase.getDatabase(this);
        appDatabase = AppDatabase.getDatabase(this);
        appDatabase = AppDatabase.getDatabase(this);
        appDatabase = AppDatabase.getDatabase(this);
        appDatabase = AppDatabase.getDatabase(this);
        appDatabase = AppDatabase.getDatabase(this);
        appDatabase = AppDatabase.getDatabase(this);
        appDatabase = AppDatabase.getDatabase(this);

        Observable.just("", "", "");
        Observable.fromArray(new String[]{});

        disposable = Observable.create(new ObservableOnSubscribe<List<UserInfoEntity>>() {
            @Override
            public void subscribe(ObservableEmitter<List<UserInfoEntity>> emitter) {
                List<UserInfoEntity> entityList = appDatabase.getUserInfoDao().getAll();
                emitter.onNext(entityList);
            }
        })
                .subscribeOn(Schedulers.io())
                .flatMapIterable(items -> items)
                .map(new Function<UserInfoEntity, String>() {
                    @Override
                    public String apply(UserInfoEntity userInfoEntity) {
                        return userInfoEntity.getName();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new io.reactivex.functions.Consumer<String>() {
                    @Override
                    public void accept(String strings) throws Exception {
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });

        /*
            Как это выглядит с лямбдами

            disposable = Observable.create((ObservableOnSubscribe<List<UserInfoEntity>>) emitter -> {
                emitter.onNext(appDatabase.getUserInfoDao().getAll());
            })
                .subscribeOn(Schedulers.io())
                .flatMapIterable(items -> items)
                .map(UserInfoEntity::getName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(strings -> {}, throwable -> {});
        * */
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
