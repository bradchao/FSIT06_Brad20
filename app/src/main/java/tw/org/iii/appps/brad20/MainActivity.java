package tw.org.iii.appps.brad20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import ir.sohreco.androidfilechooser.ExternalStorageNotAvailableException;
import ir.sohreco.androidfilechooser.FileChooser;

public class MainActivity extends AppCompatActivity
    implements FileChooser.ChooserListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    12);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void test1(View view) {

        FileChooser.Builder builder =
                new FileChooser.Builder(FileChooser.ChooserType.FILE_CHOOSER, this)
                .setMultipleFileSelectionEnabled(false)
                .setFileFormats(new String[] {".jpg", ".png", ".pdf"})
                .setListItemsTextColor(R.color.colorPrimary)
                .setPreviousDirectoryButtonIcon(R.drawable.ic_prev_dir)
                .setDirectoryIcon(R.drawable.ic_directory)
                .setFileIcon(R.drawable.ic_file);
        try {
            FileChooser fileChooserFragment = builder.build();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, fileChooserFragment)
                    .commit();

        } catch (ExternalStorageNotAvailableException e) {
            Log.v("brad", e.toString());
        }
    }

    @Override
    public void onSelect(String path) {
        Log.v("brad", "file = " + path);
    }
}
