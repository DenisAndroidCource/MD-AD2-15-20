package by.it.academy.layoutexample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editNumber1;
    private EditText editNumber2;
    private Button buttonAdd;
    private TextView textResult;

    private Calculator calculator = new Calculator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNumber1 = findViewById(R.id.editNumber1);
        editNumber2 = findViewById(R.id.editNumber2);
        textResult = findViewById(R.id.textResult);

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
            }
        });
    }

    public void add(){
        String numberAText = editNumber1.getText().toString();
        String numberBText = editNumber2.getText().toString();

        int numberA = Integer.parseInt(numberAText);
        int numberB = Integer.parseInt(numberBText);
        int result = calculator.add(numberA, numberB);

        textResult.setText(String.valueOf(result));
    }
}