package by.it.academy.recyclerview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static by.it.academy.recyclerview.Consts.CITY_LIST;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new RecyclerViewAdapter(CITY_LIST));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }

    private static class RecyclerViewAdapter extends
            RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

        private List<String> items;

        public RecyclerViewAdapter(List<String> items) {
            this.items = items;
        }

        @NonNull
        @Override
        public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false);
            return new RecyclerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
            holder.bind(items.get(position));
        }

        @Override
        public int getItemCount() {
            return items != null ? items.size() : 0;
        }

        public void addItem(String text) {
            items.add(text);
            notifyItemChanged(items.indexOf(text));
        }

        static class RecyclerViewHolder extends RecyclerView.ViewHolder {

            private TextView itemTextTitle;

            public RecyclerViewHolder(@NonNull View itemView) {
                super(itemView);
                itemTextTitle = itemView.findViewById(R.id.itemTextTitle);
            }

            public void bind(String text) {
                itemTextTitle.setText(text);
            }
        }
    }
}
