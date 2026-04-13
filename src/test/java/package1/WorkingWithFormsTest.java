package package1;

import org.junit.jupiter.api.*;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class WorkingWithFormsTest {

    private WorkingWithForms formApp;
    private JFrame frame;

    @BeforeAll
    static void initializeTestSuite() {
        System.out.println("Starting WorkingWithForms Test Suite...");
    }

    @AfterAll
    static void cleanupTestSuite() {
        System.out.println("Finished WorkingWithForms Test Suite.");
    }

    @BeforeEach
    void setUp() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            formApp = new WorkingWithForms();

            // Get the active JFrame
            for (Frame f : Frame.getFrames()) {
                if (f instanceof JFrame) {
                    frame = (JFrame) f;
                    break;
                }
            }
        });
    }

    @AfterEach
    void tearDown() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            if (frame != null) {
                frame.dispose();
            }
        });
    }

    @Test
    void testFrameIsCreated() {
        assertNotNull(frame);
        assertEquals("Employee Registration System", frame.getTitle());
        assertEquals(500, frame.getWidth());
        assertEquals(600, frame.getHeight());
    }

    @Test
    void testLayoutIsGridLayout() {
        assertFalse(frame.getLayout() instanceof GridLayout);
    }

    @Test
    void testTextFieldsExist() {
        Component[] components = frame.getContentPane().getComponents();

        boolean hasTextField = false;
        boolean hasPasswordField = false;

        for (Component c : components) {
            if (c instanceof JTextField) {
                hasTextField = true;
            }
            if (c instanceof JPasswordField) {
                hasPasswordField = true;
            }
        }

        assertTrue(hasTextField, "JTextField not found");
        assertTrue(hasPasswordField, "JPasswordField not found");
    }

    @Test
    void testComboBoxExists() {
        boolean found = false;

        for (Component c : frame.getContentPane().getComponents()) {
            if (c instanceof JComboBox) {
                found = true;
                JComboBox<?> box = (JComboBox<?>) c;
                assertEquals(4, box.getItemCount());
            }
        }

        assertTrue(found, "JComboBox not found");
    }

    @Test
    void testTreeExists() {
        boolean found = false;

        for (Component c : frame.getContentPane().getComponents()) {
            if (c instanceof JScrollPane) {
                JScrollPane pane = (JScrollPane) c;
                if (pane.getViewport().getView() instanceof JTree) {
                    found = true;
                }
            }
        }

        assertTrue(found, "JTree not found inside JScrollPane");
    }

    @Test
    void testSubmitButtonExists() {
        boolean found = false;

        for (Component c : frame.getContentPane().getComponents()) {
            if (c instanceof JButton) {
                JButton btn = (JButton) c;
                if (btn.getText().equals("Submit")) {
                    found = true;
                }
            }
        }

        assertTrue(found, "Submit button not found");
    }
}