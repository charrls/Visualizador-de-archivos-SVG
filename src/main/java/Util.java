import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.io.*;

public class Util {

    public static void saveDocument(Document doc, String fileName) {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(doc);

            FileWriter writer = new FileWriter( new File(fileName) );
            StreamResult result = new StreamResult(writer);
            transformer.transform( source, result );
        } catch (TransformerConfigurationException | IOException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }


    public static class NewCircle extends JDialog {

        private JButton buttonOK;
        private JButton buttonCancel;
        private JTextField xField1;
        private JTextField yField1;
        private JTextField wField;
        private JTextField hField;
        private JPanel controlsPanel;
        private JLabel x1Label;
        private JLabel y1Label;
        private JLabel wLabel;
        private JLabel hLabel;

        private Canvas dibujo;

        public NewCircle(JFrame parent, Canvas canvas) {
            super(parent,"New circle",true);

            dibujo = canvas;

            GridLayout layout = new GridLayout(0,2);

            controlsPanel = new JPanel(layout);

            x1Label = new JLabel("X:");
            x1Label.setHorizontalAlignment(SwingConstants.CENTER);
            controlsPanel.add(x1Label);
            xField1=new JTextField(4);
            controlsPanel.add(xField1);

            y1Label = new JLabel("Y:");
            y1Label.setHorizontalAlignment(SwingConstants.CENTER);
            controlsPanel.add(y1Label);
            yField1=new JTextField(4);
            controlsPanel.add(yField1);

            wLabel = new JLabel("Ancho:");
            wLabel.setHorizontalAlignment(SwingConstants.CENTER);
            controlsPanel.add(wLabel);
            wField =new JTextField(4);
            controlsPanel.add(wField);

            hLabel = new JLabel("Alto:");
            hLabel.setHorizontalAlignment(SwingConstants.CENTER);
            controlsPanel.add(hLabel);
            hField =new JTextField(4);
            controlsPanel.add(hField);

            buttonOK = new JButton("Ok");
            buttonOK.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    onOK();
                }
            });
            controlsPanel.add(buttonOK);

            buttonCancel= new JButton("Cancel");
            buttonCancel.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    onCancel();
                }
            });
            controlsPanel.add(buttonCancel);
            // call onCancel() when cross is clicked
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    onCancel();
                }
            });

            this.setContentPane(controlsPanel);

            setSize(200,400);
            setResizable(false);
            pack();

        }

        private void onOK() {
            // add your code here
            double x = Double.parseDouble( xField1.getText() );
            double y = Double.parseDouble( yField1.getText() );

            double w = Double.parseDouble( wField.getText() );
            double h = Double.parseDouble( hField.getText() );

            Ellipse2D.Double elipse = new Ellipse2D.Double(x,y,w,h);
            dibujo.add( elipse );
            dibujo.repaint();
            dispose();
        }

        private void onCancel() {
            // add your code here if necessary
            dispose();
        }
    }



    public static class NewLine extends JDialog {

        private JButton buttonOK;
        private JButton buttonCancel;
        private JTextField xField1;
        private JTextField yField1;
        private JTextField xField2;
        private JTextField yField2;
        private JPanel controlsPanel;
        private JLabel x1Label;
        private JLabel y1Label;
        private JLabel x2Label;
        private JLabel y2Label;
        private JComboBox<String> colorCombo;
        private JComboBox<String> wCombo;
        private Canvas dibujo;






        public NewLine(JFrame parent, Canvas canvas) {
            super(parent,"New line",true);

            dibujo = canvas;

            GridLayout layout = new GridLayout(0,2);

            controlsPanel = new JPanel(layout);

            x1Label = new JLabel("X1:");
            x1Label.setHorizontalAlignment(SwingConstants.CENTER);
            controlsPanel.add(x1Label);
            xField1=new JTextField(4);
            controlsPanel.add(xField1);

            y1Label = new JLabel("Y1:");
            y1Label.setHorizontalAlignment(SwingConstants.CENTER);
            controlsPanel.add(y1Label);
            yField1=new JTextField(4);
            controlsPanel.add(yField1);

            x2Label = new JLabel("X2:");
            x2Label.setHorizontalAlignment(SwingConstants.CENTER);
            controlsPanel.add(x2Label);
            xField2=new JTextField(4);
            controlsPanel.add(xField2);

            y2Label = new JLabel("Y2:");
            y2Label.setHorizontalAlignment(SwingConstants.CENTER);
            controlsPanel.add(y2Label);
            yField2=new JTextField(4);
            controlsPanel.add(yField2);

            JLabel coloLabel = new JLabel("Color:");
            coloLabel.setHorizontalAlignment(SwingConstants.CENTER);
            controlsPanel.add(coloLabel);

            String colors[] = {"black", "red", "green", "blue", "yellow", "white", "cyan"};
            colorCombo = new JComboBox<>(colors);
            controlsPanel.add(colorCombo);

            JLabel widithLabel = new JLabel("Width:");
            widithLabel.setHorizontalAlignment(SwingConstants.CENTER);
            controlsPanel.add(widithLabel);

            String ws[] = {"1", "2", "3", "4", "5", "6", "7"};
            wCombo = new JComboBox<>(ws);
            controlsPanel.add(wCombo);


            buttonOK = new JButton("Ok");
            buttonOK.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    onOK();
                }
            });
            controlsPanel.add(buttonOK);

            buttonCancel= new JButton("Cancel");
            buttonCancel.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    onCancel();
                }
            });
            controlsPanel.add(buttonCancel);
            // call onCancel() when cross is clicked
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    onCancel();
                }
            });

            this.setContentPane(controlsPanel);

            setSize(200,400);
            setResizable(false);
            pack();

        }

        private void onOK() {
            // add your code here

            String sx1 = xField1.getText();
            String sy1 = yField1.getText() ;
            String sx2 = xField2.getText();
            String sy2 = yField2.getText();

            double x1 = Double.parseDouble( sx1 );
            double y1 = Double.parseDouble( sy1 );
            double x2 = Double.parseDouble( sx2 );
            double y2 = Double.parseDouble( sy2 );

            Line2D.Double l = new Line2D.Double(x1,y1,x2,y2);
            dibujo.add( l );

            Element line = dibujo.getSVG().createElement("line");
            line.setAttribute("x1",sx1);
            line.setAttribute("y1",sy1);
            line.setAttribute("x2",sx2);
            line.setAttribute("y2",sy2);
            String color = (String) colorCombo.getSelectedItem();

            line.setAttribute("stroke","black");
            line.setAttribute("stroke-width","2");
            dibujo.addElement(line);
            dibujo.repaint();

            try {
                prettyPrint(dibujo.getSVG());
            } catch (Exception e) {
                e.printStackTrace();
            }
            dispose();
        }

        private void onCancel() {
            // add your code here if necessary
            dispose();
        }

        public void prettyPrint(Document xml) throws Exception {
            Transformer tf = TransformerFactory.newInstance().newTransformer();
            tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            Writer out = new StringWriter();
            tf.transform(new DOMSource(xml), new StreamResult(out));
            System.out.println(out.toString());
        }

    }



    public static void scaleSVG( Document document, double scaleFactor) {

        Element root = document.getDocumentElement();

        double w = Double.parseDouble( root.getAttribute("width") ) * scaleFactor ;
        root.setAttribute("width",String.valueOf(w));

        double h = Double.parseDouble( root.getAttribute("height")  ) * scaleFactor  ;
        root.setAttribute("height",String.valueOf(h));

        NodeList shapes = root.getChildNodes();

        int len = shapes.getLength();

        for (int index =0; index < len; index++) {
            Node current = shapes.item(index);
            if( current.getNodeType() != Node.ELEMENT_NODE) {
               continue;
            }
            Element shape = (Element) current;

            String shapeName = shape.getNodeName();
            if( shapeName.equals("line") ) {
                double x1 = Double.parseDouble(  shape.getAttribute("x1")  ) * scaleFactor;
                shape.setAttribute("x1", String.valueOf(x1) );

                double y1 = Double.parseDouble(  shape.getAttribute("y1")  ) * scaleFactor;
                shape.setAttribute("y1",String.valueOf(y1));

                double x2 = Double.parseDouble(  shape.getAttribute("x2")  ) * scaleFactor;
                shape.setAttribute("x2",String.valueOf(x2));

                double y2 = Double.parseDouble(  shape.getAttribute("y2")  ) * scaleFactor;
                shape.setAttribute("y2",String.valueOf(y2));
            }

            if( shapeName.equals("circle") ) {
                double x1 = Double.parseDouble(  shape.getAttribute("cx")  ) * scaleFactor;
                shape.setAttribute("cx",String.valueOf(x1));

                double y1 = Double.parseDouble(  shape.getAttribute("cy")  ) * scaleFactor;
                shape.setAttribute("cy",String.valueOf(y1));

                double r = Double.parseDouble(  shape.getAttribute("r")  ) * scaleFactor;
                shape.setAttribute("r",String.valueOf(r));
            }

            if( shapeName.equals("rect") ) {
                double x = Double.parseDouble(  shape.getAttribute("x")  ) * scaleFactor;
                shape.setAttribute("x",String.valueOf(x));

                double y = Double.parseDouble(  shape.getAttribute("y")  ) * scaleFactor;
                shape.setAttribute("y",String.valueOf(y));

                double width = Double.parseDouble( shape.getAttribute("width") ) * scaleFactor ;
                shape.setAttribute("width",String.valueOf(width));

                double height = Double.parseDouble( shape.getAttribute("height")  ) * scaleFactor  ;
                shape.setAttribute("height",String.valueOf(height));
            }
        }

    }

    public static void rotateSVG(Document document, double dg) {
        Element root = document.getDocumentElement();

        int w = Integer.parseInt( root.getAttribute("width") );

        int h = Integer.parseInt( root.getAttribute("height") );


        // Obtener los circulos
        NodeList lines = root.getElementsByTagName("line");

        int len = lines.getLength();

        Point center = new Point(w / 2,h / 2 );

        for (int i = 0; i < len; i++) {
            Element line = (Element) lines.item(i);

            double x1 = Double.parseDouble(line.getAttribute("x1"));
            double y1 = Double.parseDouble(line.getAttribute("y1"));

            Point p1 = new Point();
            p1.setLocation(x1, y1);
            rotate(p1, center, dg);

            line.setAttribute("x1", String.valueOf(p1.getX()));
            line.setAttribute("y1", String.valueOf(p1.getY()));

            double x2 = Double.parseDouble(line.getAttribute("x2"));
            double y2 = Double.parseDouble(line.getAttribute("y2"));

            Point p2 = new Point();
            p2.setLocation(x2, y2);
            rotate(p2, center, dg);

            line.setAttribute("x1", String.valueOf(p1.getX()));
            line.setAttribute("y1", String.valueOf(p1.getY()));

            line.setAttribute("x2", String.valueOf(p2.getX()));
            line.setAttribute("y2", String.valueOf(p2.getY()));

        }

    }

    public static void rotate(Point point, double degrees) {
        double newX = point.getX() * Math.cos(Math.toRadians(degrees)) -
                point.getY() * Math.sin(Math.toRadians(degrees));

        double newY = point.getX() * Math.sin(Math.toRadians(degrees)) +
                point.getY() * Math.cos(Math.toRadians(degrees));

        point.setLocation(newX, newY);
    }

    public static void rotate(Point p,Point c, double degrees) {
        p.x -= c.x;
        p.y -= c.y;

        double newX = p.x * Math.cos( Math.toRadians(degrees) ) -
                p.y * Math.sin( Math.toRadians(degrees) );

        double newY = p.x * Math.sin( Math.toRadians(degrees) ) +
                p.y * Math.cos( Math.toRadians(degrees) );

        p.setLocation(newX+c.x, newY+c.y);
    }


    public double[] matXvect(double m[][], double v[]) {
        int cols = m[0].length;
        double a[] = null;


        if( cols == v.length) {
            a = new double[v.length];
            for(int i=0; i < cols; i++ ) {
                double sum = 0.0;
                for( int j=0; j < cols; j++) {
                    sum = sum + m[i][j] * v[j];
                }
                a[i] = sum;
            }

        }
        return  a;
    }


}
