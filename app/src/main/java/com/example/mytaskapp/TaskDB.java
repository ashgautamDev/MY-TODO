package com.example.mytaskapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;



@Database(entities = {Task.class}, version = 1,exportSchema = false)
public abstract class TaskDB extends RoomDatabase {

    private static TaskDB instance;

    public abstract TaskDao taskDao();


    public static synchronized TaskDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TaskDB.class, "task_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomdbcallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomdbcallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            new populateDBAsyncTask(instance).execute();
            super.onCreate(db);
        }
    };
    public static class populateDBAsyncTask extends AsyncTask<Void,Void,Void> {
     private TaskDao taskDao;

        public populateDBAsyncTask(TaskDB db) {
         taskDao = db.taskDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            taskDao.insert(new Task("Title 1","descriotion 1",1));
            taskDao.insert(new Task("Title c","descbzcriotion 1",5));
            taskDao.insert(new Task("Title v","desccvzriotion 1",7));
            taskDao.insert(new Task("Title c1","desccvxvzvriotion 1",8));
            return null;
        }
    }


}
