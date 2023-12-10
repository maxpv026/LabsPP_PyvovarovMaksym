package composition;
public abstract class MusicalComposition {

    private String title;
    private int duration; // Duration in seconds

    public MusicalComposition(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public abstract String getStyle();
}