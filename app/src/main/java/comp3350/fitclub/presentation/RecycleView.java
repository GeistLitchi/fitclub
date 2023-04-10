package comp3350.fitclub.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import comp3350.fitclub.R;
import comp3350.fitclub.logic.ExerciseLogic;
import comp3350.fitclub.logic.LikedLogic;
import comp3350.fitclub.logic.WorkoutLogic;
import comp3350.fitclub.objects.Exercise;

public class RecycleView extends AppCompatActivity implements RecyclerViewInterface {



    TextView textView;
    RecyclerView recycleView;
    Button deleteWorkout_btn;

    private final ExerciseLogic exercises = new ExerciseLogic();
    private final WorkoutLogic workoutLogic = new WorkoutLogic();
    private final LikedLogic liked = new LikedLogic();

    List<Exercise> exerciseList = null;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);

        textView = findViewById(R.id.workout_name);
        deleteWorkout_btn = findViewById(R.id.delete_button);
        //hide this button for now, only show if we are on a workout list
        deleteWorkout_btn.setVisibility(View.GONE);

        recycleView = findViewById(R.id.recycleView);
        recycleView.setLayoutManager(new LinearLayoutManager(this));

        setExerciseList(savedInstanceState);
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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Intent intent = getIntent();
        outState.putString("workoutTitle", intent.getStringExtra("workoutTitle"));
        outState.putString("muscleGroup", intent.getStringExtra("muscleGroup"));
        outState.putBoolean("likedExercises", intent.getBooleanExtra("likedExercises", false));

    }

    private void setExerciseList(Bundle savedInstanceState) {
        Intent intent = getIntent();

        //get the values from the intents. Only one of these fields should be set
        String workoutTitle = null;
        String muscleGroupTitle = null;
        Boolean likedExercises = false;

        if (intent.getExtras() != null) {
            workoutTitle = intent.getStringExtra("workoutTitle");
            muscleGroupTitle = intent.getStringExtra("muscleGroup");
            likedExercises = intent.getBooleanExtra("likedExercises", false);
        } else if (savedInstanceState != null) {
            workoutTitle = savedInstanceState.getString("workoutTitle");
            muscleGroupTitle = savedInstanceState.getString("muscleGroup");
            likedExercises = savedInstanceState.getBoolean("likedExercises");
        }


        if (workoutTitle != null) { //if a workout was selected, display the exercises in that workout
            textView.setText(workoutTitle);

            exerciseList = exercises.searchExerciseByWorkout(workoutTitle);

            deleteWorkout_btn.setVisibility(View.VISIBLE);

            //setup the deleteWorkout_btn
            String finalWorkoutTitle = workoutTitle;
            deleteWorkout_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //delete the workout
                    workoutLogic.deleteWorkout(finalWorkoutTitle);
                    onBackPressed();
                }
            });

        } else if(muscleGroupTitle != null) { //if a muscle group was selected, display the exercises in that muscle group
            textView.setText(muscleGroupTitle);

            exerciseList = exercises.searchExerciseByMuscleGroup(muscleGroupTitle);

        } else if(likedExercises) { //if liked exercises is true, show the liked exercises
            textView.setText("Favourites");

            exerciseList = liked.getLikedExercises();
        }
    }

    private void setAdapter() {
        CustomAdapter ad;

        if (exerciseList != null) {
            ad = new CustomAdapter(this, exerciseList, this);                 //creating new customAdapter
            recycleView.setAdapter(ad);                                                         //setting that adapter for recycler view

            //for drag and drop item but not saving yet
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
            itemTouchHelper.attachToRecyclerView(recycleView);

        } else {
            System.out.println("adapter is null");
        }
    }
}

