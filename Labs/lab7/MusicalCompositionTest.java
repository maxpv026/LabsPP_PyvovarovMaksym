package composition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MusicalCompositionTest {

    @Test
    void testSong() {
        Song song = new Song("Bohemian Rhapsody", 355, "Rock");

        assertEquals("Bohemian Rhapsody", song.getTitle());
        assertEquals(355, song.getDuration());
        assertEquals("Rock", song.getStyle());
    }

    @Test
    void testSymphony() {
        Symphony symphony = new Symphony("Symphony No. 9", 480, "Beethoven");

        assertEquals("Symphony No. 9", symphony.getTitle());
        assertEquals(480, symphony.getDuration());
        assertEquals("Symphony", symphony.getStyle());
        assertEquals("Beethoven", symphony.getComposer());
    }
}
