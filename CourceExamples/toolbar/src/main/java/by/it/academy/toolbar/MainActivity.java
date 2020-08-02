package by.it.academy.toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ((CustomView) findViewById(R.id.customView)).setTouchActionListener(new CustomView.TouchActionListener() {
            @Override
            public void onTouchDown(int x, int y) {
                Toast.makeText(MainActivity.this, "X: " + x + " Y: " + y, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        menu.add(0, R.id.openActivity, 1, getString(R.string.openActivity));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.openActivity) {
            startNextActivity();
        } else if (itemId == R.id.itemShareData) {
            showConfirmationDialog();
        } else if (itemId == R.id.itemShareWithAlex) {
            findViewById(R.id.customView).invalidate();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showConfirmationDialog() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(getString(R.string.dialogConfirmationTitle))
                .setMessage(getString(R.string.dialogConfirmationMessage))
                .setPositiveButton(getString(R.string.dialogConfirmation), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "POSITIVE", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(getString(R.string.dialogReject), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Snackbar.make(findViewById(R.id.parentContainer), "NEGATIVE", Snackbar.LENGTH_SHORT).show();
                    }
                })
                .create();
        dialog.show();
    }

    private void startNextActivity() {
        startActivity(ToolbarActivity.newIntent(this, 1));
    }
}
