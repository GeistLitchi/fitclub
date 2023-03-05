package comp3350.fitclub.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import comp3350.fitclub.R;
import comp3350.fitclub.objects.Workout;
import comp3350.fitclub.logic.WorkoutLogic;

public class WorkoutActivity extends AppCompatActivity implements RecyclerViewInterface {
    TextView workoutName;
    RecyclerView workoutView;

    private final WorkoutLogic workoutLogic = new WorkoutLogic();
    List<Workout> workoutList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        workoutName = findViewById(R.id.workout_name);

        workoutView = findViewById(R.id.workout_exercise_list);
        workoutView.setLayoutManager(new LinearLayoutManager(this));

        //get the type of workout (Upper or Lower)
        Intent intent = getIntent();
        String workoutType = intent.getStringExtra(WorkoutPage.EXTRA_NAME_WORKOUT);

        if(null != workoutType){
            workoutName.setText(workoutType);
            workoutList = workoutLogic.searchWorkoutType(workoutType);
        }

//        if(null != workoutList) {
//            CustomAdapter ca = new CustomAdapter(this, null, workoutList, this);                 //creating new customAdapter
//            workoutView.setAdapter(ca);
//        }
    }

    //on click of a workout go to page to display the exercises in that workout
    @Override
    public void onItemClick(int position) {

        Intent intent = new Intent(this, ExercisesActivity.class);
        intent.putExtra("workoutName", (ArrayList) workoutList.get(position).getWorkoutExercises());
        startActivity(intent);
    }
}
