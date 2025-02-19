import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import java.util.Random;

public class DinoRunner extends JFrame implements KeyListener{
    char[][] grid = {{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},{'.','.','@','.','.','.','.','.','.','.','.','.','.','.','.','.'},{'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'}};
    
    int dinoRow = 14; int dinoCol = 2;
    int dinoRowVel = 0;
    int cactusRow = 14; int cactusCol = 0;
    int cactusTimer = 0;
    Random random = new Random();
    int score = 0;
    boolean gameRun = true;
    boolean zKey = false;

    public DinoRunner() {
        this.setTitle("Dino Runner");//title  of window
        this.setSize(300, 200);//window dimensions
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//closes program when window is closed
        this.addKeyListener(this);//allows keylistener to exsist
        this.setVisible(true);//makes window visible
	    gameLoop();	
    }
	
    private void gameLoop(){
	while (gameRun == true){
	    
        //render
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.print("Score: " + score);
        score++;
        System.out.println();
        
	    for (int i = 0; i < 16; i++){
	        for (int j = 0; j < 16; j++){
	            System.out.print(grid[i][j]);
	        }
	    System.out.println();
        }

        //dino code
        grid[dinoRow][dinoCol] = '.';
        dinoRowVel++;
        dinoRow += dinoRowVel;
        if (dinoRow > 14){
            dinoRow = 14;
        }
        if (zKey == true && dinoRow == 14){
            dinoRowVel = -4;
        }
        grid[dinoRow][dinoCol] = '@';

        //cactus code
        if (cactusCol > 0){
            grid[cactusRow][cactusCol] = '.';
            cactusCol--;
            grid[cactusRow][cactusCol] = 'o';
            if (cactusRow == dinoRow && cactusCol == dinoCol){
                gameRun = false;
            }
        }
        else{
            grid[14][0] = '.';
            cactusTimer += random.nextInt(2)+1;
            if (cactusTimer >= 14){
                cactusCol = 15;
                cactusTimer = 0;
            }
        }

        //delay
	    try {
		Thread.sleep(100);
            } catch (InterruptedException e) {
		e.printStackTrace();
	    }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used in this program
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'z'){
            zKey = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == 'z'){
            zKey = false;
        }
    }
    
    public static void main(String[] args) {
       new DinoRunner();
    }
}