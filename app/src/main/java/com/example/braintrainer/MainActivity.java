package com.example.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button button ;
    Button button1 ;
    Button button2 ;
    Button button3 ;
    Button button4;
    ArrayList<Integer> answers = new ArrayList<>();
    int locationOfCorrectAnswer;
    TextView point;
    TextView result;
    TextView sumtextView;
    TextView timerTextView;
    Button playAgain;
    int score,attempt;

    public void playAgain(View view)
    {
        playAgain.setVisibility(View.INVISIBLE);
        result.setText("");
        score=0;
        attempt=0;
        new CountDownTimer(30*1000 + 100,1000){//"100" is added
            @Override                                                      //to give some extra time
            public void onTick(long millisUntilFinished) {                 //during start up
                timerTextView.setText(millisUntilFinished/1000 + "s");
            }

            @Override
            public void onFinish() {
                timerTextView.setText("0s");
                result.setText("Your Score : \n" +  score +"/" + attempt );
                playAgain.setText("Play Again");
                playAgain.setVisibility(View.VISIBLE);
                result.setVisibility(View.VISIBLE);

            }
        }.start();
    }
    public void generateQuestion()//to create a new question each time a question is answered
    {
        Random random = new Random();
        int a = random.nextInt(21);//generate a random number between 0 and 20
        int b = random.nextInt(21);

        answers.clear();//to reset the arraylist containing answers
        sumtextView.setText(a + " + "+ b);
        locationOfCorrectAnswer = random.nextInt(4);

        int incorrectAnswer ;
        for(int i =0;i<4;i++)
        {
            if(i==locationOfCorrectAnswer)
                answers.add(a+b);
            else{
                incorrectAnswer=random.nextInt(41);//to ensure
                while (incorrectAnswer== a+b)             //incorrect answer is not equal
                    incorrectAnswer =a+b;                 //to correct answer viz. there is only
                answers.add(incorrectAnswer);             //1 correct answer

            }
        }
        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));
    }
    public void chooseAnswer(View view)
    {
        if(Integer.valueOf(view.getTag().toString())==    locationOfCorrectAnswer)
        {
        score++;
        result.setText("Correct!");

        }
        else
        {
            result.setText("Incorrect!");
        }
        attempt++;
        point.setText(score + "/" + attempt);
        generateQuestion();
    }
    public void start(View view)
    {
        button.setVisibility(View.GONE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button =findViewById(R.id.button);
        sumtextView = findViewById(R.id.sumTextView);
        timerTextView=findViewById(R.id.timerTextView);
        point= findViewById(R.id.pointTextView);
        result = findViewById(R.id.resultTextView);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        playAgain = findViewById(R.id.playAgain);
        generateQuestion();
        playAgain(playAgain);

    }

}
