package com.example.deadline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    private Button startBtn;
    private Button endBtn;
    private CalendarView startCalendar;
    private CalendarView endCalendar;
    private Button okBtn;
    private String startDateTxt;
    private long startDate;
    private String endDateTxt;
    private long endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inIt();
    }

    private void inIt() {
        startBtn = findViewById(R.id.start);
        endBtn = findViewById(R.id.end);
        startCalendar = findViewById(R.id.startsCalendar);
        endCalendar = findViewById(R.id.endCalendar);
        okBtn = findViewById(R.id.okBtn);

        startCalendar.setVisibility(View.GONE);
        endCalendar.setVisibility(View.GONE);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCalendar.setVisibility(View.VISIBLE);
                endCalendar.setVisibility(View.GONE);
            }
        });

        endBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCalendar.setVisibility(View.GONE);
                endCalendar.setVisibility(View.VISIBLE);
            }
        });

        startCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                startDateTxt = i + "-" + i1 + "-" + i2;
                startBtn.setText("Дата начала задачи: " + startDateTxt);
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.set(i, i1, i2);
                startDate = gregorianCalendar.getTimeInMillis();
                calendarView.setVisibility(View.GONE);
            }
        });

        endCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                endDateTxt = i + "-" + i1 + "-" + i2;
                endBtn.setText("Дата окончания задачи: " + endDateTxt);
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.set(i, i1, i2);
                endDate = gregorianCalendar.getTimeInMillis();
                calendarView.setVisibility(View.GONE);
            }
        });

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(startDate > endDate) {
                    Toast.makeText(MainActivity.this, "Ошибка", Toast.LENGTH_LONG).show();
                    startBtn.setText(getText(R.string.start_date).toString());
                    endBtn.setText(getText(R.string.end_date).toString());
                } else {
                    Toast.makeText(MainActivity.this, "Старт: " + startDateTxt +
                            " Окончание: " + endDateTxt, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}