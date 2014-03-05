package hodbrowser;

import javafx.scene.web.WebEngine;

public class Navigation{
	private int i=0;
	private String[] URLpath=new String[10];
	private WebEngine usedWebEngine;
	
	public String getPreviousURLpath() {
		System.out.println(i);
		if((i>0)&&(i<10)){
			i--;
			return URLpath[i];	
		}
		else{
			return URLpath[0];	
		}
		
		
	}
	
	public String getNextURLpath() {
		System.out.println(i);
		if(((i>=0)&&(i<10))&&(URLpath[i]!=null)){
			i++;
			return URLpath[i];	
		}
		else{
			return URLpath[10];	
		}
	}
	
	protected void setURLpath() {
		usedWebEngine=HodBrowser.getWebEngine();
		this.URLpath[i]=usedWebEngine.getLocation().toString();
		//System.out.println(URLpath[i]);
		i++;
	}
	
	
	
}