/*
    Bryan Dingman
    Lab 6 App 1
 */

package bryan.dingmanlab6app1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    public final double PI = Math.PI;
    String shapeType;
    double radius, length, width, side_a, side_b, side_c, s;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner shapes = (Spinner)findViewById(R.id.cb_Shapes);
        EditText input1 = (EditText)findViewById(R.id.tb_Input1);
        EditText input2 = (EditText)findViewById(R.id.tb_Input2);
        EditText input3 = (EditText)findViewById(R.id.tb_Input3);

        shapes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id)
            {
                // Reset these
                input1.setVisibility(View.INVISIBLE);
                input2.setVisibility(View.INVISIBLE);
                input3.setVisibility(View.INVISIBLE);

                // Clear the inputs
                input1.setText("");
                input2.setText("");
                input3.setText("");

                // Get the selected shape
                shapeType = shapes.getSelectedItem().toString();

                // show and set hints based on selection
                switch (shapeType)
                {
                    case "Circle":
                    {
                        input1.setHint(R.string.radius);
                        input1.setVisibility(View.VISIBLE);
                        break;
                    }
                    case "Rectangle":
                    {
                        input1.setHint(R.string.length);
                        input2.setHint(R.string.width);

                        input1.setVisibility(View.VISIBLE);
                        input2.setVisibility(View.VISIBLE);
                        break;
                    }
                    case "Triangle":
                    {
                        input1.setHint(R.string.side_a);
                        input2.setHint(R.string.side_b);
                        input3.setHint(R.string.side_c);

                        input1.setVisibility(View.VISIBLE);
                        input2.setVisibility(View.VISIBLE);
                        input3.setVisibility(View.VISIBLE);
                        break;
                    }
                    default:
                    {
                        input1.setVisibility(View.INVISIBLE);
                        input2.setVisibility(View.INVISIBLE);
                        input3.setVisibility(View.INVISIBLE);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        // Add the onClickListner for the calculate button
        (findViewById(R.id.btn_Calculate)).setOnClickListener(v ->
        {
            // Get the shape
            shapeType = shapes.getSelectedItem().toString();

            // Create our intent
            Intent displayIntent = new Intent(MainActivity.this, DisplayActivity.class);

            // Add the shape
            displayIntent.putExtra("Shape", shapeType);

            switch (shapeType)
            {
                case "Circle":
                {
                    radius = Double.parseDouble(input1.getText().toString());

                    // Package it up
                    displayIntent.putExtra("Area", PI * Math.pow(radius, 2));
                    displayIntent.putExtra("Circumference", 2 * PI * Math.pow(radius, 2));
                    break;
                }
                case "Rectangle":
                {
                    length = Double.parseDouble(input1.getText().toString());
                    width = Double.parseDouble(input2.getText().toString());

                    // Package it up
                    displayIntent.putExtra("Area", length * width);
                    displayIntent.putExtra("Perimeter", 2 *(length * width));
                    break;
                }
                case "Triangle":
                {
                    side_a = Double.parseDouble(input1.getText().toString());
                    side_b = Double.parseDouble(input2.getText().toString());
                    side_c = Double.parseDouble(input3.getText().toString());

                    // Display a toast if the triangle is invalid
                    if (!isValidTriangle(side_a, side_b, side_c))
                    {
                        Toast.makeText(MainActivity.this, "Invalid triangle! Sum of any two sides must be greater than the third!", Toast.LENGTH_LONG).show();
                        return;
                    }

                    // Calculate S
                    s = (side_a + side_b + side_c) / 2;

                    // Package it up
                    displayIntent.putExtra("Area", Math.sqrt(s * (s - side_a) * (s - side_b) * (s - side_c)));
                    displayIntent.putExtra("S", s);
                    displayIntent.putExtra("Perimeter", side_a + side_b + side_c);

                    break;
                }
                // Don't do anything
                default:
                {
                    return;
                }
            }

            // Start the new activity
            startActivity(displayIntent);
        });
    }

    // Checks to see if the triangle is valid
    private boolean isValidTriangle(double a, double b, double c)
    {
        return ((a + b) > c && (a + c) > b && (b + c) > a);
    }
}
