package com.app.taskapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.app.taskapp.adapter.DataAdapter;
import com.app.taskapp.databinding.ActivityMainBinding;
import com.app.taskapp.listener.OnDataItemClickedListener;
import com.app.taskapp.model.Data;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private int positionToChange = -1;
    private DataAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolBar);

        setActions();
        initRecyclerView();

    }

    private void setActions() {
        binding.btnSave.setOnClickListener(view -> {
            Data data = new Data(binding.edtName.getText().toString(), binding.edtAddress.getText().toString());

            if (positionToChange != -1) {
                dataAdapter.modifyData(data, positionToChange);
                positionToChange = -1;
            } else {
                dataAdapter.insertData(data);
            }

            binding.edtAddress.setText("");
            binding.edtName.setText("");
        });

        binding.imgGame.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, TicTacToeActivity.class));
            ;
        });
    }

    private void initRecyclerView() {
        dataAdapter = new DataAdapter(new OnDataItemClickedListener() {
            @Override
            public void onDataItemClicked(Data data, int position) {
                positionToChange = position;
                binding.edtName.setText(data.getName());
                binding.edtAddress.setText(data.getAddress());
            }
        });
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(dataAdapter);
    }

}