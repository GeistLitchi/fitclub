package comp3350.fitclub.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import comp3350.fitclub.R;
import comp3350.fitclub.application.Main;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_NAME_MAIN = "comp3350.fitclub.presentation.extra.NAME_MAIN";

    //this flag will flip once the db file has been copied to device
    //this fill prevent the db name from being set multiple times which appends it to current
    //creating multiple nested directories
    private static boolean copyFlag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (copyFlag) {
            copyDatabaseToDevice();
            copyFlag = false;
        }

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button goToMuscleGroupBtn = (Button) findViewById(R.id.btn_go_to_muscle_group);
//        Button goToExercisesBtn = (Button) findViewById(R.id.btn_go_to_exercises_activity);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button goToWorkout = findViewById(R.id.btn_go_to_find_workout);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button goToFavorite = findViewById(R.id.btn_liked);
//        goToExercisesBtn.setOnClickListener(new View.OnClickListener()
//        {
//            public void onClick(View view)
//            {
//                Intent intent = new Intent(MainActivity.this, ExercisesActivity.class);
//                startActivity(intent);
//            }
//        });

        goToMuscleGroupBtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, MuscleGroupPage.class);
                startActivity(intent);
            }
        });


        goToWorkout.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
//                Intent intent = new Intent(MainActivity.this, WorkoutPage.class);
                Intent intent = new Intent(MainActivity.this, RecycleViewWorkoutList.class);
                startActivity(intent);
            }
        });

        goToFavorite.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                String title = "Favorite";
                Intent intent = new Intent(MainActivity.this,RecycleView.class);
                intent.putExtra(EXTRA_NAME_MAIN,title);
                startActivity(intent);
            }
        });
    }

    //Iterate through the assets folder and add their path to an array to be copied to device files
    private void copyDatabaseToDevice() {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try {

            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(assetNames, dataDirectory);
            String name = Main.getDbName();
            String result = dataDirectory.toString() + "/" + Main.getDbName();
            Main.setDBPathName(dataDirectory.toString() + "/" + Main.getDbName());

        } catch (final IOException ioe) {
            Toast.makeText(this, "Unable to access application data: " + ioe.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    //iterate through the provided asset array and copy any file to the device that doesn't already exist there
    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
        AssetManager assetManager = getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];

            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }

    @Override
    public void onDestroy(){super.onDestroy();}

}