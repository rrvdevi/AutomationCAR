package com.automation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LoadText {

    public List loadTextValues(){
        List text_values = new ArrayList();
        String line;
        try {
            String temp = " ";
            int i=0;
            BufferedReader inputStream = new BufferedReader(new FileReader("src/car_input.txt"));
            while ((line = inputStream.readLine()) != null) {
                String[] aux = line.split("\\s");
                int j=0;
                for(String s : aux){
                    if(s.matches("^[A-Z][A-Z]*[0-9][0-9]+$")){
                        temp=s;
                        j=i+1;
                    }
                    if(s.matches("^[A-Z][A-Z]*[0-9][0-9]*[A-Z][A-Z][A-Z]+$")){
                        text_values.add(s);

                    }
                    if(j==i&&s.matches("^[A-Z][A-Z][A-Z.]+$")){
                        temp=temp.concat(s);
                        if(temp.contains(".")){
                            temp= temp.replace(".","");
                        }
                        text_values.add(temp);
                    }
//                    if(j==i&&s.matches("^[A-Z][A-Z][A-Z]+$")){
//                        temp=temp.concat(s);
//                        text_values.add(temp);
//                    }
                    i++;
                }

            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return text_values;
    }

}
