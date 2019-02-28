package com.rayt.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class ShiftModel implements Parcelable {

    private String name;
    private String description;

    public ShiftModel(String name, String description) {
        this.name = name;
        this.description = description;
    }

    protected ShiftModel(Parcel in) {
        name = in.readString();
        description = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
    }

    public static final Creator<ShiftModel> CREATOR = new Creator<ShiftModel>() {
        @Override
        public ShiftModel createFromParcel(Parcel in) {
            return new ShiftModel(in);
        }

        @Override
        public ShiftModel[] newArray(int size) {
            return new ShiftModel[size];
        }
    };

}
