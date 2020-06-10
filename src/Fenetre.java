import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * 
 * @author Thomas DEGENETAIS 
 * @version 1.0 2017-04-07
 * Programme permettant d'eteindre l'ordinateur à une heure donné 
 */
public class Fenetre extends JFrame implements ActionListener
{
	
	JButton btnValider;
	JButton btnEffacer;
	JTextField txt;
	
	public Fenetre()
	{
		this.setTitle("Eteindre l'ordinateur");
		this.setSize(600,600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel pan = new JPanel();
		pan.setLayout(new GridLayout(3, 1));
		txt = new JTextField();
		btnValider = new JButton("Valider");
		btnEffacer = new JButton("Effacer");
		pan.add(txt);
		pan.add(btnValider);
		pan.add(btnEffacer);
		
		btnValider.addActionListener(this);
		btnEffacer.addActionListener(this);
		
		add(pan);
		
		this.setVisible(true);
		
		
		while(true)
		{
			gestionDuTemps(); 
		}
	}
	
	/**
	 *  methode pour lancer la commande 
	 * @return le nom de la commande a executer
	 */
	private String getCommand() {
		return "shutdown /s";
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
			
			/*System.out.println(formater.format(date));
			System.out.println("--------------------");
			System.out.println(s);
			System.out.println("--------------------");*/
			
			return s;
		}
	}

	/**
	 * Gestion des evenements au clics des boutons quitter et valider
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == btnEffacer)
		{
			txt.setText("");
		}
		if (e.getSource() == btnValider)
		{
			int heure = Integer.parseInt(txt.getText().toString().substring(0,2));
			//System.out.println(heure);
			int minutes = Integer.parseInt(txt.getText().toString().substring(2,4));
			//System.out.println(minutes);
			
			
			boolean vrai = true;
			while(vrai) 
			{
				String temps = gestionDuTemps().toString();
				//System.out.println(temps);
				
				int h = Integer.parseInt(temps.toString().substring(0, 2));
				int m = Integer.parseInt(temps.toString().substring(3,5));
				//System.out.println(h + " minutes " + m);
				
				if (heure == h && minutes == m)
				{
					
					System.out.println("Arret de la machine en cours");
					vrai = false;
					//System.exit(0);

					Runtime r = Runtime.getRuntime();
					try 
					{
						r.exec(getCommand());
					} catch (IOException ioe) {
						ioe.printStackTrace();
					}
				}
			}			
		}
	}
	
	/**
	 * Main du programme 
	 */
	public static void main(String[] args) 
	{
		new Fenetre();
	}
}