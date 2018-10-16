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
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;




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

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    /***************************************************/

    private GridView listView;
    ListAdapter adapter;

    public String judul, src_asset, mp3;
    String[] product_title,product_image,product_price,product_save;
    String[] productimage1,productimage2,productimage3,productgender,productdesc;



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

        return view;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //LIST START
        product_title = getResources().getStringArray(R.array.products_titles);
        product_price = getResources().getStringArray(R.array.products_price);
        product_image = getResources().getStringArray(R.array.products_image);
        product_save = getResources().getStringArray(R.array.products_price_save);

        productimage1 = getResources().getStringArray(R.array.products_image1);
        productimage2 = getResources().getStringArray(R.array.products_image2);
        productimage3 = getResources().getStringArray(R.array.products_image3);
        productgender= getResources().getStringArray(R.array.gender);
        productdesc= getResources().getStringArray(R.array.description);

        listView = (GridView)getView().findViewById(R.id.listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Toast.makeText(getContext(),"Item",Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(getActivity(), ProductDetails.class);
                intent.putExtra("producttitle",product_title);
                intent.putExtra("productimage",product_image);
                intent.putExtra("position",position);
                intent.putExtra("productprice",product_price);
                intent.putExtra("productsave",product_save);

                intent.putExtra("productimage1",productimage1);
                intent.putExtra("productimage2",productimage2);
                intent.putExtra("productimage3",productimage3);
                intent.putExtra("productgender",productgender);
                intent.putExtra("productdesc",productdesc);


                /*producttitleString url = intent.getExtras().getString("userurl");
                intent.putExtra("userurl", url);*/




                try{
                    startActivity(intent);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                //startActivity(intent);

            }

        });


        adapter= new ListAdapter(this,product_title,product_image,product_price,product_save);
        listView.setAdapter(adapter);

        //LIST END




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

        public ListAdapter(CatalogoFragment context, String[] product_titles, String[] product_images, String[] product_prices, String[] product_save_prices) {
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
}
