package com.twx.page.Model;

public class Guanli {

    private String gname;
    private String gpass;

    public Guanli(String gname, String gpass) {
        this.gname = gname;
        this.gpass = gpass;
    }

    public Guanli() {
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getGpass() {
        return gpass;
    }

    public void setGpass(String gpass) {
        this.gpass = gpass;
    }
}
