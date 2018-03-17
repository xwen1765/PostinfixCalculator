import java.util.*;
public class URCalculator {
	static convertPostfix conv = new convertPostfix();
	static String equation= "";
	static HashMap<String, Double> varible = new HashMap<>();
	static double anwser;
	
	public static void readEquation() throws InvalidOprentException, NotDefinedVarException, Divide0Exception {
		Scanner sc = new Scanner(System.in);
		do{
			System.out.println("Please input your command");
			System.out.print("> ");
			equation = sc.nextLine();
			if(!equation.equals("quit") && !equation.contains("clear") && !equation.contains("show all") && !equation.contains("get")) {
				if(convertPostfix.infixToPostfix(findEquation(equation)).contains("Not")) {
					System.out.println("> Not Well-Formed");
				}else {
					anwser = Calculator(postfixStingtoStack(convertPostfix.infixToPostfix(findEquation(equation))));
					if(equation.contains("=")) {
						storeMemory(findVariable(equation), anwser);
					}
					System.out.println("> "+ anwser);
				}
			}
			else if(equation.contains("clear all") ) {
				varible.clear();
				System.out.println("Cleared");
			}
			else if(equation.contains("show all")) {
				if(!varible.isEmpty()) {
					for (String name: varible.keySet()){
		            String key = name;
		            String value = varible.get(name).toString();  
		            System.out.printf("%s  %s\n", key, value);  
					} 
				}else System.out.println("Nothing in the memory");
			}
			else if(equation.contains("get")) {
				String[] arr = equation.split(" ");
				String element = arr[arr.length-1];
				System.out.println(element);
				System.out.println(varible.get(element));
			}
			else if(equation.contains("clear" )&& !equation.contains("all")) {
				String[] arr = equation.split(" ");
				String element = arr[arr.length-1];
				varible.remove(element);
				System.out.println(element + " has cleared");
			}
		}while(!equation.equals("quit"));
		System.out.println("URCalculator closed");
		sc.close();
	}
	
	public static ArrayList<String> postfixStingtoStack(String str) {
		ArrayList<String> elem = new ArrayList<>();
		String[] strArray = str.split(" ");
		for (String s : strArray)
			elem.add(s);
		return elem;
	}
	
	public static String findEquation(String str) {
		str = str.replace(" ", "");
		String[] variable = str.split("=");
		return variable[variable.length-1];
	}
	
	public static String[] findVariable(String str) {
		str = str.replace(" ", "");
		String[] all = str.split("=");
		String[] variable = new String[all.length-1];
		for(int i = 0; i < all.length-1; i++) {
			variable[i] = all[i];
		}
		return variable;
	}
	
	public static void storeMemory(String[] str, double equation) {
		for(String s : str) {
			varible.put(s,equation);
		}
	}
	
	public static double Calculator(ArrayList<String> elem) throws InvalidOprentException, NotDefinedVarException, Divide0Exception{
		try {
		Stack<Double> cal = new Stack<Double>();
		for (int i = 0; i < elem.size(); i++) {
			String indexElem = elem.get(i);
			if (NumType(indexElem)) {
				cal.push(Double.parseDouble(indexElem));
			} else if(!isOpreant(indexElem)) {
				if(varible.containsKey(indexElem)) {
					cal.push(varible.get(indexElem));
				}else {
					throw new NotDefinedVarException();
				}
			}
			else{
				double val1 = cal.pop();
				double val2 = cal.pop();
				switch (indexElem) {
				case "+":
					cal.push(val2 + val1);
					break;
				case "-":
					cal.push(val2 - val1);
					break;
				case "*":
					cal.push(val2 * val1);
					break;
				case "/":
					if(val1 != 0) {
						cal.push(val2 / val1);
					}else {
						throw new Divide0Exception();
					}
					break;
				case "^":
					cal.push(Math.pow(val2, val1));
					break;
				default:
					break;
				}
			}
		}
		return cal.pop();
		}catch(EmptyStackException e) {
			throw new InvalidOprentException();
		}
	}
	public static boolean NumType(String s) {
		try {
			@SuppressWarnings("unused")
			Double a = Double.parseDouble(s);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public static boolean isOpreant(String s) {
		return s.contains("+") || s.contains("-") ||s.contains("*")||s.contains("/")||s.contains("^");
	}
	
	public static void main(String[] args) throws InvalidOprentException, NotDefinedVarException, Divide0Exception {
		readEquation();
	}
}
