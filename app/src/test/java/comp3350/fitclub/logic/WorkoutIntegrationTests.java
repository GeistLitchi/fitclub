package comp3350.fitclub.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import comp3350.fitclub.Utilities.TestUtils;
import comp3350.fitclub.objects.Workout;
import comp3350.fitclub.persistence.WorkoutExercisePersistence;
import comp3350.fitclub.persistence.WorkoutPersistence;
import comp3350.fitclub.persistence.sql.WorkoutExerciseSQL;
import comp3350.fitclub.persistence.sql.WorkoutSQL;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class WorkoutIntegrationTests {
    private WorkoutLogic workoutLogic;
    private File tempDB;


    @Before
    public void setUp() throws IOException {
        this.tempDB = TestUtils.copyDB();
        final WorkoutPersistence workoutPersistence = new WorkoutSQL(this.tempDB.getAbsolutePath().replace(".script", ""));
        final WorkoutExercisePersistence workoutExercisePersistence= new WorkoutExerciseSQL(this.tempDB.getAbsolutePath().replace(".script", ""));
        this.workoutLogic = new WorkoutLogic(workoutPersistence, workoutExercisePersistence);
    }

    @Test
    public void testGetWorkouts() {
        final Workout workout1 = new Workout("Test_New_Workout_1", "Arms");
        final Workout workout2 = new Workout("Test_New_Workout_2", "Arms");
        final Workout workout3 = new Workout("Test_New_Workout_3", "Arms");

        workoutLogic.insertWorkout(workout1);
        workoutLogic.insertWorkout(workout2);
        workoutLogic.insertWorkout(workout3);

        List<Workout> workoutList = workoutLogic.getWorkouts();

        assertNotNull("Returned list should not be null", workoutList);
        assertTrue(workoutList.size() >= 3 );
    }

    @Test
    public void testInsertWorkout() {
        final Workout workout = new Workout("Test_New_Workout", "Arms");

        Workout response = workoutLogic.insertWorkout(workout);
        assertNotNull("Inserting valid workout should return same workout", response);
        assertTrue("Test_New_Workout".equals(workout.getName()) );
        assertTrue("Arms".equals(workout.getType()) );
    }

    @Test
    public void testInvalidInsertWorkout() {
        final Workout workout1 = new Workout("", "Arms");
        final Workout workout2 = new Workout("Test_New_Workout", "Arms");

        Workout response = workoutLogic.insertWorkout(workout1);
        assertNull("Inserting invalid workout should return null", response);

        response = workoutLogic.insertWorkout(workout2);
        assertNotNull("Inserting valid workout should return same workout", response);

        response = workoutLogic.insertWorkout(workout2);
        assertNull("Inserting same workout (invalid) should return null", response);
    }

    @Test
    public void testDeleteWorkout() {
        List<Workout> workoutList = workoutLogic.getWorkouts();
        final int size = workoutList.size();

        //insert an exercise in case the default data has none
        Workout workout = new Workout("Test_New_Workout", "Arms");
        workoutLogic.insertWorkout(workout);
        workoutList = workoutLogic.getWorkouts();

        assertTrue(workoutList.size() == size + 1 );

        workoutLogic.deleteWorkout(workout.getName());
        workoutList = workoutLogic.getWorkouts();

        assertTrue(workoutList.size() == size );
    }

    @Test
    public void testSearchWorkoutType() {
        final Workout workout = new Workout("Test_New_Workout", "Arms");
        workoutLogic.insertWorkout(workout);

        List<Workout> workoutList = workoutLogic.searchWorkoutType("Arms");

        assertNotNull("Returned list should not be null", workoutList);
        assertTrue(workoutList.size() >= 1);

        //searching by an invalid muscle type should return an empty list
        workoutList = workoutLogic.searchWorkoutType("Not a workout type");
        assertTrue(workoutList.size() == 0);
    }

    @Test
    public void testSearchByDifficulty() {
        final Workout workout = new Workout("Test_New_Workout", "Arms", 3);
        workoutLogic.insertWorkout(workout);

        List<Workout> workoutList = workoutLogic.searchByDifficulty(3);

        assertNotNull("Returned list should not be null", workoutList);
        assertTrue(workoutList.size() >= 1);

        //searching by an invalid number should return empty list
        workoutList = workoutLogic.searchByDifficulty(-1);
        assertTrue(workoutList.size() == 0);
    }

    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
    }
}

