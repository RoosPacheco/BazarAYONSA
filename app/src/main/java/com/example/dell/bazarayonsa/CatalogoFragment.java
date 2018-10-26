package com.example.dell.bazarayonsa;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 *
 * to handle interaction events.
 * Use the {@link CatalogoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CatalogoFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //String ip = "192.168.1.91";

    String host = "https://bazarayonsa.000webhostapp.com";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    /***************************************************/

    private GridView listView;
    ListAdapter adapter;

    public String judul, src_asset, mp3;
    String[] product_title,product_image,product_price,product_price_oferta,product_save;
    String[] productimage1,productimage2,productimage3,productgender,productdesc;


    String carpeta;

    int position;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CatalogoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CatalogoFragment newInstance(String param1, String param2) {
        CatalogoFragment fragment = new CatalogoFragment();
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
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_catalogo,
                container, false);


        Intent i = getActivity().getIntent();

        Bundle extras = i.getExtras();


        if (extras != null) {
            position  =i.getExtras().getInt("position");

            switch (position){
                case 0:
                    carpeta = "muebles";
                    break;
                case 1:
                    carpeta = "LíneaBlanca";
                    break;
                case 2:
                    carpeta = "Colchones";
                    break;

            }

        }
        else{
            carpeta = "muebles";
        }





        //llamada a extraer url de imagenes
        new obtenerURLImages().execute();

        //carpeta = "muebles";

        return view;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        //llamada a extraer url de imagenes
        //new obtenerURLImages().execute();




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


    //List METHOD

    public class ListAdapter extends BaseAdapter {
        // Declare Variables
        Context context;
        String[] product_titles, product_images, product_prices, product_save_prices;
        LayoutInflater inflater;

        public ListAdapter(obtenerURLImages context, String[] product_titles, String[] product_images, String[] product_prices, String[] product_save_prices) {
            this.context = getActivity();
            this.product_titles = product_titles;
            this.product_images = product_images;
            this.product_prices = product_prices;
            this.product_save_prices = product_save_prices;
        }

        @Override
        public int getCount() {
            return product_titles.length;
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
            String imageget = product_images[position];
            TextView producttitle, productprice, productsave;
            ImageView productimage;

            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View itemView = inflater.inflate(R.layout.product_list, parent, false);

            producttitle = (TextView) itemView.findViewById(R.id.product_title);
            productprice = (TextView) itemView.findViewById(R.id.product_price);
            //productpriceoferta=(TextView) itemView.findViewById(R.id.product_price_oferta);
            productsave = (TextView) itemView.findViewById(R.id.product_price_save);
            productimage = (ImageView) itemView.findViewById(R.id.product_images);
            Picasso.with(context)
                    .load(imageget)
                    .into(productimage);

            producttitle.setText(product_titles[position]);
            productprice.setText(product_prices[position]);
            productsave.setText(product_save_prices[position]);


            return itemView;

        }
    }

    public class Wrapper
    {
        public ArrayList<String> urls;
        public ArrayList<String> titles;
        public ArrayList<String> prices;
        public ArrayList<String> saves;
        public ArrayList<String> details;
        public ArrayList<String> productimageO;
        public ArrayList<String> productimageT;
    }

    class obtenerURLImages extends AsyncTask<Void, Void, Wrapper> {


        @Override
        protected Wrapper doInBackground(Void... voids) {
            HttpURLConnection connection;

            String direccion = host+"/BazarAyonsa/extraer_imagenes.php";

            ArrayList<String> list = new ArrayList<String>();
            ArrayList<String> titles = new ArrayList<String>();
            ArrayList<String> prices = new ArrayList<String>();
            ArrayList<String> saves = new ArrayList<String>();
            ArrayList<String> details = new ArrayList<String>();

            ArrayList<String> productimage1 = new ArrayList<String>();
            ArrayList<String> productimage2 = new ArrayList<String>();



            Wrapper w = new Wrapper();


            try {
                // Log.d("umagen string", URLEncoder.encode("image=" + imagen, "UTF-8"));

                connection = (HttpURLConnection) new URL(direccion).openConnection();
                connection.setRequestMethod("POST");
                connection.setDoInput(true);

                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                writer.write("carpeta="+carpeta );
                writer.flush();
                writer.close();
                outputStream.close();


                connection.connect();
                InputStream is = (InputStream) connection.getContent();


                byte [] b = new byte[100000];//buffer
                Integer numBytes = is.read(b);// numero de bites que leyó
                //convertimos ese num de bites a una cadena
                String res = new String(b, 0,  numBytes, "utf-8");
                Log.d("array json", res);

                String data;
                JSONArray arr = new JSONArray(res);
                Log.d("longitud", String.valueOf(arr.length()));

                /*************/
                for(int i = 0; i < arr.length()-2; i++){

                    for (int j = 0; j < (arr.getJSONArray(i)).length(); j++) {
                        data = (arr.getJSONArray(i)).getString(j);
                        switch (i){
                            case 0:
                                data = host + "/BazarAyonsa/" + carpeta + "/" + data;
                                list.add(data);
                                Log.d("imagenes" + i, data);
                                break;
                            case 1:
                                titles.add(data);
                                Log.d("title" + i, data);
                                break;
                            case 2:
                                prices.add(data);
                                Log.d("precio" + i, data);
                                break;
                            case 3:
                                saves.add(data);
                                Log.d("save" + i, data);
                                break;
                            case 4:
                                details.add(data);
                                Log.d("details" + i, data);
                                break;
                        }

                    }

                }

                for(int i = 5; i < arr.length(); i++){
                    for (int j = 0; j < (arr.getJSONArray(i)).length(); j++) {
                        data = (arr.getJSONArray(i)).getString(j);
                        switch (i){
                            case 5:
                                data = host + "/BazarAyonsa/" + carpeta + "/" + data;
                                productimage1.add(data);
                                Log.d("productimage1" + i, data);
                                break;
                            case 6:
                                data = host + "/BazarAyonsa/" + carpeta + "/" + data;
                                productimage2.add(data);
                                Log.d("productimage2" + i, data);
                                break;
                        }

                    }

                }

                /**********/
                w.urls          = list;
                w.titles        = titles;
                w.prices        = prices;
                w.saves         = saves;
                w.details       = details;
                w.productimageO = productimage1;
                w.productimageT = productimage2;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return w;
        }

        @Override
        protected void onPostExecute(Wrapper list) {
            super.onPostExecute(list);

            //LIST START
            product_title = list.titles.toArray(new String[0]);
            product_price = list.prices.toArray(new String[0]);
            product_image = list.urls.toArray(new String[0]);
            product_save  = list.saves.toArray(new String[0]);

            productdesc   = list.details.toArray(new String[0]);

            productimage1 = list.productimageO.toArray(new String[0]);
            productimage2 = list.productimageT.toArray(new String[0]);

            listView = (GridView)getView().findViewById(R.id.listview);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    Toast.makeText(getContext(),"item",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), ProductDetails.class);
                    intent.putExtra("producttitle",product_title);
                    intent.putExtra("productimage",product_image);
                    intent.putExtra("position",position);
                    intent.putExtra("productprice",product_price);
                    intent.putExtra("productsave",product_save);

                    intent.putExtra("productimage1",productimage1);
                    intent.putExtra("productimage2",productimage2);

                    intent.putExtra("productdesc",productdesc);


                    try{
                        startActivity(intent);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }

                }

            });


            adapter= new ListAdapter(this,product_title,product_image,product_price,product_save);
            listView.setAdapter(adapter);

            //LIST END





        }
    }
}
