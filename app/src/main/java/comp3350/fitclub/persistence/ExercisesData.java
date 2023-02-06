package comp3350.fitclub.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comp3350.fitclub.objects.Exercise;

public class ExercisesData
{
    private List<Exercise> exercises;

    public ExercisesData()
    {
        this.exercises =new ArrayList<>();
        exercises.add(new Exercise("deadlift","back",2, "Deadlift is a weight training exercise that mainly uses the back muscles\ncan be performed using dumbbells, barbells, or kettlebells with one hand or two hands"));
        exercises.add(new Exercise("squat","leg", 1,"Description of squat"));
        exercises.add(new Exercise("plank","core",3,"Description of plank"));
        exercises.add(new Exercise("dumbbell curls","arm",1,"Description of dumbbell curl"));
        exercises.add(new Exercise("dumbbell lateral raises", "shoulder",2, "Description of dumbbell lateral raises"));
    }

    public List<Exercise> getExercises()
    {
        return Collections.unmodifiableList(exercises);
    }
}
