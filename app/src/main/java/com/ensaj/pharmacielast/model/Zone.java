package com.ensaj.pharmacielast.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Zone implements Parcelable {

    private int id;

    private String nom;

    //private List<Pharmacie> pharmacies;

    private Ville ville;

    public Zone() {
    }

    protected Zone(Parcel in) {
        id = in.readInt();
        nom = in.readString();
        ville = in.readParcelable(Ville.class.getClassLoader());
    }

    public static final Creator<Zone> CREATOR = new Creator<Zone>() {
        @Override
        public Zone createFromParcel(Parcel in) {
            return new Zone(in);
        }

        @Override
        public Zone[] newArray(int size) {
            return new Zone[size];
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

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(nom);
        parcel.writeParcelable(ville, i);
    }
}
