/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.itrafa.dam_di_ud3_t1;

/**
 *
 * @author it-ra
 */
public class AlarmData {
    private boolean alarmActivated;
    private int hour;
    private int minutes;

    public boolean isAlarmActivated() {
        return alarmActivated;
    }

    public void setAlarmActivated(boolean alarmActivated) {
        this.alarmActivated = alarmActivated;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
    
}
