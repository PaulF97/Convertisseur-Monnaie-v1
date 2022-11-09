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
                if (Objects.equals(currencyTXTinit, "Euro(EURO)")) { // conversion euro en devise du fichier xml
                    if (Objects.equals(currencyTXTDest, "Dollar(USD)")) {
                        String rateFromXML = xmlBackground.doInBackground("euro", "USD");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "Convertion euro => dollar");
                        myResultInDollar.setText(String.valueOf(toDollar) + "$");
                    } else if (Objects.equals(currencyTXTDest, "Peso(MXN)")) {
                        String rateFromXML = xmlBackground.doInBackground("euro","MXN");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "convertion euro => MXN");
                        myResultInDollar.setText(String.valueOf(toDollar) + "MXN");
                    } else if (Objects.equals(currencyTXTDest, "Yen(JPY)")) {
                        String rateFromXML = xmlBackground.doInBackground("euro","JPY");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => JPY");
                        myResultInDollar.setText(String.valueOf(toDollar) + "¥");
                    } else if(Objects.equals(currencyTXTDest, "Lev Bulgare(BGN)")) {
                        String rateFromXML = xmlBackground.doInBackground("euro","BGN");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => Lev");
                        myResultInDollar.setText(String.valueOf(toDollar) + "BGN");
                    } else if(Objects.equals(currencyTXTDest, "Couronne tchèque(CZK)")) {
                        String rateFromXML = xmlBackground.doInBackground("euro","CZK");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => CZK");
                        myResultInDollar.setText(String.valueOf(toDollar) + "CZK");
                    }else if(Objects.equals(currencyTXTDest, "Couronne danoise(DKK)")) {
                        String rateFromXML = xmlBackground.doInBackground("euro","DKK");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => DKK");
                        myResultInDollar.setText(String.valueOf(toDollar) + "DKK");
                    }else if(Objects.equals(currencyTXTDest, "Livre sterling(GBP)")) {
                        String rateFromXML = xmlBackground.doInBackground("euro","GBP");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => GBP");
                        myResultInDollar.setText(String.valueOf(toDollar) + "GBP");
                    }else if(Objects.equals(currencyTXTDest, "Forint(HUF)")) {
                        String rateFromXML = xmlBackground.doInBackground("euro","HUF");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => HUF");
                        myResultInDollar.setText(String.valueOf(toDollar) + "HUF");
                    }else if(Objects.equals(currencyTXTDest, "złoty(PLN)")) {
                        String rateFromXML = xmlBackground.doInBackground("euro","PLN");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => PLN");
                        myResultInDollar.setText(String.valueOf(toDollar) + "PLN");
                    }else if(Objects.equals(currencyTXTDest, "Leu roumain(RON)")) {
                        String rateFromXML = xmlBackground.doInBackground("euro","RON");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => RON");
                        myResultInDollar.setText(String.valueOf(toDollar) + "RON");
                    }else if(Objects.equals(currencyTXTDest, "Couronne suédoise(SEK)")) {
                        String rateFromXML = xmlBackground.doInBackground("euro","SEK");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => SEK");
                        myResultInDollar.setText(String.valueOf(toDollar) + "SEK");
                    }else if(Objects.equals(currencyTXTDest, "Franc suisse(CHF)")) {
                        String rateFromXML = xmlBackground.doInBackground("euro","CHF");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => CHF");
                        myResultInDollar.setText(String.valueOf(toDollar) + "CHF");
                    }else if(Objects.equals(currencyTXTDest, "Couronne islandaise(ISK)")) {
                        String rateFromXML = xmlBackground.doInBackground("euro","ISK");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => ISK");
                        myResultInDollar.setText(String.valueOf(toDollar) + "ISK");
                    }else if(Objects.equals(currencyTXTDest, "Couronne norvégienne(NOK)")) {
                        String rateFromXML = xmlBackground.doInBackground("euro","NOK");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => NOK");
                        myResultInDollar.setText(String.valueOf(toDollar) + "NOK");
                    }else if(Objects.equals(currencyTXTDest, "Kuna croate(HRK)")) {
                        String rateFromXML = xmlBackground.doInBackground("euro","HRK");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => HRK");
                        myResultInDollar.setText(String.valueOf(toDollar) + "HRK");
                    }else if(Objects.equals(currencyTXTDest, "Livre turque(TRY)")) {
                        String rateFromXML = xmlBackground.doInBackground("euro","TRY");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => TRY");
                        myResultInDollar.setText(String.valueOf(toDollar) + "TRY");
                    }else if(Objects.equals(currencyTXTDest, "Dollar australien(AUD)")) {
                        String rateFromXML = xmlBackground.doInBackground("euro","AUD");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => AUD");
                        myResultInDollar.setText(String.valueOf(toDollar) + "AUD");
                    }else if(Objects.equals(currencyTXTDest, "Réal brésilien(BRL)")) {
                        String rateFromXML = xmlBackground.doInBackground("euro","BRL");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => BRL");
                        myResultInDollar.setText(String.valueOf(toDollar) + "BRL");
                    }else if(Objects.equals(currencyTXTDest, "Dollar canadien(CAD)")) {
                        String rateFromXML = xmlBackground.doInBackground("euro","CAD");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => CAD");
                        myResultInDollar.setText(String.valueOf(toDollar) + "CAD");
                    }else if(Objects.equals(currencyTXTDest, "Renminbi(CNY)")) {
                        String rateFromXML = xmlBackground.doInBackground("euro","CNY");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => CNY");
                        myResultInDollar.setText(String.valueOf(toDollar) + "CNY");
                    }else if(Objects.equals(currencyTXTDest, "Renminbi(HKD)")) {
                        String rateFromXML = xmlBackground.doInBackground("euro","HKD");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => HKD");
                        myResultInDollar.setText(String.valueOf(toDollar) + "HKD");
                    }else if(Objects.equals(currencyTXTDest, "Roupie indonésienne(IDR)")) {
                        String rateFromXML = xmlBackground.doInBackground("euro","IDR");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => IDR");
                        myResultInDollar.setText(String.valueOf(toDollar) + "IDR");
                    }else if(Objects.equals(currencyTXTDest, "Shekel(ILS)")) {
                        String rateFromXML = xmlBackground.doInBackground("euro","ILS");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => ILS");
                        myResultInDollar.setText(String.valueOf(toDollar) + "ILS");
                    }else if(Objects.equals(currencyTXTDest, "Roupie indienne(INR)")) {
                        String rateFromXML = xmlBackground.doInBackground("euro","INR");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => INR");
                        myResultInDollar.setText(String.valueOf(toDollar) + "INR");
                    }else if(Objects.equals(currencyTXTDest, "Won sud-coréen(KRW)")) {
                        String rateFromXML = xmlBackground.doInBackground("euro","KRW");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => KRW");
                        myResultInDollar.setText(String.valueOf(toDollar) + "KRW");
                    }else if(Objects.equals(currencyTXTDest, "Ringgit(MYR)")) {
                        String rateFromXML = xmlBackground.doInBackground("euro","MYR");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => MYR");
                        myResultInDollar.setText(String.valueOf(toDollar) + "MYR");
                    }else if(Objects.equals(currencyTXTDest, "Dollar néo-zélandais(NZD)")) {
                        String rateFromXML = xmlBackground.doInBackground("euro","NZD");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => NZD");
                        myResultInDollar.setText(String.valueOf(toDollar) + "NZD");
                    }else if(Objects.equals(currencyTXTDest, "Peso philippin(PHP)")) {
                        String rateFromXML = xmlBackground.doInBackground("euro","PHP");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => PHP");
                        myResultInDollar.setText(String.valueOf(toDollar) + "PHP");
                    }else if(Objects.equals(currencyTXTDest, "Dollar de Singapour(SGD)")) {
                        String rateFromXML = xmlBackground.doInBackground("euro","SGD");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => SGD");
                        myResultInDollar.setText(String.valueOf(toDollar) + "SGD");
                    }else if(Objects.equals(currencyTXTDest, "Baht(THB)")) {
                        String rateFromXML = xmlBackground.doInBackground("euro","THB");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => THB");
                        myResultInDollar.setText(String.valueOf(toDollar) + "THB");
                    }else if(Objects.equals(currencyTXTDest, "Rand(ZAR)")) {
                        String rateFromXML = xmlBackground.doInBackground("euro","ZAR");
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                        toDollar = (float) (theNumberToDouble * xmlToFloat);
                        Log.d(TAG, "button euro => ZAR");
                        myResultInDollar.setText(String.valueOf(toDollar) + "ZAR");
                    }
                } else if(Objects.equals(currencyTXTinit, "Dollar(USD)")){ // conversion from xml currencys to euro
                    String rateFromXML = xmlBackground.doInBackground("USD", "euro");
                    theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                    theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                    Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                    toDollar = (float) (theNumberToDouble / xmlToFloat);
                    Log.d(TAG, "Convertion dollar => euro");
                    myResultInDollar.setText(String.valueOf(toDollar) + "EURO");
                } else if (Objects.equals(currencyTXTinit, "Peso(MXN)")){
                    String rateFromXML = xmlBackground.doInBackground("MXN", "euro");
                    theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                    theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                    Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                    toDollar = (float) (theNumberToDouble / xmlToFloat);
                    Log.d(TAG, "Convertion Peso => euro");
                    myResultInDollar.setText(String.valueOf(toDollar) + "EURO");
                } else if (Objects.equals(currencyTXTinit, "Yen(JPY)")){
                    String rateFromXML = xmlBackground.doInBackground("JPY", "euro");
                    theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                    theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                    Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                    toDollar = (float) (theNumberToDouble / xmlToFloat);
                    Log.d(TAG, "Convertion JPY => euro");
                    myResultInDollar.setText(String.valueOf(toDollar) + "EURO");
                } else if (Objects.equals(currencyTXTinit, "Lev Bulgare(BGN)")){
                    String rateFromXML = xmlBackground.doInBackground("BGN", "euro");
                    theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                    theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                    Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                    toDollar = (float) (theNumberToDouble / xmlToFloat);
                    Log.d(TAG, "Convertion BGN => euro");
                    myResultInDollar.setText(String.valueOf(toDollar) + "EURO");
                } else if (Objects.equals(currencyTXTinit, "Couronne tchèque(CZK)")){
                    String rateFromXML = xmlBackground.doInBackground("CZK", "euro");
                    theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                    theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                    Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                    toDollar = (float) (theNumberToDouble / xmlToFloat);
                    Log.d(TAG, "Convertion CZK => euro");
                    myResultInDollar.setText(String.valueOf(toDollar) + "EURO");
                } else if (Objects.equals(currencyTXTinit, "Couronne danoise(DKK)")){
                    String rateFromXML = xmlBackground.doInBackground("DKK", "euro");
                    theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                    theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                    Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                    toDollar = (float) (theNumberToDouble / xmlToFloat);
                    Log.d(TAG, "Convertion DKK => euro");
                    myResultInDollar.setText(String.valueOf(toDollar) + "EURO");
                } else if (Objects.equals(currencyTXTinit, "Livre sterling(GBP)")){
                    String rateFromXML = xmlBackground.doInBackground("GBP", "euro");
                    theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                    theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                    Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                    toDollar = (float) (theNumberToDouble / xmlToFloat);
                    Log.d(TAG, "Convertion GBP => euro");
                    myResultInDollar.setText(String.valueOf(toDollar) + "EURO");
                } else if (Objects.equals(currencyTXTinit, "Forint(HUF)")){
                    String rateFromXML = xmlBackground.doInBackground("HUF", "euro");
                    theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                    theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                    Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                    toDollar = (float) (theNumberToDouble / xmlToFloat);
                    Log.d(TAG, "Convertion HUF => euro");
                    myResultInDollar.setText(String.valueOf(toDollar) + "EURO");
                } else if (Objects.equals(currencyTXTinit, "złoty(PLN)")){
                    String rateFromXML = xmlBackground.doInBackground("PLN", "euro");
                    theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                    theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                    Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                    toDollar = (float) (theNumberToDouble / xmlToFloat);
                    Log.d(TAG, "Convertion PLN => euro");
                    myResultInDollar.setText(String.valueOf(toDollar) + "EURO");
                } else if (Objects.equals(currencyTXTinit, "Leu roumain(RON)")){
                    String rateFromXML = xmlBackground.doInBackground("RON", "euro");
                    theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                    theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                    Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                    toDollar = (float) (theNumberToDouble / xmlToFloat);
                    Log.d(TAG, "Convertion RON => euro");
                    myResultInDollar.setText(String.valueOf(toDollar) + "EURO");
                } else if (Objects.equals(currencyTXTinit, "Couronne suédoise(SEK)")){
                    String rateFromXML = xmlBackground.doInBackground("SEK", "euro");
                    theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                    theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                    Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                    toDollar = (float) (theNumberToDouble / xmlToFloat);
                    Log.d(TAG, "Convertion SEK => euro");
                    myResultInDollar.setText(String.valueOf(toDollar) + "EURO");
                } else if (Objects.equals(currencyTXTinit, "Franc suisse(CHF)")){
                    String rateFromXML = xmlBackground.doInBackground("CHF", "euro");
                    theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                    theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                    Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                    toDollar = (float) (theNumberToDouble / xmlToFloat);
                    Log.d(TAG, "Convertion CHF => euro");
                    myResultInDollar.setText(String.valueOf(toDollar) + "EURO");
                } else if (Objects.equals(currencyTXTinit, "Couronne islandaise(ISK)")){
                    String rateFromXML = xmlBackground.doInBackground("ISK", "euro");
                    theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                    theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                    Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                    toDollar = (float) (theNumberToDouble / xmlToFloat);
                    Log.d(TAG, "Convertion ISK => euro");
                    myResultInDollar.setText(String.valueOf(toDollar) + "EURO");
                } else if (Objects.equals(currencyTXTinit, "Couronne norvégienne(NOK)")){
                    String rateFromXML = xmlBackground.doInBackground("NOK", "euro");
                    theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                    theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                    Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                    toDollar = (float) (theNumberToDouble / xmlToFloat);
                    Log.d(TAG, "Convertion NOK => euro");
                    myResultInDollar.setText(String.valueOf(toDollar) + "EURO");
                } else if (Objects.equals(currencyTXTinit, "Kuna croate(HRK)")){
                    String rateFromXML = xmlBackground.doInBackground("HRK", "euro");
                    theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                    theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                    Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                    toDollar = (float) (theNumberToDouble / xmlToFloat);
                    Log.d(TAG, "Convertion HRK => euro");
                    myResultInDollar.setText(String.valueOf(toDollar) + "EURO");
                } else if (Objects.equals(currencyTXTinit, "Livre turque(TRY)")){
                    String rateFromXML = xmlBackground.doInBackground("TRY", "euro");
                    theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                    theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                    Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                    toDollar = (float) (theNumberToDouble / xmlToFloat);
                    Log.d(TAG, "Convertion TRY => euro");
                    myResultInDollar.setText(String.valueOf(toDollar) + "EURO");
                } else if (Objects.equals(currencyTXTinit, "Dollar australien(AUD)")){
                    String rateFromXML = xmlBackground.doInBackground("AUD", "euro");
                    theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                    theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                    Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                    toDollar = (float) (theNumberToDouble / xmlToFloat);
                    Log.d(TAG, "Convertion AUD => euro");
                    myResultInDollar.setText(String.valueOf(toDollar) + "EURO");
                } else if (Objects.equals(currencyTXTinit, "Réal brésilien(BRL)")){
                    String rateFromXML = xmlBackground.doInBackground("BRL", "euro");
                    theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                    theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                    Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                    toDollar = (float) (theNumberToDouble / xmlToFloat);
                    Log.d(TAG, "Convertion BRL => euro");
                    myResultInDollar.setText(String.valueOf(toDollar) + "EURO");
                } else if (Objects.equals(currencyTXTinit, "Dollar canadien(CAD)")){
                    String rateFromXML = xmlBackground.doInBackground("CAD", "euro");
                    theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                    theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                    Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                    toDollar = (float) (theNumberToDouble / xmlToFloat);
                    Log.d(TAG, "Convertion CAD => euro");
                    myResultInDollar.setText(String.valueOf(toDollar) + "EURO");
                } else if (Objects.equals(currencyTXTinit, "Renminbi(CNY)")){
                    String rateFromXML = xmlBackground.doInBackground("CNY", "euro");
                    theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                    theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                    Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                    toDollar = (float) (theNumberToDouble / xmlToFloat);
                    Log.d(TAG, "Convertion CNY => euro");
                    myResultInDollar.setText(String.valueOf(toDollar) + "EURO");
                } else if (Objects.equals(currencyTXTinit, "Roupie indonésienne(IDR)")){
                    String rateFromXML = xmlBackground.doInBackground("IDR", "euro");
                    theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                    theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                    Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                    toDollar = (float) (theNumberToDouble / xmlToFloat);
                    Log.d(TAG, "Convertion IDR => euro");
                    myResultInDollar.setText(String.valueOf(toDollar) + "EURO");
                } else if (Objects.equals(currencyTXTinit, "Shekel(ILS)")){
                    String rateFromXML = xmlBackground.doInBackground("ILS", "euro");
                    theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                    theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                    Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                    toDollar = (float) (theNumberToDouble / xmlToFloat);
                    Log.d(TAG, "Convertion ILS => euro");
                    myResultInDollar.setText(String.valueOf(toDollar) + "EURO");
                } else if (Objects.equals(currencyTXTinit, "Roupie indienne(INR)")){
                    String rateFromXML = xmlBackground.doInBackground("INR", "euro");
                    theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                    theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                    Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                    toDollar = (float) (theNumberToDouble / xmlToFloat);
                    Log.d(TAG, "Convertion INR => euro");
                    myResultInDollar.setText(String.valueOf(toDollar) + "EURO");
                } else if (Objects.equals(currencyTXTinit, "Won sud-coréen(KRW)")){
                    String rateFromXML = xmlBackground.doInBackground("KRW", "euro");
                    theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                    theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                    Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                    toDollar = (float) (theNumberToDouble / xmlToFloat);
                    Log.d(TAG, "Convertion KRW => euro");
                    myResultInDollar.setText(String.valueOf(toDollar) + "EURO");
                } else if (Objects.equals(currencyTXTinit, "Ringgit(MYR)")){
                    String rateFromXML = xmlBackground.doInBackground("MYR", "euro");
                    theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                    theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                    Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                    toDollar = (float) (theNumberToDouble / xmlToFloat);
                    Log.d(TAG, "Convertion MYR => euro");
                    myResultInDollar.setText(String.valueOf(toDollar) + "EURO");
                } else if (Objects.equals(currencyTXTinit, "Dollar néo-zélandais(NZD)")){
                    String rateFromXML = xmlBackground.doInBackground("NZD", "euro");
                    theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                    theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                    Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                    toDollar = (float) (theNumberToDouble / xmlToFloat);
                    Log.d(TAG, "Convertion NZD => euro");
                    myResultInDollar.setText(String.valueOf(toDollar) + "EURO");
                } else if (Objects.equals(currencyTXTinit, "Peso philippin(PHP)")){
                    String rateFromXML = xmlBackground.doInBackground("PHP", "euro");
                    theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                    theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                    Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                    toDollar = (float) (theNumberToDouble / xmlToFloat);
                    Log.d(TAG, "Convertion PHP => euro");
                    myResultInDollar.setText(String.valueOf(toDollar) + "EURO");
                } else if (Objects.equals(currencyTXTinit, "Dollar de Singapour(SGD)")){
                    String rateFromXML = xmlBackground.doInBackground("SGD", "euro");
                    theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                    theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                    Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                    toDollar = (float) (theNumberToDouble / xmlToFloat);
                    Log.d(TAG, "Convertion SGD => euro");
                    myResultInDollar.setText(String.valueOf(toDollar) + "EURO");
                } else if (Objects.equals(currencyTXTinit, "Baht(THB)")){
                    String rateFromXML = xmlBackground.doInBackground("THB", "euro");
                    theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                    theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                    Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                    toDollar = (float) (theNumberToDouble / xmlToFloat);
                    Log.d(TAG, "Convertion THB => euro");
                    myResultInDollar.setText(String.valueOf(toDollar) + "EURO");
                } else if (Objects.equals(currencyTXTinit, "Rand(ZAR)")){
                    String rateFromXML = xmlBackground.doInBackground("ZAR", "euro");
                    theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                    theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                    Float xmlToFloat = Float.parseFloat(rateFromXML);// convert to double
                    toDollar = (float) (theNumberToDouble / xmlToFloat);
                    Log.d(TAG, "Convertion ZAR => euro");
                    myResultInDollar.setText(String.valueOf(toDollar) + "EURO");
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
                        Log.d(TAG, rateFromXML);
                    } else if (strings[0] == "JPY" || strings[1] == "JPY") {
                        rateFromXML = xmlCurrencyRate.get("JPY");
                        Log.d(TAG, rateFromXML);
                    } else if (strings[0] == "BGN" || strings[1] == "BGN") {
                        rateFromXML = xmlCurrencyRate.get("BGN");
                        Log.d(TAG, rateFromXML);
                    } else if (strings[0] == "CZK" || strings[1] == "CZK") {
                        rateFromXML = xmlCurrencyRate.get("CZK");
                        Log.d(TAG, rateFromXML);
                    } else if (strings[0] == "DKK" || strings[1] == "DKK") {
                        rateFromXML = xmlCurrencyRate.get("DKK");
                        Log.d(TAG, rateFromXML);
                    } else if (strings[0] == "GBP" || strings[1] == "GBP") {
                        rateFromXML = xmlCurrencyRate.get("GBP");
                        Log.d(TAG, rateFromXML);
                    } else if (strings[0] == "HUF" || strings[1] == "HUF") {
                        rateFromXML = xmlCurrencyRate.get("HUF");
                        Log.d(TAG, rateFromXML);
                    } else if (strings[0] == "PLN" || strings[1] == "PLN") {
                        rateFromXML = xmlCurrencyRate.get("PLN");
                        Log.d(TAG, rateFromXML);
                    } else if (strings[0] == "RON" || strings[1] == "RON") {
                        rateFromXML = xmlCurrencyRate.get("RON");
                        Log.d(TAG, rateFromXML);
                    } else if (strings[0] == "CHF" || strings[1] == "CHF") {
                        rateFromXML = xmlCurrencyRate.get("CHF");
                        Log.d(TAG, rateFromXML);
                    } else if (strings[0] == "ISK" || strings[1] == "ISK") {
                        rateFromXML = xmlCurrencyRate.get("ISK");
                        Log.d(TAG, rateFromXML);
                    } else if (strings[0] == "NOK" || strings[1] == "NOK") {
                        rateFromXML = xmlCurrencyRate.get("NOK");
                        Log.d(TAG, rateFromXML);
                    } else if (strings[0] == "HRK" || strings[1] == "HRK") {
                        rateFromXML = xmlCurrencyRate.get("HRK");
                        Log.d(TAG, rateFromXML);
                    } else if (strings[0] == "TRY" || strings[1] == "TRY") {
                        rateFromXML = xmlCurrencyRate.get("TRY");
                        Log.d(TAG, rateFromXML);
                    } else if (strings[0] == "AUD" || strings[1] == "AUD") {
                        rateFromXML = xmlCurrencyRate.get("AUD");
                        Log.d(TAG, rateFromXML);
                    } else if (strings[0] == "BRL" || strings[1] == "BRL") {
                        rateFromXML = xmlCurrencyRate.get("BRL");
                        Log.d(TAG, rateFromXML);
                    } else if (strings[0] == "CAD" || strings[1] == "CAD") {
                        rateFromXML = xmlCurrencyRate.get("CAD");
                        Log.d(TAG, rateFromXML);
                    } else if (strings[0] == "CNY" || strings[1] == "CNY") {
                        rateFromXML = xmlCurrencyRate.get("CNY");
                        Log.d(TAG, rateFromXML);
                    } else if (strings[0] == "HKD" || strings[1] == "HKD") {
                        rateFromXML = xmlCurrencyRate.get("HKD");
                        Log.d(TAG, rateFromXML);
                    } else if (strings[0] == "IDR" || strings[1] == "IDR") {
                        rateFromXML = xmlCurrencyRate.get("IDR");
                        Log.d(TAG, rateFromXML);
                    } else if (strings[0] == "ILS" || strings[1] == "ILS") {
                        rateFromXML = xmlCurrencyRate.get("ILS");
                        Log.d(TAG, rateFromXML);
                    } else if (strings[0] == "INR" || strings[1] == "INR") {
                        rateFromXML = xmlCurrencyRate.get("INR");
                        Log.d(TAG, rateFromXML);
                    } else if (strings[0] == "KRW" || strings[1] == "KRW") {
                        rateFromXML = xmlCurrencyRate.get("KRW");
                        Log.d(TAG, rateFromXML);
                    } else if (strings[0] == "MYR" || strings[1] == "MYR") {
                        rateFromXML = xmlCurrencyRate.get("MYR");
                        Log.d(TAG, rateFromXML);
                    } else if (strings[0] == "NZD" || strings[1] == "NZD") {
                        rateFromXML = xmlCurrencyRate.get("NZD");
                        Log.d(TAG, rateFromXML);
                    } else if (strings[0] == "PHP" || strings[1] == "PHP") {
                        rateFromXML = xmlCurrencyRate.get("PHP");
                        Log.d(TAG, rateFromXML);
                    } else if (strings[0] == "SGD" || strings[1] == "SGD") {
                        rateFromXML = xmlCurrencyRate.get("SGD");
                        Log.d(TAG, rateFromXML);
                    } else if (strings[0] == "THB" || strings[1] == "THB") {
                        rateFromXML = xmlCurrencyRate.get("THB");
                        Log.d(TAG, rateFromXML);
                    } else if (strings[0] == "ZAR" || strings[1] == "ZAR") {
                        rateFromXML = xmlCurrencyRate.get("ZAR");
                        Log.d(TAG, rateFromXML);
                    } else if (strings[0] == "SEK" || strings[1] == "SEK") {
                        rateFromXML = xmlCurrencyRate.get("SEK");
                        Log.d(TAG, rateFromXML);
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



