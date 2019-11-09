import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class Config {
	String confDatei,dbConn,zielVerz;
	ArrayList<Tabelle> tabs;
	public Config(CmdLine cmd) {
		int iArt=0;
		try {
			confDatei=cmd.getS("-conf","/home/idl/config/Config.xml");
			Document doc=null;
			doc=new SAXBuilder().build(confDatei);
			Element x=doc.getRootElement();
			if (x==null) idl.prex("Xml-File \""+confDatei+"\" opened but no root element found. This is strange!",-4);
			if(!x.getName().equals("Config")) idl.prex("\""+confDatei+"\" is no config file.",-4);
			dbConn=x.getChildText("Datenbank");
			zielVerz=x.getChildText("Ausgabe");
			
			List<Element> b;
			b=x.getChildren("Bereich");
			tabs=new ArrayList<>();
			for (Element k:b) {
				String h=k.getAttributeValue("Art");
				if (h==null) idl.prex("Ein Bereich muß das Attribut Art gesetzt haben",4);
				switch(h) {
				case "Basis": iArt=1; break;
				case" Beweg": iArt=2; break;
				default: idl.prex("Die Art muß entweder Basis oder Beweg sein.", -4);
				}
				List<Element> lt=k.getChildren("Tabelle");
				for (Element t:lt) tabs.add(new Tabelle(iArt,t));
			}
		} catch(Exception e) {e.printStackTrace(); idl.prex("Fehler beim Lesen der Config",-4);}
	}
	
	Tabelle sucheTab(int id) {
		for (Tabelle k:tabs) {
			//idl.pr("Suche Element:"+k.id+","+k.name);
			if (k.id==id) return k;
		}
		idl.prex("Eine Tabelle mit der id "+id+" existiert nicht",-2);
		return null;
	}
}
