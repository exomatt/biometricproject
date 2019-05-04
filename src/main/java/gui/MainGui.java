/*
 * Created by JFormDesigner on Fri May 03 22:20:43 CEST 2019
 */

package gui;

import binarization.BinarizationOperations;
import binarization.ColorChanger;
import imageoperation.ImageReaderSaver;
import org.jdesktop.layout.GroupLayout;
import szkieletyzacja.KMM;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import static szkieletyzacja.KMM.copyImage;

/**
 * @author exo
 */
public class MainGui extends JFrame {
    public MainGui() {
        initComponents();
    }

    private BufferedImage originalImage;

    private void menuItemLoadFileActionPerformed(ActionEvent e) {
        JFileChooser imageOpener = new JFileChooser();
        imageOpener.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                String fileName = f.getName().toLowerCase();
                return fileName.endsWith(".jpg") || fileName.endsWith(".png")
                        || fileName.endsWith(".tiff") || fileName.endsWith(".bmp") || fileName.endsWith(".svg");
            }

            @Override
            public String getDescription() {
                return "Image files (.jpg, .png, .tiff, .bmp, .svg)";
            }
        });

        int returnValue = imageOpener.showDialog(null, "Select image");
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            originalImage = ImageReaderSaver.loadImage(imageOpener.getSelectedFile().getPath());
            this.imageLabel.setIcon(new ImageIcon(originalImage));
        }
    }

    private void menuItemSaveFileActionPerformed(ActionEvent e) {
        JFileChooser imageOpener = new JFileChooser();
        imageOpener.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        imageOpener.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                String fileName = f.getName().toLowerCase();
                if (fileName.endsWith(".jpg") || fileName.endsWith(".png")
                        || fileName.endsWith(".tiff") || fileName.endsWith(".bmp") || fileName.endsWith(".svg")) {
                    return true;
                } else return false;
            }

            @Override
            public String getDescription() {
                return "Image files (.jpg, .png, .tiff, .bmp, .svg)";
            }
        });
        int returnValue = imageOpener.showDialog(null, "Save image");
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            String path = imageOpener.getSelectedFile().getPath();
            BufferedImage img = ImageReaderSaver.convertIconToImage((ImageIcon) this.imageLabel.getIcon());
            ImageReaderSaver.saveImage(img, path);
        }
    }

    private void menuItemToGrayActionPerformed(ActionEvent e) {
        ColorChanger changer = new ColorChanger();
        originalImage = changer.changeToGrey(originalImage, 3);
        imageLabel.setIcon(new ImageIcon(originalImage));
    }

    private void menuItemOtsuActionPerformed(ActionEvent e) {
        BinarizationOperations binarizationOperations = new BinarizationOperations();
        int otsuThreshold = binarizationOperations.thresholdOtsu(copyImage(originalImage));
        originalImage = binarizationOperations.userValueBinarization(copyImage(originalImage), otsuThreshold);
        imageLabel.setIcon(new ImageIcon(originalImage));
    }

    private void menuItemKMMActionPerformed(ActionEvent e) {
        KMM kmm = new KMM();
        BufferedImage bufferedImage = kmm.kmmAlgorithm(copyImage(originalImage));
        ImageShow imageShow = new ImageShow(bufferedImage);
        imageShow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        imageShow.setVisible(true);
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - exo
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItemLoadFile = new JMenuItem();
        menuItemSaveFile = new JMenuItem();
        menu2 = new JMenu();
        menuItemToGray = new JMenuItem();
        menu3 = new JMenu();
        menuItemOtsu = new JMenuItem();
        menu4 = new JMenu();
        menuItemKMM = new JMenuItem();
        scrollPane1 = new JScrollPane();
        imageLabel = new JLabel();

        //======== this ========
        Container contentPane = getContentPane();

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("file");

                //---- menuItemLoadFile ----
                menuItemLoadFile.setText("load file");
                menuItemLoadFile.addActionListener(e -> menuItemLoadFileActionPerformed(e));
                menu1.add(menuItemLoadFile);

                //---- menuItemSaveFile ----
                menuItemSaveFile.setText("save file");
                menuItemSaveFile.addActionListener(e -> menuItemSaveFileActionPerformed(e));
                menu1.add(menuItemSaveFile);
            }
            menuBar1.add(menu1);

            //======== menu2 ========
            {
                menu2.setText("color change");

                //---- menuItemToGray ----
                menuItemToGray.setText("to Averange Gray ");
                menuItemToGray.addActionListener(e -> menuItemToGrayActionPerformed(e));
                menu2.add(menuItemToGray);
            }
            menuBar1.add(menu2);

            //======== menu3 ========
            {
                menu3.setText("binarization");

                //---- menuItemOtsu ----
                menuItemOtsu.setText("otsu");
                menuItemOtsu.addActionListener(e -> menuItemOtsuActionPerformed(e));
                menu3.add(menuItemOtsu);
            }
            menuBar1.add(menu3);

            //======== menu4 ========
            {
                menu4.setText("szkieletyzacja");

                //---- menuItemKMM ----
                menuItemKMM.setText("KMM");
                menuItemKMM.addActionListener(e -> {
                    menuItemKMMActionPerformed(e);
                });
                menu4.add(menuItemKMM);
            }
            menuBar1.add(menu4);
        }
        setJMenuBar(menuBar1);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(imageLabel);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .add(contentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .add(scrollPane1, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(467, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .add(contentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .add(scrollPane1, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(205, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - exo
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItemLoadFile;
    private JMenuItem menuItemSaveFile;
    private JMenu menu2;
    private JMenuItem menuItemToGray;
    private JMenu menu3;
    private JMenuItem menuItemOtsu;
    private JMenu menu4;
    private JMenuItem menuItemKMM;
    private JScrollPane scrollPane1;
    private JLabel imageLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
