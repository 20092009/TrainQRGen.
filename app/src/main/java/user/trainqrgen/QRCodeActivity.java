package user.trainqrgen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.math.BigInteger;
import java.util.Random;

import java.util.UUID;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import android.graphics.Bitmap;
import android.util.Base64;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import android.os.Environment;

import android.view.View;
import android.widget.TextView;

import java.lang.*;

import android.widget.Toast;

import android.widget.Button;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.ToggleButton;

import android.os.Handler;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.zxing.WriterException;
import java.io.File;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;


public class QRCodeActivity extends AppCompatActivity   {

    public String both;
    public String mEncodeString;
    private static final Random random = new Random();
    private static final String CHARS = "abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ2345678901";
    public TextView mTextDesc;
    public ImageView mImageQR;
    public ProgressBar mProgress;
    public String imageInSD = "/sdcard/bobo.jpg/";
    public int j;
   public String reverse,result;
    public Bitmap mBitmapQR;

    LocationManager locationManager;
    Handler handler;
    Location location;
String currentlat,currentlon;
    public String bobou;
    double latitude;
    double longitude;
String posttargetlat,posttargetlon;


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);


        Intent intent = getIntent();
        String startlocation = intent.getExtras().getString("startlocation");
        String endlocation = intent.getExtras().getString("endlocation");
        both = startlocation + " " + endlocation;





        new AsyncGenerateQRCode().execute(GenerateQR.MARGIN_NONE);


        mImageQR = (ImageView) findViewById(R.id.mImageQR);



    if (endlocation.contains("Kandivali")) {
        posttargetlat="19.20";
        posttargetlon="72.85";
        postTargetLocation();
    } else if(endlocation.contains("Malad")){
        posttargetlat="19.18";
        posttargetlon="72.84";
        postTargetLocation();
    } else if (endlocation.contains("Goregaon")){
        posttargetlat="19.16";
        posttargetlon="72.84";
        postTargetLocation();
    }else if(endlocation.contains("Jogeshwari")){
        posttargetlat="19.13";
        posttargetlon="72.84";
        postTargetLocation();
    }else if(endlocation.contains("Andheri")){
        posttargetlat="19.11";
        posttargetlon="72.84";
        postTargetLocation();
    } else if(endlocation.contains("Parle")){
        posttargetlat="19.10";
        posttargetlon="72.84";
        postTargetLocation();
    } else if(endlocation.contains("Santa")){
        posttargetlat="19.08";
        posttargetlon="72.84";
        postTargetLocation();
    }





        ToggleButton toggle = (ToggleButton) findViewById(R.id.toggleButton);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mImageQR.setVisibility(View.VISIBLE);
                } else {
                    mImageQR.setVisibility(View.GONE
                    );
                }
            }
        });




        Button yourButton = (Button) findViewById(R.id.button11);
        yourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(getBaseContext(), PostLatLonService.class));
                startService(new Intent(getBaseContext(), RoutinecheckService.class));
            }
        });


        Button yourButton22 = (Button) findViewById(R.id.button22);
        yourButton22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(getBaseContext(), PostLatLonService.class));
                stopService(new Intent(getBaseContext(), RoutinecheckService.class));
            }
        });





    }



    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 70, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }



    private void postTargetLocation() {



            //Showing the progress dialog
        final ProgressDialog loading = ProgressDialog.show(this,"Updating...","Please wait...",false,false);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,"http://suchandra2009.esy.es/VolleyUpload/Posttargetlatlon.php" ,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            //Disimissing the progress dialog

                            loading.dismiss();
                            //Showing toast message of the response
                            Toast.makeText(QRCodeActivity.this, s , Toast.LENGTH_LONG).show();

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            //Dismissing the progress dialog
                            loading.dismiss();

                            //Showing toast
                            Toast.makeText(QRCodeActivity.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                        }
                    }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    //Converting Bitmap to String
                    String ticketbitmap = getStringImage(mBitmapQR);

                    //Getting Image Name

                    //Creating parameters
                    Map<String,String> params = new Hashtable<String, String>();

                    //Adding parameters
                    params.put("targetlat", posttargetlat);
                    params.put("targetlon",posttargetlon );
                    params.put("ticketbitmap",ticketbitmap);

                    //returning parameters
                    return params;
                }
            };

            //Creating a Request Queue
            RequestQueue requestQueue = Volley.newRequestQueue(this);

            //Adding request to the queue
            requestQueue.add(stringRequest);



    }


//GPS end here


    // GPS post


//            double d = latitude;
//           trimcurrentlat = ( (double) ( (int) (d * 100.0) ) ) / 100.0 ;
//
//
//            double d1 = longitude;
//            trimcurrentlon = ( (double) ( (int) (d1 * 100.0) ) ) / 100.0 ;

// posting code strt here

    //end here






    /**
     * AsyncTask to generate QR Code image
     */
    private class AsyncGenerateQRCode extends AsyncTask<Integer, Void, Integer> {


        @Override
        protected Integer doInBackground(Integer... params) {
            if (params.length != 1) {
                throw new IllegalArgumentException("Must pass QR Code margin value as argument");
            }

            try {
                final int colorQR = Color.BLACK;
                final int colorBackQR = Color.WHITE;
                final int marginSize = params[0];
                final int width = 400;
                final int height = 400;




                //sudorandom int strt here



                String s = "7115086358";
                reverse = new StringBuffer(s).
                        reverse().toString();
                Log.e("reverse val",reverse);

                int length=5;
                StringBuilder token1 = new StringBuilder(length);


                for (int i = 0; i < length; i++) {
                    token1.append(CHARS.charAt(random.nextInt(CHARS.length())));

                }

                StringBuilder token2 = new StringBuilder(length);


                for (int i = 0; i < length; i++) {
                    token2.append(CHARS.charAt(random.nextInt(CHARS.length())));

                }
                StringBuilder token3 = new StringBuilder(length);


                for (int i = 0; i < length; i++) {
                    token3.append(CHARS.charAt(random.nextInt(CHARS.length())));

                }
                StringBuilder token4 = new StringBuilder(length);


                for (int i = 0; i < length; i++) {
                    token4.append(CHARS.charAt(random.nextInt(CHARS.length())));

                }
                StringBuilder token5 = new StringBuilder(length);


                for (int i = 0; i < length; i++) {
                    token5.append(CHARS.charAt(random.nextInt(CHARS.length())));

                }

                StringBuilder token6 = new StringBuilder(length);


                for (int i = 0; i < length; i++) {
                    token6.append(CHARS.charAt(random.nextInt(CHARS.length())));

                }



                String currentlat1 = reverse.substring(0, Math.min(reverse.length(), 2));

                String currentlat2 = reverse.substring(2, Math.min(reverse.length(), 4));

                String currentlat3 = reverse.substring(4, Math.min(reverse.length(), 6));
                String currentlat4 = reverse.substring(6, Math.min(reverse.length(), 8));
                String currentlat5 = reverse.substring(8, Math.min(reverse.length(), 10));



                bobou=token6+currentlat1+token1+currentlat2+token2+currentlat3+token3+currentlat4+token4+currentlat5+token5;

                Log.e("product string",bobou);


//      WN41V8503SQK36kxWMc80o4c2R51xvgXz776O2VG

//         result = bobou.substring(5, 7)+ bobou.substring(12, 14)+bobou.substring(19, 21)+bobou.substring(26, 28)+bobou.substring(33, 35) ;
//        Log.e("res",result);



//sudorandom int end here


                String mydate = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                final String data = bobou+"\n" + both + "\n" + mydate ;
                Log.e("bobu at QR",bobou);
                mBitmapQR = GenerateQR.generateBitmap(data, width, height,
                        marginSize, colorQR, colorBackQR);
            } catch (IllegalArgumentException iae) {

                iae.printStackTrace();
                return 0;
            } catch (WriterException we) {

                we.printStackTrace();
                return 0;
            }
            return 1;
        }

        @Override
        protected void onPostExecute(Integer result) {

            if (result != 0) {
                mImageQR.setImageBitmap(mBitmapQR);
                mImageQR.setVisibility(View.GONE);
                File file = new File(Environment.getExternalStorageDirectory().getPath() + "/bobo.jpg");
                try {
                    file.createNewFile();
                    FileOutputStream ostream = new FileOutputStream(file);
                    mBitmapQR.compress(Bitmap.CompressFormat.JPEG, 100, ostream);
                    ostream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(getBaseContext(), "File saved successfully!", Toast.LENGTH_SHORT).show();
                Toast.makeText(getBaseContext(), "file path" + getFileStreamPath("bobo.jpg"), Toast.LENGTH_LONG).show();

            }
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }










}
