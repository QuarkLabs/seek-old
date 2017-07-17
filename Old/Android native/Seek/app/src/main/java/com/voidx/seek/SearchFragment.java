package com.voidx.seek;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;

public class SearchFragment extends Fragment {
    private OnFragmentInteractionListener mListener;

    private View mView;
    private Context context;

    private EditText questionEditText;
    private EditText tagsEditText;
    private DefaultButton addButton;
    private TagContainerLayout subjectTCL;
    private DefaultButton searchButton;

    ArrayList<String> subjectTags = new ArrayList<>();

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        mView = view;
        context = mView.getContext();

        questionEditText = mView.findViewById(R.id.question_edit_text);
        tagsEditText = mView.findViewById(R.id.tags_edit_text);
        addButton = mView.findViewById(R.id.add_button);

        subjectTCL = mView.findViewById(R.id.subject_tcl);
        subjectTCL.setTags(subjectTags);
        subjectTCL.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(final int position, String text) {
                subjectTCL.removeTag(position);
            }

            @Override
            public void onTagLongClick(int position, String text) {

            }

            @Override
            public void onTagCrossClick(int position) {

            }
        });

        subjectTCL.addTag("Oshan");
        subjectTCL.addTag("Ivantha");
        subjectTCL.addTag("Mudannayake");

        searchButton = mView.findViewById(R.id.search_button);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onSearchFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onSearchFragmentInteraction(Uri uri);
    }
}
