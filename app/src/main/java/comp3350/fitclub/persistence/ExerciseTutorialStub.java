package comp3350.fitclub.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comp3350.fitclub.objects.ExerciseTutorial;
import comp3350.fitclub.persistence.ExerciseTutorialPersistence;

public class ExerciseTutorialStub implements ExerciseTutorialPersistence {
    private List<ExerciseTutorial> tutorials;

    public ExerciseTutorialStub() {
        this.tutorials = new ArrayList<ExerciseTutorial>();

        tutorials.add(new ExerciseTutorial("deadlift",
                "Deadlifts are performed by lifting a barbell off the ground and locking out your hips.\n" +
                        "Begin by standing behind the bar with it nearly touching your shins. Feet should be about shoulder " +
                        "width apart. Keep the back straight and bring the hips back, bending a at the knees. Make sure your" +
                        "knees do not come forward overtop of your toes. Grip the bar just outside the legs.\n" +
                        "keep your back straight when lifting, driving through your legs, and bringing your hips forward." +
                        "Lockout at the top by bringing your hips all the way forward to the bar.\n" +
                        "Lower the weight by performing the steps above in reverse order.",
                "https://exrx.net/WeightExercises/ErectorSpinae/BBDeadlift"));


        tutorials.add(new ExerciseTutorial("squat"));
        tutorials.add(new ExerciseTutorial("plank"));
        tutorials.add(new ExerciseTutorial("dumbbell curl"));
        tutorials.add(new ExerciseTutorial("dumbell lateral raise"));
    }

    /*
    Get the tutorial for the specified exercise.
     */
    @Override
    public ExerciseTutorial getExerciseTutorial(String exerciseName) {
        ExerciseTutorial result = null;

        for (int i=0; i<tutorials.size(); i++) {
            if (tutorials.get(i).getExerciseName().equals(exerciseName)) {
                result = tutorials.get(i);
                break;
            }
        }

        return result;
    }
}
