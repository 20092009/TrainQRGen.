package user.trainqrgen;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.ComponentName;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.content.Context;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;

import android.support.v4.app.NotificationCompat;

import android.util.Log;
import android.widget.RemoteViews;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Map;


public class RoutinecheckService extends Service {

    private static final int NOTIFICATION_ID = 1;
    Handler handler;
    public Runnable chkLocation;
    public NotificationCompat.Builder builder;
    public NotificationManager notificationManager;
    public int notification_id;
    private NotificationManager mNotificationManager;
    private int SIMPLE_NOTFICATION_ID;
    public RemoteViews remoteViews;
    public Context context;
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }




    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        handler = new Handler();

        handler.postDelayed(chkLocation, 1000);

        chkLocation = new Runnable(){
            @Override
            public void run() {


// strt

                //Showing the progress dialog


                StringRequest stringRequest = new StringRequest(Request.Method.POST,"http://suchandra2009.esy.es/Routinecheck.php" ,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String s) {

                                //Showing toast message of the response
                                Toast.makeText(RoutinecheckService.this, s , Toast.LENGTH_LONG).show();
                                if(s.contains("yes")){
//                                    stopService(new Intent(getBaseContext(),
//                                            PostLatLonService.class));
                                    notifyUser();
                                    startService(new Intent(getBaseContext(),
                                          KillpngService.class));
//                                    stopService(new Intent(getBaseContext(),
//                                            RoutinecheckService.class));
                                    handler.removeCallbacks(chkLocation);
                                    Toast.makeText(getBaseContext(),"Deleting Starts",Toast.LENGTH_LONG).show();

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {


                                //Showing toast
                                Toast.makeText(RoutinecheckService.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();


                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        //Converting Bitmap to String


                        //Getting Image Name

                        //Creating parameters
                        Map<String,String> params = new Hashtable<String, String>();

                        //Adding parameters




                        //returning parameters
                        return params;
                    }
                };

                //Creating a Request Queue
                RequestQueue requestQueue = Volley.newRequestQueue(RoutinecheckService.this);

                //Adding request to the queue
                requestQueue.add(stringRequest);



 //end
                RoutinecheckService.this.handler.postDelayed(RoutinecheckService.this.chkLocation, 10000);

            }
        };
                return START_NOT_STICKY ;
    }

    private void notifyUser() {





        Long alertOfTime = new GregorianCalendar().getTimeInMillis() + 2 * 1000;

        Intent alertIntent = new Intent(this, Alert.class);

        AlarmManager aManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        aManager.set(AlarmManager.RTC_WAKEUP,alertOfTime,
                PendingIntent.getBroadcast(this,1,alertIntent,PendingIntent.FLAG_UPDATE_CURRENT));






    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Chk Service Destroyed", Toast.LENGTH_LONG).show();

    }

}
