package com.example.simon.poesiealbum;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EntrySelectActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_select);
    }

    public void GoToEntry(View sender)
    {
        Button b = ((Button)sender);
        int entryId = Integer.parseInt(b.getText().toString());

        Intent i = new Intent(this, EntryActivity.class);
        i.putExtra("entryId", entryId);

        startActivity(i);
    }
}
