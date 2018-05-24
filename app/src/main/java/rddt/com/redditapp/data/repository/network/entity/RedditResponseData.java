package rddt.com.redditapp.data.repository.network.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RedditResponseData {
    @SerializedName("modhash")
    private String modhash;
    @SerializedName("children")
    private List<RedditResponse<RedditEntryNetworkModel>> children;
    @SerializedName("after")
    private String after;
    @SerializedName("before")
    private Object before;

    public String getModhash() {
        return modhash;
    }

    public void setModhash(String modhash) {
        this.modhash = modhash;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }

    public Object getBefore() {
        return before;
    }

    public void setBefore(Object before) {
        this.before = before;
    }

    public List<RedditResponse<RedditEntryNetworkModel>> getChildren() {
        return children;
    }

    public void setChildren(List<RedditResponse<RedditEntryNetworkModel>> children) {
        this.children = children;
    }
}
