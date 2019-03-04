package com.rayt.myapplication;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CalendarActivity extends AppCompatActivity {

    boolean isExpanded = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_view);
        setSupportActionBar(findViewById(R.id.toolbar));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        TextView title = findViewById(R.id.button);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM dd yyyy", Locale.getDefault());
        String month_name = month_date.format(calendar.getTime());
        title.setText(month_name);
        findViewById(R.id.button).setOnClickListener(v -> {
            isExpanded = !isExpanded;
            ((AppBarLayout) findViewById(R.id.appbarlayout)).setExpanded(isExpanded, true);
        });

        CalendarView calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            title.setText(getResources().getString(R.string.tool_bar_title_date, new DateFormatSymbols().getMonths()[month], String.valueOf(dayOfMonth), String.valueOf(year)));
            isExpanded = !isExpanded;
            ((AppBarLayout) findViewById(R.id.appbarlayout)).setExpanded(isExpanded, true);
        });

    }
}
