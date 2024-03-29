import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class HtmlRead {
    JFrame frame;
    int height = 500;
    int width = 800;
    int w = 50;
    int h = 50;
    JTextArea input;
    JTextField end;
    JTextArea output;
    JLabel topLabel;
    JLabel midLabel;
    JLabel bottomLabel;
    JButton submit;
    JScrollPane scroll;
    public int maxDepth;
    public ArrayList<String> path = new ArrayList<>();
    public ArrayList<String> saveOut = new ArrayList<>();
    public int k;


    public static void main(String[] args) {
        HtmlRead html = new HtmlRead();

        //WikiGame w = new WikiGame();
    }

    public HtmlRead() {
        frame = new JFrame("wiki_game");
        frame.setLayout(new GridLayout(7, 1));
        frame.setSize(width, height);
        frame.setVisible(true);
        topLabel = new JLabel("input URL");
        input = new JTextArea();
        input.setSize(w, h);
        midLabel = new JLabel("end page");
        end = new JTextField();
        bottomLabel = new JLabel("path");
        output = new JTextArea();
        output.setSize(w, h);
        submit = new JButton("search");
        submit.setActionCommand("search");
        submit.addActionListener(new ButtonClickListener());
        scroll = new JScrollPane(output);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        frame.add(topLabel);
        frame.add(input);
        frame.add(midLabel);
        frame.add(end);
        frame.add(submit);
        frame.add(bottomLabel);
        frame.add(scroll);
        frame.setVisible(true);


    }

    public void parcelink(String sLink1) {
        try {
            URL url = new URL(sLink1);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream())
            );
            String sLink;

            //System.out.println(sLink1);
            while ((sLink = reader.readLine()) != null) {
                System.out.println(sLink);

                while (sLink.contains("href")) {
                    //&& sLink.contains(end.getText()))


                    System.out.println(sLink);
                    int start = sLink.indexOf("href=") + 6;
                    sLink = sLink.substring(start);
                    System.out.println("og " + sLink);
                    int end= start;
                    int n = -1;
                    int end1 = sLink.indexOf("\"");
                    int end2 = sLink.indexOf("\'");
                    System.out.println("end1 \": " + end1 + " end 2 \':  " + end2);
                    System.out.println("\n" + sLink);

                    String link = "";

                    if (!(end1 == n) && !(end2 == n)) {

                        if (end1 < end2) {
                            end = end1;
                            link = sLink.substring(0, end);
//                            System.out.println(link);
//                            System.out.println("\n" +link);
//                            output.setText(output.getText()+"\n"+link);
                        } else {
                            end = end2;
                            link = sLink.substring(0, end);
//                        System.out.println(link);
//                        System.out.println(("\n" +link));
//                        output.append("\n" +link);
                        }
                    }

                    if (end1 == n) {
                        end = end2;
                        link = sLink.substring(0, end);
//                        System.out.println(link);
//                        output.append("\n"+link);
//                        System.out.println("\n" +link);
                    }
                    if (end2 == n) {
                        end = end1;
                        link = sLink.substring(0, end);
                        // System.out.println(link);
                        //output.append("\n"+link);
                        // System.out.println("\n" +link);
                    }
                    if (link.contains("/wiki/")) {
                        link = sLink.substring(0,end);
                        System.out.println(link);
                        saveOut.add(link);
                        output.append("\n" + (saveOut));

                        if (!saveOut.contains(link)) {
                        output.append("\n" + link);
                        System.out.println("\n" + link);
                    }
                    }
                    //does it contain the search term?

                    //redefine slink
                   // sLink=link;
                }


                // if (line.indexOf("x", 50) != -1) {
                //System.out.println(line);
                // }
            }
            reader.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }


    public void WikiGame() {

        String startLink = "https://en.wikipedia.org/wiki/Helianthus";  // beginning link, where the program will start
        startLink = "https://en.wikipedia.org/wiki/The_Untamed_(TV_series)";
        String endLink = "https://en.wikipedia.org/wiki/Common_sunflower";    // ending link, where the program is trying to get to
        maxDepth = 2;           // start this at 1 or 2, and if you get it going fast, increase


        if (findLink(startLink, endLink, 0)) {

            System.out.println("found it********************************************************************");


            path.add(startLink);
        } else {
            System.out.println("did not find it********************************************************************");
        }

    }

    // recursion method
    public boolean findLink(String startLink, String endLink, int depth) {

        System.out.println("depth is: " + depth + ", link is: https://en.wikipedia.org" + startLink);

        // BASE CASE (search for relevant links)
        if (depth > maxDepth) {
            System.out.println("max depth reached");
            return false;
        }
        //(if you find the term that you are looking for)
        else if (startLink.equals(endLink)) {
            System.out.println("end page found");
            return true;
        }
        // GENERAL RECURSIVE CASE (if you hit the max depth)
        else {

            //findLink(startLink, endLink, depth+1);
            parcelink(startLink);
            System.out.println("additional links");
            System.out.println(path);
            return false;
        }


    }


    class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("search")) {
                WikiGame();
            }

        }
    }
}
