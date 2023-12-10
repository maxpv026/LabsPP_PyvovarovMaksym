
package recording;

import composition.MusicalComposition;
import composition.Song;
import composition.Symphony;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RecordingTest {

    @Test
    void testAddComposition() {
        Recording recording = new Recording();
        Song song = new Song("Test Song", 120, "Pop");
        recording.addComposition(song);

        List<MusicalComposition> compositions = recording.getCompositionsByStyle("Pop");
        assertTrue(compositions.contains(song));
    }

    @Test
    void testTotalDuration() {
        Recording recording = new Recording();
        recording.addComposition(new Song("Song1", 120, "Pop"));
        recording.addComposition(new Symphony("Symphony1", 300, "Composer1"));

        assertEquals(420, recording.getTotalDuration());
    }

    @Test
    void testGetCompositionsByStyle() {
        Recording recording = new Recording();
        recording.addComposition(new Song("Song1", 120, "Pop"));
        recording.addComposition(new Symphony("Symphony1", 300, "Composer1"));
        recording.addComposition(new Song("Song2", 180, "Jazz"));

        List<MusicalComposition> popCompositions = recording.getCompositionsByStyle("Pop");
        assertEquals(1, popCompositions.size());
        assertEquals("Song1", popCompositions.get(0).getTitle());
    }

    @Test
    void testFindCompositionByDurationRange() {
        Recording recording = new Recording();
        recording.addComposition(new Song("Song1", 120, "Pop"));
        recording.addComposition(new Symphony("Symphony1", 300, "Composer1"));
        recording.addComposition(new Song("Song2", 180, "Jazz"));

        MusicalComposition foundComposition = recording.findCompositionByDurationRange(150, 200);
        assertNotNull(foundComposition);
        assertEquals("Song2", foundComposition.getTitle());
    }
}


