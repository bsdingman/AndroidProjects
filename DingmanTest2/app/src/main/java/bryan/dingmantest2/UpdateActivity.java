/*
    Bryan Dingman
    Test 2
*/

package bryan.dingmantest2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity
{

    private DatabaseConnector dbc;
    private Button findStudent, updateRecord;
    private ArrayList<ArrayList<String>> queryReturn;
    private Spinner cb_Returns;
    private EditText studentName, editName, editEmail, editMajor, editGPA;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        // Initialize all of these
        TextChangeHandler tch = new TextChangeHandler();
        dbc = new DatabaseConnector(this);
        findStudent = findViewById(R.id.btn_Find);
        studentName = findViewById(R.id.tb_Name);
        cb_Returns = findViewById(R.id.cb_Returns);
        editName = findViewById(R.id.tb_EditName);
        editEmail = findViewById(R.id.tb_EditEmail);
        editMajor = findViewById(R.id.tb_EditMajor);
        editGPA = findViewById(R.id.tb_EditGPA);
        updateRecord = findViewById(R.id.btn_UpdateRecord);

        // Hide all of these
        editName.setVisibility(View.INVISIBLE);
        editEmail.setVisibility(View.INVISIBLE);
        editMajor.setVisibility(View.INVISIBLE);
        editGPA.setVisibility(View.INVISIBLE);
        updateRecord.setVisibility(View.INVISIBLE);

        // Disable this right away, we will enable it later
        findStudent.setEnabled(false);

        // Set up the event handlers
        findStudent.setOnClickListener(view -> findRecord());

        // Set on click listener
        (findViewById(R.id.btn_UpdateRecord)).setOnClickListener(view -> updateRecord());

        // Text change listener
        studentName.addTextChangedListener(tch);

        // Select listener
        cb_Returns.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id)
            {
                editName.setVisibility(View.VISIBLE);
                editEmail.setVisibility(View.VISIBLE);
                editMajor.setVisibility(View.VISIBLE);
                editGPA.setVisibility(View.VISIBLE);
                updateRecord.setVisibility(View.VISIBLE);

                displayRecord(cb_Returns.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }

    /**
     * Find any records based on the name input
     */
    private void findRecord()
    {
        ArrayAdapter<String> adapter;
        ArrayList<String> list = new ArrayList<>();

        queryReturn = dbc.getRecord(studentName.getText().toString());

        if (queryReturn.size() > 0)
        {
            // Loop through the returns and just add the ID to the spinner
            for (ArrayList<String> arr : queryReturn)
                list.add(arr.get(0) + " (" + arr.get(1) + ")");

            // This allows us to modify the spinner
            // Thx to https://stackoverflow.com/a/24825425
            adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, list);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            cb_Returns.setAdapter(adapter);
        }
    }

    /**
     * Display the record of the selected index
     * @param _index
     */
    private void displayRecord(int _index)
    {
        ArrayList<String> record = queryReturn.get(_index);

        if (record != null)
        {
            editName.setText(record.get(1));
            editEmail.setText(record.get(2));
            editMajor.setText(record.get(3));
            editGPA.setText(record.get(4));
        }
    }

    /**
     * Update the record
     */
    private void updateRecord()
    {
        int currentIndex = cb_Returns.getSelectedItemPosition();
        ArrayList<String[]> params = new ArrayList<>();
        ArrayList<String> record = queryReturn.get(currentIndex);

        if (record != null)
        {
            // Only update name if it's changed
            if (!editName.getText().toString().equals(record.get(1)))
                params.add(new String[] {"name", editName.getText().toString()});

            // Only update the email if it's changed
            if (!editEmail.getText().toString().equals(record.get(2)))
                params.add(new String[] {"email", editEmail.getText().toString()});

            // Only update the major if it's changed
            if (!editMajor.getText().toString().equals(record.get(3)))
                params.add(new String[] {"major", editMajor.getText().toString()});

            // Update the GPA if it's changed
            if (!editGPA.getText().toString().equals(record.get(4)))
                params.add(new String[] {"gpa", editGPA.getText().toString()});

            // Update it!
            dbc.updateRecord(record.get(0), params);

            // Let us know
            Toast.makeText(UpdateActivity.this, "Entry has been updated!", Toast.LENGTH_LONG).show();
        }
    }

    public void goBack(View v)
    {
        this.finish();
    }

    private class TextChangeHandler implements TextWatcher
    {
        public void afterTextChanged( Editable e )
        {
            findStudent.setEnabled(!studentName.getText().toString().equals(""));

        }

        public void beforeTextChanged( CharSequence s, int start,
                                       int count, int after ) {
        }

        public void onTextChanged( CharSequence s, int start,
                                   int before, int after ) {
        }
    }
}
