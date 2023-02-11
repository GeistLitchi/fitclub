package comp3350.fitclub.persistence.stubs;

import java.util.ArrayList;
import java.util.List;

import comp3350.fitclub.objects.Workout;
import comp3350.fitclub.persistence.WorkoutData;

public class WorkoutDataStub implements WorkoutData {

    private List<Workout> workouts;

    //-------- constructor --------//
    public WorkoutDataStub() {
        this.workouts = new ArrayList<Workout>();

        workouts.add(new Workout("UPPER BODY 1", "UPPER"));
        workouts.add(new Workout("UPPER BODY 2", "UPPER"));
        workouts.add(new Workout("LOWER BODY 1", "LOWER"));
        workouts.add(new Workout("LOWER BODY 2", "LOWER"));
    }

    @Override //'query the DB' of workouts by type
    public List<Workout> getWorkoutType(String workoutType) {
        List<Workout> list = new ArrayList<Workout>();

        if(workoutType != null) {
            //search list of workouts for matching types
            for(int i=0; i<workouts.size(); i++) {
                String type = ((Workout)(workouts.get(i))).getType();

                if(type.equals(workoutType.toUpperCase())) {
                    list.add(workouts.get(i));
                }
            }
        }

        return list;
    }

    @Override
    public Workout insertWorkout(Workout current) {
        workouts.add(current);
        return current;
    }

    @Override
    public Workout updateWorkout(Workout current) {

        return null;
    }

    @Override
    public void deleteWorkout(Workout current) {
        //future implementations
    }

    //To fill out made workouts for now
    private void fillWorkout(Workout toFill) {

    }
}
