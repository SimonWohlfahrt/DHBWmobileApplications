package com.example.simon.travelapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class StartActivity extends Activity {

    private NumberPicker numberPicker_AmountAdults, numberPicker_AmountKids, numberPicker_AmountPets;

    private EditText editText_DateFrom, editText_DateTo;

    private final int int_AmountMinValue = 0;
    private final int int_AmountMaxValue = 10;

    private final int int_PriceRangeMinValue = 0;
    private final int int_PriceRangeMaxValue = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        // init
        setTitle("Travel App");

        // initialize NumberPicker
        numberPicker_AmountAdults = findViewById(R.id.numberPicker_AmountAdults);
        numberPicker_AmountAdults.setMinValue(int_AmountMinValue);
        numberPicker_AmountAdults.setMaxValue(int_AmountMaxValue);

        numberPicker_AmountKids = findViewById(R.id.numberPicker_AmountKids);
        numberPicker_AmountKids.setMinValue(int_AmountMinValue);
        numberPicker_AmountKids.setMaxValue(int_AmountMaxValue);

        numberPicker_AmountPets = findViewById(R.id.numberPicker_AmountPets);
        numberPicker_AmountPets.setMinValue(int_AmountMinValue);
        numberPicker_AmountPets.setMaxValue(int_AmountMaxValue);

        // set initial values
        numberPicker_AmountAdults.setValue(1);

        editText_DateFrom = findViewById(R.id.editText_dateFrom);
        editText_DateTo = findViewById(R.id.editText_dateTo);

        // retrieve input values
        if (savedInstanceState != null) {
            numberPicker_AmountAdults.setValue(savedInstanceState.getInt("numberPicker_AmountAdults_value"));
            numberPicker_AmountKids.setValue(savedInstanceState.getInt("numberPicker_AmountKids_value"));
            numberPicker_AmountPets.setValue(savedInstanceState.getInt("numberPicker_AmountPets_value"));
        }
        else {
            Calendar calendar_now = Calendar.getInstance();
            String string_dateNow = SimpleDateFormat.getDateInstance().format(new GregorianCalendar(calendar_now.get(Calendar.YEAR), calendar_now.get(Calendar.MONTH), calendar_now.get(Calendar.DAY_OF_MONTH)).getTime());

            editText_DateFrom.setText(string_dateNow);
            editText_DateTo.setText(string_dateNow);
        }

        TextWatcher textWatcher_DateValidation = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
                Date dateFrom = null;
                Date dateTo = null;

                try {
                    dateFrom = formatter.parse(((EditText)findViewById(R.id.editText_dateFrom)).getText().toString());
                    dateTo = formatter.parse(((EditText)findViewById(R.id.editText_dateTo)).getText().toString());

                    if (dateFrom.after(dateTo))
                        ((EditText)findViewById(R.id.editText_dateFrom)).setError("Time travel is not possible. We're working on it!");
                    else
                        ((EditText)findViewById(R.id.editText_dateFrom)).setError(null);

                } catch (ParseException e) {

                }
            }
        };

        editText_DateFrom.addTextChangedListener(textWatcher_DateValidation);
        editText_DateTo.addTextChangedListener(textWatcher_DateValidation);

        TextWatcher textWatcher_PriceRange = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (Double.parseDouble(((EditText) findViewById(R.id.editText_PriceRangeMin)).getText().toString()) > Double.parseDouble(((EditText) findViewById(R.id.editText_PriceRangeMax)).getText().toString()))
                    ((EditText) findViewById(R.id.editText_PriceRangeMin)).setError("When the minimum is greater than the maximum...");
                else
                    ((EditText) findViewById(R.id.editText_PriceRangeMin)).setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        EditText editText_priceMin = findViewById(R.id.editText_PriceRangeMin);
        EditText editText_priceMax = findViewById(R.id.editText_PriceRangeMax);
        editText_priceMin.addTextChangedListener(textWatcher_PriceRange);
        editText_priceMax.addTextChangedListener(textWatcher_PriceRange);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putInt("numberPicker_AmountAdults_value", numberPicker_AmountAdults.getValue());
        savedInstanceState.putInt("numberPicker_AmountKids_value", numberPicker_AmountKids.getValue());
        savedInstanceState.putInt("numberPicker_AmountPets_value", numberPicker_AmountPets.getValue());
    }

    public void button_SubmitInput_OnClick(View sender) {

        if (!isValidInput()) {
            // Show Error Message
            return;
        }
            // collect Data
            EditText editText_destination = findViewById(R.id.editText_destination);
            editText_DateFrom = findViewById(R.id.editText_dateFrom);
            editText_DateTo = findViewById(R.id.editText_dateTo);
            EditText editText_PriceRangeMin = findViewById(R.id.editText_PriceRangeMin);
            EditText editText_PriceRangeMax = findViewById(R.id.editText_PriceRangeMax);

            // init intent
            Intent intentSubmit = new Intent(this, SendRequestActivity.class);
            intentSubmit.putExtra("destination", editText_destination.getText().toString());
            intentSubmit.putExtra("dateFrom", editText_DateFrom.getText().toString());
            intentSubmit.putExtra("dateTo", editText_DateTo.getText().toString());
            intentSubmit.putExtra("amountAdults", numberPicker_AmountAdults.getValue());
            intentSubmit.putExtra("amountKids", numberPicker_AmountKids.getValue());
            intentSubmit.putExtra("amountPets", numberPicker_AmountPets.getValue());
            intentSubmit.putExtra("priceRangeMin", editText_PriceRangeMin.getText().toString());
            intentSubmit.putExtra("priceRangeMax", editText_PriceRangeMax.getText().toString());

            startActivity(intentSubmit);
    }

    public void editText_dateRange_onClick(final View sender) {

        final int year;
        final int month;
        final int day;

        if (((EditText)sender).getText().toString().length() == 0) {
            Calendar calendar_now = Calendar.getInstance();
            year = calendar_now.get(Calendar.YEAR);
            month = calendar_now.get(Calendar.MONTH);
            day = calendar_now.get(Calendar.DAY_OF_MONTH);
        }
        else
        {
            // Datum parsen und DatePicker mit aktuellem Datum initialisieren!
            DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            Date date = null;
            try {
                date = formatter.parse(((EditText)sender).getText().toString());
            } catch (ParseException e) {
                date = Calendar.getInstance().getTime();
            }

            Calendar calendar_then = Calendar.getInstance();
            calendar_then.setTime(date);

            year = calendar_then.get(Calendar.YEAR);
            month = calendar_then.get(Calendar.MONTH);
            day = calendar_then.get(Calendar.DAY_OF_MONTH);
        }

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year_selected, int month_selected, int day_selected) {

                EditText editTextSender = (EditText)sender;
                String date_formatted = SimpleDateFormat.getDateInstance().format(new GregorianCalendar(year_selected, month_selected, day_selected).getTime());
                editTextSender.setText(date_formatted);
            }
        }, year, month, day);

        datePickerDialog.setTitle("Select date...");
        datePickerDialog.show();

    }

    private boolean isValidInput() {
        // dateFrom darf nicht kleiner als dateNow sein! OK
        // dateFrom darf nicht größer als dateTo sein! OK
        // Alle Felder müssen ausgefüllt sein!
        // Es können nicht 0 Personen reisen!
        // ...

        boolean isValid = true;

        // destination
        if (((EditText)findViewById(R.id.editText_destination)).getText().toString().length() == 0) {
            ((EditText)findViewById(R.id.editText_destination)).setError("And where to go?");
            isValid = false;
        }
        else
            ((EditText)findViewById(R.id.editText_destination)).setError(null);

        // Datumsvalidierung
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date dateFrom = null;
        Date dateTo = null;

        try {
            dateFrom = formatter.parse(((EditText)findViewById(R.id.editText_dateFrom)).getText().toString());
            dateTo = formatter.parse(((EditText)findViewById(R.id.editText_dateTo)).getText().toString());

            Calendar calendar_now = Calendar.getInstance();
            calendar_now.set(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);

            if (dateFrom.after(dateTo)) {
                ((EditText) findViewById(R.id.editText_dateFrom)).setError("Time travel is not possible. We're working on it!");
                isValid = false;
            }
            else {
                ((EditText) findViewById(R.id.editText_dateFrom)).setError(null);
            }
        } catch (ParseException e) {

        }

        if (((NumberPicker)findViewById(R.id.numberPicker_AmountAdults)).getValue() + ((NumberPicker)findViewById(R.id.numberPicker_AmountKids)).getValue() == 0) {
            ((TextView)findViewById(R.id.textView_NumberPickerError)).setVisibility(View.VISIBLE);
            isValid = false;
        }
        else {
            ((TextView)findViewById(R.id.textView_NumberPickerError)).setVisibility(View.GONE);
        }

        if (Double.parseDouble(((EditText) findViewById(R.id.editText_PriceRangeMin)).getText().toString()) > Double.parseDouble(((EditText) findViewById(R.id.editText_PriceRangeMax)).getText().toString())) {
            ((EditText) findViewById(R.id.editText_PriceRangeMin)).setError("When the minimum is greater than the maximum...");
            isValid = false;
        }
        else
            ((EditText) findViewById(R.id.editText_PriceRangeMin)).setError(null);

        return isValid;
    }
}
