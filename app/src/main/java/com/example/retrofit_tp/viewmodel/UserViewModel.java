package com.example.retrofit_tp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.retrofit_tp.model.User;
import com.example.retrofit_tp.repository.UserRepository;

import java.util.List;

public class UserViewModel extends ViewModel {
    private MutableLiveData<List<User>> users;
    private UserRepository userRepository;

    public UserViewModel() {
        userRepository = new UserRepository();
        users = new MutableLiveData<>();
        loadUsers();
    }

    public LiveData<List<User>> getUsers() {
        return users;
    }

    private void loadUsers() {
        userRepository.fetchUsers(users);
    }

}
