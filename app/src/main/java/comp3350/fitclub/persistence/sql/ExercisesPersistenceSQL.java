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

public class ExercisesPersistenceSQL  implements ExercisesPersistence {
    private String path;

    public ExercisesPersistenceSQL(String path) {
        this.path = path;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + path + ";shutdown=true", "SA", "");
    }

    @Override
    public List<Exercise> getExercises() {
        return new ArrayList<Exercise>();
    }

    @Override
    public Exercise insertExercise(Exercise currentExercise) {
        return new Exercise("temp");
    }

    @Override
    public void deleteExercise(Exercise currentExercise) {

    }
}
