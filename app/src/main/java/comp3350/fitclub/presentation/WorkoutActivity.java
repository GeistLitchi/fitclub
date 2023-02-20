package comp3350.fitclub.presentation;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import comp3350.fitclub.R;
import comp3350.fitclub.objects.Workout;
import comp3350.fitclub.logic.WorkoutLogic;

public class WorkoutActivity extends AppCompatActivity {
    TextView workoutName;

    private WorkoutLogic workoutLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        workoutName = findViewById(R.id.workout_name);
    }
}
