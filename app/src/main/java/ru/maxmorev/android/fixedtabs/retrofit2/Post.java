package ru.maxmorev.android.fixedtabs.retrofit2;

import com.google.gson.annotations.SerializedName;
/**
 *  POJO класс для маппирования JSON объекта в Java-сущность.
 */
public class Post {

    @SerializedName("userId")
    final long userId;

    @SerializedName("id")
    final long id;

    @SerializedName("title")
    final String title;

    @SerializedName("body")
    final String body;

    public Post(final long userId, final long id, final String title, final String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public long getUserId() {
        return userId;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
