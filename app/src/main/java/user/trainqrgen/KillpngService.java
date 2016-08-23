package user.trainqrgen;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
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
import android.view.Menu;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Hashtable;
import java.util.Map;

public class KillpngService extends Service {


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        new Handler().postDelayed(new Runnable() {

            // Using handler with postDelayed called runnable run method

            @Override
            public void run() {




                StringRequest stringRequest = new StringRequest(Request.Method.POST,"http://suchandra2009.esy.es/Killticket.php" ,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String s) {

                                //Showing toast message of the response
                                Toast.makeText(KillpngService.this, s , Toast.LENGTH_LONG).show();
                                Log.e("dadada",s);
                                if(s.contains("ticketpng")){
//

                                    Toast.makeText(getBaseContext(),"PNG=Null",Toast.LENGTH_LONG).show();

                                } else {

                                    Toast.makeText(getBaseContext(),"TicketRemoveMalf.",Toast.LENGTH_LONG).show();

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {


                                //Showing toast
                                Toast.makeText(KillpngService.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();


                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        //Converting Bitmap to String



                        //Creating parameters
                        Map<String,String> params = new Hashtable<String, String>();
                        //Adding parameters

                        //returning parameters
                        return params;
                    }
                };

                //Creating a Request Queue
                RequestQueue requestQueue = Volley.newRequestQueue(KillpngService.this);

                //Adding request to the queue
                requestQueue.add(stringRequest);





            }









        }, 5*1000);




     return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }
}



