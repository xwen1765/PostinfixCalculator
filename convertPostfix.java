import java.util.Stack;
 public class convertPostfix {
	static int Prec(char ch) {
		switch (ch) {
		case '+':
		case '-':
			return 1;
		case '*':
		case '/':
			return 2;
		case '^':
			return 3;
		}
		return -1;
	}
	
	public static String infixToPostfix(String exp) {
		String result = new String("");
		String prem = "";
		exp = "0+" + exp;
		exp = exp.replace(" ", "");
		exp = exp.replaceAll("([+-])\\1{2,}", "$1");
		exp = exp.replace("+-", "-");
		exp = exp.replace("-+", "-");
		exp = exp.replace("(-", "(0-");
		Stack<Character> stack = new Stack<>();
		
		for (int i = 0; i < exp.length(); ++i) {
			char c = exp.charAt(i);
			if (Character.isLetterOrDigit(c)|| c=='.') {
				prem += c;
			}
			else if (c == '{') {
				stack.push(c);
			}
			else if (c == '}') {
				if(!stack.isEmpty() && stack.peek() != '{') {
					result += prem+" ";
					prem = ""; 
				}
				while (!stack.isEmpty() && stack.peek() != '{') {
					if (stack.peek() == '(' || stack.peek() == ')')
						return "Not Well-Formed";
					result += (" "+stack.pop()+" ");
				}
				if (stack.isEmpty()) {
					return "Not Well-Formed";
				} else if ((!stack.isEmpty() && stack.peek() != '{')) {
					return "Not Well-Formed";  
				}
				else
					stack.pop();
			}
			else if (c == '[') {
				stack.push(c);
			}
			else if (c == ']') {
				if(!stack.isEmpty() && stack.peek() != '[') {
					result += prem+" ";
					prem = ""; 
				}
				while (!stack.isEmpty() && stack.peek() != '[') {
					if (stack.peek() == '(' || stack.peek() == ')')
						return "Not Well-Formed";
					result += (" "+stack.pop()+" ");
				}
				if (stack.isEmpty()) {
					return "Not Well-Formed";
				} else if ((!stack.isEmpty() && stack.peek() != '[')) {
					return "Not Well-Formed";  
				}
				else
					stack.pop();
			}
			else if (c == '(') {
				stack.push(c);
			}
			else if (c == ')') {
				if(!stack.isEmpty() && stack.peek() != '(') {
					result += prem+" ";
					prem = ""; 
				}
				while (!stack.isEmpty() && stack.peek() != '(')
					result += (" "+stack.pop()+" ");
				if (stack.isEmpty()) {
					return "Not Well-Formed";
				} else if (!stack.isEmpty() && stack.peek() != '(')
					return "Not Well-Formed";  
				else
					stack.pop();
			}
			else  
			{	
				result += prem+" ";
				prem = "";
				while (!stack.isEmpty() && Prec(c) <= Prec(stack.peek())) {
					result += (" "+stack.pop()+" ");
				}
				stack.push(c);
			}
		}
		result += prem+" ";
		prem = ""; 
		while (!stack.isEmpty()) {
			if (stack.peek() == '(' || stack.peek() == ')' || stack.peek() == '[' || stack.peek() == ']'|| stack.peek() == '{' || stack.peek() == '}') {
				return "Not Well-Formed";
			} else
				result += (" "+stack.pop());
		}
		return result = result.replaceAll("\\s{1,}", " ");
	}
}