import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*klasi GameArea ipoklasi t JPanel*/
public final class GameArea extends JPanel implements MouseListener
{
    private GridLayout layout = new GridLayout(3, 3, 0xA, 0xA);
    private Pazl arrayPazl[] = new Pazl[9];
    private List<Pazl> pazlList = new ArrayList<>();
    private Random genRandom = new Random();
    private MyPuzzle myApplet;
    private int freePosition;
    /*lista me ta pazl*/
    private List<Pazl> randomPazls; 
   
    /*dimiourgos p dimiourgi kenurgio GameArea me 8 pazl*/
    public GameArea(MyPuzzle myApplet) 
    {
        super();
        this.myApplet = myApplet;
        
        setLayout(layout);
        setBackground(Color.black);
        
        arrayPazl[0] = new Pazl();
        pazlList.add(arrayPazl[0]);
        
        /*ftiaxnw ta komatia tou pazl*/
        for(int i=1; i<arrayPazl.length; i++)
        {
            arrayPazl[i] = new Pazl(Integer.toString(i));
            arrayPazl[i].addMouseListener(this);
            pazlList.add(arrayPazl[i]);
        }
        
        /* lista me ta pazl sti seira ekxorounte */
        randomPazls = new ArrayList<>();
       /*tixea diataxi twn pazl*/ 
        for(int i=0; i<arrayPazl.length; i++)
        {
            int randomInt;
            do
            {
                randomInt = genRandom.nextInt(arrayPazl.length-i);
            }
            while(randomInt<0 || randomInt>8);
            
            /* prosthetoume sti lista random pazl ta ta pazl opws ta ekxoroume */
            randomPazls.add(pazlList.get(randomInt));

            add(pazlList.get(randomInt));
            pazlList.remove(randomInt);
        }
        
        int i;
        /*pernw tin thesi freePosition*/
        for(i=0; i<randomPazls.size(); i++)
        {
            if (randomPazls.get(i).label == false)
            {
                freePosition = i;
            }
        }
       /*kalw tin checkMovability me orisma t freePosition*/ 
       checkMovability(freePosition);
       
    }
    
    /*methodos moveToFreePosition me orismata tin lista kai to pazl
       opou ginete i metakinisi stin keni thesi*/
    public void moveToFreePosition(List<Pazl> pazlList, Pazl pazl)
    {
        Pazl tempPazl = pazl;
        int pazlPosition = -1;
        
        for(int i=0; i<pazlList.size() && pazlPosition == -1; i++)
        {
            if ( pazl == pazlList.get(i) )
            {
                pazlPosition = i;
            }
        }
        
        pazlList.set(pazlPosition, arrayPazl[0]);
        pazlList.set(freePosition, tempPazl);
        
        remove(pazl);
        remove(pazlList.get(freePosition));
        
        add(pazl, freePosition);
        
        freePosition = pazlPosition;
        add(pazlList.get(freePosition), freePosition);
        checkMovability(freePosition);
    }
    /*methodos checkMovability me orisma to freePosition
       opou elegxw gia to an ena pazl ine movable i oxi*/        
    public void checkMovability(int freePosition)
    {
        this.freePosition = freePosition;
        
        for(int i=0; i<randomPazls.size(); i++)
        {
            randomPazls.get(i).movable = false;
        }
        
        /*elegxw pia pazl na kanw movable*/
        switch(freePosition)
        {
            case 0:
            {
                randomPazls.get(freePosition+1).movable = true;
                randomPazls.get(freePosition+3).movable = true;
                break;
            }
            case 1:
            {
                randomPazls.get(freePosition+1).movable = true;
                randomPazls.get(freePosition-1).movable = true;
                randomPazls.get(freePosition+3).movable = true;
                break;
            }
            case 2:
            {
                randomPazls.get(freePosition-1).movable = true;
                randomPazls.get(freePosition+3).movable = true;
                break;
            }
            case 3:
            {
                randomPazls.get(freePosition+1).movable = true;
                randomPazls.get(freePosition-3).movable = true;
                randomPazls.get(freePosition+3).movable = true;
                break;
            }
            case 4:
            {
                randomPazls.get(freePosition+1).movable = true;
                randomPazls.get(freePosition-1).movable = true;
                randomPazls.get(freePosition-3).movable = true;
                randomPazls.get(freePosition+3).movable = true;
                break;
            }
            case 5:
            {
                randomPazls.get(freePosition-1).movable = true;
                randomPazls.get(freePosition-3).movable = true;
                randomPazls.get(freePosition+3).movable = true;
                break;
            }
            case 6: 
            {
                randomPazls.get(freePosition+1).movable = true;
                randomPazls.get(freePosition-3).movable = true;
                break;
            }
            case 7:
            {
                randomPazls.get(freePosition-3).movable = true;
                randomPazls.get(freePosition-1).movable = true;
                randomPazls.get(freePosition+1).movable = true;
                break;
            }
            case 8:
            {
                randomPazls.get(freePosition-1).movable = true;
                randomPazls.get(freePosition-3).movable = true;
                break;
            }
        }
    }
    
    /*methodos checkForWin tipou boolean opou elegxw pote 
     tha kerdisi o xristis*/
    public boolean checkForWin()
    {
        boolean win = true;
        
        for(int i=0; i<randomPazls.size(); i++)
        {
            if(Integer.parseInt(randomPazls.get(i).getNumber()) != i+1 )
            {
                win = false;
            }
        }
        return win;
    }
    
    /*methodos mouseClicked*/
    public void mouseClicked(MouseEvent e)
    {
        Pazl pazl = (Pazl) e.getSource();
        try
        {
            boolean win = false;
            if(pazl.movable)
            {
                moveToFreePosition(randomPazls, pazl);
                myApplet.plusMove();
                win = checkForWin();
            }
           
            if (win)
            {
                JOptionPane.showMessageDialog(myApplet,"Well Done!!!");
            }
        }
        catch (UnsupportedOperationException unEx)
        {
            JOptionPane.showMessageDialog(this, "Unsupported Error", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
    
}
