/*
    Bryan Dingman
    Test 2
*/

package bryan.dingmantest2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        // The text view that our output will be in
        TextView output = findViewById(R.id.tv_Display);

        // Database connector
        DatabaseConnector dbc = new DatabaseConnector(this);

        // Get the records
        ArrayList<String> studentRecordsList = dbc.getStudentRecords();

        // Add the output
        for (String s: studentRecordsList)
            output.append(s);
    }

    public void goBack (View v)
    {
        this.finish();
    }
}
