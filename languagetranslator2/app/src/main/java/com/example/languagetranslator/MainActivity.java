package com.example.languagetranslator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;




public class MainActivity extends AppCompatActivity  {



//    TextView orgtext;
    Button btntranslate;
    EditText srctext;
//    TextView result;
    TextView translatedtv;
    Translator result;
    String originaltext="";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        orgtext=findViewById(R.id.orgtext);
        translatedtv=findViewById(R.id.translatedtv);
        btntranslate=findViewById(R.id.btntranslate);
        srctext=findViewById(R.id.srctext);
       // result=findViewById(R.id.result);




        btntranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //stores srctext into variable original text
            originaltext=srctext.getText().toString();

            //make ready model
                readymodel();
            }
        });


    }



    private void readymodel(){
        TranslatorOptions options=new TranslatorOptions.Builder()
                .setSourceLanguage(TranslateLanguage.ENGLISH)
                .setTargetLanguage(TranslateLanguage.GUJARATI)
                .build();

        result= Translation.getClient(options);


//        for download model if there is not availabel

result.downloadModelIfNeeded().addOnSuccessListener(new OnSuccessListener<Void>() {
    @Override
    public void onSuccess(Void unused) {
//        now model is ready to use so can download
//now do translate
            translate();

    }
}).addOnFailureListener(new OnFailureListener() {
    @Override
    public void onFailure(@NonNull Exception e) {
        System.out.println(e);
    }
});

    }
    public  void translate() {
        result.translate(originaltext).addOnSuccessListener(new OnSuccessListener<String>() {
            @Override
            public void onSuccess(String s) {
                translatedtv.setText(s);


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                translatedtv.setText("can not be translated" + e.getMessage());
            }
        });






    }

//spinner method


}