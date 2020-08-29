package by.it.academy.async;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.Size;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class MainActivity extends AppCompatActivity {

    private ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);

    // Async Job -> Send data to the main thread

    private TextView textView;

    private Handler handler;

    private Runnable asyncJob = new Runnable() {
        @Override
        public void run() {
            int i = 0;
            while (i < 5) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                final int count = i;
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        textView.setText(String.valueOf(count));
//                    }
//                });
                i++;

                handler.sendEmptyMessage(1);
                Message msg = handler.obtainMessage(0, new Size(8, 9));
                handler.sendMessage(msg);
            }


//            while (true) {
//                Log.d("THREADLOG", Thread.currentThread().getName());
//                Log.d("THREADLOG", String.valueOf(Thread.currentThread().getId()));
//            }
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        executor.execute(asyncJob);
        executor.execute(asyncJob);
        executor.execute(asyncJob);
        executor.execute(asyncJob);
        executor.execute(asyncJob);
        executor.execute(asyncJob);

        Future<Integer> future = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 0;
            }
        });

        executor.shutdown();

        future.cancel(true);

        try {
            Integer result = future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        CompletableFuture future1 = CompletableFuture
                .supplyAsync(new Supplier() {
                    @Override
                    public Integer get() {
                        return 56;
                    }
                }, executor)
                .thenAcceptAsync(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer o) {
                        textView.setText(String.valueOf(o));
                    }
                }, getMainExecutor());

//        new MyThread().start()

        handler = new Handler(getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {
                if (message.what == 1) {
                    textView.setText("Success");
                    Log.d("handleMessage", "Success");
                } else if (message.what == 0) {
                    Size size = (Size) message.obj;
                    textView.setText(size.toString());
                    Log.d("handleMessage", "size.toString()");
                }

                return false;
            }
        });

        Log.d("THREADLOG", Thread.currentThread().getName());
        Log.d("THREADLOG", String.valueOf(Thread.currentThread().getId()));

        textView = findViewById(R.id.textView);

        findViewById(R.id.startJob).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(asyncJob).start();
            }
        });

//        JobHandler jobHandler = new JobHandler();
//        jobHandler.post(asyncJob);


    }

    //Params, Progress, Result
    class AsyncTaskJob extends AsyncTask<Integer, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(Integer... voids) {
            setProgress(0);
            return "null";
        }

        @Override
        protected void onPostExecute(String aVoid) {
            textView.setText(aVoid);
            super.onPostExecute(aVoid);
        }
    }
}
