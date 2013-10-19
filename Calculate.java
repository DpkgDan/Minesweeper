
public class Calculate {

	public static int roundToInt(double number){
		int periodIndex = 0;
		String unroundedNumber = "" + number;
		String roundedNumber = "";
		
		for (int counter = 0; counter < unroundedNumber.length(); counter++){
			if (unroundedNumber.charAt(counter) == '.'){
				periodIndex = counter;
				break;
			}
		}
		
		for (int counter = 0; counter < periodIndex; counter++){
			if (counter == (periodIndex - 1)){
				if (Integer.parseInt("" + unroundedNumber.charAt(counter+2)) >= 5){
					int tempNumber = 
							Integer.parseInt("" + unroundedNumber.charAt(counter));
					tempNumber++;
					
					roundedNumber += tempNumber;
				}
				else {
					roundedNumber += unroundedNumber.charAt(counter);
				}
			}
			else {
				roundedNumber += unroundedNumber.charAt(counter);
			}
		}
		int answer = Integer.parseInt(roundedNumber);
		return answer;
	}
}
