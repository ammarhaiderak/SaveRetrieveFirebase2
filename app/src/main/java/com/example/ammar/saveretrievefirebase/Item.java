package com.example.ammar.saveretrievefirebase;

public class Item {
    String itemname;
    String itemid;
    String sizel;
    String sizem;
    String sizes;
    String imageurl;

    public Item(){
    //
    }



    public Item(String itemname, String itemid, String sizel, String sizem, String sizes, String url) {
        this.itemname = itemname;
        this.itemid = itemid;
        this.sizel = sizel;
        this.sizem = sizem;
        this.sizes = sizes;
        this.imageurl=url;
    }
    public String getImageurl() {
        return imageurl;
    }

    public String getItemname() {
        return itemname;
    }

    public String getItemid() {
        return itemid;
    }

    public String getSizel() {
        return sizel;
    }

    public String getSizem() {
        return sizem;
    }

    public String getSizes() {
        return sizes;
    }

    @Override
    public String toString() {
        return this.itemid+"\n"+this.itemname+"\n"+this.sizel+"\n"+this.sizem+"\n"+this.sizes+"\n"+this.imageurl;
    }
}
