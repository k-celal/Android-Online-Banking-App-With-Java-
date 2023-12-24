package com.example.onlinebankingappproject.view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.onlinebankingappproject.R;
import com.example.onlinebankingappproject.adapters.TransactionHistoryAdapter;
import com.example.onlinebankingappproject.api.ApiAuthService;
import com.example.onlinebankingappproject.api.ApiGetTransactionService;
import com.example.onlinebankingappproject.model.ResponseModels.TransactionHistoryModel;
import com.example.onlinebankingappproject.model.ResponseModels.TransactionHistoryResponseModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TransactionHistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TransactionHistoryAdapter adapter;



    private ApiGetTransactionService apiGetTransactionService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);

        apiGetTransactionService = new ApiGetTransactionService(this);

        // Eğer gerçek bir API üzerinden veri alıyorsanız, bu kısmı uygun şekilde güncelleyin.




        recyclerView = findViewById(R.id.recyclerView);
        adapter = new TransactionHistoryAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        getTransactionHistoryData();
        // Adapter'a verileri set et
    }



    private void getTransactionHistoryData() {
        apiGetTransactionService.getTransactionHistory().thenAccept(responseData -> {
            System.out.println(responseData.getTransactionHistory());
            adapter.setData(responseData.getTransactionHistory());
        }).exceptionally(ex -> {
            // Hata durumunu ele al
            System.err.println("Error: " + ex.getMessage());
            return null;
        });
    }
}