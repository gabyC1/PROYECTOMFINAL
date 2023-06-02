package proyectomundial;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.stream.Collectors;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class GUIManual extends JFrame {
    
    private int totalSeleccionesCargadas = 0;
    private int totalPartidosCargados = 0;
    private String promedioDeGoles ;
    private int numeroGanadores = 0;
    private int numeroEmpates = 0;
    public String[][] totalSeleccionesPorContinente = new String [7][2];
    public String [][] clasificadosPorGrupo = new String [16][3];
    private Map<String, Integer> nacionalidades = new LinkedHashMap<>();
    private Map<String, Integer> golesPorSeleccion = new LinkedHashMap<>();
    private Map<String, Integer> golesPorContinente = new LinkedHashMap<>();
    private Map<String, Integer> puntosPorSeleccion = new LinkedHashMap<>();
    
    public String[][] matrixAuditoria = new String [6][2];
    
    ArrayList<Partido> partidos = new ArrayList<Partido>();
    

    // Matrix que permite almancenar la información de las selecciones futbol cargadas
    public String[][] selecciones = null;
    
    // Matrix que permite almancenar la información de las selecciones futbol cargadas
    public String[][] seleccionesFiltro = null;
    
    // Matriz que permite almacenar los resultados de los partidos cargardos
    public String[][] resultados = null;
    
    // Matrix que permite almancenar la información de los resultados cargadas
    public String[][] resultadosFiltro = null;
    
    // Elementos de bara Lateral
    private JPanel jPanelLeft;
    
    private JPanel jPanelIconFIFA;
    private JLabel iconFIFA;
    
    // Elementos de opciones de Menú
    private JPanel jPanelMenu;
    
    private JPanel jPanelMenuHome;
    private JLabel btnHome;
    
    private JPanel jPanelMenuSelecciones;
    private JLabel btnSelecciones;
    
    private JPanel jPanelMenuResultados;
    private JLabel btnResultados;
    
    private JPanel jPanelMenuDashboardSel;
    private JLabel btnDashboardSel;
    
    private JPanel jPanelMenuDashboardRes;
    private JLabel btnDashboardRes;
    
    private JPanel jPanelvistas;
    private JLabel btnvistas;
        
    // Elementos de panel de contenido
    private JPanel jPanelRight;
    private JPanel jPanelLabelTop;
    private JLabel jLabelTop;
    
    private JPanel jPanelMain;
    
    
    public GUIManual() {
        
        // Se inician los componentes gráficos
        initComponents();
        
        // Se configuran propiedades de nuestra Ventana
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        
        // Se llama la función home para que al momento de iniciar la aplicacoón, por defecto se muestre el home
        accionHome();
        
    }
    
    
    private void initComponents() {
        
        matrixAuditoria[0][0] = "Opciones Menu";
        matrixAuditoria[1][0] = "Home";
        matrixAuditoria[2][0] = "Selecciones";
        matrixAuditoria[3][0] = "Resultados";
        matrixAuditoria[4][0] = "Dash Selecciones";
        matrixAuditoria[5][0] = "Dash Resultados";
        
        matrixAuditoria[0][1] = "Total Vistas";
        matrixAuditoria[1][1] = "0";
        matrixAuditoria[2][1] = "0";
        matrixAuditoria[3][1] = "0";
        matrixAuditoria[4][1] = "0";
        matrixAuditoria[5][1] = "0";

        // Inicializamos componentes del Menu Lateral
        jPanelLeft = new JPanel();
        
        jPanelIconFIFA = new JPanel();
        iconFIFA = new JLabel();
        jPanelMenu = new JPanel();
        
        jPanelMenuHome = new JPanel();
        btnHome = new JLabel();
        
        jPanelMenuSelecciones = new JPanel();
        btnSelecciones = new JLabel();
        
        jPanelMenuResultados = new JPanel();
        btnResultados = new JLabel();
        
        jPanelMenuDashboardSel = new JPanel();
        btnDashboardSel = new JLabel();
        
        jPanelMenuDashboardRes = new JPanel();
        btnDashboardRes = new JLabel();
        
        jPanelvistas = new JPanel();
        btnvistas = new JLabel();
        
        // Pinta el logo de la aplicación
        pintarLogo();
        
        // Pinta la opción de menú del Home
        pintarMenuHome();
        
        // Pinta la opción de Menú de las Selecciones
        pintarMenuSelecciones();
        
        // Pinta la opción de Menú de los resultados
        pintarMenuResultados();
        
        // Pinta la opción de Menú del dashboard de equipo
        pintarMenuDashboardSel();
        
        // Pinta la opción de Menú del dahboard de resultados
        pintarMenuDashboardRes();
        
        pintarMenuVistas();
        
        // Pinta y ajuste diseño del contenedor del panel izquierdo
        pintarPanelIzquierdo();
        
        
        
        
        
        // Inicializa los componentes del panel derecho de los contenidos
        jPanelRight = new JPanel();
        jPanelLabelTop = new JPanel();
        jPanelMain = new JPanel();
        
        // Pinta la barra superrior de color azul claro, del panel de contenido
        pintarLabelTop();
        
        // Pinta y ajusta diseño del contenedor de contenidos
        pintarPanelDerecho();
        
        setTitle("Mundial");
        pack();
        setVisible(true);
    }
    
    private void pintarLogo() {
        jPanelIconFIFA.add(iconFIFA);
        jPanelIconFIFA.setOpaque(false);
        jPanelIconFIFA.setPreferredSize((new java.awt.Dimension(220, 80)));
        jPanelIconFIFA.setMaximumSize(jPanelIconFIFA.getPreferredSize());
        iconFIFA.setIcon(new ImageIcon(getClass().getResource("/resources/fifa.png")));
        jPanelLeft.add(jPanelIconFIFA, BorderLayout.CENTER);
        
    }
    
    /**
     * Función que se encarga de ajustar los elementos gráficos que componente la opción de navegación del HOME
     * Define estilos, etiquetas, iconos que decoran la opción del Menú. 
     * Esta opción de Menu permite mostrar la página de bienvenida de la aplicación
     */
    private void pintarMenuHome() {
        btnHome.setIcon(new ImageIcon(getClass().getResource("/resources/icons/home.png"))); // NOI18N
        btnHome.setText("Home");
        btnHome.setForeground(new java.awt.Color(255, 255, 255));
        
        JLabel vacioHome = new JLabel();
        jPanelMenuHome.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuHome.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuHome.setLayout(new BorderLayout(15, 0));
        jPanelMenuHome.add(vacioHome, BorderLayout.WEST);
        jPanelMenuHome.add(btnHome, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuHome);
        
        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Home");
                accionHome();
            }
        });   
    }
    
    /**
     * Función que se ejecuta cuando el usuario hacer click sobre la opción de navegación Home
     * Permite modificar la etiqueta de Navegación en Home, remover los elementos que hay en 
     * el panel de contenidos y agregar la imagen de inicio de la aplicación
     */
    private void accionHome() {
        jLabelTop.setText("Home");
        //jLabelTopDescription.setText("Bievenido al sistema de gestión de mundiales de fútbol");

        jPanelMain.removeAll();
        JPanel homePanel = new JPanel();
        JLabel imageHome = new JLabel();
        

        imageHome.setIcon(new ImageIcon(getClass().getResource("/resources/home.jpg"))); // NOI18N
        homePanel.add(imageHome);

        jPanelMain.add(homePanel, BorderLayout.LINE_START);
        jPanelMain.repaint();
        jPanelMain.revalidate();
        
        int aux = Integer.parseInt(matrixAuditoria[1][1]);
        aux++;
        matrixAuditoria[1][1] = "" + aux;
    }
    
    /**
     * Función que se encarga de ajustar los elementos gráficos que componente la opción de navegación de SELECCIONES
     * Define estilos, etiquetas, iconos que decoran la opción del Menú. 
     * Esta opción de Menu permite mostrar las selecciones de futbol cargadas en la aplicación
     */
    private void pintarMenuSelecciones() {
        btnSelecciones.setIcon(new ImageIcon(getClass().getResource("/resources/icons/selecciones.png"))); // NOI18N
        btnSelecciones.setText("Selecciones");
        btnSelecciones.setForeground(new java.awt.Color(255, 255, 255));
        
        JLabel vacioSelecciones = new JLabel();
        jPanelMenuSelecciones.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuSelecciones.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuSelecciones.setLayout(new BorderLayout(15, 0));
        jPanelMenuSelecciones.add(vacioSelecciones, BorderLayout.WEST);
        jPanelMenuSelecciones.add(btnSelecciones, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuSelecciones);
        
        btnSelecciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Selecciones");
                accionSelecciones();
            }
        });
    }
    
    /**
     * Función que se ejecuta cuando el usuario hace click sobre la opción de navegación Selecciones
     * Permite ver la lista de selecciones que se encuentran cargadas en la aplicación. 
     * Si la lista de selecciones en vacía, muestra un botón que permite cargar un archivo CSV
     * con la información de las selelecciones
     */
    private void accionSelecciones() {
        jLabelTop.setText("Selecciones");

        // Si no hay selecciones cargadas, muestra el botón de carga de selecciones
        if (selecciones == null) {
            jPanelMain.removeAll();
            JPanel seleccionesPanel = new JPanel();

            if (selecciones == null) {

                JLabel notSelecciones = new JLabel();
                notSelecciones.setText("No hay selecciones cargadas, por favor cargue selecciones \n\n");
                seleccionesPanel.add(notSelecciones);

                JButton cargarFile = new JButton();
                cargarFile.setText("Seleccione el archivo");
                seleccionesPanel.add(cargarFile);
                this.cargarFileSelecciones();
                cargarFile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        cargarFileSelecciones();
                    }
                });
            }

            jPanelMain.add(seleccionesPanel);
            jPanelMain.repaint();
            jPanelMain.revalidate();
        }
        // Si hay selecciones cargadas, llama el método que permite pintar la tabla de selecciones
        else {
            pintarTablaSelecciones();
            
            int aux = Integer.parseInt(matrixAuditoria[1][1]);
        aux++;
        matrixAuditoria[2][1] = "" + aux;
        }
    }
    
    /**
     * Función que se encarga de ajustar los elementos gráficos que componente la opción de navegación de RESULTADOS
     * Define estilos, etiquetas, iconos que decoran la opción del Menú. 
     * Esta opción de Menu permite mostrar los diferentes resultados de los partidos de la fase de grupos de un mundial
     */
    private void pintarMenuResultados() {
        btnResultados.setIcon(new ImageIcon(getClass().getResource("/resources/icons/resultados.png"))); // NOI18N
        btnResultados.setText("Resultados");
        btnResultados.setForeground(new java.awt.Color(255, 255, 255));
        
        JLabel vacioResultados = new JLabel();
        jPanelMenuResultados.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuResultados.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuResultados.setLayout(new BorderLayout(15, 0));
        jPanelMenuResultados.add(vacioResultados, BorderLayout.WEST);
        jPanelMenuResultados.add(btnResultados, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuResultados);
        
        btnResultados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                accionResultados();
            }
        });
    }
    
    
    /**
     * Función que se ejecuta cuando el usuario hace click sobre la opción de navegación Resultados
     * Permite ver la lista de resultados que se encuentran cargadas en la aplicación. 
     * Si la lista de resultados en vacía, muestra un botón que permite cargar un archivo CSV
     * con la información de los resultados
     */
    private void accionResultados() {
        jLabelTop.setText("Resultados");

        // Si no hay resultados cargados, muestra el botón de carga de resultados
        if (resultados == null) {
            jPanelMain.removeAll();
            JPanel resultadosPanel = new JPanel();

            if (resultados == null) {

                JLabel notResultados = new JLabel();
                notResultados.setText("No hay resultados, por favor cargue resultados \n\n");
                resultadosPanel.add(notResultados);

                JButton cargarFile = new JButton();
                cargarFile.setText("Seleccione el archivo");
                resultadosPanel.add(cargarFile);
                this.cargarFileResultados();
                cargarFile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        cargarFileResultados();
                    }
                });
            }

            jPanelMain.add(resultadosPanel);
            jPanelMain.repaint();
            jPanelMain.revalidate();
        }
        // Si hay ressultados cargados, llama el método que permite pintar la tabla de resultados
        else {
            pintarTablaResultados(this.resultados);
        }
        
        int aux = Integer.parseInt(matrixAuditoria[2][1]);
        aux++;
        matrixAuditoria[3][1] = "" + aux;
    }
    
    
    /**
     * Función que se encarga de ajustar los elementos gráficos que componente la opción de navegación de Dashboard de Selecciones
     * Define estilos, etiquetas, iconos que decoran la opción del Menú. 
     * Esta opción de Menu permite mostrar los diferentes datos que será extraidos de la información de 
     * las selecciones de futbol que fueron cargadas
     */
    private void pintarMenuDashboardSel() {
        btnDashboardSel.setIcon(new ImageIcon(getClass().getResource("/resources/icons/dashboard_selecciones.png")));
        btnDashboardSel.setText("Dash Selecciones");
        btnDashboardSel.setForeground(new java.awt.Color(255, 255, 255));
        
        JLabel vacioDashboardSelecciones = new JLabel();
        jPanelMenuDashboardSel.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuDashboardSel.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuDashboardSel.setLayout(new BorderLayout(15, 0));
        jPanelMenuDashboardSel.add(vacioDashboardSelecciones, BorderLayout.WEST);
        jPanelMenuDashboardSel.add(btnDashboardSel, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuDashboardSel);
        
        btnDashboardSel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Dashboard Selecciones");
                if (selecciones !=  null) {
                    accionDashboardSel();
                }
            }
        });
    }
    
    
    /**
     * TRABAJO DEL ESTUDIANTE
     * Se debe módificar este método para poder calcular y pintar las diferentes informaciones que son solicitadas
     * Revise el proceso que se siguen en los demás métodos para poder actualizar la información de los paneles
     */
    private void accionDashboardSel() {
        
        jLabelTop.setText("Dashboard de selecciones");
        
        JTextArea a = new JTextArea();
        a.setText("En esta sección, teniendo en cuenta los datos que fueron cargados en la matriz de selecciones \n"
                + "se deben mostrar los siguientes datos:\n\n"
                + "1. Total de selecciones Cargadas \n"
                + "2. Número de selecciones por continente (Se puede usar una tabla para pintar esto) \n"
                + "3. Cantidad de nacionalidades diferentes de los directores técnicos \n"
                + "4. Ranking de nacionalidades de directores técnicos \n\n"
                + "Utilice los diferentes componentes gráficos para construir un dashboard lo más estético posible");
        
        // Primer punto
     
        JLabel jLabelTotalSelected = new JLabel();
        jLabelTotalSelected.setFont(new java.awt.Font("Liberation Sans", 0, 20)); // NOI18N
        jLabelTotalSelected.setText("Total Selecciones en el mundial: " + this.totalSeleccionesCargadas);
        jLabelTotalSelected.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Segundo punto
        
        JLabel jLabelContinentes = new JLabel("* Listado de selecciones por continente: ");
        
        JPanel panelLabelContinentes = new JPanel();
        panelLabelContinentes.setLayout(new BorderLayout());
        panelLabelContinentes.add(jLabelContinentes);
        
        JScrollPane scrollPanel = new JScrollPane(this.pintarSeleccionesPorContinente());
        scrollPanel.setPreferredSize((new java.awt.Dimension(580, 163)));
        
        // Tercer punto
        
        JLabel jLabelTotalNacionalidades = new JLabel("* En el mundial contamos directores técnicos  de " + this.nacionalidades.size() +" nacionalidades diferentes");
        
        JPanel panelLabelTotalNacionalidades = new JPanel();
        panelLabelTotalNacionalidades.setLayout(new BorderLayout());
        panelLabelTotalNacionalidades.add(jLabelTotalNacionalidades);
        
        
        // Cuarto punto
        JScrollPane scrollPanelNacionalidades = new JScrollPane(this.pintarNacionalidadesDeDirectores());
        scrollPanelNacionalidades.setPreferredSize((new java.awt.Dimension(580, 163)));
        
        
        // agregamos los puntos resueltos a la vista
        JPanel jPanelContainer = new JPanel();
        jPanelContainer.setLayout(new BoxLayout(jPanelContainer, BoxLayout.Y_AXIS));

        jPanelContainer.add(Box.createRigidArea(new Dimension(0, 10)));
        jPanelContainer.add(jLabelTotalSelected);
        jPanelContainer.add(Box.createRigidArea(new Dimension(0, 10)));
        jPanelContainer.add(panelLabelContinentes);
        jPanelContainer.add(Box.createRigidArea(new Dimension(0, 10)));
        jPanelContainer.add(scrollPanel);
        jPanelContainer.add(Box.createRigidArea(new Dimension(0, 10)));
        jPanelContainer.add(panelLabelTotalNacionalidades);
        jPanelContainer.add(Box.createRigidArea(new Dimension(0, 10)));
        jPanelContainer.add(scrollPanelNacionalidades);
        
        
        JScrollPane scrollPanelContainer = new JScrollPane(jPanelContainer);
        
        jPanelMain.removeAll();
        this.jPanelMain.setLayout(new BoxLayout(this.jPanelMain, BoxLayout.Y_AXIS));
        jPanelMain.add(scrollPanelContainer);
        jPanelMain.repaint();
        jPanelMain.revalidate();   
        
        int aux = Integer.parseInt(matrixAuditoria[3][1]);
        aux++;
        matrixAuditoria[4][1] = "" + aux;
    }
    
     private void pintarMenuVistas() {
         
        
        btnvistas.setText("AUDITORIA");
        btnvistas.setForeground(new java.awt.Color(255, 255, 255));
        
        JLabel vistas = new JLabel();
        
        jPanelvistas.setBackground(new java.awt.Color(17, 41, 63));
        jPanelvistas.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelvistas.setLayout(new BorderLayout(15, 0));
        jPanelvistas.add(vistas, BorderLayout.WEST);
        jPanelvistas.add(btnvistas, BorderLayout.CENTER);
        jPanelMenu.add(jPanelvistas);
        
        btnvistas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("vistas");
                
               accionvistas();     
                
            }
        });
        
        
        
    }
    
     private JTable accionvistas() {
         
         jPanelMain.removeAll();
         
         JPanel panelVistas = new JPanel();
         
        jLabelTop.setText("AUDITORIA");
        
         String[] columnNames = {"Opciones menu", "Total vistas"};
         
        JTable table = new JTable(this.matrixAuditoria, columnNames);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.setRowHeight(40);
        
        panelVistas.add(table);
        
        this.jPanelMain.setLayout(new BoxLayout(this.jPanelMain, BoxLayout.Y_AXIS));      
        jPanelMain.add(panelVistas);
            jPanelMain.repaint();
            jPanelMain.revalidate();
        
        return table;
         
        
      

       
       
       
    }
    
    
    /*
        Este metodo se encarga de devolver una tabla con los valores guardados
        en el arreglo totalSeleccionesPorContinente que es quien tiene el conteo de selecciones
        que pertenecen a un continente
    */
    private JTable pintarSeleccionesPorContinente(){
        String[] columnNames = {"Continente", "Total Selecciones"};
        
        String continentes [][] = new String[5][2];
        
        JTable table = new JTable(this.totalSeleccionesPorContinente, columnNames);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.setRowHeight(20);
        return table;
    }
    
    /*
        Este metodo se encarga de devolver en una tabala con el conteo de directores tecnicos 
        que pertenecen a una misma nacionalidad
    */
    private JTable pintarNacionalidadesDeDirectores(){
        String[] columnNames = {"Nacionalidad", "Total Directores"};
        
        String nacionalidad [][] = new String[this.nacionalidades.size()][2];
        
        int index = 0;
        for ( Map.Entry<String,Integer> e : this.nacionalidades.entrySet() ) {
            nacionalidad [index][0] = e.getKey();
            nacionalidad [index][1] = e.getValue()+"";
            index++;
        }
        
        JTable table = new JTable(nacionalidad, columnNames);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.setRowHeight(20);
        return table;
    }
    
    
    /**
     * Función que se encarga de ajustar los elementos gráficos que componente la opción de navegación de Dashboard de Resultados
     * Define estilos, etiquetas, iconos que decoran la opción del Menú. 
     * Esta opción de Menu permite mostrar los diferentes datos que será extraidos de la información de 
     * los resultados de los partidos que fueron cargados
     */
    private void pintarMenuDashboardRes() {
        btnDashboardRes.setIcon(new ImageIcon(getClass().getResource("/resources/icons/dashboard_resultados.png")));
        btnDashboardRes.setText("Dash Resultados");
        btnDashboardRes.setForeground(new java.awt.Color(255, 255, 255));
        
        JLabel vacioDashboardResultados = new JLabel();
        jPanelMenuDashboardRes.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuDashboardRes.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuDashboardRes.setLayout(new BorderLayout(15, 0));
        jPanelMenuDashboardRes.add(vacioDashboardResultados, BorderLayout.WEST);
        jPanelMenuDashboardRes.add(btnDashboardRes, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuDashboardRes);
        
        btnDashboardRes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Dashboard Resultados");
                 if (resultados !=  null) {
                    accionDashboardRes();
                 }
            }
        });
    }
    
    
    /**
     * TRABAJO DEL ESTUDIANTE
     * Se debe módificar este método para poder calcular y pintar las diferentes informaciones que son solicitadas
     * Revise el proceso que se siguen en los demás métodos para poder actualizar la información de los paneles
     */
    private void accionDashboardRes() {
        jLabelTop.setText("Dashboard de resultados");
        JTextArea a = new JTextArea();
        a.setText("En esta sección, teniendo en cuenta los datos que fueron cargados en la matriz de resultados \n"
                + "se deben mostrar los siguientes datos:\n\n"
                + "1. Número de partidos cargados \n"
                + "2. Promedio de goles por partido \n"
                + "3. Partido con más goles y partido con menos goles \n"
                + "4. Número de partidos dónde hubo un ganador y número de partidos dónde hubo empate \n"
                + "5. Selcción o selecciones con más goles y con menos goles \n"
                + "6. Selección con más puntos y menos puntos \n"
                + "7. Continente o continentes con más goles y menos goles \n"
                + "8. Clasificados por cada grupo (Clasifican los dos primeros equipos de cada grupo) \n\n"
                + "Utilice los diferentes componentes gráficos para construir un dashboard lo más estético posible");
        
        // Primer Punto 
     
        JLabel jLabelTotalPartidos = new JLabel();
        jLabelTotalPartidos.setFont(new java.awt.Font("Liberation Sans", 0, 20));
        jLabelTotalPartidos.setText("Total de partidos jugados en el mundial: " + this.totalPartidosCargados);
        jLabelTotalPartidos.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Segundo Punto
        
        JLabel jLabelPromedioGoles = new JLabel("* El promedio de goles durante la fase de grupos fue de " + this.promedioDeGoles + " goles por partido");
        
        JPanel panelLabelPromedioGoles = new JPanel();
        panelLabelPromedioGoles.setLayout(new BorderLayout());
        panelLabelPromedioGoles.add(jLabelPromedioGoles);
        
        
        // Tercer Punto
        
        JLabel jLabelPartidoMasGoles = new JLabel("* Resultado del partido con mayor cantidad de goles: ");
        
        JPanel panelLabelPartidoMasGoles = new JPanel();
        panelLabelPartidoMasGoles.setLayout(new BorderLayout());
        panelLabelPartidoMasGoles.add(jLabelPartidoMasGoles);
        
        JPanel panelPatidoMasGoles = this.getPanelResultados(true);
        
        JLabel jLabelPartidoMenosGoles = new JLabel("* Resultado del partido con menor cantidad de goles: ");
        
        JPanel panelLabelPartidoMenosGoles = new JPanel();
        panelLabelPartidoMenosGoles.setLayout(new BorderLayout());
        panelLabelPartidoMenosGoles.add(jLabelPartidoMenosGoles);
        
        JPanel panelPatidoMenosGoles = this.getPanelResultados(false);
        
        
        // Cuarto Punto
        
        JLabel jLabelGanadosEmpates = new JLabel("* En el mundial hubo un total de " + this.numeroGanadores + " partidos con un ganador y un total de "+ this.numeroEmpates + " partidos empatados");
        
        JPanel panelLabelGanadosEmpates = new JPanel();
        panelLabelGanadosEmpates.setLayout(new BorderLayout());
        panelLabelGanadosEmpates.add(jLabelGanadosEmpates);
        
        // Cuarto punto
        
         String[] columnNames = new String[2];
        
        
        
        // Agregamos los puntos resueltos a la vista
        JPanel jPanelContainer = new JPanel();
        jPanelContainer.setLayout(new BoxLayout(jPanelContainer, BoxLayout.Y_AXIS));

        jPanelContainer.add(Box.createVerticalStrut(10));
        jPanelContainer.add(jLabelTotalPartidos);
        
        jPanelContainer.add(Box.createVerticalStrut(10));
        jPanelContainer.add(panelLabelPromedioGoles);
        
        jPanelContainer.add(Box.createVerticalStrut(10));
        jPanelContainer.add(panelLabelPartidoMasGoles);
        jPanelContainer.add(Box.createVerticalStrut(10));
        jPanelContainer.add(panelPatidoMasGoles);
        jPanelContainer.add(Box.createVerticalStrut(10));
        jPanelContainer.add(panelLabelPartidoMenosGoles);
        jPanelContainer.add(Box.createVerticalStrut(10));
        jPanelContainer.add(panelPatidoMenosGoles);
        
        jPanelContainer.add(Box.createVerticalStrut(10));
        jPanelContainer.add(panelLabelGanadosEmpates);
        
        jPanelContainer.add(Box.createVerticalStrut(10));
        
        // Quinto Punto
        columnNames[0]= "Seleccion";
        columnNames[1]= "Goles";
        jPanelContainer.add(this.getDataMayorMenos(columnNames, this.golesPorSeleccion));
        
        
        // Sexto Punto
        jPanelContainer.add(Box.createVerticalStrut(10));
        columnNames[1]= "Puntos";
        jPanelContainer.add(this.getDataMayorMenos(columnNames, this.puntosPorSeleccion));
        
        
        // Septimo Punto
        jPanelContainer.add(Box.createVerticalStrut(10));
        columnNames[0]= "Continente";
        columnNames[1]= "Goles";
        jPanelContainer.add(this.getDataMayorMenos(columnNames, this.golesPorContinente));
        
        // Octavo Punto
        jPanelContainer.add(Box.createVerticalStrut(10));
        jPanelContainer.add(this.getTablaClasificados());
        
        
        
        
        JScrollPane scrollPanelContainer = new JScrollPane(jPanelContainer);
        
        jPanelMain.removeAll();
        ImageIcon icon = new ImageIcon("/resources/fondo.png");
        JLabel background = new JLabel(icon);
        background.setBounds(0, 0, jPanelMain.getWidth(), jPanelMain.getHeight());
        this.jPanelMain.setLayout(new BoxLayout(this.jPanelMain, BoxLayout.Y_AXIS));
        jPanelMain.add(background);
        jPanelMain.add(scrollPanelContainer);
        jPanelMain.repaint();
        jPanelMain.revalidate();   
        
        int aux = Integer.parseInt(matrixAuditoria[4][1]);
        aux++;
        matrixAuditoria[5][1] = "" + aux;
    }
    
    private JPanel getPanelResultados(boolean mayor) {
        
        
        int cantidadGoles = mayor ? 0 : 1000;
        
        ArrayList<Partido> listPartidos = new ArrayList<>();
        
        for (Partido partido : this.partidos) {
            int cantidad = partido.golesLocal + partido.golesVicitante;
            
            if (mayor) {
                if (cantidad > cantidadGoles) {
                    cantidadGoles = cantidad;
                    listPartidos = new ArrayList<>();
                }
            } else {
                if (cantidad < cantidadGoles) {
                    cantidadGoles = cantidad;
                    listPartidos = new ArrayList<>();
                }
            }
           
            if (cantidad == cantidadGoles){
                listPartidos.add(partido);
            }
        }
        
        JPanel panelResult = new JPanel();
        panelResult.setLayout(new BoxLayout(panelResult, BoxLayout.Y_AXIS));
        
        for (Partido partido : listPartidos) {
            JLabel jLabelGrupo = new JLabel("* Partido del grupo  " + partido.grupo);
        
            JPanel panelLabelGrupo = new JPanel();
            panelLabelGrupo.setLayout(new BorderLayout());
            panelLabelGrupo.add(jLabelGrupo);
        
            JPanel panel = new JPanel(new GridBagLayout());

            JLabel nombreEquipoLocal = new JLabel(partido.nombreLocal);
            nombreEquipoLocal.setFont(new Font("Arial", Font.BOLD, 12));
            nombreEquipoLocal.setHorizontalAlignment(SwingConstants.CENTER);
            nombreEquipoLocal.setPreferredSize(new Dimension(100, 30));
            
            JLabel golesEquipoLocal = new JLabel(partido.golesLocal + "");
            golesEquipoLocal.setFont(new Font("Arial", Font.BOLD, 24));
            golesEquipoLocal.setHorizontalAlignment(SwingConstants.CENTER);
            golesEquipoLocal.setPreferredSize(new Dimension(100, 30));

            JLabel vsLabel = new JLabel("VS");
            vsLabel.setFont(new Font("Arial", Font.BOLD, 18));
            vsLabel.setHorizontalAlignment(SwingConstants.CENTER);
            vsLabel.setPreferredSize(new Dimension(100, 30));

            JLabel golesEquipoVisitante = new JLabel(partido.golesVicitante + "");
            golesEquipoVisitante.setFont(new Font("Arial", Font.BOLD, 24));
            golesEquipoVisitante.setHorizontalAlignment(SwingConstants.CENTER);
            golesEquipoVisitante.setPreferredSize(new Dimension(100, 30));

            JLabel nombreEquipoVisitante = new JLabel(partido.nombreVicitante);
            nombreEquipoVisitante.setFont(new Font("Arial", Font.BOLD, 12));
            nombreEquipoVisitante.setHorizontalAlignment(SwingConstants.CENTER);
            nombreEquipoVisitante.setPreferredSize(new Dimension(100, 30));

            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.HORIZONTAL;
            c.insets = new Insets(10, 10, 10, 10);
            c.weightx = 0.5;
            c.gridx = 0;
            c.gridy = 0;
            panel.add(nombreEquipoLocal, c);
            c.gridx = 1;
            c.gridy = 0;
            panel.add(golesEquipoLocal, c);
            c.gridx = 2;
            c.gridy = 0;
            panel.add(vsLabel, c);
            c.gridx = 3;
            c.gridy = 0;
            panel.add(golesEquipoVisitante, c);
            c.gridx = 4;
            c.gridy = 0;
            panel.add(nombreEquipoVisitante, c);

            panelResult.add(panelLabelGrupo);
            panelResult.add(panel);
        }
        
        return panelResult;
        
        
    }
    
    private JPanel getDataMayorMenos( String[] columnNames, Map<String, Integer> arreglo){
       
       List<Entry<String, Integer>> list = new ArrayList<>(arreglo.entrySet());
        Collections.sort(list, new Comparator<Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue()); // Ordenamos de mayor a menor
            }
        });
        
        Map<String, Integer> mayores = new LinkedHashMap<>();
        Map<String, Integer> menores = new LinkedHashMap<>();
        
        int mayorCantidad = list.get(0).getValue();
        int menorCantidad = list.get(list.size()-1).getValue();
         
        for ( Map.Entry<String,Integer> e : list) {
           if (e.getValue() == mayorCantidad) {
               mayores.put(e.getKey(), e.getValue());
           } else if (e.getValue() == menorCantidad) {
               menores.put(e.getKey(), e.getValue());
           }
               
        }
        
        String tablaMasUnidad [][] = new String[mayores.size()][2];
        String tablaMenosUnidad [][] = new String[menores.size()][2];
        
        int index = 0;
        for ( Map.Entry<String,Integer> e : mayores.entrySet()) {
          tablaMasUnidad[index][0] = e.getKey();
          tablaMasUnidad[index][1] = e.getValue() + "";
          index++;
        }
        
        index = 0;
        for ( Map.Entry<String,Integer> e : menores.entrySet()) {
          tablaMenosUnidad[index][0] = e.getKey();
          tablaMenosUnidad[index][1] = e.getValue() + "";
          index++;
        }
        
        JTable table1 = new JTable(tablaMasUnidad, columnNames);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table1.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table1.setRowHeight(20);
        
        
        JTable table2 = new JTable(tablaMenosUnidad, columnNames);
        DefaultTableCellRenderer centerRenderer2 = new DefaultTableCellRenderer();
        centerRenderer2.setHorizontalAlignment(SwingConstants.CENTER);
        table2.getColumnModel().getColumn(1).setCellRenderer(centerRenderer2);
        table2.setRowHeight(20);
        
        JPanel panelContainer = new JPanel();
        panelContainer.setLayout(new BoxLayout(panelContainer, BoxLayout.Y_AXIS));
        
        String text1 = "";
        if (columnNames[0].equals("Seleccion")){
            text1 = "La/s selecciones";
        }else {
            text1 = "El/s continentes";
        }
        
        JLabel jLabelSeleccionesMasUnidad = new JLabel("* "+ text1 +" con mayor cantidad de " + columnNames[1] + " son: ");
        
        JPanel panelLabelSeleccionesMasUnidad = new JPanel();
        panelLabelSeleccionesMasUnidad.setLayout(new BorderLayout());
        panelLabelSeleccionesMasUnidad.add(jLabelSeleccionesMasUnidad);
        
        JLabel jLabelSeleccionesMenosUnidad = new JLabel("* "+ text1 +" con menor cantidad de " + columnNames[1] + " son: ");
        
        JPanel panelLabelSeleccionesMemosUnidad = new JPanel();
        panelLabelSeleccionesMemosUnidad.setLayout(new BorderLayout());
        panelLabelSeleccionesMemosUnidad.add(jLabelSeleccionesMenosUnidad);
        
        JScrollPane scrollPanel1 = new JScrollPane(table1);
        scrollPanel1.getViewport().setPreferredSize(table1.getPreferredSize());
        
        JScrollPane scrollPanel2 = new JScrollPane(table2);
        scrollPanel2.getViewport().setPreferredSize(table2.getPreferredSize());
        
        panelContainer.add(Box.createVerticalStrut(10));
        panelContainer.add(panelLabelSeleccionesMasUnidad);
        panelContainer.add(Box.createVerticalStrut(10));
        panelContainer.add(scrollPanel1);
        panelContainer.add(Box.createVerticalStrut(10));
        panelContainer.add(panelLabelSeleccionesMemosUnidad);
        panelContainer.add(Box.createVerticalStrut(10));
        panelContainer.add(scrollPanel2);
        
        return panelContainer; 
    }
    
    private JPanel getTablaClasificados () {
        
        String[] columNames = {"Grupo", "Seleccion", "Puntos"};
        
        JTable tablaClasificados = new JTable(this.clasificadosPorGrupo, columNames);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        tablaClasificados.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tablaClasificados.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tablaClasificados.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tablaClasificados.setRowHeight(20);
        
        JLabel jLabel = new JLabel("* Equipos clasificados a octavos de Final: ");
        
        JPanel panelLabel = new JPanel();
        panelLabel.setLayout(new BorderLayout());
        panelLabel.add(jLabel);
        
        JScrollPane scrollPanel = new JScrollPane(tablaClasificados);
        scrollPanel.setPreferredSize((new java.awt.Dimension(600, 163)));
        
        JPanel panelContainer = new JPanel();
        panelContainer.setLayout(new BoxLayout(panelContainer, BoxLayout.Y_AXIS));
        
        panelContainer.add(Box.createVerticalStrut(10));
        panelContainer.add(panelLabel);
        panelContainer.add(Box.createVerticalStrut(10));
        panelContainer.add(scrollPanel);
        
        return panelContainer;
    }
 
    /**
     * Función que permite darle estilos y agregar los componentes gráficos del contendor de la parte 
     * izquierda de la interfaz, dónde se visulaiza el menú de navegaación
     */
    private void pintarPanelIzquierdo() {
        // Se elimina el color de fondo del panel del menú
        jPanelMenu.setOpaque(false);
        
        // Se agrega un border izquierdo, de color blanco para diferenciar el panel de menú del panel de contenido
        jPanelLeft.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, Color.WHITE));
        
        // Se define un BoxLayot de manera vertical para los elementos del panel izquierdo
        jPanelLeft.setLayout(new BoxLayout(jPanelLeft, BoxLayout.Y_AXIS));
        jPanelLeft.setBackground(new java.awt.Color(0, 24, 47));
        getContentPane().add(jPanelLeft, java.awt.BorderLayout.LINE_START);
        jPanelLeft.add(jPanelMenu);
        jPanelLeft.setPreferredSize((new java.awt.Dimension(220, 540)));
        jPanelLeft.setMaximumSize( jPanelLeft.getPreferredSize());
    }
    
    
    /**
     * Función que permite leer un archivo y procesar el contenido que tiene en cada una de sus líneas
     * El contenido del archivo es procesado y cargado en la matriz de selecciones. Una vez la información se carga 
     * en la atriz, se hace un llamado a la función pintarTablaSelecciones() que se encarga de pintar en la interfaz 
     * una tabla con la información almacenada en la matriz de selecciones
     */
    public void cargarFileSelecciones() {

        //JFileChooser cargarFile = new JFileChooser();
        //cargarFile.showOpenDialog(cargarFile);

        Scanner entrada = null;
        try {
            
            // Se obtiene la ruta del archivo seleccionado
            //String ruta = cargarFile.getSelectedFile().getAbsolutePath();
            String ruta = "C:\\Users\\gabri\\Downloads\\OneDrive_1_20-4-2023\\src\\proyectomundial\\files\\selecciones.csv";
            
            // Se obtiene el archivo y se almancena en la variable f
            File f = new File(ruta);
            Path path = Paths.get(ruta);
            this.totalSeleccionesCargadas = (int)(Files.lines(path).count() -1);
            entrada = new Scanner(f);

            // Se define las dimensiones de la matriz de selecciones
            selecciones = new String[this.totalSeleccionesCargadas][5];
            
            // Permite que el sistema se salte la léctura de los encabzados del archivo CSV
            entrada.nextLine();
        

            int i = 0;
            // Se leen cada unas de las líneas del archivo
            while (entrada.hasNext()) {
                String line = entrada.nextLine();
                String[] columns = line.split(",");

                for (int j = 0; j < columns.length; j++) {
                    selecciones[i][j] = columns[j];
                }
                i++;
            }
            
            this.getSeleccionesPorContinente();
            this.getDirectoresPorNacionalidad();

            pintarTablaSelecciones();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (entrada != null) {
                entrada.close();
            }
        }
    }
    
    
    /*
        Este método se encargar de realizar el conteo de selecciones por continente esto se logra
        recorriendo el arreglo de selecciones cargadas en el archivo y sumando un a una variable creada por cada
        continente cada vez que dicho continente aparecia en el arreglo
    */
    private void getSeleccionesPorContinente(){
        int europa = 0, asia = 0, africa = 0, oceania = 0, americaDelSur = 0, americaCentral = 0, americaDelNorte = 0;
        for (String[] seleccione : this.selecciones) {
            String continente = seleccione[2];
            switch (continente) {
                case "Europa" -> europa++;
                case "Asia" -> asia++;
                case "África" -> africa++;
                case "América del Sur" -> americaDelSur++;
                case "América Central" -> americaCentral++;
                case "América del Norte" -> americaDelNorte++;
                case "Oceanía" -> oceania++;
                    
            }
        }
     
         for (int i = 0; i < totalSeleccionesPorContinente.length ; i++) {
             switch (i) {
                case 0 ->   {
                    this.totalSeleccionesPorContinente [0][0] = "Europa";
                    this.totalSeleccionesPorContinente [0][1] = europa+"";
                }
                case 1 ->  {
                    this.totalSeleccionesPorContinente [1][0] = "Asia";
                    this.totalSeleccionesPorContinente [1][1] = asia+"";
                }
                case 2 ->  {
                    this.totalSeleccionesPorContinente [2][0] = "África";
                    this.totalSeleccionesPorContinente [2][1] = africa+"";
                }
                case 3 ->  {
                    this.totalSeleccionesPorContinente [3][0] = "América del Sur";
                    this.totalSeleccionesPorContinente [3][1] = americaDelSur+"";
                }
                case 4 ->  {
                    this.totalSeleccionesPorContinente [4][0] = "América Central";
                    this.totalSeleccionesPorContinente [4][1] = americaCentral+"";
                }
                case 5 ->  {
                    this.totalSeleccionesPorContinente [5][0] = "América del Norte";
                    this.totalSeleccionesPorContinente [5][1] = americaDelNorte+"";
                }
                case 6 ->  {
                    this.totalSeleccionesPorContinente [6][0] = "Oceanía";
                    this.totalSeleccionesPorContinente [6][1] = oceania+"";
                }
                    
            }
        }
        
    }
    
    /*
        Este metodo se encarga de poder obtener el ranking de nacionalidades de los directores tecnicos
        para ellos se recorre la matriz de selecciones y se almacena en un directorio, por cada vez que
        dicha nacionalidad vuelve a aparecer se actualizá el valor almacenado en el directorio sumando 
        uno al valor previamente almecenado
    */
    private void getDirectoresPorNacionalidad() {
        for (String[] element: this.selecciones) {
            String nacionalidad = element[4];
            int cantidad = 1;
            if (this.nacionalidades.containsKey(nacionalidad)) {
                 cantidad += this.nacionalidades.get(nacionalidad);
            }
            this.nacionalidades.put(nacionalidad, cantidad);
        }
        
        /*
            en este punto aplicamos un ordenamiento para poder mostrar de mayor a menor la cantidad de nacionalidades
        */
        
        List<Entry<String, Integer>> list = new ArrayList<>(this.nacionalidades.entrySet());
        Collections.sort(list, new Comparator<Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue()); // Ordenamos de mayor a menor
            }
        });
        
        this.nacionalidades = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entrada : list) {
            this.nacionalidades.put(entrada.getKey(), entrada.getValue());
        }

    }
            
    
    /**
     * Función que se encarga de pinta la tabla con la información de las selelceciones que fue cargada previamente
     * La tabla tiene definido un encabezado con las siguentes columnas: 
     * {"ID","Selección", "Continente", "DT", "Nacionalidad DT"}
     * Columnas que se corresponden son la información que fue leida desde el archivo csv
     */
    public void pintarTablaSelecciones() {
        pintarDetalleTablas(selecciones);
    }
    
    
    public void filtrarSelecciones(String cadena) {
        
        seleccionesFiltro = new String[this.selecciones.length][5];
        
        int j = 0;
        
        for(int i=0; i < selecciones.length; i++) {
            String[] seleccion = selecciones[i];
            //if(seleccion[1].contentEquals(cadena) || seleccion[2].contentEquals(cadena)) {
            if(seleccion[1].contains(cadena) || seleccion[2].contains(cadena) || seleccion[3].contains(cadena) || seleccion[4].contains(cadena)) {
                seleccionesFiltro[j] = seleccion;
                j++;
            }
        }
        
        String[][] seleccionesFinal = Arrays.copyOfRange(seleccionesFiltro, 0, j);
        
        pintarDetalleTablas(seleccionesFinal);
        
    }
    public void pintarResultados() {
        pintarTablaResultados(this.resultados);
    }
    
    private void pintarDetalleTablas (String[][] selecciones) {
        String[] columnNames = {"ID","Selección", "Continente", "DT", "Nacionalidad DT"};
        
        JTable table = new JTable(selecciones, columnNames);
        table.setRowHeight(30);
        
        JPanel form = new JPanel();
        form.setLayout(new GridLayout(4, 1, 0, 0));
        
        JLabel label = new JLabel();
        label.setText("Busqueda de Equipos");
        form.add(label);
        
        JTextField field = new JTextField();
        form.add(field);
        
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 2, 30, 0));
        
        JButton buscar = new JButton();
        buscar.setText("Buscar");
        buscar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        String valorBusqueda = field.getText();
                        filtrarSelecciones(valorBusqueda);
                    }
                });
        panelBotones.add(buscar);
        
        JButton limpiar = new JButton();
        limpiar.setText("Ver Todos");
        limpiar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        pintarTablaSelecciones();
                    }
                });
        panelBotones.add(limpiar);
        form.add(panelBotones);
        
        JPanel seleccionesPanel = new JPanel();
        seleccionesPanel.setLayout(new BoxLayout(seleccionesPanel, BoxLayout.Y_AXIS));
        seleccionesPanel.setPreferredSize((new java.awt.Dimension(620, 410)));
        seleccionesPanel.setMaximumSize( jPanelRight.getPreferredSize());
        
        JScrollPane scrollPane = new JScrollPane(table);
        seleccionesPanel.add(form);
        seleccionesPanel.add(scrollPane);
        
        jPanelMain.removeAll();
        jPanelMain.add(seleccionesPanel, BorderLayout.PAGE_START);
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }
    
    
    /**
     * Función que tiene la lógica que permite leer un archivo CSV de resultados y cargarlo 
     * sobre la matriz resultados que se tiene definida cómo variable global. 
     * Luego de cargar los datos en la matriz, se llama la función pintarTablaResultados() que se encarga 
     * de visulizar el contenido de la matriz en un componente gráfico de tabla
     */
    public void cargarFileResultados() {

        // JFileChooser cargarFile = new JFileChooser();
        // cargarFile.showOpenDialog(cargarFile);

        Scanner entrada = null;
        try {
            // Se obtiene la ruta del archivo seleccionado
            // String ruta = cargarFile.getSelectedFile().getAbsolutePath();
            String ruta = "C:\\Users\\gabri\\Downloads\\OneDrive_1_20-4-2023\\src\\proyectomundial\\files\\partidos2018.csv";
            
            
            // Se obtiene el archivo y se almancena en la variable f
            File f = new File(ruta);
            Path path = Paths.get(ruta);
            this.totalPartidosCargados = (int)(Files.lines(path).count() -1);
            entrada = new Scanner(f);

            // Se define las dimensiones de la matriz de selecciones
            resultados = new String[this.totalPartidosCargados][7];
            entrada.nextLine();

            int i = 0;
            // Se iteran cada una de las líneas del archivo
            while (entrada.hasNext()) {
                String line = entrada.nextLine();
                String[] columns = line.split(",");

                for (int j = 0; j < columns.length; j++) {
                    resultados[i][j] = columns[j];
                }
                i++;
            }

            pintarTablaResultados(this.resultados);
            
            this.getDataResultados();
            this.getClasificados();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (entrada != null) {
                entrada.close();
            }
        }
    }
    
    public void filtrarResultados(String cadena) {
        
        resultadosFiltro = new String[this.resultados.length][7];
        
        int j = 0;
        
        for(int i=0; i < resultados.length; i++) {
            String[] resultado = resultados[i];
            //if(seleccion[1].contentEquals(cadena) || seleccion[2].contentEquals(cadena)) {
            if(resultado[0].contains(cadena) || resultado[1].contains(cadena) || resultado[2].contains(cadena) || resultado[3].contains(cadena) 
                     || resultado[4].contains(cadena) || resultado[5].contains(cadena) || resultado[6].contains(cadena)) {
                resultadosFiltro[j] = resultado;
                j++;
            }
        }
        
        String[][] resultadosFinal = Arrays.copyOfRange(resultadosFiltro, 0, j);
        
        pintarTablaResultados(resultadosFinal);
        
    }
     
    
    /**
     * Función que se encarga de pintar la tabla con la información de los resultados que fue cargada previamente
     * La tabla tiene definido un encabezado con las siguentes columnas: 
     * {"Grupo","Local", "Visitante", "Continente L", "Continente V", "Goles L", "Goles V"}
     * Columnas que se corresponden son la información que fue leida desde el archivo csv
     * @param resultados
     */
    public void pintarTablaResultados(String [][] resultados) {

        String[] columnNames = {"Grupo","Local", "Visitante", "Continente L", "Continente V", "Goles L", "Goles V"};
        JTable table = new JTable(resultados, columnNames);
        table.setRowHeight(30);
        
        JPanel form = new JPanel();
        form.setLayout(new GridLayout(4, 1, 0, 0));
        
        JLabel label = new JLabel();
        label.setText("Busqueda de Resultados");
        form.add(label);
        
        JTextField field = new JTextField();
        form.add(field);
        
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 2, 30, 0));
        
        JButton buscar = new JButton();
        buscar.setText("Buscar");
        buscar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        String valorBusqueda = field.getText();
                        filtrarResultados(valorBusqueda);
                    }
                });
        panelBotones.add(buscar);
        
        JButton limpiar = new JButton();
        limpiar.setText("Ver Todos");
        limpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                pintarResultados();
            }
        });
        panelBotones.add(limpiar);
        form.add(panelBotones);
        
        JPanel seleccionesPanel = new JPanel();
        seleccionesPanel.setLayout(new BoxLayout(seleccionesPanel, BoxLayout.Y_AXIS));
        seleccionesPanel.setPreferredSize((new java.awt.Dimension(620, 410)));
        seleccionesPanel.setMaximumSize( jPanelRight.getPreferredSize());
        
        JScrollPane scrollPane = new JScrollPane(table);
        seleccionesPanel.add(form);
        seleccionesPanel.add(scrollPane);
        
        jPanelMain.removeAll();
        jPanelMain.add(seleccionesPanel, BorderLayout.PAGE_START);
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }
    
    
    private void getDataResultados() {
        
        double sumaDeGoles = 0;
        
        for (String[] seleccione : this.resultados) {
            
            int golesLocal = Integer.parseInt(seleccione[5]);
            int golesVicitante = Integer.parseInt(seleccione[6]);
            
            String local = seleccione[1];
            String vicitante = seleccione[2];
            
            String continenteLocal = seleccione[3];
            String continenteVicitante = seleccione[4];
            
            String grupo = seleccione[0];
            
            this.partidos.add(new Partido(golesLocal, golesVicitante, local, vicitante, grupo));
            
            sumaDeGoles += (golesLocal + golesVicitante);
            
            // Calculamos el numero de partidos con empates y el numero de partidos con un ganador
            if (golesLocal == golesVicitante) {
                this.numeroEmpates++;
            } else {
                this.numeroGanadores++;
            }
            
            
            // Guardamos los goles por cada seleccion
            if (this.golesPorSeleccion.containsKey(local)) {
                 golesLocal += this.golesPorSeleccion.get(local);
            }
            this.golesPorSeleccion.put(local, golesLocal);
            
            if (this.golesPorSeleccion.containsKey(vicitante)) {
                 golesVicitante += this.golesPorSeleccion.get(vicitante);
            }
            this.golesPorSeleccion.put(vicitante, golesVicitante);
            
            
            // Calculamos y guardamos los puntos por cada seleccion
            
            golesLocal = Integer.parseInt(seleccione[5]);
            golesVicitante = Integer.parseInt(seleccione[6]);
            
            int puntosLocal = 0;
            int puntosVicitante = 0;
            
            if (golesLocal == golesVicitante){
                puntosLocal += 1;
                puntosVicitante += 1;
            } else if (golesLocal > golesVicitante){
                puntosLocal += 3;
            } else {
                puntosVicitante += 3;
            }
            
            if (this.puntosPorSeleccion.containsKey(local)) {
                 puntosLocal += this.puntosPorSeleccion.get(local);
            }
            this.puntosPorSeleccion.put(local, puntosLocal);
            
            if (this.puntosPorSeleccion.containsKey(vicitante)) {
                 puntosVicitante += this.puntosPorSeleccion.get(vicitante);
            }
            
            this.puntosPorSeleccion.put(vicitante, puntosVicitante);
           
            
            // Guardamos los goles por continente
            
            // reseteamos el conteo de goles para guardarlo ahora por continentes
            golesLocal = Integer.parseInt(seleccione[5]);
            golesVicitante = Integer.parseInt(seleccione[6]);
            
            if (this.golesPorContinente.containsKey(continenteLocal)) {
                golesLocal += this.golesPorContinente.get(continenteLocal);
            }
            this.golesPorContinente.put(continenteLocal, golesLocal);

            if (this.golesPorContinente.containsKey(continenteVicitante)) {
                golesVicitante += this.golesPorContinente.get(continenteVicitante);
            }
            
            this.golesPorContinente.put(continenteVicitante, golesVicitante);
            
        }
        
        double promedio = sumaDeGoles / this.totalPartidosCargados;
        
        DecimalFormat formato = new DecimalFormat("#.#");
        this.promedioDeGoles =  formato.format(promedio);
    }
    
    private void getClasificados() {
        
        int grupo = 1;
        
        String primerPuesto = "";
        String segundoPuesto = "";
        int puntosPrimerPuesto = 0;
        int puntosSegundoPuesto = 0;
        
       
        int agregados = 0;
        int contador = 0;
        
        System.out.println("Puntos por seleccion: "+this.puntosPorSeleccion);
        
        for (Map.Entry<String, Integer> element : this.puntosPorSeleccion.entrySet()) {
            
            if (contador == 4) {
                String letraGrupo = this.getGrupo(grupo);
                this.clasificadosPorGrupo [agregados][0] = letraGrupo;
                this.clasificadosPorGrupo [agregados][1] = primerPuesto;
                this.clasificadosPorGrupo [agregados][2] = puntosPrimerPuesto + "";
                agregados++;
                this.clasificadosPorGrupo [agregados][0] = letraGrupo;
                this.clasificadosPorGrupo [agregados][1] = segundoPuesto;
                this.clasificadosPorGrupo [agregados][2] = puntosSegundoPuesto + "";
                contador = 0;
                agregados++;
                grupo++;
                
                primerPuesto = "";
                segundoPuesto = "";
                puntosPrimerPuesto = 0;
                puntosSegundoPuesto = 0;
            }
            
            if (puntosPrimerPuesto == 0 || puntosPrimerPuesto < element.getValue()){
                puntosSegundoPuesto = puntosPrimerPuesto;
                segundoPuesto = primerPuesto;
                primerPuesto = element.getKey();
                puntosPrimerPuesto = element.getValue();
            } else if (puntosSegundoPuesto < element.getValue()) {
                segundoPuesto = element.getKey();
                puntosSegundoPuesto = element.getValue();
            }
            
            contador++;
        }
        
        String letraGrupo = this.getGrupo(grupo);
        this.clasificadosPorGrupo [agregados][0] = letraGrupo;
        this.clasificadosPorGrupo [agregados][1] = primerPuesto;
        this.clasificadosPorGrupo [agregados][2] = puntosPrimerPuesto + "";
        agregados++;
        this.clasificadosPorGrupo [agregados][0] = letraGrupo;
        this.clasificadosPorGrupo [agregados][1] = segundoPuesto;
        this.clasificadosPorGrupo [agregados][2] = puntosSegundoPuesto + "";
        
    }
    
    private String getGrupo(int grupo){
        switch (grupo) {
            case 1 -> {
                return "A";
            }
            case 2 -> {
                return "B";
            }
            case 3 -> {
                return "C";
            }
            case 4 -> {
                return "D";
            }
            case 5 -> {
                return "E";
            }
            case 6 -> {
                return "F";
            }
            case 7 -> {
                return "G";
            }
            default -> {
                return "H";
            }
        }
    }
    
    /**
     * Función que permite darle estilos y agregar los componentes gráficos del contendor de la parte 
     * derecha de la interfaz, dónde se visulaiza de manera dinámica el contenido de cada una de las funciones
     * que puede realizar el usuario sobre la aplicación. 
     */
    private void pintarPanelDerecho() {
        
        // Define las dimensiones del panel
        jPanelMain.setPreferredSize((new java.awt.Dimension(620, 420)));
        jPanelMain.setMaximumSize(jPanelLabelTop.getPreferredSize());
        
        getContentPane().add(jPanelRight, java.awt.BorderLayout.CENTER);
        jPanelRight.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        jPanelRight.add(jPanelLabelTop, BorderLayout.LINE_START);
        jPanelRight.add(jPanelMain);
        jPanelRight.setPreferredSize((new java.awt.Dimension(620, 540)));
        jPanelRight.setMaximumSize( jPanelRight.getPreferredSize());
    }
    
    /**
     * Función que permite pinta la barra azul del contenedor de contenidos. Barra azul que permite indicar 
     * en que sección que se encuentra navegando el usuario.
     */
    private void pintarLabelTop() {
        jLabelTop = new JLabel();
        jLabelTop.setFont(new java.awt.Font("Liberation Sans", 1, 36)); // NOI18N
        jLabelTop.setForeground(new java.awt.Color(241, 241, 241));
        jLabelTop.setText("Home");
        
        JLabel vacioTopLabel = new JLabel();
        jPanelLabelTop.setLayout(new BorderLayout(15, 0));
        jPanelLabelTop.add(vacioTopLabel, BorderLayout.WEST);
        jPanelLabelTop.setBackground(new java.awt.Color(18, 119, 217));
        jPanelLabelTop.add(jLabelTop, BorderLayout.CENTER);
        jPanelLabelTop.setPreferredSize((new java.awt.Dimension(620, 120)));
        jPanelLabelTop.setMaximumSize(jPanelLabelTop.getPreferredSize());
    }
    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIManual().setVisible(true);
            }
        });
    }
}

class Partido {

    int golesLocal;
    int golesVicitante;
    
    String grupo;
    String nombreLocal;
    String nombreVicitante;

    public Partido(int golesLocal, int golesVicitante, String nombreLocal, String nombreVicitante, String grupo) {
        this.golesLocal = golesLocal;
        this.golesVicitante = golesVicitante;
        this.nombreLocal = nombreLocal;
        this.nombreVicitante = nombreVicitante;
        this.grupo = grupo;
    }
}



