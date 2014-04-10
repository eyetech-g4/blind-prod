package hodbrowser;

import static javafx.concurrent.Worker.State.FAILED;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.event.EventHandler;

public class Navigation {

	private String favHomePage = "https://www.google.com";
	private Web HODEngine;
	private WebEngine webEngine;
	private static Navigation instance;
	private String bookmark;
	private TextToSpeech textToSpeech;
	private SQLiteJDBC database = new SQLiteJDBC();
	private Thread t;
	private boolean ifConstrastClicked = false;
	private boolean blind;
	private boolean contrast;
	private Pattern pattern;
	private Matcher matcher;
	private ArrayList<String> list = new ArrayList<String>();
	private ArrayList<String> blindList = new ArrayList<String>();
	private int i = 0;
	private int blindCounter=0;
	private VoiceRecognizer reco;
	private JTextField addressBar;
	private WebView view;

	public Navigation(WebView view, VoiceRecognizer temp) {
		this.reco = temp;
		this.view = view;
		this.webEngine = this.view.getEngine();
		this.webEngine.setUserStyleSheetLocation(getClass().getResource("style.css").toExternalForm());
		this.initialize();
		instance = this;
	}

	protected static Navigation getInstance(){
		
		return instance;
	}
	
	protected void loadPage(final String url) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				webEngine.load(url);
				speak("Going to " + url);
			}
		});

	}

	protected void previousPage() {
		// go to the previous entry in the history list
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				if(webEngine.getHistory().getCurrentIndex() > 0){
					speak("Going to the previous page");
					webEngine.getHistory().go(-1);
				}
				else{
					speak("There is no previous page");
				}
				
			}
		});

	}

	protected void nextPage() {
		// go to the next entry in the history list
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				if(webEngine.getHistory().getCurrentIndex() < webEngine.getHistory().getEntries().size()-1){
					speak("Going to the next page");
					webEngine.getHistory().go(1);
				}
				else{
					speak("There is no next page");
				}
			}
		});

	}

	protected void HomePage() {
		// load the Home page
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				webEngine.load(favHomePage);
			}
		});

		this.speak("Going to the home page");
	}

	protected void Refresh() {
		// reload the current page.
		this.webEngine.reload();
//		String test = JOptionPane.showInputDialog(null,"Title :","ma grosse bite");
//		System.out.println(test);
		this.speak("Refreshing the current page");
	}

	protected void speak(String text) {
//		TODO: demander a renaud les methodes pour le tts a utiliser dans le try/catch
		if(this.blind == true){
			try {
				textToSpeech.interrupt();
				textToSpeech.Stop();
//				textToSpeech.join();
			} catch (Exception e) {
			}
			textToSpeech = new TextToSpeech(text);
			textToSpeech.start();
		}
		else{
		}
		
	}

	protected void initialize() {
		this.list.clear();
		this.list = this.database.getEntriesSQL("S");
		this.blind = Boolean.parseBoolean(this.list.get(1));
		this.contrast = Boolean.parseBoolean(this.list.get(2));
		this.favHomePage = list.get(0);
		if(this.contrast)this.webEngine.setUserStyleSheetLocation(getClass().getResource("contrast.css").toExternalForm());
//		System.out.println(favHomePage);
		this.webEngine.load(this.favHomePage);
//		this.currentPageLoaded(final JTextField addressBar);
	}

	protected void setBlindMode(boolean value){
		this.blind=value;
		if(this.blind){
			speak("voice activated");
		}
		else{
			
		}
	}
	
	protected void blindBookmark(){
		this.blindList.clear();
		this.blindList = database.getEntriesSQL("B");
		this.speak(this.blindList.get(i));
	}
	
	protected void nextBookmark(){
		if(this.blindCounter<this.blindList.size())this.blindCounter++;
	}
	
	protected void previousBookmark(){
		if(this.blindCounter>0)this.blindCounter--;
	}
	
	protected void smartBar(String url){
		pattern = Pattern.compile("(@)?(href=')?(HREF=')?(HREF=\")?(href=\")?(http://)?[a-zA-Z_0-9\\-]+(\\.\\w[a-zA-Z_0-9\\-]+)+(/[#&\\n\\-=?\\+\\%/\\.\\w]+)?");
        matcher = pattern.matcher(url);
        if(matcher.find()) {
           this.loadPage(url);
        }
        else
        {
        	this.loadPage("https://www.google.fr/search?q="+url);
        }
	}
	
	protected void createBookmarkList(final DefaultListModel<String> model){
		model.clear();
		this.list.clear();
		this.list = database.getEntriesSQL("B");
		for(i=0; i<list.size();i++){
			model.addElement(list.get(i));
			i++;
		}
	}
	
	protected void loadBookmark(String bookmark){
		for(i=0; i< list.size(); i++){
		if(list.get(i).equals(bookmark)){
			bookmark = list.get(i+1);
			this.i = list.size();
		}
		i++;
	}
		this.loadPage(bookmark);
	}
	
	protected String getTitle(){
		
		return webEngine.getTitle();
	}
	
	protected void addBookmark(String bookmark){
		this.bookmark = null;
		if(this.blind){
			this.bookmark = this.webEngine.getTitle();
			this.database.injectEntriesSQL(webEngine.getLocation(), this.bookmark, "B");
		}
		else{
					if(bookmark == null){
		}
					else{

						this.bookmark = bookmark;
						this.database.injectEntriesSQL(webEngine.getLocation(), this.bookmark, "B");
		}
		}

	}
	
	protected void deleteBookmark(String bookmark){
		this.bookmark = null;
		if(this.blind){
			this.bookmark = this.blindList.get(i);
			this.database.deleteEntrySQL(this.bookmark, "B");
		}
		else{
			if(bookmark == null){
		}
			else{

				this.bookmark = bookmark;
				for(i=0; i< list.size(); i++){
				if(list.get(i).equals(this.bookmark)){
					this.bookmark = list.get(i+1);
					this.i = list.size();
				}
				i++;
			}
			this.database.deleteEntrySQL(this.bookmark, "B");
		}
		}
		
	}
	
	protected void createHistorylist(){
		this.list.clear();
		this.list = database.getEntriesSQL("H");
		}
	
	protected void contrast() {

		if (ifConstrastClicked == false) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					webEngine.setUserStyleSheetLocation(getClass().getResource(
							"contrast.css").toExternalForm());
					ifConstrastClicked = true;
					System.out.println("contrast");
				}
			});

		} else {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					webEngine.setUserStyleSheetLocation(getClass().getResource("reset.css").toExternalForm());
					ifConstrastClicked = false;
					System.out.println("reset");
				}
			});

		}
	}

	protected void hoverLinkDisplay(final JLabel lblStatus) {
		webEngine.setOnStatusChanged(new EventHandler<WebEvent<String>>() {
			@Override
			public void handle(final WebEvent<String> event) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						lblStatus.setText(event.getData());
					}
				});
			}
		});
	}

	protected void currentPageLoaded(final JTextField addressBar) {
		this.addressBar = addressBar;
		webEngine.getLoadWorker().stateProperty()
				.addListener(new ChangeListener<State>() {
					public void changed(ObservableValue ov, State oldState,
							State newState) {
						if (newState == State.SUCCEEDED) {
//							save the current location in the history database
							database.injectEntriesSQL(webEngine.getLocation(),webEngine.getTitle(), "H");
//							set the current location in the address bar
							addressBar.setText(webEngine.getLocation());
//							tell the user that the page is loaded and tell him where he is
							speak("Loading complete, You're now on "+ webEngine.getTitle());
//							Display a title depending on the current location
							Display.getInstance().setTitle(webEngine.getTitle() + ", powered by HÖD");
						}
						if (newState == State.RUNNING) {
//							tell the user that the page is loading
//							speak("Loading please wait");
						}

					}
				});
	}


	protected void progressBarUpdate(final JProgressBar progressBar) {
		webEngine.getLoadWorker().workDoneProperty()
				.addListener(new ChangeListener<Number>() {
					@Override
					public void changed(
							ObservableValue<? extends Number> observableValue,
							Number oldValue, final Number newValue) {
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								progressBar.setValue(newValue.intValue());
							}
						});
					}
				});
	}



	protected void pushToTalk(final Scene scene){
	scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
		public void handle(final KeyEvent keyEvent) {
			if (keyEvent.getCode() == KeyCode.F5) {
				System.out.println("F5 pressed");
				// Stop letting it do anything else
				reco.allocate();
//				primaryStage.requestFocus();
			}
		}
	});

	scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
		public void handle(final KeyEvent keyEvent) {
			if (keyEvent.getCode() == KeyCode.F5) {
				
			}	
		}
	});

	final KeyCombination keyComb1 = new KeyCodeCombination(KeyCode.R,
			KeyCombination.CONTROL_DOWN);
	scene.addEventHandler(KeyEvent.KEY_RELEASED,
			new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent event) {
					if (keyComb1.match(event)) {
						addressBar.setText("");
						addressBar.requestFocus();
					}
				}
			});
	
    // ZOOM + WITH CTRL DOWN + M
final KeyCombination zoomPlusComb = new KeyCodeCombination(KeyCode.M,
		KeyCombination.CONTROL_DOWN);
scene.addEventHandler(KeyEvent.KEY_RELEASED,
		new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (zoomPlusComb.match(event)) {
					view.setFontScale(view.getFontScale()*1.1);
				}
			}
		});

// ZOOM - WITH CTRL DOWN + L
		final KeyCombination zoomDownComb = new KeyCodeCombination(KeyCode.L,
				KeyCombination.CONTROL_DOWN);
		scene.addEventHandler(KeyEvent.KEY_RELEASED,
				new EventHandler<KeyEvent>() {
					@Override
					public void handle(KeyEvent event) {
						if (zoomDownComb.match(event) && view.getFontScale() > 1) {
							view.setFontScale(view.getFontScale()/1.1);
						}
					}
				});
	
}
}
// ********************* methode database sql
// **********************************************
// Database = new SQLiteJDBC();
// Database.injectEntriesSQL("gooooooogle","http://gooooooooogle.com","H");
// Database.getEntriesSQL("H");
// Database.deleteEntrySQL("http://google.com","B");
// Database.getEntriesSQL("F");
// *****************************************************************************************
