package base;

import java.util.HashMap;

/**
 * @Author: janven
 * @Descrption: Created in ${Time} on 2017/12/18.
 */
public class HttpResultMap extends HashMap<String,Object>{

    public HttpResultMap(){
        super();
    }

    public void setSuccessObject(Object result){

        this.put("data",result);
        this.put("message","SUCCESS");
        this.put("code",0);

    }


    public void setFailureMessage(String msg){

        this.put("data",null);
        this.put("message",msg);
        this.put("code",-1);
    }


}
