package daniel.daniel_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class displaylistscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaylistscreen);

        // Hardcoded buttons for stories by lack of randomness
        String[] stories = {"Simple", "Tarzan", "University", "Clothes", "Dance"};

        // Create intent for next screen to use later
        final Intent TextInput = new Intent(displaylistscreen.this, wordscreen.class);

        // Sets adapter to go over the array and puts it in the ListView
        ListAdapter storyAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, stories);
        ListView storyList = (ListView) findViewById(R.id.storyList);
        storyList.setAdapter(storyAdapter);

        // onClick listener that directs user to next screen when button is pressed
        storyList.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                    String text = String.valueOf(parent.getItemAtPosition(position));

                        // Directs user to the next screen, corresponding with the position
                        // of the item clicked (based on position)
                        TextInput.putExtra("chosenText", text);
                        startActivity(TextInput);
                    }
                }
        );
    }
}
