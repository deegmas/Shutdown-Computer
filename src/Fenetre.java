import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * 
 * @author Deegmas
 * @version 1.0 2017-04-07
 * Programme permettant d'eteindre l'ordinateur à une heure définie 
 */
public class Fenetre extends JFrame implements ActionListener
{
	
	JButton 	btnValider;
	JComboBox 	listeHeure;
	JComboBox 	listeMinute;
	JLabel		labelHeure;
	JLabel		labelMinute;
	
	Object heures[]  = new Object[] {"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
	
	Object minutes[] = new Object[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", 
									"20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", 
									"40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
	
	public Fenetre()
	{
		this.setTitle("Eteindre l'ordinateur");
		this.setSize(400,200);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		JPanel pan 	= new JPanel();
		pan.setLayout(new GridLayout(5, 1));
		labelHeure 	= new JLabel("Choisissez une heure :");
		listeHeure 	= new JComboBox(heures);
		labelMinute = new JLabel("Choisissez les minutes");
		listeMinute = new JComboBox(minutes);
		btnValider 	= new JButton("Valider");
		pan.add(labelHeure);
		pan.add(listeHeure);
		pan.add(labelMinute);
		pan.add(listeMinute);
		pan.add(btnValider);
		
		btnValider.addActionListener(this);
		
		add(pan);
		
		this.setVisible(true);
		
		
		/*while(true)
		{
			gestionDuTemps();
		}*/
	}
	
	/**
	 *  methode pour lancer la commande 
	 * @return le nom de la commande a executer
	 */
	private String getCommande() {
		return "shutdown -f -s";
	}
	
	/*
	 *  methode de la gestion du temps 
	 *  @return l'heure actuel du système
	 */
	public String gestionDuTemps()
	{
		Format formater = null;
		while(true)
		{
			try 
			{
				Thread.sleep(1000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			Date date = new Date();
			
			formater = new SimpleDateFormat("HH:mm"); // (HH:mm) => (20:10)
			
			String s = formater.format(date);
			
			return s;
		}
	}

	/**
	 * Gestion des evenements au click du bouton valider
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == btnValider)
		{
			int heure = Integer.parseInt(listeHeure.getSelectedItem().toString());
			int minute = Integer.parseInt(listeMinute.getSelectedItem().toString());
			
			
			boolean vrai = true;
			while(vrai) 
			{
				String temps = gestionDuTemps().toString();
				
				int h = Integer.parseInt(temps.toString().substring(0, 2));
				int m = Integer.parseInt(temps.toString().substring(3,5));
				
				if (heure == h && minute == m)
				{
					
					System.out.println("Arret de la machine en cours");
					vrai = false;

					Runtime r = Runtime.getRuntime();
					try 
					{
						r.exec(getCommande());
					} catch (IOException ioe) {
						ioe.printStackTrace();
					}
				}
			}		
		}
	}
	

	public static void main(String[] args) 
	{
		new Fenetre();
	}
}