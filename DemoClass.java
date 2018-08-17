/*
 * This is Democlass which calls the method of test class
 * Test class contain method display which display the text at runtime
 */

package avais.com.demoapp;
public class DemoClass {

   
    public static void main(String[] args) {
        
        System.out.println("[Application - Main] Start application");
  String value = "Demonstration of Java bytecode manipulation capabilities";
  Test test = new Test();
  System.out.println("[Application - Main] Value passed to text display: " + value);
  test.display(value);
  System.out.println("[Application - Main] Complete application");
    }
    
}
