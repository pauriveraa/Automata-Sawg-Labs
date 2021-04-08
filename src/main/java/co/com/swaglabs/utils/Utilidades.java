package co.com.swaglabs.utils;

public class Utilidades {

    public static int generateRandomNumber(int size, boolean status){
        if(status){
            return (int)Math.floor(Math.random()*size+1);
        }else{
            return (int)(Math.random()*size);
        }
    }
}
