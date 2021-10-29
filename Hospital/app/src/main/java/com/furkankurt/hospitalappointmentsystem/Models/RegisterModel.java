package com.furkankurt.hospitalappointmentsystem.Models;

import java.io.Serializable;

public class RegisterModel implements Serializable {
    public String Ad;
    public String Soyad;
    public String Tc;
    public String Sifre;
    public String Secim;


    public RegisterModel(String ad, String soyad, String tc, String sifre, String secim) {
        Ad = ad;
        Soyad = soyad;
        Tc = tc;
        Sifre = sifre;
        Secim = secim;
    }
}
