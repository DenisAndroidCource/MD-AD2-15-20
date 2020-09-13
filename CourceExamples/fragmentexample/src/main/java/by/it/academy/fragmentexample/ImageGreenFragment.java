package by.it.academy.fragmentexample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class ImageGreenFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        Bundle bundle = getArguments();
//        bundle.getInt("KEY");

        return inflater.inflate(R.layout.fragment_image_green, container, false);
    }

    private void addFragment() {
        FragmentManager childFragmentManager = getChildFragmentManager();
    }
}
