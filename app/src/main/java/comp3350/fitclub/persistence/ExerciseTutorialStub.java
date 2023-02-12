package comp3350.fitclub.persistence;

import java.util.ArrayList;
import java.util.List;

import comp3350.fitclub.objects.Exercise;

import comp3350.fitclub.objects.ExerciseTutorial;

public class ExerciseTutorialStub implements ExerciseTutorialPersistence {

    private ExercisesPersistence exerciseDB; //this will be used so we can iterate over all the exercises and add them
    private List<ExerciseTutorial> tutorials;

    public ExerciseTutorialStub() {
        tutorials = new ArrayList<ExerciseTutorial>();
        exerciseDB = new ExercisesData();

        /*
        Manually adding the back exercises so we have an idea of what the pages will look like when the
        descriptions are filled out
         */
        tutorials.add(new ExerciseTutorial("deadlift",
                "Deadlifts are performed by lifting a barbell off the ground and locking out your hips.\n\n" +
                        "Begin by standing behind the bar with it nearly touching your shins. Feet should be about shoulder " +
                        "width apart. Keep the back straight and bring the hips back, bending a at the knees. Make sure your" +
                        "knees do not come forward overtop of your toes. Grip the bar just outside the legs.\n\n" +
                        "keep your back straight when lifting, driving through your legs, and bringing your hips forward." +
                        "Lockout at the top by bringing your hips all the way forward to the bar.\n\n" +
                        "Lower the weight by performing the steps above in reverse order.",
                "https://exrx.net/WeightExercises/ErectorSpinae/BBDeadlift"));

        List<Exercise> list = exerciseDB.getExercises();

        addExercisesFromList(list);
    }

    /*
    Get the tutorial for the specified exercise.
     */
    @Override
    public ExerciseTutorial getExerciseTutorial(String exerciseName) {
        ExerciseTutorial result = null;

        for (int i=0; i<tutorials.size(); i++) {
            if (tutorials.get(i).getExerciseName().equalsIgnoreCase(exerciseName)) {
                result = tutorials.get(i);
                break;
            }
        }

        return result;
    }

    //helper function for stub
    private void addExercisesFromList(List<Exercise> toAdd) {
        for (int i=0; i<toAdd.size(); i++) {
            if (!toAdd.get(i).getExerciseName().equalsIgnoreCase("Deadlift")) { //manual override for now so our custom deadlift tutorial shows
                tutorials.add(new ExerciseTutorial(toAdd.get(i).getExerciseName()));
            }
        }
    }
}
