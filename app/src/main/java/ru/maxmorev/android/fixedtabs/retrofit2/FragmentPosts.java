package ru.maxmorev.android.fixedtabs.retrofit2;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rick.a1402retrofitjson.R;

import java.util.List;

public class FragmentPosts extends FragmentTab {

    private static final String LOG_TAG = "FragmentPosts";


    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mPostsList;
    private PostsAdapter mAdapter;

    private final DataService.Listener postsListner = new DataService.Listener() {

        @Override
        public void onStateChanged(final DataService.State state) {
            switch (state) {
                case IDLE_POSTS:
                    mSwipeRefreshLayout.setRefreshing(false);
                    mPostsList.setVisibility(View.VISIBLE);
                    mAdapter.setPosts(getDataService().getPosts());
                    break;

                case LOADING_POSTS:
                    mPostsList.setVisibility(View.GONE);
                    mSwipeRefreshLayout.setRefreshing(true);
                    break;


                case LOADING_USERS:

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
            //Toast.makeText(getContext(), "Loading users complete", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onUsersLoadingFailed(Throwable t) {
            //Toast.makeText(getContext(), "Error loading users", Toast.LENGTH_SHORT).show();
            //Log.e(LOG_TAG, "Error loading posts", t);
        }
    };


    public FragmentPosts() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "onCreate");
        //DO NOTHING

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(LOG_TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment_posts, container, false);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.view_swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataService().loadPosts();
            }
        });

        mAdapter = new PostsAdapter(getLayoutInflater());
        mPostsList = (RecyclerView) view.findViewById(R.id.view_posts);
        mPostsList.setLayoutManager(new LinearLayoutManager(getContext()));
        mPostsList.setAdapter(mAdapter);
        mPostsList.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        loadData();
        getDataService().setPostsListner(postsListner);
        return view;
    }

    @Override
    public void loadData() {
        super.loadData();
        getDataService().loadPosts();
    }


    @Override
    public void onResume() {
        super.onResume();
        getDataService().setPostsListner(postsListner);
    }

    @Override
    public void onPause() {
        super.onPause();
        getDataService().setPostsListner(null);
    }
}
