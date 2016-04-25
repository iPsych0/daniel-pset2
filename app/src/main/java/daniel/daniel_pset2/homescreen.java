package daniel.daniel_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class homescreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
    }

    // Create function that opens a new screen when the button is pressed
    public void getStarted(View view) {
        Intent secondScreen = new Intent(this, displaylistscreen.class);

        secondScreen.putExtra("listScreen", 500);
        startActivity(secondScreen);
    }
}