package pl.zabrze.zs10.formularze4pa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Calendar calendar;
    Button button;
    DatePicker datePicker;
    TimePicker timePicker;
    Button buttonZegar;
    Button buttonData;
    Button buttonWyczysc

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.buttonWyswietl);
        buttonZegar = findViewById(R.id.buttonZegar);
        buttonData = findViewById(R.id.buttonData);
        buttonWyczysc = findViewById(R.id.buttonWyczysc);
        datePicker = findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.timepicker);
        datePicker.setOnDateChangedListener(
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                        //i rok
                        //i1 miesiac i2 dzien
                        Toast.makeText(MainActivity.this, String.format("%02d:%02d:%04d", i2, i1, i), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        calendar = Calendar.getInstance();
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int dzien = datePicker.getDayOfMonth();
                        int miesiac = datePicker.getMonth();
                        int rok = datePicker.getYear();
                        calendar.set(rok, miesiac - 1, dzien);
                        String dataWyswietlona = String.format("data: %02d:%02d:%04d", dzien, miesiac, rok);
                        Toast.makeText(MainActivity.this, dataWyswietlona, Toast.LENGTH_SHORT).show();

                        int minuty = timePicker.getMinute();
                        int godziny = timePicker.getHour();

                        String godzinaWyswietlona = String.format("godzina: %02d:%02d", godziny, minuty);
                        Toast.makeText(MainActivity.this, godzinaWyswietlona, Toast.LENGTH_SHORT).show();

                    }
                }
        );
        buttonZegar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {

                    }
                }, 17, 30, true);
                timePickerDialog.show();
            }
        });
        Calendar calendar = Calendar.getInstance();

        //Toast.makeText(this, String.valueOf(calendar.YEAR), Toast.LENGTH_SHORT).show();
        buttonData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                            }
                        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });
        buttonWyczysc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

                alertDialog.setMessage("Czy na pewno chcesz wyczyścić formularz");
                alertDialog.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                EditText editText = findViewById(R.id.editText);
                                editText.setText("");
                            }
                        }
                );
                alertDialog.create();


            }
        });
    }
}