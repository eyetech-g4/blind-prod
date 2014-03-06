package hodbrowser;

import java.util.ArrayList;

import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;

public class Navigation {

	private int i = 0;
	private ArrayList<String> URLpath = new ArrayList<String>();
	private WebEngine webEngine;
	private Web HODEngine= new Web();

	protected void PreviousPage() {

		if ((URLpath.size() > 0) && (i > 0)) {
			i--;
			HODEngine.goToPage(URLpath.get(i - 1).toString());
//			return URLpath.get(i - 1);
		} else {
			HODEngine.goToPage(URLpath.get(i - 1).toString());
//			return URLpath.get(i);
		}
	}

	protected void NextPage() {

		if ((URLpath.size() > 0) && (i < URLpath.size())) {
			i++;
			System.out.println(i);
			System.out.println(URLpath.get(i - 1));
			HODEngine.goToPage(URLpath.get(i - 1).toString());
//			return URLpath.get(i - 1);
		} else {
			HODEngine.goToPage(URLpath.get(i - 1).toString());
//			return URLpath.get(i - 1);
		}
		
	}
	
	protected void HomePage(String FavHomePage){
		HODEngine.goToPage(FavHomePage);
	}

	protected void setURLpath() {

		webEngine = HODEngine.getWebEngine();
		this.URLpath.add(webEngine.getLocation().toString());
		System.out.println(i);
		i++;
	}

}