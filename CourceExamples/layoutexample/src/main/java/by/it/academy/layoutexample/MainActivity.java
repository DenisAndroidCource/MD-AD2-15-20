package by.it.academy.layoutexample;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import by.it.academy.layoutexample.patternadapter.Operation;

public class MainActivity extends AppCompatActivity {

    private EditText editNumber1;
    private EditText editNumber2;
    private Button buttonAdd;
    private TextView textResult;
    private RecyclerView recyclerView;

    private Calculator calculator = new Calculator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNumber1 = findViewById(R.id.editNumber1);
        editNumber2 = findViewById(R.id.editNumber2);
        textResult = findViewById(R.id.textResult);

        recyclerView = findViewById(R.id.operationList);
        recyclerView.setAdapter(new OperationListAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
            }
        });
    }

    public void add() {
        String numberAText = editNumber1.getText().toString();
        String numberBText = editNumber2.getText().toString();

        int numberA = Integer.parseInt(numberAText);
        int numberB = Integer.parseInt(numberBText);
        int result = calculator.add(numberA, numberB);

        updateList(numberA, numberB, result);
        showResult(result);
    }

    private void updateList(int numberA, int numberB, int result) {
        OperationListAdapter adapter = (OperationListAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.addItem(new Operation(numberA, numberB, result));
        }
    }

    private void showResult(int result) {
        textResult.setText(String.valueOf(result));
    }

    private static class OperationListAdapter extends RecyclerView.Adapter<OperationListAdapter.OperationItemViewHolder> {

        private List<Operation> items = new ArrayList<Operation>();

        @NonNull
        @Override
        public OperationItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_operation, parent, false);
            return new OperationItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull OperationItemViewHolder holder, int position) {
            holder.bind(items.get(position));
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        public void addItem(Operation operation) {
            items.add(operation);
            notifyItemChanged(items.indexOf(operation));
        }

        private static class OperationItemViewHolder extends RecyclerView.ViewHolder {

            private TextView operationTitle;
            private ImageView imageView;

            public OperationItemViewHolder(@NonNull View itemView) {
                super(itemView);
                operationTitle = itemView.findViewById(R.id.operationTitle);
                imageView = itemView.findViewById(R.id.imageView);
            }

            public void bind(final Operation operation) {
                // 2 + 3 = 5
                imageView.setImageResource(R.drawable.ic_android_black_24dp);

                String format = itemView.getContext().getString(R.string.listItemFormat);
                String text = String.format(format, operation.getNum1(), operation.getNum2(), operation.getResult());
                operationTitle.setText(text);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("MSG", "result: " + operation.getResult());
                    }
                });
            }
        }
    }

}