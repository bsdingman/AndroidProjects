/*
    Bryan Dingman
    Lab 5 App 1
    Show 5 pictures of campuses and open a new activity when they are touched
 */

package bryan.dingmanlab5app1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.Touch;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity
{
    public static String SelectedCampus = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TouchListener EH
        View.OnTouchListener handleTouch = new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                // Only fire for the release event
                if (event.getAction() == MotionEvent.ACTION_UP)
                {
                    MainActivity.SelectedCampus = v.getTag().toString();
                    Intent addressIntent = new Intent(MainActivity.this, AddressActivity.class);
                    startActivity(addressIntent);
                    return true;
                }
                return false;
            }
        };

        // All of our buttons
        ImageButton blountButton = (ImageButton)findViewById(R.id.blount);
        ImageButton divisionButton = (ImageButton)findViewById(R.id.division);
        ImageButton hardinButton = (ImageButton)findViewById(R.id.hardin);
        ImageButton magnoliaButton = (ImageButton)findViewById(R.id.magnolia);
        ImageButton strawberryButton = (ImageButton)findViewById(R.id.strawberry);

        // Add the events
        blountButton.setOnTouchListener(handleTouch);
        divisionButton.setOnTouchListener(handleTouch);
        hardinButton.setOnTouchListener(handleTouch);
        magnoliaButton.setOnTouchListener(handleTouch);
        strawberryButton.setOnTouchListener(handleTouch);
    }
}
