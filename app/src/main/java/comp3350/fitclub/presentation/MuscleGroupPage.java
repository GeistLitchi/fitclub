package comp3350.fitclub.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import comp3350.fitclub.R;

public class MuscleGroupPage extends AppCompatActivity implements View.OnClickListener{

    public static final String EXTRA_NAME_MUSCLE = "comp3350.fitclub.presentation.extar.NAME_MUSCLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muscle_group_page);

        //colleting all the button in java file
        Button btn_1 = findViewById(R.id.bicep_btn);
        Button btn_2 = findViewById(R.id.legs_btn);
        Button btn_3 = findViewById(R.id.abs_btn);
        Button btn_4 = findViewById(R.id.back_btn);
        Button btn_5 = findViewById(R.id.chest_btn);
        Button btn_6 = findViewById(R.id.shoulder_btn);


        //giving instance of each button to themselves for implementing on click function
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String muscleName;                      // name of the muscle group you clicked
        Button buttonClicked;                   // to store id of the button you clicked
        Intent intent = new Intent(this, RecycleView.class);
        switch(view.getId()){
            case R.id.bicep_btn:
                buttonClicked = findViewById(R.id.bicep_btn);
                muscleName = buttonClicked.getText().toString();                            //getting the name of the text stored in buttonClicked
                break;
            case R.id.legs_btn:
                buttonClicked = findViewById(R.id.legs_btn);
                muscleName = buttonClicked.getText().toString();                            //getting the name of the text stored in buttonClicked
                break;
            case R.id.abs_btn:
                buttonClicked = findViewById(R.id.abs_btn);
                muscleName = buttonClicked.getText().toString();                            //getting the name of the text stored in buttonClicked
                break;
            case R.id.back_btn:
                buttonClicked = findViewById(R.id.back_btn);
                muscleName = buttonClicked.getText().toString();                            //getting the name of the text stored in buttonClicked
                break;
            case R.id.chest_btn:
                buttonClicked = findViewById(R.id.chest_btn);
                muscleName = buttonClicked.getText().toString();                            //getting the name of the text stored in buttonClicked
                break;
            case R.id.shoulder_btn:
                buttonClicked = findViewById(R.id.shoulder_btn);
                muscleName = buttonClicked.getText().toString();                            //getting the name of the text stored in buttonClicked
                break;


            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
        intent.putExtra(EXTRA_NAME_MUSCLE,muscleName);                                      //setting the key and value pair so that we can use that in other activity
        startActivity(intent);                                                              // starting new activity
    }
}