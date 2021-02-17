package in.bitcode.fragments1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CounterFragment extends Fragment {

    private TextView mTxtCount;
    private Button mBtnDecrement, mBtnIncrement;

    private int mCount = 0;

    public CounterFragment() {
        super();
    }

    public CounterFragment(int count) {
        super();
        mCount = count;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.counter_fragment, null);

        mTxtCount = view.findViewById(R.id.txtCount);
        mBtnDecrement = view.findViewById(R.id.btnDecrement);
        mBtnIncrement = view.findViewById(R.id.btnIncrement);

        if(getArguments() != null) {
            mCount = getArguments().getInt("count");
            mTxtCount.setText(mCount + "");
        }

        mBtnDecrement.setOnClickListener(new BtnDecrementClickListener());
        mBtnIncrement.setOnClickListener(new BtnIncrementClickListener());

        return view;

        /*
        TextView txtInfo = new TextView(getActivity());
        txtInfo.setLayoutParams( new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        txtInfo.setTextSize(50);
        txtInfo.setText("This is sample text");

        return txtInfo;
        */

    }

    public void setCount(int count) {
        mCount = count;
        mTxtCount.setText(mCount+"");
    }

    public int getCount() {
        return mCount;
    }

    private class BtnDecrementClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            mTxtCount.setText( --mCount + "");
        }
    }

    private class BtnIncrementClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            mTxtCount.setText( ++mCount + "");
        }
    }

}
