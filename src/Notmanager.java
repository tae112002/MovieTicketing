import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Notmanager extends JPanel{
	JImagePanel pnl;
	public Notmanager() {
		this.setLayout(new BorderLayout());
		
		pnl = new JImagePanel(new ImageIcon("./img/background.png"));
		pnl.setLayout(null);
		
		this.add(pnl);
	}

}
