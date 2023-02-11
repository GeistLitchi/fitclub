package comp3350.fitclub.logic;

import comp3350.fitclub.objects.Workout;
import comp3350.fitclub.objects.Exercise;
import java.util.ArrayList;

public class WorkoutLogic {

    public WorkoutLogic(){}

    //calculate the difficulty of the workout based on average difficulty of exercises
    public int calcDifficulty(Workout current) {
        int difficulty = 0;

        if(current != null && current.getSize() > 0) {
            ArrayList<Exercise> currentExercises = current.getWorkoutExercises();

            for(int i=0; i<currentExercises.size(); i++) {
                difficulty += currentExercises.get(i).getDifficulty();
            }
            difficulty /= current.getSize();
        }

        return difficulty;
    }
}
