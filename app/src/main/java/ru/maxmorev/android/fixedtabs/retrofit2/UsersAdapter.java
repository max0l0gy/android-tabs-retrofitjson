package ru.maxmorev.android.fixedtabs.retrofit2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private List<User> mUsers = new ArrayList<>();

    public  UsersAdapter(LayoutInflater inflater){
        this.mInflater = inflater;
    }

    @Override
    public UsersAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return new UsersAdapter.ViewHolder(mInflater.inflate(android.R.layout.simple_list_item_1, parent, false));
    }

    @Override
    public void onBindViewHolder(final UsersAdapter.ViewHolder holder, final int position) {
        holder.titleTextView.setText(mUsers.get(position).name);
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public void setUsers(final List<User> userList) {
        mUsers = userList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView titleTextView;

        public ViewHolder(final View itemView) {
            super(itemView);

            titleTextView = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }
}