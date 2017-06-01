package com.example.kennethelee.nammuk_app;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.fatsecret.platform.model.CompactFood;
import com.fatsecret.platform.model.CompactRecipe;
import com.fatsecret.platform.model.Food;
import com.fatsecret.platform.model.Recipe;
import com.fatsecret.platform.services.Response;
import com.fatsecret.platform.services.android.Request;
import com.fatsecret.platform.services.android.ResponseListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2017-05-21.
 */

public class FoodDicActivity extends ListActivity {

    Button cancelbtn;
    Button searchfoodbtn;
    Button makemyfoodbtn;
    Button camerabtn;
    Button myfoodbtn;
    private Spinner spinner;
    String key = "9a4581d764db42ff9846e68c6cf716b9";
    String secret = "92719520bd1b49bbac9bc7605849e476";
    String query;
    EditText searchtext;


    protected void onCreate(Bundle savedInstanceState) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Listener listener = new Listener();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_dic);

        //zxing code 예제에 있어서 추가
        final Activity activity = this;


        //list array로 받아서 보여주기(DB로 추후에 받아오는 것으로 변경해야함)
        //generate list
        //ArrayList<String> list = new ArrayList<String>();
//        list.add("item1");
//        list.add("item2");
//        list.add("item3");
//        list.add("item4");
//        list.add("item5");

        //instantiate custom adapter
        //MyCustomAdapter_withregister adapter = new MyCustomAdapter_withregister(list, this);


        //handle listview and assign adapter
        //ListView lView = (ListView)findViewById(list);
        //lView.setAdapter(adapter);


        //취소 버튼은 나가기
        cancelbtn = (Button) findViewById(R.id.button_fooddic_cancel);
        cancelbtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //검색 버튼
        searchfoodbtn = (Button) findViewById(R.id.button_fooddic_search);
        searchfoodbtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                //검색기능
                //Toast.makeText(FoodDicActivity.this, "검색기능입니다", Toast.LENGTH_SHORT).show();
                searchtext = (EditText) findViewById(R.id.SearchText);
                query = searchtext.getText().toString();
                if(TextUtils.isEmpty(query)){
                    Toast.makeText(FoodDicActivity.this, "검색어가 없습니다", Toast.LENGTH_SHORT).show();
                } else {
                    //검색요청
                    Request req = new Request(key, secret, listener);
                    req.getFoods(requestQueue, query, 0);
                }
            }
        });

        //카메라 버튼
        camerabtn = (Button) findViewById(R.id.button_fooddic_camera);
        camerabtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                //카메라기능


                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();

            }
        });



        //내 메뉴 만들기 버튼
        makemyfoodbtn = (Button) findViewById(R.id.button_fooddic_makemy);
        makemyfoodbtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                //내 메뉴 만드는 레이아웃
                Toast.makeText(FoodDicActivity.this, "마이메뉴기능입니다", Toast.LENGTH_SHORT).show();
                //내 음식 화면 보여주기
                Intent intent = new Intent(FoodDicActivity.this, MyFoodAddRecordActivity.class);
                startActivity(intent);

            }
        });

        myfoodbtn = (Button) findViewById(R.id.button_fooddic_myfood);
        myfoodbtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                //내 메뉴 만드는 레이아웃
                Toast.makeText(FoodDicActivity.this, "내식단기능입니다", Toast.LENGTH_SHORT).show();
                //내 음식화면 보여주기
                Intent intent = new Intent(FoodDicActivity.this, MyFoodActivity.class);
                startActivity(intent);

            }
        });

        //dropdown 메뉴 구현
        spinner = (Spinner) findViewById(R.id.spinner_fooddic);
        spinner.setOnItemSelectedListener(new FoodDicActivity.ItemSelectedListener());
    }

    public class ItemSelectedListener implements AdapterView.OnItemSelectedListener {

        //get strings of first item
        String firstItem = String.valueOf(spinner.getSelectedItem());

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            if (firstItem.equals(String.valueOf(spinner.getSelectedItem()))) {
                // ToDo when first item is selected
            } else {
                Toast.makeText(parent.getContext(),
                        "You have selected : " + parent.getItemAtPosition(pos).toString(),
                        Toast.LENGTH_LONG).show();
                // Todo when item is selected by the user
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg) {

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Log.d("MainActivity", "Cancelled scan");
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Log.d("MainActivity", "Scanned");
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    class Listener implements ResponseListener {
        @Override
        public void onFoodListRespone(Response<CompactFood> response) {
            System.out.println("TOTAL FOOD ITEMS: " + response.getTotalResults());

            List<CompactFood> foods = response.getResults();
            //This list contains summary information about the food items
            ArrayList<String> list = new ArrayList<String>();
            System.out.println("=========FOODS============");
            for (CompactFood food: foods) {

                list.add(foods.toString());

                System.out.println(food.getName());
            }

        }

        @Override
        public void onRecipeListRespone(Response<CompactRecipe> response) {
            System.out.println("TOTAL RECIPES: " + response.getTotalResults());

            List<CompactRecipe> recipes = response.getResults();
            System.out.println("=========RECIPES==========");
            for (CompactRecipe recipe: recipes) {
                System.out.println(recipe.getName());
            }
        }

        @Override
        public void onFoodResponse(Food food) {
            System.out.println("FOOD NAME: " + food.getName());
        }

        @Override
        public void onRecipeResponse(Recipe recipe) {
            System.out.println("RECIPE NAME: " + recipe.getName());
        }
    }

}


