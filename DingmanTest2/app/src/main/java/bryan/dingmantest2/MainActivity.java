package bryan.dingmantest2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Event handling for display button
        (findViewById(R.id.btn_Display)).setOnClickListener(view ->
        {
            Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
            startActivity(intent);
        });

        // Event handling for update button
        (findViewById(R.id.btn_Update)).setOnClickListener(view ->
        {
            Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
            startActivity(intent);
        });

        // Event handling for add button
        (findViewById(R.id.btn_Add)).setOnClickListener(view ->
        {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivity(intent);
        });

        // Event handling for delete button
        (findViewById(R.id.btn_Delete)).setOnClickListener(view ->
        {
            Intent intent = new Intent(MainActivity.this, DeleteActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        // Close our database connection so we don't leak!
        DatabaseConnector dbc = new DatabaseConnector(this);
        dbc.close();
    }
}
