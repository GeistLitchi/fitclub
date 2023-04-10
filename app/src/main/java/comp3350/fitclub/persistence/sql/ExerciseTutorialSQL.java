package comp3350.fitclub.persistence.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import comp3350.fitclub.objects.ExerciseTutorial;
import comp3350.fitclub.persistence.ExerciseTutorialPersistence;

public class ExerciseTutorialSQL implements ExerciseTutorialPersistence {
    private String path;

    public ExerciseTutorialSQL(String path) {
        this.path = path;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + path + ";shutdown=true", "SA", "");
    }

    /**
     * Extracts the data from the ResultSet into a new ExerciseTutorial Object
     * */
    private ExerciseTutorial extractData(ResultSet result) throws SQLException {
        String exerciseName = result.getString("exerciseName");
        String body = result.getString("body");

        return new ExerciseTutorial(exerciseName, body);
    }

    /**
     * Fetches all of the exerciseTutorials
     * */
    @Override
    //returns a list of the exercises
    public ExerciseTutorial getExerciseTutorial(String exerciseName) {

        try (Connection c = connect()) {
            ExerciseTutorial exerciseTutorial = null;

            PreparedStatement statement = c.prepareStatement("SELECT * FROM ExerciseTutorial WHERE exerciseName = ?");
            statement.setString(1, exerciseName);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                exerciseTutorial = extractData(result);
            }

            result.close();
            statement.close();

            return exerciseTutorial;
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    /**
     * Inserts a new exerciseTutorial into the database
     * */
    @Override
    public ExerciseTutorial insertExerciseTutorial(ExerciseTutorial exerciseTutorial) {
        try (Connection c = connect()) {
            PreparedStatement statement = c.prepareStatement("INSERT INTO ExerciseTutorial VALUES(?, ?)");
            statement.setString(1, exerciseTutorial.getExerciseName());
            statement.setString(2, exerciseTutorial.getBody());

            statement.executeUpdate();

            return exerciseTutorial;
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }
}
