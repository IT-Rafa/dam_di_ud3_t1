/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.itrafa.dam_di_ud3_t1;

import java.awt.Color;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimerTask;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;

/**
 *
 * @author it-ra
 */
@SupportedSourceVersion(SourceVersion.RELEASE_11)
public class AlarmClockBean extends JButton implements Serializable {

    private static final long serialVersionUID = 1L;

    // ATTRIBUTES
    private boolean format24h;
    private boolean alarmActivated;
    private String alarmMsg;
    private int alarmHour;
    private int alarmMinutes;

    // CONTRUCTOR
    public AlarmClockBean() {
        super("00:00:00");
        initComponent();

    }

    // GETTER & SETTERS
    public boolean isFormat24h() {
        return format24h;
    }

    public void setFormat24h(boolean format24h) {
        this.format24h = format24h;
    }

    public boolean isAlarmActivated() {
        return alarmActivated;
    }

    public void setAlarmActivated(boolean alarmActivated) {
        this.alarmActivated = alarmActivated;
    }

    public String getAlarmMsg() {
        return alarmMsg;
    }

    public void setAlarmMsg(String alarmMsg) {
        this.alarmMsg = alarmMsg;
    }

    public int getAlarmHour() {
        return alarmHour;
    }

    public void setAlarmHour(int alarmHour) {
        this.alarmHour = alarmHour;
    }

    public int getAlarmMinutes() {
        return alarmMinutes;
    }

    public void setAlarmMinutes(int alarmMinutes) {
        this.alarmMinutes = alarmMinutes;
    }

    private void initComponent() {
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

        this.addPropertyChangeListener("alarmActivated", new PropertyChangeListener() { // NOI18N
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                System.out.println("hola");

            }
        });
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
