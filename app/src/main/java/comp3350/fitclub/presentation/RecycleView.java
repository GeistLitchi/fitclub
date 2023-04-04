package comp3350.fitclub.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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

public class RecycleView extends AppCompatActivity implements RecyclerViewInterface {

    TextView textView;
    RecyclerView recycleView;
    private final ExerciseLogic exercises = new ExerciseLogic();
    private final LikedLogic liked = new LikedLogic();

    List<Exercise> doing = null;

    private FrameLayout guideLayout;
    private boolean isFirstTime;

    @SuppressLint({"MissingInflatedId", "ResourceType"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);

        Intent intent = getIntent();

        String workoutTitle = intent.getStringExtra(WorkoutPage.EXTRA_NAME_WORKOUT);                //getting the value from intent key-value pair
        String muscleGroupTitle = intent.getStringExtra(MuscleGroupPage.EXTRA_NAME_MUSCLE);         //getting the value from intent key-value pair
        String title = intent.getStringExtra(MainActivity.EXTRA_NAME_MAIN);

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

        }else if(title != null)
        {
            textView.setText(title);

            doing = liked.getLikedExercises();
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

        //guide page
        guideLayout = findViewById(R.id.guide_layout);

        //only show the guide when users first enter the recycle page
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        isFirstTime = prefs.getBoolean("isFirstTime", true);
        if (isFirstTime) {
            showGuideLayer();
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("isFirstTime", false);
            editor.apply();
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

    @Override
    public void onItemLongClick(int position) {
        Exercise currExercise = doing.get(position);
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

    private void showGuideLayer()
    {
        ImageView gestureImage = (ImageView) findViewById(R.id.gesture_image);

        guideLayout.setVisibility(View.VISIBLE);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.gesture_anim);
        gestureImage.startAnimation(animation);
        guideLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guideLayout.setVisibility(View.GONE);
            }
        });
    }
}