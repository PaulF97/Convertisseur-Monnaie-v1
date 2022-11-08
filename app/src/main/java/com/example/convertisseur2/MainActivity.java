package com.example.convertisseur2;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
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
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

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
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
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
                TestBackground xmlBackground = new TestBackground();
                xmlBackground.execute();
                myResultInDollar.setText(String.valueOf(toDollar));
                if (Objects.equals(currencyTXTinit, "Euro(EURO)")) { // euro devise de départ
                    if (Objects.equals(currencyTXTDest, "Dollar(USD)")) {
                        String rateFromXML = xmlBackground.doInBackground("euro", "USD");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "Convertion euro => dollar");
                        myResultInDollar.setText(String.valueOf(toDollar) + "$");
                    } else if (Objects.equals(currencyTXTDest, "Peso(MXN)")) {
                        String rateFromXML = xmlBackground.doInBackground("fromEuro","MXN");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "convertion euro => MXN");
                        myResultInDollar.setText(String.valueOf(toDollar) + "MXN");
                    } else if (Objects.equals(currencyTXTDest, "Yen(JPY)")) {
                        String rateFromXML = xmlBackground.doInBackground("fromEuro","JPY");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => JPY");
                        myResultInDollar.setText(String.valueOf(toDollar) + "¥");
                    } else if(Objects.equals(currencyTXTDest, "Lev Bulgare(BGN)")) {
                        String rateFromXML = xmlBackground.doInBackground("fromEuro","BGN");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => Lev");
                        myResultInDollar.setText(String.valueOf(toDollar) + "BGN");
                    } else if(Objects.equals(currencyTXTDest, "Couronne tchèque(CZK)")) {
                        String rateFromXML = xmlBackground.doInBackground("fromEuro","CZK");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => CZK");
                        myResultInDollar.setText(String.valueOf(toDollar) + "CZK");
                    }else if(Objects.equals(currencyTXTDest, "Couronne danoise(DKK)")) {
                        String rateFromXML = xmlBackground.doInBackground("fromEuro","DKK");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => DKK");
                        myResultInDollar.setText(String.valueOf(toDollar) + "DKK");
                    }else if(Objects.equals(currencyTXTDest, "Livre sterling(GBP)")) {
                        String rateFromXML = xmlBackground.doInBackground("fromEuro","GBP");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => GBP");
                        myResultInDollar.setText(String.valueOf(toDollar) + "GBP");
                    }else if(Objects.equals(currencyTXTDest, "Forint(HUF)")) {
                        String rateFromXML = xmlBackground.doInBackground("fromEuro","HUF");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => HUF");
                        myResultInDollar.setText(String.valueOf(toDollar) + "HUF");
                    }else if(Objects.equals(currencyTXTDest, "złoty(PLN)")) {
                        String rateFromXML = xmlBackground.doInBackground("fromEuro","PLN");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => PLN");
                        myResultInDollar.setText(String.valueOf(toDollar) + "PLN");
                    }else if(Objects.equals(currencyTXTDest, "Leu roumain(RON)")) {
                        String rateFromXML = xmlBackground.doInBackground("fromEuro","RON");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => RON");
                        myResultInDollar.setText(String.valueOf(toDollar) + "RON");
                    }else if(Objects.equals(currencyTXTDest, "Couronne suédoise(SEK)")) {
                        String rateFromXML = xmlBackground.doInBackground("fromEuro","SEK");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => SEK");
                        myResultInDollar.setText(String.valueOf(toDollar) + "SEK");
                    }else if(Objects.equals(currencyTXTDest, "Franc suisse(CHF)")) {
                        String rateFromXML = xmlBackground.doInBackground("fromEuro","CHF");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => CHF");
                        myResultInDollar.setText(String.valueOf(toDollar) + "CHF");
                    }else if(Objects.equals(currencyTXTDest, "Couronne islandaise(ISK)")) {
                        String rateFromXML = xmlBackground.doInBackground("fromEuro","ISK");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => ISK");
                        myResultInDollar.setText(String.valueOf(toDollar) + "ISK");
                    }else if(Objects.equals(currencyTXTDest, "Couronne norvégienne(NOK)")) {
                        String rateFromXML = xmlBackground.doInBackground("fromEuro","NOK");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => NOK");
                        myResultInDollar.setText(String.valueOf(toDollar) + "NOK");
                    }else if(Objects.equals(currencyTXTDest, "Kuna croate(HRK)")) {
                        String rateFromXML = xmlBackground.doInBackground("fromEuro","HRK");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => HRK");
                        myResultInDollar.setText(String.valueOf(toDollar) + "HRK");
                    }else if(Objects.equals(currencyTXTDest, "Livre turque(TRY)")) {
                        String rateFromXML = xmlBackground.doInBackground("fromEuro","TRY");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => TRY");
                        myResultInDollar.setText(String.valueOf(toDollar) + "TRY");
                    }else if(Objects.equals(currencyTXTDest, "Dollar australien(AUD)")) {
                        String rateFromXML = xmlBackground.doInBackground("fromEuro","AUD");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => AUD");
                        myResultInDollar.setText(String.valueOf(toDollar) + "AUD");
                    }else if(Objects.equals(currencyTXTDest, "Réal brésilien(BRL)")) {
                        String rateFromXML = xmlBackground.doInBackground("fromEuro","BRL");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => BRL");
                        myResultInDollar.setText(String.valueOf(toDollar) + "BRL");
                    }else if(Objects.equals(currencyTXTDest, "Dollar canadien(CAD)")) {
                        String rateFromXML = xmlBackground.doInBackground("fromEuro","CAD");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => CAD");
                        myResultInDollar.setText(String.valueOf(toDollar) + "CAD");
                    }else if(Objects.equals(currencyTXTDest, "Renminbi(CNY)")) {
                        String rateFromXML = xmlBackground.doInBackground("fromEuro","CNY");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => CNY");
                        myResultInDollar.setText(String.valueOf(toDollar) + "CNY");
                    }else if(Objects.equals(currencyTXTDest, "Renminbi(HKD)")) {
                        String rateFromXML = xmlBackground.doInBackground("fromEuro","HKD");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => HKD");
                        myResultInDollar.setText(String.valueOf(toDollar) + "HKD");
                    }else if(Objects.equals(currencyTXTDest, "Roupie indonésienne(IDR)")) {
                        String rateFromXML = xmlBackground.doInBackground("fromEuro","IDR");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => IDR");
                        myResultInDollar.setText(String.valueOf(toDollar) + "IDR");
                    }else if(Objects.equals(currencyTXTDest, "Shekel(ILS)")) {
                        String rateFromXML = xmlBackground.doInBackground("fromEuro","ILS");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => ILS");
                        myResultInDollar.setText(String.valueOf(toDollar) + "ILS");
                    }else if(Objects.equals(currencyTXTDest, "Roupie indienne(INR)")) {
                        String rateFromXML = xmlBackground.doInBackground("fromEuro","INR");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => INR");
                        myResultInDollar.setText(String.valueOf(toDollar) + "INR");
                    }else if(Objects.equals(currencyTXTDest, "Won sud-coréen(KRW)")) {
                        String rateFromXML = xmlBackground.doInBackground("fromEuro","KRW");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => KRW");
                        myResultInDollar.setText(String.valueOf(toDollar) + "KRW");
                    }else if(Objects.equals(currencyTXTDest, "Ringgit(MYR)")) {
                        String rateFromXML = xmlBackground.doInBackground("fromEuro","MYR");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => MYR");
                        myResultInDollar.setText(String.valueOf(toDollar) + "MYR");
                    }else if(Objects.equals(currencyTXTDest, "Dollar néo-zélandais(NZD)")) {
                        String rateFromXML = xmlBackground.doInBackground("fromEuro","NZD");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => NZD");
                        myResultInDollar.setText(String.valueOf(toDollar) + "NZD");
                    }else if(Objects.equals(currencyTXTDest, "Peso philippin(PHP)")) {
                        String rateFromXML = xmlBackground.doInBackground("fromEuro","PHP");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => PHP");
                        myResultInDollar.setText(String.valueOf(toDollar) + "PHP");
                    }else if(Objects.equals(currencyTXTDest, "Dollar de Singapour(SGD)")) {
                        String rateFromXML = xmlBackground.doInBackground("fromEuro","SGD");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => SGD");
                        myResultInDollar.setText(String.valueOf(toDollar) + "SGD");
                    }else if(Objects.equals(currencyTXTDest, "Baht(THB)")) {
                        String rateFromXML = xmlBackground.doInBackground("fromEuro","THB");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => THB");
                        myResultInDollar.setText(String.valueOf(toDollar) + "THB");
                    }else if(Objects.equals(currencyTXTDest, "Rand(ZAR)")) {
                        String rateFromXML = xmlBackground.doInBackground("fromEuro","ZAR");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => ZAR");
                        myResultInDollar.setText(String.valueOf(toDollar) + "ZAR");
                    }
                }
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
        Log.d(TAG,"no spinner selected");
    }

    private class TestBackground extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            String rateFromXML = null;
            try {
                Log.d(TAG, "In the Background task to work on XML file");
                HashMap<String, String> xmlCurrencyRate = new HashMap<String, String>();
                // parse XML files and put data in nodeLists
                DocumentBuilderFactory xmlFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder xmlBuilder = null;
                xmlBuilder = xmlFactory.newDocumentBuilder();
                assert xmlBuilder != null;
                Document xmlFile = xmlBuilder.parse("https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml");
                NodeList myCubes = xmlFile.getElementsByTagName("Cube"); // extract nodes "Cube"
                for (int i = 0; i < myCubes.getLength(); i++) {
                    Node data = myCubes.item(i);
                    Element currency = (Element) data;
                    Element currencyValue = (Element) data;
                    String currencyXML = currency.getAttribute("currency"); // get currency
                    String currencyRate = currencyValue.getAttribute("rate"); // get rate
                    xmlCurrencyRate.put(currencyXML, currencyRate); // put currency and rate in a hash map
                }
                if (strings.length > 0) {
                    if (strings[0] == "USD" || strings[1] == "USD") {
                        rateFromXML = xmlCurrencyRate.get("USD");
                        Log.d(TAG, rateFromXML);
                    } else if (strings[0] == "MXN" || strings[1] == "MXN") {
                        rateFromXML = xmlCurrencyRate.get("MXN");
                    } else if (strings[0] == "JPY" || strings[1] == "JPY") {
                        rateFromXML = xmlCurrencyRate.get("JPY");
                    } else if (strings[0] == "BGN" || strings[1] == "BGN") {
                        rateFromXML = xmlCurrencyRate.get("BGN");
                    } else if (strings[0] == "CZK" || strings[1] == "CZK") {
                        rateFromXML = xmlCurrencyRate.get("CZK");
                    } else if (strings[0] == "DKK" || strings[1] == "DKK") {
                        rateFromXML = xmlCurrencyRate.get("DKK");
                    } else if (strings[0] == "GBP" || strings[1] == "GBP") {
                        rateFromXML = xmlCurrencyRate.get("GBP");
                    } else if (strings[0] == "HUF" || strings[1] == "HUF") {
                        rateFromXML = xmlCurrencyRate.get("HUF");
                    } else if (strings[0] == "PLN" || strings[1] == "PLN") {
                        rateFromXML = xmlCurrencyRate.get("PLN");
                    } else if (strings[0] == "RON" || strings[1] == "RON") {
                        rateFromXML = xmlCurrencyRate.get("RON");
                    } else if (strings[0] == "CHF" || strings[1] == "CHF") {
                        rateFromXML = xmlCurrencyRate.get("CHF");
                    } else if (strings[0] == "ISK" || strings[1] == "ISK") {
                        rateFromXML = xmlCurrencyRate.get("ISK");
                    } else if (strings[0] == "NOK" || strings[1] == "NOK") {
                        rateFromXML = xmlCurrencyRate.get("NOK");
                    } else if (strings[0] == "HRK" || strings[1] == "HRK") {
                        rateFromXML = xmlCurrencyRate.get("HRK");
                    } else if (strings[0] == "TRY" || strings[1] == "TRY") {
                        rateFromXML = xmlCurrencyRate.get("TRY");
                    } else if (strings[0] == "AUD" || strings[1] == "AUD") {
                        rateFromXML = xmlCurrencyRate.get("AUD");
                    } else if (strings[0] == "BRL" || strings[1] == "BRL") {
                        rateFromXML = xmlCurrencyRate.get("BRL");
                    } else if (strings[0] == "CAD" || strings[1] == "CAD") {
                        rateFromXML = xmlCurrencyRate.get("CAD");
                    } else if (strings[0] == "CNY" || strings[1] == "CNY") {
                        rateFromXML = xmlCurrencyRate.get("CNY");
                    } else if (strings[0] == "HKD" || strings[1] == "HKD") {
                        rateFromXML = xmlCurrencyRate.get("HKD");
                    } else if (strings[0] == "IDR" || strings[1] == "IDR") {
                        rateFromXML = xmlCurrencyRate.get("IDR");
                    } else if (strings[0] == "ILS" || strings[1] == "ILS") {
                        rateFromXML = xmlCurrencyRate.get("ILS");
                    } else if (strings[0] == "INR" || strings[1] == "INR") {
                        rateFromXML = xmlCurrencyRate.get("INR");
                    } else if (strings[0] == "KRW" || strings[1] == "KRW") {
                        rateFromXML = xmlCurrencyRate.get("KRW");
                    } else if (strings[0] == "MYR" || strings[1] == "MYR") {
                        rateFromXML = xmlCurrencyRate.get("MYR");
                    } else if (strings[0] == "NZD" || strings[1] == "NZD") {
                        rateFromXML = xmlCurrencyRate.get("NZD");
                    } else if (strings[0] == "PHP" || strings[1] == "PHP") {
                        rateFromXML = xmlCurrencyRate.get("PHP");
                    } else if (strings[0] == "SGD" || strings[1] == "SGD") {
                        rateFromXML = xmlCurrencyRate.get("SGD");
                    } else if (strings[0] == "THB" || strings[1] == "THB") {
                        rateFromXML = xmlCurrencyRate.get("THB");
                    } else if (strings[0] == "ZAR" || strings[1] == "ZAR") {
                        rateFromXML = xmlCurrencyRate.get("ZAR");
                    } else if (strings[0] == "SEK" || strings[1] == "SEK") {
                        rateFromXML = xmlCurrencyRate.get("SEK");
                    }
                }

            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }
            return rateFromXML;
        }
    }
}



