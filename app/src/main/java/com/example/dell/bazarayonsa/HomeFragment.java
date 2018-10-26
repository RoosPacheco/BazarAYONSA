package com.example.dell.bazarayonsa;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnStartEnterTransitionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    //String ip = "192.168.1.91";

    String host = "https://bazarayonsa.000webhostapp.com";

    ListView listView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        listView = (ListView)view.findViewById(R.id.lista);

        Log.d("crear","crear");
        new CargarPromociones().execute();


        // Inflate the layout for this fragment

        return view;
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

    class CargarPromociones extends AsyncTask<Void, Void, List> {

        @Override
        protected List doInBackground(Void... voids) {
            HttpURLConnection connection;

            //example.php es el php que envia el arreglo json
            String direccion = host+"/BazarAyonsa/promociones/example.php";

            List<String> list = new ArrayList<String>();

            try {
                connection = (HttpURLConnection) new URL(direccion).openConnection();
                connection.setRequestMethod("POST");
                connection.setDoInput(true);

                connection.connect();
                InputStream is = (InputStream) connection.getContent();
                byte [] b = new byte[100000];//buffer
                Integer numBytes = is.read(b);// numero de bites que leyó
                //convertimos ese num de bites a una cadena
                String res = new String(b, 0,  numBytes, "utf-8");
                Log.d("1", res);

                //"res" contiene la cadena json(que es un array ) de los nombres de la simagenes
                JSONArray arr = new JSONArray(res);

                for(int i = 0; i < arr.length(); i++){
                    list.add(host+"/BazarAyonsa/promociones/"+arr.getString(i));
                    Log.d("IMAGENES"+i, arr.getString(i));
                }




            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return list;
        }

        @Override
        protected void onPostExecute(List list) {
            super.onPostExecute(list);

            /*
             * Esto es para cagar el arreglo al list view
             * */
            //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, list);
            //


            ArrayList<String> imageURLArray = new ArrayList<String>(list);

            for(String s : imageURLArray)
                Log.d("ooo", s);



            ListAdapter imageAdapter = new ListAdapter(getContext(), imageURLArray );
            listView.setAdapter(imageAdapter);






        }
    }


}
