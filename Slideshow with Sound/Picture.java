import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * Copyright Georgia Institute of Technology 2004-2005
 * @author Barbara Ericson ericson@cc.gatech.edu
 * 
 /* Filename: Picture.java
 
 * Created by: Rui Yan, cs8afbfd and Mengying Li, cs8afabf
 * 
 * PID:
 * Rui Yan: A92089334
 * Mengying Li: A92124721
 * 
 * Email: 
 * Rui Yan: r9yan@ucsd.edu, 
 * Mengying Li: mel116@ucsd.edu
 * 
 * Partner Histories:
 * Rui Yan:
 PSA0 Partner:  Shijie Ji, cs8afbdh
 PSA1 Partner:  Shijie Ji, cs8afbdh
 PSA2 Partner:  Shijie Ji, cs8afbdh
 PSA3 Partner:  Shijie Ji, cs8afbdh
 PSA4 Partner:  Mengying Li, cs8afabf
 PSA5 Partner:  Mengying Li, cs8afabf
 PSA6 Partner:  Mengying Li, cs8afabf
 PSA7 Partner:  Mengying Li, cs8afabf
 PSA8 Partner:  Mengying Li, cs8afabf
 
 * Mengying Li:
 PSA0 Partner:  Wendi Wu, cs8afacq
 PSA1 Partner:  David Amadeo, cs8afbcl
 PSA2 Partner:  Xinyu Wu, cs8afafl
 PSA3 Partner:  David Amadeo, cs8afbcl
 PSA4 Partner:  Rui Yan, cs8afbfd
 PSA5 Partner:  Rui Yan, cs8afbfd
 PSA6 Partner:  Rui Yan, cs8afbfd
 PSA7 Partner:  Rui Yan, cs8afbfd
 PSA8 Partner:  Rui Yan, cs8afbfd
 
 * Date: 12/01/2015
 * Due Date:12/01/2015
 */ 

public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param width the width of the desired picture
   * @param height the height of the desired picture
   */
  public Picture(int width, int height)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /**PSA4 Parta
    * 1)Horizontal flip method
    * A method to flip the image horizontally
    * width: the width of the calling object
    * limit1, limit2: the limited value of i and j
    * temColor: the reference in the Color class to temporarly 
    * store the color of right Pixel.
    * returns nothing 
    */
  public void flipHorizontal()
  {
    Color tempColor;
    int width= this.getWidth();
    int limit1 = width / 2;
    int limit2 = this.getHeight();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    for(int j = 0; j < limit2; j++)
    {
      for(int i = 0; i < limit1; i++)
      {
        leftPixel = getPixel(i,j);
        rightPixel = getPixel(width-1-i,j);
        tempColor = rightPixel.getColor();
        rightPixel.setColor(leftPixel.getColor());
        leftPixel.setColor(tempColor);
      }
    }
  }
  
  /** 2) Vertical flip method 
    * A method to flip the image vertically
    * height: the height of the calling object
    * limit1, limit2: the limited value of i and j
    * temColor: the reference in the Color class to temporarly 
    * store the color of right Pixel.
    * return nothing 
    */
  public void flipVertical()
  {
    Color tempColor;
    int limit1 = this.getWidth();
    int height=this.getHeight();
    int limit2 = height/2;
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    for(int i = 0; i < limit1; i++)
    {
      for(int j = 0; j < limit2; j++)
      {
        topPixel = getPixel(i,j);
        bottomPixel = getPixel(i,height-1-j);
        tempColor = bottomPixel.getColor();
        bottomPixel.setColor(topPixel.getColor());
        topPixel.setColor(tempColor);
      }
    }
  }
  
  /**3) The grayscale method
    * A method to turn the calling object into grayscale
    * average: the average value of the value of Red, Green and Blue in pixel
    * p: pixel in calling object
    * returns nothing */
  public void grayscale()
  {
    Pixel[] pixelArray=this.getPixels();
    Pixel p=null;
    int average;
    for (int index=0; index < pixelArray.length; index++)
    {
      p=pixelArray[index];
      average=(int)((p.getRed() + p.getGreen() + p.getBlue()) / 3);
      p.setRed(average);
      p.setBlue(average);
      p.setGreen(average);
    }
  }
  
  /**4)The collage method
    * A method to create the collage of our modified pictures.
    * pictures: an array of pictures in the class Picture
    * sourecPixel,targetPixel:the pixel in the source picture and target picture
    * sourceX,sourceY,targetX,targetY:the x-coordinate and y-coordinate of the 
    * pixel in source and target picture
    * returns nothing 
    */
  public void collage(Picture [] pictures)
  {
    Pixel sourcePixel=null;
    Pixel targetPixel=null;
    int sourceX;
    int sourceY;
    int targetX;
    int targetY;
    for(int i = 0; i < pictures.length; i++)
    {
      for(sourceX = 0, targetX=(int)(i*pictures[i].getWidth());
          sourceX < pictures[i].getWidth();
          sourceX++,targetX++)
      {
        for(sourceY = 0, targetY=0; 
            sourceY < this.getHeight();
            sourceY++,targetY++)
        {
          sourcePixel = pictures[i].getPixel(sourceX,sourceY);
          targetPixel = this.getPixel(targetX,targetY);
          targetPixel.setColor(sourcePixel.getColor());
        }
      }
    }
  }
  
  /**PSA4a Partb
    * 1)flipVerticalSquare method
    * A method to flip a square of dimensions length by length 
    * in the vertical orientation (x value of the pixel remains the same)
    * x, y: the x and y coordinates of the bottom left corner of the square
    * length:the length of the square
    * returns nothing */
  public void flipVerticalSquare(int x, int y, int length)
  { 
    Color tempColor;
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    
    int limit1 = length;
    int limit2 = length/2;
    
    for(int i = 0; i < limit1; i++)
    {
      for(int j = 0; j < limit2; j++)
      {
        topPixel = getPixel(x+i,y-(length-1)+j);
        bottomPixel = getPixel(x+i,y-j);
        tempColor = topPixel.getColor();
        topPixel.setColor(bottomPixel.getColor());
        bottomPixel.setColor(tempColor);
      }
    }
  }
  
  /**2)flipHorizontalSquare method
    * A method to flip a square of dimensions length by length 
    * in the horizontal orientation (x value of the pixel remains the same)
    * x, y: the x and y coordinates of the bottom left corner of the square
    * length:the length of the square
    * returns nothing */
  public void flipHorizontalSquare(int x, int y, int length)
  {
    Color tempColor;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int limit1 = length/2;
    int limit2 = length;
    for(int i = 0; i < limit1; i++)
    {
      for(int j = 0; j < limit2; j++)
      {
        leftPixel = getPixel(x+i,y-(length-1)+j);
        rightPixel = getPixel(x+(length-1)-i,y-(length-1)+j);
        tempColor = leftPixel.getColor();
        leftPixel.setColor(rightPixel.getColor());
        rightPixel.setColor(tempColor);
      }
    }  
  }
  
  /**StarPointsPSA4: scramble method
    * A method to divide images(originalPicture) into a 3x3 grid,
    * and randomly scramble the order of each sector of the grid.
    * originalPicture: the three pictures we want to scramble in the starpoints
    * returns nothing */
  public void scramble(Picture originalPicture)
  {
    Pixel sourcePixel = null;
    Pixel targetPixel = null;
    int sourceX;
    int sourceY;
    int targetX;
    int targetY;
    Picture[] picArray = new Picture[9];
    for (int i = 0; i < 9; i++) {
      picArray[i] = new Picture
        (originalPicture.getWidth() / 3, originalPicture.getHeight() / 3);
    }
    
    for(int i = 0; i < 9; i++)
    {
      for (sourceX = (i % 3 ) * (this.getWidth() / 3), targetX = 0; 
           sourceX < (int) ((i % 3) + 1) * (this.getWidth() / 3); 
           sourceX++, targetX++)
      {
        for (sourceY = (int) (i / 3) * (this.getHeight() / 3), targetY = 0; 
             sourceY < (int) ((i/3) + 1) * (this.getHeight() / 3); 
             sourceY++, targetY++)
        { 
          sourcePixel = originalPicture.getPixel(sourceX,sourceY);
          targetPixel = picArray[i].getPixel(targetX,targetY);
          targetPixel.setColor(sourcePixel.getColor());
        }
      }
    }
    
    for (int i = 0; i < 9; i++)
    {
      int k = (int) (Math.random() * 9);
      Picture temp = picArray[k];
      picArray[k] =  picArray[i];
      picArray[i] = temp;
    }
    
    for(int i = 0; i < picArray.length; i++)
    {
      for(sourceX = 0, targetX= (int) (i % 3) * (this.getWidth() / 3); 
          sourceX < picArray[i].getWidth(); sourceX++, targetX++)
      {
        for(sourceY = 0, targetY= (int) (i / 3) * (this.getHeight() / 3); 
            sourceY < picArray[i].getHeight(); sourceY++, targetY++)
        {
          sourcePixel = picArray[i].getPixel(sourceX,sourceY);
          targetPixel = this.getPixel(targetX,targetY);
          targetPixel.setColor(sourcePixel.getColor());
        }
      }
    }
  }
}

