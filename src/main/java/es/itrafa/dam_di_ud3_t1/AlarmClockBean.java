/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Bean.java to edit this template
 */
package es.itrafa.dam_di_ud3_t1;

import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.beans.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimerTask;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LookAndFeel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

/**
 *
 * @author it-ra
 */
public class AlarmClockBean extends JLabel implements Serializable {

    // ATTRIBUTES
    private static final long serialVersionUID = 1L;

    private PropertyChangeSupport propertySupport;

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

    private Container parent;

    // GETTER & SETTER
    public String getAlarmMsg() {
        return alarmMsg;
    }

    public void setAlarmMsg(String value) {
        String oldValue = alarmMsg;
        alarmMsg = value;
        if (propertySupport != null) {
            propertySupport.firePropertyChange(ALARM_MSG_PROPERTY, oldValue, alarmMsg);
        }
    }

    public boolean isAlarmActivated() {
        return alarmActivated;
    }

    public void setAlarmActivated(boolean value) {
        boolean oldValue = alarmActivated;
        this.alarmActivated = value;
        if (propertySupport != null) {
            propertySupport.firePropertyChange(ALARM_ACTIVATED_PROPERTY, oldValue, alarmActivated);
        }
    }

    public int getAlarmHour() {
        return alarmHour;
    }

    public void setAlarmHour(int value) {
        int oldValue = this.alarmHour;
        this.alarmHour = value;
        if (propertySupport != null) {
            propertySupport.firePropertyChange(ALARM_HOUR_PROPERTY, oldValue, this.alarmHour);
        }
    }

    public int getAlarmMinutes() {
        return alarmMinutes;
    }

    public void setAlarmMinutes(int value) {
        int oldValue = this.alarmMinutes;
        this.alarmMinutes = value;
        if (propertySupport != null) {
            propertySupport.firePropertyChange(ALARM_MINUTES_PROPERTY, oldValue, this.alarmMinutes);
        }
    }

    public boolean isFormat24h() {
        return format24h;
    }

    public void setFormat24h(boolean value) {
        boolean oldValue = format24h;
        this.format24h = value;
        if (propertySupport != null) {
            propertySupport.firePropertyChange(FORMAT24H_PROPERTY, oldValue, this.format24h);
        }

    }

    // METHODS
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        if (propertySupport == null) {
            propertySupport = new PropertyChangeSupport(this);
        }
        propertySupport.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        if (propertySupport != null) {
            propertySupport.removePropertyChangeListener(listener);
        }
    }

    private void doubleClickToChangeAlarm() {
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    System.out.println("doble click");
                    if (isFormat24h()) {
                        setFormat24h(false);
                    } else {
                        setFormat24h(true);
                    }
                }
            }
        });

        addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // Invoking later for no reason, just to simulate your code
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        dispatchEvent(new MouseEvent(
                                null,
                                MouseEvent.MOUSE_CLICKED,
                                1,
                                MouseEvent.BUTTON1,
                                0, 0,
                                2,
                                false
                        ));
                    }
                });
            }
        });
    }

    private class ClockPropertiesChangeListener implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if (null != evt.getPropertyName()) {
                switch (evt.getPropertyName()) {
                    case ALARM_ACTIVATED_PROPERTY: {
                        TitledBorder alarmTitle = (TitledBorder) getBorder();
                        if (isAlarmActivated()) {
                            System.out.println("alarma activada");
                            alarmTitle.setTitle(" Alarm on ");
                        } else {
                            System.out.println("alarma desactivada");
                            alarmTitle.setTitle("");

                        }
                        break;
                    }
                    case FORMAT24H_PROPERTY: {
                        TitledBorder alarmTitle = (TitledBorder) getBorder();
                        TitledBorder formatTitle = (TitledBorder) alarmTitle.getBorder();
                        Calendar calendar = Calendar.getInstance();
                        if (isFormat24h()) {
                            formatTitle.setTitle(" 24h ");
                        } else {
                            if (calendar.get(Calendar.AM_PM) == Calendar.AM) {
                                formatTitle.setTitle(" a.m. ");
                            } else {
                                formatTitle.setTitle(" p.m. ");
                            }

                        }
                        break;
                    }
                    default:
                        break;
                }
            }
        }
    }

    private void initValues() {

        // inicializamos propiedades
        setText("00:00:00");
        setAlarmMsg("Aviso alarma");
        setAlarmActivated(false);
        setAlarmHour(3);
        setAlarmMinutes(38);
        setFormat24h(true);

        parent = getParent();
    }

    private void runEveryT(long ms) {
        java.util.Timer timer = new java.util.Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Calendar now = Calendar.getInstance();
                String alarmTime = String.format("%02d:%02d", getAlarmHour(), getAlarmMinutes());
                String nowTime;
                // Preparamos formato hora
                SimpleDateFormat timeFormat;
                SimpleDateFormat alarmFormat;
                if (isFormat24h()) {
                    timeFormat = new SimpleDateFormat("HH:mm:ss");
                    alarmFormat = new SimpleDateFormat("HH:mm");
                } else {
                    timeFormat = new SimpleDateFormat("hh:mm");
                    alarmFormat = new SimpleDateFormat("hh:mm");
                }

                // Muestra hora con formato
                setText(timeFormat.format(now.getTime()));

                nowTime = alarmFormat.format(now.getTime());
                System.out.println(alarmTime + "=" + nowTime);

                if (alarmTime.equals(nowTime) && isAlarmActivated()) {
                    JOptionPane.showMessageDialog(
                            parent,
                            getAlarmMsg());
                    setAlarmActivated(false);
                }

            }

        };
        timer.scheduleAtFixedRate(task, 0, ms);

    }

    private void lookConfig() {
        // commmon

        LookAndFeel lookAndFeel = UIManager.getLookAndFeel();
        setForeground(new java.awt.Color(0, 102, 0));
        setFont(new java.awt.Font("Noto Mono", 1, 18)); // NOI18N

        setBorder(javax.swing.BorderFactory.createTitledBorder(
                javax.swing.BorderFactory.createTitledBorder(
                        // marca formato: 24h por defecto
                        null, " 24h ",
                        javax.swing.border.TitledBorder.RIGHT,
                        javax.swing.border.TitledBorder.DEFAULT_POSITION,
                        new java.awt.Font("Tahoma", 0, 11),
                        new java.awt.Color(0, 102, 0)
                ),
                // marca alarma activada: false por defecto
                "", javax.swing.border.TitledBorder.LEFT,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Tahoma", 0, 11),
                new java.awt.Color(0, 102, 0))); // NOI18N

        setHorizontalAlignment(SwingConstants.CENTER);

        switch (lookAndFeel.getName()) {

            case "Nimbus":
                nimbusFeel();
                break;
            case "Metal":
                metalFeel();
                break;
            default:
                break;
        }

    }

    private void feelConfig() {
        addPropertyChangeListener(new ClockPropertiesChangeListener());
        runEveryT(300);
        doubleClickToChangeAlarm();
    }

    // CONSTRUCTORS
    public AlarmClockBean() {
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
