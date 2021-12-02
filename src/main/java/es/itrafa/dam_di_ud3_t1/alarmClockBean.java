/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.itrafa.dam_di_ud3_t1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author it-ra
 */
@SupportedSourceVersion(SourceVersion.RELEASE_11)
public class alarmClockBean extends JLabel implements Serializable {

    private static final long serialVersionUID = 1L;

    // ATTRIBUTES
    private boolean format24h;
    private boolean alarmActivated;
    private String alarmMsg;
    private int alarmHour;
    private int alarmMinutes;

    private Timer updateTime;

    // CONTRUCTOR
    public alarmClockBean() {
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
        alarmMsg = "Aviso alarma";
        setBackground(Color.darkGray);
        setForeground(Color.red);
        setFont(new java.awt.Font("Noto Mono", 1, 18)); // NOI18N
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, " PM ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), java.awt.Color.gray)); // NOI18N
        setPreferredSize(new Dimension(115, 45));
        setHorizontalAlignment(SwingConstants.CENTER);
        updaterTime();

    }

    private void updaterTime() {
        final String initHtml = "<html><div style='text-align: center;'>";
        final String endHtml = "</div></html>";
        

        java.util.Timer timer = new java.util.Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                SimpleDateFormat format;
                if (format24h) {
                    format = new SimpleDateFormat("HH:mm:ss");
                } else {
                    format = new SimpleDateFormat("hh:mm:ss");
                }
                Calendar calendar = Calendar.getInstance();
                String fullTimeTxt = String.format("%s%s%s", initHtml, format.format(calendar.getTime()), endHtml);
                setText(fullTimeTxt);
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);

    }

}
