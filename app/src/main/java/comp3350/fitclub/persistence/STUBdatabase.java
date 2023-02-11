package comp3350.fitclub.persistence;

import comp3350.fitclub.logic.ExerciseList;
import comp3350.fitclub.objects.Exercise;

public class STUBdatabase {

    // back excersice
    private Exercise deadLift = new Exercise("deadlift","back",3);
    private Exercise inclineDumbellRow = new Exercise("incline dumbell row","back",2);
    private Exercise backExtention = new Exercise("back extention","back",1);
    private Exercise reverseFly = new Exercise("reverse fly","back",2);
    private Exercise superman = new Exercise("superman","back",1);


    //core excresice
    private Exercise mountaiunClimb = new Exercise("mountain climber", "core",1);
    private Exercise barbellRollout = new Exercise("barbell rollout","core",2);
    private Exercise russianTwist = new Exercise("riussian twist","core",1);
    private Exercise flutterKicks = new Exercise("flutter kick","core",1);
    private Exercise legRaise = new Exercise("leg raise","core",2);
    private Exercise mountainClimber = new Exercise("mountain climber","core",3);


    //biceps excresice
    private Exercise pushUps = new Exercise("push ups", "arm",1);
    private Exercise burpees = new Exercise("burpees", "arm",2);
    private Exercise barbellCurl = new Exercise("barbell curl", "arm",1);
    private Exercise cableCurl = new Exercise("cable curl", "arm",2);
    private Exercise seatedDumbellCurl = new Exercise("seated dumbell curl", "arm",2);


    //legs excresice
    private Exercise lunges = new Exercise("lunges", "leg",1);
    private Exercise squat = new Exercise("squat", "leg",1);
    private Exercise dumbellStepup = new Exercise("dumbell stepup", "leg",2);
    private Exercise legpress = new Exercise("leg press","leg",2);
    private Exercise pauseSquat = new Exercise("pause squat","leg",3);
    private Exercise ketSwing = new Exercise("kettlebell Swing","leg",3);


    //chest excresice
    private Exercise benchPress = new Exercise("bench press","chest",2);
    private Exercise cabelCross = new Exercise("cable cross","chest",1);
    private Exercise inclineBenchPress = new Exercise("incline bench press","chest",3);
    private Exercise dumbellFlye = new Exercise("dumbell flye","chest",2);
    private Exercise landminePress = new Exercise("landmine press","chest",3);
    private Exercise pullover = new Exercise("pullover","chest",1);


    //shoulder excresice
    private Exercise overheadPress = new Exercise("overhead press","shoulder",2);
    private Exercise arnoldPress = new Exercise("Arnold Press", "shoulder",3);
    private Exercise bottomsUpKetbellPress = new Exercise("bottoms up kettlebell press", "shoulder",1);
    private Exercise lateralRaise = new Exercise("lateral raise", "shoulder",1);
    private Exercise leanLateralRaise = new Exercise("leaning lateral raise", "shoulder",2);
    private Exercise inclineYRaise = new Exercise("incline Y raise", "shoulder",3);




    private ExerciseList biceps_exe;
    private ExerciseList legs_exe;
    private ExerciseList back_exe;
    private ExerciseList abs_exe;
    private ExerciseList chest_exe;
    private ExerciseList shoulder_exe;

    private ExerciseList upperbody_workout;
    private ExerciseList lowerbody_workout;



    //constructor
    public STUBdatabase(){
        //initializing all excresicelist
        biceps_exe = new ExerciseList();
        legs_exe = new ExerciseList();
        back_exe = new ExerciseList();
        abs_exe = new ExerciseList();
        chest_exe = new ExerciseList();
        shoulder_exe = new ExerciseList();
        upperbody_workout = new ExerciseList();
        lowerbody_workout = new ExerciseList();

        populateList();

    }

    //populating each excersicelist
    public void populateList(){

        //------------------------------MUSCLE GORUP-------------------------------------
        biceps_exe.addExercise(pushUps);
        biceps_exe.addExercise(burpees);
        biceps_exe.addExercise(barbellCurl);
        biceps_exe.addExercise(cableCurl);
        biceps_exe.addExercise(seatedDumbellCurl);

        legs_exe.addExercise(lunges);
        legs_exe.addExercise(squat);
        legs_exe.addExercise(dumbellStepup);
        legs_exe.addExercise(legpress);
        legs_exe.addExercise(pauseSquat);
        legs_exe.addExercise(ketSwing);

        back_exe.addExercise(deadLift);
        back_exe.addExercise(inclineDumbellRow);
        back_exe.addExercise(backExtention);
        back_exe.addExercise(reverseFly);
        back_exe.addExercise(superman);

        abs_exe.addExercise(mountaiunClimb);
        abs_exe.addExercise(barbellRollout);
        abs_exe.addExercise(russianTwist);
        abs_exe.addExercise(flutterKicks);
        abs_exe.addExercise(legRaise);
        abs_exe.addExercise(mountainClimber);

        chest_exe.addExercise(benchPress);
        chest_exe.addExercise(cabelCross);
        chest_exe.addExercise(inclineBenchPress);
        chest_exe.addExercise(dumbellFlye);
        chest_exe.addExercise(landminePress);
        chest_exe.addExercise(pullover);

        shoulder_exe.addExercise(overheadPress);
        shoulder_exe.addExercise(arnoldPress);
        shoulder_exe.addExercise(bottomsUpKetbellPress);
        shoulder_exe.addExercise(lateralRaise);
        shoulder_exe.addExercise(leanLateralRaise);
        shoulder_exe.addExercise(inclineYRaise);


        //---------------------------------------WORKOUTS--------------------------------------------
        upperbody_workout.addExercise(pushUps);
        upperbody_workout.addExercise(burpees);
        upperbody_workout.addExercise(mountaiunClimb);
        upperbody_workout.addExercise(barbellRollout);
        upperbody_workout.addExercise(russianTwist);
        upperbody_workout.addExercise(overheadPress);
        upperbody_workout.addExercise(arnoldPress);
        upperbody_workout.addExercise(benchPress);
        upperbody_workout.addExercise(cabelCross);
        upperbody_workout.addExercise(inclineBenchPress);

        lowerbody_workout.addExercise(lunges);
        lowerbody_workout.addExercise(squat);
        lowerbody_workout.addExercise(dumbellStepup);
        lowerbody_workout.addExercise(legpress);
        lowerbody_workout.addExercise(pauseSquat);
        lowerbody_workout.addExercise(ketSwing);
        lowerbody_workout.addExercise(deadLift);
        lowerbody_workout.addExercise(backExtention);
        lowerbody_workout.addExercise(dumbellStepup);
        lowerbody_workout.addExercise(legpress);



    }

    public ExerciseList getBiceps_exe(){return biceps_exe;}
    public ExerciseList getLegs_exe(){return legs_exe;}
    public ExerciseList getAbs_exe(){return abs_exe;}
    public ExerciseList getBack_exe(){return back_exe;}
    public ExerciseList getChest_exe(){return chest_exe;}
    public ExerciseList getShoulder_exe(){return shoulder_exe;}

    public ExerciseList getUpperbody_workout(){return upperbody_workout;}
    public ExerciseList getLowerbody_workout(){return lowerbody_workout;}

}
