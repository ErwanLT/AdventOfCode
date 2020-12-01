import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Jour6B {

    private static List<Planete> planetes = new ArrayList<>();

    private static int sumOfOrbit = 0;

    public static void main(String[] args) throws URISyntaxException {
        Jour6B app = new Jour6B();

        String fileName = "MapData";
        File file = app.getFileFromResource(fileName);
        populatePlaneteesList(file);

        String name = "";
        name = getFirstPlanet(getPlanete("SAN").getOrbits(), getPlanete("YOU").getOrbits());
        Planete youP = getPlanete("YOU");
        Planete sanP = getPlanete("SAN");
        int a = youP.getOrbit(name) - 1;
        int b = sanP.getOrbit(name) - 1;
        int amount = a + b;
        System.out.println(amount);
    }

    private File getFileFromResource(String fileName) throws URISyntaxException {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return new File(resource.toURI());
        }
    }

    private static void populatePlaneteesList(File file) {
        List<String> lines;
        try {
            lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            for (String line: lines) {
                String from = line.split("\\)")[0];
                String to = line.split("\\)")[1];

                Planete fromP = getPlanete(from);
                Planete toP = getPlanete(to);
                toP.setFrom(fromP);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Planete getPlanete(String name) {
        for (Planete Planete : planetes) {
            if (Planete.getName().equals(name)) return Planete;
        }
        Planete Planete = new Planete(name, null);
        planetes.add(Planete);
        return Planete;
    }

    private static String getFirstPlanet(List<Planete> orbit1, List<Planete> orbit2) {
        for (Planete planet : orbit1) {
            for (Planete planet2 : orbit2) {
                if (planet2.getName().equals(planet.getName())) {
                    return planet.getName();
                }
            }
        }
        return "";
    }
}
