package comp3350.fitclub.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import comp3350.fitclub.R;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import comp3350.fitclub.logic.ExerciseTutorialLogic;
import comp3350.fitclub.objects.ExerciseTutorial;

public class ExerciseTutorialActivity extends AppCompatActivity {

    TextView exerciseNameText;
    TextView bodyText;
    Button linkButton;

    private ExerciseTutorialLogic exerciseTutorialLogic;
    private ExerciseTutorial exerciseTutorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_tutorial);

        //get the elements from the xml
        exerciseNameText = findViewById(R.id.tutorial_exercise_name);
        bodyText = findViewById(R.id.tutorial_body);
        linkButton = findViewById(R.id.tutorial_link_button);

        //get the name of the selected exercise from the intent
        Intent intent = getIntent();
        String exerciseName = intent.getStringExtra("exerciseName");
        exerciseTutorialLogic = new ExerciseTutorialLogic();

        //get the tutorial for that exercise
        exerciseTutorial = exerciseTutorialLogic.getExerciseTutorial(exerciseName);

        //if we have a valid tutorial, display the information
        if (exerciseTutorial != null) {
            exerciseNameText.setText(exerciseName);
            bodyText.setText(exerciseTutorial.getBody() == null ? exerciseTutorial.getBody()
                    : "This exercise is new and doesn't have a tutorial yet");
            linkButton.setText("Learn More Here");


        } else { //if the tutorial was null, display default info
            exerciseNameText.setText("Error");
            bodyText.setText("Exercise not found.");
            linkButton.setVisibility(View.GONE);
        }
    }
}