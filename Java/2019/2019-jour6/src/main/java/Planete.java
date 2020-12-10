import java.util.ArrayList;
import java.util.List;

public class Planete {

    private Planete from;
    private String name;

    public Planete(String name, Planete from) {
        this.from = from;
        this.name = name;
    }

    public Planete getFrom() {
        return from;
    }

    public void setFrom(Planete from) {
        this.from = from;
    }

    public String getName() {
        return name;
    }

    public int getOrbit() {
        if (from == null) {
            return 0;
        }
        return from.getOrbit() + 1;
    }

    public int getOrbit(String until) {
        if (from == null) {
            return 0;
        }
        if (name.equals(until)) {
            return 0;
        }
        return from.getOrbit(until) + 1;
    }

    public List<Planete> getOrbits() {
        if (from == null) return new ArrayList<>();
        List<Planete> Planetes = new ArrayList<>();
        Planetes.add(from);
        Planetes.addAll(from.getOrbits());
        return Planetes;
    }
}
