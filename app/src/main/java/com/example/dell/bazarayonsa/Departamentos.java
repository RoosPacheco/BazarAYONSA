package com.example.dell.bazarayonsa;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


public class Departamentos extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /***************************************************/

    private GridView listView;
    ListAdapter adapter;

    public String judul, src_asset, mp3;
    String[] depart_title,depart_image;


    public static Departamentos newInstance(String param1, String param2) {
        Departamentos fragment = new Departamentos();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_departamentos,
                container, false);

        //LIST START
        depart_title = getResources().getStringArray(R.array.nombres_departamentos);
        depart_image = getResources().getStringArray(R.array.images_departamentos);

        listView = view.findViewById(R.id.listviewD);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity(), Main2Activity.class);
                intent.putExtra("position",position);
                intent.putExtra("accion","catalogo");
                try{
                    startActivity(intent);
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }

        });


        adapter= new ListAdapter(this,depart_title,depart_image);
        listView.setAdapter(adapter);

        //LIST END


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);





    }

    public class ListAdapter extends BaseAdapter {
        // Declare Variables
        Context context;
        String[] depart_titles, depart_images;
        LayoutInflater inflater;

        public ListAdapter(Departamentos context, String[] depart_title, String[] depart_image) {
            this.context = getActivity();
            this.depart_titles = depart_title;
            this.depart_images = depart_image;

        }

        @Override
        public int getCount() {
            return depart_titles.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
// Declare Variables
            String imageget = depart_image[position];
            TextView departttitle;
            ImageView depatimage;

            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View itemView = inflater.inflate(R.layout.departamento_list, parent, false);

            departttitle = (TextView) itemView.findViewById(R.id.depa_title);

            depatimage = (ImageView) itemView.findViewById(R.id.depa_images);
            Picasso.with(context)
                    .load(imageget)
                    .into(depatimage);

            departttitle.setText(depart_titles[position]);


            return itemView;

        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {

    }


}
