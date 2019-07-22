package com.example.androidmenus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    ActionMode mActionMode;

    private ActionMode.Callback mActionModecallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            getMenuInflater().inflate(R.menu.actionmode_context_menu,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()){
                case R.id.actionmode_item_1:
                    Toast.makeText(MainActivity.this, "actionmode_item_1", Toast.LENGTH_SHORT).show();
                    mode.finish();
                    return true;
                case R.id.actionmode_item_2:
                    Toast.makeText(MainActivity.this, "actionmode_item_1", Toast.LENGTH_SHORT).show();
                    mode.finish();
                    return true;
                default:
                    return false;

            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv= (TextView)findViewById(R.id.hello_id);
        this.registerForContextMenu(tv);

        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                if(mActionMode != null){
                    return false;
                }

                mActionMode = startSupportActionMode(mActionModecallback);
                return true;
            }
        });

        Button buttonListView= (Button) findViewById(R.id.list);
        buttonListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ListViewActivity.class));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.options_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.isChecked())item.setChecked(false);
        else item.setChecked(true);

        int id = item.getItemId();

        if(id == R.id.item_1){
            Toast.makeText(this, "item 1", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.item_2){
            Toast.makeText(this, "item 2", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.floatint_context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.contextual_item_1:
                Toast.makeText(this, "contextual menu 1", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.contextual_item_2:
                Toast.makeText(this, "contextual menu 1", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void showPopUp(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.popup_item_1:
                Toast.makeText(this, "Pop up 1 clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.popup_item_2:
                Toast.makeText(this, "Pop up 2 clicked", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;

        }
    }
}
