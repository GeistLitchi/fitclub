package comp3350.fitclub.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import comp3350.fitclub.R;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button goToMuscleGroupBtn = (Button) findViewById(R.id.btn_go_to_muscle_group);
        Button goToExercisesBtn = (Button) findViewById(R.id.btn_go_to_exercises_activity);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button goToWorkout = findViewById(R.id.btn_go_to_find_workout);
        goToExercisesBtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, ExercisesActivity.class);
                startActivity(intent);
            }
        });

        goToMuscleGroupBtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, MuscleGroupPage.class);
                startActivity(intent);
            }
        });


        goToWorkout.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, WorkoutPage.class);
                startActivity(intent);
            }
        });
    }



    @Override
    public void onDestroy(){super.onDestroy();}

}