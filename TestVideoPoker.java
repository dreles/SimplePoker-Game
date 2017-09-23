package PJ4;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;


import PJ4.VideoPoker;

class TestVideoPoker {

    public static void main(String args[]) 
    {
    	
    	
    	 
    	
	VideoPoker pokergame;
	if (args.length > 0)
		pokergame = new VideoPoker(Integer.parseInt(args[0]));
	else
		pokergame = new VideoPoker();
	pokergame.play();
	
    }
}
