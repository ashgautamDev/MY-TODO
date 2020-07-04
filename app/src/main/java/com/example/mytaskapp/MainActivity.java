package com.example.mytaskapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int RESULT_ADD = 1;

    private TaskViewModle taskViewModle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       FloatingActionButton addButton = findViewById(R.id.fav);
    addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Add_task.class);
                startActivityForResult(intent, RESULT_ADD);
            }
        });


        RecyclerView rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);

        final TaskAdapter adapter = new TaskAdapter();
        rv.setAdapter(adapter);

        taskViewModle = ViewModelProviders.of(this).get(TaskViewModle.class);
        taskViewModle.getAllTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable List<Task> tasks) {
                adapter.setTasks(tasks);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RESULT_ADD && requestCode == RESULT_OK){
            String title = data.getStringExtra(Add_task.EXTRA_TITLE);
            String description = data.getStringExtra(Add_task.EXTRA_DESCRIPSION);
            int priority = data.getIntExtra(Add_task.EXTRA_PRIORITY,1);

            Task task = new Task(title,description,priority);
            taskViewModle.insert(task);
            Toast.makeText(this, "Task Added", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Task not  Added", Toast.LENGTH_SHORT).show();
        }

    }
}