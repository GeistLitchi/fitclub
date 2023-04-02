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
import comp3350.fitclub.objects.Exercise;
import comp3350.fitclub.objects.Workout;

public class RecycleViewWorkoutList extends AppCompatActivity implements RecyclerViewInterface {

    //--------------------------------------------------------------------------

    Exercise e1 = new Exercise("e1");
    Exercise e2 = new Exercise("e2");
    Exercise e3 = new Exercise("e3");
    Exercise e4 = new Exercise("e4");
    Exercise e5 = new Exercise("e5");
    Exercise e6 = new Exercise("e6");
    Exercise e7 = new Exercise("e7");
    Exercise e8 = new Exercise("e8");
    Exercise e9 = new Exercise("e9");

    Workout w1 = new Workout("w1");
    Workout w2 = new Workout("w2");
    Workout w3 = new Workout("w3");

    List<Workout> workoutList = new ArrayList<Workout>();


    //---------------------------------------------------------------------------

    RecyclerView recycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view_workout_list);

        recycleView = findViewById(R.id.recycleView);
        recycleView.setLayoutManager(new LinearLayoutManager(this));

        //---------------------------------------------------------------------------------------
        w1.addExercise(e1);
        w1.addExercise(e2);
        w1.addExercise(e3);

        w2.addExercise(e4);
        w2.addExercise(e5);
        w2.addExercise(e6);

        w3.addExercise(e7);
        w3.addExercise(e8);
        w3.addExercise(e9);

        workoutList.add(w1);
        workoutList.add(w2);
        workoutList.add(w3);

        //----------------------------------------------------------------------------------------


        CustomWorkoutAdapter adapter = new CustomWorkoutAdapter(this, workoutList, this);
        recycleView.setAdapter(adapter);

    }


    @Override
    public void onItemClick(int position) {

        String name = workoutList.get(position).getName();
        Intent intent = new Intent(this, RecycleView.class);
        intent.putExtra("exerciseName", name );
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(int position) {

    }
}