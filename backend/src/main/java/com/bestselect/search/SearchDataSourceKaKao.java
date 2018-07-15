package com.bestselect.search;

import com.bestselect.book.Book;
import com.bestselect.utils.ParameterStringBuilder;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;
import com.bestselect.search.*;


public class SearchDataSourceKaKao implements SearchDataSource{

    public SearchResult findBook(String keyword, SearchDataSource.INFO info, Map<String, String> parameters ) {


        SearchResult re = new SearchResult();
        List<Book> books = new ArrayList<Book>();
        SearchMeta search_meta = new SearchMeta();

        try {
            // req
            String content = httpGetRequest(parameters);

            // parse
            JSONParser parser = new JSONParser();

            JSONObject jsonObject = (JSONObject) parser.parse(content);
            JSONArray jsonArray = (JSONArray) jsonObject.get("documents");
            JSONObject meta_obj = (JSONObject) jsonObject.get("meta");

            // meta
            search_meta.setIs_end(Boolean.parseBoolean(meta_obj.get("is_end").toString()));
            search_meta.setPageable_count(Long.parseLong(meta_obj.get("pageable_count").toString()));
            search_meta.setTotal_count(Long.parseLong(meta_obj.get("total_count").toString()));

            // books
            for (int i = 0; i < jsonArray.size(); i++) {

                JSONObject obj = (JSONObject) jsonArray.get(i);

                String authors = String.join(",", (JSONArray)parser.parse(obj.get("authors") + ""));
                String translators = String.join(",", (JSONArray)parser.parse(obj.get("translators") + ""));

                books.add(new Book(
                        obj.get("isbn").toString(),
                        obj.get("title").toString(),
                        obj.get("price").toString(),
                        obj.get("url").toString(),
                        authors,
                        obj.get("barcode").toString(),
                        obj.get("category").toString(),
                        obj.get("contents").toString(),
                        obj.get("datetime").toString(),
                        obj.get("ebook_barcode").toString(),
                        obj.get("publisher").toString(),
                        obj.get("sale_price").toString(),
                        obj.get("sale_yn").toString(),
                        obj.get("status").toString(),
                        obj.get("thumbnail").toString(),
                        translators
                ));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        //sort
        if(parameters.containsKey("sort")) {
            String type = parameters.get("sort");
            String order = parameters.get("order");

            if(type.equals("title")) {
                System.out.println("sort title");
                if(order.equals("asc")){
                    books.sort(new Comparator<Book>() {
                        @Override
                        public int compare(Book o1, Book o2) {
                            return o1.getTitle().compareToIgnoreCase(o2.getTitle()) > 0 ? 1 : -1;
                        }
                    });
                }else{
                    books.sort(new Comparator<Book>() {
                        @Override
                        public int compare(Book o1, Book o2) {
                            return o1.getTitle().compareToIgnoreCase(o2.getTitle()) < 0 ? 1 : -1;
                        }
                    });
                }
            }else if(type.equals("datetime")){
                //System.out.println("sort datetime");

                if(order.equals("asc")) {
                    books.sort(new Comparator<Book>() {
                        @Override
                        public int compare(Book o1, Book o2) {
                            return o1.getDatetime().compareToIgnoreCase(o2.getDatetime()) > 0 ? 1 : -1;
                        }
                    });
                }else{
                    books.sort(new Comparator<Book>() {
                        @Override
                        public int compare(Book o1, Book o2) {
                            return o1.getDatetime().compareToIgnoreCase(o2.getDatetime()) < 0 ? 1 : -1;
                        }
                    });
                }
            }
        }

        // make re
        re.setBooks(books);
        re.setMeta(search_meta);

        return re;
    }


    public String httpGetRequest(Map parameters) throws URISyntaxException, IOException, InterruptedException {

        String api_url = "https://dapi.kakao.com/v2/search/book?" + ParameterStringBuilder.getParamsString(parameters);

        URL url = new URL(api_url);

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        // header
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "KakaoAK 4ecada674fefb64b4da5d10568a4891c");

        // res
        int status = con.getResponseCode();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();

        return content.toString();
    }


}
