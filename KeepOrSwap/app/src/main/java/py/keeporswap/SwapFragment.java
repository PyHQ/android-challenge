package py.keeporswap;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SwapFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SwapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SwapFragment extends Fragment {
    private static final String ARG = "snippet";
    private static final String KEEP = "keep";
    private static final String SWAP = "swap";
    private Snippet snippet;
    private Button keepButton, swapButton, nextButton;
    private MainActivity parent;
    private TextView text;

    public SwapFragment() {
        // Required empty public constructor
    }

    public static SwapFragment newInstance(Snippet snip) {
        SwapFragment fragment = new SwapFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG, snip);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            snippet = (Snippet) getArguments().getSerializable(ARG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View parentView =  inflater.inflate(R.layout.fragment_swap, container, false);
        text = parentView.findViewById(R.id.code_snippet);
        showSnippet(text, snippet);
        keepButton = parentView.findViewById(R.id.keep_button);
        swapButton = parentView.findViewById(R.id.swap_button);
        nextButton = parentView.findViewById(R.id.next_button);
        keepButton.setOnClickListener(view -> resultChosen(KEEP));
        swapButton.setOnClickListener(view -> resultChosen(SWAP));
        return parentView;
    }

    private void showSnippet(TextView tv, Snippet snippet) {
        SpannableString span = new SpannableString(snippet.code);
        HighlightSpan highlight = new HighlightSpan();
        HighlightSpan highlight2 = new HighlightSpan();
        span.setSpan(highlight, snippet.indexFirst,
                snippet.indexFirst + snippet.wordFirst.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(highlight2, snippet.indexSecond,
                snippet.indexSecond + snippet.wordSecond.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(span);
    }

    public void resultChosen(String result) {
        keepButton.setEnabled(false);
        swapButton.setEnabled(false);
        if (snippet.swap && result.equals(SWAP)) {
            swapButton.setBackgroundResource(R.drawable.button_box_green);
            swapSnippet(text, snippet);
        } else if (!snippet.swap && result.equals(SWAP)) {
            swapButton.setBackgroundResource(R.drawable.button_box_red);
        } else if (snippet.swap && result.equals(KEEP)) {
            keepButton.setBackgroundResource(R.drawable.button_box_red);
            swapSnippet(text, snippet);
        } else if (!snippet.swap && result.equals(KEEP)) {
            keepButton.setBackgroundResource(R.drawable.button_box_green);
        }
        nextButton.setEnabled(true);
        nextButton.setVisibility(View.VISIBLE);
        nextButton.setOnClickListener(view -> parent.loadNextFragment());
    }

    private void swapSnippet(TextView text, Snippet snippet) {
        StringBuilder s = new StringBuilder();
        s.append(snippet.code.substring(0, snippet.indexFirst));
        int indexFirst = s.length();
        s.append(snippet.wordSecond);
        s.append(snippet.code.substring(snippet.indexFirst+snippet.wordFirst.length(), snippet.indexSecond));
        int indexSecond = s.length();
        s.append(snippet.wordFirst);
        s.append(snippet.code.substring(snippet.indexSecond+snippet.wordSecond.length(), snippet.code.length()));

        SpannableString span = new SpannableString(s.toString());
        HighlightSpan highlight = new HighlightSpan();
        HighlightSpan highlight2 = new HighlightSpan();
        span.setSpan(highlight, indexFirst, indexFirst + snippet.wordSecond.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(highlight2, indexSecond, indexSecond + snippet.wordFirst.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.setText(span);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        parent = (MainActivity) context;
    }
}
