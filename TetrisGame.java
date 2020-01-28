/*
 * Uyen Le		11-14-2019
 * Final Project: Tetris
 * This program creates the GUI display for Tetris.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
import java.awt.Color;

public class TetrisGame extends JFrame implements ActionListener, KeyListener{
	
//	private int score = 0;
	static boolean game_over = false;
	
//	Color cyan = Color.CYAN;
//	Color red = Color.RED;
	Color white = Color.WHITE;
	
	//display dimensions
	private final int WIDTH = 300;
	private final int HEIGHT = 600;
	
	//play area
	private static final int ROWS = 20;
	private static final int COLS = 10;
	private final int MAX_PIECES = 100;
	
	JPanel gameArea = new JPanel(new GridLayout(ROWS, COLS, 1, 1));
	static JPanel[][] panel = new JPanel[ROWS][COLS];
		
	JPanel home = new JPanel();
	JButton play = new JButton("Play");
//	JPanel play_button_panel = new JPanel();
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	JPanel panel4 = new JPanel();
	JPanel panel5 = new JPanel();



	JLabel title = new JLabel("Tetris", SwingConstants.CENTER);
	JLabel name = new JLabel("Made by Uyen Le", SwingConstants.CENTER);
	
//	JLabel score_display = new JLabel("Score: " + score);
	
	// Keeping track of tetris pieces
	TetrisPiece[] pieces = new TetrisPiece[MAX_PIECES];
	static private int piece_index = 0;
	
	private int time = 0;
	
	CardLayout cardlayout = new CardLayout();
	
	public TetrisGame()
	{
		super("Tetris");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setLayout(cardlayout);
		
		// Creating how game looks
		for(int row = 0; row < ROWS; ++row) 
		{
			for(int col = 0; col < COLS; ++col) 
			{
				panel[row][col] = new JPanel();
				gameArea.add(panel[row][col]);
				panel[row][col].setBackground(white);
			}
		}
		
		// Setting up home screen
		home.setLayout(new GridLayout(5,1,5,5));
		title.setFont(new Font("Arial", Font.BOLD, 80));
		name.setFont(new Font("Arial", Font.ITALIC, 10));
		panel1.add(new JLabel(" "));
		panel2.add(title);
		panel3.add(name);
		panel4.setLayout(new GridLayout(2,3,0,0));
		panel4.add(new JLabel(" "));
		panel4.add(play);
		panel4.add(new JLabel(" "));
		panel4.add(new JLabel(" "));
		panel4.add(new JLabel(" "));
		panel4.add(new JLabel(" "));
		panel5.add(new JLabel(" "));
		
		panel1.setBackground(Color.CYAN);
		panel2.setBackground(Color.PINK);
		panel3.setBackground(Color.CYAN);
		panel4.setBackground(Color.PINK);
		panel5.setBackground(Color.CYAN);
		
		home.add(panel1);
		home.add(panel2);
		home.add(panel3);
		home.add(panel4);
		home.add(panel5);
		
//		add(home, "panel1"); //Not working, fix error!
		add(gameArea, "panel2");
		
		make_piece(); //makes the first piece
		
		addKeyListener(this);
		play.addActionListener(this);
	}
	
	// Checks if there is a full line of blocks
	public void check_lines_filled() {
		for(int r = 0; r < ROWS; ++r) {
			boolean filled = true;
			for (int c = 0; c < COLS; ++c) {
				if(panel[r][c].getBackground() == white) {
					filled = false;
				}
			}
			
			if(filled) {
				for(int i = 0; i < COLS; ++i) {
					panel[r][i].setBackground(white);
				}
				timer_();
				move_everything_down(r);
			}
		}
	}
	
	// Moves everything on top of the fill rows of blocks down
	public void move_everything_down(int row) {
		for(int r = row; r > 0; --r) {
			for(int c = 0; c < COLS; ++c) {
				panel[r][c].setBackground(panel[r-1][c].getBackground());
			}
		}
	}
	
	public void timer_() {
		if(time >= 100000) {
			time = 0;
		}
		++time;
		System.out.println("HERE " + time);
	}
	
	public void make_piece() {
		pieces[piece_index] = new TetrisPiece(panel, game_over);
		
		//Reusing space in the TetrisPiece array (need to fix error)
//		if(piece_index >= MAX_PIECES) {
//			piece_index = 0;
//		}
	}
	
	//Game Loop
	public void update() {
		while(!game_over) {
			pieces[piece_index].piece_update(panel);
						
			if((pieces[piece_index].reached_bottom() && 
					pieces[piece_index].get_time() == 100000 )|| 
					pieces[piece_index].get_moving() == false) {
				check_lines_filled();
				piece_index += 1;
				make_piece();
			}
			
//			game_over = pieces[piece_index].get_game_state();
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
		
		// Assigning actions to keys being pressed
		if(keyCode == KeyEvent.VK_UP) 
		{
			pieces[piece_index].rotate_right(panel);
		} else if(keyCode == KeyEvent.VK_DOWN)
			//hard drop (not coded yet)
		{
			pieces[piece_index].move_down(panel);
		}else if(keyCode == KeyEvent.VK_RIGHT) 
		{
			if(!pieces[piece_index].reached_right_side() && 
					pieces[piece_index].get_moving())
			{
				pieces[piece_index].move_right(panel);
			}
		} else if(keyCode == KeyEvent.VK_LEFT) 
		{
			if(!pieces[piece_index].reached_left_side() && 
					pieces[piece_index].get_moving())
			{
				pieces[piece_index].move_left(panel);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		
		if(source == play) {
			cardlayout.show(getContentPane(), "panel2");
//			game_over = false;
//			make_piece();
//			update();
		}
	}
	
}
