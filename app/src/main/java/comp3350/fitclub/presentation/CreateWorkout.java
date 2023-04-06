package comp3350.fitclub.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import comp3350.fitclub.R;
import comp3350.fitclub.logic.ExerciseLogic;
import comp3350.fitclub.logic.WorkoutLogic;
import comp3350.fitclub.objects.Exercise;
import comp3350.fitclub.objects.Workout;

public class CreateWorkout extends AppCompatActivity {

    WorkoutLogic workoutLogic = new WorkoutLogic();
    ExerciseLogic exerciseLogic = new ExerciseLogic();

    EditText workout_name;
    Spinner spinner;
    Button submit_btn;
    RecyclerView recyclerView;

    MultiAdapter multiAdapter;

    String[] muscleGroup = {"ALL", "ARMS", "BACK", "CHEST", "CORE", "LEGS", "SHOULDERS"};
    List<Exercise> exerciseList = new ArrayList<Exercise>();

    Context context = this;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_workout);

        workout_name = findViewById(R.id.your_workout_name);
        spinner = findViewById(R.id.spinner);
        submit_btn = findViewById(R.id.submit_btn);
        recyclerView = findViewById(R.id.recycleView);

        //Setup the spinner that will hold the muscle groups
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(CreateWorkout.this, android.R.layout.simple_spinner_item, muscleGroup);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String exerciseName = adapterView.getItemAtPosition(i).toString();

                setExerciseList(exerciseName);
                multiAdapter = setMultiAdapter();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String workoutName = workout_name.getText().toString();
                String muscleGroup = spinner.getSelectedItem().toString();

                List<Exercise> selectedList = multiAdapter.getSelected();

                Workout workout = workoutLogic.insertWorkout(new Workout(workoutName, muscleGroup));

                if (selectedList.size() > 0) {
                    workoutLogic.addExercises(workout, selectedList);
                }

                Toast.makeText(CreateWorkout.this, "New workout created", Toast.LENGTH_SHORT).show();

                onBackPressed();
            }
        });

    }

    private void setExerciseList(String muscleGroupSelected) {
        if (muscleGroupSelected.equalsIgnoreCase("ALL")) {
            exerciseList = exerciseLogic.getExercises();
        } else {
            exerciseList = exerciseLogic.searchExerciseByMuscleGroup(muscleGroupSelected);
        }
    }

    private MultiAdapter setMultiAdapter() {
        MultiAdapter multiAdapter = new MultiAdapter(this, exerciseList);
        recyclerView.setAdapter(multiAdapter);

        return multiAdapter;
    }
}