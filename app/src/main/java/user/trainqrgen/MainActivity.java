package user.trainqrgen;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

String startlocation;
    String endlocation;
    private static final int NOTIFICATION_ID = 1;

Button generate;
    AutoCompleteTextView autotextView,startauto;

    String[] Stations = {
            "Kandivali",
            "Malad",
            "Goregaon",
            "Jogeshwari",
            "Andheri",
            "Vile-Parle",
            "Santa-Cruz",

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, Stations);


        autotextView = (AutoCompleteTextView) findViewById(R.id.startauto);

        autotextView.setThreshold(0);

        autotextView.setAdapter(adapter);


        startauto = (AutoCompleteTextView) findViewById(R.id.autotext);

        startauto.setThreshold(0);

        startauto.setAdapter(adapter);
//  String startlocation=textstart.getText().toString();
//
//String endlocation=textView.getText().toString();

    }




public void generate(View v){

    String startlocation = (String)autotextView.getText().toString();
    String endlocation = (String)startauto.getText().toString();



if (endlocation!=null){
    Intent intent=new Intent(getBaseContext(),QRCodeActivity.class);
    intent.putExtra("startlocation", startlocation);
    intent.putExtra("endlocation", endlocation);

startActivity(intent);


} }


public void loadprevious(View v){

    Intent i=new Intent(getBaseContext(),LoadPreviousTicket.class);
    startActivity(i);

}

    public void killpost(View v){
        stopService(new Intent(getBaseContext(), PostLatLonService.class));
        stopService(new Intent(getBaseContext(), RoutinecheckService.class));


    }



}
