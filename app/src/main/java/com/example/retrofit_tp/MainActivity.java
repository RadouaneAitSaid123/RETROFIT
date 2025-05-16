package com.example.retrofit_tp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit_tp.adapter.UserAdapter;
import com.example.retrofit_tp.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        SearchView searchView = findViewById(R.id.searchView);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.item_spacing);
        recyclerView.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        userAdapter = new UserAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(userAdapter);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getUsers().observe(this, users -> {
            if (users != null) {
                userAdapter.setUserList(users);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                userAdapter.filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                userAdapter.filter(newText);
                return false;
            }
        });
    }
}