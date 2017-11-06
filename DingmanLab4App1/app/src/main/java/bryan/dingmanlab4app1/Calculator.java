/*
    Bryan Dingman
    Lab 4 App 1
    Extend the calculator app and add functionality to it
*/
package bryan.dingmanlab4app1;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

public class Calculator
{
    private String left = "";
    private String right = "";
    private String sign = "";
    private Context main;
    private TextView tv_output;
    private boolean equalsLock = false;
    private ClearDialog clearDialog = new ClearDialog(this);

    /**
     * Calculator Constructor
     * @param main
     * @param tv
     */
    public Calculator(Context main, TextView tv)
    {
        this.main = main;
        this.tv_output = tv;
    }

    /**
     * OnButtonClick event handler for our buttons
     * @param button
     */
    public void onButtonClick(Button button)
    {
        // Get the tag of the button
        String buttonTag = button.getTag().toString();

        // Make sure the length is less than or equal to 12 characters, we aren't locked because we clicked the equals sign, or the button is clear
        if ((tv_output.getText().length() <= 12 && !equalsLock) || buttonTag.equals("ac"))
        {
            // Switch through the buttons
            switch (buttonTag)
            {
                case "ac":
                {
                    AlertDialog.Builder builder;
                    builder = new AlertDialog.Builder(main);
                    builder.setTitle("All Clear Window")
                    .setMessage("Do you want to clear the output?")
                    .setPositiveButton("Yes", clearDialog)
                    .setNegativeButton("No", clearDialog)
                    .show();
                    break;
                }
                // Fall through
                case "+":
                case "-":
                case "x":
                case "/":
                case "%":
                {
                    // Don't assign a tag if we don't have anything on the left
                    if (!left.equals("") && sign.equals(""))
                    {
                        // Remember our sign
                        sign = button.getTag().toString();

                        // Set the output window
                        setOutput(left + " " + sign + " " + right);
                    }
                    break;
                }
                // Handle the equals sign
                case "=":
                {
                    // Make sure we have numbers for the left, right and a sign
                    if (!left.equals("") && !right.equals("") && !sign.equals(""))
                        calculate();
                    break;
                }
                // Any number button
                default:
                {
                    // We don't have a sign yet, so save it on the left
                    if (sign.equals(""))
                        left += button.getTag().toString();
                    else
                        right += button.getTag().toString();

                    // Set the output
                    setOutput(left + " " + sign + " " + right);
                    break;
                }
            }
        }
    }

    /**
     * Sets the output screen to the value of the string
     * @param value
     */
    private void setOutput(String value)
    {
        tv_output.setText(value);
    }

    /**
     * Clear the output screen and reset all our vars
     */
    public void clearOutput()
    {
        this.left = "";
        this.right = "";
        this.sign = "";
        this.equalsLock = false;
        setOutput("");
    }

    /**
     * Calculate and assign the output to the field
     */
    private void calculate()
    {
        // Lock it so we can't do anything but clear
        this.equalsLock = true;

        // Parse the values from our left and right
        int left = Integer.parseInt(this.left);
        int right = Integer.parseInt(this.right);

        // Default to zero
        int value = 0;

        // Switch our signs so we know what to do
        switch (sign)
        {
            case "+":
                value = left + right;
                break;
            case "-":
                value = left - right;
                break;
            case "x":
                value = left * right;
                break;
            case "/":
                value = left / right;
                break;
            case "%":
                value = left % right;
                break;
        }

        // Set the output
        setOutput(tv_output.getText().toString() + "\n= " + value);
    }
}
