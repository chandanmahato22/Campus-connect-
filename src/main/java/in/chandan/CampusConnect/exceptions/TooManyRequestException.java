package in.chandan.CampusConnect.exceptions;

public class TooManyRequestException extends RuntimeException{
    public TooManyRequestException(String msg){
        super(msg);
    }
}
