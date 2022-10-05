import java.util.regex.Pattern;
public class ControleDeSaisie {
	
	//verifier l'email
	public static boolean isEmailValid(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";
                              
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
	
	//verifier Alphabetique
	public static boolean isAlpha(String name) {
        return name.matches("[a-zA-Z\s]+");
    }
	
	//verifier numerique
	public static boolean isNumber(String name) {
        return name.matches("[0-9]+");
    }
	
	//verifier CIN
	public static boolean verifCin(String cin) {
		return cin.length() == 8 && (isNumber(cin)) && ((cin.charAt(0) == '0')||(cin.charAt(0) == '1'));
	}
	
	//verifier RPPS
	public static boolean verifRPPS(String rpps) {
		return rpps.length() == 12 && (isNumber(rpps)) ;
	}
	
	
	//verifier Tel
	public static boolean verifTel(String tel) {
		return (tel.length() == 8) && ((tel.charAt(0) == '5') || (tel.charAt(0) == '2') || (tel.charAt(0) == '9')) && (isNumber(tel));
	}
	
	public static boolean verifDate(String date) {
		String dateRegEx="(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/((19|20)\\d\\d)";
        return Pattern.matches(dateRegEx, date) && (date.length() == 10);  
	}

}
