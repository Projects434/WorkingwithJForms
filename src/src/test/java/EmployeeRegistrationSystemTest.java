import com.toedter.calendar.JDateChooser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeRegistrationSystemTest {
    EmployeeRegistrationSystem app;

    @BeforeEach
    void setUp() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            app = new EmployeeRegistrationSystem();
        });
    }

    @Test
    void testComponentInitialization() {
        // Verify important components are created
        assertNotNull(findComponent(app, JTextField.class), "Name/Email fields should exist");
        assertNotNull(findComponent(app, JPasswordField.class), "Password field should exist");
        assertNotNull(findComponent(app, JComboBox.class), "Department dropdown should exist");
        assertNotNull(findComponent(app, JDateChooser.class), "Calendar should exist");
        assertNotNull(findComponent(app, JTree.class), "Organization tree should exist");
    }

    @Test
    void testClearButtonFunctionality() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            // Fill a field
            JTextField nameField = findComponent(app, JTextField.class);
            nameField.setText("John Doe");

            // Find and click clear button
            JButton clearBtn = findButtonByText(app, "Clear");
            assertNotNull(clearBtn);
            clearBtn.doClick();

            // Verify it is empty
            assertEquals("", nameField.getText(), "Name field should be empty after clear");
        });
    }

    private <T extends Component> T findComponent(Container container, Class<T> type) {
        for (Component c : container.getComponents()) {
            if (type.isInstance(c)) return type.cast(c);
            if (c instanceof Container) {
                T result = findComponent((Container) c, type);
                if (result != null) return result;
            }
        }
        return null;
    }

    private JButton findButtonByText(Container container, String text) {
        for (Component c : container.getComponents()) {
            if (c instanceof JButton && ((JButton) c).getText().equals(text)) return (JButton) c;
            if (c instanceof Container) {
                JButton result = findButtonByText((Container) c, text);
                if (result != null) return result;
            }
        }
        return null;
    }
}
