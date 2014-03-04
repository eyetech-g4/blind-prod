package hodbrowser;

public class Navigation{
	private int i=0;
	private String[] URLpath=new String[10];
	
	protected String[] getPreviousURLpath() {
		
		return URLpath;
	}
	
	protected void setURLpath(String uRLpath) {
		this.URLpath[i]=uRLpath;
		i++;
	}
	
	
	
}