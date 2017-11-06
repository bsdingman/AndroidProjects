/*
    Bryan Dingman
    Lab 6 App 1
 */
package bryan.dingmanlab6app1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        TextView shapeName = (TextView)findViewById(R.id.tv_SelectedShape);
        TextView output1 = (TextView)findViewById(R.id.tv_Output1);
        TextView output2 = (TextView)findViewById(R.id.tv_Output2);
        TextView output3 = (TextView)findViewById(R.id.tv_Output3);

        // Hide everything
        output1.setVisibility(View.INVISIBLE);
        output2.setVisibility(View.INVISIBLE);
        output3.setVisibility(View.INVISIBLE);

        // Get our shape
        String shape = getIntent().getExtras().getString("Shape");

        // Set which shape we selected
        shapeName.setText(shape);

        // Process based on shape
        switch (shape)
        {
            case "Circle":
            {
                double area = getIntent().getExtras().getDouble("Area");
                double circumference = getIntent().getExtras().getDouble("Circumference");

                output1.setText(String.format("%s %.2f", getString(R.string.area), area));
                output2.setText(String.format("%s %.2f", getString(R.string.circumference), circumference));

                output1.setVisibility(View.VISIBLE);
                output2.setVisibility(View.VISIBLE);
                break;
            }
            case "Rectangle":
            {
                double area = getIntent().getExtras().getDouble("Area");
                double perimeter = getIntent().getExtras().getDouble("Perimeter");

                output1.setText(String.format("%s %.2f", getString(R.string.area), area));
                output2.setText(String.format("%s %.2f", getString(R.string.perimeter), perimeter));

                output1.setVisibility(View.VISIBLE);
                output2.setVisibility(View.VISIBLE);
                break;
            }
            case "Triangle":
            {
                double area = getIntent().getExtras().getDouble("Area");
                double perimeter = getIntent().getExtras().getDouble("Perimeter");
                double s = getIntent().getExtras().getDouble("S");

                output1.setText(String.format("%s %.2f", getString(R.string.area), area));
                output2.setText(String.format("%s %.2f", getString(R.string.perimeter), perimeter));
                output3.setText(String.format("%s %.2f", getString(R.string.s), s));

                output1.setVisibility(View.VISIBLE);
                output2.setVisibility(View.VISIBLE);
                output3.setVisibility(View.VISIBLE);
                break;
            }
            default:
            {
                output1.setVisibility(View.INVISIBLE);
                output2.setVisibility(View.INVISIBLE);
                output3.setVisibility(View.INVISIBLE);
            }
        }
    }

    // Closes this activity
    public void goBack(View v)
    {
        this.finish();
    }
}
