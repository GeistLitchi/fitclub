package comp3350.fitclub.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import comp3350.fitclub.R;

public class CustomizableRecyclerView extends AppCompatActivity {
    EditText editText;
    Button btn_addExercise, btn_saveWorkout;
    RecyclerView customizableRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customizable_recycler_view);

        Intent intent = getIntent();

        editText = findViewById(R.id.CustomExerciseName);
        btn_addExercise = findViewById(R.id.btn_add_exercise);
        btn_saveWorkout = findViewById(R.id.btn_save_workout);

        customizableRecycleView = findViewById(R.id.customizaleRecycleView);
        customizableRecycleView.setLayoutManager(new LinearLayoutManager(this));                        // layout manager
        CustomAdapter ad;



        btn_addExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomizableRecyclerView.this,MuscleGroupPage.class);
                startActivity(intent);
            }
        });


    }
}