package comp3350.fitclub.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import comp3350.fitclub.R;
import comp3350.fitclub.logic.ExerciseList;
import comp3350.fitclub.objects.Exercise;
import comp3350.fitclub.persistence.STUBdatabase;

public class RecycleView extends AppCompatActivity implements RecyclerViewInterface {

    TextView textView;
    RecyclerView recycleView;
    private final STUBdatabase stub = new STUBdatabase();

    private final ExerciseList upperbody_workout = stub.getUpperbody_workout();       //getting Arraylist of upper body workout
    private final ExerciseList lowerbody_workout = stub.getLowerbody_workout();       //getting Arraylist of lower body workout
    private final ExerciseList bicep_exe = stub.getBiceps_exe();                      //getting Arraylist of bicep exercise
    private final ExerciseList legs_exe = stub.getLegs_exe();                         //getting Arraylist of legs exercise
    private final ExerciseList abs_exe = stub.getAbs_exe();                           //getting Arraylist of abs exercise
    private final ExerciseList back_exe = stub.getBack_exe();                         //getting Arraylist of back exercise
    private final ExerciseList chest_exe = stub.getChest_exe();                       //getting Arraylist of chest exercise
    private final ExerciseList shoulder_exe = stub.getShoulder_exe();                 //getting Arraylist of shoulder exercise

    ArrayList<Exercise> doing = null;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);

        Intent intent = getIntent();

        String workoutTitle = intent.getStringExtra(WorkoutPage.EXTRA_NAME_WORKOUT);                //getting the value from intent key-value pair
        String muscleGroupTitle = intent.getStringExtra(MuscleGroupPage.EXTRA_NAME_MUSCLE);         //getting the value from intent key-value pair

        textView = findViewById(R.id.workout_name);

        recycleView = findViewById(R.id.recycleView);
        recycleView.setLayoutManager(new LinearLayoutManager(this));                        // layout manager

        CustomAdapter ad;


        if(workoutTitle != null){                                               //checking for either we clicked on find Workout
            textView.setText(workoutTitle);                                     // setting the heading to workout name

            switch (workoutTitle){
                case "UPPER BODY":                                              //checking for UPPER BODY
                    doing = upperbody_workout.getExercises();                   // doing is referencing to that upper body workout arraylist
                    break;
                case "LOWER BODY":                                              //checking for LOWER BODY
                    doing = lowerbody_workout.getExercises();                   // doing is referencing to that lower body workout arraylist
                    break;
            }

        }else if(muscleGroupTitle != null){                                     //checking for either we clicked on muscle group
            textView.setText(muscleGroupTitle);                                 // setting the heading to muscle group name

            switch (muscleGroupTitle){
                case "BICEPS":                                                  //checking for BICEPS
                    doing = bicep_exe.getExercises();                           // doing is referencing to that biceps exercise arraylist
                    break;
                case "LEGS":                                                    //checking for LEGS
                    doing = legs_exe.getExercises();                            // doing is referencing to that legs exercise arraylist
                    break;
                case "ABS":                                                     //checking for ABS
                    doing = abs_exe.getExercises();                             // doing is referencing to that abs exercise arraylist
                    break;
                case "BACK":                                                    //checking for BACK
                    doing = back_exe.getExercises();                            // doing is referencing to that back exercise arraylist
                    break;
                case "CHEST":                                                   //checking for CHEST
                    doing = chest_exe.getExercises();                           // doing is referencing to that chest exercise arraylist
                    break;
                case "SHOULDER":                                                //checking for SHOULDER
                    doing = shoulder_exe.getExercises();                        // doing is referencing to that shoulder exercise arraylist
                    break;
            }

        }
        if (doing != null){
            ad = new CustomAdapter(this, doing, this);                 //creating new customAdapter
            recycleView.setAdapter(ad);                                                         //setting that adapter for recycler view

            //for drag and drop item but not saving yet
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
            itemTouchHelper.attachToRecyclerView(recycleView);


        }else{
            System.out.println("ad is null");
        }



    }


    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END, 0) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();

            Collections.swap(doing, fromPosition, toPosition);

            Objects.requireNonNull(recyclerView.getAdapter()).notifyItemMoved(fromPosition, toPosition);
            //recyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);

            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        }
    };


    @Override
    public void onItemClick(int position) {

        Intent intent = new Intent(this, ExerciseTutorialActivity.class);
        intent.putExtra("exerciseName", doing.get(position).getExerciseName() );
        startActivity(intent);
    }
}

