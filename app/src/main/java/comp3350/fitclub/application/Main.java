package comp3350.fitclub.application;

public class Main {
    private static String dbName = "SC";

    public static void setDBPathName(String newName) {
        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        dbName = newName;
    }

    public static String getDbName() {
        return dbName;
    }
}
