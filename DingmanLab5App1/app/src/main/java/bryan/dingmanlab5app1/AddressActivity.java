/*
    Bryan Dingman
    Lab 5 App 1
    Show 5 pictures of campuses and open a new activity when they are touched
 */

package bryan.dingmanlab5app1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class AddressActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        int name, address, city;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        // Set the text
        TextView tv_name = (TextView)findViewById(R.id.name);
        TextView tv_address = (TextView)findViewById(R.id.address);
        TextView tv_city = (TextView)findViewById(R.id.city);

        switch(MainActivity.SelectedCampus)
        {
            case "blount":
            {
                name = R.string.name_blount;
                address = R.string.address_blount;
                city = R.string.city_blount;
                break;
            }
            case "division":
            {
                name = R.string.name_division;
                address = R.string.address_division;
                city = R.string.city_division;
                break;
            }
            case "hardin":
            {
                name = R.string.name_hardin;
                address = R.string.address_hardin;
                city = R.string.city_hardin;
                break;
            }
            case "magnolia":
            {
                name = R.string.name_magnolia;
                address = R.string.address_magnolia;
                city = R.string.city_magnolia;
                break;
            }
            // Strawberry plains
            default:
            {
                name = R.string.name_strawberry;
                address = R.string.address_strawberry;
                city = R.string.city_strawberry;
                break;
            }
        }

        tv_name.setText(name);
        tv_address.setText(address);
        tv_city.setText(city);
    }

    public void goBack(View v)
    {
        this.finish();
    }
}
