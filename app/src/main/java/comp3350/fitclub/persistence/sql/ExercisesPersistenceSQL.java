package comp3350.fitclub.persistence.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import comp3350.fitclub.persistence.ExercisesPersistence;
import comp3350.fitclub.objects.Exercise;

public class ExercisesPersistenceSQL implements ExercisesPersistence {
    private String path;

    public ExercisesPersistenceSQL(String path) {
        this.path = path;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + path + ";shutdown=true", "SA", "");
    }

    private Exercise extractData(ResultSet result) throws SQLException {
        String name = result.getString("exerciseName");
        String muscleGroup = result.getString("muscleGroup");
        int difficulty = result.getInt("difficulty");

        return new Exercise(name, muscleGroup, difficulty);
    }

    @Override
    //returns a list of the exercises
    public List<Exercise> getExercises() {
        List<Exercise> exercises = new ArrayList<Exercise>();

        try (Connection c = connect()) {

            Statement statement = c.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM Exercises");

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

    //inserts the new exercise into the database
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

    //removes the selected exercise from the database
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
