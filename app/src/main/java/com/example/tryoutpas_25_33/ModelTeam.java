package com.example.tryoutpas_25_33;

public class ModelTeam {
    private String strTeam;
    private String strStadium;
    private String strBadge;
    public ModelTeam() {}

    public ModelTeam(String strTeam, String strStadium, String strBadge) {
        this.strTeam = strTeam;
        this.strStadium = strStadium;
        this.strBadge = strBadge;
    }

    public String getStrTeam() {
        return strTeam;
    }

    public String getStrBadge(){
        return strBadge;
    }

    public String getStrStadium() {
        return strStadium;
    }
}
