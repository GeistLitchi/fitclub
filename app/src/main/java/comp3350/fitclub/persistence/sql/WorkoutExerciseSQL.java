package comp3350.fitclub.persistence.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import comp3350.fitclub.objects.Exercise;
import comp3350.fitclub.objects.Workout;
import comp3350.fitclub.objects.WorkoutExercise;
import comp3350.fitclub.persistence.WorkoutExercisePersistence;

public class WorkoutExerciseSQL implements WorkoutExercisePersistence {
    private String path;

    public WorkoutExerciseSQL(String path) {
        this.path = path;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + path + ";shutdown=true", "SA", "");
    }

    @Override
    public WorkoutExercise insertWorkoutExercise(WorkoutExercise workoutExercise) {
        try (Connection c = connect()) {
            PreparedStatement statement = c.prepareStatement("INSERT INTO WorkoutExercise VALUES(?, ?)");
            statement.setString(1, workoutExercise.getWorkoutName());
            statement.setString(2, workoutExercise.getExerciseName());

            statement.executeUpdate();

            return workoutExercise;
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public void deleteWorkoutExercisesByWorkoutName(String workoutName) {
        try (Connection c = connect()) {
            PreparedStatement statement = c.prepareStatement("DELETE FROM WorkoutExercise WHERE workoutName = ?");
            statement.setString(1, workoutName);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }
}
