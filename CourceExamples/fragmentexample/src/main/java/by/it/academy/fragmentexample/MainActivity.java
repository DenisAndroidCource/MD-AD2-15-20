package by.it.academy.fragmentexample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

//"A" -> "B"

public class MainActivity extends AppCompatActivity implements OnActionController{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FragmentManager fragmentManager = getSupportFragmentManager();
        findViewById(R.id.addFragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction1 = fragmentManager.beginTransaction();
                transaction1.add(R.id.dynamicFragmentContainer, new ImageBlackFragment(), "TAG1");
//                transaction1.addToBackStack(null);
                transaction1.commit();

                Bundle bundle = new Bundle();
                bundle.putInt("KEY", 1);

                FragmentTransaction transaction2 = fragmentManager.beginTransaction();

                ImageRedFragment fragment = new ImageRedFragment();
                fragment.setArguments(bundle);

                transaction2.add(R.id.dynamicFragmentContainer, fragment, "TAG2");
                transaction2.addToBackStack(null);
                transaction2.commit();

//                fragmentManager.findFragmentById(R.id.dynamicFragmentContainer)
                fragmentManager.findFragmentByTag("TAG2");
            }
        });

        fragmentManager.popBackStack("B", 1);
    }

    @Override
    public void foo(int iconId) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag("TAG2");



        Log.d("MAINACTIVITY", "The method has been called");
    }
}
