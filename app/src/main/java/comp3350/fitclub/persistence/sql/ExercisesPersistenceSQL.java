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

        try (final Connection c = connect()) {

            //temporary way to insert data into database
            //final Statement st = c.createStatement();
            //ResultSet result;
            //result = st.executeQuery("CREATE MEMORY TABLE PUBLIC.exercises(exerciseName VARCHAR(64) NOT NULL PRIMARY KEY,muscleGroup VARCHAR(64),difficulty int,description VARCHAR(256))");
            //result = st.executeQuery("INSERT INTO exercises VALUES('Deadlift','back',3, 'Deadlift is a weight training exercise that mainly uses the back muscles can be performed using dumbbells, barbells, or kettlebells with one hand or two hands')");
            //result.close();
            //st.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + path + ";shutdown=true", "SA", "");
    }

    private Exercise extractData(ResultSet result) throws SQLException {
        String name = result.getString("exerciseName");
        String muscleGroup = result.getString("muscleGroup");
        int difficulty = result.getInt("difficulty");
        String description = result.getString("description");

        return new Exercise(name, muscleGroup, difficulty, description);
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
            e.printStackTrace();
        }

        return exercises;
    }

    @Override
    public Exercise insertExercise(Exercise currentExercise) {
        return new Exercise("temp");
    }

    @Override
    public void deleteExercise(Exercise currentExercise) {

    }
}
