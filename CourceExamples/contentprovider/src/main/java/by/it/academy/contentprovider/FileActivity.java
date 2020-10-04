package by.it.academy.contentprovider;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileActivity extends AppCompatActivity {

    private static final int PERMISSION_CODE = 18000;

    enum StorageType {
        INTERANAL, EXTERNAL
    }

    private EditText editText;
    private Button button;
    private RadioGroup radioGroup;

    private StorageType storageType = StorageType.INTERANAL;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.viewButtonSave);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText.getText().toString();
                if (checkStoragePermission()) {
                    saveDataToFile(text);
                } else {
                    askPermission();
                }
            }
        });

        radioGroup = findViewById(R.id.fileTypeRadioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (radioGroup.getId() == R.id.internalStorage) {
                    storageType = StorageType.INTERANAL;
                } else {
                    storageType = StorageType.EXTERNAL;
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                saveDataToFile(editText.getText().toString());
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void saveDataToFile(String text) {
        File filesDir = getFilesDir();
        if (storageType == StorageType.EXTERNAL) {
            filesDir = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        }

        File file = new File(filesDir, "text.txt");

        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(text.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkStoragePermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void askPermission() {
        String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(this, permissions, PERMISSION_CODE);
    }
}
