package comp3350.fitclub.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
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

public class CreateWorkout extends AppCompatActivity {

    WorkoutLogic workoutLogic = new WorkoutLogic();
    ExerciseLogic exerciseLogic = new ExerciseLogic();

    EditText workoutname;
    String name;
    Spinner spinner;
    Button submit_btn;
    RecyclerView recyclerView;

    MultiAdapter multiAdapter;

    //-------------------------------------------------------------------------------------
    Exercise e1 = new Exercise("e1");
    Exercise e2 = new Exercise("e2");
    Exercise e3 = new Exercise("e3");
    Exercise e4 = new Exercise("e4");
    Exercise e5 = new Exercise("e5");
    Exercise e6 = new Exercise("e6");
    Exercise e7 = new Exercise("e7");

    String[] muscleGroup = {"ALL", "ARMS", "BACK", "CHEST", "CORE", "LEGS", "SHOULDERS"};
    List<Exercise> exerciseList = new ArrayList<Exercise>();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_workout);

        workoutname = findViewById(R.id.your_workout_name);
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
                String value = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(CreateWorkout.this, "you selected "+value, Toast.LENGTH_SHORT).show();

                setExerciseList(value);
                multiAdapter = setMultiAdapter();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //*****************************************************************************************************************************************

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = workoutname.getText().toString();

                List<Exercise> selectedList = multiAdapter.getSelected();
                int size = selectedList.size();
                System.out.println("the size of selected is "+ size);
                if(size>0){
                    System.out.println("this condition is true");
                    Toast.makeText(CreateWorkout.this, "name of the workout is "+name+" size of selected sting is "+size, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(CreateWorkout.this, "No Item was selected", Toast.LENGTH_SHORT).show();
                }

            }
        });
        //***************************************************************************************************************************************************
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