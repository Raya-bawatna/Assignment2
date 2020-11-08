package raya.cs.birzeit.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class BMICalculation extends AppCompatActivity {
    private Spinner spinner;
    public static final String NAME = "NAME";
    public static final String Weight = "Weight";
    public static final String Height = "Height";
    public static final String FLAG = "FLAG";
    private EditText edtName;
    private EditText edtWeight;
    private EditText edtHeight;
    private CheckBox chkRememberInfo;
    private EditText edtResult;
    private SharedPreferences.Editor editor;
    private SharedPreferences prefs;
    private Button btnOpen;
    private  TextView txtView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);
        edtResult=findViewById(R.id.edtResult);
        edtWeight=findViewById(R.id.edtWeight);
        edtHeight=findViewById(R.id.edtHeight);
        edtName=findViewById(R.id.edtName);
        txtView=findViewById(R.id.txtView);

//        btnOpen=findViewById(R.id.btnOpen);
        populateSpinner();
        setupViews();
        setupSharedPrefs();
        checkPrefs();
        btnOpen=findViewById(R.id.btnOpen);



    }
    private void checkPrefs(){
        boolean flag = prefs.getBoolean(FLAG, false);
        if(flag){
            String name = prefs.getString(NAME,"");
            String height=prefs.getString(Height,"");
            String weight = prefs.getString(Weight,"");
            edtName.setText(name);
            edtHeight.setText(height);
            edtWeight.setText(weight);
            chkRememberInfo.setChecked(true);
        }
    }
    private void setupSharedPrefs(){
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }
    private void setupViews(){
        edtName = findViewById(R.id.edtName);
        edtHeight=findViewById(R.id.edtHeight);
        edtWeight=findViewById(R.id.edtWeight);
        chkRememberInfo = findViewById(R.id.chkRememberInfo);
    }



    public void onEnterClicked(View view) {
        String name = edtName.getText().toString();
        String height=edtHeight.getText().toString();
        String weight=edtWeight.getText().toString();
        if(chkRememberInfo.isChecked()){
            editor.putString(NAME,name);
           editor.putString(Height,height);
           editor.putString(Weight,weight);
            editor.putBoolean(FLAG,true);
            editor.commit();

        }
    }


    private void populateSpinner() {

        String[]cats=new String[]{"Male","Female"};


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, cats);

        spinner.setAdapter(adapter);
    }
    public void calculateBMI(View v) {
        String height = edtHeight.getText().toString();
        String weight= edtWeight.getText().toString();

        if (height != null && !"".equals(height)
                && weight != null  &&  !"".equals(weight)) {
            float heightValue = Float.parseFloat(height) / 100;
            float weightValue = Float.parseFloat(weight);

            float bmi = weightValue / (heightValue * heightValue);

            displayBMI(bmi);


        }
    }

    private void displayBMI(float bmi) {
        String bmiLabel = "";

        if (Float.compare(bmi, 15f) <= 0) {
            bmiLabel = getString(R.string.very_severely_underweight);
        } else if (Float.compare(bmi, 15f) > 0  &&  Float.compare(bmi, 16f) <= 0) {
            bmiLabel = getString(R.string.severely_underweight);
        } else if (Float.compare(bmi, 16f) > 0  &&  Float.compare(bmi, 18.5f) <= 0) {
            bmiLabel = getString(R.string.underweight);
        } else if (Float.compare(bmi, 18.5f) > 0  &&  Float.compare(bmi, 25f) <= 0) {
            bmiLabel = getString(R.string.normal);
        } else if (Float.compare(bmi, 25f) > 0  &&  Float.compare(bmi, 30f) <= 0) {
            bmiLabel = getString(R.string.overweight);
        } else if (Float.compare(bmi, 30f) > 0  &&  Float.compare(bmi, 35f) <= 0) {
            bmiLabel = getString(R.string.moderately_obese);
        } else if (Float.compare(bmi, 35f) > 0  &&  Float.compare(bmi, 40f) <= 0) {
            bmiLabel = getString(R.string.severely_obese);
        } else {
            bmiLabel = "very_severely_obese";
        }

        bmiLabel = bmi + "\n\n" + bmiLabel;
        edtResult.setText(bmiLabel);

    }


    public void onClickOpen(View view) {
        Intent intent=new Intent(this,Timer.class);
        startActivity(intent);
    }
}