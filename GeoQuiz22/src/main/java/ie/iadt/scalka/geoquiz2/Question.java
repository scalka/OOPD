package ie.iadt.scalka.geoquiz2;
/*The Question class holds two pieces of data: the question text and the question answer (true or false).
The mTextResId variable will hold the resource ID (always an int) of a string resource for the question. You will create the question string resources later.
*/
public class Question {

    private int mTextResourceId;
    private boolean mAnswerTrue;

    public Question(int textResourceId, boolean answerTrue){
        mTextResourceId = textResourceId;
        mAnswerTrue = answerTrue;
    }
    public int getTextResId(){
        return mTextResourceId;
    }
    public void setTextResourceId(int textResourceId){
        mTextResourceId = textResourceId;
    }
    public boolean ismAnswerTrue(){
        return mAnswerTrue;
    }
    public void setmAnswerTrue(boolean answerTrue){
        mAnswerTrue = answerTrue;
    }

}

