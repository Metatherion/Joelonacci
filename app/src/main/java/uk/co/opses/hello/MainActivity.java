package uk.co.opses.hello;

import androidx.appcompat.app.AppCompatActivity;
import androidx.documentfile.provider.DocumentFile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int READ_REQUEST_CODE = 1;
    private TextView count;
    private TextView numberInput;
    private Button run;
    private  ArrayList<Integer> sequence;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String PATH = "path";

    private static final String FILE_NAME = "Joelanacci.txt";
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadPath();
        if (path == null)
        {
            requestPath();
        }

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
                    try {
                        printSequenceToFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    public void requestPath()
    {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
        startActivityForResult(intent, READ_REQUEST_CODE);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK)
        {
            path = DocumentFile.fromTreeUri(this, data.getData()).toString();
            count.setText(path);
        }
    }
    public void setPath()
    {
        SharedPreferences sharePref = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharePref.edit();

        editor.putString(PATH, path);

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
    public void printSequenceToFile() throws IOException {
        FileOutputStream fos = null;

        fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
        fos.write(sequence.toString().getBytes());
        Toast.makeText(this, "Saved to " + getFilesDir() + "-" + FILE_NAME, Toast.LENGTH_LONG).show();
        if (fos != null)
        {
            fos.close();
        }

    }
}
