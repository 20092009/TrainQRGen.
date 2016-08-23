package user.trainqrgen;

        import android.app.Service;
        import android.content.Intent;
        import android.os.IBinder;
        import android.widget.Toast;
        import android.content.Context;
        import android.location.Location;
        import android.location.LocationListener;
        import android.location.LocationManager;
        import android.os.Bundle;

        import java.lang.*;

        import android.os.Handler;

        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.toolbox.Volley;

        import org.json.JSONException;
        import org.json.JSONObject;

public class PostLatLonService extends Service implements LocationListener {


    LocationManager locationManager;
    Handler handler;
    Location location;
    public Runnable runLocation;
    String currentlat,currentlon;
    double latitude;
    double longitude;



    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.

        handler = new Handler();

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();


        }

        handler.postDelayed(runLocation, 1000);

         runLocation = new Runnable(){
            @Override
            public void run() {


//here

                String aString = (String.valueOf(latitude));
                String bString = (String.valueOf(longitude));
                currentlat = aString.substring(0, Math.min(aString.length(), 5));
                currentlon = bString.substring(0, Math.min(bString.length(), 5));


                final String name = "dada";
                final String username = "dada";
                final String age = "12";
                final String password = "123";


//            double d = latitude;
//           trimcurrentlat = ( (double) ( (int) (d * 100.0) ) ) / 100.0 ;
//
//
//            double d1 = longitude;
//            trimcurrentlon = ( (double) ( (int) (d1 * 100.0) ) ) / 100.0 ;


//                Toast.makeText(PostLatLonService.this, currentlat + "\n" + currentlon, Toast.LENGTH_SHORT).show();
// test strt








//test end
// posting code strt here


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Toast.makeText(PostLatLonService.this, "PTDB=1", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(PostLatLonService.this, "Error Posting", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                PostLatLonRequest registerRequest = new PostLatLonRequest(name, username, age, password, currentlat, currentlon, responseListener);
                RequestQueue queue = Volley.newRequestQueue(PostLatLonService.this);
                queue.add(registerRequest);


// posting code end here




                PostLatLonService.this.handler.postDelayed(PostLatLonService.this.runLocation, 5000);

            }
            };








        return START_NOT_STICKY;
    }


    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();


    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    @Override
    public void onTaskRemoved(Intent rootIntent) {


        if(startService(new Intent(this, PostLatLonService.class)) != null) {
            Toast.makeText(getBaseContext(), "Service is already running", Toast.LENGTH_SHORT).show();

        }
        else
        {
            startService(new Intent(getBaseContext(), PostLatLonService.class));

        }
super.onTaskRemoved(rootIntent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();

    }



}