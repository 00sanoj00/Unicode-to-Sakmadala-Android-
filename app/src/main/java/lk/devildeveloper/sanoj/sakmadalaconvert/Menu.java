package lk.devildeveloper.sanoj.sakmadalaconvert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.pixplicity.easyprefs.library.Prefs;
import com.theartofdev.edmodo.cropper.CropImage;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.prefs.PreferenceChangeEvent;

import gun0912.tedimagepicker.builder.TedImagePicker;
import gun0912.tedimagepicker.builder.listener.OnSelectedListener;
import pub.devrel.easypermissions.EasyPermissions;


public class Menu extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
    RelativeLayout mTextcon;
    RelativeLayout mImagepicker;
    RelativeLayout mOcr;
    private static final String[] Storage =
            {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mTextcon = findViewById(R.id.textcon);
        mImagepicker = findViewById(R.id.imagepicker);
        mOcr = findViewById(R.id.ocr);

        EasyPermissions.requestPermissions(
                this,
                getString(R.string.app_name),
                120,
                Storage);




        mTextcon.setOnClickListener(view ->{
            Intent mainIntent = new Intent(Menu.this, MainActivity.class);
            startActivity(mainIntent);
        });
        mOcr.setOnClickListener(view ->{
            EasyPermissions.requestPermissions(
                    this,
                    getString(R.string.app_name),
                    120,
                    Storage);
            Intent mainIntent = new Intent(Menu.this, imagetext.class);
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
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }
}