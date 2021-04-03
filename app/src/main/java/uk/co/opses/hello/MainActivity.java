package uk.co.opses.hello;

import androidx.appcompat.app.AppCompatActivity;
import androidx.documentfile.provider.DocumentFile;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int READ_REQUEST_CODE = 1;
    private TextView count;
    private TextView numberInput;
    private  ArrayList<Integer> sequence;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String PATH = "path";

    private static final String FILE_NAME = "Joelanacci.txt";
    private String path;
    private  Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Checks for preset file path in shared preferences
        loadPath();

        // If no path is present, requests one
        if (path == null)
        {
            requestPath();
        }
        else
        {
            uri = Uri.parse(path);
        }

        // Assign Views to variables
        count = (TextView) findViewById(R.id.Count);
        numberInput = (EditText) findViewById(R.id.numberInput);
        Button run = (Button) findViewById(R.id.runButton);

        // Checks if input is valid and prints if is valid
        run.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Check if empty
                if ( numberInput.getText().toString().length() == 0)
                {
                    numberInput.requestFocus();
                    numberInput.setError("Field cannot be empty");
                }
                // Check if entry is more than three integers
                else if (Integer.parseInt(numberInput.getText().toString()) >99)
                {
                    numberInput.requestFocus();
                    numberInput.setError("Number must be below 100");
                }
                // Check if entry is negative
                else if (Integer.parseInt(numberInput.getText().toString()) < 0)
                {
                    numberInput.requestFocus();
                    numberInput.setError("Number must be 0 or above");
                }
                else
                {
                    // Generate fibonacci Arraylist
                    sequence = FibonacciAlgorithm.createSequence(Integer.parseInt(numberInput.getText().toString()));

                    // Print to screen for verification
                    printSequenceToScreen();

                    // Print to file
                    try {
                        printSequenceToFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    // Presents user with directory choice
    public void requestPath()
    {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
        startActivityForResult(intent, READ_REQUEST_CODE);
    }

    // Handles result of directory choice
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK)
        {
            // Obtain URI from result data
            uri = data.getData();
            assert uri != null;
            path = uri.toString();
            savePath();

        }
    }
    // Saves user selected path to Shared Preferences
    public void savePath()
    {
        //Opens shared preferences
        SharedPreferences sharePref = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharePref.edit();
        // Assigns saved filepath to PATH
        editor.putString(PATH, path);
        // Commits changes
        editor.apply();
        // Acknowledges to user that filepath has been saved
        Toast.makeText(this,"Path Saved: " + path, Toast.LENGTH_SHORT).show();
    }
    //  Obtains saved filepath from shared preferences
    public void loadPath()
    {
        SharedPreferences sharePref = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        path = sharePref.getString(PATH, null);

    }

    // Prints given sequence to screen
    public void printSequenceToScreen()
    {
        count.setText("Sequence: \n");
        for (int i = 0; i < sequence.size(); i++)
        {
            count.append(sequence.get(i)+ "\n");
        }

    }
    // Creates a file and prints sequence at given directory
    public void printSequenceToFile() throws IOException
    {
        // Opens Directory
        DocumentFile pickedDir  = DocumentFile.fromTreeUri(this, uri);
        assert pickedDir != null;

        // Creates File
        DocumentFile newFile = pickedDir.createFile("text/plain", FILE_NAME);
        assert newFile != null;

        // Opens Output stream to file
        OutputStream outputStream = this.getContentResolver().openOutputStream(newFile.getUri(), "wa");

        // Opens a duffer writer to contain the sequence
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));

        // Writes each element of sequence on a new line to the writer
        for (Integer number : sequence)
        {
            bw.write(number.toString());
            bw.newLine();
        }
        // Closes the writer
        bw.close();

        //Closes the output stream
        assert outputStream != null;
        outputStream.close();
        // Acknowledges to user that the file has been created and sequence printed
        Toast.makeText(this,"File Saved: " + FILE_NAME, Toast.LENGTH_SHORT).show();
    }
}
