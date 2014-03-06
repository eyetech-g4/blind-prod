package hodbrowser;

import java.util.ArrayList;

import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;

public class Navigation {

	private int i = 0;
	private ArrayList<String> URLpath = new ArrayList<String>();
	private Web HODEngine;
	private WebEngine webEngine;
	
	public Navigation(final Web webhodengine) {
//		super();
		System.out.println(webhodengine);
		this.HODEngine = webhodengine;
		this.webEngine=this.HODEngine.getWebEngine();
		System.out.println(webEngine.getLocation().toString());
	}

	protected void PreviousPage() {

//		System.out.println(URLpath.size());
//		System.out.println("pre i: "+i);
//		System.out.println(URLpath.get(i).toString());
//		System.out.println("etat de i pre previous"+i);
		if ((URLpath.size() > 0) && (i > 0)) {
			i--;
//			System.out.println("pree i: "+i);
//			System.out.println(URLpath.get(i).toString());
			HODEngine.goToPage(URLpath.get(i-1).toString());
//			return URLpath.get(i - 1);
		} else {
//			System.out.println(i);
//			System.out.println(URLpath.get(i).toString());
			HODEngine.goToPage(URLpath.get(i-1).toString());
//			return URLpath.get(i);
		}
//		System.out.println("état de i post previous"+i);
	}

	protected void NextPage() {

		System.out.println("next i: "+i);
		if ((URLpath.size() > 0) && (i < URLpath.size())) {
			HODEngine.goToPage(URLpath.get(i).toString());
			i++;
//			System.out.println("etat de i pre next"+i);
//			System.out.println(URLpath.get(i - 1));
//			return URLpath.get(i - 1);
		} else {
			
//			System.out.println(i);
//			System.out.println(URLpath.get(i - 1));
			HODEngine.goToPage(URLpath.get(i-1).toString());
//			return URLpath.get(i - 1);
		}
		System.out.println("etat de i post next"+i);
		
	}
	
	protected void HomePage(String FavHomePage){
		HODEngine.goToPage(FavHomePage);
	}

	protected void Refresh(){
		webEngine.reload();
	}

	protected void Stop(){
		webEngine.getLoadWorker().cancel();
	}
	
	protected void setURLpath() {

//		System.out.println("toto");
//		System.out.println("etat de i lors du set url"+i);
//		System.out.println(webEngine.getLocation());
		this.URLpath.add(webEngine.getLocation());
		System.out.println(webEngine.getLocation());
//		System.out.println("size du array list"+URLpath.size());
//		System.out.println("url dans array list"+URLpath.get(i).toString());
//		System.out.println("fin toto");
		i++;
	}
	
	protected void setURLpath(String location){
		this.URLpath.add(location);
		System.out.println(webEngine.getLocation());
		i++;
	}

}
