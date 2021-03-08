package com.example.flashingsquirrel;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.flashingsquirrel.R.color;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*
Author: Giancarlo Garcia Deleon
Main Bugs:
If DB empty, app crashes.
If DB at default flashcard and next is clicked, app crashes.

Things left to implement:
Empty State

 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Make toggle button text null.
        final ToggleButton toggle = (ToggleButton)findViewById(R.id.shuffleButton);
        toggle.setTextOff(null);
        toggle.setTextOn(null);
        toggle.setText(null);

        // We want the program to know the status of the toggled button, so we can make decisions based on it.
        boolean ToggleButtonState = toggle.isChecked(); // returns the state of the toggle button
        // End of making toggle button text null and checking its status.s

        flashcardDatabase = new FlashcardDatabase(getApplicationContext()); //var init
        allFlashcards = flashcardDatabase.getAllCards();

        // Initializes all of the flashcards upon the start of the application. Sets the index of flashcards to 0 for both question and answer.
        if (allFlashcards != null && allFlashcards.size() > 0) {
            ((TextView) findViewById(R.id.flashcard_Question)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.flashcard_Answer)).setText(allFlashcards.get(0).getAnswer());
        } else {
            Log.d("State","Empty State. No Flash Cards identified. ");
            // Else show the empty state
        }

        // If flashcard QUESTION is clicked, then the answer will be displayed.
        findViewById(R.id.flashcard_Question).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                View answerSideView = findViewById(R.id.flashcard_Answer);

// get the center for the clipping circle
                int cx = answerSideView.getWidth() / 2;
                int cy = answerSideView.getHeight() / 2;

// get the final radius for the clipping circle
                float finalRadius = (float) Math.hypot(cx, cy);

// create the animator for this view (the start radius is zero)
                Animator anim = ViewAnimationUtils.createCircularReveal(answerSideView, cx, cy, 0f, finalRadius);

// hide the question and show the answer to prepare for playing the animation!
                findViewById(R.id.flashcard_Question).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcard_Answer).setVisibility(View.VISIBLE);
                Log.d("OnClickListener","Flashcard Question Clicked. Showing Answer. ");

                anim.setDuration(1000);
                anim.start();
            }
        });

        // If flashcard ANSWER is clicked, then the question will be displayed.
        findViewById(R.id.flashcard_Answer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                View answerSideView = findViewById(R.id.flashcard_Answer);

// get the center for the clipping circle
                int cx = answerSideView.getWidth() / 2;
                int cy = answerSideView.getHeight() / 2;

// get the final radius for the clipping circle
                float finalRadius = (float) Math.hypot(cx, cy);

// create the animator for this view (the start radius is zero)
                Animator anim = ViewAnimationUtils.createCircularReveal(answerSideView, cx, cy, 0f, finalRadius);

// hide the question and show the answer to prepare for playing the animation!
                findViewById(R.id.flashcard_Answer).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcard_Question).setVisibility(View.VISIBLE);
                Log.d("OnclickListener", " Flashcard Answer Clicked. Showing Question. ");

                anim.setDuration(500);
                anim.start();
            }
        });

        // ADD CARD ACTIVITY button clicked. "+"
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View V){
                  Intent i = new Intent(MainActivity.this, AddCardActivity.class);
                  startActivityForResult(i, 100);
                  Log.d("OnClickListener", "Changed to AddCardActivity. ");
                  overridePendingTransition(R.anim.infromtheright, R.anim.goouttotheleft);
                  Log.d("anim", "animation transition executed.");
              }
          });

        // TRASHCAN button clicked, deleting current tuple (Answer/Question Flashcard) from database.
        findViewById(R.id.trashcan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("OnClickListener","Delete button has been clicked. Index value: " + currentCardDisplayedIndex + " should be deleted from the database now. ");
                flashcardDatabase.deleteCard(((TextView) findViewById(R.id.flashcard_Question)).getText().toString());
                allFlashcards = flashcardDatabase.getAllCards();
                Log.d("OnClickListener", "Card has been deleted, we are at index: " + currentCardDisplayedIndex);
                // We also want the system to skip to another card, seeing as the currentCardDisplayedIndex should not be available for use.
                currentCardDisplayedIndex++;
                Log.d("OnClickListener", "currentCardDisplayedIndex has been increased by ONE, we are now at index: " + currentCardDisplayedIndex);
                // Make sure we don't get an IndexOutOfBoundsError if we are viewing the last indexed card in our list
                if (currentCardDisplayedIndex > allFlashcards.size() - 1) {
                    currentCardDisplayedIndex = 0;
                }
                if(findViewById(R.id.flashcard_Question).getVisibility() != View.VISIBLE){
                    findViewById(R.id.flashcard_Question).setVisibility(View.VISIBLE);
                    findViewById(R.id.flashcard_Answer).setVisibility(View.INVISIBLE);
                    Log.d("Visibility", "Flashcard was facing answer when flashcard was deleted. For UX purposes, cardfacing has been switched to question.");
                }
                ((TextView) findViewById(R.id.flashcard_Question)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                ((TextView) findViewById(R.id.flashcard_Answer)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
            }
        });

//        if(ToggleButtonState = true){  // it's on
//            //do random selection
//            Log.d("OnClickListener", "Toggle Button is ON. ");
//            // returns a random number between minNumber and maxNumber, inclusive.
//            // for example, if i called getRandomNumber(1, 3), there's an equal chance of it returning either 1, 2, or 3.
//            public int getRandomNumber(int minNumber, int maxNumber) {
//                Random rand = new Random();
//                 int usethisNum = rand.nextInt((maxNumber - minNumber) + 1) + minNumber;
                    //currentCardDisplayedIndex = MainActivity.this.getRandomNumber(0, allFlashcards.size()-1);
//            }
//        } else {
            //Log.d("OnClickListener", "Toggle Button is OFF. ");
            // NEXT flash card button has been clicked. Displaying next card.

        //}
        // NEXT CARD BUTTON is clicked.
        findViewById(R.id.nextCardButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Call for animation resources
                final Animation leftOutAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.goouttotheleft);
                final Animation rightInAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.infromtheright);


                findViewById(R.id.flashcard_Question).startAnimation(leftOutAnim);
                // 3 poss answers for question would go here as well.

                leftOutAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        // this method is called when the animation first starts

                    }
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // this method is called when the animation is finished playing
                        findViewById(R.id.flashcard_Question).startAnimation(rightInAnim);
                        // NOTE: Advance our pointer index so we can show the next card
                        currentCardDisplayedIndex++;
                        Log.d("OnClickListener","Current card has been moved to next. Value of Current Card Displayed Index: " + String.valueOf(currentCardDisplayedIndex));
                        // NOTE: Current Card Displayed Index has nothing to do with the face of the card, simply the index of the tuple (answer/question).
                        // Make sure we don't get an IndexOutOfBoundsError if we are viewing the last indexed card in our list
                        if (currentCardDisplayedIndex > allFlashcards.size() - 1) {
                            currentCardDisplayedIndex = 0;
                        }

                        // We want the visibility of the flashcard question and flashcard answer to be respectively: 0:4 <- Integer values... However, when applying to statement, must be in VISIBLE/INVISIBLE format.
                        //              Log.d("Visibility", "Current Visibility of FlashCard QUESTION: " + findViewById(R.id.flashcard_Question).getVisibility());
                        //                Log.d("Visibility","Current Visibility of FlashCard ANSWER: " + findViewById(R.id.flashcard_Answer).getVisibility());
                        if(findViewById(R.id.flashcard_Question).getVisibility() != View.VISIBLE){
                            findViewById(R.id.flashcard_Question).setVisibility(View.VISIBLE);
                            findViewById(R.id.flashcard_Answer).setVisibility(View.INVISIBLE);
                            Log.d("Visibility", "Flashcard was facing answer when next was clicked. For UX purposes, cardfacing has been switched to question.");
                        }

                        // Set the question and answer TextViews with data from the database
                        ((TextView) findViewById(R.id.flashcard_Question)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                        ((TextView) findViewById(R.id.flashcard_Answer)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                    }
                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        // we don't need to worry about this method
                    }
                });



            }
        });


        // SHUFFLE button has been toggled. If toggle on, nextcard button should be randomly picking cards, else, nextcardbutton will display cards by default.
        // Note: Shuffle will only hold boolean. Next Card should make the decision based on the boolean.
        findViewById(R.id.shuffleButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //toggle.setChecked(true); // set the current state of a toggle button to true
                boolean ToggleButtonState = toggle.isChecked(); // returns the state of the togglebutton
                Log.d("OnClickListener","Status of toggled button: " + ToggleButtonState);
            }
        });


    }

    int currentCardDisplayedIndex = 0;
    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;

    // Retrieving data from AddCardActivity to MainActivity.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100  && resultCode == RESULT_OK) {

            String newQuestion = data.getExtras().getString("question");
            String newAnswer = data.getExtras().getString("answer");
            // RETRIEVE AND STORE IN A VARIABLE
            // SHOW IT IN MAIN ACTIVITY
            ((TextView)findViewById(R.id.flashcard_Question)).setText(newQuestion);
            ((TextView)findViewById(R.id.flashcard_Answer)).setText(newAnswer);

            flashcardDatabase.insertCard(new Flashcard(newQuestion, newAnswer));
            allFlashcards = flashcardDatabase.getAllCards();

            Snackbar.make(findViewById(R.id.flashcard_Question),"Flashcard Successfully Added. ", Snackbar.LENGTH_SHORT).show();
        }
    }
}
