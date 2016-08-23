package user.trainqrgen;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class LoadPreviousTicket extends AppCompatActivity {


    public String mEncodeString;
    public TextView mTextDesc;
    public ImageView mImageQR;
    public ProgressBar mProgress;
    public String imageInSD = "/sdcard/bobo.jpg/";
    public Bitmap mBitmapQR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_previous_ticket);

    }





    public void loadprevious(View v) {

        Bitmap bitmap = BitmapFactory.decodeFile(imageInSD);
        mImageQR = (ImageView) findViewById(R.id.loadimage);
        mImageQR.setImageBitmap(bitmap);
        mImageQR.setVisibility(View.VISIBLE);

        Toast.makeText(getBaseContext(), "File loaded successfully!", Toast.LENGTH_SHORT).show();


    }
}
