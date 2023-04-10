package comp3350.fitclub.persistence.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import comp3350.fitclub.objects.Exercise;
import comp3350.fitclub.objects.Workout;
import comp3350.fitclub.persistence.WorkoutPersistence;

public class WorkoutSQL implements WorkoutPersistence {
    private String path;

    public WorkoutSQL(String path) {
        this.path = path;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + path + ";shutdown=true", "SA", "");
    }

    /**
     * Extracts the data from the ResultSet into a new Workout Object
     * */
    private Workout extractData(ResultSet result) throws SQLException {
        String name = result.getString("name");
        String type = result.getString("type");
        int difficulty = result.getInt("difficulty");

        return new Workout(name, type, difficulty);
    }

    /**
     * Fetches all of the workouts
     * */
    @Override
    public ArrayList<Workout> getAllWorkouts() {
        ArrayList<Workout> workouts = new ArrayList<Workout>();

        try (Connection c = connect()) {

            PreparedStatement statement = c.prepareStatement("SELECT * FROM Workout");
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                workouts.add(extractData(result));
            }

            result.close();
            statement.close();

        } catch (SQLException e) {
            throw new PersistenceException(e);
        }

        return workouts;
    }

    /**
     * Inserts a new workout into the database
     * */
    @Override
    public Workout insertWorkout(Workout current) {
        try (Connection c = connect()) {
            PreparedStatement statement = c.prepareStatement("INSERT INTO Workout VALUES(?, ?, ?)");
            statement.setString(1, current.getName());
            statement.setString(2, current.getType());
            statement.setInt(3, current.getDifficulty());

            statement.executeUpdate();

            return current;
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    /**
     * Deletes a workout with a given name and all associated WorkoutExercise entries
     * */
    @Override
    public void deleteWorkout(String workoutName) {
        try (Connection c = connect()) {
            PreparedStatement statement = c.prepareStatement("DELETE FROM Workout WHERE UPPER(name) = UPPER(?)");
            statement.setString(1, workoutName);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }
}
