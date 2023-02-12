package comp3350.fitclub.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comp3350.fitclub.objects.Exercise;
import comp3350.fitclub.persistence.STUBdatabase;

import comp3350.fitclub.objects.ExerciseTutorial;
import comp3350.fitclub.logic.ExerciseList;
import comp3350.fitclub.persistence.ExerciseTutorialPersistence;

public class ExerciseTutorialStub implements ExerciseTutorialPersistence {

    private STUBdatabase stubDatabase; //this will be used so we can iterate over all the exercises and add them
    private List<ExerciseTutorial> tutorials;

    public ExerciseTutorialStub() {
        tutorials = new ArrayList<ExerciseTutorial>();
        stubDatabase = new STUBdatabase();

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


        tutorials.add(new ExerciseTutorial("incline dumbell row"));
        tutorials.add(new ExerciseTutorial("back extention"));
        tutorials.add(new ExerciseTutorial("reverse fly"));
        tutorials.add(new ExerciseTutorial("superman"));

        ExerciseList list = stubDatabase.getAbs_exe();
        addExercisesFromList(list.getExercises());

        list = stubDatabase.getBiceps_exe();
        addExercisesFromList(list.getExercises());

        list = stubDatabase.getChest_exe();
        addExercisesFromList(list.getExercises());

        list = stubDatabase.getLegs_exe();
        addExercisesFromList(list.getExercises());

        list = stubDatabase.getShoulder_exe();
        addExercisesFromList(list.getExercises());

        list = stubDatabase.getLowerbody_workout();
        addExercisesFromList(list.getExercises());

        list = stubDatabase.getUpperbody_workout();
        addExercisesFromList(list.getExercises());
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
    private void addExercisesFromList(ArrayList<Exercise> toAdd) {
        for (int i=0; i<toAdd.size(); i++) {
            tutorials.add(new ExerciseTutorial(toAdd.get(i).getExerciseName()));
        }
    }
}
