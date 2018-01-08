package com.example.simon.poesiealbum;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.prefs.Preferences;

public class EntryActivity extends Activity {

    private int entryId;
    private EditText editText_name, editText_essen, editText_spitz, editText_alsKind, editText_warum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        editText_name = findViewById(R.id.editText_name);
        editText_essen = findViewById(R.id.editText_essen);
        editText_spitz = findViewById(R.id.editText_spitz);
        editText_alsKind = findViewById(R.id.editText_alsKind);
        editText_warum = findViewById(R.id.editText_warum);

        TextWatcher tw = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                indicateUnfilledFields();
            }
        };

        editText_name.addTextChangedListener(tw);
        editText_essen.addTextChangedListener(tw);
        editText_spitz.addTextChangedListener(tw);
        editText_alsKind.addTextChangedListener(tw);
        editText_warum.addTextChangedListener(tw);
    }

    private void indicateUnfilledFields()
    {
        if (editText_name.getText().length() == 0)
            editText_name.setError("ToDo");
        else
            editText_name.setError(null);

        if (editText_essen.getText().length() == 0)
            editText_essen.setError("ToDo");
        else
            editText_essen.setError(null);

        if (editText_spitz.getText().length() == 0)
            editText_spitz.setError("ToDo");
        else
            editText_spitz.setError(null);

        if (editText_alsKind.getText().length() == 0)
            editText_alsKind.setError("ToDo");
        else
            editText_alsKind.setError(null);

        if (editText_warum.getText().length() == 0)
            editText_warum.setError("ToDo");
        else
            editText_warum.setError(null);
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences prefs = getSharedPreferences(entryId+"", MODE_PRIVATE);
        SharedPreferences.Editor e = prefs.edit();
        e.putString("name", editText_name.getText().toString());
        e.putString("essen", editText_essen.getText().toString());
        e.putString("spitz", editText_spitz.getText().toString());
        e.putString("alsKind", editText_alsKind.getText().toString());
        e.putString("warum", editText_warum.getText().toString());
        e.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();

        //fill fields
        Intent i = getIntent();
        entryId = i.getIntExtra("entryId", 1);

        SharedPreferences prefs = getSharedPreferences(entryId+"", MODE_PRIVATE);
        editText_name.setText(prefs.getString("name", ""));
        editText_essen.setText(prefs.getString("essen", ""));
        editText_spitz.setText(prefs.getString("spitz", ""));
        editText_alsKind.setText(prefs.getString("alsKind", ""));
        editText_warum.setText(prefs.getString("warum", ""));

        //indicate unfilled fields
        indicateUnfilledFields();
    }

    public void GoToPrevEntry(View sender){
        Intent i = new Intent(this, EntryActivity.class);
        if (entryId == 1)
            i.putExtra("entryId", 10);
        else
            i.putExtra("entryId", entryId-1);

        startActivity(i);
    }

    public void GoToNextEntry(View sender){
        Intent i = new Intent(this, EntryActivity.class);
        if (entryId == 10)
            i.putExtra("entryId", 1);
        else
            i.putExtra("entryId", entryId+1);

        startActivity(i);
    }
}
