package tech.ceece.biittersocialnetwork_hashmap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

public class Printable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.print_screen);

        String print = getIntent().getStringExtra("print");

        TextView textView = new TextView(this);
        textView.setText(print);

        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.printable);
        viewGroup.addView(textView );
    }
}
