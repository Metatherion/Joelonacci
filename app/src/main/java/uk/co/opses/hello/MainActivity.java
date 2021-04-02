package uk.co.opses.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView count;
    TextView numberInput;
    Button run;
    ArrayList<Integer> sequence;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String PATH = "path";

    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        count = (TextView) findViewById(R.id.Count);
        numberInput = (EditText) findViewById(R.id.numberInput);
        run = (Button) findViewById(R.id.runButton);
        run.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if ( numberInput.getText().toString().length() == 0)
                {
                    numberInput.requestFocus();
                    numberInput.setError("Field cannot be empty");
                }
                else if (Integer.parseInt(numberInput.getText().toString()) >99)
                {
                    numberInput.requestFocus();
                    numberInput.setError("Number must be below 100");
                }
                else if (Integer.parseInt(numberInput.getText().toString()) < 0)
                {
                    numberInput.requestFocus();
                    numberInput.setError("Number must be 0 or above");
                }
                else
                {
                    sequence = FibonacciAlgorithm.createSequence(Integer.parseInt(numberInput.getText().toString()));
                    printSequenceToScreen();
                }
            }
        });
    }
    public void requestPath()
    {

    }
    public void setPath()
    {
        SharedPreferences sharePref = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharePref.edit();

        editor.putString(PATH, "TODO");

        editor.apply();

        Toast.makeText(this,"Path Saved", Toast.LENGTH_SHORT).show();
    }
    public void loadPath()
    {
        SharedPreferences sharePref = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        path = sharePref.getString(PATH, null);
    }


    public void printSequenceToScreen()
    {
        count.setText("Sequence: \n");
        for (int i = 0; i < sequence.size(); i++)
        {
            count.append(sequence.get(i)+ "\n");
        }

    }
    public void printSequenceToFile()
    {

    }
}
