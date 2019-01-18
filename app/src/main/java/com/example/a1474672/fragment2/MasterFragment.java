package com.example.a1474672.fragment2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;
import android.content.Context;
public class MasterFragment extends Fragment implements View.OnClickListener {
        private final String TAG = "mTag";
        private View mRootView;
        private MasterInterface mCallback;
        private TextView mTextView1;
        private TextView mTextView2;
        private TextView mTextView3;

        public MasterFragment() {
        }

        public static com.example.a1474672.fragment2.MasterFragment newInstance() {
            com.example.a1474672.fragment2.MasterFragment fragment = new com.example.a1474672.fragment2.MasterFragment();
           /* fragment.adverb = new String[4];
            fragment.verb = new String[4];
            fragment.article = new String[4];
            fragment.noun = new String[4];*/
            Log.i(fragment.TAG, "onCreateView MasterFragment");
            return fragment;
        }

        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            Log.i(TAG, "onAttach");

            try {
                mCallback = (MasterInterface) context;
            } catch (ClassCastException e) {
                throw new ClassCastException(context.toString()
                        + " must implement FragmentOneInterface");
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {
            Log.i(TAG, "reached 1:");
            mRootView = inflater.inflate(R.layout.masterfragment, container, false);
            mTextView1 = (TextView) mRootView.findViewById(R.id.cb1);
            mTextView2 = (TextView) mRootView.findViewById(R.id.cb2);
            mTextView3 = (TextView) mRootView.findViewById(R.id.cb3);
            mTextView1.setOnClickListener(this);
            mTextView2.setOnClickListener(this);
            mTextView3.setOnClickListener(this);
            return mRootView;
        }
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.cb1) {
                Log.i(TAG, "onClick switched cb1");
                mCallback.goToFrag(1);
            }
            else if(v.getId() == R.id.cb2) {
                Log.i(TAG, "onClick switched cb2");
                mCallback.goToFrag(2);
            }
            else if(v.getId() == R.id.cb3) {
                Log.i(TAG, "onClick switched cb3");
                mCallback.goToFrag(3);
            }
        }
    public interface MasterInterface{
        void goToFrag(int id);
    }
    }
