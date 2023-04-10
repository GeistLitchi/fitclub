package comp3350.fitclub.persistence.sql;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import comp3350.fitclub.objects.Exercise;
import comp3350.fitclub.persistence.LikedPersistence;

public class LikedExercisesSQL implements LikedPersistence {

    private String path;

    public LikedExercisesSQL(String path){this.path = path;}

    private Connection connect() throws SQLException
    {
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
     * Fetches all of the liked exercises
     * */
    @Override
    public List<Exercise> getLikedExercises() {
        List<Exercise> exercises = new ArrayList<Exercise>();

        try (Connection c = connect()) {

            PreparedStatement statement = c.prepareStatement("SELECT * FROM LIKEDEXERCISES");
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
     * Inserts a new exercise into the liked list
     * */
    @Override
    public Exercise insertLikedExercise(Exercise currentExercise) {
        try (Connection c = connect()) {
            PreparedStatement statement = c.prepareStatement("INSERT INTO LIKEDEXERCISES VALUES(?, ?, ?)");
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
     * Removes an exercise from the like list
     * */
    @Override
    public void deleteLikedExercise(Exercise currentExercise) {
        try (Connection c = connect()) {
            PreparedStatement statement = c.prepareStatement("DELETE FROM LIKEDEXERCISES WHERE exerciseName = ?");
            statement.setString(1, currentExercise.getExerciseName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    /**
     * Checks if the exercise is in the liked list
     * */
    @Override
    public boolean isContainsExercise(Exercise currentExercise) {
        boolean exists = false;
        try (Connection c = connect()) {
            PreparedStatement statement = c.prepareStatement("SELECT COUNT(*) FROM LikedExercises WHERE exerciseName = ?");
            statement.setString(1,currentExercise.getExerciseName());
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                exists = (result.getInt(1)>0);
            }

            result.close();
            statement.close();
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
        return exists;
    }
}
