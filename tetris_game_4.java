package Computer_Graphics;


//Made by: Ananya Banerjee axb170053
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

//extended JComponent 
class MyCanvas_6 extends JPanel  {
	  
	int centerX, centerY;
	float pixelSize, rWidth = 10.0F, rHeight = 10.0F, xP = 1000000, yP;
	Rectangle rec=new Rectangle(239,0,220,480);
	//Rectangle rec=new Rectangle(iX(-rWidth / 4), iY(rHeight/2),iY(rHeight/80),iY(rHeight/60));
	private boolean insideRect = false;
	Point p = new Point();
	
	MyCanvas_6(){
		
	    //JPanel containing pause button being flashed
		JPanel pause_area=new JPanel();
		pause_area.setLocation(iX(-rWidth/4), iY(rHeight/2));
		pause_area.setBounds(iX(-rWidth/4), iY(rHeight/2),iX(rWidth/2),iY(rHeight));
		//pause_area.setSize(iX(rWidth/2), iY(rHeight));
		pause_area.setPreferredSize(new Dimension());
		pause_area.setBackground(Color.yellow);
		
		
		this.addMouseMotionListener(new MouseMotionAdapter(){
				//@Override
				public void mouseMoved(MouseEvent e) {
					// TODO Auto-generated method stub
					
				  System.out.println("I entered");
				  System.out.println(rec.contains(e.getPoint()));
	              
				  if (rec.contains(e.getPoint())) {
	            	  System.out.println("I am inside rectangle");
	            	  insideRect = true;
	            	  
	            	  
	              } else {
	            	  
	            	  insideRect = false;
	              }   
	              p = e.getPoint();
	              repaint();
	              
	      		
	         
				}
				
			})
				
			;
		//pause_area.setLayout(null);
		pause_area.setVisible(true);
		
		this.add(pause_area);
	}
	
	void initgr() {
		Dimension d = getSize();
		int maxX = d.width - 1, maxY = d.height - 1;
		pixelSize = Math.max(rWidth / maxX, rHeight / maxY);
		centerX = maxX / 2; centerY = maxY / 2;
		}

		int iX(float x) {return Math.round(centerX + x / pixelSize);}
		int iY(float y) {return Math.round(centerY - y / pixelSize);}
		float fx(int x) {return (x - centerX) * pixelSize;}
		float fy(int y) {return (centerY - y) * pixelSize;}

		//function to draw a rectangle using drawline
		public void make_my_rectangle(Graphics g, float x, float y, float width, float height) {
			//RECTANGLE
			int x1=iX(x);
			int y1=iY(y);
			int x2=x1+iX(width);
			int y2=y1-iY(height);
			g.drawLine(x1, y1, x1, y2);
			g.drawLine(x1, y1, x2, y1);
		}

		//function to make a square
		public void draw_my_square(Graphics g, int x1, int y1, int width, Color c) {
		    g.setColor(c);
		    g.fillRect (x1, y1, width, width);
		    g.setColor(Color.black);
		    g.drawRect (x1, y1, width, width);
		}


		public void paint(Graphics g) {
		initgr();

		//making a big rectangular area for game
		 super.paintComponent(g);

		//making a big rectangular area for game

		g.drawLine(iX(-rWidth / 4), iY(rHeight/2), iX(-rWidth / 4), iY(-rHeight/2) );
		g.drawLine(iX(-rWidth / 4), iY(rHeight/2), iX(rWidth / 4), iY(rHeight/2) );
		g.drawLine(iX(rWidth / 4), iY(rHeight/2), iX(rWidth / 4), iY(-rHeight/2) );
		g.drawLine(iX(-rWidth / 4), iY(-rHeight/2), iX(rWidth / 4), iY(-rHeight/2) );

		//rec.add(MouseMotionListener());

		//MAKING green squares
		g.setColor(Color.green);
		g.fillRect(iX(-rWidth/13), iY(rHeight/3),iY(rHeight/3),iY(rHeight/3));
		g.setColor(Color.black);
		g.drawRect(iX(-rWidth/13), iY(rHeight/3),iY(rHeight/3),iY(rHeight/3));
		//2 lines in between green square
		g.drawLine(iX(0), iY(rHeight/3), iX(0), iY(rHeight/6));
		g.drawLine(iX(-rWidth/13), iY(30*rHeight/119), iX(rWidth/11), iY(30*rHeight/119));

		//DRAWING INSIDE MAIN AREA
		//drawing bottom blue block1 bottom right1
		draw_my_square(g, iX(18*rWidth/100), iY(-43*rHeight/100), iY(15*rHeight/35), Color.blue);

		//drawing bottom blue block2 bottom right2->left
		draw_my_square(g, iX(11*rWidth/100), iY(-43*rHeight/100), iY(15*rHeight/35), Color.blue);


		//drawing bottom yellow block 3 right2->left->left
		draw_my_square(g, iX(4*rWidth/100), iY(-43*rHeight/100), iY(15*rHeight/35), Color.yellow);

		//drawing bottom yellow block 3 right2->left->left->left
		draw_my_square(g, iX(-3*rWidth/100), iY(-43*rHeight/100), iY(15*rHeight/35), Color.yellow);

		//drawing bottom yellow block 3 right2->left->left->left->top
		draw_my_square(g, iX(18*rWidth/100), iY(-36*rHeight/100), iY(15*rHeight/35), Color.blue);

		//drawing bottom yellow block 3 right2->left->left->left->top->right
		draw_my_square(g, iX(18*rWidth/100), iY(-29*rHeight/100), iY(15*rHeight/35), Color.blue);


		//drawing bottom blue block 3 right3->top
		draw_my_square(g, iX(11*rWidth/100), iY(-36*rHeight/100), iY(15*rHeight/35), Color.yellow);


		//drawing bottom blue block 4 right4->top
		draw_my_square(g, iX(4*rWidth/100), iY(-36*rHeight/100), iY(15*rHeight/35), Color.yellow);

		//CREATING RIGHT RECTANGLE AREA
		g.drawRect(iX(rWidth/3), iY(rHeight/2), iY(rHeight/6),iY(27*rHeight/100));


		//making red square 1
		draw_my_square(g, iX(55*rWidth/100), iY(39*rHeight/100), iY(15*rHeight/35), Color.red);

		//making red square 2: left of square red 1
		draw_my_square(g, iX(48*rWidth/100), iY(39*rHeight/100), iY(15*rHeight/35), Color.red);

		//making red square 3: left of square red 2
		draw_my_square(g, iX(42*rWidth/100), iY(39*rHeight/100), iY(15*rHeight/35), Color.red);
			    
		//making red square 4: top of square red 1
		draw_my_square(g, iX(55*rWidth/100), iY(46*rHeight/100), iY(15*rHeight/35), Color.red);


		//FONT AND LINES
		g.setFont(new Font("default", Font.BOLD, 12));
		//text 1: LEVEL :1
		g.drawString("Level :           1", iX(rWidth/3), iY(16*rHeight/100));

		//text 2: Lines :0
		g.drawString("Lines :           0",iX(rWidth/3), iY(6*rHeight/100));

		//text 3: SLines: 0
		g.drawString("Scores :          0", iX(rWidth/3), iY(-4*rHeight/100));

		
		//MAKING PAUSE BUTTON APPEAR
		Graphics2D g2 = (Graphics2D) g;

		//g.drawRect(iX(-rWidth / 4), iY(rHeight/2),iY(rHeight/80),iY(rHeight/60));
		
	    g.setColor(Color.black);
	    g2.draw(rec);
	    if (insideRect) {
	    	g.drawRect(iX(-rWidth/7), iY(rHeight/12),iY(16*rHeight/100),iY(40*rHeight/100));
		    g.drawString("PAUSE",iX(-rWidth/16), iY(rHeight/42));
	
	      //  g.drawString("Mouse in Rectangle point " + (int)p.getX() + ", " + (int)p.getY(), 
	         //   (int)p.getX(), (int)p.getY());
	    } 
	    this.setVisible(true);
		
		}

	 
	}


public class tetris_game_4 {
	public static void main(String[] a) {
		   
		    JFrame window = new JFrame();
		    
		    //making Quit Button with its own panel   
		    window.setLayout(new BorderLayout());
			JPanel quitpanel = new JPanel();
			quitpanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			JButton clickmeButton = new JButton("Quit");
			clickmeButton.addActionListener(new ActionListener() {
			   	 public void actionPerformed (ActionEvent e) {
			   	  System.exit(0);
			   	 }
			   });
			quitpanel.add(clickmeButton);
			window.add(quitpanel,BorderLayout.SOUTH);
		    
			
			
		    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		    window.pack();
		    window.setSize(screenSize.width,screenSize.height);  
		    //window.setSize(300,600);
		    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    window.setTitle("Tetris Game");
		    window.setBounds(30, 30, 700, 500);
		    window.getContentPane().add(new MyCanvas_6());
		    window.setVisible(true);

            
		    
		  }
		}



