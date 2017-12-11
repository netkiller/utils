/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.netkiller.ipo.output;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author neoch
 */
public class JsonOutput implements OutputInterface {

    private List<String> colume = new ArrayList<String>();


    public JsonOutput(List<String> colume) {
        this.colume = colume;
    }
    @Override
    public void open() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void write(String output) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String[] array = output.split(",");
        List<String> tmpList = new ArrayList<String>(Arrays.asList(array));
        Map<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < this.colume.size(); i++) {
            map.put(this.colume.get(i), tmpList.get(i));
        }
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        System.out.println(gson.toJson(map));
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
