package comp3350.fitclub.persistence;

import java.util.ArrayList;
import java.util.List;

import comp3350.fitclub.objects.Exercise;

public class ExercisesDataStub implements ExercisesPersistence
{
    private List<Exercise> exercises;

    public ExercisesDataStub()
    {
        exercises =new ArrayList<>();
        populateList();
    }

    //test data for exercises stub
    public void populateList() {
        exercises.add(new Exercise("Deadlift","back",3));
        exercises.add(new Exercise("Incline Dumbell Row","back", 1));
        exercises.add(new Exercise("Back Extension","back", 1));
        exercises.add(new Exercise("Reverse Fly","back",2));
        exercises.add(new Exercise("Superman","back", 1));


        exercises.add(new Exercise("Squat","legs", 2));
        exercises.add(new Exercise("Lunges", "legs",1));
        exercises.add(new Exercise("Dumbell Stepup", "legs",2));
        exercises.add(new Exercise("Leg Press","legs",2));
        exercises.add(new Exercise("Pause Squat","legs",3));

        exercises.add(new Exercise("Plank","core",3));
        exercises.add(new Exercise("Mountain Climber", "core",1));
        exercises.add(new Exercise("Barbell Rollout","core",2));
        exercises.add(new Exercise("Russian Twist","core",1));
        exercises.add(new Exercise("Flutter Kick","core",1));

        exercises.add(new Exercise("Dumbbell Curls","arms",1));
        exercises.add(new Exercise("Push-ups", "arms",1));
        exercises.add(new Exercise("Burpees", "arms",2));
        exercises.add(new Exercise("Barbell Curl", "arms",1));
        exercises.add(new Exercise("Cable Curl", "arms",2));

        exercises.add(new Exercise("Dumbbell Lateral Raises", "shoulder",2));
        exercises.add(new Exercise("Overhead Press","shoulder",2));
        exercises.add(new Exercise("Arnold Press", "shoulder",3));
        exercises.add(new Exercise("Bottoms-up Kettlebell press", "shoulder",1));
        exercises.add(new Exercise("Incline Y Raise", "shoulder",3));

        exercises.add(new Exercise("Bench Press","chest",2));
        exercises.add(new Exercise("Cable Cross","chest",1));
        exercises.add(new Exercise("Incline Bench Press","chest",3));
        exercises.add(new Exercise("Dumbell Flye","chest",2));
        exercises.add(new Exercise("Landmine Press","chest",3));
    }

    @Override
    public List<Exercise> getExercises()
    {
        return exercises;
    }

    @Override
    public List<Exercise> getExercisesInWorkout(String workoutName) {
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

    public void clearExercises() {
        exercises = new ArrayList<Exercise>();
    }
}
