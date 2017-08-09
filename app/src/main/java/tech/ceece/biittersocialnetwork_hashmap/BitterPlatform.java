package tech.ceece.biittersocialnetwork_hashmap;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class BitterPlatform extends AppCompatActivity {
    //Data field
    private Bitter bitter;
    private Account account;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //File permission
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                123);
        bitter = (Bitter) getIntent().getSerializableExtra("bitter");

    }

    public void onLogin(View v){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.login);
        dialog.show();

        final Intent intent = new Intent(this, UserMenu.class); //Need to attach bitter, account, email, objects

        Button b = (Button) dialog.findViewById(R.id.button12);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = (EditText) dialog.findViewById(R.id.editText4);
                email = editText.getText().toString();
                EditText editText1 = (EditText) dialog.findViewById(R.id.editText5);
                String pass = editText1.getText().toString();

                //Check if email and password are correct
                if((account = bitter.getAccounts().getAccountInformation(email)) != null)
                    if(pass.equals(account.getPassword().getPassword())) {
                        intent.putExtra("email", email);
                        intent.putExtra("account", account);
                        intent.putExtra("bitter", bitter);
                        startActivity(intent); //Grant access
                        finish();
                    }else
                        Toast.makeText(BitterPlatform.this, "Invalid password.", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(BitterPlatform.this, "Invalid email.", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
    }

    public void onSignUp(View v){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.sign_up);
        dialog.show();

        final Intent intent = new Intent(this, UserMenu.class);

        Button b = (Button) dialog.findViewById(R.id.button5);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText emailText = (EditText) dialog.findViewById(R.id.editText2);
                email = emailText.getText().toString();
                EditText nameText = (EditText) dialog.findViewById(R.id.editText);
                EditText passText = (EditText) dialog.findViewById(R.id.editText3);

                //Create password
                Password password;
                try{
                    password = new Password(passText.getText().toString());
                    account = new Account(nameText.getText().toString(), password);
                }catch (Exception e){
                    Toast.makeText(BitterPlatform.this, "\"Your password is not secure enough.\" +\n" +
                            "                                    \" Please make sure you have at least one upper case and one \" +\n" +
                            "                                    \"lower case letter, one special character, and one number.\"", Toast.LENGTH_SHORT).show();
                    return;
                }

                User user = new User(nameText.getText().toString(), email);

                try {
                    bitter.addUser(email, user, account);
                    Toast.makeText(BitterPlatform.this, "Thanks for signing up! Welcome to Bitter!", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                    intent.putExtra("email", email);
                    intent.putExtra("account", account);
                    intent.putExtra("bitter", bitter);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    Toast.makeText(BitterPlatform.this, "Invalid email!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void onQuit(View v){
        //Save data before exiting
        try {
            String appPath = getApplicationInfo().dataDir;
            FileOutputStream fileOutputStream = new FileOutputStream(appPath + "/Bitter2.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(bitter);
            out.close();
            fileOutputStream.close();
            Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "" + e, Toast.LENGTH_LONG).show();
        }

        //Exit
        Toast.makeText(this, "Thanks for using Bitter!", Toast.LENGTH_SHORT).show();
        finish();
    }


}
