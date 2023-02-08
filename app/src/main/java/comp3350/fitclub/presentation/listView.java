package comp3350.fitclub.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import comp3350.fitclub.R;


public class listView extends AppCompatActivity {


    //------------------dummy data-------------------------------
    TextView textView;

    ListView listView;
    String[] bicep_arr = {"bicep 1", "bicep 2", "bicep 3", "bicep 4", "bicep 5", "bicep 6", "bicep 7", "bicep 8", "bicep 9"};
    String[] legs_arr = {"leg 1", "leg 2", "leg 3", "leg 4", "leg 5", "leg 6", "leg 7", "leg 8", "leg 9"};
    String[] abs_arr = {"abs 1", "abs 1", "abs 1", "abs 1", "abs 1", "abs 1", "abs 1", "abs 1", "abs 1", "abs 1"};
    String[] back_arr = {"back 1", "back 1", "back 1", "back 1", "back 1", "back 1", "back 1", "back 1", "back 1", "back 1"};
    String[] chest_arr = {"chest 1", "chest 1", "chest 1", "chest 1", "chest 1", "chest 1", "chest 1", "chest 1", "chest 1",};
    String[] shoulder_arr = {"shoulder 1", "shoulder 1", "shoulder 1", "shoulder 1", "shoulder 1", "shoulder 1", "shoulder 1",};


    String[] workout_upper = {"bicep 1", "bicep 2", "abs 1", "abs 1", "chest 1", "chest 1"};
    //----------------------------------------------------------------


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        Intent intent = getIntent();    //getting input from other page

        String workoutTitle = intent.getStringExtra(WorkoutPage.EXTRA_NAME_WORKOUT);
        String muscleGroupTitle = intent.getStringExtra(MuscleGroupPage.EXTRA_NAME_MUSCLE);


        textView = findViewById(R.id.workoutName);


        String[] doing = {"null"};

        listView = findViewById(R.id.listView);

        if(workoutTitle != null){

            textView.setText(workoutTitle);

            switch (workoutTitle) {
                case "UPPER BODY":
                    doing = workout_upper;
                    break;
                case "LOWER BODY":
                    doing = legs_arr;
                    break;
            }

        }else if(muscleGroupTitle != null){

            textView.setText(muscleGroupTitle);

            switch (muscleGroupTitle){
                case "BICEPS":
                    doing = bicep_arr;
                    break;
                case "LEGS":
                    doing = legs_arr;
                    break;
                case "ABS":
                    doing = abs_arr;
                    break;
                case "BACK":
                    doing = back_arr;;
                    break;
                case "CHEST":
                    doing = chest_arr;
                    break;
                case "SHOULDER":
                    doing = shoulder_arr;
                    break;
            }

        }


        CustomAdapter ad = new CustomAdapter(this, R.layout.custom_layout2,doing);
//        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_list_item_1, doing);
        listView.setAdapter(ad);

    }
}