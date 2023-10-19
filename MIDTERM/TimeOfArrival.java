import java.util.Scanner;
      
// An interactive application that computes for the Est. TOA (Time of Arrival)
public class TimeOfArrival {
   static public void main(String[] args) {
      Scanner scan = new Scanner(System.in);
      
      double Barili = 84.9, Dumanjug = 96.2, Argao = 107.1, distance;
      int route;
      
      System.out.println("Is Barili obstructed? \n[1] Yes \n[2] No");
      int obstrctBarili = scan.nextInt();
      
      if (obstrctBarili == 1) {
         System.out.println("\nIs Dumanjug obstructed? \n[1] Yes \n[2] No");
         int obstrctDumanjug = scan.nextInt();
      
         if (obstrctDumanjug == 1) { distance = Argao; route = 3; }
         else { distance = Dumanjug; route = 2; }
      
      }
      
      else { distance = Barili; route = 1; }
      
      System.out.println("\nCebu City (Emall) - Start");
      System.out.println("Minglanilla - Route 1");
      System.out.println("San Fernando - Route 2");
      System.out.println("Carcar - Route 3");
      
      switch(route) {
         case 1:
            System.out.println("Barili - Route 4.1");
            System.out.println("Dumanjug - Route 4.1.1");
            System.out.println("Alcantara - Route 4.1.2");
            System.out.println("Moalboal - End");
            break;
         case 2:
            System.out.println("Sibonga - Route 4.2");
            System.out.println("Dumanjug - Route 4.2.1");
            System.out.println("Alcantara - Route 4.2.2");
            System.out.println("Moalboal - End");
            break;
         case 3:
            System.out.println("Sibonga - Route 4.2");
            System.out.println("Argao - Route 5");
            System.out.println("Ronda - Route 5.1");
            System.out.println("Alcantara - Route 5.2");
            System.out.println("Moalboal - End");
            break;
      }
      
      System.out.print("\nEnter the speed (km/hr): ");
      double speed = scan.nextDouble();
      
      System.out.println("Total Distance: " + distance + " km");
      System.out.println("Est. Time of Arrival: ~" + (distance/speed) + " hr/s");
   }
}