package comp3350.fitclub.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comp3350.fitclub.objects.Exercise;

public class ExercisesData implements ExercisesPersistence
{
    private List<Exercise> exercises;

    public ExercisesData()
    {
        this.exercises =new ArrayList<>();
        exercises.add(new Exercise("Deadlift","back",3, "Deadlift is a weight training exercise that mainly uses the back muscles\ncan be performed using dumbbells, barbells, or kettlebells with one hand or two hands"));
        exercises.add(new Exercise("Incline Dumbell Row","back", 1,"Description of incline dumbell row"));
        exercises.add(new Exercise("Back Extension","back", 1,"Description of back extension"));
        exercises.add(new Exercise("Reverse Fly","back",2,"Description of reverse fly"));
        exercises.add(new Exercise("Superman","back", 1,"Description of superman"));


        exercises.add(new Exercise("Squat","legs", 2,"Description of squat"));
        exercises.add(new Exercise("Lunges", "legs",1,"Description of lunges"));
        exercises.add(new Exercise("Dumbell Stepup", "legs",2,"Description of dumbell stepup"));
        exercises.add(new Exercise("Leg Press","legs",2,"Description of leg press"));
        exercises.add(new Exercise("Pause Squat","legs",3,"Description of pause squat"));

        exercises.add(new Exercise("Plank","core",3,"Description of plank"));
        exercises.add(new Exercise("Mountain Climber", "core",1,"Description of mountain climber"));
        exercises.add(new Exercise("Barbell Rollout","core",2,"Description of barbell rollout"));
        exercises.add(new Exercise("Russian Twist","core",1,"Description of russian twist"));
        exercises.add(new Exercise("Flutter Kick","core",1,"Description of flutter kick"));

        exercises.add(new Exercise("Dumbbell Curls","arms",1,"Description of dumbbell curl"));
        exercises.add(new Exercise("Push-ups", "arms",1,"Description of push ups"));
        exercises.add(new Exercise("Burpees", "arms",2,"Description of burpees"));
        exercises.add(new Exercise("Barbell Curl", "arms",1,"Description of barbell curl"));
        exercises.add(new Exercise("Cable Curl", "arms",2,"Description of cable curl"));

        exercises.add(new Exercise("Dumbbell Lateral Raises", "shoulder",2, "Description of dumbbell lateral raises"));
        exercises.add(new Exercise("Overhead Press","shoulder",2,"Description of overhead press"));
        exercises.add(new Exercise("Arnold Press", "shoulder",3,"Description of Arnold Press"));
        exercises.add(new Exercise("Bottoms-up Kettlebell press", "shoulder",1,"Description of bottoms up kettlebell press"));
        exercises.add(new Exercise("Incline Y Raise", "shoulder",3,"Description of incline Y raise"));

        exercises.add(new Exercise("Bench Press","chest",2, "Description of bench press"));
        exercises.add(new Exercise("Cable Cross","chest",1,"Description of cable cross"));
        exercises.add(new Exercise("Incline Bench Press","chest",3,"Description of incline bench press"));
        exercises.add(new Exercise("Dumbell Flye","chest",2,"Description of dumbell flye"));
        exercises.add(new Exercise("Landmine Press","chest",3,"Description of landmine press"));

    }

    @Override
    public List<Exercise> getExercises()
    {
        return exercises;
    }

    @Override
    public Exercise insertExercise(Exercise currentExercise)
    {
        exercises.add(currentExercise);
        return currentExercise;
    }

    @Override
    public void deleteExercise(Exercise currentExercise)
    {
        int index;
        index = exercises.indexOf(currentExercise);
        if(index >= 0)
        {
            exercises.remove(index);
        }
    }
}
