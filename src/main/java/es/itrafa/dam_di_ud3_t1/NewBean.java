/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Bean.java to edit this template
 */
package es.itrafa.dam_di_ud3_t1;

import java.awt.Color;
import java.awt.Dimension;
import java.beans.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimerTask;
import javax.swing.JLabel;
import javax.swing.LookAndFeel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

/**
 *
 * @author it-ra
 */
public class NewBean extends JLabel implements Serializable {

    // ATTRIBUTES
    private static final long serialVersionUID = 1L;
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

    // METHODS
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

        // Problema al añadir elemento
        try {
            propertySupport.addPropertyChangeListener(listener);
            

        } catch (Exception e) {
            System.out.println(" - problema al añadir desconocido: Listener");
            System.out.println(listener.toString());
        }
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
private class EditorValueChangeListener implements PropertyChangeListener {
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if ("value".equals(evt.getPropertyName())) {
                System.out.println("Old value:" + evt.getOldValue());
                System.out.println("New value:" + evt.getNewValue());
            }
        }
    }

    private void initValues() {
        // configuracion estética inicial del reloj
        setText("00:00:00");
        alarmMsg = "Aviso alarma";
        alarmActivated = false;
        alarmHour = 0;
        alarmMinutes = 0;
        format24h = false;

    }

    private void updaterTime() {
        java.util.Timer timer = new java.util.Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // Captura hora actual
                Calendar calendar = Calendar.getInstance();
                // Preparamos formato hora
                SimpleDateFormat timeFormat;
                if (format24h) {
                    timeFormat = new SimpleDateFormat("HH:mm:ss");

                } else {
                    timeFormat = new SimpleDateFormat("hh:mm:ss");
                }

                // Muestra hora con formato
                setText(timeFormat.format(calendar.getTime()));

            }
        };
        timer.scheduleAtFixedRate(task, 0, 500);

    }

    private void lookConfig() {
        // commmon
        LookAndFeel lookAndFeel = UIManager.getLookAndFeel();
        setForeground(new java.awt.Color(0, 102, 0));
        setFont(new java.awt.Font("Noto Mono", 1, 18)); // NOI18N
        setBorder(javax.swing.BorderFactory.createTitledBorder(
                javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED),
                " p.m."));
        setBorder(javax.swing.BorderFactory.createTitledBorder(
                javax.swing.BorderFactory.createEtchedBorder(
                        javax.swing.border.EtchedBorder.RAISED),
                " p.m.",
                javax.swing.border.TitledBorder.RIGHT,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Tahoma", 0, 11),
                new java.awt.Color(0, 102, 0))
        ); // NOI18N


        setHorizontalAlignment(SwingConstants.CENTER);
        
        switch (lookAndFeel.getName()) {

            case "Nimbus":
                nimbusFeel();
                break;
            case "Metal":
                metalFeel();
                break;
            default:
                System.out.println("LAF: Otro");
                break;
        }

    }

    private void feelConfig() {
        propertySupport = new PropertyChangeSupport(this);
        updaterTime();
    }

    // CONSTRUCTORS
    public NewBean() {

        // Valores iniciales
        initValues();
        // Aspecto inicial
        lookConfig();
        // Comportamiento
        feelConfig();

    }

    private void nimbusFeel() {
        setPreferredSize(new Dimension(115, 45));
    }

    private void metalFeel() {
        setPreferredSize(new Dimension(115, 45));
    }

}
