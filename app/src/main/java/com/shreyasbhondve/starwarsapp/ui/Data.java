package com.shreyasbhondve.starwarsapp.ui;

import android.os.Parcel;
import android.os.Parcelable;

public class Data implements Parcelable{
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    String name;
    String height;
    String mass;
    String created;

    public Data(String name, String height, String mass, String created) {
        this.name = name;
        this.height = height;
        this.mass = mass;
        this.created = created;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Data(Parcel in){
        this.name = in.readString();
        this.height = in.readString();
        this.mass = in.readString();
        this.created = in.readString();
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(height);
        dest.writeString(mass);
        dest.writeString(created);
    }
}

