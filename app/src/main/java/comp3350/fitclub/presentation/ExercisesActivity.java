package comp3350.fitclub.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import comp3350.fitclub.R;
import comp3350.fitclub.logic.ExerciseList;
import comp3350.fitclub.objects.Exercise;
import comp3350.fitclub.persistence.ExercisesData;

public class ExercisesActivity extends AppCompatActivity
{
    ListView listView;
    ArrayAdapter<Exercise> adapter;
    ExerciseList exerciseData = new ExerciseList();
    Button difficultySortButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        difficultySortButton = findViewById(R.id.DifficultySortButton);
        ExercisesData data = new ExercisesData();                           //creating new Exercise data class variable

        exerciseData.getExercises().addAll(data.getExercises());

        adapter = new ArrayAdapter<Exercise>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, exerciseData.getExercises())
        {
            public View getView(int position, View convertView, ViewGroup parent)
            {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(exerciseData.getExercises().get(position).getExerciseName());
                text2.setText(exerciseData.getExercises().get(position).getDescription());

                return view;
            }
        };

        listView = (ListView) findViewById(R.id.listExercises);
        listView.setAdapter(adapter);

        difficultySortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                sortExercisesByDifficulty();
            }
        });
    }

    private void sortExercisesByDifficulty()
    {
        exerciseData.sortByDifficulty();
        adapter.notifyDataSetChanged();
    }


}
