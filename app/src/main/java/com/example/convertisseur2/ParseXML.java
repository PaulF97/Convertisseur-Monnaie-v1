package com.example.convertisseur2;

import android.os.AsyncTask;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class ParseXML extends AsyncTask< String,String, String> {


    protected String doInBackground(String... params) {
        String myInitParam, myDestParam, rateFromXML = null;
        myInitParam = params[0];
        myDestParam = params[1];

        HashMap<String, String> xmlCurrencyRate = new HashMap<String, String>();
        // parse XML files and put data in nodeLists
        DocumentBuilderFactory xmlFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder xmlBuilder = null;
        try {
            xmlBuilder = xmlFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document xmlFile = null;
        try {
            assert xmlBuilder != null;
            xmlFile = xmlBuilder.parse("https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        NodeList myCubes = xmlFile.getElementsByTagName("Cube"); // extract nodes "Cube"

        for (int i = 0; i < myCubes.getLength(); i++) {
            Node data = myCubes.item(i);
            Element currency = (Element) data;
            Element currencyValue = (Element) data;
            String currencyXML = currency.getAttribute("currency"); // get currency
            String currencyRate = currencyValue.getAttribute("rate"); // get rate
            xmlCurrencyRate.put(currencyXML, currencyRate); // put currency and rate in a hash map
        }
        if (params[0] == "dollar" || params[1] == "dollar") {
            rateFromXML = xmlCurrencyRate.get("USD");
        }
        return rateFromXML;
    }

    @Override
    protected void onPostExecute(String result) {
    }

    @Override
    protected void onPreExecute() {
    }

}


