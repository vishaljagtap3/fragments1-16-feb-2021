package in.bitcode.fragments1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText mEdtCount;
    private Button mBtnReset;

    private FragmentManager mFragmentManager;
    private CounterFragment mCounterFragment1, mCounterFragment2;
    private Button mBtnAddFragment, mBtnRemoveFragment;

    private int mCount = 0;

    private ArrayList<CounterFragment> mListCounterFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {

        mListCounterFragments = new ArrayList<CounterFragment>();

        mEdtCount = findViewById(R.id.edtCount);
        mBtnReset = findViewById(R.id.btnReset);

        mBtnReset.setOnClickListener(new BtnResetClickListener());

        mFragmentManager = getSupportFragmentManager();
        mCounterFragment1 = (CounterFragment) mFragmentManager.findFragmentById(R.id.counterFragment1);
        mCounterFragment2 = (CounterFragment) mFragmentManager.findFragmentById(R.id.counterFragment2);

        mBtnAddFragment = findViewById(R.id.btnAddFragment);
        mBtnRemoveFragment = findViewById(R.id.btnRemoveFragment);

        mBtnAddFragment.setOnClickListener(new BtnAddFragmentClickListener());
        mBtnRemoveFragment.setOnClickListener(new BtnRemoveFragmentClickListener());
    }

    private class BtnAddFragmentClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            CounterFragment newCounterFragment = new CounterFragment();

            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

            fragmentTransaction.add(
                    R.id.mainContainer,
                    newCounterFragment,
                    "counterFragment" + (++mCount)
            );
            //fragmentTransaction.remove(newCounterFragment);
            fragmentTransaction.addToBackStack("name for transaction");
            fragmentTransaction.commit();

            mListCounterFragments.add(newCounterFragment);

        }
    }

    private class BtnRemoveFragmentClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if(mListCounterFragments.size() == 0 ) {
                mt("Nothing to remove");
                return;
            }

            mFragmentManager.beginTransaction()
                    .remove(mListCounterFragments.get( mListCounterFragments.size()-1))
                    .addToBackStack(null)
                    .commit();

            mListCounterFragments.remove( mListCounterFragments.size()-1);

        }
    }

    private class BtnResetClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            int count = Integer.parseInt(mEdtCount.getText().toString());
            mCounterFragment1.setCount(count);
            mCounterFragment2.setCount(count);
        }
    }

    private void mt(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}