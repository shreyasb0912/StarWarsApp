package com.shreyasbhondve.starwarsapp.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class APIResponse {
    @SerializedName("results")
    public List<StarWarCharacter> results = null;

    public static class StarWarCharacter{

        @SerializedName("name")
        public String name;

        @SerializedName("height")
        public String height;

        @SerializedName("mass")
        public String mass;

        @SerializedName("hair_color")
        public String hair_color;

        @SerializedName("skin_color")
        public String skin_color;

        @SerializedName("eye_color")
        public String eye_color;

        @SerializedName("birth_year")
        public String birth_year;

        @SerializedName("gender")
        public String gender;

        @SerializedName("homeworld")
        public String homeworld;

        @SerializedName("created")
        public String created;

        @SerializedName("edited")
        public String edited;

        @SerializedName("url")
        public String url;

        @SerializedName("films")
        public String[] films;

        @SerializedName("vehicles")
        public String[] vehicles;

        @SerializedName("starships")
        public String[] starships;

        public StarWarCharacter(String name, String height, String mass, String created) {
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

        public String getHair_color() {
            return hair_color;
        }

        public void setHair_color(String hair_color) {
            this.hair_color = hair_color;
        }

        public String getSkin_color() {
            return skin_color;
        }

        public void setSkin_color(String skin_color) {
            this.skin_color = skin_color;
        }

        public String getEye_color() {
            return eye_color;
        }

        public void setEye_color(String eye_color) {
            this.eye_color = eye_color;
        }

        public String getBirth_year() {
            return birth_year;
        }

        public void setBirth_year(String birth_year) {
            this.birth_year = birth_year;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getHomeworld() {
            return homeworld;
        }

        public void setHomeworld(String homeworld) {
            this.homeworld = homeworld;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getEdited() {
            return edited;
        }

        public void setEdited(String edited) {
            this.edited = edited;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String[] getFilms() {
            return films;
        }

        public void setFilms(String[] films) {
            this.films = films;
        }

        public String[] getVehicles() {
            return vehicles;
        }

        public void setVehicles(String[] vehicles) {
            this.vehicles = vehicles;
        }

        public String[] getStarships() {
            return starships;
        }

        public void setStarships(String[] starships) {
            this.starships = starships;
        }

    }
}
