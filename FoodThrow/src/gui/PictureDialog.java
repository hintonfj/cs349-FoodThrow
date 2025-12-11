package gui;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 * Dialog with a photo
 */
public class PictureDialog extends JDialog 
{
    public PictureDialog(float scaleW, float scaleH, BufferedImage comparedImage)
    {
        super();
        int w = (int) (comparedImage.getWidth() * scaleW);
        int h = (int) (comparedImage.getHeight() * scaleH);
        BufferedImage dstImg = new BufferedImage(w, h, comparedImage.getType());
        AffineTransform scalingTransform = new AffineTransform();
        scalingTransform.scale(scaleW, scaleH);
        AffineTransformOp scaleOp = new AffineTransformOp(scalingTransform, AffineTransformOp.TYPE_BILINEAR);
        scaleOp.filter(comparedImage, dstImg);
        setTitle("Photo");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setModal(false);
        setResizable(false);
        add(new JLabel(new ImageIcon(dstImg)));
        pack();
    }
}
