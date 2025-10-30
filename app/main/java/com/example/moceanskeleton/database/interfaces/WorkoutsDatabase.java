package com.example.moceanskeleton.database.interfaces;
import com.example.moceanskeleton.objects.Workout;
import java.util.*;

public interface WorkoutsDatabase {

    List<Workout> getAllWorkouts();
    Workout insertWorkout(Workout workout, ArrayList<Integer> exerciseIDs);

}