package by.it.academy.fragmentexample;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ImageBlackFragment extends Fragment {

    private OnActionController onActionController;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnActionController) {
            onActionController = (OnActionController) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_image_black, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (onActionController != null) {
            onActionController.foo();
        }
    }

    @Override
    public void onDetach() {
        onActionController = null;
        super.onDetach();
    }
}
