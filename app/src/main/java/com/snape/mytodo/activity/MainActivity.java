package com.snape.mytodo.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.snape.mytodo.room.DatabaseClient;
import com.snape.mytodo.R;
import com.snape.mytodo.model.Task;
import com.snape.mytodo.adapter.TasksAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton buttonAddTask;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview_tasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        buttonAddTask = findViewById(R.id.floating_button_add);
        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        new GetTasks().execute();

    }


    @SuppressLint("StaticFieldLeak")
    class GetTasks extends AsyncTask<Void, Void, List<Task>> {

        @Override
        protected List<Task> doInBackground(Void... voids) {
            List<Task> taskList = DatabaseClient
                    .getInstance(getApplicationContext())
                    .getAppDatabase()
                    .taskDao()
                    .getAll();
            return taskList;
        }

        @Override
        protected void onPostExecute(List<Task> tasks) {
            super.onPostExecute(tasks);
            TasksAdapter adapter = new TasksAdapter(MainActivity.this, tasks);
            recyclerView.setAdapter(adapter);
        }
    }
}
