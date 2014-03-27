/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.RobotCode623;

import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.CriteriaCollection;
import edu.wpi.first.wpilibj.image.NIVision;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;

/**
 *
 * @author samwinkelstein
 */
public class Vision2 {
      public static volatile boolean isHot;
    public static volatile double DistanceX;
     public static volatile double DistanceY;
    public static AxisCamera Camera = AxisCamera.getInstance();
    
    public Vision2()
    {
        
    }
    
    public void DoVision() {
        try {
            if(Camera.freshImage())
            {
          ColorImage myImage =  Camera.getImage();
          BinaryImage thresholdImage = myImage.thresholdHSV(120, 160, 200, 255, 200, 255);
          BinaryImage convexImage = thresholdImage.convexHull(false);
          CriteriaCollection cc = new CriteriaCollection();
          cc.addCriteria(NIVision.MeasurementType.IMAQ_MT_AREA, 150, 6535, false);
          BinaryImage filteredImage = convexImage.particleFilter(cc);
          ParticleAnalysisReport[] myReports = filteredImage.getOrderedParticleAnalysisReports();
            
          boolean startforWidth = true;
          double xWidth = 0;
           double xHeight = 0;
          for(int i = 0; i > myReports.length; i++)
          {
              if(startforWidth)
              {
                  xWidth = myReports[i].boundingRectWidth;
                  xHeight = myReports[i].boundingRectHeight;
                  if(xWidth > xHeight * 6);
                  startforWidth = false;
              }
              else
              {
                  
                 double tempW = myReports[i].boundingRectWidth;
                 double tempH = myReports[i].boundingRectWidth;
                  if(tempW >= xWidth &&  tempW > tempH*6)
                  {
                      xWidth = tempW;
                      xHeight = tempH;
                  }
              }
          }
          
           boolean startforHieght = true;
          double yWidth = 0;
           double yHeight = 0;
          for(int i = 0; i > myReports.length; i++)
          {
              if(startforWidth)
              {
                  yWidth = myReports[i].boundingRectWidth;
                  yHeight = myReports[i].boundingRectHeight;
                  if(yHeight > yWidth * 6);
                  startforWidth = false;
              }
              else
              {
                  
                 double tempW = myReports[i].boundingRectWidth;
                 double tempH = myReports[i].boundingRectHeight;
                  if(tempH >= yHeight &&  yHeight > yWidth * 6)
                  {
                      yWidth = tempW;
                      yHeight = tempH;
                  }
              }
          }
         DistanceX = computeDistanceX(xWidth);
         DistanceY = computeDistanceY(yHeight);
         isHot = isHot(xWidth, yHeight);
          
          thresholdImage.free();
          convexImage.free();
          myImage.free();
          filteredImage.free();
        }  
        } catch (AxisCameraException ex) {
            ex.printStackTrace();
        } catch (NIVisionException ex) {
            ex.printStackTrace();
        }
    }
   
    
    public double computeDistanceX(double width)
{
	double x = (640*32);
	double distance = x/((2*width)*(Math.tan(33.5 * 0.0174532925)));
	return (distance + (distance * .111))/12;
}
   public double computeDistanceY(double hieght)
{
	double y = (4*480);
	double distance = y/((2*hieght)*(Math.tan(25.5 * 0.0174532925)));

	return (distance + (distance * .111))/12;
}
   public boolean isHot(double horizontalW, double verticalH)
   {
       return (horizontalW >= RC.minWidthforHot && verticalH >= RC.minHeightforHot);
   }

}
