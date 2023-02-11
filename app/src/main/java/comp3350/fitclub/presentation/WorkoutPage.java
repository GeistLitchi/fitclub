package comp3350.fitclub.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import comp3350.fitclub.R;

public class WorkoutPage extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_NAME_WORKOUT = "comp3350.fitclub.presentation.extar.NAME_WORKOUT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_page);

        Button btn_upper = findViewById(R.id.upper_body);
        Button btn_lower = findViewById(R.id.lower_body);

        btn_upper.setOnClickListener(this);
        btn_lower.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String workoutName;
        Button btnClicked;
        Intent intent = new Intent(this, listView2.class);

        switch (view.getId()){
            case R.id.upper_body:
                btnClicked = findViewById(R.id.upper_body);
                workoutName = btnClicked.getText().toString();
                intent.putExtra(EXTRA_NAME_WORKOUT, workoutName);
                startActivity(intent);
                //Toast.makeText(this, workoutName, Toast.LENGTH_SHORT).show();
                break;
            case R.id.lower_body:
                btnClicked = findViewById(R.id.lower_body);
                workoutName = btnClicked.getText().toString();
                intent.putExtra(EXTRA_NAME_WORKOUT, workoutName);
                startActivity(intent);
                //Toast.makeText(this, workoutName, Toast.LENGTH_SHORT).show();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }
}