package tech.ceece.biittersocialnetwork_hashmap;

import android.Manifest;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class MainActivity extends AppCompatActivity {
    //Data field
    private  Bitter bitter = new Bitter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //File permission
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                123);

        //Deserialize an object
        try {
            String appPath = getApplicationInfo().dataDir;
            FileInputStream fileIn = new FileInputStream(appPath + "/Bitter2.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            bitter = (Bitter) in.readObject();
            in.close();
            fileIn.close();
            Toast.makeText(this, "Welcome to Bitter! Previous data loaded!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Welcome to Bitter! Previous data not found!", Toast.LENGTH_SHORT).show();
        } catch (ClassNotFoundException e) {
            Toast.makeText(this, "Welcome to Bitter! Previous data not found!", Toast.LENGTH_SHORT).show();
        }

        Intent intent = new Intent(this, BitterPlatform.class);
        intent.putExtra("bitter", bitter);
        startActivity(intent);
        finish();
    }


}
