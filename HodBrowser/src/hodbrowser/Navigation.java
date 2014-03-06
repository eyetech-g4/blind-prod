package hodbrowser;

import java.util.ArrayList;

import javafx.scene.web.WebEngine;

public class Navigation{
	private int i=0;
	private ArrayList<String> URLpath=new ArrayList<String>();
	private WebEngine usedWebEngine;
	
	public String getPreviousURLpath() {
		//System.out.println(i);
		if((URLpath.size()>0)&&(i>0)){
			i--;
			//System.out.println(i-1);
			//System.out.println(URLpath.get(i));
			return URLpath.get(i-1);	
		}
		else{
			return URLpath.get(i);	
		}
		
		
	}
	
	public String getNextURLpath() {
		//System.out.println(i);
		if((URLpath.size()>0)&&(i<URLpath.size())){
			i++;
			System.out.println(i);
			System.out.println(URLpath.get(i-1));
			return URLpath.get(i-1);	
		}
		else{
			return URLpath.get(i-1);	
		}
	}
	
	protected void setURLpath() {
		usedWebEngine=HodBrowser.getWebEngine();
		this.URLpath.add(usedWebEngine.getLocation().toString());
		//System.out.println(URLpath.get(i));
		System.out.println(i);
		i++;
	}
	
	
	
}