package com.tmsnith.emotionsense;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.tmsnith.emotionsense.Fragments.imageFragment;
//import com.tmsnith.emotionsense.Fragments.textFragment;

public class MainActivity extends AppCompatActivity {

    private ViewPager pager;
    private TabLayout tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.v("hack","hjk");

        tab=(TabLayout)findViewById(R.id.profile_tab);
        pager=(ViewPager)findViewById(R.id.profile_viewpager);
        pager.setOffscreenPageLimit(1);



        MyPagerAdapter adapter=new MyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        tab.setupWithViewPager(pager);

    }


    class MyPagerAdapter extends FragmentStatePagerAdapter {

        @Override
        public CharSequence getPageTitle(int position) {
            String str="Image";
            /*
            switch (position){
                case 0: str="Text";
                    break;
                case 1: str="Image";
                    break;
            }
            */
            return str;
        }

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment=null;

            fragment= imageFragment.get();


            return fragment;
        }

        @Override
        public int getCount() {
            return 1;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.homepage_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.contact) {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            String uriText = "mailto:" +
                    Uri.encode("narendra.dodwaria@gmail.com")
                    +"?subject=" + Uri.encode("Feedback / Reporting a Bug") + "&body=" +
                    Uri.encode("Hello developers, \nI want to report a bug/give feedback corresponding to this app. \n\n.....\n\n-Your name");

            Uri uri = Uri.parse(uriText);
            intent.setData(uri);
            startActivity(Intent.createChooser(intent, "Send Email"));

            return true;
        }else if(id==R.id.about){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(String.format("%1$s", getString(R.string.app_name)));
            builder.setMessage(getResources().getText(R.string.about_us));
            builder.setPositiveButton("OK", null);
            //builder.setIcon(R.drawable.app_icon);
            AlertDialog welcomeAlert = builder.create();
            welcomeAlert.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
