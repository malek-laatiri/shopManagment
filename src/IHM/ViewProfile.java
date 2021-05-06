/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Entity.User;
import java.awt.BorderLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author malek
 */
public class ViewProfile extends JPanel {

    private final JPanel panoImg;
    private final JPanel panoData;

    public ViewProfile(User user) {
        this.setLayout(new BorderLayout());
        /**
         * *******LEFT********
         */
        panoImg = new JPanel();
        panoImg.setLayout(new BorderLayout());
        ImageIcon img = new ImageIcon(System.getProperty("user.dir") + "/src/images/" + user.getUser_img());
        panoImg.add(new JLabel(img), BorderLayout.CENTER);

        /**
         * *******RIGHT********
         */
        panoData = new JPanel();
        panoData.setLayout(new BorderLayout());

        this.add(panoImg, BorderLayout.WEST);
                this.add(panoData, BorderLayout.EAST);

    }

}
