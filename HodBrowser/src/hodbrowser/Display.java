package hodbrowser; 
 
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import javafx.stage.Screen;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import static javafx.concurrent.Worker.State.FAILED;
  
@SuppressWarnings("serial")
public class Display extends JFrame {
 
    // Window
    private final JPanel panel = new JPanel(new BorderLayout());
    private WebView view;
    private Navigation hodNavigation;

    // Tool bar
    private final JButton btnHome = new JButton("Home");
    private final JButton btnContrast = new JButton("Contrast");
    private final JButton btnPrevious = new JButton("Previous");
    private final JButton btnNext = new JButton("Next");
    private final JButton btnRefresh = new JButton("Refresh");
    private final JButton btnBookMark = new JButton("Bookmark");
    private final JButton btnHod = new JButton("HÖD");
    private final JPopupMenu popupBookList = new JPopupMenu();
    private final JPopupMenu popupBookSettings = new JPopupMenu();
    private final JPopupMenu popupHod = new JPopupMenu();
    private final JPopupMenu popupBookDelete = new JPopupMenu();
    private final Border buttonBorder = new LineBorder(Color.WHITE, 2);
    private GridBagLayout menu = new GridBagLayout();
    private JComponent toolBar = new JPanel(new BorderLayout(0, 0));
    private Dimension size;
    private JPanel topBar ;
    private JPanel statusBar;
    private Screen screen;
    private Rectangle2D bounds;
    private Integer screenWidth;
    private Integer screenHeight;
    private Dimension screenSize;
    private DefaultListModel<String> model = new DefaultListModel<String>();
    private DefaultListModel<String> model2 = new DefaultListModel<String>();
    private final JList bookList = new JList(model);
    private final JList bookSet = new JList(model2);
    private final JList bookDeleteList = new JList(model);
    private static Display instance;
    private Scene scene;

    
    // Address bar
    private final JTextField addressBar = new JTextField();
    private final JButton btnGo = new JButton("Go");
    
    // Web page
    private final JFXPanel jfxPanel = new JFXPanel();
    private WebEngine engine;
    
    // Status bar
    private final JLabel lblStatus = new JLabel();
    private final JProgressBar progressBar = new JProgressBar();
    
    // Bookmarks list
    private int i = 0;
    private int x;
    private int y;
    private String choosedBookmark;
    private String choosedOption;
    private boolean settingsListStatus = true;
    private boolean bookListStatus = true;
    
//    Action listeners
    private ActionListener goToPage;
    private ActionListener refreshPage;
    private ActionListener previousPage;
    private ActionListener nextPage;
    private ActionListener homePage;
    private ActionListener settings;
    private ActionListener contrast;
    
    private VoiceRecognizer reco;
 
    protected Display(WebView webPage, Navigation hodNavigation) {
    	
    	this.view = webPage;
    	this.hodNavigation = hodNavigation;
        initComponents();
        instance = this;
        scene = new Scene(this.view);
        this.hodNavigation.pushToTalk(this.scene);
    }
    
    public static Display getInstance() {
    	return instance;
    }

    
    private void initComponents() {
        
    	// Web page
        this.createScene();
        // Buttons actions
        
        this.goToPage = new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {

            	hodNavigation.smartBar(addressBar.getText());
            }
        };
        
        
        this.refreshPage = new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
            	hodNavigation.Refresh();
            }
        };
        
        this.previousPage = new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
            	hodNavigation.previousPage();
            }
        };
        
        this.nextPage = new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                hodNavigation.nextPage();
            }
        };
        
        this.homePage = new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                hodNavigation.HomePage();
            }
        };

        this.settings = new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
            	System.out.println("helloworld");
//                loadURL("https://www.google.com/webhp?hl=en");
//                new TextToSpeech("Going to the default page");
            }
        };
        
        this.contrast = new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
            	hodNavigation.contrast();
            }
        };
        
        
        this.btnGo.addActionListener(goToPage);
        this.addressBar.addActionListener(goToPage);
        this.btnHome.addActionListener(homePage);
        this.btnPrevious.addActionListener(previousPage);
        this.btnNext.addActionListener(nextPage);
        this.btnRefresh.addActionListener(refreshPage);
        this.btnHod.addActionListener(settings);
        this.btnContrast.addActionListener(contrast);
        
        // Address bar        
        this.addressBar.setFont(new java.awt.Font("Tahoma", 0, 40));
        this.addressBar.setPreferredSize(new Dimension(300, 75));
        this.addressBar.setMinimumSize(new java.awt.Dimension(300, 75));
        this.addressBar.setBackground(Color.WHITE);
        this.addressBar.setForeground(Color.BLACK);
        this.btnGo.setPreferredSize(new Dimension(100, 75));
        this.btnGo.setFont(new java.awt.Font("Tahoma", 0, 40));
        this.btnGo.setBackground(Color.BLACK);
        this.btnGo.setForeground(Color.WHITE);
        this.btnGo.setBorder(buttonBorder);
        
//		*************************************  Tool bar section  **************************************************************************
        
//      toolbar graphical configuration
        this.toolBar.setAlignmentX(CENTER_ALIGNMENT);
        this.toolBar.setAlignmentY(CENTER_ALIGNMENT);
        this.toolBar.setBackground(Color.BLACK);
        this.toolBar.setLayout(menu);
        
//      button home graphical configuration
        this.btnHome.setPreferredSize(new Dimension(200, 75));
        this.btnHome.setFont(new java.awt.Font("Tahoma", 0, 40));
        this.btnHome.setBackground(Color.BLACK);
        this.btnHome.setForeground(Color.WHITE);
        this.btnHome.setBorder(buttonBorder);
        
//      button previous graphical configuration
        this.btnPrevious.setPreferredSize(new Dimension(250, 75));
        this.btnPrevious.setFont(new java.awt.Font("Tahoma", 0, 40));
        this.btnPrevious.setBackground(Color.BLACK);
        this.btnPrevious.setForeground(Color.WHITE);
        this.btnPrevious.setBorder(buttonBorder);
        
//      button next graphical configuration
        this.btnNext.setPreferredSize(new Dimension(200, 75));
        this.btnNext.setFont(new java.awt.Font("Tahoma", 0, 40));
        this.btnNext.setBackground(Color.BLACK);
        this.btnNext.setForeground(Color.WHITE);
        this.btnNext.setBorder(buttonBorder);
        
//      button refresh graphical configuration
        this.btnRefresh.setPreferredSize(new Dimension(250, 75));
        this.btnRefresh.setFont(new java.awt.Font("Tahoma", 0, 40));
        this.btnRefresh.setBackground(Color.BLACK);
        this.btnRefresh.setForeground(Color.WHITE);
        this.btnRefresh.setBorder(buttonBorder);
        
//      button bookmark graphical configuration
        this.btnBookMark.setPreferredSize(new Dimension(300, 75));
        this.btnBookMark.setFont(new java.awt.Font("Tahoma", 0, 40));
        this.btnBookMark.setBackground(Color.BLACK);
        this.btnBookMark.setForeground(Color.WHITE);
        this.btnBookMark.setBorder(buttonBorder);
        
//      button HÖD graphical configuration
        this.btnHod.setPreferredSize(new Dimension(150, 75));
        this.btnHod.setFont(new java.awt.Font("Tahoma", 0, 40));
        this.btnHod.setBackground(Color.BLACK);
        this.btnHod.setForeground(Color.WHITE);
        this.btnHod.setBorder(buttonBorder);
        
//      button refresh graphical configuration
        this.btnContrast.setPreferredSize(new Dimension(250, 75));
        this.btnContrast.setFont(new java.awt.Font("Tahoma", 0, 40));
        this.btnContrast.setBackground(Color.BLACK);
        this.btnContrast.setForeground(Color.WHITE);
        this.btnContrast.setBorder(buttonBorder);
        
//      toolbar buttons append
        this.toolBar.add(btnHome);
        this.toolBar.add(btnPrevious);
        this.toolBar.add(btnNext);
        this.toolBar.add(btnRefresh);
        this.toolBar.add(btnBookMark);
        this.toolBar.add(btnHod);
        this.toolBar.add(btnContrast);
//      ***********************************************************************************************************************************
//      Popup bookmarks list
		
		this.bookList.setBackground(Color.BLACK);
		this.bookList.setForeground(Color.WHITE);
		this.bookList.setFont(new java.awt.Font("Tahoma", 0, 24));
		
//		Popup bookmark settings
        this.model2.addElement("Add current web site");
        this.model2.addElement("Delete a web site...");
        this.bookSet.setBackground(Color.BLACK);
        this.bookSet.setForeground(Color.WHITE);
        this.bookSet.setFont(new java.awt.Font("Tahoma", 0, 24));
        
        
        this.bookDeleteList.setBackground(Color.BLACK);
        this.bookDeleteList.setForeground(Color.WHITE);
        this.bookDeleteList.setFont(new java.awt.Font("Tahoma", 0, 24));
        
//      Popup Hod
		
        this.bookDeleteList.addMouseListener(new MouseAdapter() {
         	 
			public void mousePressed(MouseEvent e) {
				popupBookList.setVisible(false);
				popupBookDelete.setVisible(false);
				popupBookSettings.setVisible(false);
				choosedBookmark = bookDeleteList.getSelectedValue().toString();
				hodNavigation.deleteBookmark(choosedBookmark);
					
			}
        });
        
        this.bookList.addMouseListener(new MouseAdapter() {
          	 
			public void mousePressed(MouseEvent e) {

				popupBookList.setVisible(false);
				popupBookDelete.setVisible(false);
				popupBookSettings.setVisible(false);
				choosedBookmark = bookList.getSelectedValue().toString();
				hodNavigation.loadBookmark(choosedBookmark);
					
			}
        });
        
        this.bookSet.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				popupBookList.setVisible(false);
				popupBookDelete.setVisible(false);
				popupBookSettings.setVisible(false);
				choosedOption = bookSet.getSelectedValue().toString();
				switch (choosedOption){
				case "Add current web site" :
//					popup add
					try {
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								hodNavigation.addBookmark(JOptionPane.showInputDialog(null,"Title :",hodNavigation.getTitle()));
							}
						});
						
					} catch (Exception e2) {
					}
					

					break;
				case "Delete a web site..." :
//					popup remove
//					bookSet.setVisible(false);
					
					
					size = new Dimension(btnBookMark.getWidth(), 400);
					popupBookDelete.setMaximumSize(size);
					popupBookDelete.setMinimumSize(size);
					popupBookDelete.setPreferredSize(size);
	                x = (btnBookMark.getWidth() - size.width) / 2;
	                y = btnBookMark.getHeight();
	                popupBookDelete.show(btnBookMark, x, y);
//	                TODO create confirm
//					int response = JOptionPane.showConfirmDialog(null,
//							"Are you sure to set the current page as the default page ?",
//							"Home page setting",
//							JOptionPane.YES_NO_OPTION,
//							JOptionPane.QUESTION_MESSAGE);
					break;
				}
				popupBookSettings.setVisible(false);
			}
        });
//		TODO CRYYYYYYYYYYYYYYY
		btnBookMark.addMouseListener(new MouseAdapter() {
       	 
			public void mousePressed(MouseEvent e) {

		        hodNavigation.createBookmarkList(model);
				if ( SwingUtilities.isRightMouseButton(e) ) {
					settingsListStatus =! settingsListStatus;
					if(settingsListStatus){
						popupBookSettings.setVisible(false);
					}
					else{
						size = new Dimension(btnBookMark.getWidth(), 105);
						popupBookSettings.setMaximumSize(size);
						popupBookSettings.setMinimumSize(size);
						popupBookSettings.setPreferredSize(size);
		                x = (btnBookMark.getWidth() - size.width) / 2;
		                y = btnBookMark.getHeight();
		                popupBookSettings.show(btnBookMark, x, y);
		                bookListStatus = true;
					}

				} else {
					bookListStatus =! bookListStatus;
					if(bookListStatus){
						popupBookList.setVisible(false);
					}
					else{
						size = new Dimension(btnBookMark.getWidth(), 400);
						popupBookList.setMaximumSize(size);
						popupBookList.setMinimumSize(size);
						popupBookList.setPreferredSize(size);
		                x = (btnBookMark.getWidth() - size.width) / 2;
		                y = btnBookMark.getHeight();
		                popupBookList.show(btnBookMark, x, y);
		                settingsListStatus = true;
					}
				}
			}
        });
        
		this.popupBookList.setLayout(new BorderLayout());
		this.popupBookList.add(new JScrollPane(bookList));
		
		this.popupBookSettings.setLayout(new BorderLayout());
		this.popupBookSettings.add(new JScrollPane(bookSet));
		
		this.popupBookDelete.setLayout(new BorderLayout());
		this.popupBookDelete.add(new JScrollPane(bookDeleteList));
  
		this.topBar = new JPanel(new BorderLayout(0, 0));
		this.topBar.setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5));
		this.topBar.setBackground(Color.BLACK);
		this.topBar.add(toolBar, BorderLayout.PAGE_START);
		this.topBar.add(addressBar, BorderLayout.CENTER);
		this.topBar.add(btnGo, BorderLayout.EAST);
        
        
//      Status bar  
		this.lblStatus.setForeground(Color.WHITE);
		this.lblStatus.setFont(new java.awt.Font("Tahoma", 0, 20));        
		this.progressBar.setPreferredSize(new Dimension(100, 36));
		this.progressBar.setStringPainted(true);
		this.progressBar.setFont(new java.awt.Font("Tahoma", 0, 20));
		this.progressBar.setBackground(Color.WHITE);
		this.progressBar.setForeground(Color.BLACK);
        
		this.statusBar = new JPanel(new BorderLayout(0, 0));
		this.statusBar.setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5));
		this.lblStatus.setForeground(Color.WHITE);
		this.statusBar.add(lblStatus, BorderLayout.CENTER);
		this.statusBar.add(progressBar, BorderLayout.EAST);
		this.statusBar.setBackground(Color.BLACK);
 
//      Window
		this.panel.add(topBar, BorderLayout.NORTH);
		this.panel.add(jfxPanel, BorderLayout.CENTER);
		this.panel.add(statusBar, BorderLayout.SOUTH);
		this.panel.setBackground(Color.BLACK);
        
		this.getContentPane().add(panel);
        
//      Get the screen size and set the window with it
		this.screen = Screen.getPrimary();
//		TODO
        this.bounds = screen.getVisualBounds();
        this.screenWidth = (int)bounds.getWidth();
        this.screenHeight = (int)bounds.getHeight();
        this.screenSize = new Dimension(screenWidth,screenHeight);
        setPreferredSize(screenSize);
        
        this.setMinimumSize(new Dimension(1024, 600));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        
    }
 
    /**
     * Create the web page
     */
    private void createScene() {
 
        Platform.runLater(new Runnable() {
            @Override 
            public void run() {
 
                // Displays the link when a hover on it
                hodNavigation.hoverLinkDisplay(lblStatus);
 
                // Set the current URL in the address bar
                hodNavigation.currentPageLoaded(addressBar);
 
                // Set the percentage of loading
                hodNavigation.progressBarUpdate(progressBar);
                jfxPanel.setScene(scene);
            }
        });
        
        
        
    }
    
//  TODO  essayer de placer la fonction dans navigation



 
    
}


