package composition;
public class Symphony extends MusicalComposition {
    private String composer;

    public Symphony(String title, int duration, String composer) {
        super(title, duration);
        this.composer = composer;
    }

    @Override
    public String getStyle() {
        return "Symphony";
    }

    public String getComposer() {
        return composer;
    }
}