package ie.iadt.scalka.;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import ie.iadt.scalka.geoquiz2.R;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {

    Button true_button;
    Button false_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        true_button = (Button) findViewById(R.id.true_button);
        false_button = (Button) findViewById(R.id.false_button);

        true_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getApplicationContext(),R.string.correct_toast,Toast.LENGTH_SHORT).show();
            }
        });
        false_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getApplicationContext(),R.string.incorrect_toast,Toast.LENGTH_SHORT).show();
            }
        });

    }
}
