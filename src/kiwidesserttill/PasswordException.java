package kiwidesserttill;

public class PasswordException extends Exception{
    
    PasswordException(String message)
    {
        super(message);
    }
    PasswordException(Throwable cause)
    {
        super(cause);
    }
    PasswordException(String message, Throwable cause)
    {
        super(message, cause);
    }    
    
    public static boolean isValidPassword(String password) throws PasswordException
    {
        //1. minimum 8
        if (password.length() <SettingPassword.PASSWORD_MINIMUM_LENGTH) 
            throw new PasswordException("Password length is less than " + SettingPassword.PASSWORD_MINIMUM_LENGTH +" characters");
       
        //2. maximum 12        
        if (password.length() >SettingPassword.PASSWORD_MAXIMUM_LENGTH) 
            throw new PasswordException("Password length is over than " + SettingPassword.PASSWORD_MAXIMUM_LENGTH +" characters");
        
        int spcCounter = 0;   
        int lowCounter = 0;   
        int capCounter = 0;
        int numCounter = 0;
        for(int i = 0; i < password.length(); i++){
            
            //3. should have 1 special caractor[32-47]     
            if((SettingPassword.PASSWORD_SPECIAL_CHARACTER.indexOf(String.valueOf(password.charAt(i))))>-1){
                spcCounter ++;
            }
            //4. should have 1 lower caractor[97-122]     
            else if((password.charAt(i) >= 97) && (password.charAt(i) <= 122)){
                lowCounter ++;
            }
            //5. should have 1 capital caractor[65-90]     
            else if((password.charAt(i) >= 65) && (password.charAt(i) <= 90)){
                capCounter ++;
            }
            //6. should have 1 number[48-57]     
            else if((password.charAt(i) >= 48) && (password.charAt(i) <= 57)){
                numCounter ++;
            }
        }
            
        if (spcCounter < SettingPassword.PASSWORD_SPECIAL_CHARACTER_COUNT) 
            throw new PasswordException("Password should have atleast "+ SettingPassword.PASSWORD_SPECIAL_CHARACTER_COUNT +" special characters");
           
        if (lowCounter < SettingPassword.PASSWORD_LOWER_LETTER_COUNT) 
            throw new PasswordException("Password should have atleast "+ SettingPassword.PASSWORD_LOWER_LETTER_COUNT +" lower letters");
           
        if (capCounter < SettingPassword.PASSWORD_CAPITAL_LETTER_COUNT) 
            throw new PasswordException("Password should have atleast "+ SettingPassword.PASSWORD_CAPITAL_LETTER_COUNT +" capital letters");
           
        if (numCounter < SettingPassword.PASSWORD_NUMBER_COUNT) 
            throw new PasswordException("Password should have atleast "+ SettingPassword.PASSWORD_NUMBER_COUNT +" numbers");
          
        return  true;               
    }    
}