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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import comp3350.fitclub.R;
import comp3350.fitclub.logic.ExerciseLogic;
import comp3350.fitclub.objects.Exercise;

public class RecycleView extends AppCompatActivity implements RecyclerViewInterface {

    TextView textView;
    RecyclerView recycleView;
    private final ExerciseLogic exercises = new ExerciseLogic();

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

            doing = exercises.searchExerciseByBodyGroup(workoutTitle);          //This will return the exercises in either upper or lower body groups


        }else if(muscleGroupTitle != null){                                     //checking for either we clicked on muscle group
            textView.setText(muscleGroupTitle);                                 // setting the heading to muscle group name

            doing = exercises.searchExerciseByMuscleGroup(muscleGroupTitle);    //this will return the exercises for a given muscle group

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

