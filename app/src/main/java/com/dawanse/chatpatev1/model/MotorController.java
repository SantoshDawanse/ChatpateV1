package com.dawanse.chatpatev1.model;

public class MotorController {

    private String name;
    private String pwm;
    private String acw;
    private String time;

    public MotorController(String name, String pwm, String acw, String time) {
        this.name = name;
        this.pwm = pwm;
        this.acw = acw;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwm() {
        return pwm;
    }

    public void setPwm(String pwm) {
        this.pwm = pwm;
    }

    public String getAcw() {
        return acw;
    }

    public void setAcw(String acw) {
        this.acw = acw;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
