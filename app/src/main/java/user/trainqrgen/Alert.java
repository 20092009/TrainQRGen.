package user.trainqrgen;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by SUCHANDRA on 18-08-2016.
 */
public class Alert extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        createNotification(context, "NIGGA U THERE", "When", "BOBO");

    }


    public void createNotification(Context context, String msgg, String msgAlert, String msgText) {
        PendingIntent pendIntentTwo = PendingIntent.getActivity(context, 0,
                new Intent(context, MainActivity.class), 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(msgg)
                .setTicker(msgAlert)
                .setContentText(msgText);

        mBuilder.setContentIntent(pendIntentTwo);

        mBuilder.setDefaults(NotificationCompat.DEFAULT_SOUND);

        mBuilder.setAutoCancel(true);

        NotificationManager nNotifManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);//

        nNotifManager.notify(1, mBuilder.build());

    }
}