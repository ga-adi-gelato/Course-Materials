public class Practice {

    public static void main(String[] args){

        /**
         * Write a method called `divide152By`.
         * This method should accept one argument, a number, and should divide 152 by the given number.
         * For example, the divide152By result of 1 is `152/1` is 152. Your function should return the result.
         */
        System.out.println("divide152By");

        System.out.println("-------------");

        System.out.println(divide152By(3));
        System.out.println(divide152By(43));
        System.out.println(divide152By(8));

        System.out.println();

        //------------

        /**
         * Write a method called `thirdAndFirst`.
         * This method accepts two strings, and checks if the third letter of the first string is the same as
         * the first letter of the second string. It should be case insensitive,
         * meaning `thirdAndFirst("Apple","Peon")` should return true.
         */
        System.out.println("thirdAndFirst");

        System.out.println(thirdAndFirst("billygoat","LION"));
        System.out.println(thirdAndFirst("drEAMcaTCher","statue"));
        System.out.println(thirdAndFirst("Times","thyme"));

        System.out.println();

        //------------

        /**
         * Write a method called `transmogrifier`.
         * This method should accept three arguments, which you can assume will be numbers.
         * Your function should return the "transmogrified" result.
         * To transmogrify the values, you need to find the sum of the first two numbers
         * and raise that value to the power of the third number.
         */
        System.out.println("transmogrifier");
        System.out.println("-------------");

        System.out.println(transmogrifier(5, 4, 3));
        System.out.println(transmogrifier(13, 12, 5));
        System.out.println(transmogrifier(42, 13, 7));

        System.out.println();

        //------------

        /**
         * Bonus Challenge: Write a method called 'reverseString'.
         * This method should take one argument, a String.
         * The method should return a string with the order of the words reversed. Don't worry about punctuation
         */
        System.out.println("reverseString");
        System.out.println("-------------");

        System.out.println(reverseString("black cat"));
        System.out.println(reverseString("the cow jumped over the moon"));
        System.out.println(reverseString("I can ride my bike with no handlebars"));

        System.out.println();

        //------------

        /**
         * Bonus Challenge: Write a method called `isPalindrome`.
         * This method takes a single String parameter.
         * It returns true if the ```java String``` passed in is a palindrome, false otherwise.
         * Note: A palindrome is a word that is spelled the same forward and backward.
         * (Examples: MOM, DAD, POP, MA'AM).
         */
        System.out.println("isPalindrome");
        System.out.println("-------------");

        System.out.println(isPalindrome("android"));
        System.out.println(isPalindrome("kayak"));
        System.out.println(isPalindrome("level"));
        System.out.println(isPalindrome("antidisestablishmentarianism"));

        System.out.println();

    }

    public static int divide152By(int number){
        return 152 / number;
    }

    public static boolean thirdAndFirst(String first, String second){
        String thirdLetter = String.valueOf(first.charAt(2));
        String firstLetter = String.valueOf(second.charAt(0));

        return thirdLetter.equalsIgnoreCase(firstLetter);
    }

    public static double transmogrifier(int firstNum, int secondNum, int thirdNum){
        return Math.pow((firstNum + secondNum), thirdNum);
    }

    public static String reverseString(String normalString){
        String reversedString = "";
        for(int i = normalString.length()-1; i >= 0; i--){
            reversedString = reversedString.concat(String.valueOf(normalString.charAt(i)));
        }
        return reversedString;
    }

    public static boolean isPalindrome(String normalString){
        String reversedString = reverseString(normalString);

        return reversedString.equals(normalString);

    }

}
