package tec.triviaid.proyectoaitrivia.GraphGeneration;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.mcavallo.opencloud.Cloud;
import org.mcavallo.opencloud.Tag;


import java.awt.Color;
import java.util.Random;
import java.util.List;

public class WordCloudGenerator {

    public void initUI(List<String> words) {
        JFrame frame = new JFrame("Word Cloud");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        Cloud cloud = new Cloud();
        Random random = new Random();
        for (int x=0; x<words.size(); x++) {
            for (int i = random.nextInt(50); i > 0; i--) {
                cloud.addTag(words.get(x));
            }
        }
        for (Tag tag : cloud.tags()) {
            final JLabel label = new JLabel(tag.getName());
            label.setOpaque(false);
            label.setForeground(getRandomColor(random));
            label.setFont(label.getFont().deriveFont((float) tag.getWeight() * 10));
            panel.add(label);
        }
        frame.add(panel);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    private Color getRandomColor(Random random) {
        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }
}
