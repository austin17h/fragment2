package com.example.a1474672.fragment2;

import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements MasterFragment.MasterInterface, detailFrag.detailFragInterface{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */

    MasterFragment mMasterFragment;
    detailFrag mDetailFragment;
//    DetamMasterFragmet = MasterFragment.newInstance();

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private String filename = "storage";
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    shelf oj;
    private final String KEY = "data";
    private SharedPreferences shared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shared = getSharedPreferences(filename, MODE_PRIVATE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        oj = new shelf();
        String retrieve = shared.getString(KEY, "");
        if(!retrieve.equals(""))
        {
            Gson thistime = new Gson();
            oj = thistime.fromJson(retrieve, shelf.class);
        }
       // Log.i(KEY, "" + oj.getStuff()[0]);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    mMasterFragment = MasterFragment.newInstance();
                    return mMasterFragment;
                case 1:
                    mDetailFragment = detailFrag.newInstance();
                    return mDetailFragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    public void goToFrag(int id) {
        mViewPager.setCurrentItem(id);
        mDetailFragment.updateFragNum(id);
        switch (id) {
            case 1:
                mDetailFragment.updateText("Clipboard 1");
                mDetailFragment.recallData(id, oj.getStuff());
                break;
            case 2:
                mDetailFragment.updateText("Clipboard 2");
                mDetailFragment.recallData(id, oj.getStuff());
                break;
            case 3:
                mDetailFragment.updateText("Clipboard 3");
                mDetailFragment.recallData(id, oj.getStuff());
        }
    }
    public void goToHome(int i, String m){
        oj.getStuff()[i] = m;
        mViewPager.setCurrentItem(0);
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor editor = shared.edit();
        Gson gson = new Gson();
        String serializedData = gson.toJson(oj);
        editor.putString(KEY, serializedData );
        editor.commit();
    }
}
