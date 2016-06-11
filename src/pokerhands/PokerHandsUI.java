package pokerhands;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class PokerHandsUI extends JFrame {

	private JPanel hand1Panel;     
	private JLabel card1Label;
	private JLabel card2Label;
	private JLabel card3Label;
	private JLabel card4Label;
	private JLabel card5Label;
	private JPanel hand2Panel;        
	private JLabel card6Label;   
	private JLabel card7Label;    
	private JLabel card8Label;   
	private JLabel card9Label;    
	private JLabel card10Label;
	private JPanel resultPanel;
	private JLabel lblResult;
	private JPanel buttonPanel;    
	private JButton btnNewHand;        
	private JButton btnStats;
	int draw = 0, playerOneWins = 0, playerTwoWins = 0;
	
	public PokerHandsUI() {
		  //Set window dimensions and location
		  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		  
		  this.setTitle("Poker Cards!");
		  int width = (int)screenSize.getWidth();
		  int height = (int)screenSize.getHeight();
		  this.setLocation(width / 3, height / 3);
		  this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		  
		  this.addWindowListener( new WindowAdapter() {
		      public void windowClosing(WindowEvent e) {
		          JFrame frame = (JFrame)e.getSource();
		
		          int dialogResult = JOptionPane.showConfirmDialog(frame,
		              "Are you sure you want to exit the application?",
		              "Exit Application",
		              JOptionPane.YES_NO_OPTION);
		
		          if (dialogResult == JOptionPane.YES_OPTION) {
		              frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		          }
		      }
		  });
		  
		  //Set and Build layout
		  this.setLayout(new BorderLayout());
		  buildHandOnePanel();
		  buildHandTwoPanel();
		  buildButtonPanel();
		  buildResultPanel();
		  this.add(hand1Panel,  BorderLayout.NORTH);
		  this.add(hand2Panel,  BorderLayout.SOUTH);
		  this.add(buttonPanel, BorderLayout.EAST);
		  this.add(resultPanel, BorderLayout.WEST);
		  pack();
		  this.setSize(560, 310);
		  this.setVisible(true);
	}

	private void buildHandOnePanel() {
		hand1Panel = new JPanel();
        card1Label = new JLabel();
        card1Label.setIcon(new ImageIcon("images/back.png"));
        card2Label = new JLabel();
        card2Label.setIcon(new ImageIcon("images/back.png"));
        card3Label = new JLabel();
        card3Label.setIcon(new ImageIcon("images/back.png"));
        card4Label = new JLabel();
        card4Label.setIcon(new ImageIcon("images/back.png"));
        card5Label = new JLabel();
        card5Label.setIcon(new ImageIcon("images/back.png"));
        hand1Panel.add(card1Label);
        hand1Panel.add(card2Label);
        hand1Panel.add(card3Label);
        hand1Panel.add(card4Label);
        hand1Panel.add(card5Label);
    }
	
	private void buildHandTwoPanel() {
		hand2Panel = new JPanel();
        card6Label = new JLabel();
        card6Label.setIcon(new ImageIcon("back.png"));
        card7Label = new JLabel();
        card7Label.setIcon(new ImageIcon("back.png"));
        card8Label = new JLabel();
        card8Label.setIcon(new ImageIcon("back.png"));
        card9Label = new JLabel();
        card9Label.setIcon(new ImageIcon("back.png"));
        card10Label = new JLabel();
        card10Label.setIcon(new ImageIcon("back.png"));
        hand2Panel.add(card6Label);
        hand2Panel.add(card7Label);
        hand2Panel.add(card8Label);
        hand2Panel.add(card9Label);
        hand2Panel.add(card10Label);
    }
	
    private void buildButtonPanel() {
       buttonPanel = new JPanel();
       btnNewHand = new JButton("Get a new Hand!");
       btnNewHand.addActionListener(new btnNewHandListener());
       buttonPanel.add(btnNewHand);
       btnStats = new JButton("Get Stats");
       btnStats.addActionListener(new btnStatsListener());
       buttonPanel.add(btnStats);
    }
    
    private void buildResultPanel() {
    	resultPanel = new JPanel();
    	lblResult = new JLabel("Try it out!");
        lblResult.setFont(new Font("Serif", Font.BOLD, 14));
        resultPanel.add(lblResult);
    }

    private class btnNewHandListener implements ActionListener {
       public void actionPerformed(ActionEvent e) {
    	Card[] hand1 = new Card[5];
    	Card[] hand2 = new Card[5];
    	
    	JLabel[] hand_ONE = {card1Label, card2Label, card3Label, card4Label, card5Label };
    	JLabel[] hand_TWO = {card6Label, card7Label, card8Label, card9Label, card10Label };
		
		Deck deck = new Deck();
		
		Hand handOne = new Hand(deck);
		Hand handTwo = new Hand(deck);
		
		hand1 = handOne.getCards();
		hand2= handTwo.getCards();
		
		for (int i = 0; i < 5; i++) {
			hand_ONE[i].setIcon(new ImageIcon(hand1[i].getImageValue()));
			hand_TWO[i].setIcon(new ImageIcon(hand2[i].getImageValue()));
		}
			
		String handOneResult = handOne.display();
		String handTwoResult = handTwo.display();
		
		String outcome = handOneResult + " vs " + handTwoResult + "\n";
			
		int result = handOne.compareTo(handTwo);
		
		if (result == 0) {
			lblResult.setText(outcome + ". This one is a draw!");
			draw++;
		}
		else if (result == 1) {
			lblResult.setText(outcome + ". Player 1 is the winner!");
			playerOneWins++;
		}
		else if (result == -1) {
			lblResult.setText(outcome + ". Player 2 is the winner!");
			playerTwoWins++;
		}
      }
    }
    
    public class btnStatsListener implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    		JOptionPane.showMessageDialog(null, "Player 1 has won " + playerOneWins + " times!\n" +
		             "Player 2 has won " + playerTwoWins + " times!\n" +
		             "There have been " + draw + " draws!", 
		             "Game Statistics", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    //application entry point
    public static void main(String[] args) {
       new PokerHandsUI();
    }

}
