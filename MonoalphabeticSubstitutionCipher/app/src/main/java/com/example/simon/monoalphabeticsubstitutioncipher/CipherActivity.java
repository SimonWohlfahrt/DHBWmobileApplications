package com.example.simon.monoalphabeticsubstitutioncipher;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class CipherActivity extends AppCompatActivity {

    private enum encryptionMethod {
        SR01,
        BF01,
        //Manual
    };

    String string_manualKey = "";

    private final char unknownToken = '\'';

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cipher);

        // init
        setTitle("Monoalphabetic Substitution");

        // disable input for output editText
        EditText editText_output = findViewById(R.id.editText_Output);
        editText_output.setKeyListener(null);

        // init Spinner
        Spinner spinner_encryptionMethod = findViewById(R.id.spinner_encryptionMethod);
        spinner_encryptionMethod.setAdapter(new ArrayAdapter<encryptionMethod>(this,
                android.R.layout.simple_list_item_1,
                encryptionMethod.values()));
    }

    public void button_encrypt_onClick(View sender) {
        HashMap<Character, Character> substitutionMap = getSubstitutionMap(getSelectedEncryptionMethod());

        if (substitutionMap == null)
            return;

        EditText editText_input = findViewById(R.id.editText_Input);

        String input = editText_input.getText().toString();
        String output = getSubstitution(input, substitutionMap);

        EditText editText_output = findViewById(R.id.editText_Output);
        editText_output.setText(output);
    }

    public void button_decrypt_onClick(View sender) {
        HashMap<Character, Character> substitutionMap = getSubstitutionMapReversed(getSelectedEncryptionMethod());

        if (substitutionMap == null)
            return;

        EditText editText_input = findViewById(R.id.editText_Input);

        String input = editText_input.getText().toString().toUpperCase();
        String output = getSubstitution(input, substitutionMap);

        EditText editText_output = findViewById(R.id.editText_Output);
        editText_output.setText(output);
    }

    private String getSubstitution(String input, HashMap<Character, Character> substitutionMap) {
        String output = "";

        input = input.toUpperCase();

        for (char c: input.toCharArray()) {
            if (c == ' ' || c == '\n' || c == '\r')
                output += c;
            else if (substitutionMap.containsKey(c))
                output += substitutionMap.get(c);
            else
                output += unknownToken;
        }

        return output;
    }

    private HashMap<Character, Character> getSubstitutionMap(encryptionMethod encryption) {

        HashMap<Character, Character> substitutionMap = new HashMap<Character, Character>();
        
        switch (encryption) {
            case SR01:
                substitutionMap.put('A', 'G');
                substitutionMap.put('B', 'U');
                substitutionMap.put('C', 'H');
                substitutionMap.put('D', 'P');
                substitutionMap.put('E', 'A');
                substitutionMap.put('F', 'T');
                substitutionMap.put('G', 'M');
                substitutionMap.put('H', 'X');
                substitutionMap.put('I', 'Q');
                substitutionMap.put('J', 'W');
                substitutionMap.put('K', 'I');
                substitutionMap.put('L', 'V');
                substitutionMap.put('M', 'N');
                substitutionMap.put('N', 'C');
                substitutionMap.put('O', 'Y');
                substitutionMap.put('P', 'O');
                substitutionMap.put('Q', 'R');
                substitutionMap.put('R', 'K');
                substitutionMap.put('S', 'J');
                substitutionMap.put('T', 'L');
                substitutionMap.put('U', 'S');
                substitutionMap.put('V', 'D');
                substitutionMap.put('W', 'F');
                substitutionMap.put('X', 'Z');
                substitutionMap.put('Y', 'B');
                substitutionMap.put('Z', 'E');
                
                break;
            case BF01:
                substitutionMap.put('A', 'O');
                substitutionMap.put('B', 'I');
                substitutionMap.put('C', 'D');
                substitutionMap.put('D', 'U');
                substitutionMap.put('E', 'Q');
                substitutionMap.put('F', 'X');
                substitutionMap.put('G', 'P');
                substitutionMap.put('H', 'H');
                substitutionMap.put('I', 'T');
                substitutionMap.put('J', 'W');
                substitutionMap.put('K', 'M');
                substitutionMap.put('L', 'E');
                substitutionMap.put('M', 'B');
                substitutionMap.put('N', 'L');
                substitutionMap.put('O', 'F');
                substitutionMap.put('P', 'R');
                substitutionMap.put('Q', 'Y');
                substitutionMap.put('R', 'C');
                substitutionMap.put('S', 'S');
                substitutionMap.put('T', 'K');
                substitutionMap.put('U', 'A');
                substitutionMap.put('V', 'Z');
                substitutionMap.put('W', 'N');
                substitutionMap.put('X', 'V');
                substitutionMap.put('Y', 'J');
                substitutionMap.put('Z', 'G');
                break;

            /*case Manual:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Define your Key");

                // Set up the input
                final EditText input = new EditText(this);

                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                // Set up the buttons
                builder.setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        string_manualKey = input.getText().toString();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();


                if (string_manualKey.length() == 26) {

                    char[] alphabeth = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
                    char[] key = string_manualKey.toUpperCase().toCharArray();
                    for (int i = 0; i < alphabeth.length; i++) {
                        substitutionMap.put(alphabeth[i], key[i]);
                    }
                }

                break;*/
        }

        return substitutionMap;
    }

    private HashMap<Character, Character> getSubstitutionMapReversed(encryptionMethod encryption) {

        HashMap<Character, Character> substitutionMapReversed = new HashMap<Character, Character>();

        for (Map.Entry<Character, Character> entry: getSubstitutionMap(encryption).entrySet()) {
            substitutionMapReversed.put(entry.getValue(), entry.getKey());
        }

        return substitutionMapReversed;
    }

    private encryptionMethod getSelectedEncryptionMethod() {

        Spinner spinner_encryptionMethod = findViewById(R.id.spinner_encryptionMethod);
        String selection = spinner_encryptionMethod.getSelectedItem().toString();

        return encryptionMethod.valueOf(selection);
    }

}
