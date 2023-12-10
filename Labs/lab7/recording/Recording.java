package recording;

import composition.MusicalComposition;
import java.util.ArrayList;
import java.util.List;

public class Recording {


    private List<MusicalComposition> compositions;

    public Recording() {
        compositions = new ArrayList<>();
    }

    public void addComposition(MusicalComposition composition) {
        compositions.add(composition);
    }

    public List<MusicalComposition> getCompositions() {
        return compositions;
    }

    public int getTotalDuration() {
        int totalDuration = 0;
        for (MusicalComposition composition : compositions) {
            totalDuration += composition.getDuration();
        }
        return totalDuration;
    }

    public List<MusicalComposition> getCompositionsByStyle(String style) {
        List<MusicalComposition> compositionsByStyle = new ArrayList<>();
        for (MusicalComposition composition : compositions) {
            if (composition.getStyle().equals(style)) {
                compositionsByStyle.add(composition);
            }
        }
        return compositionsByStyle;
    }

    public MusicalComposition findCompositionByDurationRange(int minLength, int maxLength) {
        for (MusicalComposition composition : compositions) {
            int duration = composition.getDuration();
            if (duration >= minLength && duration <= maxLength) {
                return composition;
            }
        }
        return null;
    }
}