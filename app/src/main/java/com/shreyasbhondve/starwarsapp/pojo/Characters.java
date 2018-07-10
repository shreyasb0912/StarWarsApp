package com.shreyasbhondve.starwarsapp.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Characters {
    @SerializedName("results")
    public List<StarWarCharacter> results = null;

    public class StarWarCharacter{

    }
}
