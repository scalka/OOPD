package ie.iadt.scalka.geoquiz2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import ie.iadt.scalka.geoquiz2.R;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {

    private Button true_button;
    private Button false_button;
    private ImageButton mNextButton;
    private ImageButton mBackButton;
    private TextView mQuestionTextView;
    private int mCurrentIndex = 0;

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.q1, true),
            new Question(R.string.q2, true),
            new Question(R.string.q3, true)
    };

    private void updateQuestion(){
        // get id off q
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        // set q
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].ismAnswerTrue();
        int messageResId = 0;
        if (userPressedTrue == answerIsTrue){
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        true_button = (Button) findViewById(R.id.true_button);
        false_button = (Button) findViewById(R.id.false_button);
        mNextButton = (ImageButton)findViewById(R.id.imageButton1);
        mBackButton = (ImageButton)findViewById(R.id.imageButton2);
        mQuestionTextView = (TextView)findViewById(R.id.question_text_view) ;

        true_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkAnswer(true);
            }
        });
        false_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
               checkAnswer(false);
            }
        });
        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // – index for the array of questions, start at 0, increment when Next is clicked
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });
        mBackButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // – index for the array of questions, start at 0, increment when Next is clicked
                mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

    }
}
