package com.example.convertisseur2;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String TAG = "Myapp";
    private String theNumber;
    private float theNumberToDouble;
    private float toDollar;
    private EditText myEuroNumber;
    private TextView myResultInDollar;
    private String currencyTXTinit;
    private String currencyTXTDest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "activity started");

        Button myButtonConvert = (Button) this.findViewById(R.id.button3);
        this.myEuroNumber = (EditText) this.findViewById(R.id.myEnterNumber);
        this.myResultInDollar = (TextView) this.findViewById(R.id.textView);

        // Spinners
        Spinner mySpinnerMoneyInit = (Spinner) this.findViewById(R.id.mySpinnerCurrencyInit);
        Spinner mySpinnerMoneyFinal = (Spinner) this.findViewById(R.id.mySpinnerCurrencyDestination);

        ArrayAdapter<CharSequence> adapterInit = ArrayAdapter.createFromResource(this, R.array.currenceChoice, android.R.layout.simple_spinner_item);
        adapterInit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinnerMoneyInit.setAdapter(adapterInit);
        mySpinnerMoneyInit.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapterFinal = ArrayAdapter.createFromResource(this, R.array.currencySelection, android.R.layout.simple_spinner_item);
        adapterInit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinnerMoneyFinal.setAdapter(adapterInit);
        mySpinnerMoneyFinal.setOnItemSelectedListener(this);

        // lorsqu'on appui sur le bouton
        myButtonConvert.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                Log.d(TAG, "button pressed");
                //TestBackground xmlBackground = new TestBackground();
               // xmlBackground.execute();

                myResultInDollar.setText(String.valueOf(toDollar));

                if (Objects.equals(currencyTXTinit, "Euro(EURO)")) { // euro devise de départ
                    if (Objects.equals(currencyTXTDest, "Dollar(USD)")) {
                      //  backgroundXML.doInBackground("fromEuro","toDollar");
                       // String rateFromXML = xmlBackground.doInBackground("euro","dollar");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        toDollar = (float) (theNumberToDouble * 0.98);
                        Log.d(TAG, "Convertion euro => dollar");
                        myResultInDollar.setText(String.valueOf(toDollar) + "$");
                    } else if (Objects.equals(currencyTXTDest, "Peso(MXN)")) {
                        //backgroundXML.doInBackground("fromEuro","toPeso");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        toDollar = (float) (theNumberToDouble * 147.87);
                        Log.d(TAG, "convertion euro => pesos");
                        myResultInDollar.setText(String.valueOf(toDollar) + "$Ar");
                    } else if (Objects.equals(currencyTXTDest, "Yen(JPY)")) {
                       // backgroundXML.doInBackground("fromEuro","toYen");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        toDollar = (float) (theNumberToDouble * 143.87);
                        Log.d(TAG, "button euro => Yen");
                        myResultInDollar.setText(String.valueOf(toDollar) + "¥");
                    } else {
                        myResultInDollar.setText("Vous avez choisit la même currency de destination");
                    }
                } else if (Objects.equals(currencyTXTinit, "Dollar(USD)")) { // dollar currency de départ
                    if (Objects.equals(currencyTXTDest, "Euro(EURO)")) {
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        toDollar = (float) (theNumberToDouble / 0.98);
                        Log.d(TAG, "Convertion dollar => euro");
                        myResultInDollar.setText(String.valueOf(toDollar) + "€");

                    } else if (Objects.equals(currencyTXTDest, "Peso(MXN)")) {
                       // backgroundXML.doInBackground("fromDollar","toPeso");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        toDollar = (float) (theNumberToDouble * 151.32);
                        Log.d(TAG, "convertion dollar => Peso(MXN)");
                        myResultInDollar.setText(String.valueOf(toDollar) + "$Ar");
                    } else if (Objects.equals(currencyTXTDest, "Yen")) {
                        //backgroundXML.doInBackground("fromDollar","toYen");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        toDollar = (float) (theNumberToDouble * 147.14);
                        Log.d(TAG, "conversion dollar => yen");
                        myResultInDollar.setText(String.valueOf(toDollar) + "¥");
                    } else {
                        myResultInDollar.setText("Vous avez choisit la même currency de destination");
                    }
                } else if (Objects.equals(currencyTXTinit, "Yen(JPY)")) { // Yen comme currency de départ
                    if (Objects.equals(currencyTXTDest, "Euro(EURO)")) {
                        //backgroundXML.doInBackground("fromYen","toEuro");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        toDollar = (float) (theNumberToDouble / 143.85);
                        Log.d(TAG, "Convertion yen => euro");
                        myResultInDollar.setText(String.valueOf(toDollar) + "€");
                    } else if (Objects.equals(currencyTXTDest, "Peso(MXN)")) {
                       // backgroundXML.doInBackground("fromYen","toPeso");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        toDollar = (float) (theNumberToDouble * 1.03);
                        Log.d(TAG, "convertion yen => pesos");
                        myResultInDollar.setText(String.valueOf(toDollar) + "$Ar");
                    } else if (Objects.equals(currencyTXTDest, "Dollar(USD)")) {
                       // backgroundXML.doInBackground("fromYen","toDollar");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        toDollar = (float) (theNumberToDouble / 147.14);
                        Log.d(TAG, "convertion yen => dollar");
                        myResultInDollar.setText(String.valueOf(toDollar) + "$");
                    } else {
                        myResultInDollar.setText("Vous avez choisit la même currency de destination");
                    }
                } else if (Objects.equals(currencyTXTinit, "Peso(MXN)")) {
                    if (Objects.equals(currencyTXTDest, "Euro(EURO)")) {
                      //  backgroundXML.doInBackground("fromPeso","toEuro");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        toDollar = (float) (theNumberToDouble * 0.0068);
                        Log.d(TAG, "Convertion pesos => euro");
                        myResultInDollar.setText(String.valueOf(toDollar) + "€");
                    } else if (Objects.equals(currencyTXTDest, "Yen(JPY)")) {
                       // backgroundXML.doInBackground("fromPeso","toYen");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        toDollar = (float) (theNumberToDouble * 0.97);
                        Log.d(TAG, "convertion pesos => Yen(JPY)");
                        myResultInDollar.setText(String.valueOf(toDollar) + "¥");
                    } else if (Objects.equals(currencyTXTDest, "Dollar(USD)")) {
                       // backgroundXML.doInBackground("fromPeso","toDollar");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        toDollar = (float) (theNumberToDouble * 0.0066);
                        Log.d(TAG, "convertion pesos => dollar");
                        myResultInDollar.setText(String.valueOf(toDollar) + "$");
                    } else {
                        myResultInDollar.setText("Vous avez choisit la même currency de destination");
                    }
                } else if (Objects.equals(currencyTXTinit, "Choose the currency") || Objects.equals(currencyTXTDest, "Select the final currency"))
                    myResultInDollar.setText("Faite une convertion");
            }
        });
    }

    // GERE CYCLE DE VIE MAIN ACTIVITY
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "activity destroyed");
    /*    if(backgroundXML != null){
            backgroundXML.cancel(true);
        }*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "activity resumed");
    }

    // app pause
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "activity on pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "activity stoped");
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getId() == R.id.mySpinnerCurrencyInit) { // select the init spinner
            currencyTXTinit = adapterView.getItemAtPosition(i).toString();
            //  Toast.makeText(adapterView.getContext(), currencyTXTinit, Toast.LENGTH_SHORT).show();
        } else if (adapterView.getId() == R.id.mySpinnerCurrencyDestination) { // select the destination spinner
            currencyTXTDest = adapterView.getItemAtPosition(i).toString();
            // Toast.makeText(adapterView.getContext(), currencyTXTDest, Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private class TestBackground extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            Log.d(TAG, "In the Background task to work on XML file");
            String rateFromXML = null;

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
            if(strings.length > 0){
                if (strings[0] == "dollar" || strings[1] == "dollar") {
                    rateFromXML = xmlCurrencyRate.get("USD");
                    Log.d(TAG,rateFromXML );
                } else if(strings[0] == "Yen(JPY)" || strings[1] == "Yen(JPY)"){
                    rateFromXML = xmlCurrencyRate.get("JPY");
                } else if (strings[0] == "Peso(MXN)" || strings[1] == "Peso(MXN)"){
                    rateFromXML = xmlCurrencyRate.get("MXN");
                }
            }
            return rateFromXML;
        }

        protected void onPostExecute(String aVoid) {
            Log.d(TAG,"test");
        }
    }
}

