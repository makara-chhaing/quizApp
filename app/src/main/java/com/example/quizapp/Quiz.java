package com.example.quizapp;

public class Quiz {
    public static int question_index;
    public static final String[] question = new String[]{
        "1./ Which Folder do you copy and paste image to?",
                "2./ Which component property should be changed to a name that is specific of the components use?",
                "3./ Which is correct for using any image with the name \"home\"?",
                "4./ Which listener is called when something is clicked?",
                "5./ Which Android Studio file is most of the app design done in?"
};

    public static final String[] answer1 = new String[]{ "Layout", "Text", "Android:\"@drawable/home\"", "OnClickListener", "MainActivity.Java"};
    public static final String[] answer2 = new String[]{"Resources", "ID","Android:src=\"home\"","OnKeyListener","Activity_main.xml"};
    public static final String[] answer3 = new String[]{"Drawable","Drawable", "app:srcCompat=\"@drawable/home\"" , "OnContextListener", "AndroidManifest.xml"};
    public static final String[] correct_answer = new String[]{"Resources", "ID", "app:srcCompat=\"@drawable/home\"", "OnClickListener", "MainActivity.Java"};




}
