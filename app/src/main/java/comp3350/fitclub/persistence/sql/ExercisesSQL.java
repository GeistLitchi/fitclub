package comp3350.fitclub.persistence.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import comp3350.fitclub.persistence.ExercisesPersistence;
import comp3350.fitclub.objects.Exercise;

public class ExercisesSQL implements ExercisesPersistence {
    private String path;

    public ExercisesSQL(String path) {
        this.path = path;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + path + ";shutdown=true", "SA", "");
    }

    /**
     * Extracts the data from the ResultSet into a new Exercise Object
     * */
    private Exercise extractData(ResultSet result) throws SQLException {
        String exerciseName = result.getString("exerciseName");
        String muscleGroup = result.getString("muscleGroup");
        int difficulty = result.getInt("difficulty");

        return new Exercise(exerciseName, muscleGroup, difficulty);
    }

    /**
     * Fetches all of the exercises
     * */
    @Override
    public List<Exercise> getExercises() {
        List<Exercise> exercises = new ArrayList<Exercise>();

        try (Connection c = connect()) {

            PreparedStatement statement = c.prepareStatement("SELECT * FROM Exercises");
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                exercises.add(extractData(result));
            }

            result.close();
            statement.close();

        } catch (SQLException e) {
            throw new PersistenceException(e);
        }

        return exercises;
    }

    /**
     * Fetches all of the exercises in a given workout
     * */
    @Override
    public List<Exercise> getExercisesInWorkout(String workoutName) {
        List<Exercise> exercises = new ArrayList<Exercise>();

        try (Connection c = connect()) {

            PreparedStatement statement = c.prepareStatement("SELECT * FROM Exercises " +
                    "INNER JOIN WorkoutExercise ON UPPER(Exercises.exerciseName) = UPPER(WorkoutExercise.exerciseName) " +
                    "WHERE UPPER(WorkoutExercise.workoutName) = UPPER(?)");
            statement.setString(1, workoutName);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                exercises.add(extractData(result));
            }

            result.close();
            statement.close();

        } catch (SQLException e) {
            throw new PersistenceException(e);
        }

        return exercises;
    }

    /**
     * Inserts a new exercise into the database
     * */
    @Override
    public Exercise insertExercise(Exercise currentExercise) {

        try (Connection c = connect()) {
            PreparedStatement statement = c.prepareStatement("INSERT INTO Exercises VALUES(?, ?, ?)");
            statement.setString(1, currentExercise.getExerciseName());
            statement.setString(2, currentExercise.getBodyPart());
            statement.setInt(3, currentExercise.getDifficulty());

            statement.executeUpdate();



            return currentExercise;
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }

    }

    /**
     * Removes an exercise from the database
     * */
    @Override
    public void deleteExercise(Exercise currentExercise) {

        try (Connection c = connect()) {
            PreparedStatement statement = c.prepareStatement("DELETE FROM Exercises WHERE exerciseName = ?");
            statement.setString(1, currentExercise.getExerciseName());
            statement.executeUpdate();

            statement = c.prepareStatement("DELETE FROM ExerciseTutorial WHERE exerciseName = ?");
            statement.setString(1, currentExercise.getExerciseName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }
}
