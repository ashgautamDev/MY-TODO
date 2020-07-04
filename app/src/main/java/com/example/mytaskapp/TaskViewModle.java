package com.example.mytaskapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskViewModle extends AndroidViewModel {
    private TaskDao taskDao;
    private TaskRepository repository;
    private LiveData<List<Task>> allTasks;

    public TaskViewModle(@NonNull Application application) {
        super(application);

        TaskRepository repository = new TaskRepository(application);
        allTasks = repository.getAllTasks();
    }
    public void insert(Task task){
        repository.insert(task);
    }

    public void update(Task task){
        repository.update(task);
    }

    public void delete(Task task){
        repository.delete(task);
    }

    public void deleteAllTasks(){
        repository.deleteAllTasks();
    }

    public LiveData<List<Task>> getAllTasks(){
        return allTasks;
    }
}
