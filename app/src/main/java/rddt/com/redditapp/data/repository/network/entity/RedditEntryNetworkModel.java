package rddt.com.redditapp.data.repository.network.entity;

import com.google.gson.annotations.SerializedName;

import io.reactivex.annotations.Nullable;

public class RedditEntryNetworkModel {

    @Nullable
    @SerializedName("id")
    private String id;

    @Nullable
    @SerializedName("author")
    private String author;

    @Nullable
    @SerializedName("thumbnail")
    private String thumbnail;

    @Nullable
    @SerializedName("created")
    private int created;

    @Nullable
    @SerializedName("url")
    private String url;

    @Nullable
    @SerializedName("title")
    private String title;

    @Nullable
    @SerializedName("created_utc")
    private int createdUtc;

    @Nullable
    @SerializedName("num_comments")
    private int numComments;

    @Nullable
    public String getId() {
        return id;
    }

    @Nullable
    public String getAuthor() {
        return author;
    }

    @Nullable
    public String getThumbnail() {
        return thumbnail;
    }

    @Nullable
    public int getCreated() {
        return created;
    }

    @Nullable
    public String getUrl() {
        return url;
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    @Nullable
    public int getCreatedUtc() {
        return createdUtc;
    }

    @Nullable
    public int getNumComments() {
        return numComments;
    }
}
