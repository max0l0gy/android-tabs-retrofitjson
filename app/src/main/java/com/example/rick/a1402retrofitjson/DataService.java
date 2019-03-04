package com.example.rick.a1402retrofitjson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataService {


    /**
     *  инстанс синглтона нашего сервиса для работы с данными
     *
     *  */
    private static DataService INSTANCE;

    public enum State {

        IDLE_POSTS,
        IDLE_USERS,
        LOADING_POSTS,
        LOADING_USERS

    }

    /**
     *
     * состояние в текущий момент - IDLE || LOADING
     * */
    private State mState = State.IDLE_POSTS;

    private final Retrofit mRetrofit;

    private final DataBackend mBackend;

    private List<Post> mPosts = new ArrayList<>();
    private List<User> mUsers = new ArrayList<>();

    private Listener postsListner;
    private Listener usersListner;

    public static DataService getInstance() {
        if  (INSTANCE == null) {
            synchronized (DataService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DataService();
                }
           }
        }
        return(INSTANCE);
    }


    /**
    скрываем конструктор синглтона, инициализация единственного объекта класса и основных его полей
     **/
    private DataService(){
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mBackend = mRetrofit.create(DataBackend.class);
    }

    /**
    *   РЕАЛИЗАЦИЯ ОСНОВНЫХ МЕТОДОВ КЛАССОВ
    */

    /**
     * Изменение состояния сервиса
     * @param newState - новое состояние сервиса
     */
    private void changeState(final State newState) {

        if(mState==newState){
            return;
        }

           switch (newState){
               case LOADING_POSTS:
                   mState = newState;
                   if(postsListner!=null){
                       postsListner.onStateChanged(newState);
                   }
                   return;
               case LOADING_USERS:
                   mState = newState;
                   if(usersListner!=null) {
                       usersListner.onStateChanged(newState);
                   }
                   return;
               case IDLE_POSTS:
                   mState = newState;
                   if(postsListner!=null){
                       postsListner.onStateChanged(newState);
                   }
                   return;
               case IDLE_USERS:
                   mState = newState;
                   if(usersListner!=null) {
                       usersListner.onStateChanged(newState);
                   }
                   return;
           }



    }




    /**
     * Загрузка постов
     */
    public void loadPosts() {
        if (mState == State.LOADING_POSTS) {
            return;
        }

        changeState(State.LOADING_POSTS);
        mBackend.listPosts().enqueue(new Callback<List<Post>>() {

            @Override
            public void onResponse(final Call<List<Post>> call, final Response<List<Post>> response) {
                mPosts = response.body();
                if (postsListner != null) {
                    postsListner.onPostsLoaded(response.body());
                }
                changeState(State.IDLE_POSTS);
            }

            @Override
            public void onFailure(final Call<List<Post>> call, final Throwable t) {
                if (postsListner != null) {
                    postsListner.onPostsLoadingFailed(t);
                }
                changeState(State.IDLE_POSTS);
            }
        });
    }


    /**
     * Загрузка пользователей
     */
    public void loadUsers() {
        if (mState == State.LOADING_USERS) {
            return;
        }

        changeState(State.LOADING_USERS);
        mBackend.listUsers().enqueue(new Callback<List<User>>() {

            @Override
            public void onResponse(final Call<List<User>> call, final Response<List<User>> response) {
                mUsers = response.body();
                if (usersListner != null) {
                    usersListner.onUsersLoaded(response.body());
                }
                changeState(State.IDLE_USERS);
            }

            @Override
            public void onFailure(final Call<List<User>> call, final Throwable t) {
                if (usersListner != null) {
                    usersListner.onUsersLoadingFailed(t);
                }
                changeState(State.IDLE_USERS);
            }

            @Override
            protected void finalize() throws Throwable {
                super.finalize();
                changeState(State.IDLE_USERS);
            }
        });
    }



    /**
     * Устанавливаем листнер постов
     * @param listener
     */
    public void setPostsListner(final Listener listener) {
        postsListner = listener;
        if (postsListner != null) {
            postsListner.onStateChanged(mState);
        }
    }

    /**
     * Устанавливаем листнер постов
     * @param listener
     */
    public void setUsersListner(final Listener listener) {
        usersListner = listener;
        if (usersListner != null) {
            usersListner.onStateChanged(mState);
        }
    }

    public List<Post> getPosts() {
        return mPosts;
    }

    public List<User> getUsers() {
        return mUsers;
    }


    public interface Listener {

        void onStateChanged(State state);

        void onPostsLoaded(List<Post> posts);
        void onPostsLoadingFailed(Throwable t);

        void onUsersLoaded(List<User> users);
        void onUsersLoadingFailed(Throwable t);


    }


}
