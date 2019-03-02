package com.snape.mytodo.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.snape.mytodo.interfaces.TaskDao;
import com.snape.mytodo.model.Task;

@Database(entities = {Task.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
}
