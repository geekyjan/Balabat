import java.util.Scanner;

public class Student {
   private String first_name, middle_name, last_name, suffix;
   
   String getFirstName() {
      return first_name;
   } 
   String getMiddleName() { 
      return middle_name;
   }
   String getLastName() {
      return last_name;
   }
   String getSuffix() {
      return suffix;
   }
   String getFullName() {
      if (suffix.equalsIgnoreCase("n/a")) suffix = "";
      return first_name + " " + middle_name + " " + last_name + " " + suffix;
   }
   
   void setFirstName(String first_name) {
      this.first_name = first_name;
   }
   void setMiddleName(String middle_name) {
      this.middle_name = middle_name;
      }
   void setLastName(String last_name) {
      this.last_name = last_name;  
   }
   void setSuffix(String suffix) {
      this.suffix = suffix;
   }
   
   public static void main (String[] args) {
      Scanner scan = new Scanner(System.in);
      
      Student s = new Student();
      
      System.out.print("Enter first name: ");
      s.setFirstName(scan.nextLine());
      System.out.print("Enter middle name: ");
      s.setMiddleName(scan.nextLine());
      System.out.print("Enter last name: ");
      s.setLastName(scan.nextLine());
      System.out.print("Enter suffix (n/a if not applicable): ");
      s.setSuffix(scan.nextLine().trim());
      
      System.out.println("\nFirst Name: " + s.getFirstName());
      System.out.println("Middle Name: " + s.getMiddleName());
      System.out.println("Last Name: " + s.getLastName());
      System.out.println("Suffix: " + s.getSuffix());
      System.out.println("Full Name: " + s.getFullName());
   }
}      
      
       
