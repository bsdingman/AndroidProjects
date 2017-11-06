/*
    Bryan Dingman
    Test 2
*/

package bryan.dingmantest2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class DeleteActivity extends AppCompatActivity
{
    private SparseIntArray indexArr = new SparseIntArray();
    private DatabaseConnector dbc;
    private Spinner cb_Entries;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        // Get some elements
        cb_Entries = findViewById(R.id.cb_Entries);
        deleteButton = findViewById(R.id.btn_DeleteRecord);

        // Disable, we will enable later on.
        deleteButton.setEnabled(false);

        // Add the EH
        deleteButton.setOnClickListener(view -> deleteRecord());

        // Our database connector
        dbc = new DatabaseConnector(this);

        // Populate the records
        updateRecords();
    }

    /**
     * Updates the spinner
     */
    private void updateRecords()
    {
        ArrayAdapter<String> adapter;
        ArrayList<String> list = new ArrayList<>();

        ArrayList<ArrayList<String>> queryReturn = dbc.getStudentRecordsAsList();

        if (queryReturn.size() > 0)
        {
            // Loop through the returns and just add the ID to the spinner, Remember the IDs so we always get the correct entry
            int i = 0;
            for (ArrayList<String> arr : queryReturn)
            {
                list.add(arr.get(1) + " (" + arr.get(0) + ")");
                indexArr.put(i, Integer.parseInt(arr.get(0)));
                i++;
            }

            // This allows us to modify the spinner
            // Thx to https://stackoverflow.com/a/24825425
            adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, list);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            cb_Entries.setAdapter(adapter);

            // Enable this
            deleteButton.setEnabled(true);
        }
    }

    /**
     * Delete record based on index
     */
    private void deleteRecord()
    {
        boolean successful = dbc.deleteRecord(indexArr.get(cb_Entries.getSelectedItemPosition()) + "");

        Toast.makeText(this, successful ? "Record deleted!" : "Delete failed!", Toast.LENGTH_LONG).show();

        // Populate the records
        updateRecords();
    }

    public void goBack(View v)
    {
        this.finish();
    }
}
