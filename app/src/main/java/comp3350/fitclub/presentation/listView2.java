package comp3350.fitclub.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import comp3350.fitclub.R;
import comp3350.fitclub.logic.ExerciseList;
import comp3350.fitclub.objects.Exercise;
import comp3350.fitclub.persistence.STUBdatabase;

public class listView2 extends AppCompatActivity implements RecyclerViewInterface {

    TextView textView;
    ListView listView;
    RecyclerView recycleView;
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

    ArrayList<Exercise> doing = null;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view2);

        Intent intent = getIntent();

        String workoutTitle = intent.getStringExtra(WorkoutPage.EXTRA_NAME_WORKOUT);
        String muscleGroupTitle = intent.getStringExtra(MuscleGroupPage.EXTRA_NAME_MUSCLE);

        textView = findViewById(R.id.workout_name);


//        listView = findViewById(R.id.listView2);
        recycleView = findViewById(R.id.recycleView);
        recycleView.setLayoutManager(new LinearLayoutManager(this));

//        CustomAdapter2 ad = null;
        CustomAdapter ad = null;


        if(workoutTitle != null){
            textView.setText(workoutTitle);

            switch (workoutTitle){

                case "UPPER BODY":
                    doing = upperbody_workout.getExercises();
                    //ad = new CustomAdapter2(this, R.layout.custom_layout2, upperbody_workout.getExercises());
                    //listView.setAdapter(ad);
                    break;
                case "LOWER BODY":
                    doing = lowerbody_workout.getExercises();
                    //ad = new CustomAdapter2(this, R.layout.custom_layout2, lowerbody_workout.getExercises());
                    //listView.setAdapter(ad);
                    break;
            }

        }else if(muscleGroupTitle != null){

            textView.setText(muscleGroupTitle);

            switch (muscleGroupTitle){
                case "BICEPS":
                    doing = bicep_exe.getExercises();
                    //ad = new CustomAdapter2(this, R.layout.custom_layout2, bicep_exe.getExercises());
                    //listView.setAdapter(ad);
                    break;
                case "LEGS":
                    doing = legs_exe.getExercises();
                    //ad = new CustomAdapter2(this, R.layout.custom_layout2, legs_exe.getExercises());
                    //listView.setAdapter(ad);
                    break;
                case "ABS":
                    doing = abs_exe.getExercises();
                    //ad = new CustomAdapter2(this, R.layout.custom_layout2, abs_exe.getExercises());
                    //listView.setAdapter(ad);
                    break;
                case "BACK":
                    doing = back_exe.getExercises();
                    //ad = new CustomAdapter2(this, R.layout.custom_layout2, back_exe.getExercises());
                    //listView.setAdapter(ad);
                    break;
                case "CHEST":
                    doing = chest_exe.getExercises();
                    //ad = new CustomAdapter2(this, R.layout.custom_layout2, chest_exe.getExercises());
                    //listView.setAdapter(ad);
                    break;
                case "SHOULDER":
                    doing = shoulder_exe.getExercises();
//                    ad = new CustomAdapter2(this, R.layout.custom_layout2, shoulder_exe.getExercises());
                    //listView.setAdapter(ad);
                    break;
            }

        }
        if (doing != null){
//            ad = new CustomAdapter2(this, R.layout.custom_layout2, doing);
//            listView.setAdapter(ad);
            ad = new CustomAdapter(this, doing, this);
            recycleView.setAdapter(ad);


            //for darging item but not sving yet
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

            recyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);



            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        }
    };


    @Override
    public void onItemClick(int position) {

        Toast.makeText(this, doing.get(position).getExerciseName(), Toast.LENGTH_SHORT).show();

    }
}

