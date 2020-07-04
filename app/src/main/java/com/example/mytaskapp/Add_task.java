package com.example.mytaskapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;


public class Add_task extends AppCompatActivity {
    public static final String EXTRA_TITLE =
            " com.example.mytaskapp.EXTRA_TITLE";

    public static final String EXTRA_DESCRIPSION =
            " com.example.mytaskapp.EEXTRA_DESCRIPSION";

    public static final String EXTRA_PRIORITY =
            " com.example.mytaskapp.EXTRA_PRIORITY";

    private EditText ettitle, etdescription;
    private NumberPicker numberPicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        EditText ettitle = findViewById(R.id.etTitle);
        EditText etdescription = findViewById(R.id.etDescription);
        NumberPicker numberPicker = findViewById(R.id.numberPicker);

        numberPicker.setMaxValue(3);
        numberPicker.setMinValue(1);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_close_24);
        setTitle("Add Task");
    }

    private void saveTask() {
        String title = ettitle.getText().toString();
        String decription = etdescription.getText().toString();
        int priorty = numberPicker.getValue();

        if (title.trim().isEmpty() || decription.trim().isEmpty()) {
            Toast.makeText(this, "Please enter Task and description ", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TITLE, title);
        intent.putExtra(EXTRA_PRIORITY, priorty);
        intent.putExtra(EXTRA_DESCRIPSION, decription);
        setResult(RESULT_OK, intent);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.task_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.saveId:
                saveTask();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}