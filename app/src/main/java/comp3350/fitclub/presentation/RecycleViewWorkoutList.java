package comp3350.fitclub.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import comp3350.fitclub.R;
import comp3350.fitclub.logic.WorkoutLogic;
import comp3350.fitclub.objects.Workout;

public class RecycleViewWorkoutList extends AppCompatActivity implements RecyclerViewInterface {

    private final WorkoutLogic workoutLogic = new WorkoutLogic();

    RecyclerView recycleView;
    Button creatWorkout_btn;
    List<Workout> workoutList = new ArrayList<Workout>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view_workout_list);

        creatWorkout_btn = findViewById(R.id.createWorkout_btn);

        recycleView = findViewById(R.id.recycleView);
        recycleView.setLayoutManager(new LinearLayoutManager(this));

        workoutList = workoutLogic.getWorkouts();

        CustomWorkoutAdapter adapter = new CustomWorkoutAdapter(this, workoutList, this);
        recycleView.setAdapter(adapter);

        creatWorkout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecycleViewWorkoutList.this, CreateWorkout.class);
                startActivity(intent);
            }
        });

    }

    /*
    * goToWorkout.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
//                Intent intent = new Intent(MainActivity.this, WorkoutPage.class);
                Intent intent = new Intent(MainActivity.this, RecycleViewWorkoutList.class);
                startActivity(intent);
            }
        });
    * */


    @Override
    public void onItemClick(int position) {

        String name = workoutList.get(position).getName();
        Intent intent = new Intent(this, RecycleView.class);
        intent.putExtra("workoutTitle", name );
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(int position) {

    }
}