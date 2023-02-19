package comp3350.fitclub.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import comp3350.fitclub.R;

public class WorkoutPage extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_NAME_WORKOUT = "comp3350.fitclub.presentation.extar.NAME_WORKOUT";         // creating a unique key so that i can use it in key value pair for intent

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_page);

        Button btn_upper = findViewById(R.id.upper_body);
        Button btn_lower = findViewById(R.id.lower_body);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button btn_custom_1 = findViewById(R.id.custom_workout);

        btn_upper.setOnClickListener(this);
        btn_lower.setOnClickListener(this);
        btn_custom_1.setOnClickListener(this);
    }

    // this method overrides the onClick method
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        String workoutName;
        Button btnClicked;
        //Intent intent = new Intent(this, RecycleView.class);    // where we want to go from this activity
        Intent intent;

        switch (view.getId()){
            case R.id.upper_body:
                intent = new Intent(this, RecycleView.class);

                btnClicked = findViewById(R.id.upper_body);
                workoutName = btnClicked.getText().toString();                  //getting string from that button
                break;
            case R.id.lower_body:
                intent = new Intent(this, RecycleView.class);

                btnClicked = findViewById(R.id.lower_body);
                workoutName = btnClicked.getText().toString();                  // getting string from that button
                break;
            case R.id.custom_workout:
                intent = new Intent(this, CustomizableRecyclerView.class);

                btnClicked = findViewById(R.id.custom_workout);
                workoutName = btnClicked.getText().toString();                  // getting string from that button
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }

        intent.putExtra(EXTRA_NAME_WORKOUT, workoutName);                       // passing the key-value pair
        startActivity(intent);                                                  // starting the activity
    }
}