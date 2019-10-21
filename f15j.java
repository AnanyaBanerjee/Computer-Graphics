package CompG;



import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.List;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



class MyShapZ extends JPanel implements MouseListener, MouseMotionListener,KeyListener  {
	boolean left, scroll_down=false;
	boolean right, scroll_up=false;
	int centerX, centerY;
	float pixelSize, rWidth = 10.0F, rHeight = 10.0F, xP = 1000000, yP;
	Rectangle rec=new Rectangle(239,0,220,480);
	private boolean insideRect = false;
	Point p = new Point();
	int sqr_size=31;
	
	private int squareYLocation;// =iY(rHeight/2)-sqr_size;//0; //iY(0);
	private int squareXLocation;//= iX(-rWidth/4);
	boolean gameRunning = false;
	boolean do_initialize=true;
	int bottom_X, bottom_Y;
	int cw=1; //o check how much clockwise rotation is needed
	int ccw=1; //to check how much counter clock wise rotation is needed
	ArrayList<Integer> final_x = new ArrayList<Integer>(100); 
	ArrayList<Integer> final_y = new ArrayList<Integer>(100); 
	ArrayList<Color> final_color = new ArrayList<Color>(100); 
	ArrayList<String> cw_or_ccw = new ArrayList<String>(100); 
	ArrayList<Integer> angle = new ArrayList<Integer>(100); 
	ArrayList<String> orientation = new ArrayList<String>(100); 
	ArrayList<Integer> amt = new ArrayList<Integer>(100); 
	ArrayList<Integer> T_of_Shape = new ArrayList<Integer>(100); 
	
	Boolean game_run=false;
	//generate random shape between 1 and 8
	int type_of_shape = (int) (Math.random()* 8);
	//int type_of_shape=5;
	
	int no_of_sqr_X;
	int no_of_sqr_Y;
	int[][] multi_arr = new int[no_of_sqr_X][no_of_sqr_Y];
	  	
	boolean change_x=false;
	boolean change_y=false;
	boolean terminate=false;
	//Scoring factor
	int M=2; //(1-10)
	//Level
	int Level=1;
	
	//Speed Factor(0.1-1.0)
	int S=1; //(0.1-1.0)
	//Falling speed
	int FS=1;
	//Score
	int Score=0;
	//Number of rows removed
	int removed=0;
	
	//Status and Slider
	//JLabel status = new JLabel("Value of M");
    DefaultBoundedRangeModel model = new DefaultBoundedRangeModel(1, 0, 1, 10);
    JSlider slider = new JSlider(model);
    
    //number of rows required for each level of difficulty
   //(20-50)
    int N=20;
    
    SpinnerModel value_N =  
            new SpinnerNumberModel(20, //initial value  
               20, //minimum value  
               50, //maximum value  
               1); //step  
    JSpinner spinner = new JSpinner(value_N);   
    //
    JTextField for_S = new JTextField("Set S Value");
    
	 
    MyShapZ(){
			
			
		    //JPanel containing pause button being flashed
			JPanel pause_area=new JPanel();
			pause_area.setLocation(iX(-rWidth/4), iY(rHeight/2));
			pause_area.setBounds(iX(-rWidth/4), iY(rHeight/2),iX(rWidth/2),iY(rHeight));
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
		            	  do_initialize=false;
		            	  repaint();
		            	  
		              } else {
		            	  
		            	  insideRect = false;
		            	  do_initialize=false;
		              }   
		              p = e.getPoint();
		              //repaint();
		              
		      		
		         
					}
					
				})
					
				;
			this.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					
					 if(e.getButton() == MouseEvent.BUTTON1) {
				            //setText("Left Click!");
						    //move the figure shape to left by one square
		                    left=true;
		                    do_initialize=false;
		                    
						    //repaint();
				          }
				          if(e.getButton() == MouseEvent.BUTTON2) {
				            //label.setText("Middle Click!");
				          }
				          if(e.getButton() == MouseEvent.BUTTON3) {
				            //label.setText("Right Click!");
				        	  //move the figure shape to the right by one square
			                   right=true;
			                   do_initialize=false;
				        	 // repaint();
				          }
				          repaint();
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			});
			
			
			this.setFocusable(true);
			this.addMouseWheelListener(new MouseWheelListener() {

				@Override
				public void mouseWheelMoved(MouseWheelEvent e) {
					// TODO Auto-generated method stub
					 //if (e.isControlDown()) {
					        if (e.getWheelRotation() < 0) {
					            System.out.println("mouse wheel Up");
					            scroll_up=true;
					            do_initialize=false;
					            System.out.println("+++++Precision up rotation"+e.getPreciseWheelRotation());
					            System.out.println("+++++scroll amt up rotation"+e.getScrollAmount());
					            System.out.println("%%% Scroll UPPPP PAINTS&&&&&");
					            repaint();
					        } 
					        else {
					            System.out.println("mouse wheel Down");
					            scroll_down=true;
					            do_initialize=false;
					            System.out.println("+++++Precision down rotation"+e.getPreciseWheelRotation());
					            System.out.println("+++++scroll amt down rotation"+e.getScrollAmount());
					            System.out.println("%%% Scroll DOWN PAINTS&&&&&");
					            
					            repaint();
					        }
					   // 
					      //repaint();
				}
				
			});
			 
			
			
			this.setFocusable(true);
			this.addKeyListener(new KeyListener() {

				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void keyPressed(KeyEvent e) {
					//requestFocus();
					System.out.println("******* KEY HEY******");
					// TODO Auto-generated method stub
					if (e.getKeyCode() == KeyEvent.VK_UP) {
						//up arrow key
						System.out.println("up key");
						scroll_up=true;
						do_initialize=false;
			            repaint();
					}
	                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
						//down arrow key
	                	System.out.println("down key");
	                	scroll_down=true;
	                	do_initialize=false;
			            repaint();
					}
	                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
						//left arrow key
	                	System.out.println("left key");
	                	left=true;
	                    do_initialize=false;
	                    
					    repaint();
					}
	                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
						//right arrow key
	                	System.out.println("right key");
	                	right=true;
	                    do_initialize=false;
	                    
					    repaint();
					}
				}

				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			});
			this.requestFocus();
			
			//**Key Bindings**//
			/*
			//Add keybinds for UP and DOWN keys 
			this.requestFocus();
			this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "ScrollUp");
	        this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "ScrollDown");
	        
	        this.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "pressed");

	        Action pressedAction = new AbstractAction() {
	            public void actionPerformed(ActionEvent e) {
	                //do nothing
	            	System.out.println("SPACE");
	            }
	        };
	        this.getActionMap().put("pressed",
                    pressedAction);
	        
	        //Define actions for each key
	        this.getActionMap().put("ScrollUp", new AbstractAction() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	System.out.println("up key");
					scroll_up=true;
					do_initialize=false;
		            repaint();
	            }
	        });
	        
	        this.getActionMap().put("ScrollDown", new AbstractAction() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	System.out.println("down key");
                	scroll_down=true;
                	do_initialize=false;
		            repaint();
	            }
	        });
*/
			
			
			//**End Key Bindings**//
			
			
			
			//repaint();
			//pause_area.setVisible(true);
			
			/*
			this.addComponentListener(new ComponentListener() {
				public void componentResized(ComponentEvent e) {
			        Component c = e.getComponent();
			        System.out.println("componentResized event from " + c.getClass().getName() + "; new size: "
			            + c.getSize().width + ", " + c.getSize().height);
			        do_initialize=true;
			        repaint();
			        
			        
				}

				@Override
				public void componentMoved(ComponentEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void componentShown(ComponentEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void componentHidden(ComponentEvent e) {
					// TODO Auto-generated method stub
					
				}

			});
			
			*/
			
			
			
			slider.addChangeListener(new ChangeListener() {
	            public void stateChanged(ChangeEvent e) {
	                //status.setText("Value of the slider is: " + ((JSlider)e.getSource()).getValue());
	               M=((DefaultBoundedRangeModel) e.getSource()).getValue();
	               }
	        });
			
			spinner.addChangeListener(new ChangeListener() {  
		        public void stateChanged(ChangeEvent e) {  
		          N=((DefaultBoundedRangeModel) e.getSource()).getValue();  
		        }  
		     }); 
			 
			for_S.addActionListener(new java.awt.event.ActionListener() {
		            public void actionPerformed(java.awt.event.ActionEvent evt) {
		                S=Integer.parseInt(for_S.getText());
		                System.out.println(" S is " + S);
			               
		            }
		        });
	       
			pause_area.add(for_S);
	        pause_area.add(slider);
		    pause_area.add(spinner);
			this.add(slider);
			this.add(spinner);
	        this.add(for_S);//, "Center");
	        
			pause_area.setVisible(true);
			System.out.println("i am in constructor");
			 
			 
			this.add(pause_area);
		}
		
	 //function to update
	 public void update(String ori, int amt) {
		 //checking bottom and adding to final list accordingly
		 //check_bottom(squareXLocation, squareYLocation, sqr_size,, amt);
		 System.out.println("updating");
		 check_need_for_termination();
		 System.out.println("starting x="+squareXLocation+" and y="+squareYLocation);
		 
		 boolean to_update;
		 
		 if(game_run) {
		    System.out.println("Bottom reached Nope");
		    squareYLocation=iY(rHeight/2);
			squareXLocation=iX(0);
			to_update=true;
			game_run=false;
		 }
		 else {
		 to_update= check_bottom(squareXLocation, squareYLocation, sqr_size,  ori, amt);
		 }
		 
		 upgrade_arr();
		 if (squareYLocation ==bottom_Y-sqr_size || to_update==false) {
			
			 test(squareXLocation,squareYLocation,ori, amt);
			
			 squareYLocation=iY(rHeight/2);
			 squareXLocation=iX(0);
			// allow_j_update=true;
			 //to_update=true;
			 display_arr();
				
			//generate random shape between 1 and 8
			type_of_shape = (int) (Math.random()* 8);
				
		 }
		 else {
			 if(to_update==true) {
			 squareYLocation=squareYLocation+FS; //+1;
			 //i=i+1
		 }
		 }
		
		 do_initialize=false;
		 
	     repaint();
		 
	 }
	 

	 //sets the while loop to true to start the game
    public void start() {
    	System.out.println("starting falling");
        gameRunning = true;
        
        game_run=true;
    }
    
    void initgr() {
		Dimension d = getSize();
		int maxX = d.width - 1, maxY = d.height - 1;
		pixelSize = Math.max(rWidth / maxX, rHeight / maxY);
		centerX = maxX / 2; centerY = maxY / 2;
		sqr_size=iY(15*rHeight/35);
		squareYLocation=iY(rHeight/2);
		squareXLocation=iX(0);
		bottom_X=iX(-rWidth/4);
	    bottom_Y=iY(-rHeight/2);
	    System.out.println("initial rWidth"+iX(-rWidth/4));
	    System.out.println("initial rHeight"+iY(rHeight/2));
		
		System.out.println("initial sqr_size"+sqr_size);
		System.out.println("initial square x loc"+squareYLocation);
		System.out.println("initial square y loc"+squareXLocation);
		cw_or_ccw.add("cw");
		angle.add(1);
		
		//for width of main area
		int x1,x2,y1,y2;
		x1=iX(-rWidth/4);
		y1=iY(rHeight/2);
		x2=iX(rWidth/4);
		y2=iY(rHeight/2);
		double width_in_X;
        double x3=Math.pow((x2-x1), 2);
        double y3=Math.pow((y2-y1), 2);
        width_in_X = Math.sqrt(x3+y3);
        no_of_sqr_X= Math.abs((int)(width_in_X/sqr_size));
				
	    //for length of main area
        x1=iX(-rWidth/4);
		y1=iY(rHeight/2);
		x2=iX(-rWidth/4);
		y2=iY(-rHeight/2);
		double length_in_Y;
        double x4=Math.pow((x2-x1), 2);
        double y4=Math.pow((y2-y1), 2);
        length_in_Y = Math.sqrt(x4+y4);
        no_of_sqr_Y= Math.abs((int)(length_in_Y/sqr_size));
		
        
        //no_of_sqr_Y=Math.abs((int)(iY(rHeight)/sqr_size));
	    System.out.println("no of x sqr="+no_of_sqr_X);
	    System.out.println("no of y sqr="+no_of_sqr_Y);
		
	    //declare an 2d array and initialize it to 0
	    multi_arr=new int[no_of_sqr_Y][no_of_sqr_X];
	    for(int i=0;i<no_of_sqr_X;i++) {
	      Arrays.fill(multi_arr[i], 0);
	    }  
	    
	    display_arr();
	    
	    //System.out.println("multi[13][2]"+multi_arr[13][2]);
		
	    
		}

    public void display_arr() {
    	for(int i=0;i<no_of_sqr_Y;i++) {
	    	for(int j=0;j<no_of_sqr_X;j++) {
	    		//multi_arr[i][j]=1;
	    		System.out.print(multi_arr[i][j]);
	    	}
	    	System.out.println(" ");
	    }
    }
    
    int iX(float x) {return Math.round(centerX + x / pixelSize);}
	int iY(float y) {return Math.round(centerY - y / pixelSize);}
	float fx(int x) {return (x - centerX) * pixelSize;}
	float fy(int y) {return (centerY - y) * pixelSize;}

	/*
	//function to upgrade_array
	public void upgrade_arr() {
		     int number_of_non_zero=0;
		
	    	for(int j=0;j<no_of_sqr_X;j++) {
	    		if(multi_arr[13][j]!=0) {
	    			number_of_non_zero+=1;
	    		}
	    	}
	    	System.out.println("NON 0 are"+number_of_non_zero);
	    	System.out.println("no_of_sqr_y are"+no_of_sqr_X);
	    	
	    	if(number_of_non_zero==no_of_sqr_X) {
	    		//shift all elements down
	    		System.out.println("SHIFTING ");
	    		for(int i=no_of_sqr_Y-1;i>0;i--) {
	    	    	for(int j=0;j<no_of_sqr_X;j++) {
	    	    		multi_arr[i][j]=multi_arr[i-1][j];
	    	    		
	    	    	}
	    	    	
	    	    }
	    	}
	    	
	    	System.out.println("A# ");
	    	display_arr();
	    
	}*/
	
	//function to upgrade_array
	public void upgrade_arr() {
		     int number_of_non_zero=0;
		
	    	for(int j=0;j<no_of_sqr_X;j++) {
	    		if(multi_arr[12][j]!=0) {
	    			number_of_non_zero+=1;
	    		}
	    	}
	    	System.out.println("NON 0 are"+number_of_non_zero);
	    	System.out.println("no_of_sqr_y are"+no_of_sqr_X);
	    	
	    	if(number_of_non_zero==no_of_sqr_X) {
	    		
	    		//When a line is removed, do:
	    		//update line removed
	    		removed+=1;
	    		//update score
	    		Score=Score+Level*M;
	    		System.out.println(" M is " + M);
	            
	    		
	    		//shift all elements down
	    		System.out.println("SHIFTING ");
	    		for(int i=no_of_sqr_Y-2;i>0;i--) {
	    	    	for(int j=0;j<no_of_sqr_X;j++) {
	    	    		multi_arr[i][j]=multi_arr[i-1][j];
	    	    		
	    	    	}
	    	    	
	    	    }
	    	}
	    	
	    	System.out.println("A# ");
	    	display_arr();
	    
	}
	
	//function to check if no space is left
	public void check_need_for_termination() {
		int []coordi=translate_to_array(squareXLocation,squareYLocation);
		int i=coordi[1];
		int j=coordi[0];
		if(i==13) {
			terminate=false;
			return;
		}
		//if(i!=13) {
		if(multi_arr[i+1][j]!=0 &&(i==1 || i==0)) {
			System.out.println("TERMINATE TERMINATE");
			terminate=true;
			return;
		}
		//}
	}

	 //function to make a square
	public void draw_my_square(Graphics g, int x1, int y1, int width, Color c) {
	    g.setColor(c);
	    g.fillRect (x1, y1, width, width);
	    g.setColor(Color.black);
	    g.drawRect (x1, y1, width, width);
	}
	
	//function to translate array coordinates into (x,y)loc
	public int[] translate_coord(int i, int j) {
		int arr[]=new int[2];
		int corner3x=iX(-rWidth/4);
		int corner3y=iY(-rHeight/2);
		
		//array
	    int A[]= {13,12,11,10,9,8,7,6,5,4,3,2,1,0};
		//int A[]= {0,1,2,3,4,5,6,7,8,9,10,11,12,13};
	    //y coord
		arr[0]=corner3y-A[i]*sqr_size;
		//x coord
		arr[1]=corner3x+j*sqr_size;
		return arr;
	}
	
	//function to draw array from (i,j) to (x,y) pos
	public void draw_array(Graphics g) {
		System.out.println("DRAWING FINALE");
		//Color array
		Color violet=new Color(102,0,153);
		Color dark_green=new Color(0,153,0);
		Color light_blue=new Color(51,204,255);
		Color deep_orange=new Color(255,102,0);
		Color Clr[]= {violet, Color.yellow, Color.blue, Color.red, dark_green, deep_orange, light_blue, Color.pink};
		
		//draw_my_square(g, 240,376, sqr_size, violet);
		//draw_my_square(g, 271,376, sqr_size, violet);
		//draw_my_square(g, 271,407, sqr_size, violet);
		//draw_my_square(g, 302,407, sqr_size, violet);
		
		//multi_arr
		for(int i=0;i<no_of_sqr_Y;i++) {
			for(int j=0;j<no_of_sqr_X;j++) {
				if(multi_arr[i][j]!=0) {
					int arr[]=translate_coord(i,j);
					int y=arr[0];
					int x=arr[1];
					System.out.println("OHHA");
					System.out.println("x="+x+"y ="+y);
				    System.out.println("[i]"+i+" [j]"+j);
					if(multi_arr[i][j]==1) {
						System.out.println("1 is P");
						draw_my_square(g, x, y-sqr_size, sqr_size, Clr[0]);
					}
					if(multi_arr[i][j]==2) {
						draw_my_square(g, x, y, sqr_size, Clr[1]);
						
					}
					if(multi_arr[i][j]==3) {
						draw_my_square(g, x, y, sqr_size, Clr[2]);
						
					}
					if(multi_arr[i][j]==4) {
						draw_my_square(g, x, y, sqr_size, Clr[3]);
						
					}
					if(multi_arr[i][j]==5) {
						draw_my_square(g, x, y, sqr_size, Clr[4]);
						
					}
					if(multi_arr[i][j]==6) {
						draw_my_square(g, x, y, sqr_size, Clr[5]);
						
					}
					if(multi_arr[i][j]==7) {
						draw_my_square(g, x, y, sqr_size, Clr[6]);
						
					}
					if(multi_arr[i][j]==8) {
						draw_my_square(g, x, y, sqr_size, Clr[7]);
						
					}



				}
			}
		}
		
	}
	
	//function to translate (x,y) into array coord(i,j)
	public int[] translate_to_array(int x, float y0) {
		
		int arr[]=new int[2];
		int corner3x=iX(-rWidth/4);
		int corner3y=iY(-rHeight/2);
	    //j pos
		arr[0]=Math.abs((int)((x-corner3x)/sqr_size));
		//i pos
		arr[1]=Math.abs((int)((y0-corner3y)/sqr_size))-1;
		int A[]= {13,12,11,10,9,8,7,6,5,4,3,2,1,0};
		arr[1]=A[arr[1]];
		return arr;
	}
	
	//function to test
	public void test(int x, int y, String which_angle, int ori) {
		System.out.println("TESTING");
		int []coordi=translate_to_array(x,y);
		int i=coordi[1];
		int j=coordi[0];
		System.out.println("I: "+i+" J= "+j);
		
		System.out.println("[i][j]"+multi_arr[i][j]);
		
		//int A[]= {13,12,11,10,9,8,7,6,5,4,3,2,1,0};
		
		System.out.println("Changed I: "+i+" J= "+j);
		//Shape 1
		if(type_of_shape==0) {
			if(ori==1) {
				if(j==1) {
					j=2;
				}
				System.out.println("ORI 1");
				System.out.println("FIlling at:");
				System.out.println("I: "+i+" J= "+j);
				
				multi_arr[i][j]=type_of_shape+1;
             	multi_arr[i][j-1]=type_of_shape+1;
				multi_arr[i-1][j-1]=type_of_shape+1;
				multi_arr[i-1][j-2]=type_of_shape+1;
				
				System.out.println("U0");
				display_arr();
				System.out.println("");
				//System.out.println("OKOKOK");
			}
			else if((ori==2 && which_angle=="cw")||(ori==4 && which_angle=="ccw")) {
				System.out.println("ORI 2/4");
				
				System.out.println("FIlling at:");
				System.out.println("I: "+i+" J= "+j);
				
				
				/*
				multi_arr[i][j]=type_of_shape+1;
             	multi_arr[i][j-1]=type_of_shape+1;
				multi_arr[i+1][j-1]=type_of_shape+1;
				multi_arr[i+1][j-2]=type_of_shape+1;*/
				multi_arr[i][j]=type_of_shape+1;
             	multi_arr[i-1][j]=type_of_shape+1;
				multi_arr[i-1][j+1]=type_of_shape+1;
				multi_arr[i-2][j+1]=type_of_shape+1;
				
			}
			else if(ori==3) {
				System.out.println("ORI 3");
				System.out.println("FIlling at:");
				System.out.println("I: "+i+" J= "+j);
				/*
				multi_arr[i][j]=type_of_shape+1;
             	multi_arr[i-1][j]=type_of_shape+1;
				multi_arr[i-1][j-1]=type_of_shape+1;
				multi_arr[i-1][j-2]=type_of_shape+1;
				*/

				multi_arr[i][j]=type_of_shape+1;
             	multi_arr[i][j+1]=type_of_shape+1;
				multi_arr[i-1][j+1]=type_of_shape+1;
				multi_arr[i-1][j+2]=type_of_shape+1;
				
			}
			else {
				System.out.println("ORI 4/2");
				System.out.println("FIlling at:");
				System.out.println("I: "+i+" J= "+j);
				
				multi_arr[i][j]=type_of_shape+1;
             	multi_arr[i-1][j]=type_of_shape+1;
				multi_arr[i-1][j-1]=type_of_shape+1;
				multi_arr[i-2][j-1]=type_of_shape+1;
			
				/*multi_arr[i][j]=type_of_shape+1;
             	multi_arr[i][j-1]=type_of_shape+1;
				multi_arr[i-1][j-1]=type_of_shape+1;
				multi_arr[i-1][j-2]=type_of_shape+1;*/
			
			}
		}
		//Shape 2
		if(type_of_shape==1) {
			if(ori==1) {
				if(j==1) {
					j=2;
				}
				System.out.println("ORI 1");
				System.out.println("FIlling at:");
				System.out.println("I: "+i+" J= "+j);
				
				multi_arr[i][j]=type_of_shape+1;
             	multi_arr[i][j+1]=type_of_shape+1;
				multi_arr[i-1][j+1]=type_of_shape+1;
				multi_arr[i-1][j+2]=type_of_shape+1;
				
				System.out.println("U0");
				display_arr();
				System.out.println("");
				//System.out.println("OKOKOK");
			}
			else if((ori==2 && which_angle=="cw")||(ori==4 && which_angle=="ccw")) {
				System.out.println("ORI 2/4");
				
				System.out.println("FIlling at:");
				System.out.println("I: "+i+" J= "+j);
				
				
				/*
				multi_arr[i][j]=type_of_shape+1;
             	multi_arr[i][j-1]=type_of_shape+1;
				multi_arr[i+1][j-1]=type_of_shape+1;
				multi_arr[i+1][j-2]=type_of_shape+1;*/

				multi_arr[i][j]=type_of_shape+1;
             	multi_arr[i-1][j]=type_of_shape+1;
				multi_arr[i-1][j+1]=type_of_shape+1;
				multi_arr[i-2][j+1]=type_of_shape+1;
			
			}
			else if(ori==3) {
				System.out.println("ORI 3");
				System.out.println("FIlling at:");
				System.out.println("I: "+i+" J= "+j);
				
				multi_arr[i][j]=type_of_shape+1;
             	multi_arr[i][j+1]=type_of_shape+1;
				multi_arr[i-1][j+1]=type_of_shape+1;
				multi_arr[i-1][j+2]=type_of_shape+1;
				
			}
			else {
				System.out.println("ORI 4/2");
				System.out.println("FIlling at:");
				System.out.println("I: "+i+" J= "+j);
				
				/*
				multi_arr[i][j]=type_of_shape+1;
             	multi_arr[i-1][j]=type_of_shape+1;
				multi_arr[i-1][j-1]=type_of_shape+1;
				multi_arr[i-2][j-1]=type_of_shape+1;
			*/
				multi_arr[i][j]=type_of_shape+1;
             	multi_arr[i-1][j]=type_of_shape+1;
				multi_arr[i-1][j-1]=type_of_shape+1;
				multi_arr[i-2][j-1]=type_of_shape+1;
				
				/*multi_arr[i][j]=type_of_shape+1;
             	multi_arr[i][j-1]=type_of_shape+1;
				multi_arr[i-1][j-1]=type_of_shape+1;
				multi_arr[i-1][j-2]=type_of_shape+1;*/
				
			
			
			}
		}
	   //Shape 3
		if(type_of_shape==2) {
			if(ori==1) {
				//if(j==1) {
				//	j=2;
				//}
				System.out.println("ORI 1");
				System.out.println("FIlling at:");
				System.out.println("I: "+i+" J= "+j);
				
				multi_arr[i][j]=type_of_shape+1;
             	multi_arr[i-1][j]=type_of_shape+1;
				multi_arr[i][j+1]=type_of_shape+1;
				multi_arr[i][j+2]=type_of_shape+1;
				
				System.out.println("U0");
				display_arr();
				System.out.println("");
				//System.out.println("OKOKOK");
			}
			else if((ori==2 && which_angle=="cw")||(ori==4 && which_angle=="ccw")) {
				System.out.println("ORI 2/4");
				
				System.out.println("FIlling at:");
				System.out.println("I: "+i+" J= "+j);
				
				multi_arr[i][j]=type_of_shape+1;
             	multi_arr[i-1][j]=type_of_shape+1;
				multi_arr[i-2][j]=type_of_shape+1;
				multi_arr[i-2][j+1]=type_of_shape+1;
				
			}
			else if(ori==3) {
				System.out.println("ORI 3");
				System.out.println("FIlling at:");
				System.out.println("I: "+i+" J= "+j);
				
				multi_arr[i][j]=type_of_shape+1;
             	multi_arr[i-1][j]=type_of_shape+1;
				multi_arr[i-1][j-1]=type_of_shape+1;
				multi_arr[i-1][j-2]=type_of_shape+1;
				
			}
			else {
				System.out.println("ORI 4/2");
				System.out.println("FIlling at:");
				System.out.println("I: "+i+" J= "+j);
				
				multi_arr[i][j]=type_of_shape+1;
             	multi_arr[i][j+1]=type_of_shape+1;
				multi_arr[i-1][j+1]=type_of_shape+1;
				multi_arr[i-2][j+1]=type_of_shape+1;
			
				/*multi_arr[i][j]=type_of_shape+1;
             	multi_arr[i][j-1]=type_of_shape+1;
				multi_arr[i-1][j-1]=type_of_shape+1;
				multi_arr[i-1][j-2]=type_of_shape+1;*/
			
			}
		}
			//Shape 4
			if(type_of_shape==3) {
				if(ori==1) {
					//if(j==1) {
					//	j=2;
					//}
					System.out.println("ORI 1");
					System.out.println("FIlling at:");
					System.out.println("I: "+i+" J= "+j);
					
					multi_arr[i][j]=type_of_shape+1;
	             	multi_arr[i][j-1]=type_of_shape+1;
					multi_arr[i][j-2]=type_of_shape+1;
					multi_arr[i-1][j]=type_of_shape+1;
					
					System.out.println("U0");
					display_arr();
					System.out.println("");
					//System.out.println("OKOKOK");
				}
				else if((ori==2 && which_angle=="cw")||(ori==4 && which_angle=="ccw")) {
					System.out.println("ORI 2/4");
					
					System.out.println("FIlling at:");
					System.out.println("I: "+i+" J= "+j);
					
					multi_arr[i][j]=type_of_shape+1;
	             	multi_arr[i][j-1]=type_of_shape+1;
					multi_arr[i-1][j-1]=type_of_shape+1;
					multi_arr[i-2][j-1]=type_of_shape+1;
					
				}
				else if(ori==3) {
					System.out.println("ORI 3");
					System.out.println("FIlling at:");
					System.out.println("I: "+i+" J= "+j);
					
					multi_arr[i][j]=type_of_shape+1;
	             	multi_arr[i-1][j]=type_of_shape+1;
					multi_arr[i-1][j+1]=type_of_shape+1;
					multi_arr[i-1][j+2]=type_of_shape+1;
					
				}
				else {
					System.out.println("ORI 4/2");
					System.out.println("FIlling at:");
					System.out.println("I: "+i+" J= "+j);
					
					multi_arr[i][j]=type_of_shape+1;
	             	multi_arr[i-1][j]=type_of_shape+1;
					multi_arr[i-2][j]=type_of_shape+1;
					multi_arr[i-2][j-1]=type_of_shape+1;
				
					/*multi_arr[i][j]=type_of_shape+1;
	             	multi_arr[i][j-1]=type_of_shape+1;
					multi_arr[i-1][j-1]=type_of_shape+1;
					multi_arr[i-1][j-2]=type_of_shape+1;*/
				
				}
			}
			//Shape 5
			if(type_of_shape==4) {
				if(ori==1) {
					//if(j==1) {
					//	j=2;
					//}
					System.out.println("ORI 1");
					System.out.println("FIlling at:");
					System.out.println("I: "+i+" J= "+j);
					
					multi_arr[i][j]=type_of_shape+1;
	             	multi_arr[i-1][j]=type_of_shape+1;
					multi_arr[i][j-1]=type_of_shape+1;
					multi_arr[i-1][j-1]=type_of_shape+1;
					
					System.out.println("U0");
					display_arr();
					System.out.println("");
					//System.out.println("OKOKOK");
				}
				else if((ori==2 && which_angle=="cw")||(ori==4 && which_angle=="ccw")) {
					System.out.println("ORI 2/4");
					
					System.out.println("FIlling at:");
					System.out.println("I: "+i+" J= "+j);
					
					multi_arr[i][j]=type_of_shape+1;
	             	multi_arr[i][j+1]=type_of_shape+1;
					multi_arr[i-1][j+1]=type_of_shape+1;
					multi_arr[i-1][j]=type_of_shape+1;
					
				}
				else if(ori==3) {
					System.out.println("ORI 3");
					System.out.println("FIlling at:");
					System.out.println("I: "+i+" J= "+j);
					
					multi_arr[i][j]=type_of_shape+1;
	             	multi_arr[i][j+1]=type_of_shape+1;
					multi_arr[i+1][j]=type_of_shape+1;
					multi_arr[i+1][j+1]=type_of_shape+1;
					
				}
				else {
					System.out.println("ORI 4/2");
					System.out.println("FIlling at:");
					System.out.println("I: "+i+" J= "+j);
					
					multi_arr[i][j]=type_of_shape+1;
	             	multi_arr[i][j-1]=type_of_shape+1;
					multi_arr[i+1][j]=type_of_shape+1;
					multi_arr[i+1][j-1]=type_of_shape+1;
				
					/*multi_arr[i][j]=type_of_shape+1;
	             	multi_arr[i][j-1]=type_of_shape+1;
					multi_arr[i-1][j-1]=type_of_shape+1;
					multi_arr[i-1][j-2]=type_of_shape+1;*/
				
				}
	}
			//Shape 6
			if(type_of_shape==5) {
				if(ori==1) {
					//if(j==1) {
					//	j=2;
					//}
					System.out.println("ORI 1");
					System.out.println("FIlling at:");
					System.out.println("I: "+i+" J= "+j);
					
					multi_arr[i][j]=type_of_shape+1;
	             	multi_arr[i][j-1]=type_of_shape+1;
					multi_arr[i][j-2]=type_of_shape+1;
					multi_arr[i][j-3]=type_of_shape+1;
					
					System.out.println("U0");
					display_arr();
					System.out.println("");
					//System.out.println("OKOKOK");
				}
				else if((ori==2 && which_angle=="cw")||(ori==4 && which_angle=="ccw")) {
					System.out.println("ORI 2/4");
					
					System.out.println("FIlling at:");
					System.out.println("I: "+i+" J= "+j);
					
					multi_arr[i][j]=type_of_shape+1;
	             	multi_arr[i-1][j]=type_of_shape+1;
					multi_arr[i-2][j]=type_of_shape+1;
					multi_arr[i-3][j]=type_of_shape+1;
					
				}
				else if(ori==3) {
					System.out.println("ORI 3");
					System.out.println("FIlling at:");
					System.out.println("I: "+i+" J= "+j);
					
					multi_arr[i][j]=type_of_shape+1;
	             	multi_arr[i][j+1]=type_of_shape+1;
					multi_arr[i][j+2]=type_of_shape+1;
					multi_arr[i][j+3]=type_of_shape+1;
					
				}
				else {
					System.out.println("ORI 4/2");
					System.out.println("FIlling at:");
					System.out.println("I: "+i+" J= "+j);
					
					multi_arr[i][j]=type_of_shape+1;
	             	multi_arr[i+1][j]=type_of_shape+1;
					multi_arr[i+2][j]=type_of_shape+1;
					multi_arr[i+3][j]=type_of_shape+1;
				
					/*multi_arr[i][j]=type_of_shape+1;
	             	multi_arr[i][j-1]=type_of_shape+1;
					multi_arr[i-1][j-1]=type_of_shape+1;
					multi_arr[i-1][j-2]=type_of_shape+1;*/
				
				}
	}
			//Shape 7
			if(type_of_shape==6) {
				if(ori==1) {
					//if(j==1) {
					//	j=2;
					//}
					System.out.println("ORI 1");
					System.out.println("FIlling at:");
					System.out.println("I: "+i+" J= "+j);
					
					multi_arr[i][j]=type_of_shape+1;
	             	multi_arr[i][j-1]=type_of_shape+1;
					multi_arr[i][j-2]=type_of_shape+1;
					multi_arr[i-1][j-1]=type_of_shape+1;
					
					System.out.println("U0");
					display_arr();
					System.out.println("");
					//System.out.println("OKOKOK");
				}
				else if((ori==2 && which_angle=="cw")||(ori==4 && which_angle=="ccw")) {
					System.out.println("ORI 2/4");
					
					System.out.println("FIlling at:");
					System.out.println("I: "+i+" J= "+j);
					
					multi_arr[i][j]=type_of_shape+1;
	             	multi_arr[i-1][j]=type_of_shape+1;
					multi_arr[i-2][j]=type_of_shape+1;
					multi_arr[i-1][j+1]=type_of_shape+1;
					
				}
				else if(ori==3) {
					System.out.println("ORI 3");
					System.out.println("FIlling at:");
					System.out.println("I: "+i+" J= "+j);
					
					multi_arr[i][j]=type_of_shape+1;
	             	multi_arr[i][j+1]=type_of_shape+1;
					multi_arr[i][j+2]=type_of_shape+1;
					multi_arr[i+1][j+1]=type_of_shape+1;
					
				}
				else {
					System.out.println("ORI 4/2");
					System.out.println("FIlling at:");
					System.out.println("I: "+i+" J= "+j);
					
					multi_arr[i][j]=type_of_shape+1;
	             	multi_arr[i-1][j]=type_of_shape+1;
					multi_arr[i-2][j]=type_of_shape+1;
					multi_arr[i-1][j-1]=type_of_shape+1;
				
					/*multi_arr[i][j]=type_of_shape+1;
	             	multi_arr[i][j-1]=type_of_shape+1;
					multi_arr[i-1][j-1]=type_of_shape+1;
					multi_arr[i-1][j-2]=type_of_shape+1;*/
				
				}
	}
			//Shape 8
			if(type_of_shape==7) {
				if(ori==1) {
					//if(j==1) {
					//	j=2;
					//}
					System.out.println("ORI 1");
					System.out.println("FIlling at:");
					System.out.println("I: "+i+" J= "+j);
					
					multi_arr[i][j]=type_of_shape+1;
	             	multi_arr[i][j-1]=type_of_shape+1;
					multi_arr[i][j-2]=type_of_shape+1;
					multi_arr[i+1][j-1]=type_of_shape+1;
					
					System.out.println("U0");
					display_arr();
					System.out.println("");
					//System.out.println("OKOKOK");
				}
				else if((ori==2 && which_angle=="cw")||(ori==4 && which_angle=="ccw")) {
					System.out.println("ORI 2/4");
					
					System.out.println("FIlling at:");
					System.out.println("I: "+i+" J= "+j);
					multi_arr[i][j]=type_of_shape+1;
	             	multi_arr[i-1][j]=type_of_shape+1;
					multi_arr[i-2][j]=type_of_shape+1;
					multi_arr[i-1][j-1]=type_of_shape+1;
				
				}
				else if(ori==3) {
					System.out.println("ORI 3");
					System.out.println("FIlling at:");
					System.out.println("I: "+i+" J= "+j);
					
					multi_arr[i][j]=type_of_shape+1;
	             	multi_arr[i][j-1]=type_of_shape+1;
					multi_arr[i][j-2]=type_of_shape+1;
					multi_arr[i-1][j-1]=type_of_shape+1;
					
					
				}
				else {
					System.out.println("ORI 4/2");
					System.out.println("FIlling at:");
					System.out.println("I: "+i+" J= "+j);
					

					multi_arr[i][j]=type_of_shape+1;
	             	multi_arr[i-1][j]=type_of_shape+1;
					multi_arr[i-2][j]=type_of_shape+1;
					multi_arr[i-1][j+1]=type_of_shape+1;
					
				}
	}

	}
	
	//function to check if we should print bottom shape
	public boolean check_bottom(int x, int y, int width, String which_angle, int ang) {
		System.out.println("check bottom");
		//translating given to (i,j)
		int []coordi=translate_to_array(x,y);
		int i=coordi[1];
		int j=coordi[0];
		System.out.println("lllll");
		System.out.println("x= "+x+" y="+y);
		System.out.println("i= "+i+" j="+j);
		System.out.println("multi_arr[i][j]="+multi_arr[i][j]);
		//Shape 1
		if(type_of_shape==0) {
			//before everything else
			//check if you have reached bottom
			if(y ==bottom_Y-sqr_size) {//|| x==bottom_X-2*sqr_size) {
				System.out.println("bottom bottom");
				return false;
			}
			//orientation=1
			if(ang==1) { //i==2
				
				if(i==13 && j==2 ) {
					System.out.println("Exiting");
					return false;
				}
				
				if(i==13 && j==6 ) {
					System.out.println("Exiting");
					return false;
				}
				if(i==13) {
					return false;
					
				}
                 if(multi_arr[i+1][j]==0 && multi_arr[i+1][j-1]==0 && multi_arr[i][j-2]==0) {
					
				    System.out.println("O:1");
				    System.out.println("I"+i+" J"+j);
					//allow to update
					return true;
				}
			    
				
			}
			else if((ang==2 && which_angle=="cw")||(ang==4 && which_angle=="ccw")) {
				if(i==13 && j==0 ) {
					System.out.println("Exiting");
					return false;
				}
				if(i==13 && j==5 ) {
					System.out.println("Exiting");
					return false;
				}
				if(i==13) {
					return false;
				}
				if(multi_arr[i+1][j]==0 && multi_arr[i][j+1]==0 ) {
					System.out.println("O:2");
					System.out.println("i="+i+" j="+j);
					//allow to update
					return true;
				}
				
			}
			else if(ang==3) {
				System.out.println("ANA");
				if(i==13 && j==2 ) {
					System.out.println("Exiting");
					return false;
				}
				if(i==13 && j==4) {
					System.out.println("Exiting");
					return false;
				}
				if(i==13) {
					return false;
					
				}
				
				if(multi_arr[i+1][j]==0 && multi_arr[i+1][j+1]==0 && multi_arr[i][j+2]==0) {
					//allow to update
					return true;
				}
			}
			else {
				if(i==13 && j==0 ) {
					System.out.println("Exiting");
					return false;
				}
				
				if(multi_arr[i+1][j]==0 && multi_arr[i][j-1]==0 ) {
					//allow to update
					return true;
				}
			}
		}
		//Shape 2 
        if(type_of_shape==1) {
        	//before everything else
			//check if you have reached bottom
			if(y ==bottom_Y-sqr_size) {//|| x==bottom_X-2*sqr_size) {
				System.out.println("bottom bottom");
				return false;
			}
			//orientation=1
			if(ang==1) { //i==2
				
				System.out.println("ANA");
				if(i==12 && j==0 ) {
					System.out.println("Exiting");
					return false;
				}
				if(i==12 && j==4) {
					System.out.println("Exiting");
					return false;
				}
				if(i==12) {
					
						return false;
					
				}
				
				if(multi_arr[i+1][j]==0 && multi_arr[i+1][j+1]==0 && multi_arr[i][j+2]==0) {
					//allow to update
					return true;
				}
			    
				
			}
			else if((ang==2 && which_angle=="cw")||(ang==4 && which_angle=="ccw")) {
				
				if(i==12 && j==1 ) {
					System.out.println("Exiting");
					return false;
				}
				if(i==12 && j==5 ) {
					System.out.println("Exiting");
					return false;
				}
				if(i==12) {
					return false;
					
				}
				if(multi_arr[i+1][j]==0 && multi_arr[i][j-1]==0 ) {
					//allow to update
					return true;
				}
				
			}
			else if(ang==3) {
				/*
				System.out.println("ANA");
				if(i==12 && j==2 ) {
					System.out.println("Exiting");
					return false;
				}
				if(i==12 && j==4) {
					System.out.println("Exiting");
					return false;
				}
				if(i==13) {
					
						return false;
					
				}
				
				if(multi_arr[i+1][j]==0 && multi_arr[i+1][j+1]==0 && multi_arr[i][j+2]==0) {
					//allow to update
					return true;
				}*/
				if(i==12 && j==0 ) {
					System.out.println("Exiting");
					return false;
				}
				if(i==12 && j==4) {
					System.out.println("Exiting");
					return false;
				}
				if(i==12) {
					
						return false;
					
				}
				
				if(multi_arr[i+1][j]==0 && multi_arr[i+1][j+1]==0 && multi_arr[i][j+2]==0) {
					//allow to update
					return true;
				}
				
			}
			else {
				/*
				if(i==12 && j==0 ) {
					System.out.println("Exiting");
					return false;
				}
				if(i==12 && j==5 ) {
					System.out.println("Exiting");
					return false;
				}
				if(i==13) {
					return false;
				}
				if(multi_arr[i+1][j]==0 && multi_arr[i][j+1]==0 ) {
					System.out.println("O:2");
					System.out.println("i="+i+" j="+j);
					//allow to update
					return true;
				}*/
				if(i==12 && j==0 ) {
					System.out.println("Exiting");
					return false;
				}
				if(i==12 && j==5 ) {
					System.out.println("Exiting");
					return false;
				}
				if(i==12) {
					return false;
				}
				if(multi_arr[i+1][j]==0 && multi_arr[i][j+1]==0 ) {
					System.out.println("O:2");
					System.out.println("i="+i+" j="+j);
					//allow to update
					return true;
				}
				
			}
		}
        //Shape 3
        if(type_of_shape==2) {
        	//check if you have reached bottom
			if(y ==bottom_Y-sqr_size) {//|| x==bottom_X-2*sqr_size) {
				System.out.println("bottom bottom");
				return false;
			}
			if(ang==1) {
				if(j==0 && i==12) {
					return false;
				}
				if(j==4 && i==12) {
					return false;
				}
				
				if(multi_arr[i+1][j]==0 && multi_arr[i+1][j+1]==0 && multi_arr[i+1][j+2]==0) {
					return true;
				}
				
			}
			else if((ang==2 && which_angle=="cw")||(ang==4 && which_angle=="ccw")) {
				if(j==0 && i==12) {
					return false;
				}
				if(j==5 && i==12) {
					return false;
				}
				if(i==12) {
					return false;
				}
				if(multi_arr[i+1][j]==0 && multi_arr[i+1][j+1]==0 && multi_arr[i][j+1]==0 ) {
				 return true;	 
		
				}
				
			}
			else if(ang==3) {
				if(i==12 && j==2) {
					return false;
				}
				if(i==12 && j==6) {
					return false;
				}
				
			    if(multi_arr[i+1][j]==0 && multi_arr[i][j-1]==0 && multi_arr[i][j-2]==0 ) {
				 return true;	 
				 }
			}	
			
			else {
				if(i==13 && j==0) {
					return false;
				}
				if(i==13 && j==5) {
					return false;
				}
				
				if(multi_arr[i+1][j]==0 && multi_arr[i+1][j+1]==0  ) {
					 return true;	 
					 }
				
			}
		}
        //Shape 4
        if(type_of_shape==3) {
			if(ang==1) {
				if(i==12 && j==2) {
					return false;
				}
				if(i==12 && j==6) {
					return false;
				}
				if(i==12) {
					return false;
				}
				if(multi_arr[i+1][j]==0 && multi_arr[i+1][j-1]==0 && multi_arr[i+1][j-2]==0 ) {
					 return true;	 
					 }
			}
			else if((ang==2 && which_angle=="cw")||(ang==4 && which_angle=="ccw")) {
				if(i==12 && j==1) {
					return false;
				}
				if(i==12 && j==6) {
					return false;
				}
				if(i==12) {
					return false;
				}
				if(multi_arr[i+1][j]==0 && multi_arr[i+1][j-1]==0) {
					 return true;	 
					 }
				
			}
			else if(ang==3) {
				if(i==12 && j==0) {
					return false;
				}
				if(i==12 && j==4) {
					return false;
				}
				if(i==12) {
					return false;
				}
				
				if(multi_arr[i+1][j]==0 && multi_arr[i][j+1]==0 && multi_arr[i][j+2]==0 ) {
					 return true;	 
					 }
			}
			else {
				if(i==12 && j==1) {
					return false;
				}
				if(i==12 && j==6) {
					return false;
				}
				if(i==12) {
					return false;
				}
				if(i==0) {
					return true;
				}
				if(multi_arr[i-1][j-1]==0 && multi_arr[i+1][j]==0 ) {
					 return true;	 
					 }
			}
        	
		}
        //Shape 5
        if(type_of_shape==4) {
            if(ang==1) {
            	if(i==12 && j==1)
            	{	return false;
            	}
            	if(i==12 && j==6) {
            		return false;
            	}
            	if(i==12) {
            		return false;
            	}
            	
            	if(multi_arr[i+1][j]==0 && multi_arr[i+1][j-1]==0 ) {
					 return true;	 
					 }
			}
			else if((ang==2 && which_angle=="cw")||(ang==4 && which_angle=="ccw")) {
				if(i==12 && j==0)
            	{	return false;
            	}
            	if(i==12 && j==6) {
            		return false;
            	}
            	if(i==12) {
            		return false;
            	}
            	
            	if(multi_arr[i+1][j]==0 && multi_arr[i+1][j+1]==0 ) {
					 return true;	 
					 }
				
			}
			else if(ang==3) {
				if(i==11 && j==0)
            	{	return false;
            	}
            	if(i==11 && j==5) {
            		return false;
            	}
            	if(i==11) {
            		return false;
            	}
            	
            	if(multi_arr[i+2][j]==0 && multi_arr[i+2][j+1]==0 ) {
					 return true;	 
					 }
			}
			else {
				if(i==11 && j==1)
            	{	return false;
            	}
            	if(i==11 && j==6) {
            		return false;
            	}
            	if(i==11) {
            		return false;
            	}
            	
            	if(multi_arr[i+2][j-1]==0 && multi_arr[i+2][j]==0 ) {
					 return true;	 
					 }
			}
		}
        //Shape:6
		if(type_of_shape==5) {
             if(ang==1) {
            	 if(i==12 && j==3)
             	{	return false;
             	}
             	if(i==12 && j==6) {
             		return false;
             	}
             	if(i==12) {
             		return false;
             	}
             	
             	if(multi_arr[i+1][j]==0 && multi_arr[i+1][j-1]==0 && multi_arr[i+1][j-2]==0 && multi_arr[i+1][j-3]==0) {
 					 return true;	 
 					 }
			}
			else if((ang==2 && which_angle=="cw")||(ang==4 && which_angle=="ccw")) {
				 if(i==12 && j==0)
	             	{	return false;
	             	}
	             	if(i==12 && j==6) {
	             		return false;
	             	}
	             	if(i==12) {
	             		
	             		return false;
	             	}
	             	
	             	if(multi_arr[i+1][j]==0 ) {
	 					 return true;	 
	 					 }
				
			}
			else if(ang==3) {
				 if(i==12 && j==0)
	             	{	return false;
	             	}
	             	if(i==12 && j==3) {
	             		return false;
	             	}
	             	if(i==12) {
	             		return false;
	             	}
	             	
	             	if(multi_arr[i+1][j]==0 && multi_arr[i+1][j+1]==0 && multi_arr[i+1][j+2]==0 && multi_arr[i+1][j+3]==0) {
	 					 return true;	 
	 					 }
			}
			else {
				 if(i==9 && j==0)
	             	{	return false;
	             	}
	             	if(i==9 && j==6) {
	             		return false;
	             	}
	             	if(i==9) {
	             		return false;
	             	}
	             	
	             	if(multi_arr[i+4][j]==0 ) {
	 					 return true;	 
	 					 }
			}
		}
		//Shape 7 
        if(type_of_shape==6) {
            if(ang==1) {
            	if(i==12 && j==2)
             	{	return false;
             	}
             	if(i==12 && j==4) {
             		return false;
             	}
             	if(i==12) {
             		return false;
             	}
             	
             	if(multi_arr[i+1][j]==0 && multi_arr[i+1][j-1]==0 && multi_arr[i+1][j-2]==0 ) {
 					 return true;	 
 					 }
			}
			else if((ang==2 && which_angle=="cw")||(ang==4 && which_angle=="ccw")) {
				if(i==12 && j==0)
             	{	return false;
             	}
             	if(i==12 && j==5) {
             		return false;
             	}
             	if(i==12) {
             		return false;
             	}
             	
             	if(multi_arr[i+1][j]==0 && multi_arr[i][j+1]==0 ) {
 					 return true;	 
 					 }
				
			}
			else if(ang==3) {
				if(i==11 && j==0)
             	{	return false;
             	}
             	if(i==11 && j==4) {
             		return false;
             	}
             	if(i==11) {
             		return false;
             	}
             	
             	if(multi_arr[i+1][j]==0 && multi_arr[i+2][j+1]==0 &&  multi_arr[i+1][j+2]==0) {
 					 return true;	 
 					 }

			}
			else {
				if(i==12 && j==1)
             	{	return false;
             	}
             	if(i==12 && j==6) {
             		return false;
             	}
             	if(i==12) {
             		return false;
             	}
             	
             	if(multi_arr[i+1][j]==0 && multi_arr[i][j-1]==0 ) {
 					 return true;	 
 					 }

			}
		}
        //Shape 8:
        if(type_of_shape==7) {
            if(ang==1) {
            	if(i==11 && j==2)
             	{	return false;
             	}
             	if(i==11 && j==6) {
             		return false;
             	}
             	if(i==11) {
             		return false;
             	}
             	
             	if(multi_arr[i+1][j]==0 && multi_arr[i+2][j-1]==0 && multi_arr[i+1][j-2]==0 ) {
 					 return true;	 
 					 }
			}
			else if((ang==2 && which_angle=="cw")||(ang==4 && which_angle=="ccw")) {
				if(i==12 && j==1)
             	{	return false;
             	}
             	if(i==12 && j==6) {
             		return false;
             	}
             	if(i==12) {
             		return false;
             	}
             	
             	if(multi_arr[i+1][j]==0 && multi_arr[i][j-1]==0 ) {
 					 return true;	 
 					 }
				
			}
			else if(ang==3) {
				if(i==12 && j==0)
             	{	return false;
             	}
             	if(i==12 && j==4) {
             		return false;
             	}
             	if(i==12) {
             		return false;
             	}
             	
             	if(multi_arr[i+1][j]==0 && multi_arr[i+1][j-1]==0 && multi_arr[i+1][j-2]==0 ) {
 					 return true;	 
 					 }
			}
			else {
				if(i==12 && j==0)
             	{	return false;
             	}
             	if(i==12 && j==5) {
             		return false;
             	}
             	if(i==12) {
             		return false;
             	}
             	
             	if(multi_arr[i+1][j]==0 && multi_arr[i][j+1]==0 ) {
 					 return true;	 
 					 }
				
				/*
				if(i==12 && j==1)
             	{	return false;
             	}
             	if(i==12 && j==6) {
             		return false;
             	}
             	if(i==12) {
             		return false;
             	}
             	
             	if(multi_arr[i+1][j-1]==0 && multi_arr[i+2][j-2]==0 ) {
 					 return true;	 
 					 }*/
			}
		}
        return false;
	}
	
	
	
	      
			
			
			//Right Z Shape: OO   SHAPE 1
			//                OO
				//make_right_Z_shape(g, iX(-2*rWidth/5), iY(rHeight/3), iY(15*rHeight/35), Color.cyan);
			  	//angles such as 0,180,360
			public void make_right_Z_shape(Graphics g, int x1, int y1, int width, Color c, String which_angle, int angle) {
					if((which_angle=="cw" && cw==1) || angle==1 || (which_angle=="ccw" && ccw==1)) {
						//x1 and y1 are coordinates for 1st square drawm
						  draw_my_square(g, x1, y1, width, c);
					      draw_my_square(g, x1-width, y1, width, c);
					      draw_my_square(g, x1-width, y1-width, width, c);
					      draw_my_square(g, x1-2*width, y1-width, width, c);
					     	
					}
			   	
					else if((which_angle=="cw" && cw==2)|| (which_angle=="ccw" && ccw==4)) {
						//x1 and y1 are coordinates for 1st square drawm
						draw_my_square(g, x1, y1, width, c);
				    	draw_my_square(g, x1, y1+width, width, c);
				    	draw_my_square(g, x1-width, y1+width, width, c);
				    	draw_my_square(g, x1-width, y1+2*width, width, c);
				    	//length_shape=x1;
				    	//width_shape=y1+4*width;
				    	
						
					}
					else if(cw==3 || ccw==3) {
						//x1 and y1 are coordinates for 1st square drawm
						draw_my_square(g, x1, y1, width, c);
						draw_my_square(g, x1+width, y1, width, c);
						draw_my_square(g, x1+width, y1-width, width, c);
						draw_my_square(g, x1+2*width, y1-width, width, c);
			        	//length_shape=x1+4*width;
				    	//width_shape=y1;
				    	
					}
					else {//if(cw==4) {
						//angles such as 90, 270
						//x1 and y1 are coordinates for 1st square drawm
				    	//length_shape=x1;
				    	//width_shape=y1+4*width;

						draw_my_square(g, x1, y1, width, c);
				    	draw_my_square(g, x1, y1+width, width, c);
				    	draw_my_square(g, x1+width, y1+width, width, c);
				    	draw_my_square(g, x1+width, y1+2*width, width, c);
				    	
					}
					
					
			    
			    
			}
			
			
			
			//Left Z Shape:   OO      SHAPE 2
			//               OO
			public void make_left_Z_shape(Graphics g, int x1, int y1, int width, Color c, String which_angle, int angle) {
				//x1 and y1 are coordinates for 1st square drawm
				//make_left_Z_shape(g, iX(-12*rWidth/25), iY(rHeight/3), iY(15*rHeight/35), Color.cyan);
				if((which_angle=="cw" && cw==1) || angle==1 || (which_angle=="ccw" && ccw==1)) {
					//x1 and y1 are coordinates for 1st square drawm
					draw_my_square(g, x1, y1, width, c);
					draw_my_square(g, x1+width, y1, width, c);
					draw_my_square(g, x1+width, y1-width, width, c);
					draw_my_square(g, x1+2*width, y1-width, width, c);
						
				}
		   	
				else if((which_angle=="cw" && cw==2)|| (which_angle=="ccw" && ccw==4)) {
					//x1 and y1 are coordinates for 1st square drawm
					draw_my_square(g, x1, y1, width, c);
			    	draw_my_square(g, x1, y1+width, width, c);
			    	draw_my_square(g, x1-width, y1+width, width, c);
			    	draw_my_square(g, x1-width, y1+2*width, width, c);
			    	//length_shape=x1;
			    	//width_shape=y1+4*width;
			    	
					
				}
				else if(cw==3 || ccw==3) {
					//x1 and y1 are coordinates for 1st square drawm
					draw_my_square(g, x1, y1, width, c);
					draw_my_square(g, x1-width, y1, width, c);
					draw_my_square(g, x1-width, y1+width, width, c);
					draw_my_square(g, x1-2*width, y1+width, width, c);
					//length_shape=x1+4*width;
			    	//width_shape=y1;
			    	
				}
				else {//if(cw==4) {
					//angles such as 90, 270
					//x1 and y1 are coordinates for 1st square drawm
			    	
					draw_my_square(g, x1, y1, width, c);
			    	draw_my_square(g, x1, y1+width, width, c);
			    	draw_my_square(g, x1+width, y1+width, width, c);
			    	draw_my_square(g, x1+width, y1+2*width, width, c);
			    	//length_shape=x1;
			    	//width_shape=y1+4*width;
			    	
				}
			
				
			}  
			
			
			//Right inverted L: O   SHAPE 3
			//                  OOO
			public void make_right_inverted_L_shape(Graphics g, int x1, int y1, int width, Color c, String which_angle, int angle) {
		    	//x1 and y1 are coordinates for 1st square drawm
				//make_right_inverted_L_shape(g, iX(-8*rWidth/10), iY(1*rHeight/10), iY(15*rHeight/35), Color.blue);
				
				if((which_angle=="cw" && cw==1) || angle==1 || (which_angle=="ccw" && ccw==1)) {
					//x1 and y1 are coordinates for 1st square drawm
					draw_my_square(g, x1, y1, width, c);
			    	draw_my_square(g, x1, y1-width, width, c);
			    	draw_my_square(g, x1+width, y1, width, c);
			    	draw_my_square(g, x1+2*width, y1, width, c);
			    	
				}
		   	
				else if((which_angle=="cw" && cw==2)|| (which_angle=="ccw" && ccw==4)) {
					//x1 and y1 are coordinates for 1st square drawm
					draw_my_square(g, x1, y1, width, c);
			    	draw_my_square(g, x1-width, y1, width, c);
			    	draw_my_square(g, x1-width, y1+width, width, c);
			    	draw_my_square(g, x1-width, y1+2*width, width, c);
			    	//length_shape=x1;
			    	//width_shape=y1+4*width;
			    	
					
				}
				else if(cw==3 || ccw==3) {
					//x1 and y1 are coordinates for 1st square drawm
					draw_my_square(g, x1, y1, width, c);
					draw_my_square(g, x1-width, y1, width, c);
					draw_my_square(g, x1-2*width, y1, width, c);
					draw_my_square(g, x1, y1+width, width, c);
					//length_shape=x1+4*width;
			    	//width_shape=y1;
			    	
				}
				else {//if(cw==4) {
					//angles such as 90, 270
					//x1 and y1 are coordinates for 1st square drawm
			    	
					draw_my_square(g, x1, y1, width, c);
			    	draw_my_square(g, x1+width, y1, width, c);
			    	draw_my_square(g, x1+width, y1-width, width, c);
			    	draw_my_square(g, x1+width, y1-2*width, width, c);
			    	//length_shape=x1;
			    	//width_shape=y1+4*width;
			    	
				}
			
			
			
		    } 
			
			
			//Left inverted L:   O  SHAPE 4
			//                 OOO
			public void make_left_inverted_L_shape(Graphics g, int x1, int y1, int width, Color c, String which_angle, int angle) {
		    	//x1 and y1 are coordinates for 1st square drawm
				if((which_angle=="cw" && cw==1) || angle==1 || (which_angle=="ccw" && ccw==1)) {
					//x1 and y1 are coordinates for 1st square drawm
					draw_my_square(g, x1, y1, width, c);
			    	draw_my_square(g, x1, y1-width, width, c);
			    	draw_my_square(g, x1-width, y1, width, c);
			    	draw_my_square(g, x1-2*width, y1, width, c);
			    	
				}
		   	
				else if((which_angle=="cw" && cw==2)|| (which_angle=="ccw" && ccw==4)) {
					//x1 and y1 are coordinates for 1st square drawm
					//x1 and y1 are coordinates for 1st square drawm
					draw_my_square(g, x1, y1, width, c);
			    	draw_my_square(g, x1-width, y1, width, c);
			    	draw_my_square(g, x1-width, y1-width, width, c);
			    	draw_my_square(g, x1-width, y1-2*width, width, c);
			    //length_shape=x1;
			    	//width_shape=y1+4*width;
			    	
					
				}
				else if(cw==3 || ccw==3) {
					//x1 and y1 are coordinates for 1st square drawm
					draw_my_square(g, x1, y1, width, c);
					draw_my_square(g, x1-width, y1, width, c);
					draw_my_square(g, x1-2*width, y1, width, c);
					draw_my_square(g, x1-2*width, y1+width, width, c);
					//length_shape=x1+4*width;
			    	//width_shape=y1;
			    	
				}
				else {//if(cw==4) {
					//angles such as 90, 270
					//x1 and y1 are coordinates for 1st square drawm
                    draw_my_square(g, x1, y1, width, c);
			    	draw_my_square(g, x1-width, y1, width, c);
			    	draw_my_square(g, x1, y1+width, width, c);
			    	draw_my_square(g, x1, y1+2*width, width, c);
			    	//length_shape=x1;
			    	//width_shape=y1+4*width;
			    	
				}
			
		    }
			
			//Square 2X2 : OO       SHAPE 5
			//             OO
			public void make_square_shape(Graphics g, int x1, int y1, int width, Color c, String which_angle, int angle) {
		    	//x1 and y1 are coordinates for 1st square drawm
		    	if((which_angle=="cw" && cw==1) || angle==1 || (which_angle=="ccw" && ccw==1)) {
					//x1 and y1 are coordinates for 1st square drawm
		    		draw_my_square(g, x1, y1, width, c);
			    	draw_my_square(g, x1, y1-width, width, c);
			    	draw_my_square(g, x1+width, y1, width, c);
			    	draw_my_square(g, x1+width, y1-width, width, c);
			    	
				}
		   	
				else if((which_angle=="cw" && cw==2)|| (which_angle=="ccw" && ccw==4)) {
					//x1 and y1 are coordinates for 1st square drawm
					//x1 and y1 are coordinates for 1st square drawm
					draw_my_square(g, x1, y1, width, c);
			    	draw_my_square(g, x1, y1-width, width, c);
			    	draw_my_square(g, x1-width, y1, width, c);
			    	draw_my_square(g, x1-width, y1-width, width, c);
			    	  //length_shape=x1;
			    	//width_shape=y1+4*width;
			    	
					
				}
				else if(cw==3 || ccw==3) {
					//x1 and y1 are coordinates for 1st square drawm
					draw_my_square(g, x1, y1, width, c);
			    	draw_my_square(g, x1, y1+width, width, c);
			    	draw_my_square(g, x1+width, y1, width, c);
			    	draw_my_square(g, x1+width, y1+width, width, c);
			    	//length_shape=x1+4*width;
			    	//width_shape=y1;
			    	
				}
				else {//if(cw==4) {
					//angles such as 90, 270
					//x1 and y1 are coordinates for 1st square drawm
					draw_my_square(g, x1, y1, width, c);
			    	draw_my_square(g, x1, y1+width, width, c);
			    	draw_my_square(g, x1-width, y1, width, c);
			    	draw_my_square(g, x1-width, y1+width, width, c);
			    	//length_shape=x1;
			    	//width_shape=y1+4*width;
			    	
				}
			
		    }
		    
			
			  //line 1X4 : OOOO       SHAPE 6
			public void make_line_shape(Graphics g, int x1, int y1, int width, Color c, String which_angle, int angle) {
		    	//System.out.println("MAKING LINE");
				if (which_angle=="cw") {
					
				//angles such as 0,180,360
				if(cw==1 || angle==1) {
					//x1 and y1 are coordinates for 1st square drawm
			    	draw_my_square(g, x1, y1, width, c);
			    	draw_my_square(g, x1-width, y1, width, c);
			    	draw_my_square(g, x1-2*width, y1, width, c);
			    	draw_my_square(g, x1-3*width, y1, width, c);
			    //	length_shape=x1+4*width;
			    //	width_shape=y1;
			    	
				}
				
				else if(cw==2) {
					//x1 and y1 are coordinates for 1st square drawm
			    	draw_my_square(g, x1, y1, width, c);
			    	draw_my_square(g, x1, y1-width, width, c);
			    	draw_my_square(g, x1, y1-2*width, width, c);
			    	draw_my_square(g, x1, y1-3*width, width, c);
			    	//length_shape=x1;
			    	//width_shape=y1+4*width;
			    	
					
				}
				else if(cw==3) {
					//x1 and y1 are coordinates for 1st square drawm
			    	draw_my_square(g, x1, y1, width, c);
			    	draw_my_square(g, x1+width, y1, width, c);
			    	draw_my_square(g, x1+2*width, y1, width, c);
			    	draw_my_square(g, x1+3*width, y1, width, c);
			    	//length_shape=x1+4*width;
			    	//width_shape=y1;
			    	
				}
				else {//if(cw==4) {
					//angles such as 90, 270
					//x1 and y1 are coordinates for 1st square drawm
			    	draw_my_square(g, x1, y1, width, c);
			    	draw_my_square(g, x1, y1+width, width, c);
			    	draw_my_square(g, x1, y1+2*width, width, c);
			    	draw_my_square(g, x1, y1+3*width, width, c);
			    	//length_shape=x1;
			    	//width_shape=y1+4*width;
			    	
				}
				}
				else {
					ccw=angle;
				if(ccw==1 ) {
					//x1 and y1 are coordinates for 1st square drawm
			    	draw_my_square(g, x1, y1, width, c);
			    	draw_my_square(g, x1-width, y1, width, c);
			    	draw_my_square(g, x1-2*width, y1, width, c);
			    	draw_my_square(g, x1-3*width, y1, width, c);
			    	//length_shape=x1+4*width;
			    	//width_shape=y1;
					}
				else if(ccw==2) {
					//x1 and y1 are coordinates for 1st square drawm
			    	draw_my_square(g, x1, y1, width, c);
			    	draw_my_square(g, x1, y1+width, width, c);
			    	draw_my_square(g, x1, y1+2*width, width, c);
			    	draw_my_square(g, x1, y1+3*width, width, c);
			    	//length_shape=x1;
			    	//width_shape=y1+4*width;
					
				}
				
				else if(ccw==3) {
					//x1 and y1 are coordinates for 1st square drawm
			    	draw_my_square(g, x1, y1, width, c);
			    	draw_my_square(g, x1+width, y1, width, c);
			    	draw_my_square(g, x1+2*width, y1, width, c);
			    	draw_my_square(g, x1+3*width, y1, width, c);
			    	//length_shape=x1+4*width;
			    	//width_shape=y1;
					
					
				}
				else { //ccw==4
					//x1 and y1 are coordinates for 1st square drawm
			    	draw_my_square(g, x1, y1, width, c);
			    	draw_my_square(g, x1, y1-width, width, c);
			    	draw_my_square(g, x1, y1-2*width, width, c);
			    	draw_my_square(g, x1, y1-3*width, width, c);
			    	//length_shape=x1;
			     	//width_shape=y1+4*width;
					
				}}
		    }
			
			
		    
			
			//raised in the middle Top shape:  O     SHAPE 7
			//                                OOO
			public void make_raised_in_middle_top_shape(Graphics g, int x1, int y1, int width, Color c, String which_angle, int angle) {
		    	//x1 and y1 are coordinates for 1st square drawm
		    		if((which_angle=="cw" && cw==1) || angle==1 || (which_angle=="ccw" && ccw==1)) {
						//x1 and y1 are coordinates for 1st square drawm
						draw_my_square(g, x1, y1, width, c);
				    	draw_my_square(g, x1-width, y1, width, c);
				    	draw_my_square(g, x1-2*width, y1, width, c);
				    	draw_my_square(g, x1-width, y1-width, width, c);
				    	
					}
			   	
					else if((which_angle=="cw" && cw==2)|| (which_angle=="ccw" && ccw==4)) {
						//x1 and y1 are coordinates for 1st square drawm
						//length_shape=x1;
				    	//width_shape=y1+4*width;

						draw_my_square(g, x1, y1, width, c);
				    	draw_my_square(g, x1, y1-width, width, c);
				    	draw_my_square(g, x1, y1-2*width, width, c);
				    	draw_my_square(g, x1+width, y1-width, width, c);
				    
						
					}
					else if(cw==3 || ccw==3) {
						//x1 and y1 are coordinates for 1st square drawm
						draw_my_square(g, x1, y1, width, c);
						draw_my_square(g, x1+width, y1, width, c);
						draw_my_square(g, x1+2*width, y1, width, c);
						draw_my_square(g, x1+width, y1+width, width, c);
						//length_shape=x1+4*width;
				    	//width_shape=y1;
				    	
					}
					else {//if(cw==4) {
						//angles such as 90, 270
						//x1 and y1 are coordinates for 1st square drawm
						draw_my_square(g, x1, y1, width, c);
				    	draw_my_square(g, x1, y1-width, width, c);
				    	draw_my_square(g, x1, y1-2*width, width, c);
				    	draw_my_square(g, x1-width, y1-width, width, c);
				        //length_shape=x1;
				    	//width_shape=y1+4*width;
				    	
					}
				
		    }
			
			//raised in the middle DOWN shape: OOO     SHAPE 8
					//                          O
			public void make_raised_in_middle_down_shape(Graphics g, int x1, int y1, int width, Color c, String which_angle, int angle) {
				  //x1 and y1 are coordinates for 1st square drawm
				   if((which_angle=="cw" && cw==1) || angle==1 || (which_angle=="ccw" && ccw==1)) {
						//x1 and y1 are coordinates for 1st square drawm
					   draw_my_square(g, x1, y1, width, c);
					   draw_my_square(g, x1-width, y1, width, c);
					   draw_my_square(g, x1-2*width, y1, width, c);
					   draw_my_square(g, x1-width, y1+width, width, c);
					  
					}
			   	
					else if((which_angle=="cw" && cw==2)|| (which_angle=="ccw" && ccw==4)) {
						//x1 and y1 are coordinates for 1st square drawm
						//length_shape=x1;
				    	//width_shape=y1+4*width;

						draw_my_square(g, x1, y1, width, c);
				    	draw_my_square(g, x1, y1-width, width, c);
				    	draw_my_square(g, x1, y1-2*width, width, c);
				    	draw_my_square(g, x1-width, y1-width, width, c);
				    
						
						
					}
					else if(cw==3 || ccw==3) {
						//x1 and y1 are coordinates for 1st square drawm
						draw_my_square(g, x1, y1, width, c);
						draw_my_square(g, x1+width, y1, width, c);
						draw_my_square(g, x1+2*width, y1, width, c);
						draw_my_square(g, x1+width, y1-width, width, c);
						//length_shape=x1+4*width;
				    	//width_shape=y1;
				    	
					}
					else {//if(cw==4) {
						//angles such as 90, 270
						//x1 and y1 are coordinates for 1st square drawm
						draw_my_square(g, x1-2*width, y1+width, width, c);
				    	draw_my_square(g, x1-2*width, y1, width, c);
				    	draw_my_square(g, x1-2*width, y1-width, width, c);
				    	draw_my_square(g, x1-width, y1, width, c);
				        //length_shape=x1;
				    	//width_shape=y1+4*width;
				    	
					}
				
    	
				    }

			
			//function to check left boundary
			public int [] check_left_boundary(int x0, float y0, int ang, String which_angle, int width) {
				System.out.println("checking left");
				System.out.println("Before checking our right x="+x0+" and y= "+y0);
				
				
				int arr[]=translate_to_array(x0,y0);
				//this returns [i][j]
				int i=arr[1]; //i handles y-axis
				int j=arr[0]; //j handles x-axis
				System.out.println("Before checking left: i="+i+" and j="+j);
				
				if(type_of_shape==0) {
					System.out.println("CO");
					if(ang==1) {
						if(j<=2) {
							j=2;
						}
						else {
							j=j-1;
						}
					}
					else if((ang==2 && which_angle=="cw")||(ang==4 && which_angle=="ccw")) {
					    if(j<=0) {
					    	System.out.println("I am moving left");
							
					    	j=0;
					    }
					    else {
							j=j-1;
						}
					}
					else if(ang==3) {
						if(j<=0) {
							j=0;
						}
						else {
							j=j-1;
						}
					}
					else {
						if(j<=2) {
							j=2;
						}
						else {
							j=j-1;
						}
					}
				}
				//Shape:2
				if(type_of_shape==1) {
					System.out.println("SO");
					if(ang==1) {
						if(j<=0) {
							j=0;
						}
						else {
							j=j-1;
						}
					}
					else if((ang==2 && which_angle=="cw")||(ang==4 && which_angle=="ccw")) {
						if(j<=1) {
							j=1;
						}
						else {
							j=j-1;
						}
					}
					else if(ang==3) {
						if(j<=0) {
							j=0;
						}
						else {
							j=j-1;
						}
					}
					else {
						if(j<=0) {
					    	//System.out.println("I am moving left");
							
					    	j=0;
					    }
					    else {
							j=j-1;
						}
					}
				}
				
				//Shape:3
				if(type_of_shape==2) {
					if(ang==1) {
						if(j<=0) {
							j=0;
						}
						else {
							j=j-1;
						}
					}
					else if((ang==2 && which_angle=="cw")||(ang==4 && which_angle=="ccw")) {
						if(j<=0) {
							j=0;
						}
						else {
							j=j-1;
						} 
						
					}
					else if(ang==3) {
						if(j<=2) {
							j=2;
						}
						else {
							j=j-1;
						}
					}
					else {
						if(j<=0) {
							j=0;
						}
						else {
							j=j-1;
						}
					}
				}
				//Shape:4
				if(type_of_shape==3) {
					if(ang==1) {
						if(j<=2) {
							j=2;
						}
						else {
							j=j-1;
						}
					}
					else if((ang==2 && which_angle=="cw")||(ang==4 && which_angle=="ccw")) {
						if(j<=1) {
							j=1;
						}
						else {
							j=j-1;
						} 
						
					}
					else if(ang==3) {
						if(j<=0) {
							j=0;
						}
						else {
							j=j-1;
						}
					}
					else {
						if(j<=1) {
							j=1;
						}
						else {
							j=j-1;
						}
					}
				}
				//Shape:5
				if(type_of_shape==4) {
					if(ang==1) {
						if(j<=1) {
							j=1;
						}
						else {
							j=j-1;
						}
					}
					else if((ang==2 && which_angle=="cw")||(ang==4 && which_angle=="ccw")) {
						if(j<=0) {
							j=0;
						}
						else {
							j=j-1;
						} 
						
					}
					else if(ang==3) {
						if(j<=0) {
							j=0;
						}
						else {
							j=j-1;
						}
					}
					else {
						if(j<=1) {
							j=1;
						}
						else {
							j=j-1;
						}
					}
				}
				//Shape:6
				if(type_of_shape==5) {
					if(ang==1) {
						if(j<=3) {
							j=3;
						}
						else {
							j=j-1;
						}
					}
					else if((ang==2 && which_angle=="cw")||(ang==4 && which_angle=="ccw")) {
						if(j<=0) {
							j=0;
						}
						else {
							j=j-1;
						} 
						
					}
					else if(ang==3) {
						if(j<=0) {
							j=0;
						}
						else {
							j=j-1;
						}
					}
					else {
						if(j<=0) {
							j=0;
						}
						else {
							j=j-1;
						}
					}
				}
				//Shape:7
				if(type_of_shape==6) {
					if(ang==1) {
						if(j<=2) {
							j=2;
						}
						else {
							j=j-1;
						}
					}
					else if((ang==2 && which_angle=="cw")||(ang==4 && which_angle=="ccw")) {
						if(j<=0) {
							j=0;
						}
						else {
							j=j-1;
						} 
						
					}
					else if(ang==3) {
						if(j<=1) {
							j=1;
						}
						else {
							j=j-1;
						}
					}
					else {
						if(j<=1) {
							j=1;
						}
						else {
							j=j-1;
						}
					}
				}
				//Shape:8
				if(type_of_shape==7) {
					if(ang==1) {
						if(j<=2) {
							j=2;
						}
						else {
							j=j-1;
						}
					}
					else if((ang==2 && which_angle=="cw")||(ang==4 && which_angle=="ccw")) {
						if(j<=1) {
							j=1;
						}
						else {
							j=j-1;
						} 
						
					}
					else if(ang==3) {
						if(j<=2) {
							j=2;
						}
						else {
							j=j-1;
						}
					}
					else {
						if(j<=1) {
							j=1;
						}
						else {
							j=j-1;
						}
					}
				}
				
				int finale[]=translate_coord(i,j);
				System.out.println("After checking left: i="+i+" and j="+j);
				System.out.println("After checking left: x="+finale[1]+" and y="+finale[0]);
				int df[]=translate_to_array(finale[1],finale[0]);
				System.out.println("After Recnverting checking left: i="+df[1]+" and j="+df[0]);
				
				return finale;
				}
			
			
			//function to check right boundary
			public int [] check_right_boundary(int x0, int y0, int ang, String which_angle, int width) {
				System.out.println("RIGHT CH");
				//System.out.println("Before checking our right x="+x0+" and y= "+y0);
				int arr[]=translate_to_array(x0,y0);
				//this returns [i][j]
				int i=arr[1]; //i handles y-axis
				int j=arr[0]; //j handles x-axis
				int finale[] = {squareYLocation,squareXLocation };
				//int corner3x=iX(-rWidth/4);
				//Boolean use_x=true;
				
				//Shape1
				if(type_of_shape==0) {
					System.out.println("CO");
					if(ang==1) {
						System.out.println("CO: 1");
						if(j>=6) {
							System.out.println("Going right so stop");
							j=6;
							//finale[1]=corner3x+j*width;
							//use_x=false;
						}
						else {
							System.out.println("gya");
							
							j=j+1;
						}
					}
					else if((ang==2 && which_angle=="cw")||(ang==4 && which_angle=="ccw")) {
					    if(j>=5) {
					    	j=5;
					    }
					    else {
							j=j+1;
						}
						
					}
					else if(ang==3) {
						if(j>=4) {
							j=4;
						}
						else {
							j=j+1;
						}
					}
					else {
						if(j>=6) {
							j=6;
						}
						else {
							j=j+1;
						}
					}
				}
				
				//Shape 2
				if(type_of_shape==1) {
					System.out.println("SO");
					if(ang==1) {
						System.out.println("CO: 1");
						if(j>=4) {
							j=4;
						}
						else {
							j=j+1;
						}
					}
					else if((ang==2 && which_angle=="cw")||(ang==4 && which_angle=="ccw")) {
						if(j>=6) {
							//"O"//
							j=5;
						}
						else {
							j=j+1;
						}
						
					}
					else if(ang==3) {
						if(j>=4) {
							j=4;
						}
						else {
							j=j+1;
						}
					}
					else {
						if(j>=5) {
					    	j=5;
					    }
					    else {
							j=j+1;
						}
					}
				}
				
				//Shape:3
				if(type_of_shape==2) {
					if(ang==1) {
						if(j>=4) {
							//"O"//
							j=4;
						}
						else {
							j=j+1;
						}
					}
					else if((ang==2 && which_angle=="cw")||(ang==4 && which_angle=="ccw")) {
						if(j>=5) {
							j=5;
						}
						else {
							j=j+1;
						}
						
					}
					else if(ang==3) {
						if(j>=6) {
							j=6;
						}
						else {
							j=j+1;
						}
					}
					else {
						if(j>=5) {
							j=5;
						}
						else {
							j=j+1;
						}
					}
				}
               
				//Shape:4
				if(type_of_shape==3) {
					if(ang==1) {
						if(j>=6) {
							//"O"//
							j=6;
						}
						else {
							j=j+1;
						}
					}
					else if((ang==2 && which_angle=="cw")||(ang==4 && which_angle=="ccw")) {
						if(j>=6) {
							j=6;
						}
						else {
							j=j+1;
						}
						
					}
					else if(ang==3) {
						if(j>=4) {
							j=4;
						}
						else {
							j=j+1;
						}
					}
					else {
						if(j>=6) {
							j=6;
						}
						else {
							j=j+1;
						}
					}
				}
               
				//Shape:5
				if(type_of_shape==4) {
					if(ang==1) {
						if(j>=6) {
							j=6;
						}
						else {
							j=j+1;
						}
					}
					else if((ang==2 && which_angle=="cw")||(ang==4 && which_angle=="ccw")) {
						if(j>=5) {
							j=5;
						}
						else {
							j=j+1;
						} 
						
					}
					else if(ang==3) {
						if(j>=5) {
							j=5;
						}
						else {
							j=j+1;
						}
					}
					else {
						if(j>=6) {
							j=6;
						}
						else {
							j=j+1;
						}
					}
				}
				//Shape:6
				if(type_of_shape==5) {
					if(ang==1) {
						if(j>=6) {
							j=6;
						}
						else {
							j=j+1;
						}
					}
					else if((ang==2 && which_angle=="cw")||(ang==4 && which_angle=="ccw")) {
						if(j>=6) {
							j=6;
						}
						else {
							j=j+1;
						} 
						
					}
					else if(ang==3) {
						if(j>=3) {
							j=3;
						}
						else {
							j=j+1;
						}
					}
					else {
						if(j>=6) {
							j=6;
						}
						else {
							j=j+1;
						}
					}
				}
				//Shape:7
				if(type_of_shape==6) {
					if(ang==1) {
						if(j>=6) {
							j=6;
						}
						else {
							j=j+1;
						}
					}
					else if((ang==2 && which_angle=="cw")||(ang==4 && which_angle=="ccw")) {
						if(j>=5) {
							j=5;
						}
						else {
							j=j+1;
						} 
						
					}
					else if(ang==3) {
						//System.out.println("MOMO j="+j);
						if(j>=4) {
							j=4;
						}
						else {
							j=j+1;
						}
					}
					else {
						if(j>=6) {
							j=6;
						}
						else {
							j=j+1;
						}
					}
				}
				//Shape:8
				if(type_of_shape==7) {
					if(ang==1) {
						if(j>=6) {
							j=6;
						}
						else {
							j=j+1;
						}
					}
					else if((ang==2 && which_angle=="cw")||(ang==4 && which_angle=="ccw")) {
						if(j>=6) {
							j=6;
						}
						else {
							j=j+1;
						} 
						
					}
					else if(ang==3) {
						if(j>=6) {
							j=6;
						}
						else {
							j=j+1;
						}
					}
					else {
						if(j>=5) {
							j=5;
						}
						else {
							j=j+1;
						}
					}
				}
				finale=translate_coord(i,j);
				System.out.println("After checking right: i="+i+" and j="+j);
				System.out.println("After checking right: x="+finale[1]+" and y="+finale[0]);
				int df[]=translate_to_array(finale[1],finale[0]);
				System.out.println("After Recnverting checking right: i="+df[1]+" and j="+df[0]);
				
				
				return finale;
				}
			
            //PAINT 
			public void paintComponent(Graphics g) {
				//GAme over check
				if(terminate) {
					g.drawRect(iX(-rWidth/7), iY(rHeight/12),iY(16*rHeight/100),iY(40*rHeight/100));
				    g.drawString("Game Over",iX(-rWidth/16), iY(rHeight/42));
				    JOptionPane.showMessageDialog(this, "Game Over!");
					System.exit(0);
				}
				
				if (do_initialize) {
				initgr();
				}
				
				super.paintComponent(g);
				
	        	System.out.println("painting");
	        	
	        	
	        	slider.setBounds(iX(rWidth/3), iY(0),iY(rHeight/6),iY(22*rHeight/100));
			    slider.setMinorTickSpacing(1);
			    slider.setPaintLabels(true);
			    // Set the labels to be painted on the slider
		        slider.setPaintLabels(true);
		        
		        // Add positions label in the slider
		        Hashtable<Integer, JLabel> position = new Hashtable<Integer, JLabel>();
		        position.put(0, new JLabel("0"));
			    position.put(1, new JLabel("1"));
			    position.put(2, new JLabel("2"));
			    position.put(3, new JLabel("3"));
			    position.put(4, new JLabel("4"));
			    position.put(5, new JLabel("5"));
			    position.put(6, new JLabel("6"));
			    position.put(7, new JLabel("7"));
			    position.put(8, new JLabel("8"));
			    position.put(9, new JLabel("9"));
			    position.put(10, new JLabel("10"));
			     
		         
		        // Setting slider for M
		        slider.setLabelTable(position);
		        
		        //Setting spinner for N
		        spinner.setBounds(iX(4*rWidth/10), iY(-25*rHeight/100),50,30);    
		    	
		        //
		        //for_S.setText("Type S");
		        for_S.setBounds(iX(3*rWidth/10), iY(-39*rHeight/100),200,40);
		       
		       
				
	        	
		        
	        	//sqr_size=iY(15*rHeight/35);
				 
				//g.drawRect(iX(-rWidth / 4), iY(rHeight/2),  iX(-29*rWidth/100), iY(-rHeight/2));
					
				 // g.setColor(Color.blue);
				 //making a big rectangular area for game
	             g.drawLine(iX(-rWidth / 4), iY(rHeight/2), iX(-rWidth / 4), iY(-rHeight/2) );
				 g.drawLine(iX(-rWidth / 4), iY(rHeight/2), iX(rWidth / 4), iY(rHeight/2) );
				 g.drawLine(iX(rWidth / 4), iY(rHeight/2), iX(rWidth / 4), iY(-rHeight/2) );
				 g.drawLine(iX(-rWidth / 4), iY(-rHeight/2), iX(rWidth / 4), iY(-rHeight/2) );
	             
				 

			    //FONT AND LINES
				g.setFont(new Font("default", Font.BOLD, 12));
				//text 1: LEVEL :1
				g.drawString("Level :           "+Level, iX(rWidth/3), iY(16*rHeight/100));

				//text 2: Lines :0
				g.drawString("Lines :           "+removed,iX(rWidth/3), iY(6*rHeight/100));

				//text 3: SLines: 0
				g.drawString("Scores :          "+Score, iX(rWidth/3), iY(-4*rHeight/100));

				//M
				g.drawString("M: ", iX(3*rWidth/10), iY(-14*rHeight/100));

				//N
				g.drawString("N: ", iX(3*rWidth/10), iY(-29*rHeight/100));
				
				//S
				g.drawString("S: ", iX(3*rWidth/10), iY(-39*rHeight/100));

				//make violet right Z shape
				Color violet= new Color(102, 0 ,153);
				make_right_Z_shape(g, iX(-2*rWidth/5), iY(rHeight/3), iY(15*rHeight/35), violet, "cw",1);
			    
				//make yellow left Z shape
				make_left_Z_shape(g, iX(-8*rWidth/10), iY(rHeight/3), iY(15*rHeight/35), Color.yellow, "cw",1);
					    
				//make blue inverted right l
				make_right_inverted_L_shape(g, iX(-8*rWidth/10), iY(1*rHeight/10), iY(15*rHeight/35), Color.blue, "cw",1);
				
				//make red inverted left l
				make_left_inverted_L_shape(g, iX(-4*rWidth/10), iY(1*rHeight/10), iY(15*rHeight/35), Color.red, "cw", 1);
						
				//make dark green square
				Color dark_green=new Color(0,153,0);
				make_square_shape(g, iX(-8*rWidth/10), iY(-15*rHeight/100), iY(15*rHeight/35), dark_green, "cw",1);
				
				//make raised in middle line shape in orange
				Color deep_orange=new Color(255,102,0);
				make_raised_in_middle_top_shape(g, iX(-4*rWidth/10), iY(-15*rHeight/100), iY(15*rHeight/35), Color.orange, "cw",1);
				
				//make a line shape in light blue
				//Color lightBlue= new Color(0,0,182,155);
				Color lightBlue= new Color(51,204,255);
				make_line_shape(g, iX(-5*rWidth/10), iY(-37*rHeight/100), iY(15*rHeight/35), lightBlue,"cw",1);
				
				
				//CREATING RIGHT RECTANGLE AREA
				//g.drawRect(iX(rWidth/3), iY(rHeight/2), iY(rHeight/6),iY(27*rHeight/100));
				g.drawRect(iX(rWidth/3), iY(rHeight/2), iY(rHeight/6),iY(22*rHeight/100));

				
                
				
				Color c = null;
				int width=sqr_size;
			     if(type_of_shape==0) {
		            	//TYPE:1
		            	//Color violet= new Color(102, 0 ,153);
		            	final_color.add(violet);
		    			//make_right_Z_shape(g,iX(55*rWidth/100), iY(39*rHeight/100),  width, c, "cw", 1);
		            	make_right_Z_shape(g,iX(55*rWidth/100), iY(36*rHeight/100),  width, violet, "cw", 1);
			            
			     }
		            else if(type_of_shape==1) {
		            	//TYPE:2
		            	c=  Color.yellow;
		            	final_color.add(c);
		    			//make_left_Z_shape(g, iX(55*rWidth/100), iY(39*rHeight/100),  width, c, "cw", 1);
		            	make_left_Z_shape(g, iX(40*rWidth/100), iY(36*rHeight/100),  width, c, "cw", 1);
			                
		            }
		            else if(type_of_shape==2) {
		            	//TYPE:3
		            	c= Color.blue;
		            	final_color.add(c);
		    			make_right_inverted_L_shape(g, iX(40*rWidth/100), iY(39*rHeight/100),  width, c, "cw", 1);
			            }
			     
		            else if(type_of_shape==3) {
		            	//TYPE:4
		            	c= Color.red;
		            	final_color.add(c);
		    			//make_left_inverted_L_shape(g, iX(55*rWidth/100), iY(39*rHeight/100),  width, c, "cw", 1);
		            	make_left_inverted_L_shape(g, iX(55*rWidth/100), iY(39*rHeight/100),  width, c, "cw", 1);
			            
		            }
			     
		            else if(type_of_shape==4) {
		            	//TYPE:5
		            	//c= new Color(0,153,0);
		            	final_color.add(dark_green);
		    			//make_square_shape(g, iX(55*rWidth/100), iY(39*rHeight/100),  width, dark_green, "cw", 1);
		            	make_square_shape(g, iX(43*rWidth/100), iY(36*rHeight/100),  width, dark_green, "cw", 1);
			            
		            }
			     
		            else if(type_of_shape==5) {
		            	//TYPE:6
		            	//c= Color.orange;
		            	final_color.add(deep_orange);
		    			make_line_shape(g, iX(58*rWidth/100), iY(39*rHeight/100),  width, deep_orange, "cw", 1);
			            
		            }
		            else if(type_of_shape==6) {
		            	//TYPE:7
		            	//c= new Color(51,204,255);
		            	final_color.add(lightBlue);
		    			make_raised_in_middle_top_shape(g, iX(55*rWidth/100), iY(39*rHeight/100),  width, lightBlue, "cw", 1);
			            
		            }
		            else {
		            	//TYPE:8
		            	c= Color.PINK;
		            	final_color.add(c);
		    			make_raised_in_middle_down_shape(g, iX(55*rWidth/100), iY(40*rHeight/100),  width, c, "cw", 1);
			            
		            }

			    
				
			//make_line_shape(g,iX(55*rWidth/100), iY(39*rHeight/100), iY(15*rHeight/35), lightBlue,"cw",1);
				
				
				int x0=squareXLocation;
				float y0=squareYLocation;
				//int width=sqr_size;//iY(15*rHeight/35);
				
				if(!insideRect) {
					
					if(left) {
					  //if(allow_j_update==true) {
						//System.out.println("val is allow_j_update="+allow_j_update);
						//when left mouse is clicked
						System.out.println("left");
						String which=cw_or_ccw.get(cw_or_ccw.size()-1);
						int ang=angle.get(angle.size()-1);
						x0=x0-width;
						squareXLocation=x0;
						System.out.println("After :x= "+x0+" y="+y0);
						
						int coord[]= {squareXLocation, squareYLocation};
						coord=check_left_boundary(x0, y0, ang, which, width);
						//x0=coord[0];
						//y0=coord[1];
					    y0=coord[0];
						x0=coord[1];
						
						squareXLocation=x0;
						squareYLocation=(int) y0;
						
						
						left=false;
						angle.add(ang);
						cw_or_ccw.add(which);
						
						//final_color.add(deep_orange);
						//make_raised_in_middle_down_shape(g,  x0, (int) y0,  width, deep_orange, which,ang); 
						if(type_of_shape==0) {
			            	//TYPE:1
			            	//c= new Color(102, 0 ,153);
			            	//Color violet= new Color(102, 0 ,153);
			            	final_color.add(violet);
			    			make_right_Z_shape(g,  x0, (int) y0,  width, violet,which, ang);
			            }
			            else if(type_of_shape==1) {
			            	//TYPE:2
			            	c=  Color.yellow;
			            	final_color.add(c);
			    			
			    			
			            	make_left_Z_shape(g, x0, (int) y0,  width, c, which, ang);
				            }
			            else if(type_of_shape==2) {
			            	//TYPE:3
			            	c= Color.blue;
			            	final_color.add(c);
			    			make_right_inverted_L_shape(g, x0, (int) y0,  width, c, which, ang);
				            }
				     
			            else if(type_of_shape==3) {
			            	//TYPE:4
			            	c= Color.red;
			            	final_color.add(c);
			    			make_left_inverted_L_shape(g,x0, (int) y0,  width, c,which, ang);
				            
			            }
				     
			            else if(type_of_shape==4) {
			            	//TYPE:5
			            	//c= new Color(0,153,0);
			            	final_color.add(dark_green);
			    			make_square_shape(g,x0, (int) y0,  width, dark_green,which, ang);
				            
			            }
				     
			            else if(type_of_shape==5) {
			            	//TYPE:6
			            	//c= Color.orange;
			            	final_color.add(deep_orange);
			    			make_line_shape(g, x0, (int) y0,  width, deep_orange, which, ang);
				            
			            }
			            else if(type_of_shape==6) {
			            	//TYPE:7
			            	//c= new Color(51,204,255);
			            	final_color.add(lightBlue);
			    			make_raised_in_middle_top_shape(g, x0, (int) y0, width, lightBlue, which, ang);
				            
			            }
			            else {
			            	//TYPE:8
			            	c= Color.PINK;
			            	final_color.add(c);
			    			make_raised_in_middle_down_shape(g, x0, (int) y0,  width, c,which, ang);
				            
			            }

						//repaint();
						//}
				}
					else if(right) {
					  // if (allow_j_update==true) {
						//when right mouse is clicked
						System.out.println("right");
						String which_angle=cw_or_ccw.get(cw_or_ccw.size()-1);
						int ang=angle.get(angle.size()-1);
						x0=x0+width;
						squareXLocation=x0;
						//System.out.println("After :x= "+x0+" y="+y0);
						int coord[]= {squareXLocation, squareYLocation};
						coord=check_right_boundary(x0, (int)y0, ang, which_angle, width);
						
						x0=coord[1];
						y0=coord[0];
						squareXLocation=x0;
						squareYLocation=(int) y0;
						/*
						System.out.println("Af@");
						System.out.println("Before: x= "+x0+" y="+y0);
						int []coordi=translate_to_array(x0,(int) y0);
						int i=coordi[1];
						int j=coordi[0];
						System.out.println("Sp I: "+i+" Sp: J= "+j);*/
						
						angle.add(ang);
						cw_or_ccw.add(which_angle);
						right=false;
						//final_color.add(deep_orange);
						//make_raised_in_middle_down_shape(g,  x0, (int) y0,  width, deep_orange, which,ang); 
						if(type_of_shape==0) {
			            	//TYPE:1
			            	//c= new Color(102, 0 ,153);
			            	//Color violet= new Color(102, 0 ,153);
			            	final_color.add(violet);
			    			make_right_Z_shape(g,  x0, (int) y0,  width, violet, which_angle, ang);
			            }
			            else if(type_of_shape==1) {
			            	//TYPE:2
			            	c=  Color.yellow;
			            	final_color.add(c);
			    			
			    			
			            	make_left_Z_shape(g, x0, (int) y0,  width, c, which_angle, ang);
				            }
			            else if(type_of_shape==2) {
			            	//TYPE:3
			            	c= Color.blue;
			            	final_color.add(c);
			    			make_right_inverted_L_shape(g, x0, (int) y0,  width, c, which_angle, ang);
				            }
				     
			            else if(type_of_shape==3) {
			            	//TYPE:4
			            	c= Color.red;
			            	final_color.add(c);
			    			make_left_inverted_L_shape(g,x0, (int) y0,  width, c, which_angle, ang);
				            
			            }
				     
			            else if(type_of_shape==4) {
			            	//TYPE:5
			            	//c= new Color(0,153,0);
			            	final_color.add(dark_green);
			    			make_square_shape(g,x0, (int) y0,  width, dark_green, which_angle, ang);
				            
			            }
				     
			            else if(type_of_shape==5) {
			            	//TYPE:6
			            	//c= Color.orange;
			            	final_color.add(deep_orange);
			    			make_line_shape(g, x0, (int) y0,  width, deep_orange, which_angle, ang);
				            
			            }
			            else if(type_of_shape==6) {
			            	//TYPE:7
			            	//c= new Color(51,204,255);
			            	final_color.add(lightBlue);
			    			make_raised_in_middle_top_shape(g, x0, (int) y0, width, lightBlue, which_angle, ang);
				            
			            }
			            else {
			            	//TYPE:8
			            	c= Color.PINK;
			            	final_color.add(c);
			    			make_raised_in_middle_down_shape(g, x0, (int) y0,  width, c, which_angle, ang);
				            
			            }

   					//}
					}
					else if( scroll_up) {
						System.out.println("up");
						//rotate shape clockwise
						x0=squareXLocation;
						y0=squareYLocation;
						cw=cw+1;
						if (cw==5) {
							cw=1;
						}
						
						scroll_up=false;
						//Color c=new Color(51,204,255);
						//final_color.add(c);
						cw_or_ccw.add("cw");
						angle.add(cw);
						//System.out.println("@@@@@CW "+cw);
						//make_line_shape(g,  x0, (int) y0,  width,c, "cw", cw); 
						 if(type_of_shape==0) {
				            	//TYPE:1
				            	//c= new Color(102, 0 ,153);
				            	//Color violet= new Color(102, 0 ,153);
				            	final_color.add(violet);
				    			make_right_Z_shape(g,  x0, (int) y0,  width, violet,"cw", cw);
				            }
				            else if(type_of_shape==1) {
				            	//TYPE:2
				            	c=  Color.yellow;
				            	final_color.add(c);
				    			
				    			
				            	make_left_Z_shape(g, x0, (int) y0,  width, c, "cw", cw);
					            }
				            else if(type_of_shape==2) {
				            	//TYPE:3
				            	c= Color.blue;
				            	final_color.add(c);
				    			make_right_inverted_L_shape(g, x0, (int) y0,  width, c,"cw", cw);
					            }
					     
				            else if(type_of_shape==3) {
				            	//TYPE:4
				            	c= Color.red;
				            	final_color.add(c);
				    			make_left_inverted_L_shape(g,x0, (int) y0,  width, c,"cw", cw);
					            
				            }
					     
				            else if(type_of_shape==4) {
				            	//TYPE:5
				            	//c= new Color(0,153,0);
				            	final_color.add(dark_green);
				    			make_square_shape(g,x0, (int) y0,  width, dark_green,"cw", cw);
					            
				            }
					     
				            else if(type_of_shape==5) {
				            	//TYPE:6
				            	//c= Color.orange;
				            	final_color.add(deep_orange);
				    			make_line_shape(g, x0, (int) y0,  width, deep_orange,"cw", cw);
					            
				            }
				            else if(type_of_shape==6) {
				            	//TYPE:7
				            	//c= new Color(51,204,255);
				            	final_color.add(lightBlue);
				    			make_raised_in_middle_top_shape(g, x0, (int) y0, width, lightBlue, "cw", cw);
					            
				            }
				            else {
				            	//TYPE:8
				            	c= Color.PINK;
				            	final_color.add(c);
				    			make_raised_in_middle_down_shape(g, x0, (int) y0,  width, c,"cw", cw);
					            
				            }

					}
					else if( scroll_down) {
						System.out.println("down");
						//rotate shape anti-clockwise
						x0=squareXLocation;
						y0=squareYLocation;
						ccw++;
						if (ccw==5) {
							ccw=1;
						}
						scroll_down=false;
						cw_or_ccw.add("ccw");
						angle.add(ccw);
						//final_color.add(deep_orange);
						//make_raised_in_middle_down_shape(g,  x0, (int) y0,  width, deep_orange, "ccw", ccw); 
						 if(type_of_shape==0) {
				            	//TYPE:1
				            	//c= new Color(102, 0 ,153);
				            	//Color violet= new Color(102, 0 ,153);
				            	final_color.add(violet);
				    			make_right_Z_shape(g,  x0, (int) y0,  width, violet, "ccw", ccw);
				            }
				            else if(type_of_shape==1) {
				            	//TYPE:2
				            	c=  Color.yellow;
				            	final_color.add(c);
				    			
				    			
				            	make_left_Z_shape(g, x0, (int) y0,  width, c,  "ccw", ccw);
					            }
				            else if(type_of_shape==2) {
				            	//TYPE:3
				            	c= Color.blue;
				            	final_color.add(c);
				    			make_right_inverted_L_shape(g, x0, (int) y0,  width, c, "ccw", ccw);
					            }
					     
				            else if(type_of_shape==3) {
				            	//TYPE:4
				            	c= Color.red;
				            	final_color.add(c);
				    			make_left_inverted_L_shape(g,x0, (int) y0,  width, c, "ccw", ccw);
					            
				            }
					     
				            else if(type_of_shape==4) {
				            	//TYPE:5
				            	//c= new Color(0,153,0);
				            	final_color.add(dark_green);
				    			make_square_shape(g,x0, (int) y0,  width, dark_green, "ccw", ccw);
					            
				            }
					     
				            else if(type_of_shape==5) {
				            	//TYPE:6
				            	//c= Color.orange;
				            	final_color.add(deep_orange);
				    			make_line_shape(g, x0, (int) y0,  width, deep_orange, "ccw", ccw);
					            
				            }
				            else if(type_of_shape==6) {
				            	//TYPE:7
				            	//c= new Color(51,204,255);
				            	final_color.add(lightBlue);
				    			make_raised_in_middle_top_shape(g, x0, (int) y0, width, lightBlue,  "ccw", ccw);
					            
				            }
				            else {
				            	//TYPE:8
				            	c= Color.PINK;
				            	final_color.add(c);
				    			make_raised_in_middle_down_shape(g, x0, (int) y0,  width, c, "ccw", ccw);
					            
				            }

						
					}
					else{
						x0=squareXLocation;
						y0=squareYLocation;
						String which=cw_or_ccw.get(cw_or_ccw.size()-1);
						int ang=angle.get(angle.size()-1);
						//final_color.add(deep_orange);
						//make_raised_in_middle_down_shape(g,  x0, (int) y0,  width, deep_orange, which,ang); 
						if(type_of_shape==0) {
			            	//TYPE:1
			            	//c= new Color(102, 0 ,153);
			            	//Color violet= new Color(102, 0 ,153);
			            	final_color.add(violet);
			    			make_right_Z_shape(g,  x0, (int) y0,  width, violet,which, ang);
			            }
			            else if(type_of_shape==1) {
			            	//TYPE:2
			            	c=  Color.yellow;
			            	final_color.add(c);
			    			
			    			
			            	make_left_Z_shape(g, x0, (int) y0,  width, c, which, ang);
				            }
			            else if(type_of_shape==2) {
			            	//TYPE:3
			            	c= Color.blue;
			            	final_color.add(c);
			    			make_right_inverted_L_shape(g, x0, (int) y0,  width, c, which, ang);
				            }
				     
			            else if(type_of_shape==3) {
			            	//TYPE:4
			            	c= Color.red;
			            	final_color.add(c);
			    			make_left_inverted_L_shape(g,x0, (int) y0,  width, c,which, ang);
				            
			            }
				     
			            else if(type_of_shape==4) {
			            	//TYPE:5
			            	//c= new Color(0,153,0);
			            	final_color.add(dark_green);
			    			make_square_shape(g,x0, (int) y0,  width, dark_green,which, ang);
				            
			            }
				     
			            else if(type_of_shape==5) {
			            	//TYPE:6
			            	//c= Color.orange;
			            	final_color.add(deep_orange);
			    			make_line_shape(g, x0, (int) y0,  width, deep_orange, which, ang);
				            
			            }
			            else if(type_of_shape==6) {
			            	//TYPE:7
			            	//c= new Color(51,204,255);
			            	final_color.add(lightBlue);
			    			make_raised_in_middle_top_shape(g, x0, (int) y0, width, lightBlue, which, ang);
				            
			            }
			            else {
			            	//TYPE:8
			            	c= Color.PINK;
			            	final_color.add(c);
			    			make_raised_in_middle_down_shape(g, x0, (int) y0,  width, c,which, ang);
				            
			            }

						
					}
			 		   
				}	
		       
				//PAUSE  BUTTON APPEARS
			    g.setColor(Color.black);
			    if (insideRect) {
			    	
						
			    	g.drawRect(iX(-rWidth/7), iY(rHeight/12),iY(16*rHeight/100),iY(40*rHeight/100));
				    g.drawString("PAUSE",iX(-rWidth/16), iY(rHeight/42));
			        //here update score
				    Score=Score-Level*M;
				    //change shape
				    type_of_shape = (int) (Math.random()* 8);
				    repaint();
			    } 
			    
			    
			 
			  //Upgrading Level
			  if(removed==N) {
				  //Upgrade to next level
				  Level+=1;
				  JOptionPane.showMessageDialog(this, "Level Upgraded!");
					
				  //set removed back to 0
				  removed=0;
				  //Update falling speed
				  FS=FS+(1+Level*S);
			  }
			    
			  
			  update(cw_or_ccw.get(cw_or_ccw.size()-1),angle.get(angle.size()-1));
		          
		      
			    
		      //draw_result(g, width);
			  draw_array(g);
			// Add the slider to the panel
		      
		        
		      this.setVisible(true);
				
			
			}
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

				//System.out.println("******* KEY1 HEY******");
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			}

public class f15j {
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
	    //window.setSize(windowWidth, windowWidth + 22);
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setTitle("Tetris Game");
	    window.setBounds(30, 30, 700, 500);
	    window.setVisible(true);
	    //window.setLocationRelativeTo(null);
        
	    window.getContentPane().add(new MyShapZ());
	    
	    
	    MyShapZ game = new MyShapZ();
	    game.setOpaque(false);
	    game.start();
	    //updates square position, repaints square, and slows down update and paint speed.
        while (game.gameRunning) {
        	System.out.println("Running the game");
            game.update("cw",1);
            //game.repaint();
            try {
				Thread.sleep(10000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
	     window.add(game);
	     window.setVisible(true);

        
	    
	  }

}









