package com.example.convertisseur2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private static final String TAG = "Myapp";
    private String theNumber;
    private float theNumberToDouble;
    private float toDollar;

    private EditText myEuroNumber;
    private TextView myResultInDollar;
    private Spinner mySpinnerMoneyInit;
    private Spinner mySpinnerMoneyFinal;
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
        Log.d(TAG,"activity started");
        Button myButtonConvert = (Button) this.findViewById(R.id.button3);
        this.myEuroNumber = (EditText) this.findViewById(R.id.myEnterNumber);
        this.myResultInDollar = (TextView) this.findViewById(R.id.textView);
        this.mySpinnerMoneyInit = (Spinner) this.findViewById(R.id.mySpinnerCurrencyInit);
        this.mySpinnerMoneyFinal = (Spinner) this.findViewById(R.id.mySpinnerCurrencyDestination);

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
                myResultInDollar.setText(String.valueOf(toDollar));

                /*
                * TAUX DE CONVERTION :
                * 1 euro = 0.98 USD
                * 1 euro = 147.96 pesos
                * 1 euro = 143.90 yen
                * 1 dollar = 1.02 euros
                * 1 dollar = 151.29 pesos
                * 1 dollar = 147.18 yen
                * 1 yen = 0.0069 euros
                * 1 yen = 1.03 yen
                * 1 yen = 0.0068
                * 1 peso = 0.051 euros
                * 1 peso = 0.050 USD
                * 1 peso = 7.46 yen
                 */
                if(Objects.equals(currencyTXTinit,"Euro")){ // euro devise de départ
                    if(Objects.equals(currencyTXTDest, "Dollar")){
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        toDollar = (float) (theNumberToDouble * 0.98);
                        Log.d(TAG, "Convertion euro => dollar");
                        myResultInDollar.setText(String.valueOf(toDollar) + "$");
                    } else if(Objects.equals(currencyTXTDest, "Peso")){
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        toDollar = (float) (theNumberToDouble * 147.87);
                        Log.d(TAG, "convertion euro => pesos");
                        myResultInDollar.setText(String.valueOf(toDollar) + "$Ar");
                    } else if(Objects.equals(currencyTXTDest, "Yen")){
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        toDollar = (float) (theNumberToDouble  * 143.87);
                        Log.d(TAG, "button euro => Yen");
                        myResultInDollar.setText(String.valueOf(toDollar) + "¥");
                    } else{
                        myResultInDollar.setText("Vous avez choisit la même currency de destination");
                    }
                } else if(Objects.equals(currencyTXTinit, "Dollar")){ // dollar currency de départ
                    if(Objects.equals(currencyTXTDest, "Euro")){
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        toDollar = (float) (theNumberToDouble / 0.98);
                        Log.d(TAG, "Convertion dollar => euro");
                        myResultInDollar.setText(String.valueOf(toDollar) + "€");
                    } else if(Objects.equals(currencyTXTDest, "Peso")){
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        toDollar = (float) (theNumberToDouble * 151.32);
                        Log.d(TAG, "convertion dollar => pesos");
                        myResultInDollar.setText(String.valueOf(toDollar) + "$Ar");
                    } else if(Objects.equals(currencyTXTDest, "Yen")){
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        toDollar = (float) (theNumberToDouble  * 147.14);
                        Log.d(TAG, "conversion dollar => yen");
                        myResultInDollar.setText(String.valueOf(toDollar) + "¥");
                    } else{
                        myResultInDollar.setText("Vous avez choisit la même currency de destination");
                    }
                } else if(Objects.equals(currencyTXTinit, "Yen")){ // Yen comme currency de départ
                    if(Objects.equals(currencyTXTDest, "Euro")){
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        toDollar = (float) (theNumberToDouble / 143.85);
                        Log.d(TAG, "Convertion yen => euro");
                        myResultInDollar.setText(String.valueOf(toDollar) + "€");
                    } else if(Objects.equals(currencyTXTDest, "Peso")){
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        toDollar = (float) (theNumberToDouble * 1.03);
                        Log.d(TAG, "convertion yen => pesos");
                        myResultInDollar.setText(String.valueOf(toDollar) + "$Ar");
                    } else if(Objects.equals(currencyTXTDest, "Dollar")){
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        toDollar = (float) (theNumberToDouble  / 147.14);
                        Log.d(TAG, "convertion yen => dollar");
                        myResultInDollar.setText(String.valueOf(toDollar) + "$");
                    } else{
                        myResultInDollar.setText("Vous avez choisit la même currency de destination");
                    }
                } else if(Objects.equals(currencyTXTinit, "Peso")){
                    if(Objects.equals(currencyTXTDest, "Euro")){
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        toDollar = (float) (theNumberToDouble * 0.0068);
                        Log.d(TAG, "Convertion pesos => euro");
                        myResultInDollar.setText(String.valueOf(toDollar) + "€");
                    } else if(Objects.equals(currencyTXTDest, "Yen")){
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        toDollar = (float) (theNumberToDouble * 0.97);
                        Log.d(TAG, "convertion pesos => yen");
                        myResultInDollar.setText(String.valueOf(toDollar) + "¥");
                    } else if(Objects.equals(currencyTXTDest, "Dollar")){
                        theNumber = myEuroNumber.getText().toString(); // récupération du EditString
                        theNumberToDouble = Float.parseFloat(theNumber);// convert to double
                        toDollar = (float) (theNumberToDouble  * 0.0066);
                        Log.d(TAG, "convertion pesos => dollar");
                        myResultInDollar.setText(String.valueOf(toDollar) + "$");
                    } else{
                        myResultInDollar.setText("Vous avez choisit la même currency de destination");
                    }
                } else if(Objects.equals(currencyTXTinit, "Choose the currency") || Objects.equals(currencyTXTDest, "Select the final currency"))
                    myResultInDollar.setText("Faite une convertion");
            }
        });
    }

    // GERE CYCLE DE VIE MAIN ACTIVITY
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"activity destroyed");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"activity resumed");
    }

    // app pause
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"activity on pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"activity stoped");
    }

   @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
       if (adapterView.getId() == R.id.mySpinnerCurrencyInit) { // select the init spinner
           currencyTXTinit= adapterView.getItemAtPosition(i).toString();
         //  Toast.makeText(adapterView.getContext(), currencyTXTinit, Toast.LENGTH_SHORT).show();
       } else if(adapterView.getId() == R.id.mySpinnerCurrencyDestination){ // select the destination spinner
           currencyTXTDest= adapterView.getItemAtPosition(i).toString();
          // Toast.makeText(adapterView.getContext(), currencyTXTDest, Toast.LENGTH_SHORT).show();
       }
   }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}