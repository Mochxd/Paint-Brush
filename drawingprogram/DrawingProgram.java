package drawingprogram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import java.net.URL;

// Main class representing the drawing program
public class DrawingProgram extends JFrame {

    private final JCheckBox dottedCheckbox;
    private final JCheckBox filledCheckbox;
    private final DrawingPanel drawingPanel;
    private boolean dotted = false;
    private boolean filled = false;

    // Constructor for the DrawingProgram class
    public DrawingProgram() {
        
        // Set the title and size of the main window
        setTitle("Paint Program");
        setSize(1400, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create a DrawingPanel and add it to the main window
        drawingPanel = new DrawingPanel();
        add(drawingPanel, BorderLayout.CENTER);

        // Create a control panel for buttons and add it to the main window
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout()); // to organize control panel from left to right
        add(controlPanel, BorderLayout.PAGE_START);

        // Create buttons for various actions
        JButton lineButton = createIconButton("line.png", new LineButtonListener());
        lineButton.setToolTipText("Line");
        JButton rectangleButton = createIconButton("Rec.png", new RectangleButtonListener());
        rectangleButton.setToolTipText("Rectangle");
        JButton ovalButton = createIconButton("Oval.png", new OvalButtonListener());
        ovalButton.setToolTipText("Oval");
        JButton freehandButton = createIconButton("Pen.png", new FreehandButtonListener());
        freehandButton.setToolTipText("FreeHand");
        JButton redButton = new JButton();
        redButton.setToolTipText("Red");
        redButton.setPreferredSize(new Dimension(50, 35));
        JButton greenButton = new JButton();
        greenButton.setToolTipText("Green");
        greenButton.setPreferredSize(new Dimension(50, 35));
        JButton blueButton = new JButton();
        blueButton.setToolTipText("Blue");
        blueButton.setPreferredSize(new Dimension(50, 35));
        JButton eraserButton = createIconButton("Eraser.png", new EraserButtonListener());
        eraserButton.setToolTipText("Eraser");
        JButton undoButton = createIconButton("undo.png", new UndoButtonListener());
        undoButton.setToolTipText("Undo");
        JButton clearAllButton = new JButton("Clear All");
        dottedCheckbox = new JCheckBox("Dotted");
        filledCheckbox = new JCheckBox("Filled");
        JButton saveButton = createIconButton("Save.png", new SaveButtonListener());
        saveButton.setToolTipText("Save");
        JButton openButton = new JButton("Open");

        lineButton.addActionListener(new LineButtonListener());
        rectangleButton.addActionListener(new RectangleButtonListener());
        ovalButton.addActionListener(new OvalButtonListener());
        freehandButton.addActionListener(new FreehandButtonListener());
        redButton.addActionListener(new RedButtonListener());
        greenButton.addActionListener(new GreenButtonListener());
        blueButton.addActionListener(new BlueButtonListener());
        clearAllButton.addActionListener(new ClearAllButtonListener());
        eraserButton.addActionListener(new EraserButtonListener());
        undoButton.addActionListener(new UndoButtonListener());
        saveButton.addActionListener(new SaveButtonListener());
        openButton.addActionListener(new OpenButtonListener());
        dottedCheckbox.addItemListener(new DottedCheckboxListener());
        filledCheckbox.addItemListener(new FilledCheckboxListener());

        // Set background colors for the buttons
        lineButton.setBackground(Color.WHITE);
        rectangleButton.setBackground(Color.WHITE);
        ovalButton.setBackground(Color.WHITE);
        freehandButton.setBackground(Color.WHITE);
        redButton.setBackground(Color.RED);
        greenButton.setBackground(Color.GREEN);
        blueButton.setBackground(Color.BLUE);
        eraserButton.setBackground(Color.WHITE);
        clearAllButton.setBackground(Color.WHITE);
        undoButton.setBackground(Color.WHITE);
        saveButton.setBackground(Color.WHITE);
        openButton.setBackground(Color.WHITE);

        Font buttonFont = new Font("Arial", Font.BOLD, 16);
        lineButton.setFont(buttonFont);
        rectangleButton.setFont(buttonFont);
        ovalButton.setFont(buttonFont);
        freehandButton.setFont(buttonFont);
        redButton.setFont(buttonFont);
        greenButton.setFont(buttonFont);
        blueButton.setFont(buttonFont);
        eraserButton.setFont(buttonFont);
        clearAllButton.setFont(buttonFont);
        undoButton.setFont(buttonFont);
        saveButton.setFont(buttonFont);
        openButton.setFont(buttonFont);
        Font checkboxFont = new Font("Arial", Font.BOLD, 16);
        dottedCheckbox.setFont(checkboxFont);
        filledCheckbox.setFont(checkboxFont);

        // Add buttons to the control panel to be organized and take the shape of button not the all screen
        controlPanel.add(lineButton);
        controlPanel.add(rectangleButton);
        controlPanel.add(ovalButton);
        controlPanel.add(freehandButton);
        controlPanel.add(redButton);
        controlPanel.add(greenButton);
        controlPanel.add(blueButton);
        controlPanel.add(dottedCheckbox);
        controlPanel.add(filledCheckbox);
        controlPanel.add(eraserButton);
        controlPanel.add(undoButton);
        controlPanel.add(clearAllButton);
        controlPanel.add(saveButton);
        controlPanel.add(openButton);

        // Make the main window visible
        setVisible(true);
    }

    private JButton createIconButton(String iconFileName, ActionListener listener) {
        // Get the URL of the image file
        URL imageURL = getClass().getResource(iconFileName);

        ImageIcon icon = new ImageIcon(imageURL);
        JButton button = new JButton(icon);
        button.addActionListener(listener);
        // Set a specific size for the button
        button.setPreferredSize(new Dimension(65, 35));
        // Ensure that the image fits within the button
        Image scaledImage = icon.getImage().getScaledInstance(45, 30, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(scaledImage));
        return button;
    }

    // ActionListener for Line button
    private class LineButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            drawingPanel.setCurrentShape(0);
            drawingPanel.setCurrentColor(Color.BLACK);
        }
    }

    // ActionListener for Rectangle button
    private class RectangleButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            drawingPanel.setCurrentShape(1);
            drawingPanel.setCurrentColor(Color.BLACK);
        }
    }

    // ActionListener for Oval button
    private class OvalButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            drawingPanel.setCurrentShape(2);
            drawingPanel.setCurrentColor(Color.BLACK);
        }
    }

    // ActionListener for Freehand button
    private class FreehandButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            drawingPanel.setCurrentShape(3);
            drawingPanel.setCurrentColor(Color.BLACK);
        }
    }

    // ActionListener for Red button
    private class RedButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            drawingPanel.setCurrentColor(Color.RED);
        }
    }

    // ActionListener for Green button
    private class GreenButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            drawingPanel.setCurrentColor(Color.GREEN);
        }
    }

    // ActionListener for Blue button
    private class BlueButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            drawingPanel.setCurrentColor(Color.BLUE);
        }
    }

    private class EraserButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            drawingPanel.setCurrentShape(4); // Set the shape to Eraser
            drawingPanel.setCurrentColor(Color.WHITE); // Set the color to white (eraser color)

        }
    }

    // ActionListener for Clear All button
    private class ClearAllButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            drawingPanel.clearAll();
        }
    }

    private class UndoButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (drawingPanel != null) {
                drawingPanel.undo();
            }
        }
    }

    // ActionListener for Save button
    private class SaveButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // Use JFileChooser to choose the file to save
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(DrawingProgram.this);

            // If a file is selected, attempt to save the drawing
            if (result == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();

                try {
                    // Create a BufferedImage and save the drawing to the selected file
                    BufferedImage image = new BufferedImage(drawingPanel.getWidth(), drawingPanel.getHeight(), BufferedImage.TYPE_INT_ARGB);
                    Graphics2D g2d = image.createGraphics();
                    drawingPanel.paint(g2d);
                    g2d.dispose();

                    ImageIO.write(image, "png", fileToSave);
                    JOptionPane.showMessageDialog(DrawingProgram.this, "Drawing saved successfully!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(DrawingProgram.this, "Error saving drawing.");
                }
            }
        }
    }

    // Listener for Dotted checkbox
    private class DottedCheckboxListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            dotted = (e.getStateChange() == ItemEvent.SELECTED);
            if (dotted) {
                filledCheckbox.setSelected(false);
            }
            drawingPanel.setDotted(dotted);
        }
    }

    // Listener for Filled checkbox
    private class FilledCheckboxListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            filled = (e.getStateChange() == ItemEvent.SELECTED);
            if (filled) {
                dottedCheckbox.setSelected(false);
            }
            drawingPanel.setFilled(filled);
        }
    }

    // ActionListener for Open button
    private class OpenButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // Use JFileChooser to choose the file to open
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(DrawingProgram.this);

            // If a file is selected, attempt to open and load the drawing
            if (result == JFileChooser.APPROVE_OPTION) {
                File fileToOpen = fileChooser.getSelectedFile();

                try {
                    // Read the image from the selected file and load it into the drawing panel
                    BufferedImage img = ImageIO.read(fileToOpen);
                    drawingPanel.loadImage(img);
                    JOptionPane.showMessageDialog(DrawingProgram.this, "Image loaded successfully!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(DrawingProgram.this, "Error loading drawing.");
                }
            }
        }
    }

    // Main method to create an instance of the DrawingProgram
    public static void main(String[] args) {
        DrawingProgram drawingProgram = new DrawingProgram();
    }
}

// Class representing the drawing panel
class DrawingPanel extends JPanel {

    // Various properties for the drawing panel
    private ArrayList<Shape> shapes = new ArrayList<>();
    private Color currentColor = Color.BLACK;
    private int currentShape = 0;
    private boolean drawing = false;
    private Point startPoint;
    private Point endPoint;
    private boolean dotted = false;
    private boolean filled = false;
    private ArrayList<Point> freehandPoints = new ArrayList<>();

    // Constructor for the DrawingPanel
    public DrawingPanel() {
        // Set the background color of the drawing panel
        setBackground(Color.WHITE);

        // Add mouse listeners for handling drawing actions
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint();
                if (currentShape == 3) {
                    drawing = true;
                    freehandPoints.clear();
                    freehandPoints.add(startPoint);
                } else {
                    shapes.add(createShape(startPoint, startPoint, currentColor, dotted, filled));
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                endPoint = e.getPoint();
                if (currentShape == 3) {
                    drawing = false;
                    shapes.add(new Freehand(freehandPoints, currentColor, dotted));
                    freehandPoints.clear();  // Clear the points after adding the Freehand shape
                } else {
                    shapes.add(createShape(startPoint, endPoint, currentColor, dotted, filled));
                }
                repaint();
                startPoint = null;
            }

        });

        // Add mouse motion listener for freehand drawing
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                endPoint = e.getPoint();
                if (currentShape == 3) {
                    if (drawing) {
                        freehandPoints.add(endPoint);
                        repaint();
                    }
                } else {
                    // For other shapes --> add and remove the shape while dragging
                    shapes.remove(shapes.size() - 1);
                    shapes.add(createShape(startPoint, endPoint, currentColor, dotted, filled));
                    repaint();
                }
            }
        });
    }

    // Method to create a shape based on current properties
    private Shape createShape(Point start, Point end, Color color, boolean dotted, boolean filled) {
        switch (currentShape) {
            case 0: // Line
                return new Line(start, end, color, dotted);
            case 1: // Rectangle
                return new Rectangle(start, end, color, dotted, filled);
            case 2: //Oval
                return new Oval(start, end, color, dotted, filled);
            case 4: //Eraser
                return new Eraser(start, end, color, dotted);
            default:
                return null;
        }
    }

    // Set the current drawing color
    public void setCurrentColor(Color color) {
        currentColor = color;
    }

    // Set the current drawing shape
    public void setCurrentShape(int shape) {
        currentShape = shape;
    }

    public int getCurrentShape() {
        return currentShape;
    }

    // Set the dotted property
    public void setDotted(boolean isDotted) {
        dotted = isDotted;
        repaint();
    }

    // Set the filled property
    public void setFilled(boolean isFilled) {
        filled = isFilled;
        repaint();
    }

    // Clear all shapes from the drawing panel
    public void clearAll() {
        shapes.clear();
        repaint();
    }

    // Undo the last drawn shape
    public void undo() {
        if (!shapes.isEmpty()) {
            shapes.remove(shapes.size() - 1);
            repaint();
        }
    }

    // Override paintComponent to draw shapes on the panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape shape : shapes) {
            shape.draw(g);
        }
        if (drawing && currentShape == 3) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(currentColor);
            g2d.setStroke(new BasicStroke());
            for (int i = 1; i < freehandPoints.size(); i++) {
                Point p1 = freehandPoints.get(i - 1);
                Point p2 = freehandPoints.get(i);
                g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }
    }

    // Load an image onto the drawing panel
    public void loadImage(BufferedImage img) {
        shapes.add(new ImageShape(img, new Point(0, 0)));
        repaint();
    }

    // Abstract class representing a shape
    abstract class Shape {

        abstract void draw(Graphics g);
    }

    // Class representing a line shape
    class Line extends Shape {

        private final Point start;
        private final Point end;
        private final Color color;
        private final boolean dotted;

        // Constructor for the Line class
        public Line(Point start, Point end, Color color, boolean dotted) {
            this.start = start;
            this.end = end;
            this.color = color;
            this.dotted = dotted;
        }

        // Override method to draw the Line shape on the graphics context
        @Override
        void draw(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(color);

            // Set stroke properties based on whether the shape is dotted or solid
            if (dotted) {
                float[] dashPattern = {3, 3};
                g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 2.0f, dashPattern, 0.0f));
            } else {
                g2d.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f, null, 0.0f));
            }
            g2d.drawLine(start.x, start.y, end.x, end.y);
        }
    }

    // Class representing a rectangle shape
    class Rectangle extends Shape {

        private final Point start;
        private final Point end;
        private final Color color;
        private final boolean dotted;
        private final boolean filled;

        // Constructor for the Rectangle class
        public Rectangle(Point start, Point end, Color color, boolean dotted, boolean filled) {
            this.start = start;
            this.end = end;
            this.color = color;
            this.dotted = dotted;
            this.filled = filled;
        }

        // Override method to draw the Rectangle shape on the graphics context
        @Override
        void draw(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(color);

            // Set stroke properties based on whether the shape is dotted or solid
            if (dotted) {
                float[] dashPattern = {3, 3};
                g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 2.0f, dashPattern, 0.0f));
            } else {
                g2d.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f, null, 0.0f));
            }

            int width = Math.abs(end.x - start.x);
            int height = Math.abs(end.y - start.y);
            int x = Math.min(start.x, end.x);
            int y = Math.min(start.y, end.y);

            // Draw either a filled or an outlined rectangle based on the 'filled' property
            if (filled) {
                g2d.fillRect(x, y, width, height);
            } else {
                g2d.drawRect(x, y, width, height);
            }
        }
    }

    // Class representing an oval shape
    class Oval extends Shape {

        private final Point start;
        private final Point end;
        private final Color color;
        private final boolean dotted;
        private final boolean filled;

        // Constructor for the Oval class
        public Oval(Point start, Point end, Color color, boolean dotted, boolean filled) {
            this.start = start;
            this.end = end;
            this.color = color;
            this.dotted = dotted;
            this.filled = filled;
        }

        // Override method to draw the Oval shape on the graphics context
        @Override
        void draw(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(color);

            // Set stroke properties based on whether the shape is dotted or solid
            if (dotted) {
                float[] dashPattern = {3, 3};
                g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 2.0f, dashPattern, 0.0f));
            } else {
                g2d.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f, null, 0.0f));
            }

            int width = Math.abs(end.x - start.x);
            int height = Math.abs(end.y - start.y);
            int x = Math.min(start.x, end.x);
            int y = Math.min(start.y, end.y);

            // Draw either a filled or an outlined oval based on the 'filled' property
            if (filled) {
                g2d.fillOval(x, y, width, height);
            } else {
                g2d.drawOval(x, y, width, height);
            }
        }
    }

    // Class representing an image shape
    class ImageShape extends Shape {

        private final BufferedImage image;
        private final Point position;

        // Constructor for the ImageShape class
        public ImageShape(BufferedImage image, Point position) {
            this.image = image;
            this.position = position;
        }

        // Override method to draw the image on the graphics context
        @Override
        void draw(Graphics g) {
            g.drawImage(image, position.x, position.y, null);
        }
    }

    // Class representing a Freehand shape
    class Freehand extends Shape {

        private final ArrayList<Point> points;
        private final Color color;
        private final boolean dotted;

        // Constructor for the Freehand class
        public Freehand(ArrayList<Point> points, Color color, boolean dotted) {
            this.points = new ArrayList<>(points);
            this.color = color;
            this.dotted = dotted;
        }

        // Override method to draw the Freehand shape on the graphics context
        @Override
        void draw(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(color);

            // Set stroke properties based on whether the shape is dotted or solid
            if (dotted) {
                float[] dashPattern = {3, 3};
                g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 2.0f, dashPattern, 0.0f));
            } else {
                g2d.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f, null, 0.0f));
            }

            // Draw lines connecting the points to represent the Freehand shape
            for (int i = 1; i < points.size(); i++) {
                Point p1 = points.get(i - 1);
                Point p2 = points.get(i);
                g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }
    }

    // Class representing an Eraser shape (extends Rectangle)
    class Eraser extends Rectangle {

        // Constructor for the Eraser class
        public Eraser(Point start, Point end, Color color, boolean dotted) {
            super(start, end, Color.WHITE, dotted, true);
        }

        // Override method to draw the Eraser shape on the graphics context
        @Override
        void draw(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            super.draw(g2d);

        }
    }
}