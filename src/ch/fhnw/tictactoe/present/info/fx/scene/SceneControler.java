package ch.fhnw.tictactoe.present.info.fx.scene;


import ch.fhnw.tictactoe.app.ApplicationContext;
import javafx.stage.Stage;

public class SceneControler{
/*
    public MainPanel(ApplicationContext applicationContext) {

        applicationContext.getPrimaryStage().setJMenuBar(createMenuBar(applicationContext));

        setLayout(new BorderLayout());
        final JSplitPane verticalSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        verticalSplitPane.setDividerLocation(infoAreaWidth);
        verticalSplitPane.setResizeWeight(0.0);  // Only resize the right
        add(verticalSplitPane, BorderLayout.CENTER);

        // Left side
        //-----------



        // Right side
        //------------
        final JSplitPane horizontalSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT) {
            public Dimension getMinimumSize() {
                return new Dimension(0, 0);
            }
        };

        horizontalSplitPane.setDividerLocation(imageAreaHeight);
        horizontalSplitPane.setResizeWeight(1.0); // Only resize the top
        verticalSplitPane.setRightComponent(horizontalSplitPane);

    }

    private JMenuBar createMenuBar(final ApplicationContext applicationContext) {
        JMenuBar menuBar = new JMenuBar();

        JMenu menu;
        JMenuItem menuItem;

        // File
        //------

        menu = new JMenu("File");
        menuBar.add(menu);

        menuItem = new JMenuItem("Load Image...");
        menu.add(menuItem);

        menuItem = new JMenuItem("Load Data...");
        menu.add(menuItem);

        menu.addSeparator();

        menuItem = new JMenuItem("Exit");
        menu.add(menuItem);
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Help
        //------

        menu = new JMenu("Help");
        menuBar.add(menu);

        menuItem = new JMenuItem("About");
        menu.add(menuItem);
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String version = "Version: " + applicationContext.getVersion();
                JOptionPane.showMessageDialog(applicationContext.getPrimaryStage(), version, "About", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        return menuBar;
    }
    */

    public SceneControler(ApplicationContext applicationContext){
        Stage primaryStage = applicationContext.getPrimaryStage();

        applicationContext.getSceneSwitchModel().addListner(() ->
                primaryStage.setScene(applicationContext.getSceneSwitchModel().getAcctualScene()));



        primaryStage.setScene(applicationContext.getSceneSwitchModel().getAcctualScene());
        primaryStage.show();
    }

}