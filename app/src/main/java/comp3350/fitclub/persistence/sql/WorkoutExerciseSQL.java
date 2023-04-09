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

    /**
     * Inserts all of the workoutExercises from the list into the database
     * */
    @Override
    public void insertWorkoutExercises(List<WorkoutExercise> workoutExerciseList) {
        if (workoutExerciseList.size() > 0) {

            try (Connection c = connect()) {
                //setup the statement
                String statementString = "INSERT INTO WorkoutExercise VALUES (?,?)";

                for (int i = 1; i < workoutExerciseList.size(); i++) {
                    statementString += ", (?,?)";
                }

                PreparedStatement statement = c.prepareStatement(statementString);

                //set the variables in statement
                int i = 1;
                for (WorkoutExercise workoutExercise : workoutExerciseList) {
                    statement.setString(i++, workoutExercise.getWorkoutName());
                    statement.setString(i++, workoutExercise.getExerciseName());
                }


                statement.executeUpdate();
            } catch (SQLException e) {
                throw new PersistenceException(e);
            }

        }
    }

    /**
     * Deletes a workout exercise from the database
     * */
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
