package lk.devildeveloper.sanoj.sakmadalaconvert;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.pixplicity.easyprefs.library.Prefs;
import com.theartofdev.edmodo.cropper.CropImage;

import org.jetbrains.annotations.NotNull;

import java.util.prefs.PreferenceChangeEvent;

import gun0912.tedimagepicker.builder.TedImagePicker;
import gun0912.tedimagepicker.builder.listener.OnSelectedListener;


public class Menu extends AppCompatActivity {
    RelativeLayout mTextcon;
    RelativeLayout mImagepicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mTextcon = findViewById(R.id.textcon);
        mImagepicker = findViewById(R.id.imagepicker);




        mTextcon.setOnClickListener(view ->{
            Intent mainIntent = new Intent(Menu.this, MainActivity.class);
            startActivity(mainIntent);
        });
        mImagepicker.setOnClickListener(view ->{


            TedImagePicker.with(this)
                    .start(new OnSelectedListener() {
                        @Override
                        public void onSelected(@NotNull Uri uri) {
                            /*Prefs.putString("imagURL", String.valueOf(uri));
                            Intent mainIntent = new Intent(Menu.this, Photo_Editor.class);
                            startActivity(mainIntent);*/

                            CropImage.activity(uri)
                                    .start(Menu.this);
                        }
                    });
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                Prefs.putString("imagURL", String.valueOf(resultUri));
                            Intent mainIntent = new Intent(Menu.this, Photo_Editor.class);
                            startActivity(mainIntent);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

}