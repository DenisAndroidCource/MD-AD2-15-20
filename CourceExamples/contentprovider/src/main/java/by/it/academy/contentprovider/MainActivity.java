package by.it.academy.contentprovider;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String PREF_KEY = "Nash";

    private EditText text1;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = findViewById(R.id.text1);

        String load = loadText();

        if (load != null) {
            text1.setText(load);
        }

        button = findViewById(R.id.button);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = text1.getText().toString();
                saveText(text);
            }
        };
        button.setOnClickListener(listener);
    }

    private void saveText(String t) {
        SharedPreferences shared = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = shared.edit();
        edit.putString(PREF_KEY, t);
        edit.apply();
    }

    private String loadText() {
        SharedPreferences shared = getPreferences(Context.MODE_PRIVATE);
        return shared.getString(PREF_KEY, null);
    }
}
