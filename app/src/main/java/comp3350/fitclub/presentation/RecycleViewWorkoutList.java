package comp3350.fitclub.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import comp3350.fitclub.R;
import comp3350.fitclub.logic.WorkoutLogic;
import comp3350.fitclub.objects.Workout;

public class RecycleViewWorkoutList extends AppCompatActivity implements RecyclerViewInterface {

    private final WorkoutLogic workoutLogic = new WorkoutLogic();

    RecyclerView recycleView;
    List<Workout> workoutList = new ArrayList<Workout>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view_workout_list);

        recycleView = findViewById(R.id.recycleView);
        recycleView.setLayoutManager(new LinearLayoutManager(this));

        workoutList = workoutLogic.getWorkouts();

        CustomWorkoutAdapter adapter = new CustomWorkoutAdapter(this, workoutList, this);
        recycleView.setAdapter(adapter);

    }


    @Override
    public void onItemClick(int position) {

        String name = workoutList.get(position).getName();
        Intent intent = new Intent(this, RecycleView.class);
        intent.putExtra("workoutName", name );
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(int position) {

    }
}