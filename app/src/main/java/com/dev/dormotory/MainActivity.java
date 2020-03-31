package com.dev.dormotory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.dev.dormotory.Utils.GameManager;

public class MainActivity extends AppCompatActivity {
    TextView description,documents,map,mapfloor,game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        description = findViewById(R.id.Description);
        documents = findViewById(R.id.document);
        map = findViewById(R.id.Map);
        mapfloor = findViewById(R.id.MapFloor);
        game = findViewById(R.id.game);

        description.setOnClickListener(v->{
            Intent intent = new Intent(this,DescriptionActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
        mapfloor.setOnClickListener(v->{
            Intent intent = new Intent(this,MapFloorActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
        documents.setOnClickListener(v->{
            Intent intent = new Intent(this,DocumentsActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
        map.setOnClickListener(v->{
            Intent intent = new Intent(this,MapActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
        game.setOnClickListener(v->{
            Intent intent = new Intent(this, GameActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
    }
}
