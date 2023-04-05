package comp3350.fitclub.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import comp3350.fitclub.R;
import comp3350.fitclub.objects.Exercise;

public class CreateWorkout extends AppCompatActivity {

    EditText workoutname;
    String name;
    Spinner spinner;
    Button submit_btn;
    RecyclerView recyclerView;
    String[] muscleGroup = {"BICEPS", "SHOULDERS", "TRICEPS", "ABS", "LEGS", "BACK"};

    //-------------------------------------------------------------------------------------
    Exercise e1 = new Exercise("e1");
    Exercise e2 = new Exercise("e2");
    Exercise e3 = new Exercise("e3");
    Exercise e4 = new Exercise("e4");
    Exercise e5 = new Exercise("e5");
    Exercise e6 = new Exercise("e6");
    Exercise e7 = new Exercise("e7");

    ArrayList<Exercise> temp = new ArrayList<Exercise>();

    //----------------------------------------------------------------------------------------


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_workout);

        //-----------------------------------------------------------------------------------
        temp.add(e1);
        temp.add(e2);
        temp.add(e3);
        temp.add(e4);
        temp.add(e5);
        temp.add(e6);
        temp.add(e7);
        //-----------------------------------------------------------------------------------

        workoutname = findViewById(R.id.your_workout_name);
        spinner = findViewById(R.id.spinner);
        submit_btn = findViewById(R.id.submit_btn);
        recyclerView = findViewById(R.id.recycleView);

        //*************************************************************************************************************************************

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.addItemDecoration(new );
         MultiAdapter multiAdapter= new MultiAdapter(this, temp);
         recyclerView.setAdapter(multiAdapter);

        //**************************************************************************************************************************************
        ArrayAdapter<String> adapter = new ArrayAdapter<>(CreateWorkout.this, android.R.layout.simple_spinner_item, muscleGroup);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String value = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(CreateWorkout.this, "you selected "+value, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //*****************************************************************************************************************************************

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = workoutname.getText().toString();
//                Toast.makeText(CreateWorkout.this, "name of the workout is "+name, Toast.LENGTH_SHORT).show();
                ArrayList<Exercise> selectedList = multiAdapter.getSelected();
                int size = selectedList.size();
                System.out.println("the size of selected is "+ size);
                if(size>0){
                    System.out.println("this condition is true");
                    Toast.makeText(CreateWorkout.this, "name of the workout is "+name+" size of selected sting is "+size, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(CreateWorkout.this, "No Item was selected", Toast.LENGTH_SHORT).show();
                }

            }
        });
        //***************************************************************************************************************************************************
    }
}