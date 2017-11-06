/*
    Bryan Dingman
    Lab 4 App 1
    Extend the calculator app and add functionality to it
*/
package bryan.dingmanlab4app1;

import android.content.DialogInterface;

public class ClearDialog implements DialogInterface.OnClickListener
{
    Calculator calc;

    public ClearDialog(Calculator calc)
    {
        this.calc = calc;
    }

    @Override
    public void onClick(DialogInterface dialog, int which)
    {
        // If the button is Yes, clear it
        if (which == -1)
        {
            calc.clearOutput();
        }
    }
}
