package jimenez.midtermexamv2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import jimenez.midtermexamv2.fragments.ListViewFragment;

public class MainActivity extends AppCompatActivity {

    private ListViewFragment mListViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListViewFragment = ListViewFragment.newInstance();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, mListViewFragment)
                .commit();

    }


}
