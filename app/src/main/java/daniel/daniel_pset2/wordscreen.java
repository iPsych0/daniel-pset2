package daniel.daniel_pset2;

import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import java.io.InputStream;

public class wordscreen extends AppCompatActivity {

    // Declaring the variables used throughout this program
    Story story;
    InputStream inputStream;
    TextView wordSort;
    TextView remainingWords;

    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wordscreen);

        // Retrieves the item chosen in displaylistscreen.java
        Bundle extras = getIntent().getExtras();
        final String chosenText = extras.getString("chosenText");

        // Following 2 functions, declared outside this window, usable within.
        wordSort = (TextView) findViewById(R.id.wordSort);
        remainingWords = (TextView) findViewById(R.id.remainingWords);

        // Initialize the button and textfield, usable for later
        Button ok = (Button) findViewById(R.id.okButton);
        final EditText inputBox = (EditText) findViewById(R.id.wordInput);

        // Calls upon the map "assets" and declares an input stream
        AssetManager assets;
        inputStream = null;
        assets = this.getAssets();

        // Opens the input stream and uses the value of the item chosen in the previous window
        // and appends .txt to find the corresponding file to read.
        try {
            inputStream = assets.open(chosenText + ".txt", AssetManager.ACCESS_BUFFER);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        // Input stream is now available, so "story" is now able to declare functions from
        // Story.java file.
        story = new Story(inputStream);

        wordSort.setText(findWordSort());
        remainingWords.setText(findRemainingWords());

        // Makes sure that the OK button doesn't return a null value
        assert ok != null;

        // The magic happens here. When the OK button is pressed:
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assert inputBox != null;
                // Fills in the placeholders in the text
                story.fillInPlaceholder(inputBox.getText().toString());
                if (story.getPlaceholderRemainingCount() >= 1) {
                    inputBox.setText("");
                    // Updates the next word required and words left
                    wordSort.setText(findWordSort());
                    remainingWords.setText(findRemainingWords());
                } else {
                    // If there are no words left, go to the next screen and show the new text
                    Intent intent = new Intent(wordscreen.this, storyscreen.class);
                    intent.putExtra("story", story.toString());
                    startActivity(intent);
                    // Close the activity completely, to prevent users from going back to the
                    // input screen
                    finish();
                }
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    // Function to keep track of the placeholder words, as defined in Story.java
    private String findWordSort() {
        return "Please fill in a(n) " + story.getNextPlaceholder();
    }

    // Function to keep track of the remaining words, as defined in Story.java
    private String findRemainingWords() {
        return story.getPlaceholderRemainingCount() + " words left";
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "wordscreen Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://daniel.daniel_pset2/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "wordscreen Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://daniel.daniel_pset2/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}


