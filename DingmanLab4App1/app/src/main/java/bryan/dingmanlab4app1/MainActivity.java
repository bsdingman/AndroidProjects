/*
    Bryan Dingman
    Lab 4 App 1
    Extend the calculator app and add functionality to it
*/
package bryan.dingmanlab4app1;

import android.graphics.Color;
import android.graphics.Point;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private int width;
    private int height;
    private final int MARGIN = 5;
    public Calculator calc;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        buildCalculator();
    }

    /*
        Our build calculator class that creates everything
     */
    public void buildCalculator()
    {
        // Get the width of the screen
        Point screenSize = new Point();
        getWindowManager().getDefaultDisplay().getSize(screenSize);
        width = screenSize.x / 4;
        height = (int)(0.9 * (screenSize.y / 6));

        // Create the grid layout
        GridLayout main_grid = new GridLayout(this);
        main_grid.setColumnCount(4);
        main_grid.setRowCount(6);
        main_grid.setBackgroundColor(Color.BLACK);

        // Create the output
        TextView output = new TextView(this);
        GridLayout.Spec outputRowSpec = GridLayout.spec(0, 1);
        GridLayout.Spec outputColumnSpec = GridLayout.spec(0, 4);
        GridLayout.LayoutParams outputParams = new GridLayout.LayoutParams(outputRowSpec, outputColumnSpec);
        outputParams.rightMargin = MARGIN;
        outputParams.topMargin = MARGIN;
        outputParams.bottomMargin = MARGIN;
        output.setLayoutParams(outputParams);

        // Set up the attributes
        output.setWidth(4 * width);
        output.setHeight(height);
        output.setGravity(Gravity.CENTER | Gravity.RIGHT);
        output.setText("");
        output.setTextSize((int)(width * 0.1));
        output.setBackgroundColor(ContextCompat.getColor(this, R.color.colorResult));
        output.setTextColor(Color.BLACK);

        // Add to main grid
        main_grid.addView(output);

        // Initialize the calculator
        this.calc = new Calculator(this, output);

        ////////////////////////////////////
        // ROW 2
        ////////////////////////////////////
        // Add the AC button
        buildButton(main_grid, 0, 2, 1, width * 2, R.string.clear, ContextCompat.getColor(this, R.color.colorClear), "ac");

        // Add the Mod button
        buildButton(main_grid, 2, 1, 1, width, R.string.mod, ContextCompat.getColor(this, R.color.colorOther), "%");

        // Add the divide button
        buildButton(main_grid, 3, 1, 1, width, R.string.divide, ContextCompat.getColor(this, R.color.colorOther), "/");

        ////////////////////////////////////
        // ROW 3
        ////////////////////////////////////

        // Add the seven button
        buildButton(main_grid, 0, 1, 2, width, R.string.seven, ContextCompat.getColor(this, R.color.colorOther), "7");

        // Add the eight button
        buildButton(main_grid, 1, 1, 2, width, R.string.eight, ContextCompat.getColor(this, R.color.colorOther), "8");

        // Add the nine button
        buildButton(main_grid, 2, 1, 2, width, R.string.nine, ContextCompat.getColor(this, R.color.colorOther), "9");

        // Add the multiply button
        buildButton(main_grid, 3, 1, 2, width, R.string.multiply, ContextCompat.getColor(this, R.color.colorOther), "x");

        ////////////////////////////////////
        // ROW 4
        ////////////////////////////////////

        // Add the four button
        buildButton(main_grid, 0, 1, 3, width, R.string.four, ContextCompat.getColor(this, R.color.colorOther), "4");

        // Add the five button
        buildButton(main_grid, 1, 1, 3, width, R.string.five, ContextCompat.getColor(this, R.color.colorOther), "5");

        // Add the six button
        buildButton(main_grid, 2, 1, 3, width, R.string.six, ContextCompat.getColor(this, R.color.colorOther), "6");

        // Add the minus button
        buildButton(main_grid, 3, 1, 3, width, R.string.minus, ContextCompat.getColor(this, R.color.colorOther), "-");


        ////////////////////////////////////
        // ROW 5
        ////////////////////////////////////

        // Add the one button
        buildButton(main_grid, 0, 1, 4, width, R.string.one, ContextCompat.getColor(this, R.color.colorOther), "1");

        // Add the two button
        buildButton(main_grid, 1, 1, 4, width, R.string.two, ContextCompat.getColor(this, R.color.colorOther), "2");

        // Add the three button
        buildButton(main_grid, 2, 1, 4, width, R.string.three, ContextCompat.getColor(this, R.color.colorOther), "3");

        // Add the plus button
        buildButton(main_grid, 3, 1, 4, width, R.string.plus, ContextCompat.getColor(this, R.color.colorOther), "+");


        ////////////////////////////////////
        // ROW 6
        ////////////////////////////////////

        // Add the zero button
        buildButton(main_grid, 0, 1, 5, width, R.string.zero, ContextCompat.getColor(this, R.color.colorOther), "0");

        // Add the equal button
        buildButton(main_grid, 1, 3, 5, width * 3, R.string.equals, ContextCompat.getColor(this, R.color.colorEquals), "=");


        // Add it back!
        setContentView(main_grid);
    }

    public void buildButton(GridLayout main_grid, int colSt, int colSi, int rowSt, int width, int text, int color, String tag)
    {
        final Button button = new Button(this);

        GridLayout.Spec rowSpec = GridLayout.spec(rowSt, 1);
        GridLayout.Spec columnSpec = GridLayout.spec(colSt, colSi);
        GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, columnSpec);
        params.rightMargin = MARGIN;
        params.bottomMargin = 5;
        button.setLayoutParams(params);

        // Set up the attributes
        button.setWidth(width);
        button.setHeight(height);
        button.setGravity(Gravity.CENTER);
        button.setText(text);
        button.setTextSize((int)(this.width * 0.08));
        button.setBackgroundColor(color);
        button.setTag(tag);

        // Add the event handler
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                calc.onButtonClick(button);
            }
        });

        // Add to main grid
        main_grid.addView(button);
    }
}
