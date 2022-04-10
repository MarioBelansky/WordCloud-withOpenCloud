import java.awt.*;
import java.io.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.mcavallo.opencloud.Cloud;
import org.mcavallo.opencloud.Tag;



public class TestOpenCloud {

private static final String[] WORDS = {"art","art","art", "fun", "fun","tokyo","usa","hola","hola","hola","hola","hola","festival", "flower", "flowers", "food"
    ,"sunset", "taiwan","sunset", "taiwan","nikon", "nyc"};

    //file into array
    String keywordsFile = "output3.txt";
    String[] keywords;

    {
        try {
            keywords = FileToArrayReader.readLines(keywordsFile);
        } catch (IOException e) {e.printStackTrace();}
    }

    protected void initUI(int TagsToDisplay,boolean CategoryColor) {
        JFrame frame = new JFrame(TestOpenCloud.class.getSimpleName());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        Cloud cloud = new Cloud();

        cloud.setMaxTagsToDisplay(TagsToDisplay);

        for (int i = 0; i < keywords.length; i++) {

                cloud.addTag(keywords[i]);

            }

        for (Tag tag : cloud.tags()) {

            final JLabel label = new JLabel(tag.getName());
            label.setOpaque(false);

            //scale
            label.setFont(label.getFont().deriveFont((float) tag.getWeight() *10+20));

            //random color
            float r = (float) Math.random();
            int RGB = Color.HSBtoRGB(r, 0.5f, 0.5f);
            Color color = new Color(RGB);
            label.setForeground(color);

            //color by scale
            if(CategoryColor==true){
                if (tag.getScore() >= 100) {
                    label.setForeground(Color.YELLOW);
                }
                if (tag.getScore() >= 50 && tag.getScore() < 100) {
                    label.setForeground(Color.RED);
                }
                if (tag.getScore() >= 20 && tag.getScore() < 50) {
                    label.setForeground(Color.BLUE);
                }
                if (tag.getScore() < 20 ) {
                    label.setForeground(Color.BLACK);
                }
            }


            //output
            panel.add(label);
        }
        frame.add(panel);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }






    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                new editFile().edit("Alice.txt","output1.txt"); //first step to remove stop words and remove unwanted characters

                new RemoveStopWords().removeStopWord("output2.txt"); //remove stop words

                new editFile().edit("output2.txt","output3.txt");

                new TestOpenCloud().initUI(100,true);

                try {
                    new ExcelOut().ExcelOut();
                    } catch (Exception e) {e.printStackTrace();}


            }


        });


    }}

