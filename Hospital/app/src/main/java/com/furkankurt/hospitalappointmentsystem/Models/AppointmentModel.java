package com.furkankurt.hospitalappointmentsystem.Models;

public class AppointmentModel  {
    public String UnwellTc;
    public String DocNameSurname;
    public String Appointment_Hour;


    public AppointmentModel(String unwellTc, String docNameSurname, String appointment_Hour) {
        UnwellTc = unwellTc;
        DocNameSurname = docNameSurname;
        Appointment_Hour = appointment_Hour;
    }
}
