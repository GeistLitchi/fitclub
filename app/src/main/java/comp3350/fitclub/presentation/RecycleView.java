package comp3350.fitclub.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import comp3350.fitclub.R;
import comp3350.fitclub.application.Main;
import comp3350.fitclub.logic.ExerciseLogic;
import comp3350.fitclub.logic.LikedLogic;
import comp3350.fitclub.objects.Exercise;
import comp3350.fitclub.objects.Workout;

public class RecycleView extends AppCompatActivity implements RecyclerViewInterface {


    TextView textView;
    RecyclerView recycleView;
    private final ExerciseLogic exercises = new ExerciseLogic();
    private final LikedLogic liked = new LikedLogic();

    List<Exercise> exerciseList = null;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);

        textView = findViewById(R.id.workout_name);

        recycleView = findViewById(R.id.recycleView);
        recycleView.setLayoutManager(new LinearLayoutManager(this));                        // layout manager

        setExerciseList();
        setAdapter();
    }


    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END, 0) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();

            Collections.swap(exerciseList, fromPosition, toPosition);

            Objects.requireNonNull(recyclerView.getAdapter()).notifyItemMoved(fromPosition, toPosition);

            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        }
    };


    @Override
    public void onItemClick(int position) {

        Intent intent = new Intent(this, ExerciseTutorialActivity.class);
        intent.putExtra("exerciseName", exerciseList.get(position).getExerciseName() );
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(int position) {
        Exercise currExercise = exerciseList.get(position);
        if(liked.isContains(currExercise))
        {
            liked.deleteLiked(currExercise);
            Toast.makeText(this,"deleted from my Favorite",Toast.LENGTH_SHORT).show();
        }else
        {
            liked.addLiked(currExercise);
            Toast.makeText(this,"Added to my Favorite",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRestart() {
        super.onRestart();

        setExerciseList();
        setAdapter();
    }

    private void setExerciseList() {
        Intent intent = getIntent();

        String workoutTitle = intent.getStringExtra("workoutTitle");                //getting the value from intent key-value pair
        String muscleGroupTitle = intent.getStringExtra(MuscleGroupPage.EXTRA_NAME_MUSCLE);         //getting the value from intent key-value pair
        String title = intent.getStringExtra(MainActivity.EXTRA_NAME_MAIN);

        if(workoutTitle != null){                                               //checking for either we clicked on find Workout
            textView.setText(workoutTitle);                                     // setting the heading to workout name

            exerciseList = exercises.searchExerciseByWorkout(workoutTitle);          //This will return the exercises in either upper or lower body groups

        } else if(muscleGroupTitle != null) {                                     //checking for either we clicked on muscle group
            textView.setText(muscleGroupTitle);                                 // setting the heading to muscle group name

            exerciseList = exercises.searchExerciseByMuscleGroup(muscleGroupTitle);    //this will return the exercises for a given muscle group

        } else if(title != null) {
            textView.setText(title);

            exerciseList = liked.getLikedExercises();
        }
    }

    private void setAdapter() {
        CustomAdapter ad;

        if (exerciseList != null){
            ad = new CustomAdapter(this, exerciseList, this);                 //creating new customAdapter
            recycleView.setAdapter(ad);                                                         //setting that adapter for recycler view

            //for drag and drop item but not saving yet
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
            itemTouchHelper.attachToRecyclerView(recycleView);

        }else{
            System.out.println("adapter is null");
        }
    }
}

