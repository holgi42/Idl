import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.jdom2.Element;

public class idl {
	
	static SqConn ora;
	static Config conf;

	public static void main(String[] args) {
		CmdLine cmd;
		cmd=new CmdLine(args);
		String bef=cmd.getS(0);
		if (bef==null) prex("Befehl fehlt. Rufe auf \"idl hilfe\" für hilfe",-1);
		conf=new Config(cmd);
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			ora=new SqConn("ora");
//			if (!ora.open("jdbc:oracle:thin:@inf-ssd-db-prod.spanset.local:1521:prod","idl2","idl2"))
			if (!ora.open(conf.dbConn))
				prex("Konnte Datenbankverbindung nicht öffnen",-2);
			switch(cmd.getS(0)) {
			case "hilfe": tuHilfe(); break;
			case "show": tuZeige(cmd); break;
			default: prex("Unbekannter Befehl; rufe auf \"idl hilfe\" für hilfe",-1);
			}
			ora.close();
		} catch(Exception e) {e.printStackTrace(); System.exit(-2);}

	}
	
	public static void pr(String s) {
		System.out.println(s);
	}
	
	public static void prex(String s,int erg) {
		pr(s);
		System.exit(erg);
	}
	
	public static String getas(Element x,String s) {
		String h=x.getAttributeValue(s);
		if (h==null) idl.prex("Attribut "+s+" nicht geunden.",-4);
		return h;		
	}

	public static int getai(Element x,String s) {
		return Integer.parseInt(getas(x,s));
	}

	public static String gets(Element x,String s) {
		String h=x.getChildText(s);
		if (h==null) idl.prex("Tag "+s+" nicht geunden.",-4);
		return h;		
	}

	public static int geti(Element x,String s) {
		return Integer.parseInt(gets(x,s));
		
	}
	
	public static Timestamp s2t(String s) {
		java.util.Date h=null;
		try {
			DateFormat df=new SimpleDateFormat("dd.MM.yyyy");
			h=df.parse(s);
		} catch(Exception e) {prex("Ungültiges Datumformat:"+s,-1);}
		Timestamp t=new Timestamp(h.getTime());
		return t;
	}
	
	public static void tuHilfe() {
		pr("Hilfe\n=========");
		pr("idl Befehl ; Befehl kann sein:\nhilfe\t\t\t\tDiese Hilfe anzeigen");
	}
	
	public static void tuZeige(CmdLine cmd) {
		String was=cmd.getS(1);
		if (was==null) prex("show braucht Info darüber, was zu zeigen ist.",-1);
		switch(was) {
		case "conf": zeigeConfig(); break;
		case "tabs": zeigeTabs(); break;
		case "tab": zeigeTab(cmd); break;
		default: prex("Was soll gezeigt werden ?",-1);
		}
	}
	
	public static void zeigeConfig() {
		pr("Konfiguration\n--------------------------------");
		pr("Configdatei    : "+conf.confDatei);
		pr("Datenbank      : "+conf.dbConn);
		pr("Zielverzeichnis: "+conf.zielVerz);
	}
	
	public static void zeigeTabs() {
		pr("Tabellen\n");
		pr("Id  Name\n--- ------------------------------------------");
		for (Tabelle k:conf.tabs) pr(k.showShort());
	}

	public static void zeigeTab(CmdLine cmd) {
		int id=cmd.geti("-id", -1);
		Tabelle t=conf.sucheTab(id);
		pr(t.showLong());
	}
}
