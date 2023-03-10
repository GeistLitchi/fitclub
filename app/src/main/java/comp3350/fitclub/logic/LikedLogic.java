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

    public List<Exercise> getLikedExercises(){return likedPersistence.getLikedExercises();}

    public void addLiked(Exercise exercise)
    {
        likedPersistence.insertLikedExercise(exercise);
    }

    public boolean isContains(Exercise exercise)
    {
        return likedPersistence.isContainsExercise(exercise);
    }

    public void deleteLiked(Exercise exercise)
    {
        if(likedPersistence.isContainsExercise(exercise))
        {
            likedPersistence.deleteLikedExercise(exercise);
        }
    }
}
