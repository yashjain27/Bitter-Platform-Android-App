package tech.ceece.biittersocialnetwork_hashmap;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserMenu extends AppCompatActivity {
    User user;
    Account tempAccount;
    private Account account;
    private String email;
    private Bitter bitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_menu);
        //account = (Account) getIntent().getSerializableExtra("account");
        email = (String) getIntent().getSerializableExtra("email");
        bitter = (Bitter) getIntent().getSerializableExtra("bitter");
        account = bitter.getAccounts().getAccountInformation(email);
    }

    public void onFollow(View v){
        //Create dialog
        final Dialog d = new Dialog(this);
        d.setContentView(R.layout.input_field);
        d.show();
        TextView textView = (TextView) d.findViewById(R.id.textView);
        textView.setText("Follow someone...");

        Button b = (Button) d.findViewById(R.id.button13);
        b.setText("Follow");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = (EditText) d.findViewById(R.id.editText6);
                if((user = bitter.getUsers().getUser(editText.getText().toString())) != null) {
                    account.follow(user); // Follow the user

                    //The followed user receives a follower
                    tempAccount = bitter.getAccounts().getAccountInformation(user.getEmail());
                    tempAccount.receiveFollower(bitter.getUsers().getUser(email));
                }else
                    Toast.makeText(UserMenu.this, "Invalid email, user not found!", Toast.LENGTH_SHORT).show();
                d.cancel();
            }
        });
    }

    public void onUnfollow(View v){
        //Create dialog
        final Dialog d = new Dialog(this);
        d.setContentView(R.layout.input_field);
        d.show();
        TextView textView = (TextView) d.findViewById(R.id.textView);
        textView.setText("Unfollow someone...");

        Button b = (Button) d.findViewById(R.id.button13);
        b.setText("Unfollow");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = (EditText) d.findViewById(R.id.editText6);
                if((user = bitter.getUsers().getUser(editText.getText().toString())) != null) {
                    account.unfollow(user); //Unfollow the user

                    //The unfollowed user lost a follower
                    tempAccount = bitter.getAccounts().getAccountInformation(user.getEmail());
                    tempAccount.loseFollower(bitter.getUsers().getUser(email));
                }else
                    Toast.makeText(UserMenu.this, "Invalid email, user not found!", Toast.LENGTH_SHORT).show();
                d.cancel();
            }
        });
    }

    public void onViewFollowers(View v){
        Intent in = new Intent(this, Printable.class);
        in.putExtra("print", account.printFollowers());
        startActivity(in);
    }

    public void onFollowing(View v){
        Intent in = new Intent(this, Printable.class);
        in.putExtra("print", account.printFollowing());
        startActivity(in);
    }

    public void onListAllUsers(View v){
        Intent in = new Intent(this, Printable.class);
        in.putExtra("print", bitter.toString());
        startActivity(in);
    }

    public void onLogout(View v){
        Intent intent = new Intent(this, BitterPlatform.class);
        intent.putExtra("bitter", bitter);
        startActivity(intent);
        finish();
    }

    public void onCloseAccount(View v){
        for(String s : bitter.getAccounts().keySet()){
            //Accounts who follow this account will lose them in the Followers/Following list
            bitter.getAccounts().getAccountInformation(s).loseFollower(bitter.getUsers().getUser(email));
            bitter.getAccounts().getAccountInformation(s).unfollow(bitter.getUsers().getUser(email));
        }
        System.out.println(account.getName() + "'s account has been closed.");
        bitter.removeUser(email);
        Intent intent = new Intent(this, BitterPlatform.class);
        intent.putExtra("bitter", bitter);
        startActivity(intent);
        finish();
    }
}
