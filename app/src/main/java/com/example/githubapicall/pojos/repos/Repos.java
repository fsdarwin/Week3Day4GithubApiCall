
package com.example.githubapicall.pojos.repos;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Repos implements Parcelable
{

    @SerializedName("repos")
    @Expose
    private List<Repo> repos = null;
    public final static Creator<Repos> CREATOR = new Creator<Repos>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Repos createFromParcel(Parcel in) {
            return new Repos(in);
        }

        public Repos[] newArray(int size) {
            return (new Repos[size]);
        }

    }
    ;

    protected Repos(Parcel in) {
        in.readList(this.repos, (com.example.githubapicall.pojos.repos.Repo.class.getClassLoader()));
    }

    public Repos() {
    }

    public List<Repo> getRepos() {
        return repos;
    }

    public void setRepos(List<Repo> repos) {
        this.repos = repos;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(repos);
    }

    public int describeContents() {
        return  0;
    }

}
