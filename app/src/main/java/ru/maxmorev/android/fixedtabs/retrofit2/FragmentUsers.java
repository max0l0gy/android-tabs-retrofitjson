package ru.maxmorev.android.fixedtabs.retrofit2;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rick.a1402retrofitjson.R;

import java.util.List;

public class FragmentUsers extends FragmentTab {


    private static final String LOG_TAG = "FragmentUsers";

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mUsersList;
    private UsersAdapter mAdapter;

    public FragmentUsers(){

    }

    private final DataService.Listener usersListner = new DataService.Listener() {

        @Override
        public void onStateChanged(final DataService.State state) {
            switch (state) {
                case IDLE_USERS:
                    mSwipeRefreshLayout.setRefreshing(false);
                    mUsersList.setVisibility(View.VISIBLE);
                    mAdapter.setUsers(getDataService().getUsers());
                    setIsLoaded(true);
                    break;

                case LOADING_POSTS:
                    break;


                case LOADING_USERS:
                    mUsersList.setVisibility(View.GONE);
                    mSwipeRefreshLayout.setRefreshing(true);
                    break;

            }
        }

        @Override
        public void onPostsLoaded(final List<Post> posts) {
            Toast.makeText(getContext(), "Loading posts complete", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPostsLoadingFailed(final Throwable t) {
            Toast.makeText(getContext(), "Error loading posts", Toast.LENGTH_SHORT).show();
            Log.e(LOG_TAG, "Error loading posts", t);
        }

        @Override
        public void onUsersLoaded(List<User> users) {
            Toast.makeText(getContext(), "Loading users complete", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onUsersLoadingFailed(Throwable t) {
            Toast.makeText(getContext(), "Error loading users", Toast.LENGTH_SHORT).show();
            Log.e(LOG_TAG, "Error loading posts", t);
        }
    };



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(LOG_TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment_users, container, false);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.view_swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataService().loadUsers();
            }
        });

        mAdapter = new UsersAdapter(getLayoutInflater());
        mUsersList = (RecyclerView) view.findViewById(R.id.view_users);
        mUsersList.setLayoutManager(new LinearLayoutManager(getContext()));
        mUsersList.setAdapter(mAdapter);
        setIsLoaded(false);
        getDataService().setUsersListner(usersListner);
        return view;
    }

    @Override
    public void loadData() {
        super.loadData();
        getDataService().loadUsers();
    }

    @Override
    public void onResume() {
        super.onResume();
        getDataService().setUsersListner(usersListner);
    }

    @Override
    public void onPause() {
        super.onPause();
        getDataService().setUsersListner(null);
    }


}
