/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Bean.java to edit this template
 */
package es.itrafa.dam_di_ud3_t1;

import java.beans.*;
import java.io.Serializable;
import javax.swing.JLabel;

/**
 *
 * @author it-ra
 */
public class NewBean extends JLabel implements Serializable {
    // ATTRIBUTES
    // PropertyChange descriptions??
    public static final String ALARM_MSG_PROPERTY = "alarmMsg";
    public static final String ALARM_ACTIVATED_PROPERTY = "alarmActivated";
    public static final String ALARM_HOUR_PROPERTY = "alarmHour";
    public static final String ALARM_MINUTES_PROPERTY = "alarmMinutes";
    public static final String FORMAT24H_PROPERTY = "format24h";
    // real attributes
    private String alarmMsg;
    private boolean alarmActivated;
    private int alarmHour;
    private int alarmMinutes;
    private boolean format24h;

    // for PropertyChange
    private PropertyChangeSupport propertySupport;


    // GETTER & SETTER
    public String getAlarmMsg() {
        return alarmMsg;
    }

    public void setAlarmMsg(String value) {
        String oldValue = alarmMsg;
        alarmMsg = value;
        propertySupport.firePropertyChange(ALARM_MSG_PROPERTY, oldValue, alarmMsg);
    }

    public boolean isAlarmActivated() {
        return alarmActivated;
    }

    public void setAlarmActivated(boolean value) {
        boolean oldValue = alarmActivated;
        this.alarmActivated = value;
        propertySupport.firePropertyChange(ALARM_ACTIVATED_PROPERTY, oldValue, alarmActivated);
    }

    public int getAlarmHour() {
        return alarmHour;
    }

    public void setAlarmHour(int value) {
        int oldValue = this.alarmHour;
        this.alarmHour = value;
        propertySupport.firePropertyChange(ALARM_HOUR_PROPERTY, oldValue, this.alarmHour);
    }

    public int getAlarmMinutes() {
        return alarmMinutes;
    }

    public void setAlarmMinutes(int value) {
        int oldValue = this.alarmMinutes;
        this.alarmMinutes = value;
        propertySupport.firePropertyChange(ALARM_MINUTES_PROPERTY, oldValue, this.alarmMinutes);
    }

    public boolean isFormat24h() {
        return format24h;
    }

    public void setFormat24h(boolean value) {
        boolean oldValue = alarmActivated;
        this.format24h = value;
        propertySupport.firePropertyChange(FORMAT24H_PROPERTY, oldValue, this.format24h);
    }
    
    // CONSTRUCTORS
    public NewBean() {
        propertySupport = new PropertyChangeSupport(this);
    }
    
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }

    public PropertyChangeSupport getPropertySupport() {
        return propertySupport;
    }

    public void setPropertySupport(PropertyChangeSupport propertySupport) {
        this.propertySupport = propertySupport;
    }

}
