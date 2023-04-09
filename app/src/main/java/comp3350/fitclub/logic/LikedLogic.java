package comp3350.fitclub.logic;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import comp3350.fitclub.application.InitializePersistence;
import comp3350.fitclub.objects.Exercise;
import comp3350.fitclub.persistence.LikedPersistence;

public class LikedLogic {

    private LikedPersistence likedPersistence;

    public  LikedLogic()
    {
        likedPersistence = InitializePersistence.getLikedPersistence();
    }

    /**
     * Fetches all of the liked exercises
     * */
    public List<Exercise> getLikedExercises(){return likedPersistence.getLikedExercises();}

    /**
     * Add a new exercise to the liked list
     * */
    public void addLiked(Exercise exercise)
    {
        likedPersistence.insertLikedExercise(exercise);
    }

    /**
     * Checks if the exercise is in the liked list
     * */
    public boolean isContains(Exercise exercise)
    {
        return likedPersistence.isContainsExercise(exercise);
    }

    /**
     * Removes an exercise from a liked list
     * */
    public void deleteLiked(Exercise exercise)
    {
        if(likedPersistence.isContainsExercise(exercise))
        {
            likedPersistence.deleteLikedExercise(exercise);
        }
    }
}
