package bryan.dingmanlab3app1;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.GatewayInfo;
import android.view.Gravity;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private int width;
    private int height;
    private final int MARGIN = 5;

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
        output.setText(R.string.display);
        output.setTextSize((int)(width * 0.1));
        output.setBackgroundColor(ContextCompat.getColor(this, R.color.colorResult));
        output.setTextColor(Color.BLACK);

        // Add to main grid
        main_grid.addView(output);

        ////////////////////////////////////
        // ROW 2
        ////////////////////////////////////
        // Add the AC button
        buildButton(main_grid, 0, 2, 1, 1, width * 2, MARGIN, R.string.clear, ContextCompat.getColor(this, R.color.colorClear));

        // Add the Mod button
        buildButton(main_grid, 2, 1, 1, 1, width, MARGIN, R.string.mod, ContextCompat.getColor(this, R.color.colorOther));

        // Add the divide button
        buildButton(main_grid, 3, 1, 1, 1, width, MARGIN, R.string.divide, ContextCompat.getColor(this, R.color.colorOther));

        ////////////////////////////////////
        // ROW 3
        ////////////////////////////////////

        // Add the seven button
        buildButton(main_grid, 0, 1, 2, 1, width, MARGIN, R.string.seven, ContextCompat.getColor(this, R.color.colorOther));

        // Add the eight button
        buildButton(main_grid, 1, 1, 2, 1, width, MARGIN, R.string.eight, ContextCompat.getColor(this, R.color.colorOther));

        // Add the nine button
        buildButton(main_grid, 2, 1, 2, 1, width, MARGIN, R.string.nine, ContextCompat.getColor(this, R.color.colorOther));

        // Add the multiply button
        buildButton(main_grid, 3, 1, 2, 1, width, MARGIN, R.string.multiply, ContextCompat.getColor(this, R.color.colorOther));

        ////////////////////////////////////
        // ROW 4
        ////////////////////////////////////

        // Add the four button
        buildButton(main_grid, 0, 1, 3, 1, width, MARGIN, R.string.four, ContextCompat.getColor(this, R.color.colorOther));

        // Add the five button
        buildButton(main_grid, 1, 1, 3, 1, width, MARGIN, R.string.five, ContextCompat.getColor(this, R.color.colorOther));

        // Add the six button
        buildButton(main_grid, 2, 1, 3, 1, width, MARGIN, R.string.six, ContextCompat.getColor(this, R.color.colorOther));

        // Add the minus button
        buildButton(main_grid, 3, 1, 3, 1, width, MARGIN, R.string.minus, ContextCompat.getColor(this, R.color.colorOther));


        ////////////////////////////////////
        // ROW 5
        ////////////////////////////////////

        // Add the one button
        buildButton(main_grid, 0, 1, 4, 1, width, MARGIN, R.string.one, ContextCompat.getColor(this, R.color.colorOther));

        // Add the two button
        buildButton(main_grid, 1, 1, 4, 1, width, MARGIN, R.string.two, ContextCompat.getColor(this, R.color.colorOther));

        // Add the three button
        buildButton(main_grid, 2, 1, 4, 1, width, MARGIN, R.string.three, ContextCompat.getColor(this, R.color.colorOther));

        // Add the plus button
        buildButton(main_grid, 3, 1, 4, 1, width, MARGIN, R.string.plus, ContextCompat.getColor(this, R.color.colorOther));


        ////////////////////////////////////
        // ROW 6
        ////////////////////////////////////

        // Add the zero button
        buildButton(main_grid, 0, 1, 5, 1, width, MARGIN, R.string.zero, ContextCompat.getColor(this, R.color.colorOther));

        // Add the equal button
        buildButton(main_grid, 1, 3, 5, 1, width * 3, MARGIN, R.string.equals, ContextCompat.getColor(this, R.color.colorEquals));


        // Add it back!
        setContentView(main_grid);
    }

    public void buildButton(GridLayout main_grid, int colSt, int colSi, int rowSt, int rowSi, int width, int rightMargin, int text, int color)
    {
        Button button = new Button(this);

        GridLayout.Spec rowSpec = GridLayout.spec(rowSt, rowSi);
        GridLayout.Spec columnSpec = GridLayout.spec(colSt, colSi);
        GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, columnSpec);
        params.rightMargin = rightMargin;
        params.bottomMargin = 5;
        button.setLayoutParams(params);

        // Set up the attributes
        button.setWidth(width);
        button.setHeight(height);
        button.setGravity(Gravity.CENTER);
        button.setText(text);
        button.setTextSize((int)(this.width * 0.08));
        button.setBackgroundColor(color);

        // Add to main grid
        main_grid.addView(button);
    }
}
