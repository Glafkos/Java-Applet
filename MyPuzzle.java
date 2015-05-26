import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;

/*klasi MyPuzzle ipoklasi t JApplet*/
public class MyPuzzle extends JApplet implements ActionListener
{
    /*orizw to layout*/
    FlowLayout appletLayout = new FlowLayout();
    /*dimiourgo to koumpi newGame*/
    JButton newGame = new JButton("New Game");
    /*metavliti numberOfMoves arxikopiimeni me 0*/
    int numberOfMoves = 0;
    /*Label movesLabel opou tha emfanizonte oi fores p metakinithike ena pazl*/
    JLabel movesLabel = new JLabel("Moves : " + numberOfMoves);
    
    GameArea gameArea = new GameArea(this);
    
    /*methodos init*/
    public void init()
    {
        /*thetw to layout tou applet*/
        setLayout(appletLayout);
        /*orizw to size t parathirou*/ 
        setSize(350, 450);
        
        /*kanw add to kumpi newGame*/
        add(newGame);
        newGame.addActionListener(this);
        
        /*prosthetw to gameArea*/
        add(gameArea);
        
        /*kanw add to label*/
        add(movesLabel);
        
    }
    /*methodos actionPerformed opou vazoume tixea ta pazl*/
    public void actionPerformed(ActionEvent e) 
    {
        for(int i=0; i<10; i++)
        {
            remove(gameArea);
            remove(movesLabel);
            numberOfMoves = 0;
            movesLabel = new JLabel("Moves : " + numberOfMoves);
            gameArea = new GameArea(this);
            add(gameArea);
            add(movesLabel);
            revalidate();
        }
    }
    /*methodos  plusMove opou auxanoume to numberOfMoves kathe fora*/
    public void plusMove()
    {
        remove(movesLabel);
        movesLabel = new JLabel("Moves : " + ++numberOfMoves );
        add(movesLabel);
        revalidate();
    }           
}
