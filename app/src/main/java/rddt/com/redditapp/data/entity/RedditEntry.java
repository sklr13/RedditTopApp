package rddt.com.redditapp.data.entity;

import android.support.annotation.Nullable;

public class RedditEntry {

    @Nullable
    private String id;

    @Nullable
    private String author;

    @Nullable
    private String thumbnail;

    @Nullable
    private int created;

    @Nullable
    private String url;

    @Nullable
    private String title;

    @Nullable
    private int createdUtc;

    @Nullable
    private int numComments;

    @Nullable
    public String getId() {
        return id;
    }

    public void setId(@Nullable String id) {
        this.id = id;
    }

    @Nullable
    public String getAuthor() {
        return author;
    }

    public void setAuthor(@Nullable String author) {
        this.author = author;
    }

    @Nullable
    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(@Nullable String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Nullable
    public int getCreated() {
        return created;
    }

    public void setCreated(@Nullable int created) {
        this.created = created;
    }

    @Nullable
    public String getUrl() {
        return url;
    }

    public void setUrl(@Nullable String url) {
        this.url = url;
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    public void setTitle(@Nullable String title) {
        this.title = title;
    }

    @Nullable
    public int getCreatedUtc() {
        return createdUtc;
    }

    public void setCreatedUtc(@Nullable int createdUtc) {
        this.createdUtc = createdUtc;
    }

    @Nullable
    public int getNumComments() {
        return numComments;
    }

    public void setNumComments(@Nullable int numComments) {
        this.numComments = numComments;
    }
}
