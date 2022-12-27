package com.ensaj.pharmacielast.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Ville implements Parcelable {

    private int id;

    private String nom;

    private List<Zone> zones;

    public Ville() {
    }

    protected Ville(Parcel in) {
        id = in.readInt();
        nom = in.readString();
    }

    public static final Creator<Ville> CREATOR = new Creator<Ville>() {
        @Override
        public Ville createFromParcel(Parcel in) {
            return new Ville(in);
        }

        @Override
        public Ville[] newArray(int size) {
            return new Ville[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Zone> getZones() {
        return zones;
    }

    public void setZones(List<Zone> zones) {
        this.zones = zones;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(nom);
    }
}
