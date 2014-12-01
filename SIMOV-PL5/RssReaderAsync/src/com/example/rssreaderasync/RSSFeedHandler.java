package com.example.rssreaderasync;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

public class RSSFeedHandler {
	
	public RSSFeedHandler(){
		
	}
	public RSSFeed getRSSFeed(URL url){
		try
    	{
			// create the factory
	        SAXParserFactory factory = SAXParserFactory.newInstance();
	        // create a parser
	        SAXParser parser = factory.newSAXParser();
	        // create the reader (scanner)
	        XMLReader xmlreader = parser.getXMLReader();
	        // instantiate our handler
	        RSSHandler handler = new RSSHandler();
	        // assign our handler
	        xmlreader.setContentHandler(handler);
	        // get our data via the url class
	        InputSource is = new InputSource(url.openStream());
	        // perform the synchronous parse           
	        xmlreader.parse(is);
	        // get the results - should be a fully populated RSSFeed instance, or null on error
	        return handler.getFeed();
           
    	}
    	catch (Exception ee)
    	{
    		return null;
    	}
	}

}
