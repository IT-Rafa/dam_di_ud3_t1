/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.itrafa.dam_di_ud3_t1_2;

import java.io.Serializable;
import javax.swing.JLabel;

/**
 *
 * @author it-ra
 */
public class AlarmClockBean extends JLabel implements Serializable {
    // ATTRIBUTES
    private boolean format24h;
    private boolean alarmActived;
    private int hourAlarm;
    private int minutesAlarm;
    private String alarmMsg;

    // CONSTRUCTOR
    AlarmClockBean(){

    }
    
    // GETTER & SETTERS
    public boolean isFormat24h() {
        return format24h;
    }

    public void setFormat24h(boolean format24h) {
        this.format24h = format24h;
    }

    public boolean isAlarmActived() {
        return alarmActived;
    }

    public void setAlarmActived(boolean alarmActived) {
        this.alarmActived = alarmActived;
    }

    public int getHourAlarm() {
        return hourAlarm;
    }

    public void setHourAlarm(int hourAlarm) {
        this.hourAlarm = hourAlarm;
    }

    public int getMinutesAlarm() {
        return minutesAlarm;
    }

    public void setMinutesAlarm(int minutesAlarm) {
        this.minutesAlarm = minutesAlarm;
    }

    public String getAlarmMsg() {
        return alarmMsg;
    }

    public void setAlarmMsg(String alarmMsg) {
        this.alarmMsg = alarmMsg;
    }

}
