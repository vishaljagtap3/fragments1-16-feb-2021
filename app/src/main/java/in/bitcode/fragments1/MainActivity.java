package in.bitcode.fragments1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mEdtCount;
    private Button mBtnReset;

    private FragmentManager mFragmentManager;
    private CounterFragment mCounterFragment1, mCounterFragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        mEdtCount = findViewById(R.id.edtCount);
        mBtnReset = findViewById(R.id.btnReset);

        mBtnReset.setOnClickListener(new BtnResetClickListener());

        mFragmentManager = getSupportFragmentManager();
        mCounterFragment1 = (CounterFragment) mFragmentManager.findFragmentById(R.id.counterFragment1);
        mCounterFragment2 = (CounterFragment) mFragmentManager.findFragmentById(R.id.counterFragment2);
    }

    private class BtnResetClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            int count = Integer.parseInt(mEdtCount.getText().toString());
            mCounterFragment1.setCount(count);
            mCounterFragment2.setCount(count);
        }
    }
}