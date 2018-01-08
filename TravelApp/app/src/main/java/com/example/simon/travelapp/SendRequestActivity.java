package com.example.simon.travelapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

public class SendRequestActivity extends Activity
{

    private String emailAdress;
    private String destination;
    private String dateFrom, dateTo;
    private int amountAdults, amountKids, amountPets;
    private String priceRangeMin, priceRangeMax;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_request);

        setTitle("Confirm and Request");

        Intent intent=getIntent();

        destination = intent.getStringExtra("destination");
        dateFrom = intent.getStringExtra("dateFrom");
        dateTo = intent.getStringExtra("dateTo");
        amountAdults = intent.getIntExtra("amountAdults", 1);
        amountKids = intent.getIntExtra("amountKids", 0);
        amountPets = intent.getIntExtra("amountPets", 0);
        priceRangeMin = intent.getStringExtra("priceRangeMin");
        priceRangeMax = intent.getStringExtra("priceRangeMax");

        ((EditText)findViewById(R.id.editText_destination)).setText(destination + "");
        ((EditText)findViewById(R.id.editText_dateFrom)).setText(dateFrom + "");
        ((EditText)findViewById(R.id.editText_dateTo)).setText(dateTo + "");
        ((TextView)findViewById(R.id.textView_AmountAdultsNumber)).setText(amountAdults + "");
        ((TextView)findViewById(R.id.textView_AmountKidsNumber)).setText(amountKids + "");
        ((TextView)findViewById(R.id.textView_AmountPetsNumber)).setText(amountPets + "");
        ((EditText)findViewById(R.id.editText_PriceRangeMin)).setText(priceRangeMin + "");
        ((EditText)findViewById(R.id.editText_PriceRangeMax)).setText(priceRangeMax + "");

        final EditText editText_Email = findViewById(R.id.editText_EmailAdress);
        editText_Email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (!isValidEmail(editable.toString()))
                    editText_Email.setError("invalid email adress!");
            }
        });

    }

    public void button_SendRequest_OnClick(View sender)
    {

        EditText editText_emailAdress = ((EditText)findViewById(R.id.editText_EmailAdress));
        if (!isValidEmail(editText_emailAdress.getText().toString())) {
            editText_emailAdress.setError("invalid email adress!");
            return;
        }
        editText_emailAdress.setError(null);

        Intent intent = new Intent(this, EndActivity.class);
        intent.putExtra("email", editText_emailAdress.getText().toString());
        startActivity(intent);
        finish();
    }

    private boolean isValidEmail(String email) {
        if (email.length() == 0)
            return false;

        return (email.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"));
    }
}
