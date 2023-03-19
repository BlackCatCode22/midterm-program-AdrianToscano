import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

/* Midterm.java
 *
 * AAT 3/06/23
 * CIT-63 Java programming class
 * References:
 *
 * input: Reads animal information from file
 * processing: Organizes animals into habitats
 * output: outputs data into new text file, zooPopulation.txt
 */
public class zooMidterm
{
    static String genBirthDay(int age, String birthSeason)
    {
        // variables
        int year = 2023 - age;
        String monthDay;
        String DOB;

        // assigns a month and day depending on the birth season. defaults to jan 1st for unknown birth seasons
        if (birthSeason.equals("spring,"))
            monthDay = "03-14";
        else if (birthSeason.equals("summer,"))
            monthDay = "06-15";
        else if (birthSeason.equals("fall,"))
            monthDay = "09-16";
        else if (birthSeason.equals("winter,"))
            monthDay = "12-17";
        else
            monthDay = "01-01";

        // concatenates strings and returns them as the date of birth
        DOB = Integer.toString(year) + "-" + monthDay;
        return DOB;
    }

    static String genUanimalID(String speciesName, int numOfSpecies)
    {
        // uses species name and the number as a unique ID
       return speciesName.substring(0,2) + "0" + numOfSpecies;
    }

    static String genAnimalName(String uniqueIdPrefix, int uniqueIdSuffix)
    {
        // animal names saved as arrays
        String hyenaNames[] = {"Shenzi", "Banzai", "Ed", "Zig", "Bud", "Lou", "Kamari", "Wema", "Nne", "Madoa", "Prince Nevarah"},
                lionNames[] = {"Scar", "Mufasa", "Simba", "Kiara", "King", "Drooper", "Kimba", "Nala", "Leo", "Samson", "Elsa", "Cecil"},
                bearNames[] = {"Yogi", "Smokey", "Paddington", "Lippy", "Bungle", "Baloo", "Rupert", "Winnie the Pooh", "Snuggles", "Bert"},
                tigerNames[] = {"Tony", "Tigger", "Amber", "Cosimia", "Cuddles", "Dave", "Jiba", "Rajah", "Rayas", "Ryker"};

        String name;

        // uses unique Id from main to generate an animal name from the animal name arrays.
        switch (uniqueIdPrefix)
        {
            case "hy":
                name = hyenaNames[uniqueIdSuffix];
                break;
            case "li":
                name = lionNames[uniqueIdSuffix];
                break;
            case "ti":
                name = tigerNames[uniqueIdSuffix];
                break;
            case "be":
                name = bearNames[uniqueIdSuffix];
                break;
            default:
                name = "error in name switch statement";
                break;
        }
        return name;
    }

    static void genZooHabitats(String allAnimals[])
    {
        // new habitat arrays
        String hyenas[] = new String[4],
               lions[] = new String[4],
               tigers[] = new String[4],
               bears[] = new String[4];

        // moves animals from allAnimals[] to their new habitat
        for(int i = 0; i < 16; i++)
        {
            if (i <= 3)
                hyenas[i] = allAnimals[i];
            else if(i <= 7)
                lions[i % 4] = allAnimals[i];
            else if (i <= 11)
                tigers[i % 4] = allAnimals[i];
            else
                bears[i % 4] = allAnimals[i];
        }

        // output final habitats into zooPopulation.txt
        try
        {
            FileWriter file = new FileWriter("C:\\Users\\angui\\Documents\\a_code\\Java\\Midterm\\src\\zooPopulation.txt");
            BufferedWriter output = new BufferedWriter(file);

            // output hyena habitat
            output.write("Hyena Habitat:\n");
            for (int i = 0; i < 4; i++)
                output.write(hyenas[i] + "\n");

            output.write("\n");

            // output lion habitat
            output.write("Lion Habitat:\n");
            for (int i = 0; i < 4; i++)
                output.write(lions[i] + "\n");

            output.write("\n");

            // output tiger habitat
            output.write("Tiger Habitat:\n");
            for (int i = 0; i < 4; i++)
                output.write(tigers[i] + "\n");

            output.write("\n");

            // output bears habitat
            output.write("Bear Habitat:\n");
            for (int i = 0; i < 4; i++)
                output.write(bears[i] + "\n");

            output.close();
        }
        catch (Exception e)
        {
            e.getStackTrace();
        }

    }
    public static void main(String[] args)
    {
        // array for arrivingAnimals.txt data
        String arrivingAnimals[] = new String[16];

        // array will hold processed data of all animals
        String allAnimals[] = new String[16];

        // variables for processing uniqueID
        String uniqueID = "000";
        int numOfHyenas = 0,
                numOfLions = 0,
                numOfTigers = 0,
                numOfBears = 0;

        // open arrivingAnimals.txt and store data, by line, into array
        try
        {
            int i = 0;
            File arrivingAnimalsFile = new File("C:\\Users\\angui\\Documents\\a_code\\Java\\Midterm\\src\\arrivingAnimals.txt");
            Scanner arrivingAnimalsSc = new Scanner(arrivingAnimalsFile);
            while (arrivingAnimalsSc.hasNextLine())
            {
                String fileLine = arrivingAnimalsSc.nextLine();
                arrivingAnimals[i] = fileLine;
                i++;
            }
            arrivingAnimalsSc.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("\n A file error occurred.");
            e.printStackTrace();
        }

       // loop through the arrivingAnimals array and process animal information
       for (int inputLineCounter = 0; inputLineCounter < arrivingAnimals.length; inputLineCounter++)
       {
            // split input line into separate words. store in splitStr array
            String splitStr[] = arrivingAnimals[inputLineCounter].split(" ",0);

            // use that array to calculate birthday. index 0 will have the age of the animal. index 7 has the season.
            String birthDay = genBirthDay(Integer.parseInt(splitStr[0]), splitStr[7]);

            // save animal's sex
            String sex = splitStr[3];

            // save animal species, remove the comma
            String species = splitStr[4];
            int commaIndex = species.indexOf(",");
            species = species.substring(0,commaIndex);

            // generates a unique ID depending on the species
            if (species.equals("hyena"))
            {
                numOfHyenas++;
                uniqueID = genUanimalID(species, numOfHyenas);
            }
            else if (species.equals("lion"))
            {
                numOfLions++;
                uniqueID = genUanimalID(species, numOfLions);
            }
            else if (species.equals("tiger"))
            {
                numOfTigers++;
                uniqueID = genUanimalID(species, numOfTigers);
            }
            else if (species.equals("bear"))
            {
                numOfBears++;
                uniqueID = genUanimalID(species, numOfBears);
            }
            else
                System.out.println("Error tabulating number of species...");

            // split array by comma to find the color. this will be in index 2 after the split.
            String commaSplit[] = arrivingAnimals[inputLineCounter].split(",", 0);
            String color = commaSplit[2];

            // save the animals weight and origin
            String weight = commaSplit[3];
            String origin = commaSplit[4] + "," + commaSplit[5];

            // use the animals unique ID to generate an animal name via genAnimalName function
            String uniqueIDPrefix = uniqueID.substring(0,2);
            String uniqueIDSuffix = uniqueID.substring(2);
            int suffixAsInt = Integer.parseInt(uniqueIDSuffix);

            String name = genAnimalName(uniqueIDPrefix, suffixAsInt);

            // create local date variable to determine arrival date and age
            String arrivalDate;
            LocalDate curDate = LocalDate.now();
            arrivalDate = curDate.toString();
            LocalDate bDate = LocalDate.parse(birthDay);
            int age =  Period.between(bDate, curDate).getYears();

           // assign all information gathered into allAnimals[] array
           String finalAnimalData = uniqueID + "; " + name + "; " + age + " years old;" + " birth date " +
                                  birthDay + ";" + color + "; " + sex + "; " + weight + "; " + origin + "; arrived " + arrivalDate;

           allAnimals[inputLineCounter] = finalAnimalData;
       }

       // creates new habitats for animals and outputs them to zooPopulation.txt
       genZooHabitats(allAnimals);
    }
}