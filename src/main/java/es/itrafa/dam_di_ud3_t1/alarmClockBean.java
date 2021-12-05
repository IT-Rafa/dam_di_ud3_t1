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
    /**
     * Retorna el mensaje que muestra la alarma al "saltar"
     *
     * @return Mensaje
     */
    public String getAlarmMsg() {
        return alarmMsg;
    }

    /**
     * Modifica el mensaje que muestra el reloj cuando salte la alarma
     *
     * @param value Mensaje a mostrar
     */
    public void setAlarmMsg(String value) {
        String oldValue = alarmMsg;
        alarmMsg = value;
        if (propertySupport != null) {
            propertySupport.firePropertyChange(ALARM_MSG_PROPERTY, oldValue, alarmMsg);
        }
    }

    /**
     * Indica si la alarma del reloj está activada. Es decir si va a saltar
     * cuando llegue la hora de la alarma
     *
     * @return true si está activada; false si no.
     */
    public boolean isAlarmActivated() {
        return alarmActivated;
    }

    /**
     * Activa o desactiva la alarma del reloj
     *
     * @param value true para activar; false para desactivar
     */
    public void setAlarmActivated(boolean value) {
        boolean oldValue = alarmActivated;
        this.alarmActivated = value;
        if (propertySupport != null) {
            propertySupport.firePropertyChange(ALARM_ACTIVATED_PROPERTY, oldValue, alarmActivated);
        }
    }

    /**
     * Devuelve la hora en formato 24h a la que se mostará el mensaje si la
     * alarma está activada
     *
     * @return Hora (sin minutos) al la que saltará la alarma
     */
    public int getAlarmHour() {
        return alarmHour;
    }

    /**
     * Modifica la hora en formato 24h a la que se mostará el mensaje si la
     * alarma está activada
     *
     * @param value Hora (sin minutos) al la que saltará la alarma
     */
    public void setAlarmHour(int value) {
        int oldValue = this.alarmHour;
        this.alarmHour = value;
        if (propertySupport != null) {
            propertySupport.firePropertyChange(ALARM_HOUR_PROPERTY, oldValue, this.alarmHour);
        }
    }

    /**
     * Devuelve los minutos (dentro de la hora) en los que se mostará el mensaje
     * si la alarma está activada
     *
     * @return Minuto (dentro de hora) al la que saltará la alarma
     */
    public int getAlarmMinutes() {
        return alarmMinutes;
    }

    /**
     * Modifica los minutos (dentro de la hora) en los que se mostará el mensaje
     * si la alarma está activada
     *
     * @param value Minuto (dentro de hora) al la que saltará la alarma
     */
    public void setAlarmMinutes(int value) {
        int oldValue = this.alarmMinutes;
        this.alarmMinutes = value;
        if (propertySupport != null) {
            propertySupport.firePropertyChange(ALARM_MINUTES_PROPERTY, oldValue, this.alarmMinutes);
        }
    }

    /**
     * Indica si la hora está en formato 24h. Si no lo está, será formato am/pm
     *
     * @return true si el formato es 24; false si el formato es am/pm
     */
    public boolean isFormat24h() {
        return format24h;
    }

    /**
     * Cambia el formato del reloj
     *
     * @param value true si se quiere formato de 24h. false para am/pm
     */
    public void setFormat24h(boolean value) {
        boolean oldValue = format24h;
        this.format24h = value;
        if (propertySupport != null) {
            propertySupport.firePropertyChange(FORMAT24H_PROPERTY, oldValue, this.format24h);
        }

    }

    // CONSTRUCTORS
    /**
     * Crea objeto AlarmClockBean con valores por defecto
     */
    public AlarmClockBean() {
        // Valores propiedades
        initValues();
        // Configuración gráfica
        lookConfig();
        // Eventos
        feelConfig();

    }

// ASSIGN VALUES AND EVENTS
    /**
     * Inicializa los valores de las propiedades de AlarmClockBean
     */
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

    /**
     * Define aspecto del reloj
     *
     * @nota Use diseñador netbeans con JLabel para diseño inicial y copie
     * código (sin nombre de variable)
     *
     * @nota usa un TitleBorder dentro de otro para mostrar el formato y si la
     * alarma está activada
     */
    private void lookConfig() {
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
        setPreferredSize(new Dimension(115, 45));

        // Por si el aspecto según lookAndFeel precisa ser ajustado
        // En su momento hizo falta, pero luego no, no se porque, pero lo dejo
        // por ser práctico
        LookAndFeel lookAndFeel = UIManager.getLookAndFeel();
        switch (lookAndFeel.getName()) {

            case "Nimbus":
                break;
            case "Metal":
                break;
            default:
                break;
        }

    }

    // EVENTOS
    /**
     * Añade los Listener para los eventos necesarios
     *
     */
    private void feelConfig() {
        // eventos cambios propiedades
        addPropertyChangeListener(new ClockPropertiesChangeListener());
        // evento actualizar y alamrma
        runEveryT(300);
        // doble click para activar alarma (Pendiente PropertyEditor para modificarla)
        doubleClickEvent();
    }

    // PROPERTY SUPPORT AND EVENTS
    /**
     * Añade los listeners para las propiedades. Creado al elegir como proyecto
     * new Bean
     *
     * @param listener listener a añadir
     *
     * @nota El original causo muchos problemas porque proppertySupport erá null
     * al principio y lo modifique para evitarlo
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        if (propertySupport == null) {
            propertySupport = new PropertyChangeSupport(this);
        }
        propertySupport.addPropertyChangeListener(listener);
    }

    /**
     * Elimina los listeners para las propiedades. Creado al elegir como
     * proyecto new Bean
     *
     * @param listener listener a eliminar
     */
    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        if (propertySupport != null) {
            propertySupport.removePropertyChangeListener(listener);
        }
    }

    /**
     * Listener para todas los cambios en propiedades. Funciona en editor diseño
     * IDE aunque no ejecutes Incluye manejadores según propiedad
     */
    private class ClockPropertiesChangeListener implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if (null != evt.getPropertyName()) {

                switch (evt.getPropertyName()) {
                    // Manejador alarma activada
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
                    // Manejador formato hora
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

    /**
     * Detecta el doble click y lo maneja
     *
     * @Pendiente probar en container
     */
    private void doubleClickEvent() {
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    doubleClickHandler();
                }
            }

            private void doubleClickHandler() {
                System.out.println("doble click en reloj");
                if(isAlarmActivated()){
                    setAlarmActivated(false);
                }else{
                    setAlarmActivated(true);
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

    /**
     * Crea evento que ocurrirá cada ms Milisegundos y lo ejecuta Incluye
     * instrucciones para manejar evento
     *
     * @param ms
     */
    private void runEveryT(long ms) {
        java.util.Timer timer = new java.util.Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // Actualiza hora actual según formato
                Calendar now = Calendar.getInstance();
                String nowTime;

                SimpleDateFormat timeFormat; // para mostrar 
                SimpleDateFormat alarmFormat; // para comparar con alarma
                if (isFormat24h()) {
                    timeFormat = new SimpleDateFormat("HH:mm:ss");
                    alarmFormat = new SimpleDateFormat("HH:mm");
                } else {
                    timeFormat = new SimpleDateFormat("hh:mm");
                    alarmFormat = new SimpleDateFormat("hh:mm");
                }

                setText(timeFormat.format(now.getTime()));

                //Muestra mensaje alarma si está activada y hora y minutos coinciden
                // con la actual
                String alarmTime = String.format("%02d:%02d", getAlarmHour(), getAlarmMinutes());
                nowTime = alarmFormat.format(now.getTime());

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

}
