package com.example.a1474672.fragment2;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

    public class detailFrag extends Fragment implements View.OnClickListener {
        private final static String TAG = "mTag";
        private View mRootView;
        // private FragmentOneInterface mCallback;
        private EditText EditText1;
        private TextView mTextView;
        private Button mButton;
        private detailFragInterface mCallback;
        private int fragNum;
        public detailFrag() {
        }

        @Override
        public void setArguments(@Nullable Bundle args) {
            super.setArguments(args);
        }

        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            Log.i(TAG, "onAttach");

            try {
                mCallback = (detailFragInterface) context;
            } catch (ClassCastException e) {
                throw new ClassCastException(context.toString()
                        + " must implement FragmentOneInterface");
            }
        }

        public static com.example.a1474672.fragment2.detailFrag newInstance() {
            com.example.a1474672.fragment2.detailFrag fragment = new com.example.a1474672.fragment2.detailFrag();
            return fragment;
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {
            mRootView = inflater.inflate(R.layout.detailfragment, container, false);
            EditText1 = (EditText) mRootView.findViewById(R.id.editText);
            mTextView = (TextView) mRootView.findViewById(R.id.textView);
            mButton = (Button) mRootView.findViewById(R.id.button);
            mButton.setOnClickListener(this);
            return mRootView;
        }
        @Override
        public void onClick(View v) {
            Log.i(TAG, "onClick fragNum: " + fragNum);
            mCallback.goToHome(fragNum-1, EditText1.getText().toString());
        }
        public void updateText(String i)
        {
            mTextView.setText(i);
        }
        public void updateFragNum(int i)
        {
            fragNum = i;
        }
        public void recallData(int i, String[] array)
        {
            EditText1.setText( array[i-1] );
        }
        public interface detailFragInterface{
            void goToHome(int i, String m);
        }
    }

