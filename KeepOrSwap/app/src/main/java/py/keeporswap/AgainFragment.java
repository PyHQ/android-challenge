package py.keeporswap;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass to restart the game or exit the app
 */
public class AgainFragment extends Fragment {
    private MainActivity parentActivity;

    public AgainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View parent = inflater.inflate(R.layout.fragment_again, container, false);
        parent.findViewById(R.id.yes_button)
                .setOnClickListener(view -> parentActivity.again());
        parent.findViewById(R.id.no_button)
                .setOnClickListener(view -> parentActivity.requestFinish());
        return parent;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        parentActivity = (MainActivity) context;
    }
}
