package com.rayt.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    public RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        Adapter adapter = new Adapter();
        List<ShiftModel> shiftModelList = new ArrayList<ShiftModel>();
        shiftModelList.add(new ShiftModel("John Brian", "Employee Number One"));
        shiftModelList.add(new ShiftModel("Peter Shimbo", "Employee Number Two"));
        adapter.setShiftModelList(shiftModelList);
        adapter.setStartActivityCallback(new Adapter.StartActivityCallback() {
            @Override
            public void startActivityIntent(View v, ShiftModel shiftModel) {
                animateIntent(v,shiftModel);
            }
        });
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        linearLayoutManager.setAutoMeasureEnabled(false);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    public void animateIntent(View view, ShiftModel shiftModel) {

        // Ordinary Intent for launching a new activity
        Intent intent = new Intent(this, ShiftSwapActivity.class);
        Bundle bundle = new Bundle();


        intent.putExtra("SHIFT_MODEL", shiftModel);
        // Get the transition name from the string
        String transitionName = "usercard";

        // Define the view that the animation will start from

        ActivityOptionsCompat options =

                ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                        view,   // Starting view
                        transitionName    // The String
                );
        //Start the Intent
        ActivityCompat.startActivity(this, intent, options.toBundle());

    }
}
