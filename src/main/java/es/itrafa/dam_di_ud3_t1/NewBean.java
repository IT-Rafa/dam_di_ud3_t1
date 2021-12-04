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
import javax.swing.SwingConstants;
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

    // CONSTRUCTORS
    public NewBean() {
        propertySupport = new PropertyChangeSupport(this);
        myInitComponents();
        
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

    private void myInitComponents() {
        // configuracion estética inicial del reloj
        alarmMsg = "Aviso alarma";
        setBackground(Color.darkGray);
        setForeground(Color.red);
        setFont(new java.awt.Font("Noto Mono", 1, 18)); // NOI18N
        setBorder(javax.swing.BorderFactory.createTitledBorder(
                javax.swing.BorderFactory.createTitledBorder(
                        null, " set alarm ",
                        javax.swing.border.TitledBorder.CENTER,
                        javax.swing.border.TitledBorder.BOTTOM
                ),
                " alarm & format ",
                javax.swing.border.TitledBorder.LEFT,
                javax.swing.border.TitledBorder.DEFAULT_POSITION)
        );

        setPreferredSize(new Dimension(115, 45));
        setHorizontalAlignment(SwingConstants.CENTER);

        // Asigna y activa listener para actualizar la hora
        updaterTime();
    }

    private void updaterTime() {
        final String initHtml = "<html><div style='text-align: center;'>";
        final String endHtml = "</div></html>";

        TitledBorder tb = (TitledBorder) getBorder();

        java.util.Timer timer = new java.util.Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // Captura hora actual
                Calendar calendar = Calendar.getInstance();
                // Preparamos formato hora
                SimpleDateFormat format;
                String timeFormat;
                if (format24h) {
                    timeFormat = " 24 h"; // pendiente: pasar a listener de cambio propiedad de format24h
                    format = new SimpleDateFormat("HH:mm:ss");

                } else {
                    // // if pendiente: pasar a listener de cambio propiedad de format24h
                    if (calendar.get(Calendar.AM_PM) == Calendar.AM) {
                        timeFormat = " a.m.";
                    } else {
                        timeFormat = " p.m.";
                    }

                    format = new SimpleDateFormat("hh:mm:ss");
                }
                // Muestra hora con formato
                String fullTimeTxt = String.format("%s%s%s", initHtml, format.format(calendar.getTime()), endHtml);
                setText(fullTimeTxt);

                // esto debe ir en listener de cambio propiedad de alarmActivated
                if (alarmActivated) {
                    tb.setTitle(" alarm - " + timeFormat);

                } else {
                    tb.setTitle(timeFormat);
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 500);

    }

}
