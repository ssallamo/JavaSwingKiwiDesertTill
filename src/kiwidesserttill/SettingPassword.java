package kiwidesserttill;

/**
 * This class defines about password limited valiables
 * @author HJS
 * @version 2016. 8. 8.
 */ 
public interface SettingPassword {
    
    //1. minimum 4
    //2. maximum 6
    //3. should have 1 special caractor
    //4. should have 1 lower letter
    //5. should have 1 capital letter
    //6. should have 1 number
    
    int PASSWORD_MINIMUM_LENGTH=4;
    int PASSWORD_MAXIMUM_LENGTH=6;
    
    String PASSWORD_SPECIAL_CHARACTER ="!@#$%^&*()-=_+";  
    
    int PASSWORD_SPECIAL_CHARACTER_COUNT=1;    
    int PASSWORD_LOWER_LETTER_COUNT=1;
    int PASSWORD_CAPITAL_LETTER_COUNT=1;
    int PASSWORD_NUMBER_COUNT=1;
}
