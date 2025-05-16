package com.example.retrofit_tp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit_tp.R;
import com.example.retrofit_tp.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> userList;
    private List<User> filteredList = new ArrayList<>();

    public void setUserList(List<User> users) {
        this.userList = users;
        this.filteredList = new ArrayList<>(users);
        notifyDataSetChanged();
    }

    public void filter(String query) {
        filteredList.clear();
        if (query.isEmpty()) {
            filteredList.addAll(userList);
        } else {
            for (User user : userList) {
                if (user.getName().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(user);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView name, email, phone, address, company, website;

        public UserViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.userName);
            email = itemView.findViewById(R.id.userEmail);
            phone = itemView.findViewById(R.id.userPhone);
            address = itemView.findViewById(R.id.userAddress);
            company = itemView.findViewById(R.id.userCompany);
            website = itemView.findViewById(R.id.website);
        }
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = filteredList.get(position);
        holder.name.setText(user.getName());
        holder.email.setText(user.getEmail());
        holder.phone.setText(user.getPhone());

        String address = user.getAddress().getStreet() + ", " +
                user.getAddress().getSuite() + ", " +
                user.getAddress().getCity() + ", " +
                user.getAddress().getZipcode() + ", " + user.getAddress().getGeo().getLat() + ", " + user.getAddress().getGeo().getLng();
        holder.address.setText(address);
        String website = user.getWebsite();
        holder.website.setText(website);

        String company = user.getCompany().getName() + " - " +
                user.getCompany().getCatchPhrase() + " - " + user.getCompany().getBs();
        holder.company.setText(company);
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

}
