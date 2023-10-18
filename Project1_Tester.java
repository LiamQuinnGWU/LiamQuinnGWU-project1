import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.*;
import java.io.*;

public class Project1_Tester {

  private boolean checkInFile(String filename, String phrase){
    try {
      File file = new File("./" + filename);
      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()){
        String line = scanner.nextLine();
        line = line.replaceAll("\\s+","") ;
        phrase = phrase.replaceAll("\\s+","") ;
        if (line.contains(phrase))
          return true;
      }
    } catch(Exception e){
      e.printStackTrace();
    }
    return false;
  }

  private String getCommandLineOutput(String command) {
    String outputCollected = "";

    try {
      Process process = Runtime.getRuntime().exec(command);
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      String line;
      while ((line = reader.readLine()) != null) 
        outputCollected += line;
      reader.close();

      reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
      while ((line = reader.readLine()) != null) 
        outputCollected += line;
      reader.close();
    } catch (IOException exc) {
      exc.printStackTrace();
    }

    return outputCollected;
  }



  // change the levels of completion to all or nothing

  @Test
  public void test1() {
    System.out.println("checking that Creature has a step method with good OOP");
    assertEquals(true, checkInFile("Creature.java", "public void step() {"));
  }

    @Test
  public void test2() {
    System.out.println("checking that Mouse and Cat extend Creature");
    assertEquals(true, checkInFile("Mouse.java", "public class Mouse extends Creature"));
    assertEquals(true, checkInFile("Cat.java", "public class Cat extends Creature"));
  }

  @Test
  public void test3() {
    System.out.println("check that Mouse and Cat call creature's step with good OOP");
    assertEquals(true, checkInFile("Mouse.java", "super.step();"));
    assertEquals(true, checkInFile("Cat.java", "super.step();"));
  }

  @Test
  public void test4() {
    System.out.println("check that Creature has an abstract takeAction with good OOP");
    assertEquals(true, checkInFile("Creature.java", "public abstract void takeAction();"));
  }

  @Test
  public void test5() {
    System.out.println("check that Mouse and cat implement Creature's takeAction with good OOP");
    assertEquals(true, checkInFile("Mouse.java", "public void takeAction(){"));
  }

  @Test
  public void test6() {
    System.out.println("checking that Mouse randomly changes direction 20% of the time and is a blue dot");
    String expected = "62 58 bdone 162 57 bdone 262 56 bdone 362 55 bdone 463 55 bdone 564 55 bdone 665 55 bdone 766 55 bdone 867 55 bdone 967 54 bdone 10";
    assertEquals(expected, getCommandLineOutput("java Simulator 1 0 0 10 40"));

  }

  @Test
  public void test7() {
    System.out.println("checking that Mouse produces a baby after 20 rounds");
      String expected = "6 27 bdone 16 26 bdone 26 25 bdone 36 24 bdone 46 23 bdone 56 22 bdone 66 21 bdone 75 21 bdone 84 21 bdone 93 21 bdone 102 21 bdone 113 21 bdone 124 21 bdone 135 21 bdone 146 21 bdone 157 21 bdone 168 21 bdone 178 22 bdone 188 23 bdone 198 22 b8 22 bdone 208 21 b7 22 bdone 21";
      assertEquals(expected, getCommandLineOutput("java Simulator 1 0 0 21 30"));
  }

  @Test
  public void test8() {
    System.out.println("checking that Mouse dies after 22 rounds");
    String expected = "6 27 bdone 16 26 bdone 26 25 bdone 36 24 bdone 46 23 bdone 56 22 bdone 66 21 bdone 75 21 bdone 84 21 bdone 93 21 bdone 102 21 bdone 113 21 bdone 124 21 bdone 135 21 bdone 146 21 bdone 157 21 bdone 168 21 bdone 178 22 bdone 188 23 bdone 198 22 b8 22 bdone 208 21 b7 22 bdone 218 20 b6 22 bdone 226 23 bdone 23";
    assertEquals(expected, getCommandLineOutput("java Simulator 1 0 0 23 30"));
  }


  @Test
  public void test9() {
    System.out.println("checking that all Cat functionality is properly implemented");
      String expected = "73 43 b13 12 y17 72 y0 35 c43 27 y38 28 ydone 174 43 b11 12 y17 70 y2 35 c43 29 y38 30 ydone 275 43 b9 12 y17 68 y4 35 c43 31 y38 32 ydone 374 43 b7 12 y17 66 y6 35 y43 33 y38 34 ydone 473 43 b5 12 y17 64 y8 35 y43 35 y38 36 ydone 573 42 b3 12 y17 62 y10 35 y43 37 y38 38 ydone 673 41 b1 12 y17 60 y12 35 y43 39 y38 40 ydone 774 41 b3 12 y17 58 y14 35 y43 41 y38 42 ydone 875 41 b5 12 y17 56 y16 35 y43 43 y38 44 ydone 976 41 b7 12 y17 54 y18 35 y43 45 y38 46 ydone 1077 41 b9 12 y17 52 y20 35 y43 47 y38 48 ydone 1178 41 b11 12 y17 54 y22 35 y43 49 y38 50 ydone 1279 41 b13 12 y17 56 y24 35 y43 51 y36 50 ydone 1379 40 b15 12 y17 58 y26 35 y43 53 y34 50 ydone 140 40 b15 10 y17 60 y28 35 y43 55 y32 50 ydone 151 40 b15 8 y17 62 y30 35 y43 57 y30 50 ydone 162 40 b15 6 y17 64 y32 35 y43 59 y28 50 ydone 173 40 b15 4 y17 66 y34 35 y43 61 y26 50 ydone 184 40 b13 4 y17 68 y36 35 y43 63 y24 50 ydone 195 40 b11 4 y17 70 y38 35 y43 65 y22 50 y5 40 bdone 206 40 b9 4 y17 72 y40 35 y43 67 y20 50 y6 40 bdone 217 40 b7 4 y17 74 y42 35 y43 69 y18 50 y7 40 bdone 225 4 y17 76 y44 35 y43 71 y16 50 c8 40 bdone 233 4 y17 78 y46 35 y43 73 y16 48 c9 40 bdone 241 4 y17 0 y48 35 y43 75 y16 46 c10 40 b67 3 ydone 2579 4 y17 2 y50 35 y43 77 y14 46 c11 40 b65 3 ydone 2677 4 y17 4 y52 35 y43 79 y14 44 c12 40 b63 3 ydone 2775 4 y17 6 y54 35 y43 1 y14 42 c13 40 b61 3 ydone 2873 4 y17 8 y56 35 y43 3 y14 40 c59 3 ydone 2971 4 y17 10 y58 35 y43 5 y14 38 y57 3 ydone 30";
      assertEquals(expected, getCommandLineOutput("java Simulator 1 5 0 30 35"));
  }

  @Test
  public void test10() {
    System.out.println("checking new mice and cats are added correctly");
      String expected = "73 43 b13 12 y17 72 y0 35 c43 27 y38 28 ydone 174 43 b11 12 y17 70 y2 35 c43 29 y38 30 ydone 275 43 b9 12 y17 68 y4 35 c43 31 y38 32 ydone 374 43 b7 12 y17 66 y6 35 y43 33 y38 34 ydone 473 43 b5 12 y17 64 y8 35 y43 35 y38 36 ydone 573 42 b3 12 y17 62 y10 35 y43 37 y38 38 ydone 673 41 b1 12 y17 60 y12 35 y43 39 y38 40 ydone 774 41 b3 12 y17 58 y14 35 y43 41 y38 42 ydone 875 41 b5 12 y17 56 y16 35 y43 43 y38 44 ydone 976 41 b7 12 y17 54 y18 35 y43 45 y38 46 ydone 1077 41 b9 12 y17 52 y20 35 y43 47 y38 48 ydone 1178 41 b11 12 y17 54 y22 35 y43 49 y38 50 ydone 1279 41 b13 12 y17 56 y24 35 y43 51 y36 50 ydone 1379 40 b15 12 y17 58 y26 35 y43 53 y34 50 ydone 140 40 b15 10 y17 60 y28 35 y43 55 y32 50 ydone 151 40 b15 8 y17 62 y30 35 y43 57 y30 50 ydone 162 40 b15 6 y17 64 y32 35 y43 59 y28 50 ydone 173 40 b15 4 y17 66 y34 35 y43 61 y26 50 ydone 184 40 b13 4 y17 68 y36 35 y43 63 y24 50 ydone 195 40 b11 4 y17 70 y38 35 y43 65 y22 50 y5 40 bdone 206 40 b9 4 y17 72 y40 35 y43 67 y20 50 y6 40 bdone 217 40 b7 4 y17 74 y42 35 y43 69 y18 50 y7 40 bdone 225 4 y17 76 y44 35 y43 71 y16 50 c8 40 bdone 233 4 y17 78 y46 35 y43 73 y16 48 c9 40 bdone 241 4 y17 0 y48 35 y43 75 y16 46 c10 40 b67 3 ydone 2579 4 y17 2 y50 35 y43 77 y14 46 c11 40 b65 3 ydone 2677 4 y17 4 y52 35 y43 79 y14 44 c12 40 b63 3 ydone 2775 4 y17 6 y54 35 y43 1 y14 42 c13 40 b61 3 ydone 2873 4 y17 8 y56 35 y43 3 y14 40 c59 3 ydone 2971 4 y17 10 y58 35 y43 5 y14 38 y57 3 ydone 30";
      assertEquals(expected, getCommandLineOutput("java Simulator 1 5 0 30 35"));
  }
}