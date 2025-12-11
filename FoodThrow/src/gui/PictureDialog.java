package gui;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class PictureDialog extends JDialog 
{
    public PictureDialog(int width, int height, float scaleW, float scaleH, BufferedImage comparedImage)
    {
        super();
        int w = (int) (width * scaleW);
        int h = (int) (height * scaleH);
        BufferedImage dstImg = new BufferedImage(w, h, comparedImage.getType());
        AffineTransform scalingTransform = new AffineTransform();
        scalingTransform.scale(scaleW, scaleH);
        AffineTransformOp scaleOp = new AffineTransformOp(scalingTransform, AffineTransformOp.TYPE_BILINEAR);
        dstImg = scaleOp.filter(comparedImage, dstImg);
        setTitle("Photo");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize((width / 2) + 100, (height / 2) + 100);
        setLocationRelativeTo(null);
        setModal(true);
        setResizable(false);
        add(new JLabel(new ImageIcon(dstImg)));
    }
}
