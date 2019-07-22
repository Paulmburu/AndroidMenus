package com.example.androidmenus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {
    private ListView mainListView;
    private ArrayAdapter<String> listAdapter;
    private ArrayList<String> planetList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        // find the listview resource
        mainListView = (ListView) findViewById(R.id.mainListView);

        // create and populate list of planet names
        String [] planets = new String[]{"Mercury","Venus","Earth","Mars","Jupiter","Saturn","Uranus","Neptune"};

        planetList = new ArrayList<>();
        planetList.addAll(Arrays.asList(planets));

        // create ArrayAdpater using planet list
        listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,planets);

        // Set the ArrayAdapter as the ListView's Adapter
        mainListView.setAdapter(listAdapter);

        mainListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        mainListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                // to do something when items are selected | deselected

            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                getMenuInflater().inflate(R.menu.listview_menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                // take action when a menu item is clicked
                switch (item.getItemId()){
                    case R.id.list_delete:
                        deleteSelectedItems();
                        mode.finish();
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });
    }

    // delete selected items from list view
    private void deleteSelectedItems(){
        /* getting the checked items from the listview*/
        SparseBooleanArray checkedItemPositions = mainListView.getCheckedItemPositions();
        int itemCount = mainListView.getCount();

        for(int i = itemCount-1; i >=0 ; i--){
            if (checkedItemPositions.get(i)){
                listAdapter.remove(planetList.get(i));
            }
        }
        checkedItemPositions.clear();
        listAdapter.notifyDataSetChanged();

    }
}
