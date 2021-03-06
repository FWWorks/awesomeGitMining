package org.Client.ui.utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class SkinConfig {

	private int currentSkinNum;
	private final String[] skinXmlPathList = {"src/main/java/org/Client/ui/Yellow.xml","src/main/java/org/Client/ui/Pink.xml","src/main/java/org/Client/ui/Dark.xml"};

	private static SkinConfig instance;

	private SkinConfig() {
		readNumFromXml();
	}

	public static SkinConfig getInstance(){
		return instance==null?new SkinConfig():instance;
	}

	public String getFxmlResoursePath(String key){
		SAXReader reader = new SAXReader();
		String name ="";
		try {
			Document d = reader.read(new File(skinXmlPathList[currentSkinNum]));
			Element root = d.getRootElement();

			name = root.element(key).getText();


		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return name;
	}

	public String getCssResoursePath(){
		SAXReader reader = new SAXReader();
		String path = "file:src/main/java/org/Client/ui/style/";
		try {
			Document d = reader.read(new File(skinXmlPathList[currentSkinNum]));

			Element root = d.getRootElement();

			path += root.element("css").getText();


		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(path);
		}
		return path;
	}


	public int getSkinNum(){
		return currentSkinNum;
	}

	public void setSkinNum(int skin) {
		currentSkinNum = skin;

		Document d = DocumentHelper.createDocument();
		Element root = d.addElement("skin");
		root.addText(currentSkinNum+"");

		try {
			XMLWriter out = new XMLWriter(new FileWriter(new File("src/main/java/org/Client/ui/SkinConfig.xml")));
			out.write(d);
			out.flush();
	//		out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void readNumFromXml(){
		SAXReader reader = new SAXReader();
		try {
			Document d = reader.read(new File("src/main/java/org/Client/ui/SkinConfig.xml"));

			Element root = d.getRootElement();

			currentSkinNum = Integer.parseInt(root.getText());


		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void readSkinFromXml(){}

	public static void main(String[] args) {
		SkinConfig.getInstance().setSkinNum(1);;
	}
}
