package kitipoom.moderndiary.activities;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileDescriptor;
import java.io.IOException;
import java.util.Random;

import kitipoom.moderndiary.R;
import kitipoom.moderndiary.models.Datasend;
import kitipoom.moderndiary.models.Storage;

public class DateActivity extends AppCompatActivity {
    private TextView date;
    private Bitmap bmp;
    private EditText notetext;
    private  ImageView imageView;
    private Button savebutton,deletebutton, picButton;
    private int day, month,year;

    private static int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initComponent();

    }
    private void initComponent(){
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setDate();
        checkDate();
        notetext = (EditText)findViewById(R.id.NoteText);
        notetext.setText(Storage.getSt().getDateyear().getDateMonth(month).getDateDay(day).getDiary().getText());

        date = (TextView) findViewById(R.id.Datetext);
        date.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.Datetextsizes));
        date.setText(day + "/" + month + "/" + year);
        savebutton = (Button)findViewById(R.id.SaveNote);
        savebutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Storage.getSt().getDateyear().getDateMonth(month).getDateDay(day).getDiary().saveDiary(notetext.getText().toString(), ((BitmapDrawable) imageView.getDrawable()).getBitmap());
                finish();
            }
        });
        deletebutton=(Button)findViewById(R.id.DeleteNote);
        deletebutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Storage.getSt().getDateyear().getDateMonth(month).getDateDay(day).getDiary().delete();
                finish();
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.Diaryadd);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DateActivity.this, NotifyListActivity.class);
                startActivity(intent);
            }
        });

        picButton = (Button) findViewById(R.id.addPic);
        picButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        imageView = (ImageView) findViewById(R.id.imgView);
        imageView.setImageBitmap(Storage.getSt().getDateyear().getDateMonth(month).getDateDay(day).getDiary().getBmp());
        imageView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });


    }
    public void checkDate(){
        Storage.getSt().saveDate(day, month, year);
    }
    public void setDate(){
        day= Datasend.getInstant().getDay();
        month =Datasend.getInstant().getMonth();
        year=Datasend.getInstant().getYear();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();



            bmp = null;
            try {
                bmp = getBitmapFromUri(selectedImage);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            imageView.setImageBitmap(bmp);

        }


    }

    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }

}
