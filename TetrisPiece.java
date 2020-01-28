/*
 * Uyen Le		11-14-2019
 * Final Project: Tetris
 * This program creates the pieces in a Tetris game.
 */

import javax.swing.*;
import java.awt.Color;
//import java.util.Timer;
//import java.util.TimerTask;

public class TetrisPiece extends JFrame{
	private final int ROWS = 20;
	private final int COLS = 10;

	//RxC Coordinates of each block in a tetris piece
	private int block1_r = 0;
	private int block1_c = 3;
	private int block2_r = 0;
	private int block2_c = 4;
	private int block3_r = 0;
	private int block3_c = 5;
	private int block4_r = 0;
	private int block4_c = 6;
	
	private boolean moving = true;
	
//	JPanel[][] panel;
//	Color[][] cur_clr;
	
//	public void get_panels(JPanel[][] p, Color[][] c) {
//		panel = p;
//		cur_clr = c;
//	}
	
	// Making a timer
	private int time = 0;
//	Timer timer = new Timer();
//	TimerTask myTask = new TimerTask() {
//
//		@Override
//		public void run() {
//			// TODO Auto-generated method stub
//			move_down(panel, cur_clr);
//		}};
	
	Color color;
	Color piece_color;
	Color white = Color.WHITE;
	Color cyan = Color.CYAN;
	Color red = Color.RED;
	Color pink = Color.PINK;
	Color green = Color.GREEN;
	Color yellow = Color.YELLOW;
	Color blue = Color.BLUE;
	Color orange = Color.ORANGE;
	
	int piece_type = 0;
	
	private boolean game_state;
	
	int rotation = 0;
	
	int rise = 1; // row
	int run = 1; // col
	int r = -1;
	int c = 1;
	
	public void set_piece_type() {
		piece_type = (int)(Math.random() * 7);
	}
	
	public int get_piece_type() {
		return piece_type;
	}
	
	public TetrisPiece(JPanel[][] panel, boolean gm) {
//		get_panels(panel, cur_clr);
		set_game_state(gm);
		set_piece_type();
		draw_piece(panel);
	}
	
	public void set_game_state(boolean game_over) {
		game_state = game_over;
	}
	
	public boolean get_game_state() {
		return game_state;
	}
	
		
	private void set_color(JPanel[][] panel) {
		//Setting up game over condition
//		if(panel[block1_r][block1_c].getBackground() != white ||
//				panel[block2_r][block2_c].getBackground() != white ||
//				panel[block3_r][block3_c].getBackground() != white ||
//				panel[block4_r][block4_c].getBackground() != white) {
//			game_state = true;
//		}
		
		//Setting the color of each panel in a tetris piece
		panel[block1_r][block1_c].setBackground(color);
		panel[block2_r][block2_c].setBackground(color);
		panel[block3_r][block3_c].setBackground(color);
		panel[block4_r][block4_c].setBackground(color);
	}
	
	//Sets up RxC coordinates for specified piece type
	public void draw_piece(JPanel[][] panel) {
		if(piece_type == 0) { // straight
			color = piece_color = cyan;
		} else if(piece_type == 1) { // Z
			block1_r = 0;
			block1_c = 3;
			block2_r = 0;
			block2_c = 4;
			block3_r = 1;
			block3_c = 4;
			block4_r = 1;
			block4_c = 5;
			
			color = piece_color = red;
		} else if(piece_type == 2) { // reverse Z / S
			block1_r = 0;
			block1_c = 4;
			block2_r = 0;
			block2_c = 5;
			block3_r = 1;
			block3_c = 3;
			block4_r = 1;
			block4_c = 4;
			
			color = piece_color = green;
		} else if(piece_type == 3) { // upward T
			block1_r = 0;
			block1_c = 4;
			block2_r = 1;
			block2_c = 3;
			block3_r = 1;
			block3_c = 4;
			block4_r = 1;
			block4_c = 5;
			
			color = piece_color = pink;
		} else if(piece_type == 4) { // square
			block1_r = 0;
			block1_c = 3;
			block2_r = 0;
			block2_c = 4;
			block3_r = 1;
			block3_c = 3;
			block4_r = 1;
			block4_c = 4;
			
			color = piece_color = yellow;
		} else if(piece_type == 5) { //blue L
			block1_r = 0;
			block1_c = 3;
			block2_r = 1;
			block2_c = 3;
			block3_r = 1;
			block3_c = 4;
			block4_r = 1;
			block4_c = 5;
			
			color = piece_color = blue;
		} else if(piece_type == 6) { //orange L
			block1_r = 0;
			block1_c = 5;
			block2_r = 1;
			block2_c = 3;
			block3_r = 1;
			block3_c = 4;
			block4_r = 1;
			block4_c = 5;
			
			color = piece_color = orange;
		}
		set_color(panel);
	}
	
	public void update_piece(JPanel[][]panel) {
		color = piece_color;
		set_color(panel);
	}
	
	public void reset_piece(JPanel[][] panel) {
		color = white;
		set_color(panel);
	}
	
	// Checks the left side of a tetris piece
	public boolean is_left_empty(JPanel[][] panel) {
		int[] block_r = {block1_r, block2_r, block3_r, block4_r};
		int[] block_c = {block1_c, block2_c, block3_c, block4_c};
		boolean not_self = true;

		for(int i = 0; i < 4; ++i) {
			for(int k = 0; k < 4; ++k) {
				if(block_c[i]-1 == block_c[k] && block_r[i] == block_r[k]) {
					not_self = false;
				}
			}
			if(block_c[i]-1 >= 0 && not_self) {
				if(panel[block_r[i]][block_c[i]-1].getBackground() != white) {
					return false;
				}
			}
			not_self = true;
		}
		return true;
	}
	
	//Checks the right side of a tetris piece
	public boolean is_right_empty(JPanel[][] panel) {
		int[] block_r = {block1_r, block2_r, block3_r, block4_r};
		int[] block_c = {block1_c, block2_c, block3_c, block4_c};
		boolean not_self = true;

		for(int i = 0; i < 4; ++i) {
			for(int k = 0; k < 4; ++k) {
				if(block_c[i]+1 == block_c[k] && block_r[i] == block_r[k]) {
					not_self = false;
				}
			}
			if(block_c[i]+1 <= get_max_rows() && not_self) {
				if(panel[block_r[i]][block_c[i]+1].getBackground() != white) {
					return false;
				}
			}
			not_self = true;
		}
		return true;
	}
	
	// Checks the bottom side of a tetris piece
	public boolean is_empty(JPanel[][] panel) {
		int[] block_r = {block1_r, block2_r, block3_r, block4_r};
		int[] block_c = {block1_c, block2_c, block3_c, block4_c};
		boolean not_self = true;

		for(int i = 0; i < 4; ++i) {
			for(int k = 0; k < 4; ++k) {
				if(block_r[i]+1 == block_r[k] && block_c[i] == block_c[k]) {
					not_self = false;
				}
			}
			if(block_r[i]+1 <= get_max_rows() && not_self) {
				if(panel[block_r[i] + 1][block_c[i]].getBackground() != white) {
					return false;
				}
			}
			not_self = true;
		}
		return true;
	}
	
	private void move_row_down() {
		block1_r += 1;
		block2_r += 1;
		block3_r += 1;
		block4_r += 1;
	}
	
	// Moves tetris piece down if called
	public void move_down(JPanel[][] panel) {
		if(!reached_bottom() && is_empty(panel))
		{
			reset_piece(panel);
			move_row_down();
			update_piece(panel);
		} else if(!is_empty(panel) || reached_bottom()) 
		{
			moving = false;
		} 				
	}
	
	public int get_time() {
		return time;
	}
	
	// Temporary timer until Timer & TimerTask is figured out
	public void delay(JPanel[][] panel) { 
		if(time >= 100000) 
		{			
			move_down(panel);
			time = 0;
		}
		if(time <= 100000) 
		{
			++time;
		}
		System.out.println(time);
	}
	
	public void piece_update(JPanel[][] panel) {
		delay(panel);
//		timer.scheduleAtFixedRate(myTask, 600, 600);
		
//		if(reached_bottom() || moving == false) {
//			timer.cancel();
//		}
	}
	
	private void move_col_right() {
		block1_c += 1;
		block2_c += 1;
		block3_c += 1;
		block4_c += 1;
	}
	private void move_col_left() {
		block1_c -= 1;
		block2_c -= 1;
		block3_c -= 1;
		block4_c -= 1;
	}
	
	// Moves tetris piece to the right
	public void move_right(JPanel[][] panel) {
		if(is_right_empty(panel)) {
			reset_piece(panel);
			move_col_right();
			update_piece(panel);
		}
	}
	
	// Moves tetris piece to the left
	public void move_left(JPanel[][] panel) {
		if(is_left_empty(panel)) {
			reset_piece(panel);
			move_col_left();
			update_piece(panel);
		}
	}
	
	public void increment_rotation() {
		rotation += 1;
		if(rotation > 3) {
			rotation = 0;
		}
	}
	
	// Keeps rotated tetris pieces in bounds
	public void check_sides() {
		while(block1_c > COLS-1 || block2_c > COLS-1 || block3_c > COLS-1 || 
				block4_c > COLS-1) {
			block1_c -= 1;
			block2_c -= 1;
			block3_c -= 1;
			block4_c -= 1;
		}
		while(block1_c < 0 || block2_c < 0 || block3_c < 0 || block4_c < 0) {
			block1_c += 1;
			block2_c += 1;
			block3_c += 1;
			block4_c += 1;
		}
	}
	

	
	private void next_rotation_type_0(JPanel[][] panel) {
		while(block2_r < 1) {
			block1_r += 1;
			block2_r += 1;
			block3_r += 1;
			block4_r += 1;
		}
		block1_r -= rise;
		block1_c += run;
//		block2_r = block2_r;
//		block2_c = block2_c;
		block3_r += rise;
		block3_c -= run;
		block4_r += 2 * rise;
		block4_c -= 2 * run;
		
		rise *= r;
		run *= c;
		
		r *= -1;
		c *= -1;
	}

	public void next_rotation_type_1(JPanel[][] panel) {
		while(block3_r < 1) {
			block1_r += 1;
			block2_r += 1;
			block3_r += 1;
			block4_r += 1;
		}
		if(rotation == 0) {
			block1_r += 0;
			block1_c += 2;
			block2_r += 1;
			block2_c += 1;
//			block3_r = block3_r;
//			block3_c = block3_c;
			block4_r += 1;
			block4_c += -1;
		} else if(rotation == 1) {
			block1_r += 2;
			block1_c += 0;
			block2_r += 1;
			block2_c += -1;
//			block3_r = block3_r;
//			block3_c = block3_c;
			block4_r += -1;
			block4_c += -1;
		} else if(rotation == 2) {
			block1_r += 0;
			block1_c += -2;
			block2_r += -1;
			block2_c += -1;
//			block3_r = block3_r;
//			block3_c = block3_c;
			block4_r += -1;
			block4_c += 1;
		} else if(rotation == 3) {
			block1_r += -2;
			block1_c += 0;
			block2_r += -1;
			block2_c += 1;
//			block3_r = block3_r;
//			block3_c = block3_c;
			block4_r += 1;
			block4_c += 1;
		}
	}
	
	public void next_rotation_type_2(JPanel[][] panel) {
		while(block4_r < 1) {
			block1_r += 1;
			block2_r += 1;
			block3_r += 1;
			block4_r += 1;
		}
		if(rotation == 0) {
			block1_r += 1;
			block1_c += 1;
			block2_r += 2;
			block2_c += 0;
			block3_r += -1;
			block3_c += 1;
//			block4_r = block4_r;
//			block4_c = block4_c;
		} else if(rotation == 1) {
			block1_r += 1;
			block1_c += -1;
			block2_r += 0;
			block2_c += -2;
			block3_r += 1;
			block3_c += 1;
//			block4_r = block4_r;
//			block4_c = block4_c;
		} else if(rotation == 2) {
			block1_r += -1;
			block1_c += -1;
			block2_r += -2;
			block2_c += 0;
			block3_r += 1;
			block3_c += -1;
//			block4_r = block4_r;
//			block4_c = block4_c;
		} else if(rotation == 3) {
			block1_r += -1;
			block1_c += 1;
			block2_r += 0;
			block2_c += 2;
			block3_r += -1;
			block3_c += -1;
//			block4_r = block4_r;
//			block4_c = block4_c;
		}
	}
	
	public void next_rotation_type_3(JPanel[][] panel) {
		while(block3_r < 1) {
			block1_r += 1;
			block2_r += 1;
			block3_r += 1;
			block4_r += 1;
		}
		if(rotation == 0) {
			block1_r += 1;
			block1_c += 1;
			block2_r += -1;
			block2_c += 1;
//			block3_r = block3_r;
//			block3_c = block3_c;
			block4_r += 1;
			block4_c += -1;
		} else if(rotation == 1) {
			block1_r += 1;
			block1_c += -1;
			block2_r += 1;
			block2_c += 1;
//			block3_r = block3_r;
//			block3_c = block3_c;
			block4_r += -1;
			block4_c += -1;
		} else if(rotation == 2) {
			block1_r += -1;
			block1_c += -1;
			block2_r += 1;
			block2_c += -1;
//			block3_r = block3_r;
//			block3_c = block3_c;
			block4_r += -1;
			block4_c += 1;
		} else if(rotation == 3) {
			block1_r += -1;
			block1_c += 1;
			block2_r += -1;
			block2_c += -1;
//			block3_r = block3_r;
//			block3_c = block3_c;
			block4_r += 1;
			block4_c += 1;
		}
	}
	
	public void next_rotation_type_4(JPanel[][] panel) {
		//no rotation
	}
	
	public void next_rotation_type_5(JPanel[][] panel) {
		while(block3_r < 1) {
			block1_r += 1;
			block2_r += 1;
			block3_r += 1;
			block4_r += 1;
		}
		if(rotation == 0) {
			block1_r += 0;
			block1_c += 2;
			block2_r += -1;
			block2_c += 1;
//			block3_r = block3_r;
//			block3_c = block3_c;
			block4_r += 1;
			block4_c += -1;
		} else if(rotation == 1) {
			block1_r += 2;
			block1_c += 0;
			block2_r += 1;
			block2_c += 1;
//			block3_r = block3_r;
//			block3_c = block3_c;
			block4_r += -1;
			block4_c += -1;
		} else if(rotation == 2) {
			block1_r += 0;
			block1_c += -2;
			block2_r += 1;
			block2_c += -1;
//			block3_r = block3_r;
//			block3_c = block3_c;
			block4_r += -1;
			block4_c += 1;
		} else if(rotation == 3) {
			block1_r += -2;
			block1_c += 0;
			block2_r += -1;
			block2_c += -1;
//			block3_r = block3_r;
//			block3_c = block3_c;
			block4_r += 1;
			block4_c += 1;
		}
	}
	
	public void next_rotation_type_6(JPanel[][] panel) {
		while(block3_r < 1) {
			block1_r += 1;
			block2_r += 1;
			block3_r += 1;
			block4_r += 1;
		}
		if(rotation == 0) {
			block1_r += 2;
			block1_c += 0;
			block2_r += -1;
			block2_c += 1;
//			block3_r = block3_r;
//			block3_c = block3_c;
			block4_r += 1;
			block4_c += -1;
		} else if(rotation == 1) {
			block1_r += 0;
			block1_c += -2;
			block2_r += 1;
			block2_c += 1;
//			block3_r = block3_r;
//			block3_c = block3_c;
			block4_r += -1;
			block4_c += -1;
		} else if(rotation == 2) {
			block1_r += -2;
			block1_c += 0;
			block2_r += 1;
			block2_c += -1;
//			block3_r = block3_r;
//			block3_c = block3_c;
			block4_r += -1;
			block4_c += 1;
		} else if(rotation == 3) {
			block1_r += 0;
			block1_c += 2;
			block2_r += -1;
			block2_c += -1;
//			block3_r = block3_r;
//			block3_c = block3_c;
			block4_r += 1;
			block4_c += 1;
		}
	}
	
	// Picks the correct rotation for the specified piece
	public void next_rotation(JPanel[][] panel) {
		if(piece_type == 0) {
			next_rotation_type_0(panel);
		} else if(piece_type == 1) {
			next_rotation_type_1(panel);
		} else if(piece_type == 2) {
			next_rotation_type_2(panel);
		} else if(piece_type == 3) {
			next_rotation_type_3(panel);
		} else if(piece_type == 4) {
			next_rotation_type_4(panel);
		} else if(piece_type == 5) {
			next_rotation_type_5(panel);
		} else if(piece_type == 6) {
			next_rotation_type_6(panel);
		}
	}
	
	public void rotate_right(JPanel[][] panel) {
		reset_piece(panel);
		next_rotation(panel);
		check_sides();
		update_piece(panel);
		increment_rotation();
	}
	
	// Checks if tetris piece has reached the bottom
	public boolean reached_bottom() {
		return block1_r == get_max_rows() || block2_r == get_max_rows() ||
				block3_r == get_max_rows() || block4_r == get_max_rows();
	}
	
	// Checks if tetris piece has reached the left side
	public boolean reached_left_side() {
		return block1_c == 0 || block2_c == 0 || block3_c == 0 || block4_c == 0;
	}
	
	// Checks if tetris piece has reached the right side
	public boolean reached_right_side() {
		return block1_c == get_max_cols() || block2_c == get_max_cols() || 
				block3_c == get_max_cols() || block4_c == get_max_cols();
	}
	
	public boolean get_moving() {
		return moving;
	}
	
	public int get_max_rows() {
		return ROWS-1;
	}
	
	public int get_max_cols() {
		return COLS-1;
	}
}
