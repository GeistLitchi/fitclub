package comp3350.fitclub.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Executable;

import comp3350.fitclub.R;
import comp3350.fitclub.logic.ExerciseList;
import comp3350.fitclub.objects.Exercise;
import comp3350.fitclub.persistence.STUBdatabase;

public class listView2 extends AppCompatActivity {

    TextView textView;
    ListView listView;
    private STUBdatabase stub = new STUBdatabase();

//    private ExerciseList bicep_exe = stub.getBiceps_exe();
    private ExerciseList upperbody_workout = stub.getUpperbody_workout();
    private ExerciseList lowerbody_workout = stub.getLowerbody_workout();
    private ExerciseList bicep_exe = stub.getBiceps_exe();
    private ExerciseList legs_exe = stub.getLegs_exe();
    private ExerciseList abs_exe = stub.getAbs_exe();
    private ExerciseList back_exe = stub.getBack_exe();
    private ExerciseList chest_exe = stub.getChest_exe();
    private ExerciseList shoulder_exe = stub.getShoulder_exe();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view2);

        Intent intent = getIntent();

        String workoutTitle = intent.getStringExtra(WorkoutPage.EXTRA_NAME_WORKOUT);
        String muscleGroupTitle = intent.getStringExtra(MuscleGroupPage.EXTRA_NAME_MUSCLE);

        textView = findViewById(R.id.workout_name);


        listView = findViewById(R.id.listView2);

        CustomAdapter2 ad = null;

        if(workoutTitle != null){
            textView.setText(workoutTitle);

            switch (workoutTitle){

                case "UPPER BODY":
                    ad = new CustomAdapter2(this, R.layout.custom_layout2, upperbody_workout.getExercises());
                    //listView.setAdapter(ad);
                    break;
                case "LOWER BODY":
                    ad = new CustomAdapter2(this, R.layout.custom_layout2, lowerbody_workout.getExercises());
                    //listView.setAdapter(ad);
                    break;
            }

        }else if(muscleGroupTitle != null){

            textView.setText(muscleGroupTitle);

            switch (muscleGroupTitle){
                case "BICEPS":
                    ad = new CustomAdapter2(this, R.layout.custom_layout2, bicep_exe.getExercises());
                    //listView.setAdapter(ad);
                    break;
                case "LEGS":
                    ad = new CustomAdapter2(this, R.layout.custom_layout2, legs_exe.getExercises());
                    //listView.setAdapter(ad);
                    break;
                case "ABS":
                    ad = new CustomAdapter2(this, R.layout.custom_layout2, abs_exe.getExercises());
                    //listView.setAdapter(ad);
                    break;
                case "BACK":
                    ad = new CustomAdapter2(this, R.layout.custom_layout2, back_exe.getExercises());
                    //listView.setAdapter(ad);
                    break;
                case "CHEST":
                    ad = new CustomAdapter2(this, R.layout.custom_layout2, chest_exe.getExercises());
                    //listView.setAdapter(ad);
                    break;
                case "SHOULDER":
                    ad = new CustomAdapter2(this, R.layout.custom_layout2, shoulder_exe.getExercises());
                    //listView.setAdapter(ad);
                    break;
            }

        }
        if (ad != null){
            listView.setAdapter(ad);
        }else{
            System.out.println("ad is null");
        }



    }
}