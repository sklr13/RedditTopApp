package rddt.com.redditapp.data.repository.network.entity;

import com.google.gson.annotations.SerializedName;

public class RedditResponse<T> {

    @SerializedName("kind")
    private String kind;
    @SerializedName("data")
    private T data;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
