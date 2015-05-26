import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*klasi pazl ipoklasi tou JPanel*/
public class Pazl extends JPanel
{
    private JLabel number;
    public boolean label;
    public boolean movable = false;
    
    Pazl(String str)
    {
        super(new FlowLayout());
        
        label = true;
        
        /*dimiourgoume antikimeno tipou pazl*/
        number = new JLabel(str);
        number.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
        number.setForeground(Color.DARK_GRAY);
        setBackground(Color.gray);
        setPreferredSize(new Dimension(100, 100));
        add(number);
    }
    
    Pazl()
    {
        super(new FlowLayout());
        number = new JLabel("9");
        label = false;
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(100, 100));
    }
    
    public String getNumber()
    {
        return number.getText();
    }
}
