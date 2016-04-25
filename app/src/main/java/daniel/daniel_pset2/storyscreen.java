package daniel.daniel_pset2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class storyscreen extends AppCompatActivity {

    TextView textOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storyscreen);

        // Retrieves the data from wordscreen.java and outputs a string which is the new story
        // into a TextView
        String outputString = getIntent().getStringExtra("story");
        textOutput = (TextView) findViewById(R.id.textOutput); //
        assert textOutput != null;
        textOutput.setText(outputString);
    }
}