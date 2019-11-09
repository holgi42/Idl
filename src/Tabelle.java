import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;

class Feld{
	String sql,ueber,anker,bemerkung;
	Feld(Element x){
		sql=idl.gets(x, "Sql");
		ueber=idl.gets(x, "Ueber");
		anker=x.getChildText("Anker");
		bemerkung=x.getChildText("Bemerkung");
	}
	
	String showFeld() {
		return String.format("%-5s %-15s %-30s %s\n", sql,ueber,anker,bemerkung);
	}
}

public class Tabelle {
	int id,art;
	String name,dateiName,bauen,bemerkung;
	boolean kopfConfig;
	ArrayList<Feld> felder;
	public Tabelle(int art,Element x) {
		this.art=art;
		this.id=idl.getai(x,"Id");
		name=idl.gets(x,"Name");
		dateiName=idl.gets(x, "Dateiname");
		bauen=x.getChildText("Bauen");
		bemerkung=x.getChildText("Bemerkung");
		if (x.getChild("KopfConfig")!=null) kopfConfig=true; else kopfConfig=false;
		felder=new ArrayList<>();
		List<Element> lf;
		lf=x.getChildren("Feld");
		for (Element f:lf) felder.add(new Feld(f));		
	}
	
	public String showShort() {
		return String.format("%3d %s", id,name);
	}
	
	public String showLong() {
		String z="Tabelle\n--------------------------------\n";
		z+="Id       : "+id+"\n";
		z+="Art      : "+art+"\n";
		z+="Name     : "+name+"\n";
		z+="Dateiname: "+dateiName+"\n";
		z+="Bauen    : "+bauen+"\n";
		z+="Bemerkung: "+bemerkung+"\n";
		z+=String.format("%-5s %-15s %-30s %s\n", "Sql","Überschrift","Anker","Bemerkung");
		z+="----- --------------- ------------------------------  ------------------------------\n";
		for (Feld k:felder) z+=k.showFeld();
		return z;
	}
}
