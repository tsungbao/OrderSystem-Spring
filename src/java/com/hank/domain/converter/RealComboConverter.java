
package com.hank.domain.converter;

import com.hank.domain.item.CondimentDecorator;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.util.StrutsTypeConverter;

public class RealComboConverter extends StrutsTypeConverter {

    @Override
    public Object convertFromString(Map map, String[] strings, Class type) {
        Object returnVaule = null; 
        if(type.equals(ArrayList.class)){
             Map <String,Object> request = ( Map <String,Object> ) map.get("request");
             returnVaule = request.get("beChanged_desc");
        }else if (type.equals(CondimentDecorator.class)){
            Map <String,Object> request = ( Map <String,Object> ) map.get("request");
            returnVaule = request.get("beChanged_content");
        }else{
            throw new RuntimeException("轉換發生錯誤");
        }
        return returnVaule;
    }

    @Override
    public String convertToString(Map map, Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
