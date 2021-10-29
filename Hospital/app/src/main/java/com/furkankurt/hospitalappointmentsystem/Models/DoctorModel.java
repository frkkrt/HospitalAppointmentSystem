package com.furkankurt.hospitalappointmentsystem.Models;

import java.io.Serializable;

    public class DoctorModel implements Serializable {
        public String DocAd;
        public String DocSoyad;
        public String Doc_Unvan;
        public String Doc_Yas;
        public String DocTc;
        public String DocSifre;
        public String DocSecim;


        public DoctorModel(String docAd, String docSoyad, String doc_Unvan, String doc_Yas, String docTc, String docSifre, String docSecim) {
            DocAd = docAd;
            DocSoyad = docSoyad;
            Doc_Unvan = doc_Unvan;
            Doc_Yas = doc_Yas;
            DocTc = docTc;
            DocSifre = docSifre;
            DocSecim = docSecim;
        }
    }
