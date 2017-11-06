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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity
{
    private EditText name, email, major, gpa;
    private Button addButton;
    private DatabaseConnector dbc;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // Initialize the text change handler
        TextChangeHandler tch = new TextChangeHandler();

        // Database connector
        dbc = new DatabaseConnector(this);

        // Grab some elements
        name = findViewById(R.id.tb_AddName);
        email = findViewById(R.id.tb_AddEmail);
        major = findViewById(R.id.tb_AddMajor);
        gpa = findViewById(R.id.tb_AddGPA);
        addButton = findViewById(R.id.btn_AddRecord);

        // Disable this, we will enable it later
        addButton.setEnabled(false);

        // Add these text changer so we know when to enable the add button
        name.addTextChangedListener(tch);
        email.addTextChangedListener(tch);
        major.addTextChangedListener(tch);
        gpa.addTextChangedListener(tch);

        // Add the EH to add the record
        addButton.setOnClickListener(view -> addRecord());
    }

    /**
     * Add Record
     * Adds a record based on text from the user
     */
    private void addRecord()
    {
        // Add the record
        dbc.insertRecord(name.getText().toString(), email.getText().toString(), major.getText().toString(), gpa.getText().toString());

        // Let em know
        Toast.makeText(AddActivity.this, "Record for " + name.getText().toString() + " has been added!", Toast.LENGTH_LONG).show();
    }

    private class TextChangeHandler implements TextWatcher
    {
        public void afterTextChanged( Editable e )
        {
            // Only enable the add button if we have text for all fields
            addButton.setEnabled(!name.getText().toString().equals("") && !email.getText().toString().equals("") && !major.getText().toString().equals("") && !gpa.getText().toString().equals(""));
        }

        public void beforeTextChanged( CharSequence s, int start, int count, int after ) {}

        public void onTextChanged( CharSequence s, int start, int before, int after ) {}
    }

    public void goBack(View v)
    {
        this.finish();
    }
}
